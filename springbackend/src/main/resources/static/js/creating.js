import {changePage} from "./utils/ChangePage.js";

const form = document.querySelector("#create-task-form");
const title = document.querySelector("#task-name");
const description = document.querySelector("#task-description");
const priority = document.querySelector(".priority");

export let json;

async function createTaskButton(event) {
    event.preventDefault();

    if ((title.value.length > 30 || title.value.length < 1) || description.value.length > 75) {
        changePage("");
        throw new Error("Title must be at maximum 15 characters long and description 75, and both need to be longer than 0!");
    }

    await fetch("/tasks/write", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                title: title.value,
                description: description.value,
                priority: priority.value,
                completed: false
            })
    });


    const lastIDFile = await fetch("/tasks/last-id");
    const lastID = await lastIDFile.text();
    const lastIDInteger = Number(lastID);

    const jsonFile = await fetch(`/tasks/query-task/${lastIDInteger}`);
    json = await jsonFile.json();

    changePage("");
}

form.addEventListener("submit", createTaskButton);