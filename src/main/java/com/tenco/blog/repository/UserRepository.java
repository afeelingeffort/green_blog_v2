package com.tenco.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tenco.blog.model.User;

// @Repository // 여기서는 생략 가능 - 이유는 JpaRepository<>가 내부적으로 처리해줌
public interface UserRepository extends JpaRepository<User, Integer> {
	// select, selectAll, insert, update, delete 생성해줌

	// Spring JPA 네이밍 전략
	// 규칙을 지켜서 쓴다면 메서드 이름으로 JPA가 쿼리를 만들어준다.
	// SELECT * FROM user WHERE username = ? AND password = ?;
	User findByUsernameAndPassword(String username, String password);

	// 두번째 방법
	// 네이티브 쿼리 직접 작성
	@Query(value = " SELECT * FROM user WHERE username = ? AND password = ? ", nativeQuery = true)
	User login(String username, String password);

	// 메서드를 통해 쿼리 만들기
	// SELECT * FROM user WHERE username=?
	Optional<User> findByUsername(String username);
}
