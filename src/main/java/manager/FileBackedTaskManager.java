package manager;

import task.Epic;
import task.Subtask;
import task.Task;

import java.io.File;
import java.util.List;

public class FileBackedTaskManager extends InMemoryTaskManager {

    private File file;

    public FileBackedTaskManager(File file) {
        this.file = file;
    }

    public static void saveToFile() {
//        try (fileWriter) {
//
//        } catch() {
//
//        }

    }

    public static FileBackedTaskManager loadFromFile(File file) {
        final FileBackedTaskManager result = new FileBackedTaskManager(file);


        return result;
    }

    @Override
    public Epic createEpic(Epic epic) {
        return super.createEpic(epic);
    }

    @Override
    public Task createTask(Task task) {
        return super.createTask(task);
    }

    @Override
    public Subtask createSubtask(Subtask subtask) {
        return super.createSubtask(subtask);
    }

    @Override
    public Task updateTask(Task task) {
        return super.updateTask(task);
    }

    @Override
    public Subtask updateSubtask(Subtask subtask) {
        return super.updateSubtask(subtask);
    }

    @Override
    public Epic updateEpic(Epic epic) {
        return super.updateEpic(epic);
    }

    @Override
    public List<Task> getAllTask() {
        return super.getAllTask();
    }

    @Override
    public List<Epic> getAllEpic() {
        return super.getAllEpic();
    }

    @Override
    public List<Subtask> getAllSubtask() {
        return super.getAllSubtask();
    }

    @Override
    public List<Subtask> getAllSubtaskByEpic(Epic epic) {
        return super.getAllSubtaskByEpic(epic);
    }

    @Override
    public void dellAllTask() {
        super.dellAllTask();
    }

    @Override
    public void dellAllEpic() {
        super.dellAllEpic();
    }

    @Override
    public void dellAllSubtask() {
        super.dellAllSubtask();
    }

    @Override
    public Task getTaskById(int id) {
        return super.getTaskById(id);
    }

    @Override
    public Epic getEpicById(int id) {
        return super.getEpicById(id);
    }

    @Override
    public Subtask getSubtaskById(int id) {
        return super.getSubtaskById(id);
    }

    @Override
    public void dellTaskById(int id) {
        super.dellTaskById(id);
    }

    @Override
    public void dellSubtaskById(int id) {
        super.dellSubtaskById(id);
    }

    @Override
    public void dellEpicById(int id) {
        super.dellEpicById(id);
    }

    @Override
    public List<Task> getHistory() {
        return super.getHistory();
    }
}
