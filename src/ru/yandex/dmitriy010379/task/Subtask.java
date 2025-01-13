package ru.yandex.dmitriy010379.task;

// Класс для подзадачи
public class Subtask extends Task {
    private final int epicId; // Идентификатор эпика, к которому относится подзадача

    public Subtask(String name, String description, TaskStatus status, int epicId) {
        super(name, description, status);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }


    @Override
    public String toString() {
        return "Subtask {" +
               "name='" + name +
                ", id=" + id +
                ", epicId=" + epicId +'\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}' + "\n";

    }

}


