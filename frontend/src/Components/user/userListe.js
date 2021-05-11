import { RedirectUrl } from "../Router.js";
import { getUserSessionData } from "../../utils/session.js";
import callAPI from "../../utils/api.js";
import PrintError from "../PrintError.js";
const API_BASE_URL = "/api/users/";

const UserListPage = async () => {
  // deal with page title
  let page = document.querySelector("#page");
  // clear the page
  page.innerHTML = "";
  let title = document.createElement("h4");
  title.id = "pageTitle";
  title.innerText = "List of users";
  page.appendChild(title);

  const user = getUserSessionData();
  if(!user || user.user.role !== "admin"){
    RedirectUrl("/");
  } 

  try {
    const users = await callAPI(API_BASE_URL, "GET", user.token);
    onUserList(users);
  } catch (err) {
    console.error("UserListPage::onFilmList", err);
    PrintError(err);
  }
};

const onUserList = (data) => {
  if (!data) return;
  let table = `
  <div id="tableFilms" class="table-responsive mt-3">
  <table class="table">
      <thead>
          <tr>
              <th class="Nom">Nom</th>
              <th class="Prenom">Prénom</th>
              <th class="username">Pseudo</th>
              <th class="email">Email</th>
              <th class="date inscription">Date d'inscription</th>
              <th class="profile">Voir profil</th>
          </tr>
      </thead>
      <tbody>`;

  data.forEach((element) => {
    const str = element.registrationDate+'';
    const words = str.split(',');
    table += `<tr data-username="${element.username}">
                <td class="title" contenteditable="true">${element.firstName}</td>
                <td class="link" contenteditable="true">${element.lastName}</td>
                <td class="link" contenteditable="true">${element.username}</td>
                <td class="email" contenteditable="true">${element.email}</td>
                <td class="registrationDate" contenteditable="true">${words[2]}/${words[1]}/${words[0]}</td>
                <td class="see"><button class="btn btn-dark seeBtn">Voir</button></td>
            </tr>
            `;
  });

  table += `</tbody>
  </table>
  </div>`;
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





export default UserListPage;
