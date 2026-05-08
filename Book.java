public class Book {

    private String name;
    private Date circulationStartDate;
    private Date circulationEndDate;
    private boolean archived;
    private Bookqueue queue;
    private Employee currentHolder;
    private Date holderReceiveDate;

    public Book(String name) {
        this.name = name;
        this.archived = false;
        this.queue = new Bookqueue();
    }

    public String getName() {
        return name;
    }

    public boolean isArchived() {
        return archived;
    }

    public Date getCirculationStartDate() {
        return circulationStartDate;
    }

    public Employee getCurrentHolder() {
        return currentHolder;
    }

    public Bookqueue getQueue() {
        return queue;
    }

    public void startCirculation(Date startDate) {
        circulationStartDate = startDate;
        currentHolder = queue.pop();
        holderReceiveDate = startDate;
    }

    public Employee passOn(Date passDate) {
        Employee outgoing = currentHolder;
        outgoing.addRetainingTime(holderReceiveDate.daysUntil(passDate));

        if (queue.isEmpty()) {
            archived = true;
            circulationEndDate = passDate;
            currentHolder = null;
            holderReceiveDate = null;
        } else {
            Employee incoming = queue.pop();
            incoming.addWaitingTime(circulationStartDate.daysUntil(passDate));
            currentHolder = incoming;
            holderReceiveDate = passDate;
        }
        return outgoing;
    }

    @Override
    public String toString() {
        String holder = currentHolder != null ? currentHolder.getName() : "none";
        return "Book: " + name + ", archived: " + archived + ", holder: " + holder;
    }
}