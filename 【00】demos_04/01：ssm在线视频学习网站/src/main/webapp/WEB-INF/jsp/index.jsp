<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>在线视频</title>
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
    <%--轮播图--%>
    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
        <%--轮播下划线--%>
        <ol class="carousel-indicators">
            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
        </ol>
        <%--轮播的内容--%>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="/static/imgs/index_banner.jpg" class="d-block w-100 rounded" alt="...">
            </div>
            <div class="carousel-item">
                <img src="/static/imgs/index_banner.jpg" class="d-block w-100 rounded" alt="...">
            </div>
            <div class="carousel-item">
                <img src="/static/imgs/index_banner.jpg" class="d-block w-100 rounded" alt="...">
            </div>
        </div>
        <%--左箭头--%>
        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <%--右箭头--%>
        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
    <br>

    <%--最新课程类型--%>
    <div class="border border-top-0 border-left-0 border-right-0  border-secondary">
        <a href="/course_list" class="float-right">更多 ></a>
        <h4 class="text-center">最新课程</h4>
    </div>

    <div class="row row-cols-1 row-cols-md-4 mt-2">
        <c:forEach items="${newsetTopicList.list}" var="topic">
            <div class="col mb-3">
                <a href="#" target="_blank">
                    <div class="card select-shadow">
                        <img src="${topic.coverUrl}" class="card-img-top" alt="...">
                        <div class="card-body">
                            <p class="card-text">${topic.title}</p>
                            <p class="card-text">${topic.views}人学习</p>
                            <c:choose>
                                <c:when test="${topic.vipFlag == 1}">
                                    <span class="badge badge-pill badge-success ">免费</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="badge badge-pill badge-danger ">会员</span>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </a>
            </div>
        </c:forEach>
    </div>

    <br>

    <%--最新常用框架类型--%>
    <div class="border border-top-0 border-left-0  border-right-0  border-secondary">
        <a href="#" class="float-right">更多 ></a>
        <h4 class="text-center">常用框架</h4>
    </div>

    <div class="row row-cols-1 row-cols-md-4 mt-2">
        <c:forEach items="${courseTopicList.list}" var="topic">
            <div class="col mb-3">
                <a href="#" target="_blank">
                    <div class="card select-shadow">
                        <img src="${topic.coverUrl}" class="card-img-top" alt="...">
                        <div class="card-body">
                            <p class="card-text">${topic.title}</p>
                            <p class="card-text">${topic.views}人学习</p>
                            <c:choose>
                                <c:when test="${topic.vipFlag == 1}">
                                    <span class="badge badge-pill badge-success ">免费</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="badge badge-pill badge-danger ">会员</span>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </a>
            </div>
        </c:forEach>
    </div>
</div>
<br>
<br>

<%--尾部--%>
<jsp:include page="/WEB-INF/jsp/common/footer.jsp"></jsp:include>

</body>
</html>
