<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>工具</title>

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/static/css/bootstrap.min.css" crossorigin="anonymous">

    <link rel="stylesheet" href="/static/css/video.css" crossorigin="anonymous">
</head>
<body>

<%--头部--%>
<jsp:include page="/WEB-INF/jsp/common/header.jsp"></jsp:include>
<br><br>

<%--首页内容区域--%>
<div class="container">

    <%--导航--%>
    <ul class="nav nav-pills">
        <li class="nav-item">
            <a class="nav-link ${toolTypeId == 0? "active" :""}" href="/tool">全部</a>
        </li>
        <c:forEach items="${toolTypeList}" var="type">
            <li class="nav-item">
                <a class="nav-link ${type.id == toolTypeId ? "active" :""} "
                   href="/tool/type/${type.id}">${type.name}</a>
            </li>
        </c:forEach>
    </ul>

    <%--内容--%>
    <c:forEach items="${toolList.list}" var="tool" varStatus="idx" begin="0">
        <c:if test="${idx.index % 4 == 0 }">
            <div class="row row-cols-1 row-cols-md-4 mt-2">
        </c:if>
        <div class="col mb-3">
            <a href="${tool.toolUrl}" target="_blank">
                <div class="card select-shadow">
                    <img src="${tool.coverUrl}" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="card-text">${tool.name}</p>
                    </div>
                </div>
            </a>
        </div>
        <c:if test="${idx.index % 4 == 3 || idx.last}">
            </div>
        </c:if>
    </c:forEach>

    <%--分页--%>
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
            <li class="page-item ${toolList.hasPreviousPage? "" :"disabled"} ">
                <a class="page-link" href="/tool/type/${toolTypeId}?pageNum=${toolList.prePage}">上一页</a>
            </li>
            <c:forEach var="i" begin="${toolList.navigateFirstPage}" end="${toolList.navigateLastPage}">
                <li class=" page-item ${toolList.pageNum == i ? "active" : "" } ">
                    <a class="page-link" href="/tool/type/${toolTypeId}?pageNum=${i}">${i}</a>
                </li>
            </c:forEach>
            <li class="page-item ${toolList.hasNextPage? "" :"disabled"} ">
                <a class="page-link" href="/tool/type/${toolTypeId}?pageNum=${toolList.nextPage}">下一页</a>
            </li>
        </ul>
    </nav>

</div>
<br>
<br>

<%--网站的尾部--%>
<jsp:include page="/WEB-INF/jsp/common/footer.jsp"></jsp:include>

</body>
</html>