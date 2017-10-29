
package arprast.qiyosq.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import arprast.qiyosq.model.MenusModel;

@Repository
public interface MenusDao extends PagingAndSortingRepository<MenusModel, Long> {

}
