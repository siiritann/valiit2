// alert(1);

// FETCH ALL ACCOUNTS
// fetch('http://62.65.38.242:63343/accountslist')
fetch('http://localhost:8080/accountslist')
    .then(result => result.json())
    .then(
        function (accountslist){
            for (let i = 0; i < accountslist.length; i++) {
                console.log(accountslist[i]);
            }
        }
    );

// FETCH ACCOUNTS FOR THIS CLIENT
fetch('http://localhost:8080/accountslist')