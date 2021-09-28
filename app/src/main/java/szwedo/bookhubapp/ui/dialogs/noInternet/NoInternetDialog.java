package szwedo.bookhubapp.ui.dialogs.noInternet;

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
import szwedo.bookhubapp.dataAccess.InternetConnection;
import szwedo.bookhubapp.ui.activity.main.MainActivity;

public class NoInternetDialog extends AppCompatDialogFragment {

    private Button back;
    private NoInternetDialogListener listener;

    public NoInternetDialog(NoInternetDialogListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_no_internet, null);
        initComponents(view);
        setOnClickListener();
        builder.setView(view);
        return builder.create();
    }

    public void setOnClickedBack() {
        listener.goBackToTheFragment();
    }

    public void closeDialog() {
        dismiss();
    }

    private void initComponents(View view) {
        back = view.findViewById(R.id.no_internet_dialog_button_back);
    }

    private void setOnClickListener() {
        back.setOnClickListener(onClickedBack);
    }

    private View.OnClickListener onClickedBack = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (InternetConnection.isConnection(MainActivity.getAppContext()))
                listener.goBackToTheFragment();
            else
                listener.showNoInternetToast();
        }
    };
}
