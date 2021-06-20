<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js" type="text/javascript"></script>
<script>

function delCategory() {
	if (confirm("카테고리에 속한 글도 모두 삭제됩니다.\n카테고리를 정말 삭제하시겠습니까?")) {
		return true;
	} else{
		return false;
	}
};
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/blog/admin/includes/header.jsp" />
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/blog/admin/includes/menu.jsp" />
				<table class="admin-cat" id="admin-cat">
					<tr>
						<th>번호</th>
						<th>카테고리명</th>
						<th>포스트 수</th>
						<th>설명</th>
						<th>삭제</th>
					</tr>
					<c:set var="count" value="${fn:length(categoryVo) }" />
					<c:if test="${ count eq 0}">
						<tr>
							<td colspan="5">
								<h3>카테고리가 없습니다.</h3>
								<h3>카테고리를 입력해 주세요.</h3>
							</td>
						</tr>
					</c:if>


					<c:forEach var="vo" items="${categoryVo }" varStatus="status">
						<tr>
							<td>${count-status.index}</td>
							<td>${vo.name }</td>
							<td>${vo.postCnt }</td>
							<td>${vo.desc }</td>
							<td>
							<a href="${pageContext.request.contextPath }/${authUser.id}/admin/delete/${vo.no}" onclick="return delCategory();">
								<img src="${pageContext.request.contextPath}/assets/images/delete.jpg">
							</a>
							</td>
						</tr>
					</c:forEach>
				</table>

				<form action="${pageContext.request.contextPath}/${authUser.id}/admin/category" method="post">
					<h4 class="n-c">새로운 카테고리 추가</h4>
					<table id="admin-cat-add">
						<tr>
							<td class="t">카테고리명</td>
							<td><input type="text" name="name"></td>
						</tr>
						<tr>
							<td class="t">설명</td>
							<td><input type="text" name="desc"></td>
						</tr>
						<tr>
							<td class="s">&nbsp;</td>
							<td><input type="submit" value="카테고리 추가"></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/blog/admin/includes/footer.jsp" />
	</div>
</body>
</html>