window.onload = function(){

    const params = new Proxy(new URLSearchParams(window.location.search), {
        get: (searchParams, prop) => searchParams.get(prop),
    });

    
    userId = params.userId;

    let messageElem = document.getElementById("welcomeMessage")
    messageElem.innerText = `Welcome User Id: ${userId}`


    async function getAllforAll()
}

async function filterByStatus(){

}

async function filterByType(){
    
}


function openForm() {
    let infoCard = document.getElementById("infoCard")
    infoCard.style.display = "flex";
    infoCard.style.flexDirection;
    infoCard.style.justify-content;
}
  
function closeForm() {
    document.getElementById("infoCard").style.display = "none";
}