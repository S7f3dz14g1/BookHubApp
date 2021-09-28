package szwedo.bookhubapp.dataAccess;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import szwedo.bookhubapp.models.EditUserModel;
import szwedo.bookhubapp.models.LoginApiUserModel;
import szwedo.bookhubapp.models.RegistrationApiUserModel;
import szwedo.bookhubapp.utils.Direction;
import szwedo.bookhubapp.utils.Sorting;

public final class LibraryAccess extends LibraryAPI {
    private HTTPMethods HTTPMethods;
    private static LibraryAccess instance;
    private final Retrofit retrofit;
    private String token;

    private LibraryAccess() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://puz-biblioteka.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HTTPMethods = retrofit.create(HTTPMethods.class);
        instance = this;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static LibraryAccess getInstance() {
        if (instance == null) return new LibraryAccess();
        else return instance;
    }

    public void getBooks(int limit, int page, Sorting sort) {
        HTTPMethods.getBooks(limit, page, sort)
                .enqueue(callbackForBooksList);
    }

    public void getBooks(int limit, int page, Sorting sort, Direction direction) {
        HTTPMethods.getBooks(limit, page, sort, direction)
                .enqueue(callbackForBooksList);
    }

    public void getBookById(int bookId) {
        HTTPMethods.getBookById(bookId)
                .enqueue(callbackForBook);
    }

    public void getSearchBooks(int limit, int page, String title) {
        HTTPMethods.getBooks(limit, page, title)
                .enqueue(callbackForBooksList);
    }

    public void getDiscoverBooks(int limit) {
        HTTPMethods.getBooks(limit)
                .enqueue(callbackForDiscoverBooksList);
    }

    public void getAvailableBookNumber(int bookId) {
        HTTPMethods.getAvailableBook(bookId)
                .enqueue(callbackForAvailableBook);
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public void getAuthorization(LoginApiUserModel user) {
        HTTPMethods.authorize(user)
                .enqueue(callbackForLoginAuthorization);
    }

    public void getRegistration(RegistrationApiUserModel user) {
        HTTPMethods.registration(user)
                .enqueue(callbackForRegistration);
    }

    public void getUserDetails(String token) {
        HTTPMethods.getUser(token)
                .enqueue(callbackForUserDetails);
    }

    public void getCopiesOfBook(int idBook) {
        HTTPMethods.getCopiesOfBookById(idBook)
                .enqueue(callbackForCopiesOfBook);
    }

    public void getUserBooksDetails(String token) {
        HTTPMethods.getUserBooksDetails(token)
                .enqueue(callbackForUserBooksDetails);
    }

    public void editUserData(String token, EditUserModel user) {
        HTTPMethods.editUserData(token, user)
                .enqueue(callbackForEditUserData);
    }

    public void getHistoryBooks(int limit, int page, String token) {
        HTTPMethods.getHistoryBooks(limit, page, token)
                .enqueue(callbackForHistoryOfBook);
    }

    public void reserveBook(String token, long idBook) {
        HTTPMethods.reserveBook(token, idBook)
                .enqueue(callbackForReserveBook);
    }

    public void cancelReservation(String token, int idBook) {
        HTTPMethods.cancelReservation(token, idBook)
                .enqueue(callbackForCancelReservation);
    }

    public void getCurrentBooks(int limit, int page, String token) {
        HTTPMethods.getCurrentBooks(limit, page, token)
                .enqueue(callbackForCurrentOfBook);
    }

    public void getReservationBooks(int limit, int page, String token) {
        HTTPMethods.getReservationBooks(limit, page, token)
                .enqueue(callbackForReservationOfBook);
    }

    public void getExternalBooksRental(String token, int idBook) {
        HTTPMethods.getExternalBookRental(idBook, token)
                .enqueue(callbackExternalBooksRental);
    }

    public void increaseLimit(String token, String decryption) {
        HTTPMethods.increaseTheLimit(token, decryption)
                .enqueue(callbackForIncreaseLimit);
    }

    public void isUserBanned(String token) {
        HTTPMethods.isUserBanned(token)
                .enqueue(callbackForIsUserBanned);
    }
}
