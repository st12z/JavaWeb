<%-- 
    Document   : login
    Created on : Aug 31, 2024, 11:10:39 PM
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
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <style>
            .form-login{
                display:flex;
                justify-content: center;
                align-content: center;
            }
        </style>
 
        <div class="container mb-3">
            <div class="form-login">
                <div class="row">
                    <div clas="col-xl-6 col-lg-6 col-sm-6 col-6">
                        <div class="card-body">
                            <h5 class="card-title">Đăng nhập</h5>
                            <c:if test="${requestScope.error!=null}">
                                <h5 style="color:red">${requestScope.error}</h5>
                            </c:if>

                            <form action="login" method="post">
                                <div class="form-group">
                                    <label for="User">User</label>
                                    <input value="${requestScope.user}" type="text" class="form-control" id="User" aria-describedby="User" name="username">
                                </div>
                                <div class="form-group">
                                    <label for="Password">Password</label>
                                    <input value="${requestScope.pass}" type="password" class="form-control" id="Password" name="password">
                                </div>
                                <div class="form-group form-check">
                                    <input type="checkbox" value="1" class="form-check-input" id="exampleCheck1" name="checkBox">
                                    <label class="form-check-label" for="exampleCheck1">Remember Me</label>
                                </div>
                                <button type="submit" class="btn btn-primary">Submit</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>

    </body>
</html>
