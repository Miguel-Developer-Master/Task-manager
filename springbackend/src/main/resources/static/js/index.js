const createButton = document.querySelector("#create");
const section = document.querySelector("#create-view-task");


import {changePage} from "./utils/ChangePage.js"

createButton.addEventListener("click", () => changePage("creating"));

async function showTasks() {
    console.log("showTasks started");
    const tasksList = await fetch("/tasks/apis/query-data");
    const tasks = await tasksList.json();


    for (const task of tasks) {

        const newDiv = document.createElement("div");
        newDiv.classList.add("new-tasks");

        const newParagraph = document.createElement("p");
        newParagraph.classList.add("task-title");
        newParagraph.classList.add("modifying");
        newParagraph.textContent = task.title;

        const newImage = document.createElement("img");
        newImage.src = "/images/Change.webp";
        newImage.alt = "Modify-icon";


        const newChangeButton = document.createElement("button");
        newChangeButton.classList.add("modifyButton");
        newChangeButton.classList.add("modify");
        newChangeButton.classList.add("modifying");
        newChangeButton.innerHTML = `<svg width="32" height="32" viewBox="0 0 32 32">
                                    <!-- Pencil -->
                                    <path
                                        d="M8 24L9.5 19L21 7.5L24.5 11L13 22.5L8 24Z"
                                        fill="none"
                                        stroke="black"
                                        stroke-width="2"
                                        stroke-linejoin="round"
                                    />
                                
                                    <!-- Pencil tip -->
                                    <path
                                        d="M8 24L9.5 19L13 22.5L8 24Z"
                                        fill="black"
                                    />
                                
                                    <!-- Pencil top -->
                                    <line
                                        x1="19.5"
                                        y1="9"
                                        x2="23"
                                        y2="12.5"
                                        stroke="black"
                                        stroke-width="2"
                                    />
                                </svg>`;

        const newDeleteButton = document.createElement("button");
        newDeleteButton.classList.add("delete");
        newDeleteButton.classList.add("modify");
        newDeleteButton.classList.add("modifying");
        newDeleteButton.innerHTML = `<svg width="32" height="32" viewBox="0 0 32 32">
                                    <!-- Trash can -->
                                    <rect x="9" y="11" width="14" height="16"
                                          fill="none"
                                          stroke="black"
                                          stroke-width="2"
                                          rx="1"/>
                                
                                    <!-- Lid -->
                                    <line x1="7" y1="9" x2="25" y2="9"
                                          stroke="black"
                                          stroke-width="2"/>
                                
                                    <!-- Handle -->
                                    <line x1="13" y1="6" x2="19" y2="6"
                                          stroke="black"
                                          stroke-width="2"/>
                                
                                    <!-- Vertical lines -->
                                    <line x1="13" y1="14" x2="13" y2="23"
                                          stroke="black"
                                          stroke-width="2"/>
                                
                                    <line x1="19" y1="14" x2="19" y2="23"
                                          stroke="black"
                                          stroke-width="2"/>
                                    </svg>`;


        newDiv.append(newParagraph);
        newDiv.append(newChangeButton);
        newDiv.append(newDeleteButton);
        section.append(newDiv);
    }

}
