package szwedo.bookhubapp.ui.fragments.bookDetails;

import android.net.Uri;

public interface BookDetailsFragmentContract {
    interface View{

        void setTitle(String title);

        void setAuthor(String author);

        void setPublisher(String publisher);

        void setPages(String pages);

        void setImage(Uri uriImage);

        void setDate(String date);

        void setAvailability(String status);

        void setStars(double number);

        void startProgressBar();

        void endProgressBar();

        void openNoInternetDialog();

        void setNumberOfStars(String number);

        void onRefresh();
    }

    interface Presenter {

        void setBook();
    }
}
