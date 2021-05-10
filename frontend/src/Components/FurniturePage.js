import { getUserSessionData, setUserSessionData } from "../utils/session.js";
import { RedirectUrl } from "./Router.js";
import Navbar from "./Navbar.js";
import callAPI from "../utils/api.js";
import PrintError from "./PrintError.js";
const API_BASE_URL = "/api/furnitures/";

let furniturePage = `<div class="container">
</br>

      <div class="col-md-7 col-lg-8">

      <form id="purchase" class="needs-validation" novalidate>

          <hr class="my-4">

          <h4 class="mb-3">Purchase of furniture</h4>

          <div class="row gy-3">

            <div class="col-md-4">
              <label for="furniturePurchasedID" class="form-label">Furniture purchased ID</label>
              <input type="number" min="0" class="form-control" id="furniturePurchasedID" placeholder="1" required>
              <div class="invalid-feedback">
                Please enter the id of the furniture purchased.
              </div>
            </div>

            <div class="col-md-4">
              <label for="furniturePurchasedPrice" class="form-label">Purchase price</label>
              <input type="number" min="0" class="form-control" id="purchasePrice" placeholder="100" required>
              <div class="invalid-feedback">
                Please enter the price of the furniture purchased.
              </div>
            </div>

            <div class="col-md-4">
              <label for="cc-name" class="form-label">Collection date</label>
              <input type="date" class="form-control" id="collectionDate" placeholder="" required>
              <div class="invalid-feedback">
                Date of collection is required.
              </div>
            </div>
          </div>
          <br>

          <button class="w-100 btn btn-primary btn-lg" type="submit">Confirm purchase</button>
        </form>
        <hr class="my-4">


        <form id="workshop" class="needs-validation" novalidate>

          <hr class="my-4">

          <h4 class="mb-3">Send to the workshop</h4>

          <div class="row gy-3">

            <div class="col-md-4">
              <label for="furnitureSentToWorkshop" class="form-label">Furniture ID</label>
              <input type="number" min="0" class="form-control" id="furnitureSentID" placeholder="1" required>
              <div class="invalid-feedback">
                Please enter the id of the furniture sent to the workshop.
              </div>
            </div>
          </div>
          <br>

          <button class="w-100 btn btn-primary btn-lg" type="submit">Confirm sending</button>
        </form>
        <hr class="my-4">

        <form id="deposit" class="needs-validation" novalidate>

          <hr class="my-4">

          <h4 class="mb-3">Deposit</h4>

          <div class="row gy-3">

            <div class="col-md-4">
              <label for="furnitureDepositID" class="form-label">Furniture ID</label>
              <input type="number" min="0" class="form-control" id="furnitureDepositID" placeholder="1" required>
              <div class="invalid-feedback">
                Please enter the id of the furniture purchased.
              </div>
            </div>

            <div class="col-md-4">
              <label for="cc-name" class="form-label">Deposit date</label>
              <input type="date" class="form-control" id="depositDate" placeholder="" required>
              <div class="invalid-feedback">
                Deposit date is required.
              </div>
            </div>
          </div>
          <br>

          <button class="w-100 btn btn-primary btn-lg" type="submit">Confirm deposit</button>
        </form>
        <hr class="my-4">

        <h4 class="mb-3">Furniture price</h4>
        <form id="selling" class="needs-validation" novalidate>
          <div class="row g-3">

            <div class="col-md-4">
              <label for="furnitureID" class="form-label">Furniture ID</label>
              <input type="number" min="0" class="form-control" id="furnitureID" placeholder="1" required>
              <div class="invalid-feedback">
                Please enter the id of the furniture.
              </div>
            </div>  

            <div class="col-md-4">
              <label for="price" class="form-label">Selling price (€)</label>
              <input type="number" min="0.01" step="0.01" class="form-control" id="price" placeholder="200" required>
              <div class="invalid-feedback">
                Please enter the price for this furniture.
              </div>
            </div>

            <div class="col-md-4">
              <label for="specialPrice" class="form-label">Special selling price (€)</label>
              <input type="number" min="0.01" step="0.01" class="form-control" id="specialPrice" placeholder="150" required>
              <div class="invalid-feedback">
                Please enter the special price for this furniture.
              </div>
            </div>

          </div>
          </br>
          <button class="w-100 btn btn-primary btn-lg" type="submit">Confirm prices</button>
        </form> 

        <form id="sold" class="needs-validation" novalidate>

          <hr class="my-4">

          <h4 class="mb-3">Furniture sold</h4>

          <div class="row gy-3">

            <div class="col-md-4">
              <label for="furnitureSoldID" class="form-label">Furniture sold ID</label>
              <input type="number" min="0" class="form-control" id="furnitureSoldID" placeholder="1" required>
              <div class="invalid-feedback">
                Please enter the id of the furniture sold.
              </div>
            </div>

            <div class="col-md-4">
              <label for="furnitureSoldID" class="form-label">Customer ID</label>
              <input type="number" min="-1" class="form-control" id="customerID" placeholder="-1" required>
              <div class="invalid-feedback">
                Please enter the id of the customer or -1 if the customer is not registered.
              </div>
            </div>

            <div class="col-md-4">
              <label for="cc-name" class="form-label">Date of sale</label>
              <input type="date" class="form-control" id="soldDate" placeholder="" required>
              <div class="invalid-feedback">
                Date of sale is required
              </div>
            </div>
          </div>
          <br>

          <button class="w-100 btn btn-primary btn-lg" type="submit">Confirm selling</button>
        </form> 
      </br>
      </br>
      </br>
      </br>
</div>`;

const FurniturePage = () =>{
  let page = document.querySelector("#page");
  page.innerHTML= furniturePage;
  const user = getUserSessionData();
  if(!user){
    RedirectUrl("/login");
  }
  let purchaseForm = document.querySelector('#purchase');
  purchaseForm.addEventListener("submit",onPurchaseSubmit);
  let workshopForm = document.querySelector('#workshop');
  workshopForm.addEventListener("submit",onWorkshopSubmit);
  let depositForm = document.querySelector("#deposit");
  depositForm.addEventListener("submit",onDepositSubmit);
  let sellingForm = document.querySelector('#selling');
  sellingForm.addEventListener("submit", onSellingSubmit);
  let soldForm = document.querySelector('#sold');
  soldForm.addEventListener("submit",onSoldSubmit);
};

const onPurchaseSubmit = async (e) =>{
  e.preventDefault();
  let furniture={
    idFurniture: document.getElementById("furniturePurchasedID").value,
    purchasePrice: document.getElementById("purchasePrice").value,
    furnitureCollectionDateBoss: document.getElementById("collectionDate").value
  }
  console.log(furniture);
  try{
    const furniturePurchasedSubmitted = await callAPI(
      API_BASE_URL + "purchasedSubmitted",
      "POST",
      undefined,
      furniture
    );
    onPurchaseSubmitted(furniturePurchasedSubmitted);
  } catch (err) {
    console.error("FurniturePage::onPurchaseSubmit", err);
    PrintError(err);
  }
}

const onPurchaseSubmitted = (furnitureData) =>{
  console.log("FurniturePage::onPurchaseSubmitted",furnitureData);
  RedirectUrl("/");
}

const onWorkshopSubmit = async (e) =>{
  e.preventDefault();
  let furniture={
    idFurniture: document.getElementById("furnitureSentID").value,
  }
  try{
    const furnitureWorkshop = await callAPI(
      API_BASE_URL + "toWorkshop",
      "POST",
      undefined,
      furniture
    );
    onWorkshopSubmitted(furnitureWorkshop);
  }catch (err){
    console.error("FurniturePage::onWorkshopSubmit",err);
    PrintError(err);
  }
}

const onWorkshopSubmitted = (furnitureData) =>{
  console.log("FurniturePage::OnWorkshopSubmitted",furnitureData);
  RedirectUrl("/");
}

const onDepositSubmit = async (e) =>{
  e.preventDefault();
  let furniture={
    idFurniture: document.getElementById("furnitureDepositID").value,
    depositDate: document.getElementById("depositDate").value
  }
  console.log(furniture);
  try{
    const furnitureDepositSubmitted = await callAPI(
      API_BASE_URL + "deposit",
      "POST",
      undefined,
      furniture
    );
    onDepositSubmitted(furnitureDepositSubmitted);
  } catch (err) {
    console.error("FurniturePage::onDepositSubmit", err);
    PrintError(err);
  }
}

const onDepositSubmitted = (furnitureData) =>{
  console.log("FurniturePage::onDepositSubmitted",furnitureData);
  RedirectUrl("/");
}

const onSellingSubmit = async (e) =>{
  e.preventDefault();
  let furniture= {
    id_meuble: document.getElementById("furnitureID").value,
    prix_vente: document.getElementById("price").value,
    prix_special: document.getElementById("specialPrice").value
  };
  try {
    const priceSubmitted = await callAPI(
      API_BASE_URL + "priceSubmitted",
      "POST",
      undefined,
      furniture
    );
    onPriceSubmitted(priceSubmitted);
  } catch (err) {
    console.error("FurniturePage::onSellingSubmit", err);
    PrintError(err);
  }
};

const onPriceSubmitted = (furnitureData) =>{
  console.log("FurniturePage::onPriceSubmitted",furnitureData);
  RedirectUrl("/");
}

const onSoldSubmit = async (e) =>{
  e.preventDefault();
  let furniture={
    idFurniture: document.getElementById("furnitureSoldID").value,
    idUser: document.getElementById("customerID").value,
    soldDate: document.getElementById("soldDate").value
  }
  console.log(furniture);
  try{
    const saleSoldSubmitted = await callAPI(
      "/api/sales/soldSubmitted",
      "POST",
      undefined,
      furniture
    )
    console.log(saleSoldSubmitted)
    const furnitureSoldSubmitted = await callAPI(
      API_BASE_URL + "soldSubmitted",
      "POST",
      undefined,
      furniture
    );
    onSoldSubmitted(saleSoldSubmitted,furnitureSoldSubmitted);
  } catch (err) {
    console.error("FurniturePage::onSoldSubmit", err);
    PrintError(err);
  }
}
const onSoldSubmitted = (saleData,furnitureData) =>{
  console.log("FurniturePage::onSoldSubmitted",saleData);
  console.log("FurniturePage::onSoldSubmitted",furnitureData);
  RedirectUrl("/");
}

export default FurniturePage;