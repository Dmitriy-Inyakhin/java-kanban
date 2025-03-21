package file;

import task.Epic;
import task.Subtask;
import task.Task;

public class TaskCsvFormatHandler {

    public static final String DELIMITER = ", ";

    public static String getHeader() {
        return "id,type,name,status,description,duration,startTime,epic\n";
    }

    public static String toString(Task task) {
        return task.getId() + DELIMITER
                + task.getType() + DELIMITER
                + task.getName() + DELIMITER
                + task.getStatus() + DELIMITER
                + task.getDescription() + DELIMITER
                + task.getDuration().toMillis() + DELIMITER
                + task.getStartTime() + DELIMITER
                + "\n";

    }

    public static String toString(Epic epic) {
        return epic.getId() + DELIMITER
                + epic.getType() + DELIMITER
                + epic.getName() + DELIMITER
                + epic.getStatus() + DELIMITER
                + epic.getDescription() + DELIMITER
                + epic.getDuration().toMillis() + DELIMITER
                + epic.getStartTime() + DELIMITER
                + epic.subtaskId +
                "\n";

    }

    public static String toString(Subtask subtask) {
        return subtask.getId() + DELIMITER
                + subtask.getType() + DELIMITER
                + subtask.getName() + DELIMITER
                + subtask.getStatus() + DELIMITER
                + subtask.getDescription() + DELIMITER
                + subtask.getEpicId()
                + subtask.getDuration().toMillis() + DELIMITER
                + subtask.getStartTime() + DELIMITER
                + "\n";

    }


}
