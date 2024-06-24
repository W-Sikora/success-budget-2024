let rankingForm;
let tableBody;
let orderedIdsInput;

function updateOrder() {

    const rows = tableBody.querySelectorAll("tr");
    const orderedIds = [];

    rows.forEach((row, index) => {

        row.querySelector(".no").textContent = index + 1;

        orderedIds.push(row.getAttribute("data-id"));
    });

    orderedIdsInput.value = orderedIds.join(",");

    rankingForm.submit();
}

function moveRowUp(row) {

    const prevRow = row.previousElementSibling;

    if (prevRow) {

        tableBody.insertBefore(row, prevRow);
    }
}

function moveRowDown(row) {

    const nextRow = row.nextElementSibling;

    if (nextRow) {

        tableBody.insertBefore(nextRow, row);
    }
}

export function initRanking(){

    rankingForm = document.getElementById("rankingForm");
    tableBody = document.getElementById("tableBody");
    orderedIdsInput = document.getElementById("orderedIds");

    if (!rankingForm || !tableBody || !orderedIdsInput) return;

    tableBody.addEventListener("click", (event) => {

        if (event.target.closest(".move-up") && !event.target.closest(".move-up").disabled) {

            const row = event.target.closest("tr");

            moveRowUp(row);

            updateOrder();
        }
        else if (event.target.closest(".move-down") && !event.target.closest(".move-down").disabled) {

            const row = event.target.closest("tr");

            moveRowDown(row);

            updateOrder();
        }
    });
}