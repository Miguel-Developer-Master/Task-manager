import {changePage} from "./utils/ChangePage.js";

const form = document.querySelector("#create-task-form");
const title = document.querySelector("#task-name");
const description = document.querySelector("#task-description");
const priority = document.querySelector(".priority");

export let json;

async function createTaskButton(event) {
    event.preventDefault();

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