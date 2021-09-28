package szwedo.bookhubapp.ui.dialogs.stopBorrow;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import szwedo.bookhubapp.R;
import szwedo.bookhubapp.ui.activity.login.LoginActivity;
import szwedo.bookhubapp.ui.activity.main.MainActivity;
import szwedo.bookhubapp.ui.activity.register.RegisterActivity;

public class StopBorrowDialog extends AppCompatDialogFragment {

    private Button back;
    private Button login;
    private Button registration;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_stop_borrow, null);
        initComponents(view);
        setOnClickListener();
        builder.setView(view);
        return builder.create();
    }

    public void closeDialog() {
        dismiss();
    }

    private void initComponents(View view) {
        back = view.findViewById(R.id.stopBorrow_back_button);
        login = view.findViewById(R.id.stopBorrow_login_button);
        registration = view.findViewById(R.id.stopBorrow_registration_button);
    }

    private void setOnClickListener() {
        back.setOnClickListener(onBackClicked);
        registration.setOnClickListener(onRegistrationClicked);
        login.setOnClickListener(onLoginClicked);
    }

    private View.OnClickListener onBackClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dismiss();
        }
    };

    private View.OnClickListener onLoginClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(MainActivity.getAppContext(), LoginActivity.class));
        }
    };

    private View.OnClickListener onRegistrationClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(MainActivity.getAppContext(), RegisterActivity.class));
        }
    };
}