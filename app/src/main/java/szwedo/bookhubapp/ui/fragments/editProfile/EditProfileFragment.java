package szwedo.bookhubapp.ui.fragments.editProfile;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import szwedo.bookhubapp.R;
import szwedo.bookhubapp.models.EditUserModel;
import szwedo.bookhubapp.ui.activity.main.MainActivity;
import szwedo.bookhubapp.ui.dialogs.noInternet.NoInternetDialog;
import szwedo.bookhubapp.ui.dialogs.noInternet.NoInternetDialogListener;
import szwedo.bookhubapp.ui.dialogs.passwordSecurity.DialogPasswordSecurityListener;
import szwedo.bookhubapp.ui.dialogs.passwordSecurity.PasswordSecurityDialog;

public class EditProfileFragment extends Fragment implements EditProfileFragmentContract.View, DialogPasswordSecurityListener, NoInternetDialogListener {

    private EditText editEmail;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch switchEditPassword;
    private TextView textEditPassword;
    private EditText editPassword;
    private TextView textRepeatEditPassword;
    private EditText editTextRepeatPassword;
    private EditText firstName;
    private EditText lastName;
    private EditText address;
    private EditText phone;
    private Button cancelBtn;
    private Button saveBtn;
    private ProgressBar progressBar;
    private EditProfileFragmentContract.Presenter presenter;
    private NoInternetDialog dialog;
    private PasswordSecurityDialog securityDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        initComponents(view);
        setOnClickListeners();
        presenter = new EditProfileFragmentPresenter(this);
        presenter.setUserDetails();
        hideEditPasswordsFields();
        dialog = new NoInternetDialog(this);
        securityDialog = new PasswordSecurityDialog(this);
        return view;
    }

    private void initComponents(View view) {
        editEmail = view.findViewById(R.id.editProfile_editText_email2);
        switchEditPassword = view.findViewById(R.id.fragment_edit_profile_switch_change_password);
        textEditPassword = view.findViewById(R.id.editProfile_textViev_newPassword2);
        editPassword = view.findViewById(R.id.editProfile_editText_newPassword2);
        textRepeatEditPassword = view.findViewById(R.id.editProfile_textViev_repeatPassword2);
        editTextRepeatPassword = view.findViewById(R.id.editProfile_editText_repeatPassword2);
        progressBar = view.findViewById(R.id.editProfile_progresBar);
        cancelBtn = view.findViewById(R.id.editProfile_btn_cancel2);
        saveBtn = view.findViewById(R.id.editProfile_btn_save2);
        firstName = view.findViewById(R.id.editProfile_editText_firstName);
        lastName = view.findViewById(R.id.editProfile_editText_firstName2);
        address = view.findViewById(R.id.editProfile_editText_address);
        phone = view.findViewById(R.id.editProfile_editText_phone);
    }

    private void setOnClickListeners() {
        cancelBtn.setOnClickListener(cancelOnClickListener());
        saveBtn.setOnClickListener(saveOnClickListener());
        switchEditPassword.setOnClickListener(checkBoxOnClickListener);
    }

    private final View.OnClickListener checkBoxOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            checkBoxStatus();
        }
    };

    private View.OnClickListener cancelOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        };
    }

    private final View.OnClickListener saveOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchEditPassword.isChecked()){
                    presenter.onSaveClicked(new EditUserModel(
                            editEmail.getText().toString(),
                            editPassword.getText().toString(),
                            editTextRepeatPassword.getText().toString(),
                            firstName.getText().toString(),
                            lastName.getText().toString(),
                            address.getText().toString(),
                            phone.getText().toString()));
                }
                else{
                    presenter.onSaveClicked((new EditUserModel(
                            editEmail.getText().toString(),
                            "",
                            "",
                            firstName.getText().toString(),
                            lastName.getText().toString(),
                            address.getText().toString(),
                            phone.getText().toString())));
                };
            }
        };
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void startProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void endProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void checkBoxStatus() {
        if (!switchEditPassword.isChecked()) {
            hideEditPasswordsFields();
        } else {
            showEditPasswordsFields();
        }
    }

    private void hideEditPasswordsFields() {
        textEditPassword.setVisibility(View.GONE);
        textRepeatEditPassword.setVisibility(View.GONE);
        editPassword.setVisibility(View.GONE);
        editTextRepeatPassword.setVisibility(View.GONE);
    }

    private void showEditPasswordsFields() {
        textEditPassword.setVisibility(View.VISIBLE);
        textRepeatEditPassword.setVisibility(View.VISIBLE);
        editPassword.setVisibility(View.VISIBLE);
        editTextRepeatPassword.setVisibility(View.VISIBLE);
    }

    @Override
    public void setEmail(String email) {
        editEmail.setText(email);
    }

    @Override
    public String getEmail() {
        return editEmail.getText().toString();
    }

    @Override
    public String getPassword() {
        return editPassword.getText().toString();
    }

    @Override
    public String getRepeatPassword() {
        return editTextRepeatPassword.getText().toString();
    }

    @Override
    public boolean isCheckedBoxEditPassword() {
        return switchEditPassword.isChecked();
    }

    @Override
    public void openDialog() {
        securityDialog.show(getActivity().getSupportFragmentManager(), "");
    }

    @Override
    public void setPhone(String phone) {
        this.phone.setText(phone);
    }

    @Override
    public void setAddress(String address) {
        this.address.setText(address);
    }

    @Override
    public void setFistName(String fistName) {
        this.firstName.setText(fistName);
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName.setText(lastName);
    }

    @Override
    public void onRefresh() {
        presenter.setUserDetails();
    }

    @Override
    public void closeFragment() {
        securityDialog.closeDialog();
        getActivity().onBackPressed();
    }

    @Override
    public void firstNameIsEmpty() {
        firstName.setError(getString(R.string.field_is_empty));
    }

    @Override
    public void lastNameIsEmpty() {
        lastName.setError(getString(R.string.field_is_empty));
    }

    @Override
    public void addressIsEmpty() {
        address.setError(getString(R.string.field_is_empty));
    }

    @Override
    public void phoneIsEmpty() {
        phone.setError(getString(R.string.field_is_empty));
    }

    @Override
    public void emailErrorMessage() {
        editEmail.setError(getString(R.string.incorrect_email));
    }

    @Override
    public void firstNameErrorMessage() {
        firstName.setError(getString(R.string.incorrect_first_name));
    }

    @Override
    public void lastNameErrorMessage() {
        lastName.setError(getString(R.string.incorrect_last_name));
    }

    @Override
    public void addressErrorMessage() {
        address.setError(getString(R.string.incorrect_addres));
    }

    @Override
    public void phoneErrorMessage() {
        phone.setError(getString(R.string.incorresct_phone));
    }

    @Override
    public void errorEmailIncorrect() {
        editEmail.setError(getString(R.string.incorrect_email));
    }

    @Override
    public void errorEmptyEmail() {
        editPassword.setError(getString(R.string.field_is_empty));
    }

    @Override
    public void errorEmptyPassword() {
        editPassword.setError(getString(R.string.field_is_empty));
    }

    @Override
    public void errorEmptyRepeatPassword() {
        editTextRepeatPassword.setError(getString(R.string.field_is_empty));
    }

    @Override
    public void errorPasswordIncorrect() {
        editPassword.setError(getString(R.string.password_should_include));
    }

    @Override
    public void errorRepeatPasswordAreNotIdentical() {
        editTextRepeatPassword.setError(getString(R.string.password_are_not_identical));
    }

    @Override
    public void openNoInternetDialog() {
        dialog.show(getActivity().getSupportFragmentManager(), getString(R.string.no_internet_dialog));
        dialog.setOnClickedBack();
    }

    @Override
    public void applyPassword(String password) {
        presenter.changeUserData(new EditUserModel(
                editEmail.getText().toString(),
                editPassword.getText().toString(),
                password,
                firstName.getText().toString(),
                lastName.getText().toString(),
                address.getText().toString(),
                phone.getText().toString()
        ));
    }

    @Override
    public void goBackToTheFragment() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.setUserDetails();
                dialog.closeDialog();
            }
        }, 5000);
    }

    @Override
    public void showNoInternetToast() {
        Toast.makeText(MainActivity.getAppContext(), R.string.no_internet_message, Toast.LENGTH_LONG).show();
    }
}