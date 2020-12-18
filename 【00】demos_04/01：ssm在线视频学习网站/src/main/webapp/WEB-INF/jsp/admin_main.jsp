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
                <div class="card-header py-3"><h6 class="m-0 font-weight-bold text-primary">DataTables Example</h6>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
                            <div class="row">
                                <div class="col-sm-12 col-md-6">
                                    <a href="#" class="btn btn-primary">添加</a>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-12">
                                    <table class="table table-bordered dataTable" id="dataTable" width="100%"
                                           cellspacing="0" role="grid" aria-describedby="dataTable_info"
                                           style="width: 100%;">
                                        <thead>
                                        <tr role="row">
                                            <th class="sorting_asc" tabindex="0" aria-controls="dataTable" rowspan="1"
                                                colspan="1" aria-sort="ascending"
                                            >#ID
                                            </th>
                                            <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1"
                                                colspan="1"
                                            >手机
                                            </th>
                                            <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1"
                                                colspan="1"
                                            >用户名
                                            </th>
                                            <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1"
                                                colspan="1"
                                            >密码
                                            </th>
                                            <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1"
                                                colspan="1"
                                            >邮箱
                                            </th>
                                            <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1"
                                                colspan="1"
                                            >会员
                                            </th>
                                            <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1"
                                                colspan="1"
                                            >注册时间
                                            </th>
                                            <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1"
                                                colspan="1"
                                            >删除
                                            </th>
                                            <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1"
                                                colspan="1"
                                            >修改
                                            </th>
                                        </tr>
                                        </thead>

                                        <tbody>
                                        <c:forEach items="${userList.list}" var="user">
                                            <tr role="row" class="odd">
                                                <td class="sorting_1">${user.id}</td>
                                                <td>${user.number}</td>
                                                <td>${user.name}</td>
                                                <td>${user.password}</td>
                                                <td>${user.email}</td>
                                                <td>${user.vipFlag}</td>
                                                <td>${user.createTime}</td>
                                                <td><a  href="#" onclick="delUser(this)" myAttr="/admin/userDel/${user.id}?pageNum=${userList.pageNum}"><i
                                                        class="fa fa-trash  fa-2x text-danger"
                                                        aria-hidden="true"></i></a></td>
                                                <td><a href="/admin/userUpdatePage/${user.id}?pageNum=${userList.pageNum}"><i class="fa fa-pencil-square-o  fa-2x text-warning"
                                                                   aria-hidden="true"></i></a></td>
                                            </tr>
                                        </c:forEach>

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-12 col-md-5">
                                    <div class="dataTables_info" id="dataTable_info" role="status" aria-live="polite">
                                        Showing 1 to ${userList.pages} of ${userList.total} entries
                                    </div>
                                </div>
                                <div class="col-sm-12 col-md-7">
                                    <div class="dataTables_paginate paging_simple_numbers" id="dataTable_paginate">
                                        <%-- 分页--%>
                                        <nav aria-label="Page navigation example">
                                            <ul class="pagination">
                                                <li class="page-item ${userList.hasPreviousPage? "" :"disabled"} ">
                                                    <a class="page-link"
                                                       href="/admin/user?pageNum=${userList.prePage}">上一页</a>
                                                </li>
                                                <c:forEach var="i" begin="${userList.navigateFirstPage}"
                                                           end="${userList.navigateLastPage}">
                                                    <li class=" page-item ${userList.pageNum == i ? "active" : "" } ">
                                                        <a class="page-link"
                                                           href="/admin/user?pageNum=${i}">${i}</a>
                                                    </li>
                                                </c:forEach>
                                                <li class="page-item ${userList.hasNextPage? "" :"disabled"} ">
                                                    <a class="page-link"
                                                       href="/admin/user?pageNum=${userList.nextPage}">下一页</a>
                                                </li>
                                            </ul>
                                        </nav>
                                    </div>
                                </div>
                            </div>
                        </div>
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

    function delUser(delNode){
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
                        window.location.href= myAttr;

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
