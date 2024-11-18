<%-- 
    Document   : order
    Created on : Sep 17, 2024, 3:58:34 PM
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
            <c:if test="${not empty requestScope.orders}">
                <c:forEach items="${requestScope.orders}" var="o" varStatus="status">
                    <div class="card">
                        <div class="card-header">
                            <h2>Đơn hàng  #${o.code}: </h2>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered m-0" table-cart>
                                    <thead>
                                        <tr>
                                            <!-- Set columns width -->
                                            <th class="text-center py-3 px-4" style="min-width: 400px;">Tên sản phẩm &amp; Ảnh</th>
                                            <th class="text-right py-3 px-4" style="width: 200px;">Giá</th>
                                            <th class="text-center py-3 px-4" style="width: 100px;">Màu</th>
                                            <th class="text-right py-3 px-4" style="width: 150px;">Số lượng</th>
                                            <th class="text-right py-3 px-4" style="width: 150px;">Tổng tiền</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${o.list}" var="i" varStatus="status">
                                            <tr>
                                                <td class="p-4">
                                                    <div class="media align-items-center">
                                                        <img src="${i.image}" alt="image" style="width:80px" />
                                                        <div class="media-body">
                                                            <p><b>${i.product.name}</b></p>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td class="text-right font-weight-semibold align-middle p-4">${i.product.getPriceVND()} VNĐ
                                                </td>
                                                <td><span class="badge badge-info">${i.color}</span></td>
                                                <td class="align-middle p-4">
                                                    <div class="inner-quantity">
                                                        <input style="width:50px" type="number" readonly value="${i.quantity}" />
                                                    </div>

                                                </td>
                                                <td class="text-right font-weight-semibold align-middle p-4">${i.getMoneyVND()} VNĐ</td>
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
                                        <div class="text-large"><strong total-payment>Tổng tiền thanh toán: ${o.getTotalMoneyVND()} VNĐ
                                                VNĐ</strong></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row mt-5">
                        <div class="col-8 justify-items-center mx-auto">
                            <div class="card">
                                <div class="card-header">
                                    Thông tin người thanh toán
                                </div>
                                <div class="card-body">
                                    <form >
                                        <div class="form-group">
                                            <label for="exampleInputEmail1">Họ và tên</label>
                                            <input readonly  class="form-control" value="${o.fullName}">
                                        </div>
                                        <div class="form-group">
                                            <label for="password-current">Số điện thoại</label>
                                            <input readonly  class="form-control" value="${o.phone}"/>
                                        </div>
                                        <div class="form-group">
                                            <label for="password-current">Địa chỉ giao hàng</label>
                                            <input readonly  class="form-control" value="${o.address}"/>
                                        </div>
                                        <div class="form-group">
                                            <label for="password-current">Ngày đặt</label>
                                            <input readonly  class="form-control" value="${o.formatCreatedAt()}"/>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>                             
                </c:forEach>
            </c:if>

            <c:if test="${empty requestScope.orders}">
                <h1>Bạn không có đơn hàng nào</h1>
            </c:if>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
    </body>
</html>
