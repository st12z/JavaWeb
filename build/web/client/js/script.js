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

