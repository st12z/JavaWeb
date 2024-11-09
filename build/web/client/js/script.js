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

                buttonColor.forEach(button => {
                    button.classList.remove("active");
                })
                // Cập nhật giá trị cho input
                button.classList.add("active");
                const colorId = button.getAttribute("colorId");
                console.log(colorId);
                inputColor.value = colorId;
            });
        });
    }

}
const uploadImage = document.querySelector("[upload-image]");
if (uploadImage) {
    const inputFile = uploadImage.querySelector("[upload-image-input]");
    inputFile.addEventListener("change", (e) => {
        const file = e.target.files[0];
        const imgSrc = uploadImage.querySelector(".image-preview");
        if (file) {
            imgSrc.src = URL.createObjectURL(file);
        }
    });
}
;
const updateStar = (value, stars) => {
    stars.forEach((star) => {
        const starValue = parseInt(star.getAttribute("data-value"));
        if (starValue <= value) {
            star.classList.add("checked");
        } else {
            star.classList.remove("checked");
        }
    });
};
const inputRating = document.querySelector("input[rating]");
const reviewStars = document.querySelectorAll("[reviewing-star]");
console.log(reviewStars);
if (reviewStars) {
    reviewStars.forEach((star) => {
        star.addEventListener("click", () => {
            const value = star.getAttribute("data-value");
            inputRating.value = value;
            updateStar(value, reviewStars);
        });
    });
}
const reviewedStar = () => {
  const reviewedRatings = document.querySelectorAll("[reviewed-rating]");
  if (reviewedRatings) {
    reviewedRatings.forEach((reviewedRating) => {
      const rating = reviewedRating.getAttribute("reviewed-rating");
      const reviewedStars = reviewedRating.querySelectorAll("[reviewed-star]");
      console.log(reviewedStars);
      reviewedStars.forEach((reviewedStar) => {
        const data = reviewedStar.getAttribute("data-value");
        if (data <= rating) {
          reviewedStar.classList.add("checked");
        } else {
          reviewedStar.classList.remove("checked");
        }
      });
    });
  }
};
reviewedStar();