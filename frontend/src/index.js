import {Router} from "./Components/Router.js";
import Navbar from "./Components/Navbar.js";
/* use webpack style & css loader*/
import "./stylesheets/style.css";
/* load bootstrap css (web pack asset management) */
import 'bootstrap/dist/css/bootstrap.css';
/* load bootstrap module (JS) */
import 'bootstrap';
import PathMe from "./Components/PathMe.js";

const HEADER_TITLE = "JS meets JAX-RS";
const FOOTER_TEXT = "Happy learning : )";


PathMe();

Navbar();

Router();




// deal with header and footer
document.querySelector("#headerTitle").innerText = HEADER_TITLE;
document.querySelector("#footerText").innerText = FOOTER_TEXT;
