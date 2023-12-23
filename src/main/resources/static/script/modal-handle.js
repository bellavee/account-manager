const modal = new bootstrap.Modal(document.getElementById("modal"))

htmx.on("htmx:afterSwap", (e) => {
    if (e.detail.target.id == "dialog") {
        modal.show();
    }
})

htmx.on("htmx:beforeSwap", (e) => {
    if (e.detail.target.id.includes("table")) {
        if (e.detail.xhr.response !== "") {
            modal.hide();
            e.detail.shouldSwap = true;
        } else {
            e.detail.shouldSwap = false;
        }
    }
})