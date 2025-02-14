

window.addEventListener('DOMContentLoaded', () => {
    Quill.register('modules/imageResize', QuillResizeImage);
    document.querySelectorAll('form').forEach(form=>{
        form.querySelectorAll('.editor').forEach(el => {

            const initContent =el.textContent
            el.textContent=''
            const {id}=el.dataset
            const input = document.createElement('input')
            input.type='hidden'
            input.name=id
            form.appendChild(input)

            const quill = new Quill(el, {
                modules: {
                    toolbar: toolbarOptions,
                    imageResize:{},
                },
                theme: 'snow'
            })
            quill.root.innerHTML=initContent
            form.addEventListener('submit',e=>{
                input.value=quill.root.innerHTML;

            })
        })
    })
})
const toolbarOptions = [
    ['bold', 'italic', 'underline', 'strike'],        // toggled buttons
    ['blockquote', 'code-block'],
    ['link', 'image', 'video', 'formula'],
    [{ 'header': 1 }, { 'header': 2 }],               // custom button values
    [{ 'list': 'ordered'}, { 'list': 'bullet' }, { 'list': 'check' }],
    [{ 'script': 'sub'}, { 'script': 'super' }],      // superscript/subscript
    [{ 'indent': '-1'}, { 'indent': '+1' }],          // outdent/indent
    [{ 'direction': 'rtl' }],                         // text direction
    [{ 'size': ['small', false, 'large', 'huge'] }],  // custom dropdown
    [{ 'header': [1, 2, 3, 4, 5, 6, false] }],
    [{ 'color': [] }, { 'background': [] }],          // dropdown with defaults from theme
    [{ 'font': [] }],
    [{ 'align': [] }],
    ['table'],
    ['html'],
    ['clean']                                         // remove formatting button
];