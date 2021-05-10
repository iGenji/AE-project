import { RedirectUrl } from "./Router.js";
import Navbar from "./Navbar.js";
import { getUserSessionData,  setUserSessionData } from "../utils/session.js";
import callAPI from "../utils/api.js";
import PrintError from "./PrintError.js";
const API_BASE_URL = "/api/auths/";

let registerPage = `
<div class="container">
  <h4 id="pageTitle">Register</h4> 
  </br> 
  <form>      
    <div class="form-group"> 
      <label for="username">Username</label>
      <input class="form-control" id="username" type="text" name="username" placeholder="Enter your username" required="" />
    </div>

    <div class="form-group">
      <label for="firstname">Firstname</label>
      <input class="form-control" id="firstname" type="text" name="firstname" placeholder="Enter your firstname" required="" />
    </div>

    <div class="form-group">
      <label for="lastname">Lastname</label>
      <input class="form-control" id="lastname" type="text" name="lastname" placeholder="Enter your lastname" required="" />
    </div>

    <div class="form-group">
      <label for="email">Email</label>
      <input class="form-control" id="email" type="text" name="email" placeholder="Enter your email" required="" pattern="^\\w+([.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,4})+\$" />
    </div>

    <div class="form-group">
      <div class="row">
      
        <div class="col-6">
          <label for="street">Street</label>
          <input class="form-control" id="street" type="text" name="street" placeholder="Enter your street" required="" />
        </div>
      
        <div class="col-3">
          <label for="building_number">Building number</label>
          <input class="form-control" id="building_number" type="text" name="building_number" placeholder="Enter your building number" required="" />
        </div>
      
        <div class="col-3">
          <label for="unit_number">Unit number</label>
          <input class="form-control" id="unit_number" type="text" name="unit_number" placeholder="Enter your unit number"  />   
        </div>
      </div>
    </div>

    <div class="form-group">
      <div class="row">
        <div class="col-3">
          <label for="postcode">Postcode</label>
          <input class="form-control" id="postcode" type="number" name="postcode" placeholder="Enter your postcode" min="0" required="" />
        </div>  

        <div class="col-5">
          <label for="commune">Commune</label>
          <input class="form-control" id="commune" type="text" name="commune" placeholder="Enter your commune" required="" />
        </div>
        <div class="col-4">
          <label for="country">Country</label>
          <input class="form-control" id="country" type="text" name="country" placeholder="Enter your country" required="" />
        </div>
      </div>
    </div>

    <div class="form-group">
      <label for="password">Password</label>
      <input class="form-control" id="password" type="password" name="password" placeholder="Enter your password" required="" />
    </div>  

    <button class="btn btn-primary" id="btn" type="submit">Submit</button>
    <!-- Create an alert component with bootstrap that is not displayed by default-->
    <div class="alert alert-danger mt-2 d-none" id="messageBoard"></div><span id="errorMessage"></span>
  </form> 
  <br/>
  <br/>
  <br/>
  <br/>
</div>`;

const RegisterPage = () => {
  let page = document.querySelector("#page");
  page.innerHTML = registerPage;
  let registerForm = document.querySelector("form");
  const user = getUserSessionData();
  if (user) {
    // re-render the navbar for the authenticated user
    Navbar();
    RedirectUrl("/");
  } else registerForm.addEventListener("submit", onRegister);
};

const onRegister = async (e) => {
  e.preventDefault();

  let user = {
    username: document.getElementById("username").value,
    firstName: document.getElementById("firstname").value,
    lastName: document.getElementById("lastname").value,
    email: document.getElementById("email").value,
    password: document.getElementById("password").value,
    addressObject : {
      street: document.getElementById("street").value,
      buildingNumber: document.getElementById("building_number").value,
      unitNumber: document.getElementById("unit_number").value,
      postCode: document.getElementById("postcode").value,
      commune: document.getElementById("commune").value,
      country: document.getElementById("country").value,  
    }
  };
 
  try {
    const userRegistered = await callAPI(
      API_BASE_URL + "register",
      "POST",
      undefined,
      user
    );
    RedirectUrl("/");
  } catch (err) {
    console.error("RegisterPage::onRegister", err);
    PrintError(err);
  }
};

export default RegisterPage;
