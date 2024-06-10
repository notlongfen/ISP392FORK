document.addEventListener('DOMContentLoaded', function () {
    var decreaseBtn = document.getElementById('decrease');
    var increaseBtn = document.getElementById('increase');
    var quantitySpan = document.getElementById('quantity');
    var addToCartButton = document.getElementById('add_to_cart');
    var addToWishlistButton = document.getElementById('add_to_wishlist');

    var notificationVisible = false;

    // Thêm sự kiện click cho nút giảm
    decreaseBtn.addEventListener('click', function () {
        if (!notificationVisible) {
            var currentQuantity = parseInt(quantitySpan.innerText);
            if (currentQuantity > 1) {
                quantitySpan.innerText = currentQuantity - 1;
            }
        }
    });

    // Thêm sự kiện click cho nút tăng
    increaseBtn.addEventListener('click', function () {
        if (!notificationVisible) {
            var currentQuantity = parseInt(quantitySpan.innerText);
            quantitySpan.innerText = currentQuantity + 1;
        }
    });

    addToCartButton.addEventListener('click', function (event) {
        event.preventDefault();
        hideNotification();
        showNotification('Added to cart!');
    });

    addToWishlistButton.addEventListener('click', function (event) {
        event.preventDefault();
        hideNotification();
        showNotification('Added to wishlist!');
    });

    function showNotification(message) {
        notificationVisible = true;
        var notification = document.createElement('div');
        notification.className = 'notification';
        notification.textContent = message;
        document.body.appendChild(notification);
        notification.style.display = 'block';
        setTimeout(() => {
            hideNotification();
        }, 3000);
    }

    function hideNotification() {
        notificationVisible = false;
        var existingNotification = document.querySelector('.notification');
        if (existingNotification) {
            existingNotification.style.display = 'none';
            document.body.removeChild(existingNotification);
        }
    }

    addToCartButton.addEventListener('click', function () {
        var selectedSize = "";
        var sizeButtons = document.querySelectorAll('.sizes .size');
        sizeButtons.forEach(function (button) {
            if (button.classList.contains('selected')) {
                selectedSize = button.innerText;
            }
        });

        var selectedQuantity = parseInt(document.getElementById('quantity').innerText);

        if (selectedSize !== "" && !isNaN(selectedQuantity)) {
            alert("Selected Size: " + selectedSize + "\nQuantity: " + selectedQuantity);
            sizeButtons.forEach(function (button) {
                button.classList.remove('selected');
            });
        } else {
            alert("Please select size and quantity before adding to cart.");
        }
    });

    // Thêm sự kiện click cho các nút kích thước
    var sizeButtons = document.querySelectorAll('.sizes .size');
    sizeButtons.forEach(function (button) {
        button.addEventListener('click', function () {
            sizeButtons.forEach(function (btn) {
                btn.classList.remove('selected');
            });
            button.classList.add('selected');
        });
    });
});
