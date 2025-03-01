// Базовый класс для задачи
package task;

import java.util.Objects;

public class Task {

    protected int id;                                       // Уникальный идентификатор задачи
    protected String name;                                  // Название задачи
    protected String description;                           // Описание задачи
    protected TaskStatus status;                            // Статус задачи

    public Task(String name, String description, TaskStatus status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    private void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                "{name='" + name +
                ", id=" + id + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}' + "\n";
    }

}