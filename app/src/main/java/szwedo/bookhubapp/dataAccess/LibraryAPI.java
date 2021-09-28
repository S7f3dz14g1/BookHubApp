package szwedo.bookhubapp.dataAccess;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import szwedo.bookhubapp.dataAccess.local.LocalDataAccess;
import szwedo.bookhubapp.exception.ApiError;
import szwedo.bookhubapp.exception.ApiErrorParser;
import szwedo.bookhubapp.exception.ApiExceptionHandler;
import szwedo.bookhubapp.models.BookModel;
import szwedo.bookhubapp.models.CopiesOfBookModel;
import szwedo.bookhubapp.models.HistoryBookModel;
import szwedo.bookhubapp.models.ReservationBookModel;
import szwedo.bookhubapp.models.UserBookDetails;
import szwedo.bookhubapp.models.UserModel;
import szwedo.bookhubapp.utils.PageHolder;

abstract class LibraryAPI {
    protected APIListener listener;

    public void setListener(APIListener listener) {
        this.listener = listener;
    }


    protected Callback<PageHolder<BookModel>> callbackForBooksList = new Callback<PageHolder<BookModel>>() {
        @Override
        public void onResponse(Call<PageHolder<BookModel>> call, Response<PageHolder<BookModel>> response) {
            if (response.isSuccessful()) {
                PageHolder<BookModel> page = response.body();
                listener.onBookListReceive(page);
            } else {
                ApiError apiError = ApiErrorParser.parseError(response);
                listener.onErrorReceive(apiError);
            }
        }

        @Override
        public void onFailure(Call<PageHolder<BookModel>> call, Throwable t) {
            listener.onRefreshServer();
        }
    };

    protected Callback<PageHolder<BookModel>> callbackForDiscoverBooksList = new Callback<PageHolder<BookModel>>() {
        @Override
        public void onResponse(Call<PageHolder<BookModel>> call, Response<PageHolder<BookModel>> response) {
            if (response.isSuccessful()) {
                PageHolder<BookModel> page = response.body();
                listener.onDiscoverBookListReceive(page);
            } else {
                ApiError apiError = ApiErrorParser.parseError(response);
                listener.onErrorReceive(apiError);
            }
        }

        @Override
        public void onFailure(Call<PageHolder<BookModel>> call, Throwable t) {
            listener.onRefreshServer();
        }
    };

    protected Callback<BookModel> callbackForBook = new Callback<BookModel>() {
        @Override
        public void onResponse(Call<BookModel> call, Response<BookModel> response) {
            if (response.isSuccessful()) {
                BookModel book = response.body();
                listener.onBookReceive(book);
            } else {
                ApiError apiError = ApiErrorParser.parseError(response);
                listener.onErrorReceive(apiError);
            }
        }

        @Override
        public void onFailure(Call<BookModel> call, Throwable t) {
            listener.onRefreshServer();
        }
    };

    protected Callback<Integer> callbackForAvailableBook = new Callback<Integer>() {
        @Override
        public void onResponse(Call<Integer> call, Response<Integer> response) {
            if (response.isSuccessful()) {
                Integer available = response.body();
                listener.onAvailableBook(available);
            } else {
                ApiError apiError = ApiErrorParser.parseError(response);
                listener.onErrorReceive(apiError);
            }
        }

        @Override
        public void onFailure(Call<Integer> call, Throwable t) {
            listener.onRefreshServer();
        }
    };

    protected Callback<Void> callbackForLoginAuthorization = new Callback<Void>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            if (response.isSuccessful()) {
                String token = response.headers().get("Authorization");
                LibraryAccess.getInstance().setToken(token);
                LocalDataAccess.setToken(token);
                LocalDataAccess.setLogin(true);
                listener.onLoginSuccesses();
            } else {
                ApiError apiError = ApiErrorParser.parseError(response);
                listener.onErrorReceive(apiError);
            }
        }

        @Override
        public void onFailure(Call<Void> call, Throwable t) {
            listener.onRefreshServer();
        }
    };

    protected Callback<ResponseBody> callbackForRegistration = new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            if (response.isSuccessful()) {
                listener.onRegistrationSuccesses();
            } else {
                ApiError apiError = ApiErrorParser.parseError(response);
                listener.onErrorReceive(apiError);
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            listener.onRefreshServer();
        }
    };

    protected Callback<UserModel> callbackForUserDetails = new Callback<UserModel>() {
        @Override
        public void onResponse(Call<UserModel> call, Response<UserModel> response) {
            if (response.isSuccessful()) {
                UserModel userModel = response.body();
                listener.onUserDetailsReceive(userModel);
            } else {
                ApiError apiError = ApiErrorParser.parseError(response);
                ApiExceptionHandler.handle(apiError);
                listener.onErrorReceive(apiError);
            }
        }

        @Override
        public void onFailure(Call<UserModel> call, Throwable t) {
            listener.onRefreshServer();
        }
    };

    protected Callback<List<CopiesOfBookModel>> callbackForCopiesOfBook = new Callback<List<CopiesOfBookModel>>() {
        @Override
        public void onResponse(Call<List<CopiesOfBookModel>> call, Response<List<CopiesOfBookModel>> response) {
            if (response.isSuccessful()) {
                listener.onCopiesOfBookReceive(response.body());
            } else {
                ApiError apiError = ApiErrorParser.parseError(response);
                listener.onErrorReceive(apiError);
            }
        }

        @Override
        public void onFailure(Call<List<CopiesOfBookModel>> call, Throwable t) {
            listener.onRefreshServer();
        }
    };

    protected Callback<UserBookDetails> callbackForUserBooksDetails = new Callback<UserBookDetails>() {
        @Override
        public void onResponse(Call<UserBookDetails> call, Response<UserBookDetails> response) {
            if (response.isSuccessful()) {
                listener.onUserBooksDetailsReceive(response.body());
            } else {
                ApiError apiError = ApiErrorParser.parseError(response);
                listener.onErrorReceive(apiError);
            }
        }

        @Override
        public void onFailure(Call<UserBookDetails> call, Throwable t) {
            listener.onRefreshServer();
        }
    };

    protected Callback<Integer> callbackForEditUserData = new Callback<Integer>() {
        @Override
        public void onResponse(Call<Integer> call, Response<Integer> response) {
            if (response.isSuccessful()) {
                listener.onEditUserReceive();
            } else {
                ApiError apiError = ApiErrorParser.parseError(response);
                listener.onErrorReceive(apiError);
            }
        }

        @Override
        public void onFailure(Call<Integer> call, Throwable t) {
            listener.onRefreshServer();
        }
    };

    protected Callback<PageHolder<HistoryBookModel>> callbackForHistoryOfBook = new Callback<PageHolder<HistoryBookModel>>() {
        @Override
        public void onResponse(Call<PageHolder<HistoryBookModel>> call, Response<PageHolder<HistoryBookModel>> response) {
            if (response.isSuccessful()) {
                listener.onHistoryBooksReceive(response.body());
            } else {
                ApiError apiError = ApiErrorParser.parseError(response);
                ApiExceptionHandler.handle(apiError);
                listener.onErrorReceive(apiError);
            }
        }

        @Override
        public void onFailure(Call<PageHolder<HistoryBookModel>> call, Throwable t) {
            listener.onRefreshServer();
        }
    };

    protected Callback<Long> callbackForReserveBook = new Callback<Long>() {
        @Override
        public void onResponse(Call<Long> call, Response<Long> response) {
            if (response.isSuccessful()) {
                listener.onReserveBook();
            } else {
                ApiError apiError = ApiErrorParser.parseError(response);
                listener.onErrorReceive(apiError);
            }
        }

        @Override
        public void onFailure(Call<Long> call, Throwable t) {
            listener.onRefreshServer();
        }
    };

    protected Callback<Integer> callbackForCancelReservation = new Callback<Integer>() {
        @Override
        public void onResponse(Call<Integer> call, Response<Integer> response) {
            if (response.isSuccessful()) {
                listener.onCancelReservationReceive();
            } else {
                ApiError apiError = ApiErrorParser.parseError(response);
                listener.onErrorReceive(apiError);
            }
        }

        @Override
        public void onFailure(Call<Integer> call, Throwable t) {
            listener.onRefreshServer();
        }
    };

    protected Callback<PageHolder<HistoryBookModel>> callbackForCurrentOfBook = new Callback<PageHolder<HistoryBookModel>>() {
        @Override
        public void onResponse(Call<PageHolder<HistoryBookModel>> call, Response<PageHolder<HistoryBookModel>> response) {
            if (response.isSuccessful()) {
                listener.onCurrentBooksReceive(response.body());
            } else {
                ApiError apiError = ApiErrorParser.parseError(response);
                ApiExceptionHandler.handle(apiError);
                listener.onErrorReceive(apiError);
            }
        }

        @Override
        public void onFailure(Call<PageHolder<HistoryBookModel>> call, Throwable t) {
            listener.onRefreshServer();
        }
    };

    protected Callback<PageHolder<ReservationBookModel>> callbackForReservationOfBook = new Callback<PageHolder<ReservationBookModel>>() {
        @Override
        public void onResponse(Call<PageHolder<ReservationBookModel>> call, Response<PageHolder<ReservationBookModel>> response) {
            if (response.isSuccessful()) {
                listener.onReservationBooksReceive(response.body());
            } else {
                ApiError apiError = ApiErrorParser.parseError(response);
                ApiExceptionHandler.handle(apiError);
                listener.onErrorReceive(apiError);
            }
        }

        @Override
        public void onFailure(Call<PageHolder<ReservationBookModel>> call, Throwable t) {
            listener.onRefreshServer();
        }
    };

    protected Callback<Void> callbackExternalBooksRental = new Callback<Void>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            if (response.isSuccessful()) {
                listener.onExtendBookRentalReceive();
            } else {
                ApiError apiError = ApiErrorParser.parseError(response);
                ApiExceptionHandler.handle(apiError);
                listener.onErrorReceive(apiError);
            }
        }

        @Override
        public void onFailure(Call<Void> call, Throwable t) {
            listener.onRefreshServer();
        }
    };

    protected Callback<Long> callbackForIncreaseLimit = new Callback<Long>() {
        @Override
        public void onResponse(Call<Long> call, Response<Long> response) {
            if (response.isSuccessful()) {
                listener.onIncreaseLimitReceive();
            } else {
                ApiError apiError = ApiErrorParser.parseError(response);
                ApiExceptionHandler.handle(apiError);
                listener.onErrorReceive(apiError);
            }
        }

        @Override
        public void onFailure(Call<Long> call, Throwable t) {
            listener.onRefreshServer();
        }
    };

    protected Callback<Boolean> callbackForIsUserBanned = new Callback<Boolean>() {
        @Override
        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
            if (response.body() == null||!response.body()) {
                listener.isUserNoBanned();
            } else {
                listener.isUserBanned();
                listener.onLoginBanedResult();
                LocalDataAccess.clean();
            }
        }

        @Override
        public void onFailure(Call<Boolean> call, Throwable t) {
            listener.onRefreshServer();
        }
    };
}