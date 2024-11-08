package com.fileManageent.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepo extends JpaRepository<User, Long>{

	
	@Query("select u from User u where u.UserName= :username")
	public User getUserByUserName(@Param("username") String username);
	
	
	@Query("select u from User u where u.id= :id")
	public User getUserByUserId(@Param("id") Long id);

}
