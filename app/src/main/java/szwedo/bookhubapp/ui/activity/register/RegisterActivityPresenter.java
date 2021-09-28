package szwedo.bookhubapp.ui.activity.register;

import android.content.Context;
import android.os.Handler;

import szwedo.bookhubapp.R;
import szwedo.bookhubapp.dataAccess.APIAdapter;
import szwedo.bookhubapp.dataAccess.InternetConnection;
import szwedo.bookhubapp.dataAccess.LibraryAccess;
import szwedo.bookhubapp.exception.ApiError;
import szwedo.bookhubapp.models.LoginApiUserModel;
import szwedo.bookhubapp.models.RegistrationApiUserModel;
import szwedo.bookhubapp.models.RegistrationUserModel;
import szwedo.bookhubapp.ui.activity.main.MainActivity;
import szwedo.bookhubapp.utils.RegistrationStatus;

class RegisterActivityPresenter extends APIAdapter implements RegisterActivityContract.Presenter {

    private final RegisterActivityContract.View view;
    private final LibraryAccess api;
    private final Context context;

    public RegisterActivityPresenter(RegisterActivityContract.View view, Context context) {
        this.view = view;
        api = LibraryAccess.getInstance();
        api.setListener(this);
        this.context = context;
    }

    @Override
    public void onRegisterClicked(RegistrationUserModel user) {
        RegistrationStatus status = RegistrationActivityUtils.validate(user);
        if (status.equals(RegistrationStatus.CORRECT)) {
            if (InternetConnection.isConnection(context)) {
                view.startProgressBar();
                api.getRegistration(new RegistrationApiUserModel(
                        user.getNick()
                        , user.getEmail()
                        , user.getPasswordFirst()
                ));
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
            showToast(status);
        }
    }

    private void showToast(RegistrationStatus status) {
        if (status.equals(RegistrationStatus.EMPTY_NICK)) {
            view.errorNickIsEmpty();
        } else if (status.equals(RegistrationStatus.EMPTY_EMAIL)) {
            view.errorEmailIsEmpty();
        } else if (status.equals(RegistrationStatus.EMPTY_PASSWORD)) {
            view.errorPasswordIsEmpty();
        } else if (status.equals(RegistrationStatus.EMPTY_REPEAT_PASSWORD)) {
            view.errorRepeatPasswordIsEmpty();
        } else if (status.equals(RegistrationStatus.INCORRECT_NICK)) {
            view.errorNickIncorrect();
        } else if (status.equals(RegistrationStatus.INCORRECT_EMAIL)) {
            view.errorEmailIncorrect();
        } else if (status.equals(RegistrationStatus.INCORRECT_PASSWORD)) {
            view.errorPasswordIncorrect();
        } else {
            view.errorRepeatPasswordAreNotIdentical();
        }
    }

    @Override
    public void loginUser(LoginApiUserModel model) {
        api.getAuthorization(model);
    }

    @Override
    public void onRegistrationSuccesses() {
        view.onSuccessRegistration();
    }

    @Override
    public void onLoginSuccesses() {
        view.endProgressBar();
        view.showToast(String.valueOf(MainActivity.getAppContext().getString(R.string.successful_registration)));
        view.openContinueDialog();
    }

    @Override
    public void onErrorReceive(ApiError error) {
        view.endProgressBar();
        if (error.getStatus() == 409)
            view.showToast(String.valueOf(MainActivity.getAppContext().getString(R.string.user_with_given_email_or_logiin_exist)));
    }

    @Override
    public void onRefreshServer() {
        view.onRefresh();
    }
}
