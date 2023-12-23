const deleteAlert = () => {
    $(document).on('htmx:confirm', '#delete-btn', (e) => {
        e.preventDefault();
        Swal.fire({
            text: "Êtes-vous sûr de le supprimer ?",
            icon: "info",
            buttonsStyling: false,
            showCancelButton: true,
            confirmButtonText: "Ok, supprime-le !",
            cancelButtonText: 'Non, annule-le !',
            customClass: {
                confirmButton: "btn btn-danger",
                cancelButton: 'btn btn-success'
            }

        }).then((result) => {
            if (result.isConfirmed) {
                e.detail.issueRequest();
            }
        });
    });
}

$(document).ready(function() {
    deleteAlert();
});

