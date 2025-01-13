package ru.yandex.dmitriy010379.manager;

import ru.yandex.dmitriy010379.task.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Менеджер задач
public class TaskManager {
    protected final Map<Integer, Task> tasks = new HashMap<>();           // Менджер хранения задач
    protected final Map<Integer, Epic> epics = new HashMap<>();           // Менджер хранения эпиков
    protected final Map<Integer, Subtask> subtasks = new HashMap<>();     // Менджер хранения субтасков
    protected static int idCounter = 0;                                   // Счетчик для уникальных ID

    private int setNewId() {
        return (++idCounter);
    }     // Генерация уникального ID

    public Task createTask(Task task) {
        if (task.getId() > 0) {
            return task;
        }
        int newId = setNewId();
        task.setId(newId);
        tasks.put(task.getId(), task);
        return task;
    }               //создание таска

    public Epic createEpic(Epic epic) {
        if (epic.getId() > 0) {
            return epic;
        }
        int newId = setNewId();
        epic.setId(newId);
        epics.put(epic.getId(), epic);
        return epic;
    }               //создание эпика

    public Subtask createSubtask(Subtask subtask) {
        if (subtask.getId() > 0 ||
                subtask.getEpicId() == 0 ||
                !epics.containsKey(subtask.getEpicId())) {               //если такой субтаск уже существует
            return subtask;
        }
        int newId = setNewId();
        subtask.setId(newId);
        subtasks.put(subtask.getId(), subtask);
        Epic epic = epics.get(subtask.getEpicId());
        epic.getSubtaskId().add(newId);
        updateSubtask(subtask);
        return subtask;
    }   //создание субтаска

    public Task updateTask(Task task) {
        if (task.getId() == 0 ||
                tasks.containsKey(task.getId())) {
            return task;
        }
        tasks.put(task.getId(), task);
        return task;
    }               //обновление таска

    public Epic updateEpic(Epic epic) {
        if (epic.getId() == 0 ||
                tasks.containsKey(epic.getId())) {
            return epic;
        }
        epics.put(epic.getId(), epic);
        return epic;
    }               //обновление эпика (наличие данного метода спорное!)

    public void updateEpicStatus(int epicId) {
        Epic epic = epics.get(epicId);
        boolean allDone = true;
        boolean allNew = true;

        for (int subtaskId : epic.getSubtaskId()) {
            TaskStatus status = subtasks.get(subtaskId).getStatus();
            if (status != TaskStatus.DONE) {
                allDone = false;
            }
            if (status != TaskStatus.NEW) {
                allNew = false;
            }
        }

        if (allDone) {
            epic.setStatus(TaskStatus.DONE);
        } else if (allNew) {
            epic.setStatus(TaskStatus.NEW);
        } else {
            epic.setStatus(TaskStatus.IN_PROGRESS);
        }
    }       //метод для обновления статуса эпика

    public Subtask updateSubtask(Subtask subtask) {
        if (subtask.getId() == 0 ||                             //если нет такого субтаска
                subtask.getEpicId() == 0 ||                     //если субтаску не назначен эпик
                !epics.containsKey(subtask.getEpicId())) {       //если нет такого эпика
            return subtask;
        }
        subtasks.put(subtask.getId(), subtask);                 //перезапись субтаска с новым статусом
        updateEpicStatus(subtask.getEpicId());
        return subtask;
    }   //изменение статуса субтаска ведет к изменению статуса эпика

    public Map getAllTask() {
        return tasks;
    }            //получение всех задач

    public Map getAllEpic() {
        return epics;
    }            //получение всех эпиков

    public Map getAllSubtask() {
        return subtasks;
    }      //получение всех субтасков

    public void dellAllTask() {
        tasks.clear();
    }         //удаление всех задач

    public void dellAllEpic() {
        for (Epic epics : epics.values()) {
            if (!epics.isEmptySubtasks()) {
                for (int i = epics.subtaskId.size(); i > 0; i--) {
                    subtasks.remove(i);
                }
            }
        }
        epics.clear();                              //при удалении всех эпиков так же удаляются
        subtasks.clear();                           //все субтаски потому, что без них не они не имеют смысла
    }                      //удаление всех эпиков

    public void dellAllSubtask() {
        for (Epic epics : epics.values()) {
            epics.subtaskId.clear();
        }
        subtasks.clear();
    }                   //удаление всех субтасков

    public Task getTaskById(int id) {
        if (!tasks.containsKey(id)) {
            return null;
        }
        return tasks.get(id);
    }                //получение таска по Id

    public Epic getEpicById(int id) {
        if (!epics.containsKey(id)) {
            return null;
        }
        return epics.get(id);
    }                //получение эпика по Id

    public Subtask getSubtaskById(int id) {
        if (!subtasks.containsKey(id)) {
            return null;
        }
        return subtasks.get(id);
    }          //получение субтаска по Id

    public void dellTaskById(int id) {
        if (!tasks.containsKey(id)) {
            return;
        }
        tasks.remove(id);
    }               //удаление задачи по Id

    public void dellSubtaskById(int id) {
        if (!subtasks.containsKey(id) ||                                    //если нет такого субтаска
                subtasks.get(id).getEpicId() == 0 ||                        //если субтаску не назначен эпик
                !epics.containsKey(subtasks.get(id).getEpicId())) {         //если нет такого эпика
            return;
        }
        int counter = subtasks.get(id).getEpicId();
        Epic epic = epics.get(subtasks.get(id).getEpicId());
        epic.subtaskId.remove((Integer) id);
        subtasks.remove(id);
        updateEpicStatus(counter);
    }           //удаление субтаска по Id

    public void dellEpicById(int id) {
        if (!epics.containsKey(id)) {
            return;
        }
        List<Integer> list = epics.get(id).subtaskId;
        for (int i = list.size(); i > 0 ; i--) {
            Integer lists = list.get(i-1);
            subtasks.remove(lists);
        }
        epics.remove(id);
    }                 //удаление субтаска по Id
}

