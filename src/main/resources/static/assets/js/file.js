const UP_FILE_PATH = '/upload'
const LOADING_CLASS_NAME = 'loading'
window.addEventListener('DOMContentLoaded', e => {
    document.querySelectorAll('form').forEach(form => {
        // const csrfToken = form._csrf.value
        form.querySelectorAll('.file-uploader').forEach(el => {

            const {id, accept, name} = el.dataset

            const input = document.createElement('input')
            input.id = id
            input.name = name
            input.accept = accept
            input.type = 'file'
            input.multiple = true
            input.hidden = true
            form.appendChild(input)

            el.classList.add(LOADING_CLASS_NAME)
            fetch(`${UP_FILE_PATH}/${id}`)
                .then(response => {
                    return response.json()
                })
                .then(json => set(json))
                .finally(() => el.classList.remove(LOADING_CLASS_NAME))


            const uploadHandler = (files) => {
                let fileSize = 0;
                const form = new FormData()
                for (let index = 0; index < files.length; index++) {
                    const file = files[index]
                    fileSize += file.size
                    form.append("uploadFiles", file)
                }
                el.classList.add(LOADING_CLASS_NAME)
                fetch(`${UP_FILE_PATH}/${id}`, {
                    method: 'POST',
                    body: form,
                }).then(response => {
                    return response.json()
                })
                    .then(json => set(json))
                    .finally(() => el.classList.remove(LOADING_CLASS_NAME))

            }
            el.addEventListener('click', () => input.click())
            el.addEventListener('dragover', (e) => {
                e.preventDefault()
                el.style.border = '2px dashed #007bff'
            })
            el.addEventListener('dragleave', () => el.style.border = null)
            el.addEventListener('drop', (e) => {
                e.preventDefault()
                el.style.border = null
                const {files} = e.dataTransfer
                uploadHandler(files)
            })
            input.addEventListener('change', e => {
                const {files} = e.target
                uploadHandler(files)
                input.value = ''
            })

            const set = (files) => {

                el.innerHTML = ''
                for (let index = 0; index < files.length; index++) {
                    const file = files[index]

                    const tag = document.createElement('div')

                    const fileInfo = document.createElement('p')
                    fileInfo.classList.add('info')
                    const name = document.createElement('span')
                    name.textContent = file.origin
                    const size = document.createElement('span')
                    size.textContent = `(${file.size}KB)`
                    fileInfo.appendChild(name)
                    fileInfo.appendChild(size)


                    const fileButtons = document.createElement('p')
                    fileButtons.classList.add('actions')
                    const previewButton = document.createElement('a')
                    previewButton.classList.add('preview')
                    const downloadButton = document.createElement('a')
                    downloadButton.classList.add('download')
                    const deleteButton = document.createElement('a')
                    deleteButton.classList.add('delete')


                    previewButton.addEventListener('click', e => {
                        e.preventDefault()
                        e.stopPropagation()
                        el.classList.add(LOADING_CLASS_NAME)
                        fetch(`${UP_FILE_PATH}/${id}/${file.id}`)
                            .then(response => {
                                return response.blob()
                            }).then(blob => {

                            const url = URL.createObjectURL(blob)
                            let source = null
                            if(file.type.startsWith('image/')){
                                source = document.createElement('img')
                                source.src=url
                            }else{
                                source=document.createElement('iframe')
                                source.src=`https://docs.google.com/gview?url=${file.url}&embedded=true`
                            }
                            if(source){
                                const modalContent = document.createElement('div')
                                modalContent.classList.add('modal-content')
                                modalContent.appendChild(source)
                                const modal = document.createElement('div')
                                modal.classList.add('modal')
                                const modalContainer = document.createElement('div')
                                modalContainer.classList.add('modal-container')
                                modal.appendChild(modalContainer)
                                modal.addEventListener('click',e=>{
                                    modal.remove()
                                })
                                modalContainer.addEventListener('click',e=>e.stopPropagation())
                                modalContainer.appendChild(modalContent)
                                document.body.appendChild(modal)
                            }
                        }).finally(() => el.classList.remove(LOADING_CLASS_NAME))
                    })
                    fileButtons.appendChild(previewButton)

                    downloadButton.addEventListener('click', e => {
                        e.stopPropagation()
                        e.preventDefault()
                        el.classList.add(LOADING_CLASS_NAME)
                        fetch(`${UP_FILE_PATH}/${id}/${file.id}`)
                            .then(response => {
                                return response.blob()
                            }).then(blob => {
                            const link = document.createElement('a')
                            link.href = URL.createObjectURL(blob)
                            link.download = file.origin
                            link.click()
                        }).finally(() => el.classList.remove(LOADING_CLASS_NAME))
                    })
                    fileButtons.appendChild(downloadButton)

                    deleteButton.addEventListener('click', e => {
                        e.preventDefault()
                        e.stopPropagation()
                        el.classList.add(LOADING_CLASS_NAME)
                        fetch(`${UP_FILE_PATH}/${id}/${file.id}`, {
                            method: 'DELETE'
                        }).then(response => {
                            return response.json()
                        }).then(json => set(json))
                            .finally(() => el.classList.remove(LOADING_CLASS_NAME))
                    })
                    fileButtons.appendChild(deleteButton)

                    tag.appendChild(fileInfo)
                    tag.appendChild(fileButtons)

                    el.appendChild(tag)
                }
            }
        })

    })
})

