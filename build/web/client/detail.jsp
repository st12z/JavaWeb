<%-- 
    Document   : detail
    Created on : Sep 1, 2024, 9:29:21 AM
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
        <%@ include file="header.jsp" %>
        <main>

            <div class="container mt-3">
                <h2 class="mt-3">Thông tin sản phẩm</h2>
                <div class="row">
                    <div class="col-xl-5 col-lg-5 col-sm-5 col-12">
                        <div class="inner-image">
                            <img src="${requestScope.product.image}"/>
                        </div>
                    </div>
                    <div class="col-xl-7 col-lg-7 col-sm-5 col-12">
                        <div class="inner-content">
                            <h5>Tên sản phẩm: ${requestScope.product.name}</h5>
                            <h5 style="color:red">Giá: ${requestScope.product.price}$</h5>
                            <p>Mô tả: ${requestScope.product.describe}</p>
                            <div class="button-buy">
                                <a href="add-cart?id=${requestScope.product.id}">
                                    <button class="btn btn-primary">
                                        Thêm vào giỏ hàng
                                    </button>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
        </main>
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
    </body>

</html>
