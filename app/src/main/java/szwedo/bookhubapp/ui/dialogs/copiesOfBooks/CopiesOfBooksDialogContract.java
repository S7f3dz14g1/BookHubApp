package szwedo.bookhubapp.ui.dialogs.copiesOfBooks;

import java.util.List;

import szwedo.bookhubapp.models.CopiesOfBookModel;

interface CopiesOfBooksDialogContract {

    interface Presenter {

        void reserveBook(long idBook);

        void increaseLimit(String decryption);

    }

    interface View {

        void setList(List<CopiesOfBookModel> books);

        void showSuccessReservationBookToast();

        void startProgressBar();

        void endProgressBar();

        void openOnInternetDialog();

        void openInformDialog();

        void showToast(String message);

        void showStopBorrowDialog();

        void onRefresh();

        void openIncreaseTheLimitDialog();
    }
}
