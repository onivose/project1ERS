window.onload = function(){

    const params = new Proxy(new URLSearchParams(window.location.search), {
        get: (searchParams, prop) => searchParams.get(prop),
    });

    
    userId = params.userId;

    let messageElem = document.getElementById("welcomeMessage")
    messageElem.innerText = `Welcome User Id: ${userId}`
}

function openForm() {
    document.getElementById("new-reimb").style.display = "block";
}
  
function closeForm() {
    document.getElementById("new-reimb").style.display = "none";
}

function viewReimbInfo() {

}