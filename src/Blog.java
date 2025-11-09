package src;

public class Blog {
    private String title;
    private String author;
    private String category;
    private String content;

    public Blog(String title, String author, String category, String content) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.content = content;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getCategory() { return category; }
    public String getContent() { return content; }
}
