package szwedo.bookhubapp.models;

import java.util.Date;

public class CopiesOfBookModel {

    private final long id;
    private final long bookId;
    private final boolean borrow;
    private final boolean access;
    private final String code;
    private final Date approximateDate;

    public CopiesOfBookModel(long id, long bookId, boolean borrow, boolean access, String code, Date approximateDate) {
        this.id = id;
        this.bookId = bookId;
        this.borrow = borrow;
        this.access = access;
        this.code = code;
        this.approximateDate = approximateDate;
    }

    public long getId() {
        return id;
    }

    public long getBookId() {
        return bookId;
    }

    public boolean isBorrow() {
        return borrow;
    }

    public boolean isAccess() {
        return access;
    }

    public String getCode() {
        return code;
    }

    public Date getApproximateDate() {
        return approximateDate;
    }
}
