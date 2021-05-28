// For test purpose only
/*
let roomList = [
    {
      id: 0,
      description: "Super game room",
      user: 0,
      bet: 50,
    },
    {
      id: 1,
      description: "Low ELO game room",
      user: 1,
      bet: 10,
    },
    {
      id: 2,
      description: "No bet",
      user: 2,
      bet: 0,
    },
  ];

  displayRooms()
*/

$(document).ready(function () {
  console.log("Getting rooms");

  if (localStorage.getItem('auth') != null) {
    // Get current user
    const Http = new XMLHttpRequest();
    const url = 'http://localhost:5003/secure/rooms/available';

    Http.open("GET", url);

    Http.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    Http.setRequestHeader("Authorization", "Bearer " + localStorage.getItem('auth'));

    Http.onreadystatechange = (e) => {
      if (Http.readyState === XMLHttpRequest.DONE && Http.status === 200) {
        // Récupération des données de l'utilisateur
        roomList = JSON.parse(Http.responseText);

        displayRooms();
      } else if (Http.readyState === XMLHttpRequest.DONE && Http.status >= 400) {
        alert('Error while getting rooms : ' + Http.responseText);
      }
    }

    Http.send();
  }
});

function displayRooms() {

  let template = document.querySelector("#row");

  // Loading all room from roomList
  for (const room of roomList) {
    let clone = document.importNode(template.content, true);

    newContent = clone.firstElementChild.innerHTML
      .replace(/{{room_id}}/g, room.id)
      .replace(/{{room_description}}/g, room.description)
      .replace(/{{room_user}}/g, room.user)
      .replace(/{{room_bet}}/g, room.bet);
    clone.firstElementChild.innerHTML = newContent;

    clone.firstElementChild.addEventListener("click", () => {
      joinRoom(room.id);
    });

    let roomContainer = document.querySelector("#tableContent");
    roomContainer.appendChild(clone);
  }
}

// Add listener for add room button
document.getElementById("room-add-button").addEventListener("click", () => {
  addRoom();
});

// Create a room by sending the bet and the description
function addRoom() {
  console.log('Create room')
  if (localStorage.getItem('auth') != null) {
    // Get current user
    const Http = new XMLHttpRequest();
    const url = 'http://localhost:5003/secure/rooms/add/'; //TODO: Change port for reverse proxy

    const bet = document.getElementById('form_room_bet').value;
    const description = document.getElementById('form_room_description').value;


    Http.open("POST", url);

    Http.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    Http.setRequestHeader("Authorization", "Bearer " + localStorage.getItem('auth'));

    Http.onreadystatechange = (e) => {
      if (Http.readyState === XMLHttpRequest.DONE && Http.status === 200) {
        document.location.href = "./play.html";
      } else if (Http.readyState === XMLHttpRequest.DONE && Http.status >= 400) {
        alert("Error while trying to create a new room : " + Http.responseText);
      }
    }

    Http.send(JSON.stringify({
      "bet": bet,
      "description": description,
    }));
  }
}

// Join an existing room by sending the roomId
function joinRoom(roomId) {
  console.log('Join room')
  if (localStorage.getItem('auth') != null) {
    // Get current user
    const Http = new XMLHttpRequest();
    const url = 'http://localhost:5003/secure/rooms/join/' + roomId; //TODO: Change port for reverse proxy

    Http.open("PUT", url);

    Http.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    Http.setRequestHeader("Authorization", "Bearer " + localStorage.getItem('auth'));

    Http.onreadystatechange = (e) => {
      if (Http.readyState === XMLHttpRequest.DONE && Http.status === 200) {
        document.location.href = "./play.html";
      } else if (Http.readyState === XMLHttpRequest.DONE && Http.status >= 400) {
        alert("Error while trying to create a new room : " + Http.responseText);
      }
    }

    Http.send();
  }
}