package szwedo.bookhubapp.ui.dialogs.filContactDetails;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import szwedo.bookhubapp.R;

public class FilContactDetailsDialog extends AppCompatDialogFragment {

    private Button cancel;
    private Button continueRegistration;
    private FilContactDetailsDialogListener limitDialogListener;

    public FilContactDetailsDialog(FilContactDetailsDialogListener limitDialogListener) {
        this.limitDialogListener = limitDialogListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_fil_contact_detalist, null);
        initComponents(view);
        setOnClickListener();
        builder.setView(view);
        return builder.create();
    }

    private void initComponents(View view) {
        continueRegistration = view.findViewById(R.id.fil_contact_details_comlpete);
        cancel = view.findViewById(R.id.fil_contact_details_cancel);
    }

    private void setOnClickListener() {
        cancel.setOnClickListener(onCancelClicked);
        continueRegistration.setOnClickListener(onContinueClicked);
    }

    private View.OnClickListener onCancelClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dismiss();
        }
    };

    private View.OnClickListener onContinueClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dismiss();
            limitDialogListener.onContinueRegistrationClicked();
        }
    };
}