import { getUserSessionData, setUserSessionData } from "../utils/session.js";
import callAPI from "../utils/api.js";
import PrintError from "./PrintError.js";
import { RedirectUrl } from "./Router.js";
const API_BASE_URL = "/api/users/";
import Navbar from "./Navbar.js";

const PathMe = () => {

  const user = JSON.parse(window.localStorage.getItem("user"));
  if(user){
      onPathMe(user);
  }
    
};

const onPathMe = async (user) => {
    try {
        const userLogged = await callAPI(
          API_BASE_URL + "me",
          "GET",
          user.token
        );
        user.user = userLogged;
        onUserLogin(user);
      } catch (err) {
        console.error("LoginPage::onLogin", err);
        PrintError(err);
      }
}

const onUserLogin = (userData) => {
  console.log("onUserLogin:", userData);
  const user = { ...userData, isAutenticated: true };
  setUserSessionData(user);
  window.localStorage.setItem("user", JSON.stringify(user));
  // re-render the navbar for the authenticated user
  Navbar();
};

export default PathMe;
