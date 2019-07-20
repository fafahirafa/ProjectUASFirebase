package id.ac.pnj.projectuasfirebase;

public class ToDoList {
    String task_name, date, notes, id;

    public ToDoList(String task_name, String date, String notes, String id) {
        this.task_name = task_name;
        this.date = date;
        this.notes = notes;
        this.id = id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
