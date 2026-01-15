public class Article {
    private String link;
    private String headline;
    private String category;
    private String description;
    private String author;
    private String date;

    public Article(String author, String category, String date, String description, String headline, String link) {
        this.author = author;
        this.category = category;
        this.date = date;
        this.description = description;
        this.headline = headline;
        this.link = link;
    }

    @Override
    public String toString() {
        return headline + ", description" + description;
    }

    public String getLink() {
        return link;
    }

    public String getHeadline() {
        return headline;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
}