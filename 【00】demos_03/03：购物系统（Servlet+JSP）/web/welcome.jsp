<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript">
        window.onload = function(){
            var logoutButton = document.getElementById("logout");
            logoutButton.onclick = function () {
                window.location.href = "logout";
            }
        }
    </script>
</head>
<body>

    登录用户: ${sessionScope.uname},&nbsp;&nbsp;&nbsp;&nbsp;<button id="logout" type="button" >账号退出</button>
    <hr><br>

    欢迎进入假的购物系统，想买东西么？<br>
    如果想买，请继续点击 <a href="selectAllKind">进入</a> 购物系统

</body>
</html>
