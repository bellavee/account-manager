toastr.options = {
    "closeButton": true,
    "debug": false,
    "newestOnTop": true,
    "progressBar": true,
    "positionClass": "toastr-top-right",
    "preventDuplicates": false,
    "onclick": null,
    "showDuration": "300",
    "hideDuration": "1000",
    "timeOut": "5000",
    "extendedTimeOut": "1000",
    "showEasing": "swing",
    "hideEasing": "linear",
    "showMethod": "fadeIn",
    "hideMethod": "fadeOut"
};

let message;

htmx.on("showMessage", (e) => {
    const result = JSON.parse(e.detail.value);
    if (result.resCode === '00')
        toastr.success(result.message);
    else if (result.resCode === '02')
        toastr.warning(result.message);
    else
        toastr.error(result.message);
})

htmx.on("exportWeeklyReport", (e) => {
    window.location.href = "/export/weekly-report/" + e.detail.value;
})

htmx.on("exportMonthlyReport", (e) => {
    window.location.href = "export/monthly-report/" + e.detail.value;
})


