/* OTI LAHENDUS
*
* */

console.log("Seotud");

let postButton = document.getElementById("createClient");
let firstName = document.getElementById("firstName");
let lastName = document.getElementById("lastName");
let checkbox = document.getElementById("checkbox");


// POOLELI
/*postButton.onclick = function (){
    if (checkbox == true) {

    }
}*/

postButton.addEventListener("click", function () {
    console.log("click");
    let client = {
        firstName: firstName.value,
        lastName: lastName.value
    }
    createClient(client).then(result => {

    });
});

function createClient(client) {

// Send the JavaScript company object to the server...
    return fetch('/clients',
        {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(client)
        }
    );
}

