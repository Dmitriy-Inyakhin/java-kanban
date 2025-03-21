// Класс для менеджера задач

package manager;

import task.*;

import java.util.*;
import java.util.stream.Collectors;

// Менеджер задач
public class InMemoryTaskManager implements TaskManager {
    protected static final Map<Integer, Task> tasks = new HashMap<>();           // Менджер хранения задач
    protected static final Map<Integer, Epic> epics = new HashMap<>();           // Менджер хранения эпиков
    protected static final Map<Integer, Subtask> subtasks = new HashMap<>();     // Менджер хранения субтасков

    private final HistoryManager historyManager = Managers.getDefaultHistoryManager();      //переменная для хранения
    // истории просмотров

    protected int idCounter = 0;                                   // Счетчик для уникальных ID

    //создание таска
    @Override
    public Task createTask(Task task) {
        if (task.getId() > 0) {
            return task;
        }
        task.setId(setNewId());
        tasks.put(task.getId(), task);
        return task;
    }

    //создание эпика
    @Override
    public Epic createEpic(Epic epic) {
        if (epic.getId() > 0) {
            return epic;
        }
        epic.setId(setNewId());
        epics.put(epic.getId(), epic);
        return epic;
    }

    //создание субтаска
    @Override
    public Subtask createSubtask(Subtask subtask) {
        if (subtask.getId() > 0 ||
                subtask.getEpicId() == 0 ||
                !epics.containsKey(subtask.getEpicId())) {               //если такой субтаск уже существует
            return subtask;
        }
        subtask.setId(setNewId());
        subtasks.put(subtask.getId(), subtask);
        Epic epic = epics.get(subtask.getEpicId());
        epic.getSubtaskId().add(subtask.getId());
        updateSubtask(subtask);
        return subtask;
    }

    //********************
    // обновление таска
    @Override
    public Task updateTask(Task task) {
        if (task.getId() == 0 ||
                tasks.containsKey(task.getId())) {
            return task;
        }
        tasks.put(task.getId(), task);
        return task;
    }

    // обновление статуса субтаска  ! изменение статуса субтаска ведет к изменению статуса эпика
    @Override
    public Subtask updateSubtask(Subtask subtask) {
        if (subtask.getId() == 0 ||                             //если нет такого субтаска
                subtask.getEpicId() == 0 ||                     //если субтаску не назначен эпик
                !epics.containsKey(subtask.getEpicId())) {       //если нет такого эпика
            return subtask;
        }
        subtasks.put(subtask.getId(), subtask);                 //перезапись субтаска с новым статусом
        updateEpicStatus(subtask.getEpicId());
        return subtask;
    }

    //обновление эпика
    @Override
    public Epic updateEpic(Epic epic) {
        if (epic.getId() == 0 ||
                tasks.containsKey(epic.getId())) {
            return epic;
        }
        epics.put(epic.getId(), epic);
        return epic;
    }

    //********************
    //получение всех задач
    @Override
    public List<Task> getAllTask() {
        return new ArrayList<>(tasks.values());
    }

    //получение всех эпиков
    @Override
    public List<Epic> getAllEpic() {
        return new ArrayList<>(epics.values());
    }

    //получение всех субтасков
    @Override
    public List<Subtask> getAllSubtask() {
        return new ArrayList<>(subtasks.values());
    }

    //получение всех субтасков определенного эпика
    @Override
//    public List<Subtask> getAllSubtaskByEpic(Epic epic) {
//        if (!epics.containsValue(epic)) {
//            return null;
//        }
//        List<Subtask> list = new ArrayList<>();
//        for (int index : epic.subtaskId) {
//            list.add(subtasks.get(index));
//        }
//        return list;
//    }
    public List<Subtask> getAllSubtaskByEpic(Epic epic) {
        if (!epics.containsValue(epic)) {
            return null;
        }

        return epic.subtaskId.stream() // Создаем поток из списка subtaskId
                .map(subtasks::get) // Получаем Subtask по каждому ID
                .collect(Collectors.toList()); // Собираем результат в список
    }

    //********************
    //удаление всех задач
    @Override
    public void dellAllTask() {
        tasks.clear();
    }

    //удаление всех эпиков
    @Override
//    public void dellAllEpic() {
//        for (Epic epics : epics.values()) {
//            if (!epics.isEmptySubtasks()) {
//                for (int i = epics.subtaskId.size(); i > 0; i--) {
//                    subtasks.remove(i);
//                }
//            }
//        }
//        epics.clear();                              //при удалении всех эпиков так же удаляются
//        subtasks.clear();                           //все субтаски потому, что без них не они не имеют смысла
//    }
    public void dellAllEpic() {
        // Удаляем все подзадачи, связанные с эпиками
        epics.values().stream()
                .filter(epic -> !epic.isEmptySubtasks()) // Фильтруем эпики с непустыми подзадачами
                .flatMap(epic -> epic.subtaskId.stream()) // Получаем поток всех subtaskId
                .forEach(subtasks::remove); // Удаляем каждую подзадачу из subtasks

        // Очищаем коллекции эпиков и подзадач
        epics.clear();
        subtasks.clear();
    }

    //удаление всех субтасков
    @Override
//    public void dellAllSubtask() {
//        for (Epic epics : epics.values()) {
//            epics.subtaskId.clear();
//        }
//        subtasks.clear();
//        for (Epic epics : epics.values()) {
//            updateEpicStatus(epics.getId());            //обновления статуса всех эпиков
//        }
//    }
    public void dellAllSubtask() {
        // Очищаем список subtaskId для всех эпиков
        epics.values().stream()
                .forEach(epic -> epic.subtaskId.clear());

        // Очищаем коллекцию подзадач
        subtasks.clear();

        // Обновляем статусы всех эпиков
        epics.values().stream()
                .forEach(epic -> updateEpicStatus(epic.getId()));
    }

    //********************
    //получение таска по Id
    @Override
    public Task getTaskById(int id) {
        if (!tasks.containsKey(id)) {
            return null;
        }
        Task task = tasks.get(id);
        historyManager.addTask(task);
        return task;
    }

    //получение эпика по Id
    @Override
    public Epic getEpicById(int id) {
        if (!epics.containsKey(id)) {
            return null;
        }
        Epic epic = epics.get(id);
        historyManager.addTask(epic);
        return epic;
    }

    //получение субтаска по Id
    @Override
    public Subtask getSubtaskById(int id) {
        if (!subtasks.containsKey(id)) {
            return null;
        }
        Subtask subtask = subtasks.get(id);
        historyManager.addTask(subtask);
        return subtask;
    }

    //********************
    //удаление задачи по Id
    @Override
    public void dellTaskById(int id) {
        if (!tasks.containsKey(id)) {
            return;
        }
        tasks.remove(id);
        historyManager.remove(id);
    }

    //удаление субтаска по Id
    @Override
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
        historyManager.remove(id);
        updateEpicStatus(counter);
    }

    //удаление эпика по Id
    @Override
//    public void dellEpicById(int id) {
//        if (!epics.containsKey(id)) {
//            return;
//        }
//        List<Integer> list = epics.get(id).subtaskId;
//        for (int i = list.size(); i > 0; i--) {
//            Integer lists = list.get(i - 1);
//            subtasks.remove(lists);
//            historyManager.remove(lists);
//        }
//        epics.remove(id);
//        historyManager.remove(id);
//    }
    public void dellEpicById(int id) {
        // Проверяем, существует ли эпик с указанным ID
        Epic epic = epics.get(id);
        if (epic == null) {
            return;
        }

        // Удаляем все подзадачи, связанные с эпиком
        epic.subtaskId.stream()
                .forEach(subtaskId -> {
                    subtasks.remove(subtaskId); // Удаляем подзадачу из коллекции subtasks
                    historyManager.remove(subtaskId); // Удаляем подзадачу из истории
                });

        // Удаляем сам эпик
        epics.remove(id);

        // Удаляем эпик из истории
        historyManager.remove(id);
    }

    //********************
    // получение истории обращения к задачам
    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }

    //********************
    // Генерация уникального ID
    private int setNewId() {
        return (++idCounter);
    }

    //метод для обновления статуса эпика
//    public void updateEpicStatus(int epicId) {
//        Epic epic = epics.get(epicId);
//        boolean allDone = true;
//        boolean allNew = true;
//
//        for (int subtaskId : epic.getSubtaskId()) {
//            TaskStatus status = subtasks.get(subtaskId).getStatus();
//            if (status != TaskStatus.DONE) {
//                allDone = false;
//            }
//            if (status != TaskStatus.NEW) {
//                allNew = false;
//            }
//        }
//        if (allDone) {
//            epic.setStatus(TaskStatus.DONE);
//        } else if (allNew) {
//            epic.setStatus(TaskStatus.NEW);
//        } else {
//            epic.setStatus(TaskStatus.IN_PROGRESS);
//        }
//    }
    public void updateEpicStatus(int epicId) {
        Epic epic = epics.get(epicId);
        if (epic == null) {
            return; // Если эпик не найден, завершаем выполнение метода
        }

        // Получаем статусы всех подзадач эпика
        List<TaskStatus> subtaskStatuses = epic.getSubtaskId().stream()
                .map(subtasks::get) // Получаем подзадачи по ID
                .map(Task::getStatus) // Получаем статус каждой подзадачи
                .toList(); // Собираем статусы в список

        // Проверяем условия для обновления статуса эпика
        boolean allDone = subtaskStatuses.stream().allMatch(status -> status == TaskStatus.DONE);
        boolean allNew = subtaskStatuses.stream().allMatch(status -> status == TaskStatus.NEW);

        if (allDone) {
            epic.setStatus(TaskStatus.DONE);
        } else if (allNew) {
            epic.setStatus(TaskStatus.NEW);
        } else {
            epic.setStatus(TaskStatus.IN_PROGRESS);
        }
    }

}

