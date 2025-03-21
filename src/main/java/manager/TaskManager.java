package manager; //

import task.*;

import java.util.List;

public interface TaskManager {
    Task createTask(Task task);              //создание таска

    Epic createEpic(Epic epic);               //создание эпика

    Subtask createSubtask(Subtask subtask);     //создание субтаска

    //********************
    Task updateTask(Task task);                 //обновление таска

    Subtask updateSubtask(Subtask subtask);     //изменение статуса субтаска ведет к изменению статуса эпика

    //********************
    List<Task> getAllTask();                    //получение всех задач

    List<Epic> getAllEpic();                    //получение всех эпиков

    List<Subtask> getAllSubtask();              //получение всех субтасков

    //********************
    void dellAllTask();                         //удаление всех задач

    void dellAllEpic();                         //удаление всех эпиков

    void dellAllSubtask();                      //удаление всех субтасков

    //********************
    Task getTaskById(int id);                   //получение таска по Id

    Epic getEpicById(int id);                   //получение эпика по Id

    Subtask getSubtaskById(int id);             //получение субтаска по Id

    //********************
    void dellTaskById(int id);                  //удаление задачи по Id

    void dellSubtaskById(int id);               //удаление субтаска по Id

    void dellEpicById(int id);                  //удаление субтаска по Id

    //********************
    List<Subtask> getAllSubtaskByEpic(Epic epic); //получение всех субтасков определенного эпика

    //********************
    Epic updateEpic(Epic epic);                 //обновление эпика

    List<? extends Task> getHistory();
}
