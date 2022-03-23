let user;

window.onload =  async function(){

    let response = await fetch(`${domain}/session`);

    let responseBody = await response.json();

    if(!responseBody.success){ // if a session was not found redirect to login
        window.location = "../";
    }

    user = responseBody.data; 

    let messageElem = document.getElementById("welcomeMessage")
    messageElem.innerText = `Welcome ${user.firstname} ${user.lastname} !`

    //getAllforAll()
}

async function getAllforAll(){
        
}

async function filterByStatus(){

}

async function filterByType(){
    
}

async function changeStatus(event){
    event.preventDefault();    
}

async function logout(){
    let response = await fetch(`${domain}/session`, {
        method: "DELETE"
    });

    window.location = "../";

}

