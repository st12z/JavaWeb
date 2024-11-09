<%-- 
    Document   : detail-user
    Created on : Nov 2, 2024, 11:29:20 PM
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
        <div class="container-xl px-4 mt-4">
            <c:if test="${requestScope.error!=null}">
                <div class="alert alert-danger" role="alert" alert-cart>
                    ${requestScope.error}
                </div>  
            </c:if>
            <hr class="mt-0 mb-4">
            <form method="POST" action="detail-user" enctype="multipart/form-data" class="form-group">
                <div class="row">
                    <div class="col-12">
                        <!-- Account details card-->
                        <div class="card mb-4">
                            <div class="card-header">Chi tiết tài khoản</div>
                            <div class="card-body">
                                <!-- Form Group (username)-->
                                <div class="mb-3">
                                    <div class="form-group">
                                        <label class="small mb-1" for="inputUsername">Họ và tên</label>
                                        <input class="form-control" id="inputUsername" type="text" 
                                               name="fullname"
                                               value="${requestScope.User.fullName}"
                                               />
                                    </div>
                                    <div class="form-group">
                                        <label class="small mb-1" for="inputEmail">Email</label>
                                        <input class="form-control" id="inputEmail" type="text" 
                                               name="email" value="${requestScope.User.email}"
                                               />
                                    </div>
                                    <div class="form-group" upload-image >
                                        <label class="form-label" class="small mb-1" for="avatar">Ảnh avatar</label>
                                        <input upload-image-input  class="form-control-file" type="file" id="avatar" name="avatar" accept="images/*" />
                                        <img src="${requestScope.User.avatar}" width="200px" class="image-preview"/>
                                    </div>    
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
                <div class="form-group ml-3">
                    <button class="btn btn-primary" type="submit">Cập nhật</button>
                </div>
            </form>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
        <script src="client/js/script.js"></script>
    </body>
</html>
