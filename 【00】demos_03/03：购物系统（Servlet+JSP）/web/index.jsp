<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript">
        window.onload = function(){
          var registButton = document.getElementById("regist");
          registButton.onclick = function(){
              window.location.href = "regist.jsp";
          }
        }
    </script>
</head>
<body>

    <%
        //如果登录失败
        String result = (String) request.getAttribute("result");
        if (result != null) {
            out.write(result + "<br><br>");
        }
    %>

    <form action="login" method="post">
      账号: <input type="text" name="uname" value=""><br>
      密码: <input type="password" name="upassword" value=""><br>
      <br>
      <input type="submit" value="登录">
      &nbsp;&nbsp;&nbsp;&nbsp;
      <input id="regist" type="button" value="注册">
    </form>

</body>
</html>
