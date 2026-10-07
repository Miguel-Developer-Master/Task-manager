import {changePage} from "./utils/ChangePage.js";

const viewSection = document.querySelector("#view-section");
const saveButton = document.querySelector("#save-button");
const cancelButton = document.querySelector("#cancel-button");

saveButton.addEventListener("click", () => edit());
cancelButton.addEventListener("click", () => changePage(""));

async function view() {

    const titleDiv = document.createElement("div");
    titleDiv.id = "title";

    const descriptionDiv = document.createElement("div");
    descriptionDiv.id = "description";

    const priorityDiv = document.querySelector("#priority");

    const completedDiv = document.createElement("div");
    completedDiv.id = "completed";

    const params = new URLSearchParams(window.location.search);
    const taskID = Number(params.get("id"));

    const tasksList = await fetch("/tasks/apis/query-data");
    const tasks = await tasksList.json();

    for (const task of tasks) {

        if (task.id === taskID) {

            const titleParagraph = document.createElement("input");
            titleParagraph.classList.add("text-inputs");
            titleParagraph.value = task.title;
            viewSection.insertBefore(titleDiv, priorityDiv);
            titleDiv.append(titleParagraph);

            const descriptionParagraph = document.createElement("textarea");
            descriptionParagraph.id= "description-textarea";
            descriptionParagraph.classList.add("text-inputs");
            descriptionParagraph.value = task.description;
            viewSection.insertBefore(descriptionDiv, priorityDiv);
            descriptionDiv.append(descriptionParagraph);

            const options = document.querySelector("#priority-select");
            options.value = task.priority;

            const completedLabel = document.createElement("label");
            completedLabel.htmlFor = "Completed";
            completedLabel.textContent = "Completed:";
            const completedCheck = document.createElement("input");
            completedCheck.type = "checkbox";
            completedCheck.checked = task.completed === true;
            viewSection.insertBefore(completedDiv, saveButton);
            completedDiv.append(completedLabel);
            completedDiv.append(completedCheck);

        }
    }
}

async function edit() {


    const params = new URLSearchParams(window.location.search);
    const taskID = params.get("id");

    const title = document.querySelector("#title");
    const titleContent = (title.querySelector("input")).value;

    const description = document.querySelector("#description");
    const descriptionContent = (description.querySelector("textarea")).value;

    const priority = (document.querySelector("#priority-select")).value;

    const completed = document.querySelector("#completed");
    const completedCheck = (completed.querySelector("input")).checked;

    if ((!(titleContent.trim() === "") && !(priority.trim() === ""))
        &&
        (titleContent.length < 30 && titleContent.length > 0)
    ) {

        const response = await fetch(`/tasks/edit?id=${taskID}`, {
                method: "POST",
                headers: {"Content-Type": "application/json"},
                body: JSON.stringify({
                    title: titleContent,
                    description: descriptionContent,
                    priority: priority,
                    completed: completedCheck
                })
            }
        );

        changePage("");
    } else {
        window.location.reload();
        throw new Error("All camps are required");
    }
}

await view();