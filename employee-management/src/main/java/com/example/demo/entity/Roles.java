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
@Table(name = "roles")
public class Roles {
	@Id
	String Id;
	String roles;
	
	@JsonIgnore
	@OneToMany(mappedBy = "roleId")
	List<NhanVien> nhanvien;
}
