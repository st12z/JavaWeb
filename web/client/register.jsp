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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
        <link rel="stylesheet" href="/Shop/client/css/style.css">
    </head>

    <body>
        <%@ include file="header.jsp" %>
        <%@ include file='banner.jsp' %>
        <%@include file="contact.jsp" %>
        <div class="container mt-3 "  >
            <c:if test="${requestScope.error!=null}">
                <div class="alert alert-danger" role="alert" alert-cart>
                    ${requestScope.error}
                </div>  
            </c:if>
            <section class="gradient-custom">
                <div class="container">
                    <div class="row d-flex justify-content-center align-items-center">
                        <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                            <div class="card bg-dark text-white" style="border-radius: 1rem;height: 600px">
                                <div class="card-body p-5 text-center">
                                    <div class="mb-md-5 mt-md-4 pb-5">
                                        <h2 class="fw-bold mb-2 text-uppercase">Đăng kí</h2>
                                        <form action="register" method="POST">
                                            <div data-mdb-input-init class="form-outline form-white mb-4">
                                                <label class="form-label"  for="typeEmailX">Họ và tên</label>
                                                <input type="fullName" id="typeEmailX" name="fullName" required class="form-control form-control-lg" />
                                            </div>
                                            <div data-mdb-input-init class="form-outline form-white mb-4">
                                                <label class="form-label" for="typeEmailX">Email</label>
                                                <input type="email" id="typeEmailX" name="email" required class="form-control form-control-lg" />
                                            </div>

                                            <div data-mdb-input-init class="form-outline form-white mb-4">
                                                <label class="form-label"  for="typePasswordX1">Mật khẩu</label>
                                                <input type="password" name="password1" required id="typePasswordX1" class="form-control form-control-lg" />
                                            </div>
                                            <div data-mdb-input-init class="form-outline form-white mb-4">
                                                <label class="form-label"  for="typePasswordX2">Nhập lại mật khẩu</label>
                                                <input type="password" id="typePasswordX2" required name="password2" class="form-control form-control-lg" />
                                            </div>    
                                            <button data-mdb-button-init data-mdb-ripple-init class="btn btn-outline-light btn-lg px-5" type="submit">Đăng kí</button>

                                            <div class="d-flex justify-content-center text-center mt-4 pt-1">
                                                <a href="#!" class="text-white"><i class="fab fa-facebook-f fa-lg"></i></a>
                                                <a href="#!" class="text-white"><i class="fab fa-twitter fa-lg mx-4 px-2"></i></a>
                                                <a href="#!" class="text-white"><i class="fab fa-google fa-lg"></i></a>
                                            </div>
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
