
document.addEventListener('DOMContentLoaded', (event) => {
    // Select the checkbox
    const toggle = document.getElementById('toggleAddButtons');

    // Add an event listener to toggle the add buttons
    toggle.addEventListener('change', function() {
        // Select all add buttons
        const addButtons = document.querySelectorAll('.add-button');

        // Toggle the display of the add buttons
        addButtons.forEach(button => {
            button.style.display = this.checked ? '' : 'none';
        });
    });
});

const timePicker = (id) => {
    new tempusDominus.TempusDominus(document.getElementById(id), {
        localization: {
            format: "HH:mm",
            hourCycle: 'h23'
        },
        display: {
            viewMode: "clock",
            components: {
                decades: false,
                year: false,
                month: false,
                date: false,
                hours: true,
                minutes: true,
                seconds: false,
            }
        }
    });
}

const datePicker = (id) => {
    new tempusDominus.TempusDominus(document.getElementById(id), {
        localization: {
            format: "dd/MM/yyyy",
            locale: "fr",
        },
        display: {
            viewMode: "calendar",
            components: {
                decades: true,
                year: true,
                month: true,
                date: true,
                hours: false,
                minutes: false,
                seconds: false
            }
        }
    });
}

const weekPicker = (id) => {
    new tempusDominus.TempusDominus(document.getElementById(id), {
        localization: {
            format: "dd/MM/yyyy",
            locale: "fr",
        },
        display: {
            calendarWeeks: true,
            viewMode: "calendar",
            components: {
                decades: true,
                year: true,
                month: true,
                date: true,
                hours: false,
                minutes: false,
                seconds: false
            }
        }
    });
}

const monthPicker = (id) => {
    new tempusDominus.TempusDominus(document.getElementById(id), {
        localization: {
            format: "MM/yyyy",
            locale: "fr",
        },
        display: {
            viewMode: "calendar",
            components: {
                decades: true,
                year: true,
                month: true,
                date: false,
                hours: false,
                minutes: false,
                seconds: false
            }
        }
    });
}

weekPicker("weekPicker");
monthPicker("monthPicker");

function uploadImage(id, file) {
    let formData = new FormData();
    formData.append('file', file)
    formData.append('id', id)

    $.ajax({
        url: '/api/upload', // Replace with your server endpoint
        type: 'POST',
        data: formData,
        processData: false,
        contentType: false,
        success: function(response) {
            toastr.success('File uploaded successfully')
        },
        error: function(xhr, status, error) {
            toastr.error('Error uploading file')
        }
    })
}