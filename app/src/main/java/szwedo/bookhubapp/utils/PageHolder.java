package szwedo.bookhubapp.utils;

import java.util.List;

public class PageHolder<T> {
    private boolean first;
    private boolean last;
    private int currentPage;
    private int totalPages;
    private int totalElements;
    private int numberOfElements;
    private List<T> content;

    public boolean isFirst() {
        return first;
    }

    public boolean isLast() {
        return last;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public List<T> getContent() {
        return content;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
