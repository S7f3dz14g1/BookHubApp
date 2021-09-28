package szwedo.bookhubapp.ui.fragments.editProfile;

import android.os.Handler;

import szwedo.bookhubapp.R;
import szwedo.bookhubapp.dataAccess.APIAdapter;
import szwedo.bookhubapp.dataAccess.InternetConnection;
import szwedo.bookhubapp.dataAccess.LibraryAccess;
import szwedo.bookhubapp.dataAccess.local.LocalDataAccess;
import szwedo.bookhubapp.exception.ApiError;
import szwedo.bookhubapp.models.EditUserModel;
import szwedo.bookhubapp.models.UserModel;
import szwedo.bookhubapp.ui.activity.main.MainActivity;
import szwedo.bookhubapp.utils.EditProfileStatus;

class EditProfileFragmentPresenter extends APIAdapter implements EditProfileFragmentContract.Presenter {

    private final EditProfileFragmentContract.View view;
    private final LibraryAccess api;
    private UserModel user;

    public EditProfileFragmentPresenter(EditProfileFragmentContract.View view) {
        this.view = view;
        api = LibraryAccess.getInstance();
        api.setListener(this);
    }

    @Override
    public void onSaveClicked(EditUserModel user) {
        EditProfileStatus status = EditProfileUtils.validateUserDetails(user);
        if (status.equals(EditProfileStatus.CORRECT)) {
            if (view.isCheckedBoxEditPassword()) {
                status = EditProfileUtils.validatePassword(view.getPassword(), view.getRepeatPassword());
                if (!status.equals(EditProfileStatus.CORRECT)) {
                    showMessage(status);
                } else {
                    view.openDialog();
                }
            } else {
                view.openDialog();
            }
        }else {
            showMessage(status);
        }
    }

    private void showMessage(EditProfileStatus status) {
        if (status.equals(EditProfileStatus.EMPTY_EMAIL)) {
            view.errorEmptyEmail();
        } else if (status.equals(EditProfileStatus.EMPTY_FIRST_NAME)) {
            view.firstNameIsEmpty();
        } else if (status.equals(EditProfileStatus.EMPTY_LAST_NAME)) {
            view.lastNameIsEmpty();
        } else if (status.equals(EditProfileStatus.EMPTY_ADDRESS)) {
            view.addressIsEmpty();
        } else if (status.equals(EditProfileStatus.EMPTY_PHONE)) {
            view.phoneIsEmpty();
        } else if (status.equals(EditProfileStatus.EMPTY_PASSWORD)) {
            view.errorEmptyPassword();
        } else if (status.equals(EditProfileStatus.EMPTY_PASSWORD2)) {
            view.errorEmptyRepeatPassword();
        } else if (status.equals(EditProfileStatus.INCORRECT_EMAIL)) {
            view.emailErrorMessage();
        } else if (status.equals(EditProfileStatus.INCORRECT_FIRST_NAME)) {
            view.firstNameErrorMessage();
        } else if (status.equals(EditProfileStatus.INCORRECT_LAST_NAME)) {
            view.lastNameErrorMessage();
        }else if(status.equals(EditProfileStatus.INCORRECT_ADDRESS)){
            view.addressErrorMessage();
        }else if(status.equals(EditProfileStatus.INCORRECT_PHONE)){
            view.phoneErrorMessage();
        }else if(status.equals(EditProfileStatus.INCORRECT_PASSWORD)){
            view.errorPasswordIncorrect();
        }else {
            view.errorRepeatPasswordAreNotIdentical();
        }
    }

    @Override
    public void setUserDetails() {
        view.startProgressBar();
        if (InternetConnection.isConnection(MainActivity.getAppContext()))
            api.getUserDetails(LocalDataAccess.getToken());
        else {
            openNoInternetDialog();
        }
    }

    @Override
    public void changeUserData(EditUserModel model) {
        view.startProgressBar();
        if (InternetConnection.isConnection(MainActivity.getAppContext()))
            api.editUserData(LocalDataAccess.getToken(), model);
        else {
            openNoInternetDialog();
        }
    }

    private void openNoInternetDialog() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.openNoInternetDialog();
            }
        }, 5000);
    }

    @Override
    public void onEditUserReceive() {
        view.onRefresh();
        ;
        view.endProgressBar();
        view.showToast(MainActivity.getAppContext().getString(R.string.the_data_has_been_edited));
        view.closeFragment();
    }

    @Override
    public void onErrorReceive(ApiError error) {
        view.endProgressBar();
    }

    @Override
    public void onUserDetailsReceive(UserModel user) {
        this.user = user;
        view.setEmail(user.getEmail());
        if (user.getPhone() != null) view.setPhone(user.getPhone());
        if (user.getLastName() != null) view.setLastName(user.getLastName());
        if (user.getFirstName() != null) view.setFistName(user.getFirstName());
        if (user.getAddress() != null) view.setAddress(user.getAddress());
        view.endProgressBar();
    }
}
