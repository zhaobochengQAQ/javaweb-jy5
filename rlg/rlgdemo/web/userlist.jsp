
<%--
  Created by IntelliJ IDEA.
  User: 赵勃程
  Date: 2019/8/11
  Time: 21:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>



<table width="100%" border="1" cellpadding="0" cellspacing="0" id="biaoge">
    <tr>
        <th>ID</th>
        <th>账号</th>
        <th>密码</th>
        <th>手机号</th>
        <th>权限</th>
        <th>状态</th>
    </tr>
    <c:forEach items="${userlist.data}" var="usl">
        <tr>
            <td>${usl.id}</td>
            <td>${usl.uname}</td>
            <td>${usl.psd}</td>
            <td>${usl.tel}</td>
            <td>${usl.type}</td>
            <td>${usl.stats}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
