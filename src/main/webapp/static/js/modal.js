const CSS_ACTIVATION_CLASS = 'is-active';

export function openModal(modal) {

    modal.classList.add(CSS_ACTIVATION_CLASS);
}

export function closeModal(modal) {

    modal.classList.remove(CSS_ACTIVATION_CLASS);
}

export function isNotOpen(modal) {

    return !modal.classList.contains(CSS_ACTIVATION_CLASS);
}