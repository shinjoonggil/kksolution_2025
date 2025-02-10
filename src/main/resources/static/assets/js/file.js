window.addEventListener('load', e => {
    document.querySelectorAll('form').forEach(form => {

        form.querySelectorAll('.file-uploader').forEach(el => {
            const dataTransfer = new DataTransfer()  // 새로운 DataTransfer 객체
            const {id, accept, name, url} = el.dataset
            const input = document.createElement('input')
            input.id = id
            input.name = name
            input.accept = accept
            input.type = 'file'
            input.multiple = true
            input.hidden = true
            form.appendChild(input)

            el.addEventListener('click', e => {
                input.click()
            })
            // 드래그 앤 드롭 기능 추가
            el.addEventListener('dragover', (e) => {
                e.preventDefault()
                el.style.border = '2px dashed #007bff' // 스타일 변경
            })

            el.addEventListener('dragleave', () => {
                el.style.border = null
            })

            el.addEventListener('drop', (e) => {
                e.preventDefault()
                const {files} = e.dataTransfer
                el.style.border = null
                for (let index = 0; index < files.length; index++) {
                    const file = files[index]
                    dataTransfer.items.add(file)
                }
                set()
            })
            input.addEventListener('change', e => {
                const {files} = e.target
                for (let index = 0; index < files.length; index++) {
                    const file = files[index]
                    dataTransfer.items.add(file)
                }
                input.value = ''
                set()
            })
            form.addEventListener('submit', e => {
                input.files = dataTransfer.files
                console.log(dataTransfer)
                console.log(dataTransfer.files)
                for(let  i=0;i<dataTransfer.files.length;i++){
                    dataTransfer.files[i].delete=true
                    console.log(dataTransfer.files[i])
                }
                // e.preventDefault()
            })
            const set = () => {
                const {files} = dataTransfer
                el.innerHTML = ''
                for (let index = 0; index < files.length; index++) {
                    const file = files[index]

                    const tag = document.createElement('div')

                    const fileInfo = document.createElement('p')
                    fileInfo.classList.add('info')
                    const name = document.createElement('span')
                    name.textContent = file.name
                    const size = document.createElement('span')
                    size.textContent = `(${file.size}KB)`
                    fileInfo.appendChild(name)
                    fileInfo.appendChild(size)


                    const fileButton = document.createElement('p')
                    fileButton.classList.add('actions')

                    const previewButton = document.createElement('a')

                    previewButton.textContent = '미리보기'
                    previewButton.addEventListener('click', e => {
                        e.preventDefault()
                        e.stopPropagation()
                        const fileReader = new FileReader()
                        fileReader.addEventListener('load', (e) => {
                            const blob = new Blob([e.target.result], {type: file.type})
                            const url = URL.createObjectURL(blob)
                            window.open(url)
                        })
                        fileReader.readAsArrayBuffer(file)
                    })

                    const deleteButton = document.createElement('a')
                    deleteButton.textContent = '삭제하기'
                    deleteButton.addEventListener('click', e => {
                        e.preventDefault()
                        e.stopPropagation()
                        dataTransfer.items.remove(index)
                        set()
                    })
                    fileButton.appendChild(previewButton)
                    fileButton.appendChild(deleteButton)

                    tag.appendChild(fileInfo)
                    tag.appendChild(fileButton)
                    el.appendChild(tag)
                }
            }
        })

    })
})

