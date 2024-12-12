package entity;

public class Book {
    private String title;
    private String author;
    private boolean isAvailable;

    public Book() {
    }

    public Book(String title, String author, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.isAvailable = isAvailable;
    }
    
    public void borrow(){
        // 책 빌리는 작업
        isAvailable = false;
    }
    
    public void returnBook(){
        // 책 반납하는 작업
        isAvailable = true;
    }
    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
