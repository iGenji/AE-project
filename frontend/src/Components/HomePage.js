import { getUserSessionData, setUserSessionData } from "../utils/session.js";
import { RedirectUrl } from "./Router.js";
import Navbar from "./Navbar.js";
import callAPI from "../utils/api.js";
import PrintError from "./PrintError.js";
const API_BASE_URL = "/api/";
const API_BASE_URL_FURNITURE = "/api/furnitures/";


const HomePage = async () => {
  // deal with page title
  let page = document.querySelector("#page");
  // clear the page
  page.innerHTML = "";
  let title = document.createElement("h4");
  title.id = "pageTitle";
  title.innerText = "Home";
  page.appendChild(title);

  //const user = getUserSessionData();
  //if (user) {
  //  loginForm.addEventListener("submit", onLogin)
 // };

  try {
    const furnitures = await callAPI(API_BASE_URL+"furnitureList", "POST");
    console.log(furnitures);
    onFurnituresList(furnitures);
  } catch (err) {
    console.error("UserListPage::onFilmList", err);
    PrintError(err);
  }
};

const onFurnituresList = (data) => {
  if (!data) return;
  let table = `<body>
  <ul>`;

  data.forEach((element) => {
    var vendu="Disponible";
    if(element.stateFurniture==="achete"){
      vendu="Vendu!";
    }
    const str = element.registrationDate+'';
    const words = str.split(',');
    table += `<li class="item-a">
    <div class="box" data-id="${element.idFurniture}">
      <div class="slide-img">
        <img src="https://www.w3schools.com/images/w3schools_green.jpg" alt="" />
        <div class="overlay">
          <button href="#" class="buyBtn">Buy Now</button>
        </div>
      </div>
      <div class="detail-box">
        <div class="type">
          <a href="#">${element.typeString}</a>
          <span>${vendu}</span>
        </div>
        <a href="#" class="price">${element.sellingPrice}$</a>
      </div>
    </div>
    ${element.description}
  </li> `;
  });

  table += `</ul>
  </body>`;
  page.innerHTML += table;

  const buyBtns = document.querySelectorAll(".buyBtn");
  

  buyBtns.forEach((buyBtn) => {
    buyBtn.addEventListener("click", onBuy);
  });

};

const onBuy = async (e) => {
 
  const idFurniture = e.target.parentElement.parentElement.parentElement.dataset.id;
  //window.location.href = "http://localhost/furnitureCustomer?furnitureId="+idFurniture;
  const user = getUserSessionData();
  if(!user){
    RedirectUrl("/login");
    //+ajouter message disant que la personne doit être connecté
  }else{
      onFurniturePageCustomer(user, idFurniture);
  }
  
};

const onFurniturePageCustomer = async (user, id) =>{
  try {
      console.log(id);
      const furniture = await callAPI(
        API_BASE_URL_FURNITURE + id,
        "GET",
        user.token,

      );
      //user.user = userLogged;
      onDisplayFurnitureCostumer(furniture);
    } catch (err) {
      console.error("FurniturePageCustomer::onFurniturePageCustomer", err);
      PrintError(err);
    }
};

const onDisplayFurnitureCostumer = (furniture) => {
  console.log("onDisplayFurnitureCostumer:", furniture);
  
};

export default HomePage;



