package szwedo.bookhubapp.models;

import java.util.Date;

public class BookModel {

    private final int id;
    private final String title;
    private final String authors;
    private final double rating;
    private final double popularity;
    private final int pages;
    private final Date year;
    private final String publisher;
    private final String imageUrl;

    public BookModel(int id, String title, String authors, double rating, double popularity, int pages, Date year, String publisher,String imageUrl) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.rating = rating;
        this.popularity = popularity;
        this.pages = pages;
        this.year = year;
        this.publisher = publisher;
        this.imageUrl=imageUrl;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        return authors;
    }

    public double getRating() {
        return rating;
    }

    public double getPopularity() {
        return popularity;
    }

    public int getPages() {
        return pages;
    }

    public Date getYear() {
        return year;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
