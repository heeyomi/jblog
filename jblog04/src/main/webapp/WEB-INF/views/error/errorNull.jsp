<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body style="text-align: center;">
	<a href="${pageContext.request.contextPath }"><img src="${pageContext.request.contextPath }/assets/images/logo.jpg" style="margin-top: 50px"></a>
	<h1>죄송합니다.<br>요청하신 페이지를 찾을 수 없습니다.</h1>
	<pre>
	
	방문하시려는 페이지의 주소가 잘못 입력되었거나,
	페이지의 주소가 변경 혹은 삭제되어 요청하신 페이지를 찾을 수 없습니다.
	
	입력하신 주소가 정확한지 다시 한번 확인해 주시기 바랍니다.
	</pre>
	<a href="${pageContext.request.contextPath }">메인으로 돌아가기</a>
</body>
</html>