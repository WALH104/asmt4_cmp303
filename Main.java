public class Main {

    public static void main(String[] args) {

        Library library = new Library();

        library.addBook("Software Engineering");
        library.addBook("Chemistry");
        library.addEmployee("Adam");
        library.addEmployee("Sam");
        library.addEmployee("Ann");

        System.out.println();

        library.circulateBook("Chemistry", new Date(2015, 3, 1, Date.DateFormat.US));
        library.circulateBook("Software Engineering", new Date(2015, 4, 1, Date.DateFormat.US));

        System.out.println();

        library.passOn("Chemistry", new Date(2015, 3, 5, Date.DateFormat.US));
        library.passOn("Chemistry", new Date(2015, 3, 7, Date.DateFormat.US));
        library.passOn("Chemistry", new Date(2015, 3, 15, Date.DateFormat.US));

        System.out.println();

        library.passOn("Software Engineering", new Date(2015, 4, 5, Date.DateFormat.US));
        library.passOn("Software Engineering", new Date(2015, 4, 10, Date.DateFormat.US));
        library.passOn("Software Engineering", new Date(2015, 4, 15, Date.DateFormat.US));

        System.out.println();

        library.printStatus();
    }
}