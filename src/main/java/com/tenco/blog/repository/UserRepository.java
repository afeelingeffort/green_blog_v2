package com.tenco.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tenco.blog.model.User;

// @Repository // 여기서는 생략 가능 - 이유는 JpaRepository<>가 내부적으로 처리해줌
public interface UserRepository extends JpaRepository<User, Integer>{
	// select, selectAll, insert, update, delete 생성해줌
	
	
}
