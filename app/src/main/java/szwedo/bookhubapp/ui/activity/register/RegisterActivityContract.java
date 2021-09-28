package szwedo.bookhubapp.ui.activity.register;

import szwedo.bookhubapp.models.LoginApiUserModel;
import szwedo.bookhubapp.models.RegistrationUserModel;

interface RegisterActivityContract {
    interface View {
        void showToast(String message);

        void startProgressBar();

        void endProgressBar();

        void openContinueDialog();

        void errorNickIncorrect();

        void errorNickIsEmpty();

        void errorEmailIncorrect();

        void errorEmailIsEmpty();

        void errorPasswordIsEmpty();

        void errorPasswordIncorrect();

        void errorRepeatPasswordAreNotIdentical();

        void openOnInternetDialog();

        void onSuccessRegistration();

        void onRefresh();

        void errorRepeatPasswordIsEmpty();
    }

    interface Presenter {
        void onRegisterClicked(RegistrationUserModel user);

        void loginUser(LoginApiUserModel model);
    }
}
