window.addEventListener('load', function () {
  document.getElementById('signup_form').addEventListener('submit', function (event) {
    event.stopPropagation();
    signup();
  });

  document.getElementById('signup_button').addEventListener('click', function (event) {
    event.stopPropagation();
  });

  document.getElementById('cancel-button').addEventListener('click', function (event) {
    document.location.href = "home.html";
  });
})

function signup() {
  const Http = new XMLHttpRequest();
  const url = 'http://localhost:5004/public/users/signup';

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
