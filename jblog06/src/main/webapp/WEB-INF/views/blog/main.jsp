<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
pageContext.setAttribute("newline", "\n");
%>

<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/blog/admin/includes/header.jsp" />
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<c:set var="count" value="${fn:length(postList) }"/>				
						<c:choose>
							<c:when test="${count eq 0 }">
								<h4 class="post-empty">아직 작성된 글이 없습니다</h4>
								<c:if test="${id eq authUser.id }">
									<p class="write-post">
										<a href="${pageContext.request.contextPath }/${authUser.id}/admin/write">글 작성하기</a>
									</p>
								</c:if>
							</c:when>
							<c:otherwise>
								<h4>${postVo.title}</h4>
								<p>
								<p>${fn:replace(postVo.contents ,newline,"<br/>")}
						</c:otherwise>
					</c:choose>
				</div>
				<ul class="blog-list">
					<c:forEach var="post" items="${postList }">
						<li ><a href="${pageContext.request.contextPath}/${id}/${post.categoryNo}/${post.no}">${post.title }</a><span>${post.regDate }</span></li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}/${blogVo.logo}">
			</div>
		</div>

		<div id="navigation">
			<h2 style="margin-bottom: 10px">${id }</h2>
			<h2><a href="${pageContext.request.contextPath }/${id}">전체보기</a></h2>
			<ul>
			<c:forEach var="category" items="${categoryVo }">
				<li><a href="${pageContext.request.contextPath}/${id}/${category.no}">${category.name }</a></li>
			</c:forEach>
			</ul>
		</div>
		<c:import url="/WEB-INF/views/blog/admin/includes/footer.jsp" />
	</div>
</body>
</html>