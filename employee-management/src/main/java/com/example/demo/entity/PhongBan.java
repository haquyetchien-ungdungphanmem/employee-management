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
@Table(name = "phongban")
public class PhongBan {
	@Id
	String phong_ban;
	
	String TenPB;
	
	@JsonIgnore
	@OneToMany(mappedBy = "phongbanId")
	List<NhanVien> nhanvienPB;
	
}
