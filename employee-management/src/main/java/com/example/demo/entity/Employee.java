package com.example.demo.entity;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

//@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "employee", uniqueConstraints = {
		@UniqueConstraint(columnNames = {
				"username"
		}),
		@UniqueConstraint(columnNames = {
				"email"
		})
})
public class Employee {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	int id;
	@NotBlank
	@Size(min = 3, max = 50)
	String username;

	@NotBlank
	String fullname;
	@NotBlank
	@Size(max = 50)
	@Email
	String email;
	@JsonIgnore
	@Size(min = 6, max = 100)
	String pass;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	Date birthday;
	@ManyToOne
	@JoinColumn(name = "roleId")
	Roles roleId;
	@ManyToOne
	@JoinColumn(name = "department_id")
	Department department_id;

	@NotBlank
	String degree;
	@NotBlank
	String specialize;

	public Employee() {
	}

	public Employee(String username, String fullname, String email, String pass, Date birthday, Roles roleId,
					Department department, String degree, String specialize) {
		this.username = username;
		this.fullname = fullname;
		this.email = email;
		this.pass = pass;
		this.birthday = birthday;
		this.roleId = roleId;
		this.department_id = department;
		this.degree = degree;
		this.specialize = specialize;
	}



}
