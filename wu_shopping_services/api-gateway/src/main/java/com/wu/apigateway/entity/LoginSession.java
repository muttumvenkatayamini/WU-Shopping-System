package com.wu.apigateway.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "login_session")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class LoginSession {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;

	@Column(name = "username")
	private String username;

	@Column(name = "sessionId")
	private String sessionId;

	@Column(name = "loginDT")
	private Date loginDT;

	@Column(name = "expiryDT")
	private Date expiryDT;
}
