<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<h1>로그인 화면</h1>
<div class="container">
	<form action="/temp/join2" method="post">
		<div class="form-group">
			<label for="username">username : </label> <input type="text" class="form-control" name="username" id="username" value="짱구">
		</div>

		<div class="form-group">
			<label for="password">password : </label> <input type="password" class="form-control" name="password" id="password" value="1234">
		</div>

		<div class="form-group">
			<label for="email">email : </label> <input type="text" class="form-control" name="email" id="email" value="abc@naver.com">
		</div>
	</form>
	<button type="submit" id="btn--login" class="btn btn-primary">로그인</button>
</div>

<script src="/js/user.js">
	
</script>
<%@ include file="../layout/footer.jsp"%>
