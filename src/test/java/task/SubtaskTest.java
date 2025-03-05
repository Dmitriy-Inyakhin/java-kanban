package task;

import org.junit.jupiter.api.Test;
import manager.*;

import static org.junit.jupiter.api.Assertions.*;
class SubtaskTest {
    TaskManager manager;

    @Test
    void getEpicId() {
        manager = Managers.getTaskManager();

        Epic epic1 = new Epic("Test addNewEpic1", "Test addNewEpic1 description.", TaskStatus.NEW);
        Epic epic1M = manager.createEpic(epic1);

        Subtask subtask1 = new Subtask("Test addNewSubtask1", "Test addNewSubtask1 description.", TaskStatus.NEW, epic1.getId());
        subtask1 = manager.createSubtask(subtask1);

        int epicId = epic1M.getId();
        assertEquals(subtask1.getEpicId(), epicId, "Id разные.");
    }
}