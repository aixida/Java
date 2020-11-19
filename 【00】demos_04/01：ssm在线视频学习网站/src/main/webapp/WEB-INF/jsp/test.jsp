<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>在线视频学习</title>

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/static/css/bootstrap.min.css" crossorigin="anonymous">


</head>
<body>


<nav class="navbar navbar-expand-lg navbar-light bg-light">

    <div class="container">

        <a class="navbar-brand" href="#">我的视频</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="#">首页 <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">课程</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">会员</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">直播</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">工具</a>
                </li>

            </ul>
            <a href="#" data-toggle="modal" data-target="#loginModal" data-whatever="@fat" class="mr-1">登录</a> /
            <a href="#" data-toggle="modal" data-target="#registModal" data-whatever="@mdo" class="ml-1 mr-3">注册</a>
            <form class="form-inline my-2 my-lg-0">
                <input class="form-control mr-sm-2" type="search" placeholder="搜索视频" aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">搜索</button>
            </form>
        </div>

    </div>
</nav>


<!-- 登录对话框 -->
<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form>
                <div class="modal-header">
                    <h5 class="modal-title" id="loginModalLabel">登录</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">


                    <div class="form-row">
                        <div class="col-md-12 mb-3">
                            <label for="validationEmail2">邮箱</label>
                            <!-- is-valid is-invalid-->
                            <input type="text" class="form-control " id="validationEmail2" required>
                            <div class="valid-feedback">
                                Looks good!
                            </div>
                        </div>

                    </div>
                    <div class="form-row">
                        <div class="col-md-12 mb-3">
                            <label for="validationPassword2">密码</label>
                            <input type="password" class="form-control " id="validationPassword2" required>
                            <div class="invalid-feedback">
                                Please provide a valid city.
                            </div>
                        </div>


                    </div>
                    <div class="form-group">
                        <div class="form-check">
                            <input class="form-check-input " type="checkbox" value="" id="invalidCheck3">
                            <label class="form-check-label">
                                自动登录
                            </label>

                            <a href="#" class="float-right">忘记密码</a>
                        </div>
                    </div>


                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                    <button type="submit" class="btn btn-primary">登录</button>
                </div>
                <div class="mb-3 ml-3">
                    <a href="#" data-toggle="modal" data-dismiss="modal" data-target="#registModal">还没有账号？点我注册</a>
                </div>

            </form>
        </div>


    </div>

</div>


<!-- 注册对话框 -->

<div class="modal fade" id="registModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form method="post" action="/regist" 
                  onsubmit="return registSubmit()">
                <div class="modal-header">
                    <h5 class="modal-title" id="registerModalLabel">注册</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">


                    <div class="form-row">
                        <div class="col-md-12 mb-3">
                            <label for="validationEmail">邮箱</label>
                            <!-- is-valid is-invalid-->
                            <input type="text"  name="email" placeholder="请输入邮箱"
                                   onblur="checkEmail(this)"
                                   pattern="[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?"
                                   class="form-control" id="validationEmail" required>
                            <div id="feedbackEmail" class="valid-feedback">

                            </div>
                        </div>

                    </div>
                    <div class="form-row">
                        <div class="col-md-12 mb-3">
                            <label for="validationNumber">手机</label>
                            <!-- is-valid is-invalid-->
                            <input type="text" name="number" placeholder="请输入手机号"
                                   pattern="1[345678]\d{9}"
                                   class="form-control " id="validationNumber" required>
                            <div class="valid-feedback">

                            </div>
                        </div>

                    </div>
                    <div class="form-row">
                        <div class="col-md-12 mb-3">
                            <label for="validationPassword">密码</label>
                            <input type="password" name="password" placeholder="包含数字和字母且在6-20位之间"
                                   pattern="^(?![\d]+$)(?![a-zA-Z]+$)(?![^\da-zA-Z]+$).{6,20}$"
                                   class="form-control " id="validationPassword"
                                   required>
                            <div class="invalid-feedback">

                            </div>
                        </div>


                    </div>
                    <div class="form-row">
                        <div class="col-md-12 mb-3">
                            <label for="validationVcode">验证码</label>
                            <!-- is-valid is-invalid-->
                            <div class="row">
                                <div class="col-md-7">
                                    <input type="text" name="vcode" class="form-control" id="validationVcode" required>
                                    <div id="feedbackVcode" class="valid-feedback">

                                    </div>
                                </div>
                                <div class="col-md-5"><img src="/vcode" onclick="changeVcode(this)"/></div>
                            </div>
                        </div>
                    </div>


                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                    <button type="submit" class="btn btn-primary">注册</button>
                </div>
                <div class="mb-3 ml-3">
                    <a href="#" data-toggle="modal" data-dismiss="modal" data-target="#loginModal">已有账号？点我登录</a>
                </div>

            </form>
        </div>


    </div>

</div>


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="/static/js/jquery-3.3.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="/static/js/bootstrap.min.js"
        crossorigin="anonymous"></script>
<script type="application/javascript">

    function registSubmit() {
        var vcodeFlag = checkVcode();

        if (!vcodeFlag) {
            $("#validationVcode").removeClass("is-valid").addClass("is-invalid");
            $("#feedbackVcode").text("validationCode error");
            $("#feedbackVcode").removeClass("valid-feedback").addClass("invalid-feedback");
            return false;
        }

        // 提交注册
        return true;
    }

    function checkVcode() {
        var vcode = $("#validationVcode").val();
        var flag = false;
        $.ajax({
            url: "checkVcode?vcode=" + vcode,
            success: function (result) {
                if (result.rcode == 1) {
                    flag = true;
                } else {
                    flag = false;
                }
            },
            // ajax请求变为同步
            // 异步会导致registSubmit()调用此方法还没接收到返回值就执行完后面的代码了 以免vcodeFlag = undefined
            async: false
        });
        return flag;
    }

    function checkEmail(emailNode) {
        var email = emailNode.value;
        $.ajax({
            url: "/checkEmail?email=" + email,
            success: function (result) {
                if (result.rcode == 1) {
                    // regist success
                    $("#validationEmail").removeClass("is-invalid").addClass("is-valid");
                    $("#feedbackEmail").removeClass("invalid-feedback").addClass("valid-feedback");
                } else {
                    // regist fail
                    $("#validationEmail").removeClass("is-valid").addClass("is-invalid");
                    $("#feedbackEmail").removeClass("valid-feedback").addClass("invalid-feedback");
                }
                $("#feedbackEmail").text(result.message);
            }
        });
    }

    function changeVcode(imgNode) {
        imgNode.src = "/vcode?ram=" + new Date().getTime();
    }

</script>
</body>
</html>
