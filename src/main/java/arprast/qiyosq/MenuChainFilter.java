/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arprast.qiyosq;

import arprast.qiyosq.model.RolesModel;
import arprast.qiyosq.ref.ActionType;
import arprast.qiyosq.services.AuthorizationService;
import arprast.qiyosq.services.MenuService;
import arprast.qiyosq.services.RolesService;
import arprast.qiyosq.util.LogsUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

public class MenuChainFilter implements Filter {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final StringBuilder authorityLogs = new StringBuilder();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// injector filter chain to spring
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, filterConfig.getServletContext());
	}

	@Autowired
	AuthorizationService authorizationService;

	@Autowired
	RolesService rolesService;

	@Autowired
	MenuService menuService;

	@Override
	@Transactional
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRquest = (HttpServletRequest) servletRequest;

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();

		// Convert Object to List
		List<?> listAuthorities = new ArrayList<Object>(auth.getAuthorities());
		List<String> listAuthoritiesString = new ArrayList<>(listAuthorities.size());
		for (Object xx : listAuthorities) {
			listAuthoritiesString.add(xx.toString());
		}

		// Looking for id from sys_roles
		List<RolesModel> listId = rolesService.getListIdByName(listAuthoritiesString);
		// convert SysRoles.getId to List Long
		List<Long> listLongId = getListAuthorities(listId);

		for (int a = 0; a < listAuthorities.size(); a++) {
			authorityLogs.append(listAuthorities.get(a));
			if (a < (listAuthorities.size() - 1)) {
				authorityLogs.append(",");
			}
		}
		LogsUtil.logDebug(logger, true, ActionType.ACCESS_PAGE, "User login : {}, Access page : {}, Authorities : {}",
				name, httpServletRquest.getRequestURI(), authorityLogs);
		authorityLogs.delete(0, authorityLogs.length());

		StringBuilder tmpScript = new StringBuilder();
		String listMenu = menuService.getScreenMenu(listLongId, 0, tmpScript).toString();
		servletRequest.setAttribute("scriptMenu", listMenu);
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {

	}

	private List<Long> getListAuthorities(List<RolesModel> listRoles) {
		List<Long> listId = new ArrayList<>();
		for (RolesModel sysRole : listRoles) {
			listId.add(sysRole.getId());
		}
		return listId;
	}
}
