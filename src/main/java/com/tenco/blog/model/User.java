package com.tenco.blog.model;

import java.sql.Timestamp;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Data // 주의 ! (Object Mapper)가 파싱 처리할 때 setter 반드시 있어야 함.
public class User {

	// 기본키 매핑
	@Id // pk 설정
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	
	@Column(nullable = false, length = 30)
	private String username;

	@NotBlank // null, "" - 빈문자열을 잡겠다.
	@Column(nullable = false, length = 100)
	private String password;
	
	@NotBlank
	@Column(nullable = false, length = 50)
	private String email;
	
	@CreationTimestamp // 자동 시간 입력 now() 
	private Timestamp createdDate;
	
	@ColumnDefault("'user'") // 문자열 타입이라고 명시는 ''(홑따옴표)
	private String role; // 데이터의 범주화 : user, admin, manager
	
}
	