window.onload = function(){

    const params = new Proxy(new URLSearchParams(window.location.search), {
        get: (searchParams, prop) => searchParams.get(prop),
    });

    userId = params.userId;

    let messageElem = document.getElementById("welcomeMessage")
    messageElem.innerText = `Welcome User Id: ${userId}`

    //getAllReimbursementsForUser()
}

async function getAllReimbursementsForUser(){

    //let response = await fetch(`${domain}/list?userId=${userId}`);
    let response = await fetch(`${domain}/reimb`, {
        method: "GET",
        // get user from session ???
        body: JSON.stringify(user)
    });

    let responseBody = await response.json();

    console.log(responseBody)

    let reimbursements = responseBody.data;


    reimbursements.forEach(reimb => {
        createReimbInfoCard(reimb)
    });


}

function createReimbInfoCard(reimb){
    let statusString = "";

    switch (reimb.statusId){
        case 1:
            statusString = "Pending"
            break
        case 2:
            statusString = "Approved"
            break
        case 3:
            statusString = "Denied"
            break
    }

    let listContainerElem = document.getElementById("list-container");

    let listCardElem = document.createElement("div");
    listCardElem.className = "info-card"

    listCardElem.innerHTML = `
    <div id="infoCard">

        <div class="list-title">Reimbursement Id : ${reimb.reimbId}</div>
        <div class="list-title">Type: ${reimb.type}</div>
        <div class="list-title">Amount : $${reimb.amount}</div>
        <div class="list-title">Time Submitted : ${reimb.timeSubmitted}</div>
        <div class="list-title">Status: ${statusString}</div>

    </div>`

    listContainerElem.appendChild(listCardElem);

}

async function createNewReimb(event){ //WORKS!
    event.preventDefault();

    let newReimbAmountInputElem = document.getElementById("inputAmount");
    let newReimbTypeInputElem = document.getElementById("inputType");

    let reimbToCreate = 
    {   amount : newReimbAmountInputElem.value, 
        authorId : userId,
        typeId : newReimbTypeInputElem.value
    }

    let response = await fetch(`${domain}/reimb/new`,{
        method: "POST",
        body: JSON.stringify(reimbToCreate)
    })

    let responseBody = await response.json();

    let messageElem = document.getElementById("message")
        messageElem.innerText = responseBody.message
    
    console.log(responseBody)

    

    createReimbInfoCard(responseBody.data);

}


// for the pop up "Create New Reimbersement" form 
function openForm() {
    document.getElementById("new-reimb").style.display = "block";
}
  
function closeForm() {
    let messageElem = document.getElementById("message")
        messageElem.innerText = ""
    document.getElementById("new-reimb").style.display = "none";

}
