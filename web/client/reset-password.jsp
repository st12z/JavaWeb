<%-- 
    Document   : login
    Created on : Aug 31, 2024, 11:10:39 PM
    Author     : T
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
        <link rel="stylesheet" href="client/css/style.css"/>
    </head>
    <body>

        <style>
            .form-login{
                display:flex;
                justify-content: center;
                align-content: center;
            }


        </style>
        <%@ include file="header.jsp" %>
        <%@ include file='banner.jsp' %>
        <%@include file="contact.jsp" %>
        <div class="container mb-3">
            <c:if test="${requestScope.error!=null}">
                <div class="alert alert-danger" role="alert" alert-cart>
                    ${requestScope.error}
                </div>  
            </c:if>
            <section class="vh-100 gradient-custom">
                <div class="container py-5 h-100">
                    <div class="row d-flex justify-content-center align-items-center h-100">
                        <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                            <div class="card bg-dark text-white" style="border-radius: 1rem;">
                                <div class="card-body p-5 text-center">

                                    <div class="mb-md-5 mt-md-4 pb-5">

                                        <h2 class="fw-bold mb-2 text-uppercase">Quên mật khẩu</h2>
                                        <form action="reset-password" method="POST">
                                            <div data-mdb-input-init class="form-outline form-white mb-4">
                                                <label class="form-label" for="typeEmailX">Email</label>
                                                <input type="email" id="typeEmailX" name="email" required class="form-control form-control-lg" />
                                            </div>
                                            <div data-mdb-input-init class="form-outline form-white mb-4">
                                                <label class="form-label" for="typeEmailX">Mã OTP</label>
                                                <input type="text" id="typeEmailX" name="OTP" required class="form-control form-control-lg" />
                                            </div>
                                            <div data-mdb-input-init class="form-outline form-white mb-4">
                                                <label class="form-label" for="password1">Nhập mật khẩu mới</label>
                                                <input type="password" id="password1" name="password1" required class="form-control form-control-lg" />
                                            </div>
                                            <div data-mdb-input-init class="form-outline form-white mb-4">
                                                <label class="form-label" for="password2">Nhập lại mật khẩu mới</label>
                                                <input type="password" id="password2" name="password2" required class="form-control form-control-lg" />
                                            </div>
                                            <button data-mdb-button-init data-mdb-ripple-init class="btn btn-outline-light btn-lg px-5" type="submit">Gửi mã OTP</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

        </div>
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
    </body>
</html>
