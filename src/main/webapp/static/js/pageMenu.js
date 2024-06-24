let previousStepButton;
let previousStepForm;
let nextStepButton;
let nextStepForm;

function initElements() {

    previousStepButton = document.getElementById("previousStepButton");
    previousStepForm = document.getElementById("previousStepForm")
    nextStepButton = document.getElementById("nextStepButton");
    nextStepForm = document.getElementById("nextStepForm");
}

function initEvent(button, form) {

    if (!button || !form) return;

    button.addEventListener("click", (event) => {

        event.stopPropagation();

        if (!button.disabled) {

            form.submit();
        }
    });
}

export function initNext() {

    initElements();
    initEvent(previousStepButton, previousStepForm);
    initEvent(nextStepButton, nextStepForm);
}