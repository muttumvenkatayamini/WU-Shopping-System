package com.wu.apigateway.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wu.apigateway.entity.LoginSession;

@Repository
public interface LoginSessionRepository extends JpaRepository<LoginSession, Long> {
	@Query("select a from LoginSession a where a.username =:username and a.sessionId =:sessionId and a.expiryDT > :expiryDT")
	Optional<LoginSession> findAllByUserNameAndSessionIdWithCreationDateTimeAfter(@Param("username") String username,
			@Param("sessionId") String sessionId, @Param("expiryDT") Date expiryDT);
}
