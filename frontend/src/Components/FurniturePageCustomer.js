/*
import { getUserSessionData, setUserSessionData } from "../utils/session.js";
import { RedirectUrl } from "./Router.js";
import Navbar from "./Navbar.js";
import callAPI from "../utils/api.js";
import PrintError from "./PrintError.js";
const API_BASE_URL = "/api/furnitures/";

const FurniturePageCustomer = () =>{
    let page = document.querySelector("#page");
    //page.innerHTML= furniturePage;  //page encore a faire 
    const user = getUserSessionData();
    if(!user){
      RedirectUrl("/login");
      //+ajouter message disant que la personne doit être connecté
    }else{
        onFurniturePageCustomer(user);
    }
    
};

const onFurniturePageCustomer = async (user) =>{
    try {
        //const test = $_GET['furnitureId'];
        let params = new URLSearchParams(location.search);
        const id = params.get('idFurniture');
        console.log(id);
        const furniture = await callAPI(
          API_BASE_URL + id,
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

export default FurniturePageCustomer;
*/