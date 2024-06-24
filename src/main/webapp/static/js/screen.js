import {closeModal, openModal, isNotOpen} from "./modal.js";

const MIN_ACCEPTABLE_WIDTH = 1024;
let modal;

function initElements() {

    modal = document.getElementById("smallScreenModal");
}

function isInnerWidthTooSmall() {

    return window.innerWidth < MIN_ACCEPTABLE_WIDTH;
}

export function initScreen() {

    initElements();

    if (isNotOpen(modal) && isInnerWidthTooSmall()) {

        openModal(modal);
    }

    window.addEventListener("resize", () => {

        if (isNotOpen(modal) && isInnerWidthTooSmall()) {

            openModal(modal);
        }
        else {

            closeModal(modal);
        }
    });
}