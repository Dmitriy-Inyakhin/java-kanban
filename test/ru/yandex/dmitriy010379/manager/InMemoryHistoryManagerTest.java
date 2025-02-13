package ru.yandex.dmitriy010379.manager;

import org.junit.jupiter.api.Test;
import ru.yandex.dmitriy010379.task.Epic;
import ru.yandex.dmitriy010379.task.Node;
import ru.yandex.dmitriy010379.task.Task;
import ru.yandex.dmitriy010379.task.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {
    public  HistoryManager historyManager = Managers.getDefaultHistoryManager();      //переменная для хранения
    InMemoryTaskManager memoryManager = new InMemoryTaskManager(); // истории просмотров
    Task task1 = new Task("Task1", "Description Task1.", TaskStatus.NEW);
    Task task2 = new Task("Task1", "Description Task1.", TaskStatus.NEW);
    Epic epic1 = new Epic("Epic1", "Description Epic1.", TaskStatus.NEW);
    List list = new ArrayList<>();

    @Test
    void addTaskTest() {
        assertEquals(list, historyManager.getHistory());        //проверяем, что история эквивалентна пустому списку
        assertNotNull(historyManager.getHistory());             // проверяем, что история не пустая (список)
        memoryManager.createEpic(epic1);
        historyManager.addTask(task1);                          //добавляем в историю task1
        list.add(task1);                                        //добавляем в пустой лист task1
        assertEquals(list, historyManager.getHistory());        //сравниваем tack1 в списках
    }

    @Test
    void getHistoryTest() {
        list.clear();
        assertEquals(list, historyManager.getHistory());        //проверяем, что история эквивалентна пустому списку
        assertNotNull(historyManager.getHistory());             // проверяем, что история не пустая (список)
        memoryManager.createTask(task2);
        memoryManager.createTask(task1);
        memoryManager.createEpic(epic1);
        historyManager.addTask(task2);                          //добавляем события в историю
        historyManager.addTask(task1);
        historyManager.addTask(epic1);

        for (Task task : historyManager.getHistory()) {         //смотрим, что находится в истории
            list.add(task);
        }
        assertEquals(3, list.size());                   // проверяем что список истории состоит из 3 элементов

        list.clear();
        list.add(task2);
        list.add(task1);
        list.add(epic1);
        assertEquals(list, historyManager.getHistory());        //проверяем, что история правильно заполнена

    }

    @Test
    void removeTest() {
        list.clear();
        assertEquals(list, historyManager.getHistory());        //проверяем, что история эквивалентна пустому списку
        assertNotNull(historyManager.getHistory());             // проверяем, что история не пустая (список)
        memoryManager.createTask(task2);
        memoryManager.createTask(task1);
        memoryManager.createEpic(epic1);
        historyManager.addTask(task2);                          //добавляем события в историю
        historyManager.addTask(task1);
        historyManager.addTask(epic1);

        for (Task task : historyManager.getHistory()) {         //смотрим, что находится в истории
            list.add(task);
        }
        assertEquals(3, list.size());                   // проверяем что список истории состоит из 3 элементов

        historyManager.remove(task1.getId());                   //удаляем 1 элемент task1

        list.clear();
        for (Task task : historyManager.getHistory()) {         //смотрим, что находится в истории
            list.add(task);
        }
        assertEquals(2, list.size());                   // проверяем, что один элемент удален, список стал равным 2

        list.clear();
        list.add(task2);
        list.add(epic1);
        assertEquals(list, historyManager.getHistory());        //проверяем, что история после удалления правильно
        // заполнена
    }
}