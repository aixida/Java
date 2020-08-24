<%@ page import="domain.Kind" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript">
        window.onload = function(){
            var kindSelect = document.getElementById("kindSelect");
            kindSelect.onchange = function(){
                if (this.value == -1) {
                    window.location.href = "";
                } else {
                    window.location.href = "selectCommodity?kid=" + this.value;
                }
            }
        }
    </script>
</head>
<body>

    假的购物系统有商品种类如下，请下拉选择<br>
    <select id="kindSelect" name="kid">
        <option value="-1">==请选择==</option>
        <%
            ArrayList<Kind> kindList = (ArrayList<Kind>) request.getAttribute("kindList");
            for(Kind kind:kindList){
                out.write("<option value=\"" + kind.getKid() + "\">" + kind.getKname() + "</option>");
            }
        %>
    </select>

</body>
</html>
