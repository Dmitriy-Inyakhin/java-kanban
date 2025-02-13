package ru.yandex.dmitriy010379.manager;

import org.junit.jupiter.api.Test;
import ru.yandex.dmitriy010379.task.Epic;
import ru.yandex.dmitriy010379.task.Subtask;
import ru.yandex.dmitriy010379.task.Task;
import ru.yandex.dmitriy010379.task.TaskStatus;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {
    InMemoryTaskManager memoryManager = new InMemoryTaskManager();
    Task task1 = new Task("Task1", "Description Task1.", TaskStatus.NEW);
    Epic epic1 = new Epic("Epic1", "Description Epic1.", TaskStatus.NEW);


    int taskId = 0;
    int epicId = 0;
    int subtaskId = 0;

    @Test
    void createTask() {
        memoryManager.createTask(task1);
        taskId = task1.getId();

        assertTrue(task1 instanceof Task);
    }

    @Test
    void createEpic() {
        memoryManager.createEpic(epic1);
        epicId = epic1.getId();

        assertTrue(epic1 instanceof Epic);
    }

    @Test
    void createSubtask() {
        Subtask subtask1 = new Subtask("Subtask1", "Description Subtask1.", TaskStatus.NEW, epic1.getId());
        memoryManager.createEpic(epic1);
        memoryManager.createSubtask(subtask1);
        subtaskId = subtask1.getId();

        assertTrue(subtask1 instanceof Subtask);
    }

    @Test
    void getTaskById() {
        memoryManager.createTask(task1);
        taskId = task1.getId();
        Task task = memoryManager.getTaskById(taskId);
        taskId = task1.getId();
        assertEquals(task, task1);
    }

    @Test
    void getEpicById() {
        memoryManager.createEpic(epic1);
        epicId = epic1.getId();
        Epic epic = memoryManager.getEpicById(epicId);
        assertEquals(epic, epic1);
    }

    @Test
    void getSubtaskById() {
        memoryManager.createEpic(epic1);
        Subtask subtask1 = new Subtask("Subtask1", "Description Subtask1.", TaskStatus.NEW, epic1.getId());
        memoryManager.createSubtask(subtask1);
        subtaskId = subtask1.getId();
        Subtask subtask = memoryManager.getSubtaskById(subtaskId);
        assertEquals(subtask, subtask1);

    }
}