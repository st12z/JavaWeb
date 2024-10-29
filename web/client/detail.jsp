<%-- 
    Document   : detail
    Created on : Sep 1, 2024, 9:29:21 AM
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

        <style>
            .inner-image{
                aspect-ratio:1/1;
                border:1px solid #ddd;
                border-radius: 10px;
                background: #ddd;
            }
            .inner-image img{
                width: 300px;
                height:auto;
                object-fit:cover;
            }
            .inner-color{
                border: 1px solid #ddd;
                border-radius:5px;
            }
            .inner-color:hover{
                cursor: pointer;
                border:1px solid black;
            }
            .inner-color.active{
                background: orange;
            }
        </style>

        <main>
            <div class="container mt-3">
                <c:if test="${requestScope.error!=null}">
                    <div class="alert alert-danger" role="alert" alert-cart>
                        ${requestScope.error}
                    </div>  
                </c:if>
                <h2 class="mt-3 mb-3">Thông tin sản phẩm</h2>
                <div class="row">
                    <div class="col-xl-5 col-lg-5 col-sm-5 col-12 ">
                        <div class="inner-image">
                            <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
                                <div class="carousel-inner">
                                    <div class="carousel-item active">
                                        <img class="d-block w-100" src="/Shop/${requestScope.product.image}" alt="First slide">
                                    </div>
                                    <c:forEach items="${requestScope.colorsProduct}" var="i">
                                        <div class="carousel-item ">
                                            <img class="d-block w-100" src="/Shop/${i.image}" alt="First slide">
                                        </div>
                                    </c:forEach>
                                </div>
                                <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
                                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                    <span class="sr-only">Previous</span>
                                </a>
                                <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
                                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                    <span class="sr-only">Next</span>
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-7 col-lg-7 col-sm-7 col-12">
                        <div class="inner-content">
                            <h5>Tên sản phẩm: ${requestScope.product.name}</h5>
                            <h5 style="color:red">Giá: ${requestScope.product.getPriceVND()} VNĐ</h5>
                            <p><b>Lựa chọn màu</b></p>
                            <div class="row mb-3">
                                <c:forEach items="${requestScope.colorsProduct}" var="i" varStatus="status">
                                    <div class="col-3">
                                        <div class="inner-color ${status.first ? 'active' : ''}" colorId="${i.colorId}">
                                            <span></span>
                                            <img  style="width:50px" src="/Shop/${i.image}"/>
                                            <span>${i.color}</span>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>

                            <div class="button-buy">
                                <form method="POST" form-cart action="/Shop/add-cart/${requestScope.product.id}" productId="${requestScope.product.id}">
                                    <input value="" type="text" hidden name="colorId"/>
                                    <button class="btn btn-primary" type="submit">
                                        Thêm vào giỏ hàng
                                    </button>
                                </form>



                            </div>
                        </div>
                    </div>
                    </main>
                    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
                    <script src="/Shop/client/js/script.js"></script>
                    </body>

                    </html>