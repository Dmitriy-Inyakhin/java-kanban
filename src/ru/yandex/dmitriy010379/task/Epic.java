// Класс для эпиков

package ru.yandex.dmitriy010379.task;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    public List<Integer> subtaskId = new ArrayList<>(); // Список подзадач

    public Epic(String name, String description, TaskStatus status) {
        super(name, description, status);
    }

    public List<Integer> getSubtaskId() {
        return subtaskId;
    }

    public boolean isEmptySubtasks() {
        return subtaskId.isEmpty();
    }


    @Override
    public String toString() {
        return "Epic {" +
                 "name='" + name +
                ", id=" + id +'\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", subtaskId" + subtaskId +
                '}' +"\n";
    }

}