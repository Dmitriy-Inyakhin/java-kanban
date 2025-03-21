package main;

import task.*;
import manager.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


public class Main {

    private static final String FILE_NAME = "src/main/resources/storage.csv";

    public static void main(String[] args) throws IOException {

        TaskManager manager = Managers.getTaskManager(new File(FILE_NAME));

        /*
        для проверки работоспособности удаляем файл storage.csv
        запускаем метод printSprint_6_solution(manager); -
        он создает файл storage.csv и записывает в него задачи.
        после этого комментируем метод printSprint_6_solution(manager);
        и запускаем метод printSprint_7_solution(manager); который производит
        печать из ранее созданного файла
         */

        printSprint_6_solution(manager);

        printSprint_7_solution(manager);


    }

    private static void printSprint_7_solution(TaskManager manager) {
        System.out.println("Распечатываем загруженные из файла списки эпиков, задач и подзадач");
        System.out.println("\n" + manager.getAllTask());
        System.out.println("\n" + manager.getAllEpic());
        System.out.println("\n" + manager.getAllSubtask());
        System.out.println("* ".repeat(15));
    }

    private static void printSprint_6_solution(TaskManager manager) {
        //Реализуйте в классе Main опциональный пользовательский сценарий
        System.out.println("* ".repeat(15));
        System.out.println("Создаем две задачи, а также эпик с тремя подзадачами и эпик без подзадач.");

        //Создайте две задачи, а также эпик с двумя подзадачами и эпик с одной подзадачей.
        Task task1 = new Task("Task1", "Description Task1", TaskStatus.NEW, Duration.of(10, ChronoUnit.MINUTES), LocalDateTime.now());
        task1 = manager.createTask(task1);

        Task task2 = new Task("Task2", "Description Task2", TaskStatus.NEW, Duration.of(10, ChronoUnit.MINUTES), task1.getEndTime());
        task2 = manager.createTask(task2);

        Epic epic1 = new Epic("Epic1", "Description Epic1", TaskStatus.NEW, Duration.of(10, ChronoUnit.MINUTES), task2.getEndTime());
        epic1 = manager.createEpic(epic1);

        Subtask subtask1 = new Subtask("Subtask1", "Description Subtask1", TaskStatus.NEW, epic1.getId(), Duration.of(10, ChronoUnit.MINUTES), epic1.getEndTime());
        subtask1 = manager.createSubtask(subtask1);

        Subtask subtask2 = new Subtask("Subtask2", "Description Subtask2", TaskStatus.DONE, epic1.getId(), Duration.of(10, ChronoUnit.MINUTES), subtask1.getEndTime());
        subtask2 = manager.createSubtask(subtask2);

        Subtask subtask3 = new Subtask("Subtask3", "Description Subtask3", TaskStatus.NEW, epic1.getId(), Duration.of(10, ChronoUnit.MINUTES), subtask2.getEndTime());
        subtask3 = manager.createSubtask(subtask3);

        Epic epic2 = new Epic("Epic2", "Description Epic2", TaskStatus.NEW, Duration.of(10, ChronoUnit.MINUTES), subtask3.getEndTime());
        epic2 = manager.createEpic(epic2);

        Subtask subtask4 = new Subtask("Subtask4", "Description Subtask4", TaskStatus.NEW, epic2.getId(), Duration.of(10, ChronoUnit.MINUTES), epic2.getEndTime());
        subtask4 = manager.createSubtask(subtask4);


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

    }

    public static void printHistory(TaskManager manager) {
        System.out.println("Распечатываем историю:");
        for (Task task : manager.getHistory()) {
            System.out.println(task);
        }
        System.out.println("* ".repeat(15));
    }
}