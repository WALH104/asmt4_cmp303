import java.util.ArrayList;

public class Library {

    private ArrayList<Book> books;
    private ArrayList<Employee> employees;

    public Library() {
        books = new ArrayList<>();
        employees = new ArrayList<>();
    }

    public void addBook(String name) {
        books.add(new Book(name));
        System.out.println("Added book: " + name);
    }

    public void addEmployee(String name) {
        employees.add(new Employee(name));
        System.out.println("Added employee: " + name);
    }

    public void circulateBook(String bookName, Date startDate) {
        Book book = findBook(bookName);
        for (Employee emp : employees)
            book.getQueue().push(emp);
        book.startCirculation(startDate);
        System.out.println("[" + startDate + "] Circulation started for " + bookName + ". First holder: "
                + book.getCurrentHolder().getName());
    }

    public void passOn(String bookName, Date passDate) {
        Book book = findBook(bookName);
        Employee outgoing = book.getCurrentHolder();
        Employee incoming = book.getQueue().isEmpty() ? null : book.getQueue().peek();

        book.passOn(passDate);

        if (book.isArchived()) {
            System.out.println(
                    "[" + passDate + "] " + bookName + " returned by " + outgoing.getName() + " and ARCHIVED.");
        } else {
            System.out.println("[" + passDate + "] " + bookName + " passed from " + outgoing.getName() + " to "
                    + book.getCurrentHolder().getName());
        }

        // update other queues since priorities changed
        for (Book other : books) {
            if (other == book || other.isArchived() || other.getCirculationStartDate() == null)
                continue;
            if (outgoing != null && other.getQueue().contains(outgoing))
                other.getQueue().update(outgoing);
            if (incoming != null && other.getQueue().contains(incoming))
                other.getQueue().update(incoming);
        }
    }

    public void printStatus() {
        System.out.println("Books:");
        for (Book b : books)
            System.out.println("  " + b);
        System.out.println("Employees:");
        for (Employee e : employees)
            System.out.println("  " + e);
    }

    private Book findBook(String name) {
        for (Book b : books)
            if (b.getName().equals(name))
                return b;
        throw new IllegalArgumentException("Book not found: " + name);
    }
}