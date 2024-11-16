<%-- 
    Document   : cart.jsp
    Created on : Sep 2, 2024, 10:58:53 AM
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
        <link rel="stylesheet" href="/Shop/client/css/style.css"/>
    </head>


    <style>

        td{
            border:1px solid chocolate;
        }
        th{
            border:1px solid chocolate;
        }

    </style>
    <body>
        <%@ include file="header.jsp" %>
        <%@ include file='banner.jsp' %>
        <%@include file="contact.jsp" %>
        <div class="container px-3 my-5 clearfix">
            <c:if test="${not empty requestScope.items}">

                <div class="card">
                    <div class="card-header">
                        <h2>Shopping Cart</h2>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered m-0" table-cart>
                                <thead>
                                    <tr>
                                        <!-- Set columns width -->
                                        <th class="text-center py-3 px-4" style="min-width: 400px;">Tên sản phẩm &amp; Ảnh</th>
                                        <th class="text-right py-3 px-4" style="width: 150px;">Giá</th>
                                        <th class="text-center py-3 px-4" style="width: 150px;">Màu</th>
                                        <th class="text-right py-3 px-4" style="width: 150px;">Số lượng</th>
                                        <th class="text-right py-3 px-4" style="width: 100px;">Hành động</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${requestScope.items}" var="i" varStatus="status">
                                        <tr>
                                            <td class="p-4">
                                                <div class="media align-items-center">
                                                    <img src="${i.image}" alt="image" style="width:80px"/>
                                                    <div class="media-body">
                                                        <p><b>${i.product.name}</b></p>
                                                    </div>
                                                </div>
                                            </td>
                                            <td class="text-right font-weight-semibold align-middle p-4">${i.product.getPriceVND()} VNĐ</td>
                                            <td><span class="badge badge-info">${i.color}</span></td>
                                            <td class="align-middle p-4">
                                                <div class="inner-quantity">
                                                    <a href="process?id=${i.product.id}&action=desc&colorId=${i.colorId}">
                                                        <button>-</button>
                                                    </a>
                                                    <input style="width:50px" type="number" value="${i.quantity}"/>
                                                    <a href="process?id=${i.product.id}&action=incr&colorId=${i.colorId}">
                                                        <button>+</button>
                                                    </a>
                                                </div>

                                            </td>
                                            <td class="text-center align-middle px-0"><a href="process?id=${i.product.id}&action=delete&colorId=${i.colorId}">
                                                    <button class="btn btn-info"><i class="fa-solid fa-trash-can"></i></button>
                                                </a></td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!-- / Shopping cart table -->

                        <div class="d-flex flex-wrap justify-content-between align-items-center pb-4">
                            <div class="mt-4">

                            </div>
                            <div class="d-flex">
                                <div class="text-right mt-4">
                                    <label class="text-muted font-weight-normal m-0">Total price</label>
                                    <div class="text-large"><strong total-payment>Tổng tiền thanh toán: ${requestScope.totalMoney} VNĐ</strong></div>
                                </div>
                            </div>
                        </div>

                        <div class="float-right">
                            <a href="payment">
                                <button class="btn btn-primary mt-3">
                                    Thanh toán
                                </button>
                            </a>
                        </div>

                    </div>
                </div>

            </c:if>
            <c:if test="${empty requestScope.items}">
                <h1>Giỏ hàng trống</h1>
            </c:if>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
    </body>
</html>
