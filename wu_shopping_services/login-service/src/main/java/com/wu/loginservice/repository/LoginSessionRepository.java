package com.wu.loginservice.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wu.loginservice.entity.LoginSession;

@Repository
public interface LoginSessionRepository extends JpaRepository<LoginSession, Long> {
	@Query("select a from LoginSession a where a.username =:username and a.expiryDT > :expiryDT")
	Optional<LoginSession> findAllByUserNameWithCreationDateTimeAfter(@Param("username") String userName,
			@Param("expiryDT") Date expiryDT);
	
//	@Query("delete a from LoginSession a where a.username = :username")
//	String deleteRecords(@Param("username") String username);
}
