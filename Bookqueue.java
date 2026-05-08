import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Max-heap priority queue where priority = waitingTime - retainingTime
// Supports updating an employee's position when their priority changes
public class Bookqueue {

    private static class Node {
        Employee employee;
        Node(Employee e) { this.employee = e; }
    }

    private final List<Node> heap = new ArrayList<>();
    private final Map<String, Integer> indexMap = new HashMap<>(); // maps employee name to heap index

    public Bookqueue() {
    }

    // Add employee to the queue. O(log n)
    public void push(Employee e) {
        if (indexMap.containsKey(e.getName()))
            throw new IllegalArgumentException("Employee already in queue: " + e.getName());
        heap.add(new Node(e));
        int idx = heap.size() - 1;
        indexMap.put(e.getName(), idx);
        siftUp(idx);
    }

    // Remove and return highest priority employee. O(log n)
    public Employee pop() {
        if (isEmpty()) throw new IllegalStateException("Priority queue is empty.");
        Employee top = heap.get(0).employee;
        indexMap.remove(top.getName());
        int last = heap.size() - 1;
        if (last == 0) {
            heap.remove(0);
        } else {
            heap.set(0, heap.remove(last));
            indexMap.put(heap.get(0).employee.getName(), 0);
            siftDown(0);
        }
        return top;
    }

    // Return highest priority employee without removing. O(1)
    public Employee peek() {
        if (isEmpty()) throw new IllegalStateException("Priority queue is empty.");
        return heap.get(0).employee;
    }

    // Re-sort an employee after their priority changed. O(log n)
    public void update(Employee e) {
        Integer idx = indexMap.get(e.getName());
        if (idx == null) return;
        siftUp(idx);
        siftDown(indexMap.get(e.getName()));
    }

    public boolean contains(Employee e) { return indexMap.containsKey(e.getName()); }
    public boolean isEmpty() { return heap.isEmpty(); }
    public int size() { return heap.size(); }

    // Returns employees sorted by priority (for display)
    public List<Employee> toSortedList() {
        Bookqueue copy = new Bookqueue();
        for (Node n : heap) copy.push(n.employee);
        List<Employee> result = new ArrayList<>();
        while (!copy.isEmpty()) result.add(copy.pop());
        return result;
    }

    private int priority(int i) { return heap.get(i).employee.getPriority(); }

    private void swap(int i, int j) {
        Node tmp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, tmp);
        indexMap.put(heap.get(i).employee.getName(), i);
        indexMap.put(heap.get(j).employee.getName(), j);
    }

    private void siftUp(int i) {
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (priority(i) > priority(parent)) { swap(i, parent); i = parent; }
            else break;
        }
    }

    private void siftDown(int i) {
        int n = heap.size();
        while (true) {
            int left = 2 * i + 1, right = 2 * i + 2, largest = i;
            if (left  < n && priority(left)  > priority(largest)) largest = left;
            if (right < n && priority(right) > priority(largest)) largest = right;
            if (largest == i) break;
            swap(i, largest);
            i = largest;
        }
    }
}