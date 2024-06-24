let filterForm;
let name;
let category;
let priority;
let dateFrom;
let dateTo;
let clear;
let urlParams;

function initElements() {

    filterForm = document.querySelector("form.is-filter");

    if (!filterForm) return;

    name = filterForm.querySelector("input[name=name]");
    category = filterForm.querySelector("select[name=category]");
    priority = filterForm.querySelector("select[name=priority]");
    dateFrom = document.getElementById("dateFrom");
    dateTo = document.getElementById("dateTo");

    if (dateFrom && dateTo) {

        dateFrom.addEventListener("click", initDateValues);
        dateTo.addEventListener("click", initDateValues)
    }

    clear = filterForm.querySelector(".js-clear");
    urlParams = new URLSearchParams(window.location.search);

    initClearAll();
}

function initDateValues() {

    if (dateFrom && dateTo) {

        dateTo.min = dateFrom.value;
        dateFrom.max = dateTo.value;
    }
}

function initClearAll() {

    clear.addEventListener("click", () => {

        if (name) {

            name.value = "";
        }

        if (category) {

            category.value = "";
        }

        if (priority) {

            priority.value = "";
        }

        if (dateFrom) {

            dateFrom.value = "";
        }

        if (dateTo) {

            dateTo.value = "";
        }

        filterForm.submit();
    });
}

function initData() {

    if (name) {

        name.value = urlParams.get("name");
    }

    if (category) {

        let categoryValue = urlParams.get("category");

        category.value = categoryValue ? +categoryValue : "";
    }

    if (priority) {

        let priorityValue = urlParams.get("priority");

        priority.value = priorityValue ? +priorityValue : "";
    }

    if (dateFrom) {

        dateFrom.value = urlParams.get("dateFrom");
    }

    if (dateTo) {

        dateTo.value = urlParams.get("dateTo");
    }
}

export function initFilter() {

    initElements();
    initData();
}
