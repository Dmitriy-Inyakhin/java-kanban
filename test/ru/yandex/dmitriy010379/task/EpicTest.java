package ru.yandex.dmitriy010379.task;

import org.junit.jupiter.api.Test;
import ru.yandex.dmitriy010379.manager.Managers;
import ru.yandex.dmitriy010379.manager.TaskManager;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EpicTest {
    TaskManager manager;

    @Test
    void getSubtaskId() {
        manager = Managers.getDefaultTaskManager();
        Epic epic1 = new Epic("Test addNewEpic1", "Test addNewEpic1 description.", TaskStatus.NEW);
        Epic epic1M = manager.createEpic(epic1);

        Subtask subtask1 = new Subtask("Test addNewSubtask1", "Test addNewSubtask1 description.", TaskStatus.NEW, epic1.getId());
        subtask1 = manager.createSubtask(subtask1);

        Subtask subtask2 = new Subtask("Test addNewSubtask2", "Test addNewSubtask2 description.", TaskStatus.NEW,
                epic1.getId());
        subtask2 = manager.createSubtask(subtask2);
        Integer[] list = new Integer[]{2,3};                        //Создаем образцовый массив значений Id субтасков
        List<Integer> listSubtask = epic1.getSubtaskId();           //получаем List субтасков
        Integer[] integerArray = new Integer[listSubtask.size()];   //создаем массив размером по количеству субтасков
        integerArray = listSubtask.toArray(integerArray);           //преобразовываем List субтасков в массив Id
        // субтасков

        assertArrayEquals(integerArray, list, "Номер субтаска не совпадает");

    }

    @Test
    void isEmptySubtasks() {
        manager = Managers.getDefaultTaskManager();
        Epic epic1 = new Epic("Test addNewEpic1", "Test addNewEpic1 description.", TaskStatus.NEW);
        Epic epic1M = manager.createEpic(epic1);
        List<Subtask> subtasks = manager.getAllSubtaskByEpic(epic1M);

        assertEquals(subtasks.size(), 0, "Список субтасков не пустой");
    }
}