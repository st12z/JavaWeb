/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
const buttonCid = document.querySelectorAll("[dataCategory-id]");
let url = new URL(window.location.href);
console.log(buttonCid);
if (buttonCid) {
    buttonCid.forEach(button => {
        button.addEventListener("click", () => {
            const cid = button.getAttribute("dataCategory-id");
            if (cid != 0) {
                url.searchParams.set("cid", cid);
                url.searchParams.delete("keyword");
            } else {
                url.searchParams.delete("cid");

            }
            const page = url.searchParams.get("page");
            url.searchParams.set("page", 1);
            window.location.href = url;
        });
    });
}

const buttonPagination = document.querySelectorAll("[button-pagination]");
if (buttonPagination) {
    buttonPagination.forEach(button => {
        button.addEventListener("click", () => {
            const page = button.getAttribute("data-page");
            url.searchParams.set("page", page);
            window.location.href = url;
        });
    });
}




const selectSort = document.querySelector("[select-sort]");
if (selectSort) {
    selectSort.addEventListener("change", (e) => {
        console.log(e.target.value);
        const page = url.searchParams.get("page");
        const [sortKey, sortValue] = e.target.value.split("-");
        url.searchParams.set("sortKey", sortKey);
        url.searchParams.set("sortValue", sortValue);
        url.searchParams.set("page", 1);
        window.location.href = url;
    });
}
const alertCart = document.querySelector("[alert-cart]");
if (alertCart) {
    setTimeout(() => {
        alertCart.classList.add("alert-hidden");
    }, 3000);
}

const radioPrice = document.querySelectorAll("input[name='radio-price']");
if (radioPrice) {
    radioPrice.forEach(radio => {
        radio.addEventListener("click", () => {
            const price = radio.value;
            const page = url.searchParams.get("page");
            url.searchParams.set("page", 1);
            url.searchParams.set("price", price);

            window.location.href = url;
        });
    });
}

//function getCookie(name) {
//    const value = `; ${document.cookie}`;
//    const parts = value.split(`; ${name}=`);
//    if (parts.length === 2) {
//        return parts.pop().split(';').shift();
//    }
//    return null; // Nếu không tìm thấy cookie
//}
//function setCookie(name, value, days = 7) {
//    const date = new Date();
//    date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000)); // Chuyển ngày thành thời gian
//    const expires = `expires=${date.toUTCString()}`;
//    document.cookie = `${name}=${value}; ${expires}; path=/`;
//}
//function deleteCookie(name) {
//    // Đặt cookie với giá trị rỗng và thời gian hết hạn trong quá khứ
//    document.cookie = `${name}=; expires=Thu, 01 Jan 1970 00:00:00 GMT; path=/`;
//}
//const buttonColor = document.querySelectorAll(".inner-color");
//const formCart = document.querySelector("[form-cart]");
//const messageCart=document.querySelector("[cart-success]");
//if (formCart) {
//    const inputColor = formCart.querySelector("input[name='colorId']");
//    let colorActive = document.querySelector(".inner-color.active");
//
//    // Nếu có colorActive, gán giá trị của nó cho input
//    if (colorActive) {
//        const colorId = colorActive.getAttribute("colorId");
//        inputColor.value = colorId;
//    }
//    if (buttonColor) {
//        buttonColor.forEach(button => {
//            button.addEventListener("click", () => {
//                buttonColor.forEach(button => {
//                    button.classList.remove("active");
//                })
//                // Cập nhật giá trị cho input
//                button.classList.add("active");
//                const colorId = button.getAttribute("colorId");
//                inputColor.value = colorId;
//            });
//        });
//    }
//    ;
//    const productId = formCart.getAttribute("productId");
//
//    formCart.addEventListener("submit", (e) => {
//        e.preventDefault();
//        const cartId = getCookie("cartId");
//        const cart_store = getCookie("cart-" + cartId);
//        let newCartValue;
//        if (!cart_store) {
//            
//            newCartValue = productId + "$" + inputColor.value + "-";
//        } else {
//            deleteCookie("cart-" + cartId);
//            newCartValue = cart_store + productId + "$" + inputColor.value + "-";
//        }
//        messageCart.innerHTML="Đã đặt hàng thành công!";
//        messageCart.classList.remove("hidden");
//        setCookie("cart-" + cartId, newCartValue);
//        console.log("Updated cart cookie:", getCookie("cart-" + cartId));
//        
////        addCart();
//    });
//
//}
//;
const buttonColor = document.querySelectorAll(".inner-color");
const formCart = document.querySelector("[form-cart]");

if (formCart) {
    const inputColor = formCart.querySelector("input[name='colorId']");
    let colorActive = document.querySelector(".inner-color.active");

    // Nếu có colorActive, gán giá trị của nó cho input
    if (colorActive) {
        const colorId = colorActive.getAttribute("colorId");
        inputColor.value = colorId;
    }

    if (buttonColor) {
        buttonColor.forEach(button => {
            button.addEventListener("click", () => {
                
                buttonColor.forEach(button=>{
                    button.classList.remove("active");
                })
                // Cập nhật giá trị cho input
                button.classList.add("active");
                const colorId = button.getAttribute("colorId");
                console.log(colorId);
                inputColor.value=colorId;
            });
        });
    }
 
}