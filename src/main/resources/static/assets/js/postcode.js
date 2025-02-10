function getAddressInfo(zipCodeId, addressId,focusId) {
    const zipcodeElement = document.getElementById(zipCodeId) ?? null
    const addressElement = document.getElementById(addressId) ?? null
    const focusElement = document.getElementById(focusId) ?? null

    const modal = document.createElement('div')
    modal.classList.add('modal')
    const modalContainer = document.createElement('div')
    modalContainer.classList.add('modal-container')
    modal.appendChild(modalContainer)
    modal.addEventListener('click',e=>{
        modal.remove()
    })
    modalContainer.addEventListener('click',e=>{e.stopPropagation()})
    new daum.Postcode({
        theme: {
            bgColor: "#fff", //바탕 배경색
            searchBgColor: "#fff", //검색창 배경색
            contentBgColor: "#fff", //본문 배경색(검색결과,결과없음,첫화면,검색서제스트)
            pageBgColor: "#fff", //페이지 배경색
            textColor: "#2e455c", //기본 글자색
            queryTextColor: "#2e455c", //검색창 글자색
            postcodeTextColor: "#fff", //우편번호 글자색
            emphTextColor: "#2f5ee7", //강조 글자색
            outlineColor: "#fff" //테두리
        },
        oncomplete: (data) => {
            if (zipcodeElement) zipcodeElement.value = data.zonecode
            if (addressElement) addressElement.value = data.address
            if(focusElement)focusElement.focus()
            modal.remove()
        }, onresize: (size) => {
            // console.info('resize', size)
        }, onclose: (state) => {
            // console.info('close', state)
        }, onsearch: (data) => {
            // console.info('search', data)
        }
    }).embed(modalContainer, {
        q: '',
        autoClose: true
    })
    document.body.appendChild(modal)
}
