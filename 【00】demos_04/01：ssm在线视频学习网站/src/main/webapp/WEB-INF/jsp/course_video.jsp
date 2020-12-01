<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>课程视频</title>

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/static/css/bootstrap.min.css" crossorigin="anonymous">
    <link rel="stylesheet" href="/static/css/font-awesome.min.css">

    <link rel="stylesheet" href="/static/css/video.css" crossorigin="anonymous">
</head>
<body>

<%--头部--%>
<jsp:include page="/WEB-INF/jsp/common/header.jsp"></jsp:include>
<br><br>

<%--首页内容区域--%>
<div class="container">

    <%--视频--%>
    <div class="card">
        <div class="embed-responsive embed-responsive-16by9">
            <iframe src="//player.bilibili.com/player.html?aid=53893527&bvid=BV1E4411W735&cid=94275556&page=1" scrolling="no" border="0" frameborder="no" framespacing="0" allowfullscreen="true"> </iframe>
        </div>
    </div>

    <%--视频相关--%>
    <div class="row mt-2">
        <div class="col-md-9">
            <span class="font-weight">浏览次数：<b class="text-primary">1069次</b></span>
            <span class="font-weight ml-3">课时数：<b class="text-primary">19</b></span>
            <span class="text-weight ml-3">类别：<b class="text-success">免费</b></span>

            <i class="fa fa-share-alt text-secondary ml-3">
                &nbsp;&nbsp;
                <a href="#" class="alert-link text-secondary">分享</a>
            </i>
            <i class="fa fa-star text-secondary ml-3">
                &nbsp;&nbsp;
                <a href="#" class="alert-link text-secondary">收藏</a>
            </i>
        </div>

        <div class="col-md-3">
            <a class="btn btn-info text-white" href="#">资料下载</a>
            <a class="btn btn-info text-white ml-2" target="_blank">在线咨询</a>
        </div>
    </div>

    <%--视频内容列表--%>
    <div class="row mt-2">

        <%--主要内容：导航栏--%>
        <div class="col-md-9">
            <div class="card">

                <nav>
                    <div class="nav nav-tabs" id="nav-tab" role="tablist">
                        <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home"
                           role="tab"
                           aria-controls="nav-home" aria-selected="true">课程介绍</a>
                        <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile"
                           role="tab"
                           aria-controls="nav-profile" aria-selected="false">课程目录</a>
                    </div>
                </nav>

                <div class="tab-content" id="nav-tabContent">
                    <%--课程介绍（导航栏）--%>
                    <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
                        Vue3.0（正式版） + TS 仿知乎专栏企业级项目
                        慕课网首发，Vue3.0企业级项目实战。热门技术双剑合璧，Vue3.0 配合 TypeScript ，使用新版Vuex 和 Vue-Router
                        全家桶完成前后端分离复杂实战项目。一系列由易到难通用组件开发，让你学会一个基本的组件库的开发思路和技巧。接入真实后端API，告别 mock 数据，并提供Swagger
                        在线调试查询。抓住前后端分离开发痛点 - 权限管理，路由控制，全局Store 结构设计，前端缓存实现等。
                    </div>
                    <%--课程目录（导航栏）--%>
                    <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
                        <a href="#" class="list-group-item list-group-item-action list-group-item-light border-0">
                            <i class="fa fa-play-circle-o">
                                &nbsp;&nbsp;第1讲
                                <span class="ml-3">1.武汉学院-IDEA-IntelliJ IDEA的介绍和优势</span>
                                <span class="ml-1 badge badge-pill badge-success  pt-1">免费</span>
                            </i>
                        </a>
                        <a href="#" class="list-group-item list-group-item-action list-group-item-light border-0">
                            <i class="fa fa-play-circle-o">
                                &nbsp;&nbsp;第1讲
                                <span class="ml-3">1.武汉学院-IDEA-IntelliJ IDEA的介绍和优势</span>
                                <span class="ml-1 badge badge-pill badge-success  pt-1">免费</span>
                            </i>
                        </a>
                        <a href="#" class="list-group-item list-group-item-action list-group-item-light border-0">
                            <i class="fa fa-play-circle-o">
                                &nbsp;&nbsp;第1讲
                                <span class="ml-3">1.武汉学院-IDEA-IntelliJ IDEA的介绍和优势</span>
                                <span class="ml-1 badge badge-pill badge-success  pt-1">免费</span>
                            </i>
                        </a>
                    </div>

                </div>

            </div>
        </div>

        <%--右边栏：老师介绍--%>
        <div class="col-md-3">
            <div class="card">
                <div class="card-header">
                    教学视频
                </div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-4">
                            <img src="/static/imgs/hicon.jpg" class="rounded-circle" alt="Responsive image" style="height: 60px;width: 60px;">
                        </div>
                        <div class="col-md-8">
                            zgh老师<br>
                            <span class="text-secondary" style="font-size: 16px;">管理员</span>
                        </div>
                        <span class="text-secondary mt-2" style="font-size: 14px;">分布式微服务QQ交流群：123456789</span>
                    </div>
                </div>
            </div>
        </div>

    </div>

</div>
<br>
<br>

<%--网站的尾部--%>
<jsp:include page="/WEB-INF/jsp/common/footer.jsp"></jsp:include>

</body>
</html>
