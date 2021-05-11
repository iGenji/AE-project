import { RedirectUrl } from "../Router.js";
import { getUserSessionData } from "../../utils/session.js";
import callAPI from "../../utils/api.js";
import PrintError from "../PrintError.js";
const API_BASE_URL = "/api/users/";
console.log(location.search.split('=')[1]);
var pseudo = location.search.split('=')[1];
const UserPage = async () => {

  // deal with page title
  let page = document.querySelector("#page");
  // clear the page
  page.innerHTML = "";
  let title = document.createElement("h4");
  title.id = "pageTitle";
  title.innerText = "Customer information";
  page.appendChild(title);
  var idAdress;
  var adresse;
  const user = getUserSessionData();
  /* if(user.user.role!=="admin"){
    RedirectUrl("/error");
  } */

  try {
    const users = await callAPI(API_BASE_URL+pseudo, "GET", user.token);
    idAdress = users.address;
    console.log(idAdress);
    onUserList(users);
  } catch (err) {
    console.error("UserPage::user", err);
    //PrintError(err);
    RedirectUrl("/error");
  }

  
};

const onUserList = (data) => {
  if (!data) return;
  console.log(data);
  var str=data.registrationDate+'';
  const words = str.split(',');
  let html = `
  <p>pseudo: ${data.username} </p>
  <p>nom: ${data.firstName} </p>
  <p>prenom: ${data.lastName} </p>
  <p>email: ${data.email} </p>
  <p>date inscription: ${words[2]}/${words[1]}/${words[0]} </p>
  <p>role: ${data.role} </p>
  <h4><u>Adresse:</u></h4>
  <p>${data.addressObject.street}, n° ${data.addressObject.buildingNumber}</p>
  <p>${data.addressObject.postcode} ${data.addressObject.commune}, ${data.addressObject.country}</p>
  `;
  page.innerHTML += html;

  

};



export default UserPage;
