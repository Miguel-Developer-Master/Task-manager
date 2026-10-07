import {changePage} from "./ChangePage.js";

export async function showDeletePopUp(id) {
    const deleteTask = prompt("Are you sure you want to delete this task? Type: \"Delete\" if you have sure");
    if (deleteTask.trim().toLowerCase() === "delete") {
        const response = await fetch(`/tasks/delete/${id}`, {
            method: "DELETE"
        });
    }

    changePage("");
}