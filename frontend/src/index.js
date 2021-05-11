import {Router} from "./Components/Router.js";
import Navbar from "./Components/Navbar.js";
/* use webpack style & css loader*/
import "./stylesheets/style.css";
/* load bootstrap css (web pack asset management) */
import 'bootstrap/dist/css/bootstrap.css';
/* load bootstrap module (JS) */
import 'bootstrap';
import PathMe from "./Components/PathMe.js";

const HEADER_TITLE = "Li Vi Satcho";
const FOOTER_TEXT = "Adresse: Ibis sente des artistes  Verviers";
const HEADER = 'https://hevinci-my.sharepoint.com/personal/celestin_sivixay_student_vinci_be/Documents/images/logoAE_v2.png';


PathMe();

Navbar();

Router();




// deal with header and footer
document.querySelector("#headerTitle").innerText = HEADER_TITLE;
document.querySelector("#footerText").innerText = FOOTER_TEXT;
document.getElementById("imgHeader").src = HEADER;