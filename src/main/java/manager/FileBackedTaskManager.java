package manager;

import exceptions.ManagerLoadException;
import exceptions.ManagerSaveException;
import file.TaskCsvFormatHandler;
import task.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class FileBackedTaskManager extends InMemoryTaskManager {

    private File file;

    public FileBackedTaskManager(File file) {
        this.file = file;
        try {
            createFile(file.toString());
        } catch (ManagerSaveException e) {
            throw new RuntimeException("Ошибка инициализации файла", e);
        }
    }

    public static FileBackedTaskManager loadFromFile(File file) throws ManagerLoadException {
        FileBackedTaskManager result = new FileBackedTaskManager(file);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            if (!br.ready()) {
                return result;
            }
            // Пропускаем заголовок
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    result.fromString(line);
                }
            }
        } catch (FileNotFoundException e) {
            throw new ManagerLoadException("Файл не найден");
        } catch (IOException e) {
            throw new ManagerLoadException("Ошибка чтения файла");
        }
        return result;
    }


    protected static Path createFile(String fileName) {
        if (!Files.exists(Path.of(fileName))) {
            try {
                System.out.println("создаем файл");
                return Files.createFile(Paths.get(fileName));
            } catch (IOException exception) {
                System.out.println("Файл для файлового хранилища не создан :( " + exception.getMessage());
            }
            throw new UnsupportedOperationException("К сожалению, файл не создан");
        }
        System.out.println("файл уже создан");
        return Path.of(fileName);
    }

    public void saveToFile() {                  //запись в файл
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(TaskCsvFormatHandler.getHeadler());

            for (Map.Entry<Integer, Task> entry : this.tasks.entrySet()) {
                Task task = entry.getValue();
                writer.write(TaskCsvFormatHandler.toString(task));
            }

            for (Map.Entry<Integer, Epic> entry : this.epics.entrySet()) {
                Epic epic = entry.getValue();
                writer.write(TaskCsvFormatHandler.toString(epic));
            }

            for (Map.Entry<Integer, Subtask> entry : this.subtasks.entrySet()) {
                Subtask subtask = entry.getValue();
                writer.write(TaskCsvFormatHandler.toString(subtask));
            }

        } catch (IOException exception) {
            throw new ManagerSaveException("Невозможно работать с файлом");
        }

    }

    private Task fromString(String taskString) throws ManagerSaveException {
        String[] data = taskString.split(",");
        try {
            TaskType taskType = TaskType.valueOf(data[1]);

            switch (taskType) {
                case TASK:
                    Task task = new Task(taskString);
                    tasks.put(task.getId(), task); // сразу записываем в менеджер
                    return task;
                case EPIC:
                    Epic epic = new Epic(taskString);
                    epics.put(epic.getId(), epic);
                    return epic;
                case SUBTASK:
                    Subtask subtask = new Subtask(taskString);
                    if (epics.containsKey(subtask.getEpicId())) {
                        subtasks.put(subtask.getId(), subtask);
                        Epic epicForSubtask = epics.get(subtask.getEpicId());
                        epicForSubtask.getSubtaskId().add(subtask.getId());
                        return subtask;
                    }
                    throw new ManagerSaveException("Эпик для сабтаска не найден");
                default:
                    throw new ManagerSaveException("Неподдерживаемый тип задачи");
            }
        } catch (IllegalArgumentException e) {
            throw new ManagerSaveException("Ошибка парсинга строки: " + taskString, e);
        }
    }

    @Override
    public Epic createEpic(Epic epic) {
        Epic innerEpic = super.createEpic(epic);
        saveToFile();
        return innerEpic;
    }

    @Override
    public Task createTask(Task task) {
        Task innerTask = super.createTask(task);
        saveToFile();
        return innerTask;
    }

    @Override
    public Subtask createSubtask(Subtask subtask) {
        Subtask innerSubtask = super.createSubtask(subtask);
        saveToFile();
        return innerSubtask;
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
