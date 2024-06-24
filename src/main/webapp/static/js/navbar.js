import {redirectTo, isCurrentPathLogin, hideElement} from "./util.js";

const CSS_ACTIVATION_CLASS = "is-active";

let navbarDropdown;
let localeForm;
let changeLocaleElements;
let navbarLoginButton;
let navbarLogoutButton;
let navbarLogoutForm;

function initElements() {

    navbarDropdown = document.getElementById("navbarDropdown");
    localeForm = document.getElementById("localeForm");
    changeLocaleElements = document.querySelectorAll(".change-locale");
    navbarLoginButton = document.getElementById("navbarLoginButton");
    navbarLogoutButton = document.getElementById("navbarLogoutButton");
    navbarLogoutForm = document.getElementById("navbarLogoutForm");
}

function initNavbarDropdown() {

    if (!navbarDropdown) return;

    navbarDropdown.addEventListener("click", (event) => {

        event.stopPropagation();

        navbarDropdown.classList.toggle(CSS_ACTIVATION_CLASS);
    });

    changeLocaleElements.forEach(el =>

        el.addEventListener("click", () => setLocale(el.dataset.code))
    );
}

function setLocale(value) {

    if (!value) return;

    localeForm.querySelector("input[name=language]").value = value;
    localeForm.querySelector("input[name=originalUrl]").value = window.location.href;
    localeForm.submit();
}

function initNavbarLogin() {

    if (!navbarLoginButton) return;

    if (isCurrentPathLogin()) {

        hideElement(navbarLoginButton);
    }

    navbarLoginButton.addEventListener("click", () => {

        redirectTo("login");
    });
}

function initNavbarLogout() {

    if (!navbarLogoutButton || !navbarLogoutForm) return;

    navbarLogoutButton.addEventListener("click", () => {

        navbarLogoutForm.submit();
    });

}

export function initNavbar() {

    initElements();
    initNavbarDropdown();
    initNavbarLogin();
    initNavbarLogout()
}



