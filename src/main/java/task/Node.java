package task;

public class Node {
    public Task task;
    public Node next;
    public Node prev;

    public Node(Node prev, Task task, Node next) {
        this.task = task;
        this.next = next;
        this.prev = prev;
    }

    // собирать все задачи из него в обычный ArrayList
    public Task getTask() {
        return task;
    }
}
