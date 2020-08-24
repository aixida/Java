<%@ page import="domain.Commodity" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.Iterator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style type="text/css">
        td {
            text-align : center;
        }
    </style>
</head>
<body>
    <table border="1" align="center" width="60%" height=""60%>
        <tr>
            <th>商品名称</th><th>商品单价</th><th>商品数量</th>
        </tr>
        <%
           HashMap<Commodity, Integer> shoppingCar = (HashMap<Commodity, Integer>) session.getAttribute("shoppingCar");
            float sumPrice = (float)request.getAttribute("sumPrice");
            Set<Commodity> keys =  shoppingCar.keySet();
            Iterator<Commodity> it =  keys.iterator();
            while(it.hasNext()){
                Commodity key = it.next();
                int count = shoppingCar.get(key);
                out.write("<tr>");
                out.write("<td>" + key.getCname() + "</td>");
                out.write("<td>" + key.getCprice() + "</td>");
                out.write("<td>" + count + "</td>");
                out.write("</tr>");
            }
        %>
        <tr>
            <td colspan="3">总计: <%=sumPrice%></td>
        </tr>
    </table>

    <%
        //删除session中的购物车
        session.removeAttribute("shoppingCar");
    %>

</body>
</html>
