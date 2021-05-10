/* In a template literal, the ` (backtick), \ (backslash), and $ (dollar sign) characters should be 
escaped using the escape character \ if they are to be included in their template value. 
By default, all escape sequences in a template literal are ignored.*/
import { getUserSessionData, setUserSessionData} from "../utils/session.js";
import { RedirectUrl } from "./Router.js";
import Navbar from "./Navbar.js";
import callAPI from "../utils/api.js";
import PrintError from "./PrintError.js";
const API_BASE_URL = "/api/auths/";

let loginPage = `<div class="container">
<h4 id="pageTitle">Login</h4>
</br>
<form>
<div class="form-group">
  <label for="username">Username</label>
  <input class="form-control" id="username" type="text" placeholder="Enter your username" required="" />
</div>
<div class="form-group">
  <label for="password">Password</label>
  <input class="form-control" id="password" type="password" name="password" placeholder="Enter your password" required=""  />
</div>
</br>
<div class="form-inline">
  <label class="form-check-label" for="rememberMe">Remember Me</label>
  <input class="form-check-input" id="rememberMe" type="checkbox" name="rememberMe" />
</div>
</br>
<button class="btn btn-primary" id="btn" type="submit">S'identifier</button>
<!-- Create an alert component with bootstrap that is not displayed by default-->
<div class="alert alert-danger mt-2 d-none" id="messageBoard"></div>
</form>
</div>`;

const LoginPage = () => {  
  let page = document.querySelector("#page");
  page.innerHTML = loginPage;
  let loginForm = document.querySelector("form");
  const user = getUserSessionData();
  if (user) {
    // re-render the navbar for the authenticated user
    Navbar();
    RedirectUrl("/");
  } else loginForm.addEventListener("submit", onLogin);
};

const onLogin = async (e) => {
  e.preventDefault();
  let username = document.getElementById("username");
  let password = document.getElementById("password");
  let rememberMe;
  if(document.getElementById("rememberMe").checked){
    rememberMe = true;
  }else{
    rememberMe = false;
  }
  
  let user = {
    username: username.value,
    password: password.value,
    rememberMe: rememberMe,
  };
  try {
    const userLogged = await callAPI(
      API_BASE_URL + "login",
      "POST",
      undefined,
      user  
    );
    onUserLogin(userLogged);
  } catch (err) {
    console.error("LoginPage::onLogin", err);
    PrintError(err);
  }
};

const onUserLogin = (userData) => {
  console.log("onUserLogin:", userData);
  const user = { ...userData, isAutenticated: true };
  setUserSessionData(user);
  if(user.checked == "true"){
    window.localStorage.setItem("user", JSON.stringify(user));
  }

  // re-render the navbar for the authenticated user
  Navbar();
  RedirectUrl("/");
};

export default LoginPage;
