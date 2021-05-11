import { getUserSessionData} from "../utils/session.js";
import { RedirectUrl } from "./Router.js";
import callAPI from "../utils/api.js";
import PrintError from "./PrintError.js";
const API_BASE_URL = "/api/";
const API_BASE_PHOTO = "/api/photo/";


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
        <img src="${element.favouritePhotoString}" alt="" />
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
  const user = getUserSessionData();
  if(!user){
    RedirectUrl("/login");
  }else{
      onFurniturePageCustomer(user, idFurniture);
  }
  
};

const onFurniturePageCustomer = async (user, id) =>{
  try {
      console.log(id);
      const furniture = await callAPI(
        API_BASE_URL + id,
        "GET",
        user.token,

      );
      onDisplayFurnitureCostumer(furniture);
    } catch (err) {
      console.error("FurniturePageCustomer::onFurniturePageCustomer", err);
      PrintError(err);
    }
};

const onDisplayFurnitureCostumer = async (furniture) => {
  console.log("onDisplayFurnitureCostumer:", furniture);
    // show the furniture's page
    
        try {
          const id = furniture.idFurniture;
          console.log("test", id);
          const photos = await callAPI(API_BASE_PHOTO + id, "GET");
          console.log(photos);
          onPhotoList(photos, furniture);
        } catch (err) {
          console.error("PhotoListPage::onPhotoList", err);
          PrintError(err);
        }
      };
      
      const onPhotoList = (data, furniture) => {
        if (!data) return;
       
        let table = `<body>
        
    <div class="container-fluid-width row" style="background-color: grey;">
      <a align="left" class="border col-md-4" href="http://localhost/"><button>Retour</button></a>
      <div align="center" class="border col-md-4">Type: ${furniture.typeString}</div>
      <a align="right" class="border col-md-4"><button>Voir mes options (non demandé)</button></a>
    </div>

    <div class="container-fluid-width" style="background-color: black;">
      <div id="demo" class="carousel slide" data-ride="carousel">
      <div class="carousel-inner" >
      
    `;
      
        data.forEach((element) => {
          //si element n'est pas autorisé à être vu, alors on passe l'affichage de cette photo
          if(element.isVisible ==="false"){
            return;
          }
          if(element.idPhoto == furniture.favouritePhoto){
            table += `
        <!-- Carrousel -->
          <div class="carousel-item active">
            <img src="${element.photo}" alt="" class="rounded mx-auto d-block w-25" >
          </div>
        `;
          }else{
            table += `
            <!-- Carrousel -->
              <div class="carousel-item">
                <img src="${element.photo}" alt="" class="rounded mx-auto d-block w-25" >
              </div>
            `;
          }      
      });
        table += `
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
    
    
    </body>
    `;
    page.innerHTML = table;  
  };

export default HomePage;



