<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/css2?family=Great+Vibes&family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="resources/css/bootstrap.min.css" />
<link rel="stylesheet" href="resources/css/style.css" />
<link rel="stylesheet" href="resources/css/summernote.min.css" />
</head>
<body>
<h1 class="text-center mt-5">MY BBS</h1>
<div class="container ">
	<form action="replyok" name="write_form" method="post" class="row was-validated">
		<div class="col-md-6">
			<label for="">이름</label>
			<input type="text" name="uname" class="form-control" placeholder="이름" required/>
			<div class="invalid-feedback">이름을 입력하세요.</div>
		</div>
		<div class="col-md-6">
			<label for="">비밀번호</label>
			<input type="password" name="upass" class="form-control" placeholder="비밀번호" required/>
			<div class="invalid-feedback">비밀번호를 입력하세요.</div>
		</div>
		<div class="col-md-12">
			<label for="">제목</label>
			<input type="text" name="title" class="form-control" placeholder="제목" required/>
			<div class="invalid-feedback">제목을 입력하세요.</div>
		</div>
		<div class="col-md-12 mt-3">
			<textarea name="content" id="content" cols="30" rows="10"></textarea>
		</div>
		<input type="hidden" name="s_group" value="${reply.s_group }" />
		<input type="hidden" name="s_step" value="${reply.s_step }" />
		<input type="hidden" name="s_indent" value="${reply.s_indent }" />
		<div class="col-md-12 text-center mt-4">
			<a href="javascript:history.back();" class="btn btn-danger text-white">취소</a>
			&nbsp;
			<button type="submit" class="btn btn-primary">전송</button>
		</div>
	</form>
</div>
	
<script src="resources/js/jquery.min.js"></script>	
<script src="resources/js/popper.min.js"></script>	
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/summernote-bs4.min.js"></script>		
<script src="resources/js/script.js"></script>		
<script src="resources/css/lang/summernote-ko-KR.js"></script>
</body>
</html>