/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arprast.qiyosq.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import arprast.qiyosq.model.UserModel;

/**
 *
 * @author ari-prasetiyo
 */
@Repository
public interface UserDao extends JpaRepository<UserModel, Long> {

	/**
	 * 
	 * @param email
	 * @return Integer
	 */
	@Query("select count(id) from UserModel where email = :nEmail ")
	public int countUserByEmail(@Param("nEmail") String email);

	/**
	 * 
	 * @param email
	 * @return UserModel
	 */
	@Query("from UserModel where email = :nEmail ")
	public UserModel findUserByEmail(@Param("nEmail") String email);

	/**
	 * 
	 * @param email
	 * @param userName
	 * @param password
	 * @return Integer
	 */
	@Query("select count(id) from UserModel where email = :nEmail and username=:nUserName and password =:nPassword ")
	public int countUserByEmailAndPassword(@Param("nEmail") String email, @Param("nUserName") String userName,
			@Param("nPassword") String password);

	/*
	 * @Autowired
	 * 
	 * @PersistenceContext private EntityManager em;
	 * 
	 * @SuppressWarnings("unchecked") public List<UserModel> listAllUser(int
	 * offset, int limit) { return
	 * em.createQuery("from UserModel order by username asc").setFirstResult(
	 * offset).setMaxResults(limit) .getResultList(); }
	 * 
	 * @SuppressWarnings("unchecked") public List<UserModel>
	 * listUserSearchByUserName(int offset, int limit, String keySearch) {
	 * return em.
	 * createQuery("from UserModel where username like :searchUserName order by username asc"
	 * ) .setParameter("searchUserName", "%" + keySearch +
	 * "%").setFirstResult(offset).setMaxResults(limit) .getResultList(); }
	 * 
	 * @Modifying public int deleteByUserId(long userId) { return em.
	 * createNativeQuery("delete from sys_user_roles where sys_user_id =:nUserId"
	 * ) .setParameter("nUserId", userId).executeUpdate(); }
	 * 
	 * @Transactional(rollbackFor = { Exception.class, Throwable.class,
	 * IllegalArgumentException.class }, readOnly = false) public UserModel
	 * saveEditUserRole(UserModel user, boolean isEdit) { TransactionStatus
	 * TransactionStatus = TransactionAspectSupport.currentTransactionStatus();
	 * try { if (isEdit) { deleteByUserId(user.getId()); } user = save(user);
	 * return user; } catch (Exception e) { TransactionStatus.setRollbackOnly();
	 * // System.err.println("rollbacckk"); e.printStackTrace(); } return null;
	 * }
	 * 
	 * public UserModel saveUser(UserModel user) { user = save(user); return
	 * user; }
	 */
}
