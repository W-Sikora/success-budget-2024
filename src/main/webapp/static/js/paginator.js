let previousPageButton;
let nextPageButton;
let form;
let pageInput;
let currentPage;
let lastPage;
let sizeSelect;
let sizeInput;


function initElements() {

    previousPageButton = document.querySelector(".js-previous-page-button");
    nextPageButton = document.querySelector(".js-next-page-button");
    form = document.querySelector(".js-pagination-form");
    sizeSelect = document.getElementById("sizeSelect");

    if (!sizeSelect) return;

    sizeSelect.value = '' + setSizeSelect();

    if (!form) return;

    pageInput = form.querySelector("input.js-parameter-page");
    sizeInput = form.querySelector("input.js-parameter-size");

    let paginator = document.getElementById("paginator");

    if (!paginator) return;

    currentPage = paginator.getAttribute("data-current");
    lastPage = paginator.getAttribute("data-last");
}

function setSizeSelect() {

    const urlParams = new URLSearchParams(window.location.search);

    let size = +urlParams.get("size");

    return size > 0 ? size : 15;
}

function onPreviousPageButtonClick() {

    if (currentPage > 0) {

        pageInput.value = +currentPage - 1;
        form.submit();
    }
}

function onNextPageButtonClick() {

    if (currentPage < lastPage) {

        pageInput.value = +currentPage + 1;
        form.submit();
    }
}

function onChangeSize(event) {

    sizeInput.value = event.target.value;
    form.submit();
}


export function initPaginator() {

    initElements();

    if (previousPageButton) {

        previousPageButton.addEventListener("click", onPreviousPageButtonClick);
    }

    if (nextPageButton) {

        nextPageButton.addEventListener("click", onNextPageButtonClick);
    }

    if (sizeSelect) {

        sizeSelect.addEventListener("change", (event) => onChangeSize(event));
    }
}