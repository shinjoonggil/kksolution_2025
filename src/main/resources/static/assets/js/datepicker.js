window.addEventListener('load',()=>{
    document.addEventListener("DOMContentLoaded", function () {
        flatpickr("#datetimepicker", {
            enableTime: true,
            dateFormat: "Y-m-d H:i",
            time_24hr: true,
            locale: 'ko'
        })
    })
    document.querySelectorAll('input[data-type=date-picker]').forEach(el=>{
        flatpickr(el, {
            enableTime: true,
            dateFormat: "Y-m-d H:i",
            time_24hr: true,
            locale: 'ko'
        });

    })
})