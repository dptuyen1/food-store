/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

const deleteData = (path) => {
    fetch(path, {
        method: "DELETE"
    }).then(res => {
        if (res.status === 204) {
            location.reload();
        } else {
            let body = document.querySelector(".modal-body");
            body.innerText = "Có lỗi xảy ra, vui lòng thử lại...";
        }
    });
};