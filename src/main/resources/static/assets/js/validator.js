window.addEventListener('load', () => {

    document.querySelectorAll('input[type=tel]').forEach(el => {
        el.addEventListener('input', e => {
            e.target.value = e.target.value
                .replace(/\D/g, '')
                .slice(0, 11)
                .replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, '$1-$2-$3')
        })
    })
    document.querySelectorAll('input[data-type=business-number]').forEach(el=>{
        el.addEventListener('input', e => {
            e.target.value = e.target.value
                .replace(/\D/g, '')
                .slice(0, 10)
                .replace(/^(\d{3})(\d{2})(\d{5})$/, '$1-$2-$3')
        })
    })


})