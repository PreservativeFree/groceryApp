const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];

const groceryContainer = document.getElementById("grocery-container")

const headers = {
    'Content-Type': 'application/json'
}

const baseUrl = 'http://localhost:8080/produce/'

function handleLogout() {
    let c = document.cookie.split(";")
    for(let i in c) {
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}	

async function getProduce() { //get all Notes sort of
    await fetch(`${baseUrl}all`, {
        method:"GET",
        headers: headers
    }).then(response => response.json())
      .then(data => createGroceryCards(data))
      .catch(err => console.error(err))
}

// async function getProduceById(produceId) { //getNoteById
//     await fetch(`${baseUrl}users/${produceId}`, {
//         method:"GET",
//         headers: headers
//     }).then(response => response.json())
//       .then(data => createGroceryCards(data)) //function call
//       .catch(err => console.error(err))
// }

const createGroceryCards = (array) => {
    groceryContainer.innerHTML = ''
    array.forEach(obj => {
        let groceryCard = document.createElement("div")
        groceryCard.classList.add("image")
        groceryCard.innerHTML = `    
        <div class="container">
        	<div class="image">
            	<img src="${obj.imageUrl}">
            	<h3>${obj.name}</h3>
            	<h3>${obj.description}</h3>
            	<h3>$${obj.price}</h3>
            	<a class="add-cart cart1" href="#">Add to Cart</a>
        	</div>
        </div>
        `
        groceryContainer.append(groceryCard)
	})
}

getProduce().then(value => console.log("Handler 1 Nelson"))
