package ru.yandex.dmitriy010379.manager; //

import ru.yandex.dmitriy010379.task.Task;

import java.util.List;

public interface HistoryManager {

    void addTask(Task task);

    List<Task> getHistory();

}
