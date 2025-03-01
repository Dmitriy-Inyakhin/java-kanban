// Класс для подзадачи
package task;

import java.util.Objects;

public class Subtask extends Task {
    private final int epicId; // Идентификатор эпика, к которому относится подзадача

    public Subtask(String name, String description, TaskStatus status, int epicId) {
        super(name, description, status);
        this.epicId = epicId;
    }

    public int getEpicId() {        //возвращаем ID эпика, в который входит субтаск
        return epicId;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                "{name='" + name +
                ", id=" + id +
                ", epicId=" + epicId + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}' + "\n";

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Subtask subtask = (Subtask) o;
        return epicId == subtask.epicId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), epicId);
    }
}


