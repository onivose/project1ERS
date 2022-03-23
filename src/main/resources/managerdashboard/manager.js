window.onload = function(){

    const params = new Proxy(new URLSearchParams(window.location.search), {
        get: (searchParams, prop) => searchParams.get(prop),
    });

    
    userId = params.userId;

    let messageElem = document.getElementById("welcomeMessage")
    messageElem.innerText = `Welcome User Id: ${userId}`

    getAllforAll()

}

async function getAllforAll(){
        
}

async function filterByStatus(){

}

async function filterByType(){
    
}

async function changeStatus(event){
    
}

