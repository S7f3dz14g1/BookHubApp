package szwedo.bookhubapp.dataAccess;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import szwedo.bookhubapp.models.BookModel;
import szwedo.bookhubapp.models.CopiesOfBookModel;
import szwedo.bookhubapp.models.EditUserModel;
import szwedo.bookhubapp.models.HistoryBookModel;
import szwedo.bookhubapp.models.LoginApiUserModel;
import szwedo.bookhubapp.models.RegistrationApiUserModel;
import szwedo.bookhubapp.models.ReservationBookModel;
import szwedo.bookhubapp.models.UserBookDetails;
import szwedo.bookhubapp.models.UserModel;
import szwedo.bookhubapp.utils.Direction;
import szwedo.bookhubapp.utils.PageHolder;
import szwedo.bookhubapp.utils.Sorting;

public interface HTTPMethods {
    @GET("api/library/books")
    Call<PageHolder<BookModel>> getBooks(@Query("limit") int limit,
                                         @Query("page") int page,
                                         @Query("sort") Sorting sort);

    @GET("api/library/books")
    Call<PageHolder<BookModel>> getBooks(@Query("limit") int limit,
                                         @Query("page") int page,
                                         @Query("sort") Sorting sort,
                                         @Query("order") Direction direction);

    @GET("api/library/books/id/{id}")
    Call<BookModel> getBookById(@Path("id") int bookId);

    @GET("/api/library/books/search")
    Call<PageHolder<BookModel>> getBooks(@Query("limit") int limit,
                                         @Query("page") int page,
                                         @Query("title") String title);

    @GET("/api/library/books/discover/{limit}")
    Call<PageHolder<BookModel>> getBooks(@Path("limit") int limit);

    @GET("/api/library/books/copies/available/{id}")
    Call<Integer> getAvailableBook(@Path("id") int id);

    @Headers("Content-Type: application/json")
    @POST("login")
    Call<Void> authorize(@Body LoginApiUserModel user);

    @Headers("Content-Type: application/json")
    @POST("register")
    Call<ResponseBody> registration(@Body RegistrationApiUserModel user);

    @GET("/api/library/users")
    Call<UserModel> getUser(@Header("Authorization") String token);

    @GET("/api/library/books/copies/{id}")
    Call<List<CopiesOfBookModel>> getCopiesOfBookById(@Path("id") int bookId);

    @GET("/api/library/users/bookDetails")
    Call<UserBookDetails> getUserBooksDetails(@Header("Authorization") String token);

    @PUT("/api/library/users/edit")
    Call<Integer> editUserData(@Header("Authorization") String token,
                               @Body EditUserModel user);

    @GET("/api/library/users/history")
    Call<PageHolder<HistoryBookModel>> getHistoryBooks(@Query("limit") int limit,
                                                       @Query("page") int page,
                                                       @Header("Authorization") String token);

    @GET("/api/library/users/reserve/{bookCopyId}")
    Call<Long> reserveBook(@Header("Authorization") String token,
                           @Path("bookCopyId") long bookCopyId);

    @GET("/api/library/users/reservations/cancel/{reservationId}")
    Call<Integer> cancelReservation(@Header("Authorization") String token,
                                    @Path("reservationId") int reservationId);

    @GET("/api/library/users/currentBooks")
    Call<PageHolder<HistoryBookModel>> getCurrentBooks(@Query("limit") int limit,
                                                       @Query("page") int page,
                                                       @Header("Authorization") String token);

    @GET("/api/library/users/reservations")
    Call<PageHolder<ReservationBookModel>> getReservationBooks(@Query("limit") int limit,
                                                               @Query("page") int page,
                                                               @Header("Authorization") String token);

    @GET("/api/library/users/currentBooks/extend/{borrowId}")
    Call<Void> getExternalBookRental(@Path("borrowId") int borrowId,
                                     @Header("Authorization") String token);

    @POST("/api/library/users/request/limit/{message}")
    Call<Long> increaseTheLimit(@Header("Authorization") String token,
                                @Path("message") String decryption);

    @GET("/api/library/users/isBanned/")
    Call<Boolean> isUserBanned(@Header("Authorization") String token);
}
