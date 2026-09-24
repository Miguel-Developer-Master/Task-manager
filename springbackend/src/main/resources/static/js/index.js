const createButton = document.querySelector("#create");

import {changePage} from "./utils/ChangePage.js"

createButton.addEventListener("click", () => changePage("creating"));