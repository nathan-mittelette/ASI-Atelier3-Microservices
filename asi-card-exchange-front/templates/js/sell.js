let cardList = [];

$(document).ready(function () {
  const template = document.querySelector("#row");
  console.log("Récupération des cartes");

  if (localStorage.getItem('auth') != null) {
    // Récupération de l'utilisateur courrant.
    const Http = new XMLHttpRequest();
    const url = 'http://localhost:5001/secured/cards/mine';

    Http.open("GET", url);

    Http.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    Http.setRequestHeader("Authorization", "Bearer " + localStorage.getItem('auth'));

    Http.onreadystatechange = (e) => {
      if (Http.readyState === XMLHttpRequest.DONE && Http.status === 200) {
        // Récupération des données de l'utilisateur
        cardList = JSON.parse(Http.responseText);

        displayCards();
      } else if (Http.readyState === XMLHttpRequest.DONE && Http.status >= 400) {
        alert('Erreur lors de la récupération des cartes' + Http.responseText);
      }
    }

    Http.send();
  }
});


function displayCards() {
  const template = document.getElementById("row");

  for (const card of cardList) {
    let clone = document.importNode(template.content, true);

    newContent = clone.firstElementChild.innerHTML
      .replace(/{{card_id}}/g, card.id)
      .replace(/{{family_src}}/g, card.family)
      .replace(/{{family_name}}/g, card.family)
      .replace(/{{img_src}}/g, card.imageUrl)
      .replace(/{{name}}/g, card.name)
      .replace(/{{description}}/g, card.description)
      .replace(/{{hp}}/g, card.hp)
      .replace(/{{energy}}/g, card.energy)
      .replace(/{{attack}}/g, card.attack)
      .replace(/{{defense}}/g, card.defense)
      .replace(/{{price}}/g, card.price)
      .replace(/{{available}}/g, card.available);
    clone.firstElementChild.innerHTML = newContent;

    clone.firstElementChild.addEventListener("click", () => {
      switchToCard(card.id);
    });

    clone.firstElementChild.getElementsByClassName("sell-button").item(0).addEventListener("click", () => {
      sellCard(card.id);
    });

    let cardContainer = document.querySelector("#tableContent");
    cardContainer.appendChild(clone);
  }

  if (cardList.length > 0) {
    switchToCard(cardList[0].id);
  }
}

function sellCard(cardId) {
  if (localStorage.getItem('auth') != null) {
    // Récupération de l'utilisateur courrant.
    const Http = new XMLHttpRequest();
    const url = 'http://localhost:5002/secured/market/sell/' + cardId;

    Http.open("PUT", url);

    Http.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    Http.setRequestHeader("Authorization", "Bearer " + localStorage.getItem('auth'));

    Http.onreadystatechange = (e) => {
      if (Http.readyState === XMLHttpRequest.DONE && Http.status === 200) {
        document.location.href = "./sell.html";
      } else if (Http.readyState === XMLHttpRequest.DONE && Http.status >= 400) {
        alert("Erreur lors de la vente de la carte : " + Http.responseText);
      }
    }

    Http.send();
  }
}


function switchToCard(cardId) {
  const card = cardList.find((el) => el.id === cardId);
  console.log(card);
  const template = document.getElementById("card-template");
  const clone = document.importNode(template.content, true);
  const newContent = clone.firstElementChild.innerHTML
    .replace(/{{card_id}}/g, card.id)
    .replace(/{{family_src}}/g, card.family)
    .replace(/{{family_name}}/g, card.family)
    .replace(/{{img_src}}/g, card.imageUrl)
    .replace(/{{name}}/g, card.name)
    .replace(/{{description}}/g, card.description)
    .replace(/{{hp}}/g, card.hp)
    .replace(/{{energy}}/g, card.energy)
    .replace(/{{attack}}/g, card.attack)
    .replace(/{{defense}}/g, card.defense)
    .replace(/{{price}}/g, card.price)
    .replace(/{{available}}/g, card.available);
  clone.firstElementChild.innerHTML = newContent;

  const cardContainer = document.getElementById("card");

  while (cardContainer.firstChild) {
    cardContainer.removeChild(cardContainer.firstChild);
  }

  cardContainer.appendChild(clone);
}
