package szwedo.bookhubapp.ui.activity.login;

import android.os.Handler;

import szwedo.bookhubapp.R;
import szwedo.bookhubapp.dataAccess.APIAdapter;
import szwedo.bookhubapp.dataAccess.InternetConnection;
import szwedo.bookhubapp.dataAccess.LibraryAccess;
import szwedo.bookhubapp.dataAccess.local.LocalDataAccess;
import szwedo.bookhubapp.exception.ApiError;
import szwedo.bookhubapp.models.LoginApiUserModel;
import szwedo.bookhubapp.models.LoginUserModel;
import szwedo.bookhubapp.ui.activity.main.MainActivity;
import szwedo.bookhubapp.utils.LoginStatus;

class LoginActivityPresenter extends APIAdapter implements LoginActivityContract.Presenter {

    private final LoginActivityContract.View view;
    private final LibraryAccess api;
    private LoginUserModel user;

    public LoginActivityPresenter(LoginActivityContract.View view) {
        this.view = view;
        api = LibraryAccess.getInstance();
        api.setListener(this);
    }

    @Override
    public void onLoginClicked(LoginUserModel user) {
        view.startProgressBar();
        LoginStatus status = LoginActivityUtils.validate(user);
        if (status.equals(LoginStatus.CORRECT)) {
            this.user = user;
            if (InternetConnection.isConnection(MainActivity.getAppContext())) {
                api.isUserBanned(LocalDataAccess.getToken());
            } else {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view.openOnInternetDialog();
                    }
                }, 5000);
            }
        } else {
            showMessage(status);
            view.endProgressBar();
        }
    }

    private void showMessage(LoginStatus status) {
        if (status.equals(LoginStatus.EMPTY_NICK)) {
            view.errorEmptyLogin();
        } else if (status.equals(LoginStatus.EMPTY_PASSWORD)) {
            view.errorEmptyPassword();
        } else if (status.equals(LoginStatus.INCORRECT_NICK) ||
                status.equals(LoginStatus.INCORRECT_PASSWORD)) {
            view.showToast(MainActivity.getAppContext().getString(R.string.incorrect_data));
        }
    }

    @Override
    public void onLoginBanedResult() {
        view.openUserBanedDialog();
        view.endProgressBar();
    }

    @Override
    public void isUserNoBanned() {
        LoginStatus status = LoginActivityUtils.validate(user);
        if (status.equals(LoginStatus.CORRECT))
            api.getAuthorization(new LoginApiUserModel(user.getNick(), user.getPassword()));
        else {
            showMessage(status);
            view.endProgressBar();
        }
    }

    @Override
    public void onLoginSuccesses() {
        view.endProgressBar();
        view.openMainActivity();
        view.showToast(MainActivity.getAppContext().getString(R.string.login_successes));
    }

    @Override
    public void onRefreshServer() {
        view.onRefresh();
    }

    @Override
    public void onErrorReceive(ApiError error) {
        view.showToast(MainActivity.getAppContext().getString(R.string.incorrect_data));
        view.endProgressBar();
    }
}