document.addEventListener("DOMContentLoaded", function () {
    let quantity = 1;
    let selectedSize = ""; // Lưu trữ giá trị của "size select"
    let selectedFlag = false; // Cờ để kiểm tra đã chọn size và quantity chưa

    const decreaseButton = document.getElementById("decrease");
    const increaseButton = document.getElementById("increase");
    const quantityInput = document.getElementById("quantity");
    const sizeSelect = document.getElementById("size-select");

    decreaseButton.addEventListener("click", function () {
        if (quantity > 1) {
            quantity--;
            updateQuantityDisplay();
        }
    });

    increaseButton.addEventListener("click", function () {
        quantity++;
        updateQuantityDisplay();
    });

    function updateQuantityDisplay() {
        quantityInput.value = quantity;
    }

    const addToCartButton = document.querySelector(".add-to-cart");

    addToCartButton.addEventListener("click", function () {
        const size = sizeSelect.value;
        if (!size || !quantity) {
            alert("Please select a size and quantity."); // Kiểm tra cả size và quantity
            return;
        }

        selectedSize = size; // Lưu trữ giá trị của "size select"
        selectedFlag = true; // Đặt cờ là true khi đã chọn size và quantity
        showNotification("Added to cart", size, quantity);
        sizeSelect.value = ""; // Thiết lập giá trị của "size select" về empty sau khi thêm vào giỏ hàng
        quantity = 1; // Thiết lập số lượng về 1 sau khi thêm vào giỏ hàng
        updateQuantityDisplay(); // Cập nhật hiển thị số lượng
    });

    function showNotification(message, size, quantity) {
        if (!selectedFlag) {
            // Chỉ hiển thị cảnh báo nếu đã chọn size và quantity
            alert("Please select a size and quantity.");
            return;
        }

        const notification = document.getElementById("notification");
        notification.textContent = `${message} Size: ${size}, Quantity: ${quantity}`;
        notification.classList.remove("hidden");
        notification.classList.add("visible");

        setTimeout(() => {
            notification.classList.remove("visible");
            notification.classList.add("hidden");
            // Đặt lại cờ là false sau khi thông báo đã đóng
            selectedFlag = false;
        }, 3000);
    }

    // Validate input to accept only numeric values
    quantityInput.addEventListener("input", function () {
        var currentValue = quantityInput.value;
        if (!/^\d+$/.test(currentValue)) {
            quantityInput.value = currentValue.replace(/\D/g, "");
        }
        quantity = parseInt(quantityInput.value);
    });
});


document.addEventListener("DOMContentLoaded", function () {
    const removeButton = document.querySelector(".left-button.remove");
    const elementToRemove = document.querySelector(".wishlist-item");

    removeButton.addEventListener("click", function () {
        elementToRemove.remove(); // Xóa phần tử khi nút "remove" được nhấn
    });
});