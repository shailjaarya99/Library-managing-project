
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Library {

    private static final String DB_FILE = "library_db.txt";
    private ArrayList<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.getTitle());
    }

    public void removeBook(int index) {
        if (index >= 0 && index < books.size()) {
            Book removed = books.remove(index);
            System.out.println("Removed: " + removed.getTitle());
        } else {
            System.out.println("Invalid index.");
        }
    }

    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in library.");
            return;
        }
        System.out.println("Library Books:");
        for (int i = 0; i < books.size(); i++) {
            Book b = books.get(i);
            String status = b.isBorrowed() ? "[Borrowed]" : "[Available]";
            System.out.println((i + 1) + ". " + b.getTitle() + " by " + b.getAuthor() + " (" + b.getGenre() + ") " + status);
        }
    }

    public void borrowBook(int index) {
        if (index >= 0 && index < books.size()) {
            Book b = books.get(index);
            if (b.isBorrowed()) {
                System.out.println("Already borrowed.");
            } else {
                b.setBorrowed(true);
                System.out.println("Borrowed: " + b.getTitle());
            }
        } else {
            System.out.println("Invalid index.");
        }
    }

    public void returnBook(int index) {
        if (index >= 0 && index < books.size()) {
            Book b = books.get(index);
            if (b.isBorrowed()) {
                b.setBorrowed(false);
                System.out.println("Returned: " + b.getTitle());
            } else {
                System.out.println("This book was not borrowed.");
            }
        } else {
            System.out.println("Invalid index.");
        }
    }

    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DB_FILE))) {
            for (Book b : books) {
                writer.write(b.getTitle() + "|" + b.getAuthor() + "|" + b.getGenre() + "|" + b.isBorrowed());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Could not save data.");
        }
    }

    public void loadFromFile() {
        File file = new File(DB_FILE);
        if (!file.exists()) {
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    Book b = new Book(parts[0], parts[1], parts[2]);
                    b.setBorrowed(Boolean.parseBoolean(parts[3]));
                    books.add(b);
                }
            }
        } catch (IOException e) {
            System.out.println("Could not load data.");
        }
    }
}