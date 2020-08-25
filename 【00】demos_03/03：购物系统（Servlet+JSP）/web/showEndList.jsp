<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style type="text/css">
        td {
            text-align : center;
        }
    </style>
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

    <table border="1" align="center" width="60%" height=""60%>
        <tr>
            <th>商品名称</th><th>商品单价</th><th>商品数量</th>
        </tr>
        <c:forEach var="map" items="${sessionScope.shoppingCar}">
            <tr>
                <td>${map.key.cname}</td>
                <td>${map.key.cprice}</td>
                <td>${map.value}</td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="3">总计: ${requestScope.sumPrice} ${requestScope.result}</td>
        </tr>
    </table>

    ${sessionScope.remove("shoppingCar")}

</body>
</html>
