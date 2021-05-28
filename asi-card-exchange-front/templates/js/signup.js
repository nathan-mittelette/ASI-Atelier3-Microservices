window.addEventListener('load', function() {
    document.getElementById('signup_form').addEventListener('submit', function(event) {
        console.log("signup_form");
        event.stopPropagation();
        signup();
    });
    document.getElementById('signup_button').addEventListener('click', function(event) {
        console.log("signup_button");

        event.stopPropagation();
    })
})

function signup() {
    const Http = new XMLHttpRequest();
    const url='http://localhost:5000/users/signup';

    const firstname = document.getElementById('firstname').value;
    const lastname = document.getElementById('lastname').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    const money = document.getElementById('money').value;


    Http.open("POST", url);

    Http.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

    Http.onreadystatechange = (e) => {
        if (Http.readyState === XMLHttpRequest.DONE && Http.status === 200) {
            document.location.href = "./login.html";
        } else if (Http.readyState === XMLHttpRequest.DONE && Http.status >= 400) {
            alert('Erreur lors de l\'inscription" : ' + Http.responseText);
        }
    }

    Http.send(JSON.stringify({
        "firstname": firstname,
        "lastname": lastname,
        "email": email,
        "password": password,
        "money": money
    }));
}

