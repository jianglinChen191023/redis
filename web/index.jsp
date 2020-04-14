<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/4/10
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>200410学习首页</title>
    <script src='./js/jquery-3.3.1.js'></script>
    <script>
        $(function () {
            $.get("${pageContext.request.contextPath}/provinceServlet/getDataOptin", {}, function (data) {
                $(data).each(function () {
                    var option = "<option name=" + this.id + ">" + this.name + "</option>"
                    $("#provincce").append(option);
                });
            })
        })
    </script>
</head>
<body>
<a href="${pageContext.request.contextPath}/hello/world">springmvc</a><br/>
<a href="${pageContext.request.contextPath}/provinceServlet/getData">jedis</a><br/>

<select id="provincce">
    <option>--请选择省份--</option>

</select><br/>

<img src="js/dinasor.png">

</body>
</html>
