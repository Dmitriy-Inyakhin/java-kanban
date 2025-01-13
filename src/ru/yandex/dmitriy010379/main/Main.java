package ru.yandex.dmitriy010379.main;
import ru.yandex.dmitriy010379.task.*;
import ru.yandex.dmitriy010379.manager.*;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * *");
        System.out.println("Создаем две задачи, а также эпик с двумя подзадачами и эпик с одной подзадачей.");

        //Создайте две задачи, а также эпик с двумя подзадачами и эпик с одной подзадачей.
        Task task1 = new Task("Task1", "Description Task1.", TaskStatus.NEW);
        task1 = taskManager.createTask(task1);

        Task task2 = new Task("Task2", "Description Task2.", TaskStatus.NEW);
        task2 = taskManager.createTask(task2);


        Epic epic1 = new Epic("Epic1", "Description Epic1.", TaskStatus.NEW);
        epic1 = taskManager.createEpic(epic1);

        Subtask subtask1 = new Subtask("Subtask1", "Description Subtask1.", TaskStatus.NEW, epic1.getId());
        subtask1 = taskManager.createSubtask(subtask1);

        Subtask subtask2 = new Subtask("Subtask2", "Description Subtask2.", TaskStatus.NEW, epic1.getId());
        subtask2 = taskManager.createSubtask(subtask2);


        Epic epic2 = new Epic("Epic2", "Description Epic2.", TaskStatus.NEW);
        epic2 = taskManager.createEpic(epic2);

        Subtask subtask3 = new Subtask("Subtask4", "Description Subtask4.", TaskStatus.NEW, epic2.getId());
        subtask3 = taskManager.createSubtask(subtask3);

        //Распечатайте списки эпиков, задач и подзадач через System.out.println(..).
        System.out.println("Распечатываем созданные списки эпиков, задач и подзадач");
        System.out.println("\n" + taskManager.getAllTask());
        System.out.println("\n" + taskManager.getAllEpic());
        System.out.println("\n" + taskManager.getAllSubtask());
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * *");

        //Измените статусы созданных объектов, распечатайте их. Проверьте, что статус задачи и подзадачи сохранился,
        // а статус эпика рассчитался по статусам подзадач.
        System.out.println("Изменяем статусы созданных объектов и распечатываем их.");
        task1.setStatus(TaskStatus.IN_PROGRESS);
        task2.setStatus(TaskStatus.DONE);
        task1 = taskManager.updateTask(task1);
        task2 = taskManager.updateTask(task2);

        subtask1.setStatus(TaskStatus.IN_PROGRESS);
        subtask2.setStatus(TaskStatus.DONE);
        subtask3.setStatus(TaskStatus.DONE);
        taskManager.updateSubtask(subtask1);
        taskManager.updateSubtask(subtask2);
        taskManager.updateSubtask(subtask3);

        System.out.println("\n" + taskManager.getAllTask());
        System.out.println("\n" + taskManager.getAllEpic());
        System.out.println("\n" + taskManager.getAllSubtask());
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * *");

        //И, наконец, попробуйте удалить одну из задач и один из эпиков.
        System.out.println("Удаляем одну из задач и один из эпиков. Распечатываем объекты");
        taskManager.dellTaskById(task1.getId());
        taskManager.dellEpicById(epic1.getId());

        System.out.println("\n" + taskManager.getAllTask());
        System.out.println("\n" + taskManager.getAllEpic());
        System.out.println("\n" + taskManager.getAllSubtask());
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * *");

    }
}