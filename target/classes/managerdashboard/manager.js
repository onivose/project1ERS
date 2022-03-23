let user;

window.onload =  async function(){

    let response = await fetch(`${domain}/session`);

    let responseBody = await response.json();

    if(!responseBody.success){ // if a session was not found redirect to login
        window.location = "../";
    }

    user = responseBody.data; 

    let messageElem = document.getElementById("welcomeMessage")
    messageElem.innerText = `Welcome ${user.firstname} ${user.lastname} ! Here are all the reimbursement requests:`

    getAllforAll()
}

async function getAllforAll(){
    let response = await fetch(`${domain}/reimb?userId=${user.id}`);

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

    timeSubmitted = new Date(reimb.timeSubmitted).toDateString()


   /*  <div class="info-card" id="infoCard">
            <div class="list-title">Reimbursement Id : 1</div>
            <div class="list-title">Author User Id : 1</div>
            <div class="list-title">Type: Food</div>
            <div class="list-title">Amount : $500</div>
            <div class="list-title">Time Submitted : 2022-03-13 17:23:52.769</div>
            <div class="list-title">Status: Pending</div>

            <form id="ESForm" class="editStatusForm" onsubmit="changeStatus(event)">
                <label for="NewStatus"><div id="editStatus">Edit Reimbursement Status :</div></label>

                <select id="changeStatus" name="NewStatus" required>
                    <option value=""> Choose...</option>
                    <option value="2">Approved</option>
                    <option value="3">Denied</option>
                    <option value="1">Pending</option>
                </select>
                
                <button id="ESFBtn" type="submit" class="btn btn-info">Change Status</button>
            </form>
        </div> */

    listCardElem.innerHTML = `
    <div id="infoCard">
            <div class="list-title">Reimbursement Id : ${reimb.reimbId}</div>
            <div class="list-title">Author User Id : ${reimb.authorId}</div>
            <div class="list-title">Author Username : ${reimb.authorUsername}</div>
            <div class="list-title">Type: ${reimb.type}</div>
            <div class="list-title">Amount : $${reimb.amount}</div>
            <div class="list-title">Time Submitted : ${timeSubmitted}</div>
            <div class="list-title">Status: ${statusString}</div>

            <form id="ESForm" class="editStatusForm" onsubmit="changeStatus(event)">
                <label for="NewStatus"><div id="editStatus">Edit Reimbursement Status :</div></label>

                <select id="changeStatus" name="NewStatus" required>
                    <option value=""> Choose...</option>
                    <option value="2">Approved</option>
                    <option value="3">Denied</option>
                    <option value="1">Pending</option>
                </select>
                
                <button id="ESFBtn" type="submit" class="btn btn-info">Change Status</button>
            </form>
        </div> `

    listContainerElem.appendChild(listCardElem);

}

async function filterByStatus(){

}

async function filterByType(){
    
}

async function changeStatus(event){
    event.preventDefault();
    console.log(reimb.id)    
}

//allows us to end our session and logout
async function logout(){
    let response = await fetch(`${domain}/session`, {
        method: "DELETE"
    });

    window.location = "../";

}

