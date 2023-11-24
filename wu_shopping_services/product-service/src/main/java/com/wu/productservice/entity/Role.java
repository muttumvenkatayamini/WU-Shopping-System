package com.wu.productservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "role")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "roleId")
	private long roleId;

	@Column(name = "roleName")
	private String roleName;

}
