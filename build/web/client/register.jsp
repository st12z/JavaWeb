<%-- 
    Document   : register
    Created on : Sep 16, 2024, 5:14:44 PM
    Author     : T
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">

    </head>

    <body>

        <div class="container mt-3 "  >
            <div class="form-register d-flex justify-content-center">

                <div class="row d-flex ">

                    <div class="col-12 ">
                        <c:if test="${requestScope.success!=null}">
                            <div class="alert alert-success alert-register" role="alert" alert-cart>
                                ${requestScope.success}
                            </div>  
                        </c:if>
                        <c:if test="${requestScope.error!=null}">
                            <div class="alert alert-danger" role="alert" alert-cart>
                                ${requestScope.error}
                            </div>  
                        </c:if>
                        <div class="card" style="width: 30rem;">
                            <div class="card-body" align>
                                <h5 class="card-title">Đăng kí tài khoản</h5>
                                <form method="POST" action="register" enctype="multipart/form-data">
                                    <div class="form-group">
                                        <label for="username">UserName</label>
                                        <input type="username" class="form-control" id="username" name="username" required aria-describedby="userHelp">
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleInputEmail1">Email address</label>
                                        <input type="email" class="form-control" id="exampleInputEmail1" name="email" required aria-describedby="emailHelp">
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleInputPassword1">Password</label>
                                        <input type="password" class="form-control" required id="exampleInputPassword1" name="password">
                                    </div>
                                    <div class="form-group">
                                        <label>Avatar</label>
                                        <input type="file" accept="image/*" class="form-control" name="file" placeholder="Enter photo">
                                    </div>
                                    <button type="submit" class="btn btn-primary">Đăng ký</button>
                                </form>
                                <a href="login">Bạn đã có tài khoản?</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>

    </body>
</html>
