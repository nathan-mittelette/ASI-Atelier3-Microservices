// some arbitrary business-logic
window.user = {
    name       : 'Simon',
    adminLevel : 1
};

function check_username() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4){
            return ( this.status == 200);
        }

    };
    xhttp.open("GET", "https://jsonplaceholder.typicode.com/todos/12", true);
    xhttp.send();
}

// custom form validation rule
$.fn.form.settings.rules.check_server = function(check_server) {
    result = check_username();
    console.log(result);
    return (result)
};


$('.ui.form')
    .form({
        fields: {

            username: {
                identifier: 'username',
                rules: [
                    {
                        type: 'check_server',
                        prompt: 'Failed to reach server'
                    },
                    {
                        type   : 'empty',
                        prompt : 'Please enter a username'
                    }
                ]
            },
            password: {
                identifier: 'password',
                rules: [
                    {
                        type   : 'empty',
                        prompt : 'Please enter a password'
                    },
                    {
                        type   : 'minLength[4]',
                        prompt : 'Your password must be at least {ruleValue} characters'
                    }
                ]
            },
            password_confirm: {
                identifier: 'password_confirm',
                rules: [
                    {
                        type   : 'match[password]',
                        prompt : 'This field should match the password'
                    }
                ]
            }
        }
    })
;
