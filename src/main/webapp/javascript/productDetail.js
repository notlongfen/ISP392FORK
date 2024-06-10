document.addEventListener('DOMContentLoaded', () => {
    const thumbnails = document.querySelectorAll('.thumbnail img');
    const mainImage = document.getElementById('mainImage');
    const quantityElement = document.getElementById('quantity');
    const sizeButtons = document.querySelectorAll('.size');
    let quantity = 1;

    thumbnails.forEach(thumbnail => {
        thumbnail.addEventListener('click', () => {
            mainImage.src = thumbnail.src;
        });
    });

    document.getElementById('decrease').addEventListener('click', () => {
        if (quantity > 1) {
            quantity--;
            quantityElement.textContent = quantity;
        }
    });

    document.getElementById('increase').addEventListener('click', () => {
        quantity++;
        quantityElement.textContent = quantity;
    });

    document.getElementById('addToCart').addEventListener('click', () => {
        alert(`Added ${quantity} items to cart`);
    });

    document.getElementById('favorite').addEventListener('click', () => {
        alert('Added to favorites');
    });

    sizeButtons.forEach(button => {
        button.addEventListener('click', () => {
            sizeButtons.forEach(btn => btn.classList.remove('selected'));
            button.classList.add('selected');
        });
    });
});
