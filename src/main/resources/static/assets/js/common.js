window.addEventListener('load', () => {
    // Hover
    document.querySelectorAll('.hover').forEach(el => {
        const text = el.textContent
        el.addEventListener('mouseover', () => {
            el.textContent = ''
            const letters = text.split('')
            const duration = parseInt(el.dataset.duration || 500)
            const step = duration / 10
            const animation = el.dataset.animation || 'jump'
            letters.forEach((letter, index) => {
                if (!letter.trim()) letter = "&nbsp;"
                const letterElement = document.createElement('span')
                letterElement.style.animationDuration = `${duration}ms`
                setTimeout(() => letterElement.classList.add(animation), step * index)
                letterElement.textContent = letter
                el.appendChild(letterElement)
            })
            setTimeout(() => el.innerHTML = text, step * letters.length + duration)
        })
    })
})
