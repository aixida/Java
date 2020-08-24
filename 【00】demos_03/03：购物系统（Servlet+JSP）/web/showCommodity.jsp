<%@ page import="domain.Commodity" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style type="text/css">
        td {
            text-align:center;
        }
    </style>
    <script type="text/javascript">
        window.onload = function(){

            var logoutButton = document.getElementById("logout");
            logoutButton.onclick = function () {
                window.location.href = "logout";
            }

            var buttonElements = document.getElementsByClassName("button");//数组 两个按钮对象
            for (var i = 0; i < buttonElements.length; i++) {//每一次循环找到一个按钮对象
                buttonElements[i].onclick = function () {
                    //大前提是  我们利用form表单携带信息
                    //如果发送的是get请求
                    //  get没有协议体的  只能将form中的所有信息拼接在请求URL后面
                    //  注意这个时候我们自己不能在请求后面追加?来携带参数
                    //如果发送的是post请求
                    //  利用协议体传输信息
                    //  这个时候是可以在请求名字后面携带自己拼接的?传参数的
                    //formElement.action = "saveCommodity?flag="+this.value;
                    var hiddenElement = document.getElementById("hidden");
                    hiddenElement.value = this.value;
                    var formElement = document.getElementById("form");//表单对象
                    formElement.submit();//提交
                };
            }
        }
    </script>
</head>
<body>

    <%
        String uname = (String) session.getAttribute("uname");
    %>
    登录用户: <%=uname%>,&nbsp;&nbsp;&nbsp;&nbsp;<button id="logout" type="button" >账号退出</button>
    <hr><br>

    <form id="form" action="saveCommodity" method="post">
        <input id="hidden" type="hidden" name="flag" value="">
        <table border="1" align="center" width="60%" height="60%">
            <tr>
                <th></th><th>商品名称</th><th>商品单价</th><th>商品存量</th>
            </tr>
            <%
                ArrayList<Commodity> commodityList = (ArrayList<Commodity>) request.getAttribute("commodityList");
                for(Commodity commodity:commodityList){
                    out.write("<tr>");
                    out.write("<td><input type=\"checkbox\" name=\"cid\" value=\"" + commodity.getCid() + "\"></td>");
                    out.write("<td>" + commodity.getCname() + "</td>");
                    out.write("<td>" + commodity.getCprice() + "</td>");
                    out.write("<td>" + commodity.getCcount() + "</td>");
                    out.write("</tr>");
                }
            %>
            <tr>
                <td colspan="4">
                    <!--
                        将现在这个JSP中选择的商品存起来 查询种类 显示种类
                        将现在这个JSP中选择的商品存起来 计算总价 显示所有购买商品
                    -->
                    <input class="button" type="button" value="继续购物">
                    &nbsp;&nbsp;||&nbsp;&nbsp;
                    <input class="button" type="button" value="结算清单">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
