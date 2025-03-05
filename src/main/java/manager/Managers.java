package manager;

import java.io.File;

public final class Managers {

    private Managers() {
    }

    public static TaskManager getTaskManager() {
        return new InMemoryTaskManager();
    }

    public static TaskManager getTaskManager(File file) {
        //FileBackedTaskManager.createFile(file.toString());
        return FileBackedTaskManager.loadFromFile(file);
    }


    public static HistoryManager getDefaultHistoryManager() {
        return new InMemoryHistoryManager();
    }

}
