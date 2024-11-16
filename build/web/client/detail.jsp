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
                <div id="rating" rating="${requestScope.product.rating}">
                    <span class="review-star">Đánh giá sản phẩm: ${requestScope.product.rating}.0</span>
                    <span class="feed-star fa fa-star" data-value="1"></span>
                    <span class="feed-star fa fa-star" data-value="2"></span>
                    <span class="feed-star fa fa-star" data-value="3"></span>
                    <span class="feed-star fa fa-star" data-value="4"></span>
                    <span class="feed-star fa fa-star" data-value="5"></span>
                </div>
                <div class="row mt-3">
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
                        <div class="inner-button mt-3">
                            <button type="button" class="btn btn-secondary" data-toggle="modal" data-target="#staticBackdrop">
                                Xem thông số kĩ thuật
                            </button>
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
                        <!-- Button trigger modal -->


                        <!-- Modal -->
                        <div class="modal fade" id="staticBackdrop" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="staticBackdropLabel">
                                            <i class="fa-solid fa-gear mr-3"></i>Thông số kỹ thuật
                                        </h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <dl class="row">
                                            <dt class="col-sm-4">Màn hình:</dt>
                                            <dd class="col-sm-8">${requestScope.statics.screen}</dd>

                                            <dt class="col-sm-4">Camera:</dt>
                                            <dd class="col-sm-8">${requestScope.statics.camera}</dd>

                                            <dt class="col-sm-4">Hệ điều hành:</dt>
                                            <dd class="col-sm-8">${requestScope.statics.processor}</dd>

                                            <dt class="col-sm-4">Đồ họa:</dt>
                                            <dd class="col-sm-8">${requestScope.statics.graphics}</dd>

                                            <dt class="col-sm-4">Bộ nhớ:</dt>
                                            <dd class="col-sm-8">${requestScope.statics.battery}</dd>

                                            <dt class="col-sm-4">Trọng lượng:</dt>
                                            <dd class="col-sm-8">${requestScope.statics.weight}</dd>
                                        </dl>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col-12">
                        <div class="form-rating">
                            <h3>Đánh giá của khách hàng</h3>
                            <form class="form-review" action="/Shop/review" method="POST">
                                <div class="inner-review">
                                    <div class="inner-rating form-group">
                                        <p>
                                            <b>1. Đánh giá của bạn về sản phẩm</b>
                                        </p>
                                        <span reviewing-star class="fa fa-star" data-value="1"></span>
                                        <span reviewing-star class="fa fa-star" data-value="2"></span>
                                        <span reviewing-star class="fa fa-star" data-value="3"></span>
                                        <span reviewing-star class="fa fa-star" data-value="4"></span>
                                        <span reviewing-star class="fa fa-star" data-value="5"></span>
                                        <input name="rating" hidden rating/>
                                        <input name="productId" hidden value="${requestScope.product.id}"/>
                                    </div>
                                    <div class="inner-content form-group">
                                        <p>
                                            <b>2. Cảm nhận của bạn về sản phẩm</b>
                                        </p>
                                        <textarea class="form-control" name="description"></textarea>
                                    </div>
                                    <button class="btn btn-primary" type="submit">Đánh giá</button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="col-12 mt-3 inner-feed">
                        <c:forEach items="${requestScope.listRV}" var="feedback">
                            <div class="person-review" person-review="${feedback.id}">
                                <div class="inner-info">
                                    <div class="inner-avatar">
                                        <img review-avatar src="/Shop/${feedback.user.avatar}" width="50px" />
                                    </div>
                                    <div class="inner-name">
                                        <p>${feedback.user.fullName}</p>
                                        <p>${feedback.createdAt}</p>
                                    </div>
                                </div>

                                <div class="inner-rating" reviewed-rating="${feedback.rating}">
                                    <span class="fa fa-star" reviewed-star data-value="1"></span>
                                    <span class="fa fa-star" reviewed-star data-value="2"></span>
                                    <span class="fa fa-star" reviewed-star data-value="3"></span>
                                    <span class="fa fa-star" reviewed-star data-value="4"></span>
                                    <span class="fa fa-star" reviewed-star data-value="5"></span>
                                </div>

                                <div class="inner-content">
                                    <p class="reviewed-content">${feedback.content}</p>
                                </div>
                                <c:if test="${requestScope.User != null && feedback.user.id==requestScope.User.id}">

                                    <div class="inner-action" feedback="${feedback.id}">

                                        <i class="fa-solid fa-pen-to-square mr-2" review-edit="${feedback.id}"></i>
                                        <a href="/Shop/delete-review/${feedback.id}">
                                            <i class="fa-solid fa-trash" review-delete="${feedback.id}"></i>
                                        </a>

                                    </div>
                                </c:if>

                            </div>
                            <div class="inner-edit"  inner-edit="${feedback.id}">
                                <form class="form-edit-review" method="POST" action="/Shop/update-review/${feedback.id}">
                                    <div class="inner-review">
                                        <div class="inner-rating form-group">
                                            <p><b>1. Đánh giá của bạn về khóa học</b></p>
                                            <span edit-star class="fa fa-star" data-value="1"></span>
                                            <span edit-star class="fa fa-star" data-value="2"></span>
                                            <span edit-star class="fa fa-star" data-value="3"></span>
                                            <span edit-star class="fa fa-star" data-value="4"></span>
                                            <span edit-star class="fa fa-star" data-value="5"></span>
                                            <input 
                                                name="rating" 
                                                type="text"
                                                hidden
                                                edit-rating
                                                />
                                           <input name="productId" hidden value="${requestScope.product.id}"/>
                                        </div>

                                        <div class="inner-content form-group">
                                            <p><b>2. Cảm nhận của bạn về khóa học</b></p>
                                            <textarea class="form-control" name="description">
                                                ${feedback.content}
                                            </textarea>
                                        </div>

                                        <button class="btn btn-primary mb-3" type="submit">Cập nhật</button>
                                    </div>
                                </form>
                            </div>        
                        </c:forEach>
                    </div>                
                </div>
            </div>
        </main>
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
        <script src="/Shop/client/js/script.js"></script>
    </body>

</html>