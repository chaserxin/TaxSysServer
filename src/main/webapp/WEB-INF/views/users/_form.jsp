<%--
  Created by IntelliJ IDEA.
  User: Sinner
  Date: 2017/5/2
  Time: 17:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action="/users" method="POST">
    <div class="form-body">
        <div class="field">
            <label for="number">账号</label>
            <input type="text" name="number" placeholder="请输入用户名">
        </div>
        <div class="field">
            <label for="password">密码</label>
            <input type="text" name="password" placeholder="请输入密码">
        </div>
        <div class="field">
            <label for="gender">性别</label>
            <input type="text" name="gender" placeholder="请输入性别">
        </div>
        <div class="field">
            <label for="cellphone">手机号</label>
            <input type="text" name="cellphone" placeholder="请输入手机号">
        </div>
    </div>
    <div class="form-footer">
        <input type="submit" value="提交">
    </div>
</form>
