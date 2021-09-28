package szwedo.bookhubapp.models;

import java.util.Date;

public class HistoryBookModel {

    private final long id;
    private final String userId;
    private final long bookId;
    private final long bookCopyId;
    private final String title;
    private final String imageUrl;
    private final Date dateIssued;
    private final Date expectedDate;
    private final Date dateReturn;

    public HistoryBookModel(long id, String userId, long bookId, long bookCopyId, String title, String imageUrl, Date dateIssued, Date expectedDate, Date dateReturn) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.bookCopyId = bookCopyId;
        this.title = title;
        this.imageUrl = imageUrl;
        this.dateIssued = dateIssued;
        this.expectedDate = expectedDate;
        this.dateReturn = dateReturn;
    }

    public Date getExpectedDate() {
        return expectedDate;
    }

    public long getBookCopyId() {
        return bookCopyId;
    }

    public long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public long getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Date getDateIssued() {
        return dateIssued;
    }

    public Date getDateReturn() {
        return dateReturn;
    }
}