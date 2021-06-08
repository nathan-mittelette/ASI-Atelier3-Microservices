$(document).ready(function () {
  if (localStorage.getItem('auth') != null) {
    // Get current user
    const Http = new XMLHttpRequest();
    const url = 'http://localhost:5003/secured/rooms';

    Http.open("GET", url);

    Http.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    Http.setRequestHeader("Authorization", "Bearer " + localStorage.getItem('auth'));

    Http.onreadystatechange = (e) => {
      if (Http.readyState === XMLHttpRequest.DONE && Http.status === 200) {
        roomList = JSON.parse(Http.responseText);
        displayRooms();
        getMyCards();
      } else if (Http.readyState === XMLHttpRequest.DONE && Http.status >= 400) {
        alert('Error while getting rooms : ' + Http.responseText);
      }
    }

    Http.send();
  }

  document.getElementById("room-add-button").addEventListener("click", () => {
    addRoom();
  });
});

function displayRooms() {
  let template = document.querySelector("#row");

  let i = 0;
  for (const room of roomList) {
    let clone = document.importNode(template.content, true);

    newContent = clone.firstElementChild.innerHTML
      .replace(/{{room_id}}/g, room.id)
      .replace(/{{room_name}}/g, room.name)
      .replace(/{{room_userid}}/g, room.player1.id)
      .replace(/{{room_bet}}/g, room.bet);
    clone.firstElementChild.innerHTML = newContent;

    clone.firstElementChild.getElementsByClassName("join-button")[0].addEventListener("click", () => {
      joinRoom(room.id, i);
    });

    let roomContainer = document.querySelector("#tableContent");
    roomContainer.appendChild(clone);

    i++;
  }
}

function getMyCards() {
  const Http = new XMLHttpRequest();
  const url = 'http://localhost:5001/secured/cards/mine';

  Http.open("GET", url);

  Http.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
  Http.setRequestHeader("Authorization", "Bearer " + localStorage.getItem('auth'));

  Http.onreadystatechange = (e) => {
    if (Http.readyState === XMLHttpRequest.DONE && Http.status === 200) {
      cardList = JSON.parse(Http.responseText);
      console.log(cardList);

      const selectMyCards = document.getElementsByClassName("my-cards");
      for (const card of cardList) {
        for (const select of selectMyCards) {
          select.add(new Option(card.name, card.id));
        }
      }

    } else if (Http.readyState === XMLHttpRequest.DONE && Http.status >= 400) {
      alert('Erreur lors de la récupération des cartes' + Http.responseText);
    }
  }

  Http.send();
}

function addRoom() {
  if (localStorage.getItem('auth') != null) {
    // Get current user
    const Http = new XMLHttpRequest();
    const url = 'http://localhost:5003/secured/rooms'; //TODO: Change port for reverse proxy

    const bet = document.getElementById('form_room_bet').value;
    const name = document.getElementById('form_room_name').value;
    const selectedCard = document.querySelectorAll('#newRoomForm .my-cards')[0].value;

    console.log(bet, name, selectedCard);

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
      "cardId": selectedCard,
      "bet": bet,
      "name": name,
    }));
  }
}

function joinRoom(roomId, cpt) {
  if (localStorage.getItem('auth') != null) {
    // Get current user
    const Http = new XMLHttpRequest();
    const url = 'http://localhost:5003/secured/rooms';

    const card = document.querySelectorAll("#tableContent .my-cards")[cpt - 1].value;

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

    Http.send(JSON.stringify({
      "cardId": card,
      "roomId": roomId
    }));
  }
}