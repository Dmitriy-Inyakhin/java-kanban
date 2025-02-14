package ru.yandex.dmitriy010379.main; //
import ru.yandex.dmitriy010379.task.*;
import ru.yandex.dmitriy010379.manager.*;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = Managers.getDefaultTaskManager();

        printInPreviousSprint(manager);
        //printInPreviousTwoSprint(manager);

    }


    private static void printInPreviousTwoSprint(TaskManager manager) {
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * *");


        Task task1 = new Task("Task1", "Description Task1.", TaskStatus.NEW); //создаем задачу
        Task task1Modified = manager.createTask(task1);                                 // сохраняем ее в менеджере историй

//        task1 = manager.createTask(task1);

        Task taskFirst = manager.getTaskById(task1Modified.getId());        //получаем задачу по идентификатору
        taskFirst.setStatus(TaskStatus.IN_PROGRESS);                        // изменяем ей статус
        Task taskFirstModified = manager.updateTask(taskFirst);             //обновляем задачу

        Task taskSecond = manager.getTaskById(taskFirstModified.getId());   // получаем ее вторую версию по идентификатору
        taskSecond.setDescription(taskSecond.getDescription() + " Дополнениие 1"); //изменяем ей описание
        Task taskSecondModified = manager.updateTask(taskSecond);               //опять обновляем задачу

        Task task4 = manager.getTaskById(taskFirstModified.getId());
        taskSecond.setDescription(task4.getDescription() + " Дополнениие 2");
        Task task4M = manager.updateTask(task4);

        Epic epic1 = new Epic("Epic1", "Description Epic1.", TaskStatus.NEW);
        Epic epic1M = manager.createEpic(epic1);

        Epic epic2 = manager.getEpicById(epic1.getId());

        Task task5 = manager.getTaskById(taskFirstModified.getId());
        taskSecond.setDescription(task5.getDescription() + " Дополнениие 3");
        Task task5M = manager.updateTask(task5);

        Task task6 = manager.getTaskById(taskFirstModified.getId());
        taskSecond.setDescription(task6.getDescription() + " Дополнениие 4");
        Task task6M = manager.updateTask(task6);

        Subtask subtask1 = new Subtask("Subtask1", "Description Subtask1.", TaskStatus.NEW, epic1.getId());
        Subtask subtask1M = manager.createSubtask(subtask1);
        Subtask subtask2 = manager.getSubtaskById(subtask1.getId());

        Task task7 = manager.getTaskById(taskFirstModified.getId());
        taskSecond.setDescription(task7.getDescription() + " Дополнениие 5");
        Task task7M = manager.updateTask(task7);

        Task task8 = manager.getTaskById(taskFirstModified.getId());
        taskSecond.setDescription(task8.getDescription() + " Дополнениие 6");
        Task task8M = manager.updateTask(task8);
        task8 = manager.getTaskById(taskFirstModified.getId());

//        Task task9 = manager.getTaskById(taskFirstModified.getId());
//        taskSecond.setDescription(task9.getDescription() + " Дополнениие 7");
//        Task task9M = manager.updateTask(task9);
//
//        Task task10 = manager.getTaskById(taskFirstModified.getId());
//        taskSecond.setDescription(task10.getDescription() + " Дополнениие 8");
//        Task task10M = manager.updateTask(task10);
//
//        Task task11 = manager.getTaskById(taskFirstModified.getId());
//        taskSecond.setDescription(task11.getDescription() + " Дополнениие 9");
//        Task task11M = manager.updateTask(task11);
//
//        Task task12 = manager.getTaskById(taskFirstModified.getId());
//        taskSecond.setDescription(task12.getDescription() + " Дополнениие 10");
//        Task task12M = manager.updateTask(task12);
//
//        Task task13 = manager.getTaskById(taskFirstModified.getId());
//        taskSecond.setDescription(task13.getDescription() + " Дополнениие 11");
//        Task task13M = manager.updateTask(task13);


        System.out.println("История:");
        for (Task task : manager.getHistory()) {
            System.out.println(task);
        }



        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * *");
    }

    private static void printInPreviousSprint(TaskManager manager) {
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * *");
        System.out.println("Создаем две задачи, а также эпик с двумя подзадачами и эпик с одной подзадачей.");

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

        Epic epic2 = new Epic("Epic2", "Description Epic2.", TaskStatus.NEW);
        epic2 = manager.createEpic(epic2);

        Subtask subtask3 = new Subtask("Subtask4", "Description Subtask4.", TaskStatus.NEW, epic2.getId());
        subtask3 = manager.createSubtask(subtask3);

        //Распечатайте списки эпиков, задач и подзадач через System.out.println(..).
        System.out.println("Распечатываем созданные списки эпиков, задач и подзадач");
        System.out.println("\n" + manager.getAllTask());
        System.out.println("\n" + manager.getAllEpic());
        System.out.println("\n" + manager.getAllSubtask());
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * *");

        //Измените статусы созданных объектов, распечатайте их. Проверьте, что статус задачи и подзадачи сохранился,
        // а статус эпика рассчитался по статусам подзадач.
        System.out.println("Изменяем статусы созданных объектов и распечатываем их.");
        task1.setStatus(TaskStatus.IN_PROGRESS);
        task2.setStatus(TaskStatus.DONE);
        task1 = manager.updateTask(task1);
        task2 = manager.updateTask(task2);

        subtask1.setStatus(TaskStatus.IN_PROGRESS);
        subtask2.setStatus(TaskStatus.DONE);
        subtask3.setStatus(TaskStatus.DONE);
        manager.updateSubtask(subtask1);
        manager.updateSubtask(subtask2);
        manager.updateSubtask(subtask3);

        System.out.println("\n" + manager.getAllTask());
        System.out.println("\n" + manager.getAllEpic());
        System.out.println("\n" + manager.getAllSubtask());
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * *");

        //И, наконец, попробуйте удалить одну из задач и один из эпиков.
        System.out.println("Удаляем одну из задач и один из эпиков. Распечатываем объекты");
        manager.dellTaskById(task1.getId());
        manager.dellEpicById(epic1.getId());

        System.out.println("\n" + manager.getAllTask());
        System.out.println("\n" + manager.getAllEpic());
        System.out.println("\n" + manager.getAllSubtask());
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * *");

    }
}