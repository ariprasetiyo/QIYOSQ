
package arprast.qiyosq.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import arprast.qiyosq.model.MenusModel;

public interface MenusDao extends PagingAndSortingRepository<MenusModel, Long > {
    
}
