package com.javaweb.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.javaweb.entity.User;

@Repository
public interface UserRepository  extends CrudRepository<User, Integer>{
	
	@Query("select u from User u where u.email = :email")
	public User getUserByEmail(@Param("email")String email);
	

	@Query("select u from User u where concat(u.email, u.firstname, u.lastname) like %?1%")
	public User search(String keyword);
	
	@Query("update User u set u.enabled=?2 where u.id=?1")
	@Modifying
	public void updateEnabledStatus(Integer id,boolean enabled);
		
	
	
	
	
}
