package szwedo.bookhubapp.ui.dialogs.increaseTheLimit;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import szwedo.bookhubapp.R;
import szwedo.bookhubapp.ui.activity.main.MainActivity;

public class IncreaseTheLimitDialog extends AppCompatDialogFragment {

    private Button cancel;
    private Button send;
    private EditText decryption;
    private IncreaseTheLimitDialogListener limitDialogListener;

    public IncreaseTheLimitDialog(IncreaseTheLimitDialogListener limitDialogListener) {
        this.limitDialogListener = limitDialogListener;
    }

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_request_to_increase_limit, null);
        initComponents(view);
        setOnClickListeners();
        builder.setView(view);
        return builder.create();
    }

    private void initComponents(View view) {
        cancel = view.findViewById(R.id.request_to_increase_cancel);
        send = view.findViewById(R.id.request_to_increase__button_send);
        decryption = view.findViewById(R.id.request_to_increase_edit_text_descrytpion);
    }

    private void setOnClickListeners() {
        cancel.setOnClickListener(onCancelClicked);
        send.setOnClickListener(onSendClicked);
    }

    private View.OnClickListener onCancelClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dismiss();
        }
    };

    private View.OnClickListener onSendClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!decryption.getText().toString().isEmpty())
                limitDialogListener.onSendClicked(decryption.getText().toString());
            else
                showToast();
        }
    };

    private void showToast() {
        Toast.makeText(MainActivity.getAppContext(),
                MainActivity.getAppContext().getString(R.string.decryption_is_empty),
                Toast.LENGTH_LONG).show();
    }
}