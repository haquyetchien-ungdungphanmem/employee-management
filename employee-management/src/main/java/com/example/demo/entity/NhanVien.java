package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

//@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "nhanvien")
public class NhanVien {
	@Id
	
	String username;
	
	String ho_ten;
	String Email;
	String Pass;
	Date ng_sinh;
	@ManyToOne
	@JoinColumn(name = "vi_tri")
	Roles roleId;
	@ManyToOne
	@JoinColumn(name = "phong_ban")
	PhongBan phongbanId;
	String bang_cap;
	String chuyen_mon;
}
