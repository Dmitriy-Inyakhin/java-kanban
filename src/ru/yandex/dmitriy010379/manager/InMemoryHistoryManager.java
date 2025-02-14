package ru.yandex.dmitriy010379.manager; //

import ru.yandex.dmitriy010379.task.Task;
import ru.yandex.dmitriy010379.task.TaskStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InMemoryHistoryManager implements HistoryManager{

    private final List<Task> historyStorage = new ArrayList<>();
    private final int MAXSIZETASK = 10;

    @Override
    public void addTask(Task task) {
        if(Objects.isNull(task)) {
            return;
        }
        historyStorage.add(task.getSnapShot());
        if (historyStorage.size() > MAXSIZETASK) {
            historyStorage.removeFirst();
        }
    }

    @Override
    public List<Task> getHistory() {
        return historyStorage;
    }
}
