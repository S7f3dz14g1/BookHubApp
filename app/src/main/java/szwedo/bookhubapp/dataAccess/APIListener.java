package szwedo.bookhubapp.dataAccess;

import java.util.List;

import szwedo.bookhubapp.exception.ApiError;
import szwedo.bookhubapp.models.BookModel;
import szwedo.bookhubapp.models.CopiesOfBookModel;
import szwedo.bookhubapp.models.HistoryBookModel;
import szwedo.bookhubapp.models.ReservationBookModel;
import szwedo.bookhubapp.models.UserBookDetails;
import szwedo.bookhubapp.models.UserModel;
import szwedo.bookhubapp.utils.PageHolder;


public interface APIListener {
    void onBookListReceive(PageHolder<BookModel> page);

    void onErrorReceive(ApiError error);

    void onBookReceive(BookModel book);

    void onAvailableBook(Integer available);

    void onLoginSuccesses();

    void onRefreshServer();

    void onRegistrationSuccesses();

    void onUserDetailsReceive(UserModel user);

    void onCopiesOfBookReceive(List<CopiesOfBookModel> book);

    void onDiscoverBookListReceive(PageHolder<BookModel> page);

    void onUserBooksDetailsReceive(UserBookDetails details);

    void onEditUserReceive();

    void onHistoryBooksReceive(PageHolder<HistoryBookModel> books);

    void onReserveBook();

    void onCancelReservationReceive();

    void onCurrentBooksReceive(PageHolder<HistoryBookModel> books);

    void onReservationBooksReceive(PageHolder<ReservationBookModel> books);

    void onExtendBookRentalReceive();

    void onIncreaseLimitReceive();

    void isUserBanned();

    void isUserNoBanned();

    void onLoginBanedResult();
}
