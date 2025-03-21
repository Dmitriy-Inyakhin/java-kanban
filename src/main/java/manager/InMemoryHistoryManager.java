package manager;

import task.Node;
import task.Task;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InMemoryHistoryManager implements HistoryManager {

    private final Map<Integer, Node> nodeMap = new HashMap<>();

    private Node head;                      //Указатель на первый элемент списка. Он же first
    private Node tail;                      //Указатель на последний элемент списка. Он же  last

    // добавляем задачу в историю просмотров с помощью HashMap
    @Override
    public void addTask(Task task) {
//        if (task != null && !task.getName().isEmpty()) {
//            if (nodeMap.get(task.getId()) != null) {
//                removeNode(nodeMap.get(task.getId()));
//            }
//            linkLast(task);
//        }
        if (task != null && !task.getName().isEmpty()) {
            Optional.ofNullable(nodeMap.get(task.getId()))
                    .ifPresent(this::removeNode); // Если задача уже есть в истории, удаляем её
            linkLast(task);
        }
    }

    // получаем историю просмотров
    @Override
//    public List<Task> getHistory() {
//        List<Task> result = new ArrayList<>();
//        Node node = head;
//        while (Objects.nonNull(node)) {
//            result.add(node.getTask());
//            node = node.next;
//        }
//        return result;
//    }

    public List<Task> getHistory() {
        return Stream.iterate(head, Objects::nonNull, node -> node.next)
                .map(Node::getTask)
                .collect(Collectors.toList());
    }

    // метод удаления Ноды по Id таска из списка
    @Override
    public void remove(int id) {
        removeNode(nodeMap.get(id));
    }

    // метод удаления Ноды
    private void removeNode(Node node) {
        if (node == null) {                                     // если пытаемся удалить задачу, которая не попадала в
            return;                                                    // историю, то выход

        }
        if (node.prev != null && node.next != null) {               // если есть и предыдущий, и следующий
            Node previous = node.prev;                              // предыдущий
            Node next = node.next;                                  // следующий
            previous.next = next;                                   // переопределяем
            next.prev = previous;
        } else if (node.prev != null) {                             // если нет следующего
            node.prev.next = null;                                  // предыдущий становится хвостом
            tail = node.prev;
        } else if (node.next != null) {                             // если нет предыдущего
            node.next.prev = null;                                  // следующий становится головой
            head = node.next;
        } else {                                                    // если узел был единственный
            head = null;
            tail = null;
        }
        nodeMap.remove(node.task.getId());                          // удаляем нужный узел
    }

    //метод добавления задачи в конец списка
    private void linkLast(Task task) {
        if (Objects.isNull(task)) {
            return;
        }
        Node newNode = new Node(null, task, null); // Создаем новый узел
        if (head == null) {
            head = newNode;                                             // новый узел становится и головой, и хвостом
            tail = newNode;
        } else {
            tail.next = newNode;                                        // новый узел становится следующим после текущего хвоста
            newNode.prev = tail;                                        // предыдущим для нового узла становится текущий хвост
            tail = newNode;                                             // новый узел становится новым хвостом
        }
        nodeMap.put(task.getId(), newNode);                             // кладем в мапу готовый узел

    }


}

