package szwedo.bookhubapp.ui.activity.login;

import szwedo.bookhubapp.models.LoginUserModel;

interface LoginActivityContract {
    interface View {
        void showToast(String message);

        void startProgressBar();

        void endProgressBar();

        void openMainActivity();

        void openOnInternetDialog();

        void errorEmptyLogin();

        void errorEmptyPassword();

        void onRefresh();

        void openUserBanedDialog();
    }

    interface Presenter {
        void onLoginClicked(LoginUserModel user);
    }
}
