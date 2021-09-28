package szwedo.bookhubapp.ui.fragments.editProfile;

import szwedo.bookhubapp.models.EditUserModel;

interface EditProfileFragmentContract {
    interface View {
        void showToast(String message);

        void startProgressBar();

        void endProgressBar();

        void errorEmailIncorrect();

        void errorEmptyEmail();

        void errorEmptyPassword();

        void errorEmptyRepeatPassword();

        void errorPasswordIncorrect();

        void errorRepeatPasswordAreNotIdentical();

        void openNoInternetDialog();

        void setEmail(String email);

        String getEmail();

        String getPassword();

        String getRepeatPassword();

        boolean isCheckedBoxEditPassword();

        void openDialog();

        void setPhone(String phone);

        void setAddress(String address);

        void setFistName(String fistName);

        void setLastName(String lastName);

        void onRefresh();

        void closeFragment();

        void firstNameIsEmpty();

        void lastNameIsEmpty();

        void addressIsEmpty();

        void phoneIsEmpty();

        void emailErrorMessage();

        void firstNameErrorMessage();

        void lastNameErrorMessage();

        void addressErrorMessage();

        void phoneErrorMessage();
    }

    interface Presenter {
        void onSaveClicked(EditUserModel user);

        void setUserDetails();

        void changeUserData(EditUserModel model);

    }
}