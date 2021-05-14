function popup() {
    var popup = document.getElementById('id01');

    window.onclick = function (event) {
        if (event.target == popup) {
            popup.style.display = "none";
        }
    }
}