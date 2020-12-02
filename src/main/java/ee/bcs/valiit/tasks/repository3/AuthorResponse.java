package ee.bcs.valiit.tasks.repository3;

public class AuthorResponse {
    private int id;
    private String name;
    private int bookCount;

    public AuthorResponse(Author author) {
        this.id = author.getId();
        this.name = author.getName();
        this.bookCount = author.getBooks().size();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBookCount() {
        return bookCount;
    }

    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }
}
