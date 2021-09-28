package szwedo.bookhubapp.ui.fragments.readingAndHistory.reading;

import android.os.Handler;

import szwedo.bookhubapp.dataAccess.APIAdapter;
import szwedo.bookhubapp.dataAccess.InternetConnection;
import szwedo.bookhubapp.dataAccess.LibraryAccess;
import szwedo.bookhubapp.dataAccess.local.LocalDataAccess;
import szwedo.bookhubapp.exception.ApiError;
import szwedo.bookhubapp.models.HistoryBookModel;
import szwedo.bookhubapp.ui.activity.main.MainActivity;
import szwedo.bookhubapp.utils.PageHolder;

public class ReadingFragmentPresenter extends APIAdapter implements ReadingFragmentContact.Presenter {

    private ReadingFragmentContact.View view;
    private LibraryAccess api;

    public ReadingFragmentPresenter(ReadingFragmentContact.View view) {
        this.view = view;
        this.api = LibraryAccess.getInstance();
        api.setListener(this);
    }

    @Override
    public void setList() {
        view.setDarkList();
        setReadingBookList();
    }

    @Override
    public void onExtendBookRentalClicked(int idBook) {
        api.getExternalBooksRental(LocalDataAccess.getToken(), idBook);
    }

    private void setReadingBookList() {
        view.startProgressBar();
        if (InternetConnection.isConnection(MainActivity.getAppContext())) {
            api.getCurrentBooks(10, 0, LocalDataAccess.getToken());
        } else {
            openNoInternetDialog();
        }
    }

    private void openNoInternetDialog() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.openOnInternetDialog();
            }
        }, 5000);
    }

    @Override
    public void onCurrentBooksReceive(PageHolder<HistoryBookModel> books) {
        if (books.getContent().size() != 0)
            view.setList(books.getContent());
        else
            view.setEmptyLayout();
        view.endProgressBar();
    }

    @Override
    public void onErrorReceive(ApiError error) {
        view.endProgressBar();
        if (error.getStatus() == 409)
            view.showFailureExtendBookRentalMessage();
    }

    @Override
    public void onExtendBookRentalReceive() {
        view.showSuccessExtendBookRentalMessage();
        view.endProgressBar();
        setReadingBookList();
    }

    @Override
    public void onRefreshServer() {
        view.onRefresh();
    }
}
