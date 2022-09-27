package com.example.demo.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "department")
public class Department {
	@Id
	String department_id;
	
	String departmentname;
	
	@JsonIgnore
	@OneToMany(mappedBy = "department_id")
	List<Employee> employees;
	
}
