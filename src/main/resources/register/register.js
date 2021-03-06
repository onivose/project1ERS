
async function registerUser(event){
    //this is to stop the form from reloading 
    event.preventDefault();

    console.log("form submitted");
    
    //retrieve input elements from the dom
    let usernameInputElem = document.getElementById("inputUsername");
    let passwordInputElem = document.getElementById("inputPassword");
    let firstnameInputElem = document.getElementById("inputFirstName");
    let lastnameInputElem = document.getElementById("inputLastName");
    let emailInputElem = document.getElementById("inputEmail");
    let roleInputElem = document.getElementById("inputRole");

    //get values from the input elements and put it into an object
    let user = {
        username: usernameInputElem.value,
        password: passwordInputElem.value,
        firstname: firstnameInputElem.value,
        lastname: lastnameInputElem.value,
        email: emailInputElem.value,
        roleId: roleInputElem.value
    }
    console.log(user)

    //send the http request
    let response = await fetch(`${domain}/register`, {
        method: "POST",
        body: JSON.stringify(user)
    })

    //retrieve the response body
    let responseBody = await response.json();


    //logic after response body
    if(responseBody.success == false){
        let messageElem = document.getElementById("message")
        messageElem.innerText = responseBody.message
    }else{
        //redirect page to login page if credentials were successful
        window.location = `../`
    }


}