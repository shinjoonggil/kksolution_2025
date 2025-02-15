let n = null
window.addEventListener('load', () => {
    n = new Notyf({
        duration: 2000,
        position: {
            x: 'right',
            y: 'bottom'
        },
        types: [
            {
                type: 'SUCCESS',
                background: '#99cc33'
            },
            {
                type: 'WARN',
                background: '#ffcc00',

            },
            {
                type: 'ERROR',
                background: '#ff6666',

            },
            {
                type: 'INFO',
                background: '#61a1f1',
            },
            {
                type: 'DEFAULT',
                background: '#6a6a6a',
            }
        ]
    })
})

window.addEventListener('load', e => {
    document.body.classList.remove('loading')
})

window.addEventListener('load', () => {
    const hasAllCheckListSection = document.querySelectorAll('section.list:has(#all)')
    hasAllCheckListSection.forEach(section => {
        const allCheckInput = section.querySelector('#all')
        const listCheckItem = section.querySelectorAll('input[type=checkbox]:not(#all)')
        allCheckInput.addEventListener('change', e => {
            const {checked} = e.target
            if (listCheckItem.length) {
                listCheckItem.forEach(el => {
                    el.checked = checked
                })
            } else {
                e.target.checked = false
            }

        })
        listCheckItem.forEach(checkbox => {
            checkbox.addEventListener('change', e => {
                const checkedCount = section.querySelectorAll('input[type=checkbox]:not(#all):checked').length
                allCheckInput.checked = checkedCount === listCheckItem.length
            })
        })
    })
})
window.addEventListener('load', e => {
    document.getElementById('toggle').addEventListener('click', () => {
        if (document.body.classList.contains('hide')) {
            document.body.classList.remove('hide')
            document.cookie = `menu-status=; path=/admin; expires=90000000`;
        } else {
            document.body.classList.add('hide')
            document.cookie = `menu-status=hide; path=/admin; expires=90000000`;
        }
    })
})
window.addEventListener('load', () => {
    const deleteFormModal = document.querySelector('.modal:has(#deleteForm)')
    const deleteFormModalContainer = deleteFormModal.querySelector('.modal-container')
    const deleteForm = deleteFormModal.querySelector('#deleteForm')
    const deleteId = deleteForm.querySelector('input[name=id]')
    const deleteText = deleteForm.querySelector('input[name=deleteText]')
    const cancelButton = deleteForm.querySelector('button[type=button]')
    deleteFormModalContainer.addEventListener('click', e => {
        e.stopPropagation()
    })
    deleteFormModal.addEventListener('click', e => {
        e.preventDefault()
        deleteForm.action = ''
        deleteId.value = ''
    })
    cancelButton.addEventListener('click', e => {
        e.preventDefault()
        deleteForm.action = ''
        deleteId.value = ''
    })
    deleteForm.addEventListener('submit', e => {
        e.preventDefault()
        e.target.submit()

    })
    document.querySelectorAll('button[data-type=delete]').forEach(button => {
        const {id, action} = button.dataset
        button.addEventListener('click', e => {
            e.preventDefault()
            deleteText.value = ''
            if (id && action) {
                deleteForm.action = action
                deleteId.value = id
            } else {
                deleteForm.action = ''
                deleteId.value = ''
            }
            deleteText.focus()
        })
    })
    document.querySelectorAll('.form-control:has(input[data-type=select])').forEach(control => {
        const optionsContainer = control.querySelector('ul')
        const options = optionsContainer.querySelectorAll('li')
        const span = control.querySelector('span.select-value')
        const input = control.querySelector('input[data-type=select]')
        span.addEventListener('click', e => {
            e.stopPropagation()
            optionsContainer.style.display = 'block'
            window.addEventListener('click', e => {
                optionsContainer.style.display = 'none'
            })
        })
        options.forEach(option => {
            option.addEventListener('click', e => {
                input.value = e.target.dataset.value
                span.textContent = e.target.textContent
            })
        })
    })

    document.querySelectorAll('.form-control:has([data-type=auto-complete])').forEach(control => {
        const input = control.querySelector('input')
        const list = control.querySelector('ul')
        const items = list.querySelectorAll('li')

        const setCompleteList = (text) => {
            items.forEach(item => {
                if (!item.textContent.includes(text)) {
                    item.style.display = 'none'
                } else {
                    item.style.removeProperty('display')
                }
            })
        }

        input.addEventListener('focus', e => {
            e.stopPropagation()
            list.style.display = 'block'
            window.addEventListener('click', e => {
                list.style.removeProperty('display')
            })
        })
        input.addEventListener('input', e => setCompleteList(e.target))

        items.forEach(item => {
            item.addEventListener('click', e => {
                input.value = item.textContent
                setCompleteList(item.textContent)
            })
        })
        setCompleteList(input.value)




    })
})
