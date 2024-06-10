document.addEventListener('DOMContentLoaded', function () {
    "use strict";

    var header = document.querySelector('.header');
    var topNav = document.querySelector('.top_nav');
    var mainSlider = document.querySelector('.main_slider');
    var hamburger = document.querySelector('.hamburger_container');
    var menu = document.querySelector('.hamburger_menu');
    var menuActive = false;
    var hamburgerClose = document.querySelector('.hamburger_close');
    var fsOverlay = document.querySelector('.fs_menu_overlay');

    setHeader();

    window.addEventListener('resize', function () {
        initFixProductBorder();
        setHeader();
    });

    document.addEventListener('scroll', function () {
        setHeader();
    });

    initMenu();
    initFavorite();
    initFixProductBorder();
    initIsotopeFiltering();
    initPriceSlider();
    initCheckboxes();
});

function openMenu() {
    menu.classList.add('active');
    fsOverlay.style.pointerEvents = "auto";
    menuActive = true;
}

function closeMenu() {
    menu.classList.remove('active');
    fsOverlay.style.pointerEvents = "none";
    menuActive = false;
}
function initFavorite() {
    var favs = document.querySelectorAll('.favorite');
    favs.forEach(function (fav) {
        var active = fav.classList.contains('active');

        fav.addEventListener('click', function () {
            if (active) {
                fav.classList.remove('active');
                active = false;
            } else {
                fav.classList.add('active');
                active = true;
            }
        });
    });
}


function initFixProductBorder() {
    var products = document.querySelectorAll('.product_filter:visible');
    var wdth = window.innerWidth;

    products.forEach(function (product) {
        product.style.borderRight = 'solid 1px #e9e9e9';
    });

    if (wdth < 480) {
        products.forEach(function (product) {
            product.style.borderRight = 'none';
        });
    } else if (wdth < 576) {
        if (products.length < 5) {
            products[products.length - 1].style.borderRight = 'none';
        }
        for (var i = 1; i < products.length; i += 2) {
            products[i].style.borderRight = 'none';
        }
    } else if (wdth < 768) {
        if (products.length < 5) {
            products[products.length - 1].style.borderRight = 'none';
        }
        for (var i = 2; i < products.length; i += 3) {
            products[i].style.borderRight = 'none';
        }
    } else if (wdth < 992) {
        if (products.length < 5) {
            products[products.length - 1].style.borderRight = 'none';
        }
        for (var i = 2; i < products.length; i += 3) {
            products[i].style.borderRight = 'none';
        }
    } else {
        if (products.length < 5) {
            products[products.length - 1].style.borderRight = 'none';
        }
        for (var i = 3; i < products.length; i += 4) {
            products[i].style.borderRight = 'none';
        }
    }
}



function initIsotopeFiltering() {
    var sortTypes = document.querySelectorAll('.type_sorting_btn');
    var sortNums = document.querySelectorAll('.num_sorting_btn');
    var filterButton = document.querySelector('.filter_button');

    if (document.querySelector('.product-grid')) {
        var grid = document.querySelector('.product-grid');
        var iso = new Isotope(grid, {
            itemSelector: '.product-item',
            getSortData: {
                price: function (itemElem) {
                    var price = itemElem.querySelector('.product_price').textContent.replace('$', '');
                    return parseFloat(price);
                },
                name: '.product_name'
            },
            animationOptions: {
                duration: 750,
                easing: 'linear',
                queue: false
            }
        });

        sortTypes.forEach(function (btn) {
            btn.addEventListener('click', function () {
                var option = JSON.parse(btn.getAttribute('data-isotope-option'));
                iso.arrange(option);
            });
        });

        sortNums.forEach(function (btn) {
            btn.addEventListener('click', function () {
                var numSortingText = btn.textContent;
                var numFilter = ':nth-child(-n+' + numSortingText + ')';
                iso.arrange({filter: numFilter});
            });
        });

        filterButton.addEventListener('click', function () {
            var priceRange = document.getElementById('amount').value;
            var priceMin = parseFloat(priceRange.split('-')[0].replace('$', ''));
            var priceMax = parseFloat(priceRange.split('-')[1].replace('$', ''));
            iso.arrange({
                filter: function (itemElem) {
                    var itemPrice = parseFloat(itemElem.querySelector('.product_price').textContent.replace('$', ''));
                    return (itemPrice > priceMin) && (itemPrice < priceMax);
                }
            });
        });
    }
}


function initPriceSlider() {
    var slider = document.getElementById("slider-range");
    noUiSlider.create(slider, {
        start: [0, 580],
        connect: true,
        range: {
            'min': 0,
            'max': 1000
        }
    });

    var amount = document.getElementById('amount');
    slider.noUiSlider.on('update', function (values) {
        amount.value = "$" + values[0] + " - $" + values[1];
    });

    amount.value = "$" + slider.noUiSlider.get()[0] + " - $" + slider.noUiSlider.get()[1];
}


function initCheckboxes() {
    var boxes = document.querySelectorAll('.checkboxes li');

    boxes.forEach(function (box) {
        box.addEventListener('click', function () {
            if (box.classList.contains('active')) {
                box.querySelector('i').classList.remove('fa-square');
                box.querySelector('i').classList.add('fa-square-o');
                box.classList.toggle('active');
            } else {
                box.querySelector('i').classList.remove('fa-square-o');
                box.querySelector('i').classList.add('fa-square');
                box.classList.toggle('active');
            }
        });
    });

    var showMore = document.querySelector('.show_more');
    if (showMore) {
        var checkboxes = document.querySelector('.checkboxes');
        showMore.addEventListener('click', function () {
            checkboxes.classList.toggle('active');
        });
    }
}
