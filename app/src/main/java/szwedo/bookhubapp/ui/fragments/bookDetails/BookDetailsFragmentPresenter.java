package szwedo.bookhubapp.ui.fragments.bookDetails;

import android.net.Uri;

import java.text.DecimalFormat;

import szwedo.bookhubapp.dataAccess.APIAdapter;
import szwedo.bookhubapp.dataAccess.InternetConnection;
import szwedo.bookhubapp.dataAccess.LibraryAccess;
import szwedo.bookhubapp.models.BookModel;
import szwedo.bookhubapp.ui.activity.main.MainActivity;
import szwedo.bookhubapp.utils.Helper;

public class BookDetailsFragmentPresenter extends APIAdapter implements BookDetailsFragmentContract.Presenter {

    private LibraryAccess api;
    private BookDetailsFragmentContract.View view;
    private int idBook;

    public BookDetailsFragmentPresenter(BookDetailsFragmentContract.View view) {
        this.view = view;
        api = LibraryAccess.getInstance();
        view.startProgressBar();
        api.setListener(this);
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public int getIdBook() {
        return idBook;
    }

    private void setBookDetails(BookModel book) {
        view.setTitle(book.getTitle());
        view.setAuthor(book.getAuthors());
        view.setPublisher(book.getPublisher());
        view.setPages(book.getPages() + "");
        view.setStars(book.getRating());
        view.setDate(Helper.getDefaultDateFormat(book.getYear()));
        view.setImage(Uri.parse(book.getImageUrl()));
        view.setNumberOfStars(getStringRating(book.getRating()));
        view.endProgressBar();
    }

    private String getStringRating(double rating) {
        DecimalFormat format = new DecimalFormat("0.00");
        return format.format(rating) + "/5.0";
    }

    @Override
    public void setBook() {
        if (InternetConnection.isConnection(MainActivity.getAppContext())) {
            api.getBookById(idBook);
            api.getAvailableBookNumber(idBook);
       } else
            view.openNoInternetDialog();
    }

    @Override
    public void onBookReceive(BookModel book) {
        setBookDetails(book);
    }

    @Override
    public void onAvailableBook(Integer available) {
        view.setAvailability(available.toString());
    }

    @Override
    public void onReserveBook() {
        view.onRefresh();
    }
}
