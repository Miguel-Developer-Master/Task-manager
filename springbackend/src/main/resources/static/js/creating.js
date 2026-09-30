import {changePage} from "./utils/ChangePage.js";

console.log ("Js loaded");
const form = document.querySelector("#create-task-form");
const title = document.querySelector("#task-name");
const description = document.querySelector("#task-description");
const priority = document.querySelector(".priority");

export let json;

async function createTaskButton(event) {
    console.log("function started");
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
    console.log("Writted");

    const lastIDFile = await fetch("/tasks/last-id");
    const lastID = await lastIDFile.text();
    console.log("Getting last ID");
    const lastIDInteger = Number(lastID);

    const jsonFile = await fetch(`/tasks/query-task/${lastIDInteger}`);
    json = await jsonFile.json();
    console.log("Querying the task");
}

form.addEventListener("submit", createTaskButton);