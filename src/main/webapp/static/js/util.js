const numberInputExcludes = ['-', '+', 'e'];

export function redirectTo(redirection) {

    if (!redirection) return;

    window.location.href = "/" + redirection
}

export function isCurrentPathLogin() {

    return window.location.pathname === "/login";
}

export function hideElement(element) {

    if (!element) return;

    element.style.display = "none";
}

export function showElement(element) {

    if (!element) return;

    element.style.display = "inline";
}

export function initNumberInput() {

    document.querySelectorAll('input[type="number"]').forEach(numberInput => {

        numberInput.addEventListener('keydown', (e) => {

            if (numberInputExcludes.includes(e.key)) {

                e.preventDefault();
            }
        });
    });
}