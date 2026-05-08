// Represents an employee in the library system
public class Employee {

    private String name;
    private int waitingTime; // total days waited for books
    private int retainingTime; // total days books were held

    public Employee(String name) {
        this.name = name;
        this.waitingTime = 0;
        this.retainingTime = 0;
    }

    public String getName() {
        return name;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public int getRetainingTime() {
        return retainingTime;
    }

    // Priority = waitingTime - retainingTime (higher = gets book sooner)
    public int getPriority() {
        return waitingTime - retainingTime;
    }

    public void addWaitingTime(int days) {
        if (days > 0)
            waitingTime += days;
    }

    public void addRetainingTime(int days) {
        if (days > 0)
            retainingTime += days;
    }

    @Override
    public String toString() {
        return String.format("Employee{name='%s', waiting=%d, retaining=%d, priority=%d}",
                name, waitingTime, retainingTime, getPriority());
    }
}