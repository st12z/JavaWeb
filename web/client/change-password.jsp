<%-- 
    Document   : change-password.jsp
    Created on : Nov 4, 2024, 2:05:24 PM
    Author     : T
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
        <link rel="stylesheet" href="/Shop/client/css/style.css">
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <%@ include file='banner.jsp' %>
        <%@include file="contact.jsp" %>
        <div class="container">
            <div class="card">
                <div class="card-header">
                    Đổi mật khẩu
                </div>
                <div class="card-body">
                    <form method="POST" action="change-password">
                        <div class="form-group">
                            <label for="exampleInputEmail1">Email</label>
                            <input readonly type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" value="${requestScope.User.email}">
                        </div>
                        <div class="form-group">
                            <label for="password-current">Nhập khẩu hiện tại</label>
                            <input type="password" name="passwordCurrent" class="form-control" id="password-current">
                        </div>
                        <div class="form-group">
                            <label for="password-new1">Đổi mật khẩu</label>
                            <input type="password" name="passwordNew1" class="form-control" id="password-new1">
                        </div>
                        <div class="form-group">
                            <label for="password-new2">Xác nhận mật khẩu</label>
                            <input type="password" name="passwordNew2" class="form-control" id="password-new2">
                        </div>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
        <script src="client/js/script.js"></script>
    </body>
</html>
