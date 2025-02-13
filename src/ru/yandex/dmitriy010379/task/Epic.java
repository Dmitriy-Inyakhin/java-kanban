// Класс для эпиков

package ru.yandex.dmitriy010379.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Epic extends Task {
    public List<Integer> subtaskId = new ArrayList<>(); // Список подзадач

    public Epic(String name, String description, TaskStatus status) {
        super(name, description, status);
    }

    public List<Integer> getSubtaskId() {           //получаем субтаск по Id
        return subtaskId;
    }

    public boolean isEmptySubtasks() {              //проверяем есть ли у эпика субтаски
        return subtaskId.isEmpty();
    }


    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                "{name='" + name +
                ", id=" + id + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", subtaskId" + subtaskId +
                '}' + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Epic epic = (Epic) o;
        return Objects.equals(subtaskId, epic.subtaskId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), subtaskId);
    }
}