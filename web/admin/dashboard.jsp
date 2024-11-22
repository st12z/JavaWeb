<%-- 
    Document   : home.jsp
    Created on : Nov 22, 2024, 5:45:57 PM
    Author     : T
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
        <link rel="stylesheet" href="/Shop/admin/css/style.css">
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <div class="main">
            <%@ include file='sider.jsp' %>
            <div class="row">
                <div class="col-6 mb-3">
                    <div class="card">
                        <div class="card-header">
                            Danh mục
                        </div>
                        <div class="card-body">
                            <p class="card-text"><b>Tổng số danh mục: </b>${requestScope.listCategories.size()}</p>
                        </div>
                    </div>
                </div>
                <div class="col-6 mb-3">
                    <div class="card">
                        <div class="card-header">
                            Tổng số sản phẩm
                        </div>
                        <div class="card-body">
                            <p class="card-text"><b>Tổng số sản phẩm: </b>${requestScope.listProducts.size()}</p>
                        </div>
                    </div>
                </div>
                <div class="col-6 mb-3">
                    <div class="card">
                        <div class="card-header">
                            Đánh giá
                        </div>
                        <div class="card-body">
                            <p class="card-text"><b>Tổng số đánh giá: </b>${requestScope.listReviews.size()}</p>
                        </div>
                    </div>
                </div>
                <div class="col-6 mb-3">
                    <div class="card">
                        <div class="card-header">
                            Đơn hàng
                        </div>
                        <div class="card-body">
                            <p class="card-text"><b>Tổng số đơn hàng: </b>${requestScope.listOrderDetails.size()}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
    </body>
</html>
