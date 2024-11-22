/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

const trash=document.querySelector(".fa-trash");
if(trash){
    trash.addEventListener("click",()=>{
        alert("Bạn có chắc muốn xóa không?");
    });
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
