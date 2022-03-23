//function to see if we are already logged in
window.onload = async function(){
    let response = await fetch(`${domain}/session`);
    let responseBody = await response.json();

    if(responseBody.success){
        if (responseBody.data.manager == true){
            window.location = `${domain}/managerdashboard/?userId=${responseBody.data.id}`
        } else {
            window.location = `${domain}/employeedashboard?userId=${responseBody.data.id}`
        }
    }
}


//function to allow us to login
document.getElementById("login-form").addEventListener("submit", async function (event){
    //this is to stop the form from reloading 
    event.preventDefault();
    
    //retrieve input elements from the dom
    let usernameInputElem = document.getElementById("usernameInput");
    let passwordInputElem = document.getElementById("passwordInput");

    //get values from the input elements and put it into an object
    let user = {
        username: usernameInputElem.value,
        password: passwordInputElem.value
    }

    //send the http request
    let response = await fetch(`${domain}/login`, {
        method: "POST",
        body: JSON.stringify(user)
    })

    //retrieve the response body
    let responseBody = await response.json();


    //logic after response body
    if(responseBody.success == false){
        // gives feedback to user if login unsuccessful
        let messageElem = document.getElementById("message")
        messageElem.innerText = responseBody.message
    }else{

        // first check if employee or manager
        // redirect to manager dashboard if roleId = 2
        // redirect to employee dashboard if roleId = 1
        
        if (responseBody.data.manager == true){
            window.location = `${domain}/managerdashboard/?userId=${responseBody.data.id}`
        } else {
            
            window.location = `${domain}/employeedashboard?userId=${responseBody.data.id}`
        }

    }

})

//function to redirect to register page 
function goToRegister(){
    window.location = `${domain}/register/`
}

