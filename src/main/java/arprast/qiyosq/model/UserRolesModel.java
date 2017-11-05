/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arprast.qiyosq.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author ari-prasetiyo
 */
@Entity
@Table(name = "sys_user_roles")
public class UserRolesModel extends ModelSerializable {

	private static final long serialVersionUID = 2432434267482377275L;
	
    @ManyToOne
    @JoinColumn(nullable = false, name="sys_user_id")
    @Cascade(CascadeType.REMOVE)
    private UserModel sysUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private RolesModel sysRoles;

    public RolesModel getSysRoles() {
        return sysRoles;
    }

    public void setSysRoles(RolesModel sysRoles) {
        this.sysRoles = sysRoles;
    }

    public void setSysRoles(Long id) {
        RolesModel sysRolesId = new RolesModel();
        sysRolesId.setId(id);
        this.sysRoles = sysRolesId;
    }

    public UserModel getSysUser() {
        return sysUser;
    }

    public void setSysUser(UserModel sysUser) {
        this.sysUser = sysUser;
    }

    public void setSysUser(Long id) {
        UserModel sysUserId = new UserModel();
        sysUserId.setId(id);
        this.sysUser = sysUserId;
    }

	@Override
	public String toString() {
		return "UserRolesModel [sysUser=" + sysUser + ", sysRoles=" + sysRoles + "]";
	}

}
