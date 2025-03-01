package main;

import task.*;
import manager.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Main {

    private static final String FILE_NAME = "src/main/resources/storage.csv";

    public static void main(String[] args) {
        File fileStorage = createFile(FILE_NAME);
        TaskManager manager = Managers.getDefaultTaskManager(fileStorage);
        printSprint_6_solution(manager);

    }

    private static File createFile(String fileName) {
        try {
            return Files.createFile(Paths.get(fileName)).toFile();
//            File fileStorage = new File(FILE_NAME);
//            if (fileStorage.createNewFile()) {
//                return fileStorage;
//            }
        } catch (IOException exception) {
            System.out.println("Файл для файлового хранилища не создан :( " + exception.getMessage());
        }
        throw new UnsupportedOperationException("К сожалению, файл не создан");

    }

    private static void printSprint_6_solution(TaskManager manager) {
        //Реализуйте в классе Main опциональный пользовательский сценарий
        System.out.println("* ".repeat(15));
        System.out.println("Создаем две задачи, а также эпик с тремя подзадачами и эпик без подзадач.");

        //Создайте две задачи, а также эпик с двумя подзадачами и эпик с одной подзадачей.
        Task task1 = new Task("Task1", "Description Task1.", TaskStatus.NEW);
        task1 = manager.createTask(task1);

        Task task2 = new Task("Task2", "Description Task2.", TaskStatus.NEW);
        task2 = manager.createTask(task2);

        Epic epic1 = new Epic("Epic1", "Description Epic1.", TaskStatus.NEW);
        epic1 = manager.createEpic(epic1);

        Subtask subtask1 = new Subtask("Subtask1", "Description Subtask1.", TaskStatus.NEW, epic1.getId());
        subtask1 = manager.createSubtask(subtask1);

        Subtask subtask2 = new Subtask("Subtask2", "Description Subtask2.", TaskStatus.NEW, epic1.getId());
        subtask2 = manager.createSubtask(subtask2);

        Subtask subtask3 = new Subtask("Subtask3", "Description Subtask3.", TaskStatus.NEW, epic1.getId());
        subtask3 = manager.createSubtask(subtask3);

        Epic epic2 = new Epic("Epic2", "Description Epic2.", TaskStatus.NEW);
        epic2 = manager.createEpic(epic2);


        //Распечатайте созданные списки эпиков, задач и подзадач
        System.out.println("Распечатываем созданные списки эпиков, задач и подзадач");
        System.out.println("\n" + manager.getAllTask());
        System.out.println("\n" + manager.getAllEpic());
        System.out.println("\n" + manager.getAllSubtask());
        System.out.println("* ".repeat(15));

        printHistory(manager);                          //Распечатываем историю - она должна быть пустой!

        //Запросите созданные задачи несколько раз в разном порядке.
        //После каждого запроса выведите историю и убедитесь, что в ней нет повторов

        manager.getTaskById(task1.getId());
        manager.getTaskById(task2.getId());
        manager.getEpicById(epic1.getId());
        manager.getEpicById(epic2.getId());
        manager.getSubtaskById(subtask1.getId());
        manager.getSubtaskById(subtask2.getId());
        manager.getSubtaskById(subtask3.getId());

        printHistory(manager);                          //Распечатываем историю

        manager.getSubtaskById(subtask1.getId());
        manager.getTaskById(task2.getId());

        printHistory(manager);                          //Распечатываем историю

        manager.getEpicById(epic2.getId());
        manager.getTaskById(task2.getId());
        manager.getSubtaskById(subtask2.getId());

        printHistory(manager);                          //Распечатываем историю

        //Удалите задачу (и одну подзадачу), которая есть в истории, и проверьте, что при печати она не будет выводиться
        manager.dellTaskById(task2.getId());
        manager.dellSubtaskById(subtask3.getId());

        printHistory(manager);                          //Распечатываем историю

        //Удалите эпик с тремя подзадачами и убедитесь, что из истории удалился как сам эпик, так и все его подзадачи
        manager.dellEpicById(epic1.getId());

        printHistory(manager);                          //Распечатываем историю


    }

    public static void printHistory(TaskManager manager) {
        System.out.println("Распечатываем историю:");
        for (Task task : manager.getHistory()) {
            System.out.println(task);
        }
        System.out.println("* ".repeat(15));
    }
}