<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html>
<head>
<title>JBlog</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js" type="text/javascript"></script>
<script>
$(function() {
	btn = $('#btn-checkid');
	btn.click(function(){
			var id = $('#id').val();
			if (id == "") {
				return ;
			}
			$.ajax({
				url: "/jblog06/user/api/checkid?id=" + id,
				type:"GET",
				dataType:"json",
				error: function(xhr, status, e){
					console.error(status, e);
				},
				success : function(response) {
					console.log(response);
					
					if (response.result != "success") {
						console.log(response.message);
						return;
					}
				
					if (response.data) {
						alert("이미 사용중인 아이디입니다. 다른 아이디를 입력해 주세요.");
						$("#id").val();
						$("#id").focus();
						return ;
					}
				
					$("#btn-checkid").hide();
					$("#img-checkid").show();
			}
		});
	});
});
</script>

</head>
<body>
	<div class="center-content">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		
		<form:form class = "join-form" action="" id="join-form" method="post" modelAttribute="userVo">
			<label class="block-label" for="name">이름</label>
			<form:input path="name"/>
				<p style="color:red; text-align: left; padding-left: 0px">
					<form:errors path="name" />
				</p>

			<label class="block-label" for="blog-id">아이디</label>
			<form:input path="id"/>
			<input id="btn-checkid" type="button" value="id 중복체크">
			<img id="img-checkid" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">
				<p style="color:red; text-align: left; padding-left: 0px">
					<form:errors path="id" />
				</p>
				
			<label class="block-label" for="password">패스워드</label>
			<form:password path="password"/>
				<p style="color:red; text-align: left; padding-left: 0px">
					<form:errors path="password" />
				</p>
				
			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">
		</form:form>
	</div>
</body>
</html>
