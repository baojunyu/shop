<%--
  Created by IntelliJ IDEA.
  User: tomcat
  Date: 2019/2/24
  Time: 23:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="../includes/include.jsp"%>
<div class="layui-container">



<table class="layui-table " lay-even lay-skin="line" lay-size="lg">
    <colgroup>
        <col width="150">
        <col width="200">
        <col>
    </colgroup>

    <tbody>
    <tr>
        <td>角色名字</td>
        <td>${roleadd.roleName}</td>

    </tr>
    <tr>
        <td>角色创建的用户ID</td>
        <td>${roleadd.createUserId}</td>
    </tr>
    <tr>
        <td>角色创建的时间</td>
        <td>${roleadd.createTime}</td>
    </tr>
    <tr>
        <td>角色状态（1可用 0禁用）</td>

        <td>${roleadd.status}</td>
    </tr>
    </tbody>
</table>
</div>

</body>
</html>
