import { getUserSessionData, setUserSessionData } from "../utils/session.js";
import { RedirectUrl } from "./Router.js";
import Navbar from "./Navbar.js";
import callAPI from "../utils/api.js";
import PrintError from "./PrintError.js";
const API_BASE_URL = "/api/";


const HomePage = async () => {
  // deal with page title
  let page = document.querySelector("#page");
  // clear the page
  page.innerHTML = "";
  let title = document.createElement("h4");
  title.id = "pageTitle";
  title.innerText = "Home";
  page.appendChild(title);

  const user = getUserSessionData();

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
    <div class="box">
      <div class="slide-img">
        <img src="https://www.w3schools.com/images/w3schools_green.jpg" alt="" />
        <div class="overlay">
          <a href="#" class="buy-btn">Buy Now</a>
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

  page.innerHTML +=
    '<button id="addBtn" class="btn btn-primary mt-2">Add film</button>';

  const seeBtns = document.querySelectorAll(".seeBtn");
  

  seeBtns.forEach((seeBtn) => {
    seeBtn.addEventListener("click", onSeen);
  });

};

const onSeen = async (e) => {
 
  const username = e.target.parentElement.parentElement.dataset.username;
  console.log(username);
  window.location.href = "/user?name="+username;
};


 
export default HomePage;



