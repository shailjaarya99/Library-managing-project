
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        library.loadFromFile();

        while (true) {
            System.out.println("\nLibrary Menu:");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. View All Books");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            String input = scanner.nextLine().trim();
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number from 1 to 6.");
                continue;
            }

            if (choice == 6) {
                System.out.println("Exiting library. Goodbye!");
                break;
            }

            switch (choice) {
                case 1 -> {
                    System.out.print("Title: ");
                    String title = scanner.nextLine().trim();
                    System.out.print("Author: ");
                    String author = scanner.nextLine().trim();
                    System.out.print("Genre: ");
                    String genre = scanner.nextLine().trim();
                    if (title.isEmpty() || author.isEmpty() || genre.isEmpty()) {
                        System.out.println("All fields are required.");
                    } else {
                        library.addBook(new Book(title, author, genre));
                        library.saveToFile();
                    }
                }
                case 2 -> {
                    library.displayBooks();
                    System.out.print("Enter book number to remove: ");
                    int idx = readInt(scanner) - 1;
                    library.removeBook(idx);
                    library.saveToFile();
                }
                case 3 ->
                    library.displayBooks();
                case 4 -> {
                    library.displayBooks();
                    System.out.print("Enter book number to borrow: ");
                    int idx = readInt(scanner) - 1;
                    library.borrowBook(idx);
                    library.saveToFile();
                }
                case 5 -> {
                    library.displayBooks();
                    System.out.print("Enter book number to return: ");
                    int idx = readInt(scanner) - 1;
                    library.returnBook(idx);
                    library.saveToFile();
                }
                default ->
                    System.out.println("Invalid selection.");
            }
        }

        scanner.close();
    }

    private static int readInt(Scanner scanner) {
        String line = scanner.nextLine().trim();
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}