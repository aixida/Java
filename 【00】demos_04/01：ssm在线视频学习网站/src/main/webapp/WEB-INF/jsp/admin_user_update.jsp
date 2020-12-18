<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>网站后台</title>

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/static/css/bootstrap.min.css" crossorigin="anonymous">

    <link rel="stylesheet" href="/static/css/font-awesome.min.css">

    <%--提示确认对话框--%>
    <link rel="stylesheet" href="/static/css/jquery-confirm.min.css">
<%--    <link href="https://cdn.bootcdn.net/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css" rel="stylesheet">--%>


</head>
<body>
<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
    <a class="navbar-brand col-md-3 col-lg-2 mr-0 px-3" href="#">Company name</a>
    <button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-toggle="collapse"
            data-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <input class="form-control form-control-dark w-100" type="text" placeholder="Search" aria-label="Search">
    <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
            <a class="nav-link" href="#">Sign out</a>
        </li>
    </ul>
</nav>

<div class="container-fluid">
    <div class="row">
        <nav id="sidebarMenu" class="col-md-2 col-lg-2 d-md-block bg-light sidebar collapse">
            <div class="sidebar-sticky pt-3">
                <ul class="nav flex-column">
                    <li class="nav-item">
                        <a class="nav-link active" href="#">
                            <span data-feather="home"></span>
                            用户列表 <span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            <span data-feather="file"></span>
                            视频类型
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            <span data-feather="shopping-cart"></span>
                            课程视频
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            <span data-feather="users"></span>
                            工具类型
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            <span data-feather="bar-chart-2"></span>
                            网站工具
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            <span data-feather="layers"></span>
                            视频专题
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            <span data-feather="layers"></span>
                            banner广告
                        </a>
                    </li>
                </ul>
            </div>
        </nav>


        <main role="main" class="col-md-10 ml-sm-auto col-lg-10 px-md-4">

            <h2>Section title</h2>

            <div class="card shadow mb-4">
                <div class="card-header py-3"><h6 class="m-0 font-weight-bold text-primary">修改用户信息</h6>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <form action="/admin/userUpdate?pageNum="+${pageNum} method="post">
                            <input type="hidden" name="id" value="${user.id}">
                            <div class="form-group">
                                <label for="exampleInputEmail1">Email</label>
                                <input type="email" class="form-control" id="exampleInputEmail1" value="${user.email}"
                                       aria-describedby="emailHelp" name="email">
                                <small id="emailHelp" class="form-text text-muted">We'll never share your email with
                                    anyone else.</small>
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword1">mobile</label>
                                <input type="text" class="form-control" value="${user.number}" name="number">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword1">Password</label>
                                <input type="password" class="form-control" id="exampleInputPassword1" name="password">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword1">username</label>
                                <input type="text" class="form-control" value="${user.name}" name="name">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword1">vipFlag</label>
                                <input type="text" class="form-control" value="${user.vipFlag}" name="vipFlag">
                            </div>
<%--                            <div class="form-group">--%>
<%--                                <label for="exampleInputPassword1">createTime</label>--%>
<%--                                <input type="text" class="form-control" value="${user.createTime}" name="createTime" >--%>
<%--                            </div>--%>
                            <div class="form-group">
                                <label for="exampleInputPassword1">flag</label>
                                <input type="text" class="form-control" value="${user.flag}" name="flag">
                            </div>
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>


<script src="/static/js/jquery-3.3.1.min.js"></script>

<script src="/static/js/popper.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>

<script src="/static/js/jquery-confirm.min.js"></script>
<%--<script src="https://cdn.bootcdn.net/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>--%>

<script type="application/javascript">

    function delUser(delNode) {
        $.confirm({
            title: 'Delete user?',
            content: 'This dialog will automatically trigger \'cancel\' in 6 seconds if you don\'t respond.',
            autoClose: 'cancelAction|8000',
            buttons: {
                deleteUser: {
                    text: 'delete user',
                    action: function () {
                        // --/admin/userDel/${user.id}?pageNum=${userList.pageNum}
                        // var val = delNode.getAttribute("value");
                        var myAttr = $(delNode).attr("myAttr");
                        $.alert('用户已删除!');
                        window.location.href = myAttr;

                    }
                },
                cancelAction: function () {
                    $.alert('用户删除已取消!');

                }
            }
        });
    }
</script>
</body>
</html>
