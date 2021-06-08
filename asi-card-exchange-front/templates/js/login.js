window.addEventListener('load', function () {
  document.getElementById('login_form').addEventListener('submit', function (event) {
    event.stopPropagation();
    login();
  });

  document.getElementById('login_button').addEventListener('click', function (event) {
    event.stopPropagation();
  });

  document.getElementById('cancel-button').addEventListener('click', event => {
    document.location.href = "home.html";
  });
})

function login() {
  const Http = new XMLHttpRequest();
  const url = 'http://localhost:5004/public/users/login';

  const login = document.getElementById('username').value;
  const password = document.getElementById('password').value;

  Http.open("POST", url);

  Http.setRequestHeader("Content-Type", "application/json");

  Http.onreadystatechange = (e) => {
    if (Http.readyState === XMLHttpRequest.DONE && Http.status === 200) {
      localStorage.setItem('auth', Http.responseText);
      document.location.href = "./home.html";
    } else if (Http.readyState === XMLHttpRequest.DONE && Http.status >= 400) {
      alert('Erreur lors du login : ' + Http.responseText);
    }
  }

  Http.send(JSON.stringify({ "login": login, "password": password }));
}
