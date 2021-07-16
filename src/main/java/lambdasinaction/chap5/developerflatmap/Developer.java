package lambdasinaction.chap5.developerflatmap;

import java.util.HashSet;
import java.util.Set;

public class Developer {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public Set<String> getBook() {
        return book;
    }

    private Set<String> book;

    //getters, setters, toString

    public void addBook(String book) {
        if (this.book == null) {
            this.book = new HashSet<>();
        }
        this.book.add(book);
    }
}
