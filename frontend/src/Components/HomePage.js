import { getUserSessionData} from "../utils/session.js";
import { RedirectUrl } from "./Router.js";
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
      onDisplayFurnitureCostumer(furniture);
    } catch (err) {
      console.error("FurniturePageCustomer::onFurniturePageCustomer", err);
      PrintError(err);
    }
};

const onDisplayFurnitureCostumer = (furniture) => {
  console.log("onDisplayFurnitureCostumer:", furniture);
    // show the furniture's page
    page.innerHTML = 
    `
    <div class="container-fluid-width row" style="background-color: grey;">
      <a align="left" class="border col-md-4" href="http://localhost/"><button>Retour</button></a>
      <div align="center" class="border col-md-4">Nom: ${furniture.idFurniture}</div>
      <a align="right" class="border col-md-4"><button>Voir mes options (non demandé)</button></a>
    </div>

    <div class="container-fluid-width" style="background-color: black;">
      <div id="demo" class="carousel slide" data-ride="carousel">
        <!-- Indicateurs -->
        <ul class="carousel-indicators">
          <li data-target="#demo" data-slide-to="0" class="active"></li>
          <li data-target="#demo" data-slide-to="1"></li>
          <li data-target="#demo" data-slide-to="2"></li>
        </ul>

        <!-- Carrousel -->
        <div class="carousel-inner" >
          <div class="carousel-item active">
            <img src="https://www.w3schools.com/images/w3schools_green.jpg" alt="Carrousel slide 1" class="rounded mx-auto d-block w-25" >
          </div>
          <div class="carousel-item">
            <img src="https://www.w3schools.com/images/w3schools_green.jpg" alt="Carrousel slide 2" class="rounded mx-auto d-block w-25" >
          </div>
          <div class="carousel-item">
            <img src="https://www.w3schools.com/images/w3schools_green.jpg" alt="Carrousel slide 3" class="rounded mx-auto d-block w-25" >
          </div>
        </div>

        <!-- Contrôles -->
        <a class="carousel-control-prev" href="#demo" role="button" data-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="sr-only">Précédent</span>
        </a>
        <a class="carousel-control-next" href="#demo" role="button" data-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="sr-only">Suivant</span>
        </a>
      </div>
    </div> 

    <div class="container-fluid-width row" style="background-color: grey;">
      <div align="left" class="border title col-md-3">Prix: <br/>${furniture.sellingPrice}€</div>
      <div align="center" class="border col-md-6">Description: <br/>${furniture.description}</div>
      <a align="right" class="border col-md-3"><button>Introduire une option ou Annuler une option (non demandé)</button></a>
    </div>
    `;
;

};

export default HomePage;



