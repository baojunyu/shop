<%--
  Created by IntelliJ IDEA.
  User: tomcat
  Date: 2019/2/22
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="../includes/include.jsp"%>

<div class="layui-container" style="margin-top: 5px">
    <form class="layui-form" action="${pageContext.request.contextPath}/permission/domodify" method="post">
        <div class="layui-form-item">
            <label class="layui-form-label">权限ID</label>
            <div class="layui-input-block">
                <input type="text" name="perId" value="${permission.perId}"  readonly="readonly" autocomplete="off"
                       class="layui-input layui-bg-gray" id="f0">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">权限名</label>
            <div class="layui-input-block">
                <input type="text" name="name" value="${permission.name}" id="f2" lay-verify="required" autocomplete="off"
                       placeholder="请输入权限名" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">路径URL</label>
            <div class="layui-input-block">
                <input type="text" name="url" value="${permission.url}" id="f3" lay-verify="required" autocomplete="off"
                       placeholder="请输入路径URL（button没有url）" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">权限标志</label>
            <div class="layui-input-block">
                <input type="text" name="percode" value="${permission.percode}" id="f4" lay-verify="required" autocomplete="off"
                       placeholder="请输入权限标志" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item" >
            <label class="layui-form-label">权限类型：</label>
            <div class="layui-input-block">
                <input type="radio" name="type" value="menu" lay-filter="level"  title="menu"  ${permission.type eq 'menu' ? "checked":""} >

                <input type="radio" name="type" value="button" lay-filter="level"  title="button" ${permission.type eq 'button' ? "checked":""}>
            </div>
        </div>


        <div class="layui-form-item">
            <input class="layui-btn"  style="margin-left: 10%"  type="submit" value="确认修改">
        </div>
    </form>
</div>

<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    layui.use([ 'form', 'laydate' ],
        function() {
            var form = layui.form,
                layer = layui.layer,
                laydate = layui.laydate;
            //重新渲染表格
            form.render();

        });
</script>

</body>
</html>
