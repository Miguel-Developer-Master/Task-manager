const form = document.querySelector("#create-task-form");
const title = document.querySelector("#task-name");
const description = document.querySelector("#task-description");
const priority = document.querySelector(".priority");

export let json;

async function createTaskButton(event) {
    event.preventDefault();

    const response = await fetch("/write", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                title: title.value,
                description: description.value,
                priority: priority.value,
                completed: false
            })
    });

    const lastIDfile = await fetch("/tasks/last-id");
    const lastID = await lastIDfile.text();

    const jsonFile = await fetch(`/tasks/query-task/${lastID}`);
    json = await jsonFile.json();

    return json;
}

export { createTaskButton };

form.addEventListener("submit", createTaskButton);