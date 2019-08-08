<%--
  Created by IntelliJ IDEA.
  User: 赵勃程
  Date: 2019/8/6
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>选择</title>
</head>
<body>
<form action="">
  <p>请您选择想要的武器</p>
  <input type="text" placeholder="请输入您选择的武器" id="in">
  <input type="submit" value="确定" onclick="alert(a)">
</form>
</body>
<script type="text/javascript">
    function f() {
        var a = document.getElementById('in');
        b = a.value();
        alert(b);
    }
</script>
</html>
