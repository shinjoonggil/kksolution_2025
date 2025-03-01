window.addEventListener('load', () => {
    AOS.init()

    // Hover
    document.querySelectorAll('.hover').forEach(el => {
        const cloneNode = el.cloneNode(true)
        const duration = parseInt(el.dataset.duration || 500)
        const step = duration / 10
        const animation = el.dataset.animation || 'jump'

        const mouseOverHandler = (e) => {

            el.innerHTML = ''
            cloneNode.childNodes.forEach(node => {
                if (node.nodeType === Node.TEXT_NODE) {
                    node.textContent.split('').forEach((letter, index) => {
                        if (!letter.trim()) letter = "&nbsp;"
                        const letterElement = document.createElement('span')
                        letterElement.style.animationDuration = `${duration}ms`
                        setTimeout(() => letterElement.classList.add(animation), step * index)
                        letterElement.innerHTML = letter
                        el.appendChild(letterElement)
                    })
                } else {
                    el.appendChild(node.cloneNode(true))
                }
            })


            setTimeout(() => {
                el.replaceChildren(...Array.from(cloneNode.childNodes).map(node => node.cloneNode(true)));


                // cloneNode.childNodes.forEach(node=>{
                //     el.appendChild(node)
                // })
            }, step * el.childNodes.length + duration)
        }
        el.addEventListener('mouseover', mouseOverHandler)
    })


    const cursor = document.getElementById('cursor')
    if (cursor) {
        const updateCursorPosition = (event) => {
            document.documentElement.style.setProperty('--cursor-x', event.clientX + 'px')
            document.documentElement.style.setProperty('--cursor-y', event.clientY + 'px')
            document.removeEventListener('mousemove', updateCursorPosition)
        }
        window.addEventListener('mousemove', updateCursorPosition)
    }
    const SHOWN_CLASS = 'shown'
    const header = document.querySelector('header')
    const scrollGuide = document.getElementById('scroll-guide')
    const headerHeight = header.offsetHeight
    const headerShownHandler = () => {

        if (window.scrollY > headerHeight) {
            if (!header.classList.contains(SHOWN_CLASS)) {
                header.classList.add(SHOWN_CLASS)
            }
            if (scrollGuide.classList.contains(SHOWN_CLASS)) {
                scrollGuide.classList.remove(SHOWN_CLASS)
            }
        } else {
            if (header.classList.contains(SHOWN_CLASS)) {
                header.classList.remove(SHOWN_CLASS)
            }
            if (!scrollGuide.classList.contains(SHOWN_CLASS)) {
                scrollGuide.classList.add(SHOWN_CLASS)
            }
        }
    }
    headerShownHandler()
    window.addEventListener('scroll', headerShownHandler)


    document.querySelectorAll('[data-modal-target]').forEach(handler => {
        const {modalTarget} = handler.dataset
        const targetModal = document.querySelector(`[data-modal=${modalTarget}]`)
        if (targetModal) {
            const container = targetModal.querySelector('.modal-container')
            handler.addEventListener('click', e => {
                targetModal.classList.add('active')
            })
            targetModal.addEventListener('click', e => {
                targetModal.classList.remove('active')
            })
            container.addEventListener('click', e => {
                e.stopPropagation()
            })
        }

    })

    document.querySelectorAll('[data-copy]').forEach(el => {
        el.addEventListener('click', e => {
            navigator.clipboard.writeText(el.dataset.copy).then((res, a, b) => {
                alert('클립보드에 복사 되었습니다.')
            }).catch(e => {
                console.error(e)
            })


        })

    })
})
