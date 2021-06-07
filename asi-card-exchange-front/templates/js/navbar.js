$(document).ready(function () {
    if (localStorage.getItem('auth') != null) {
        // Récupération de l'utilisateur courrant.
        const Http = new XMLHttpRequest();
        const url = 'http://localhost:5004/public/users/current';

        Http.open("GET", url);

        Http.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        Http.setRequestHeader("Authorization", "Bearer " + localStorage.getItem('auth'));

        Http.onreadystatechange = (e) => {
            if (Http.readyState === XMLHttpRequest.DONE && Http.status === 200) {
                // Récupération des données de l'utilisateur
                const user = JSON.parse(Http.responseText);

                const navbarContent = document.getElementById('navbar_content');
                navbarContent.innerHTML = navbarContent.innerHTML
                    .replace(/{{name}}/g, user.firstname)
                    .replace(/{{surname}}/g, user.lastname)
                    .replace(/{{currency}}/g, user.money);
            } else if (Http.readyState === XMLHttpRequest.DONE && Http.status >= 400) {
                localStorage.removeItem('auth');
                document.location.href = "./login.html";
            }
        }

        Http.send();
    } else {
        const navbarContent = document.getElementById('navbar_content');
        navbarContent.innerHTML = navbarContent.innerHTML
            .replace(/{{name}}/g, "Unknown")
            .replace(/{{surname}}/g, "Unknown")
            .replace(/{{currency}}/g, "0");
    }

})
;
