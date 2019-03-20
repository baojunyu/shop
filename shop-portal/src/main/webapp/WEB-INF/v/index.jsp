<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/19
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/js/bt3/css/bootstrap.min.css" />
    <script src="${pageContext.request.contextPath}/static/js/jquery-1.11.0.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bt3/js/bootstrap.min.js"></script>
</head>
<body>
登录成功~~~~~~~~~~~~~~~~~~~~~~~欢迎您：${sessionScope.ActiveUser.userAccount}

<div class="row clearfix">
    <div class="col-md-4 column">
    </div>
    <div class="col-md-4 column">
        <ul class="pagination">
            <li>
                <a href="#">Prev</a>
            </li>
            <li>
                <a href="#">1</a>
            </li>
            <li>
                <a href="#">2</a>
            </li>
            <li>
                <a href="#">3</a>
            </li>
            <li>
                <a href="#">4</a>
            </li>
            <li>
                <a href="#">5</a>
            </li>
            <li>
                <a href="#">Next</a>
            </li>
        </ul>
    </div>
    <div class="col-md-4 column">
    </div>
</div>
</body>
</html>
