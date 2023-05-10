package com.tenco.blog.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Sort.Direction;
import com.tenco.blog.model.User;
import com.tenco.blog.repository.UserRepository;

import java.util.List;
import org.springframework.data.domain.Pageable;

@RestController // IoC 처리
public class DummyControllerTest {

	// DI 
	// @Autowired
	private UserRepository userRepository;
	
	// @Autowired와 같은 역할
	public DummyControllerTest(UserRepository userRepository) {
		this.userRepository = userRepository; 
	}
	
	// MIME TYPE - application/json
	// 회원 등록 - 샘플
	@PostMapping("/dummy/insert-user")
	public String insertUser(@Validated @RequestBody User user) {
		// 유효성 검사
		System.out.println(user.toString());
		user.setRole("user");
		userRepository.save(user);
		return "회원가입에 성공";
	}
	
	// localhost:8080/dummy/user/1	
	@GetMapping("/dummy/user/{id}")
	public User getUser(@PathVariable Integer id) {
		// Optional - user가 있을 수도 있고 null일 수도 있다.
		
		// 인증검사, 유효성 검사
		Optional<User> userOp = userRepository.findById(id);
		
		// 1. get() : null일 일이 없을 때 사용
		// 2. orElseGet : 데이터가 있으면 그대로 반환 없으면
		// 직접 정의한 객체를 반환시킬 수 있다.
		// 3. orElseThrow : 있으면 반환 없으면 예외던짐
		
		// 1.
//		 User user = userRepository.findById(id).get();
		
		// 2. 
//		User user = userRepository.findById(id).orElseGet(()->{
//			return new User();
//		});
		
		// 3. 
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("해당 유저는 없네요");
		});
		
		System.out.println(user);
		return user;
	}
	
//	@GetMapping("/dummy/users")
//	public Page<User> pageList(@PageableDefault(size = 2, sort = "id",
//			direction = Direction.DESC) Pageable pageable){
////		List<User> users = userRepository.findAll();
//		Page<User> pageUser = userRepository.findAll(pageable);
//		return pageUser;
//	}
	
	@GetMapping("/dummy/users")
	public List<User> pageList(@PageableDefault(size = 2, sort = "id",
			direction = Direction.DESC) Pageable pageable){
		List<User> users = userRepository.findAll();
		Page<User> pageUser = userRepository.findAll(pageable);
		
		return pageUser.getContent();
	}

	// JSON으로 던질 예정
	// Update를 할 때 
	// 1번 방식 기존 로직 처리 (save 처리)
	// 2번 방식 dirty checking (더티 체킹) 사용
	@Transactional // 2번 방식 dirty checking
	@PutMapping("/dummy/user/{id}") // 수정 !
	public User updateUser(@PathVariable Integer id, @Validated @RequestBody User reqUser) {
		
		// 인증 검사, 유효성 검사
		
		// !!! 수정을 하려면 존재여부 확인을 해야한다 !!!
		// 영속화 된 데이터
		User userEntity = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("해당 유저가 존재하지 않습니다.");
		});
		
		// 클라이언트가 던진 데이터 
		// reqUser
		userEntity.setEmail(reqUser.getEmail());
		userEntity.setPassword(reqUser.getPassword());
		
		// 저장 처리
//		userRepository.save(userEntity);
		
		// dirty checking 사용 (트랜잭션이 끝나면 자동으로 save 하는 것.)
		// save를 호출하지 않았는데 변경처리 되었다.
		// 트랜잭션 내에서 트랜잭션이 끝나기 전에 영속성 컨텍스트에 
		// 1차 캐쉬에 들어가 있는 데이터 상태를 변경 감지한다.
		
		return userEntity;
	}
	
	@DeleteMapping("/dummy/user/{id}")
	public String deleteUser(@PathVariable Integer id) {
		userRepository.deleteById(id);
		return "삭제 성공";
	}
	
}
