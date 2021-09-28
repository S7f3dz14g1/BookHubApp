package szwedo.bookhubapp.ui.dialogs.passwordSecurity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import szwedo.bookhubapp.R;

public class PasswordSecurityDialog extends AppCompatDialogFragment {

    private EditText password;
    private Button edit;
    private Button back;

    private DialogPasswordSecurityListener listener;

    public PasswordSecurityDialog(DialogPasswordSecurityListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_password_security, null);
        initComponents(view);
        setOnClickListeners();
        builder.setView(view);
        return builder.create();
    }

    private void setOnClickListeners() {
        back.setOnClickListener(onBackClicked);
        edit.setOnClickListener(onEditDataClicked);
    }

    private View.OnClickListener onEditDataClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            listener.applyPassword(password.getText().toString());
        }
    };

    private View.OnClickListener onBackClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dismiss();
        }
    };

    private void initComponents(View view) {
        password = view.findViewById(R.id.dialog_editText_password);
        back = view.findViewById(R.id.password_security_btn_cancel);
        edit = view.findViewById(R.id.password_security_btn_confirm);
    }

    public void closeDialog() {
        dismiss();
    }
}
