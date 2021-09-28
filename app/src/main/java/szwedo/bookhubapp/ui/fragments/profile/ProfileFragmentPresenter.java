package szwedo.bookhubapp.ui.fragments.profile;

import android.os.Handler;

import szwedo.bookhubapp.R;
import szwedo.bookhubapp.dataAccess.APIAdapter;
import szwedo.bookhubapp.dataAccess.InternetConnection;
import szwedo.bookhubapp.dataAccess.LibraryAccess;
import szwedo.bookhubapp.dataAccess.local.LocalDataAccess;
import szwedo.bookhubapp.exception.ApiError;
import szwedo.bookhubapp.models.UserBookDetails;
import szwedo.bookhubapp.models.UserModel;
import szwedo.bookhubapp.ui.activity.main.MainActivity;

public class ProfileFragmentPresenter extends APIAdapter implements ProfileFragmentContact.Presenter {

    private ProfileFragmentContact.view view;
    private LibraryAccess api;

    public ProfileFragmentPresenter(ProfileFragmentContact.view view) {
        this.view = view;
        api = LibraryAccess.getInstance();
        api.setListener(this);
    }

    @Override
    public void onUserBooksDetailsReceive(UserBookDetails details) {
        view.setReadBooks(details.getTotalBooks() + "");
        view.setCurrentBooks(details.getCurrentBooks() + "");
        view.endProgressBar();
    }

    @Override
    public void onUserDetailsReceive(UserModel user) {
        view.setNick(user.getUsername());
        if (user.getPhone() != null) {
            view.setAddress(user.getAddress());
            view.setFirstName(user.getFirstName());
            view.setLastName(user.getLastName());
            view.setPhone(user.getPhone());
            view.endProgressBar();
        } else {
            view.setVisibilityButton();
            view.setInvisibilityComponents();
        }
    }

    @Override
    public void setUserDetails() {
        if (InternetConnection.isConnection(MainActivity.getAppContext())) {
            view.startProgressBar();
            api.getUserDetails(LocalDataAccess.getToken());
            api.getUserBooksDetails(LocalDataAccess.getToken());
        } else {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    view.openOnInternetDialog();
                }
            }, 5000);
        }
    }

    @Override
    public void logoutUser() {
        LocalDataAccess.clean();
        view.openMainActivity();
        view.showToast(MainActivity.getAppContext().getString(R.string.you_have_been_logged_out));
    }

    @Override
    public void increaseLimit(String decryption) {
        api.increaseLimit(LocalDataAccess.getToken(),decryption);
    }

    @Override
    public void onErrorReceive(ApiError error) {
        System.out.println(error.getStatus());
    }

    @Override
    public void onIncreaseLimitReceive() {
        view.showToast(MainActivity.getAppContext().getString(R.string.message_was_sent));
    }

    @Override
    public void isUserBanned() {
        view.showUserBanedDialog();
    }

    @Override
    public void onRefreshServer() {
        view.onRefresh();
    }
}
