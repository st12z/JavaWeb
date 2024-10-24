<%-- 
    Document   : banner.jsp
    Created on : Oct 24, 2024, 11:20:56 AM
    Author     : T
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<div class="container mt-4 mb-4">
    <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img
                    src="client/images/banner1.jpg"
                    class="d-block w-100"
                    alt="..."
                    />
            </div>
            <div class="carousel-item">
                <img
                    src="client/images/banner2.jpg"
                    class="d-block w-100"
                    alt="..."
                    />
            </div>
            <div class="carousel-item">
                <img
                    src="client/images/banner3.jpg"
                    class="d-block w-100"
                    alt="..."
                    />
            </div>
            <div class="carousel-item">
                <img
                    src="client/images/banner4.jpg"
                    class="d-block w-100"
                    alt="..."
                    />
            </div>
        </div>
        <button
            class="carousel-control-prev"
            type="button"
            data-target="#carouselExampleControls"
            data-slide="prev"
            >
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </button>
        <button
            class="carousel-control-next"
            type="button"
            data-target="#carouselExampleControls"
            data-slide="next"
            >
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </button>
    </div>
</div>

