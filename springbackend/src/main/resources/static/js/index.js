const createButton = document.querySelector("#create");
const section = document.querySelector("#create-view-task");


import {changePage} from "./utils/ChangePage.js"

createButton.addEventListener("click", () => changePage("creating"));

async function showTasks() {
    const tasksList = await fetch("/tasks/apis/query-data");
    const tasks = await tasksList.json();

    const response = await fetch("/tasks/apis/query-tasks-info");
    const tasksNumber = (await response.text()).split(",");

    const firstBracket = tasksNumber[0];
    const tasksCounter = tasksNumber[1];
    const lastBracket = tasksNumber[2];
    let count = 0;

    let title = "";
    let description = "";
    let priority = "";
    let completed = false;

    for (let i = 0; i < tasksCounter; i++) {
        let shouldContinue = true;
        if (count === Number(lastBracket)) {
            break;
        }

        for (let a = count; a < tasksCounter; a++) {
            const line = tasks.get(a);

            if (a < firstBracket || a > lastBracket) {
                continue;
            }

            if (line.contains("title")) {
                const titleMessage = line.split("\"");
                title = titleMessage[4];
            } else if (line.contains("description")) {
                description = line.split("\"");
                description = description[4];
            } else if (line.contains("priority")) {
                priority = line.split("\"");
                priority = priority[4];
            } else if (line.contains("completed")) {
                completed = line.split(": ");
                completed = completed[1];
                shouldContinue = false;
            }


            if (shouldContinue === false) {
                count += 2;
                break;
            }
        } //end of inner for

        const newDiv = document.createElement("div");
        newDiv.classList.add(".new-tasks");

        const newParagraph = document.createElement("p");
        newParagraph.classList.add("task-title");
        newParagraph.classList.add("modifying");
        newParagraph.textContent = title;

        const newImage = document.createElement("img");
        newImage.src = "/images/Change.webp";
        newParagraph.alt = "Modify-icon";

        const newButton = document.createElement("button");
        button.classList.add("modify");
        button.classList.add("modifying");
        newButton.append(newImage);

        newDiv.append(newParagraph);
        newDiv.append(newButton);
        section.append(newDiv);

    } //end of outer for

}
