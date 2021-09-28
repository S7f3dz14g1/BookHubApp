package szwedo.bookhubapp.ui.dialogs.copiesOfBooks;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import szwedo.bookhubapp.R;
import szwedo.bookhubapp.adapter.VerticalSpaceItemDecoration;
import szwedo.bookhubapp.adapter.copiesOfBookList.CopiesOfBookListRecycleViewAdapter;
import szwedo.bookhubapp.models.CopiesOfBookModel;
import szwedo.bookhubapp.ui.activity.main.MainActivity;
import szwedo.bookhubapp.ui.dialogs.filContactDetails.FilContactDetailsDialog;
import szwedo.bookhubapp.ui.dialogs.filContactDetails.FilContactDetailsDialogListener;
import szwedo.bookhubapp.ui.dialogs.increaseTheLimit.IncreaseTheLimitDialog;
import szwedo.bookhubapp.ui.dialogs.increaseTheLimit.IncreaseTheLimitDialogListener;
import szwedo.bookhubapp.ui.dialogs.noInternet.NoInternetDialog;
import szwedo.bookhubapp.ui.dialogs.noInternet.NoInternetDialogListener;
import szwedo.bookhubapp.ui.dialogs.stopBorrow.StopBorrowDialog;

public class CopiesOfBooksDialog extends AppCompatDialogFragment implements CopiesOfBooksDialogContract.View, NoInternetDialogListener, FilContactDetailsDialogListener, IncreaseTheLimitDialogListener {

    private RecyclerView recyclerView;
    private Button back;
    private Button borrow;
    private CopiesOfBooksDialogPresenter presenter;
    private CopiesOfBookListRecycleViewAdapter copiesOfBookListRecycleViewAdapter;
    private ProgressBar progressBar;
    private NoInternetDialog noInternetDialog;
    private IncreaseTheLimitDialog increaseTheLimitDialog;
    private CopiesOfBooksListener listener;

    public CopiesOfBooksDialog(CopiesOfBooksListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), 2);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_copies_of_books, null);
        increaseTheLimitDialog = new IncreaseTheLimitDialog(this);
        initComponents(view);
        presenter = new CopiesOfBooksDialogPresenter(this, this.getArguments().getInt("id"));
        setOnClickListener();
        builder.setInverseBackgroundForced(false);
        builder.setView(view);
        noInternetDialog = new NoInternetDialog(this);
        return builder.create();
    }

    private void setOnClickListener() {
        back.setOnClickListener(onBackClicked);
        borrow.setOnClickListener(onBorrowClicked);
    }

    private View.OnClickListener onBackClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dismiss();
        }
    };

    private View.OnClickListener onBorrowClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            presenter.reserveBook(copiesOfBookListRecycleViewAdapter.getIdBook());
        }
    };

    private void initComponents(View view) {
        back = view.findViewById(R.id.chose_book_option_btn_cancel);
        borrow = view.findViewById(R.id.chose_book_option_btn_confirm);
        recyclerView = view.findViewById(R.id.chose_book_option_btn_recycle_view);
        progressBar = view.findViewById(R.id.copiesOfBook_progressBar);
    }

    @Override
    public void setList(List<CopiesOfBookModel> books) {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.HORIZONTAL, false));
        copiesOfBookListRecycleViewAdapter = new CopiesOfBookListRecycleViewAdapter(getContext(), books);
        recyclerView.setAdapter(copiesOfBookListRecycleViewAdapter);
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(25));
    }

    @Override
    public void goBackToTheFragment() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.reserveBook(copiesOfBookListRecycleViewAdapter.getIdBook());
                noInternetDialog.closeDialog();
            }
        }, 5000);
    }

    @Override
    public void showNoInternetToast() {
        Toast.makeText(MainActivity.getAppContext(), R.string.no_internet_message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSuccessReservationBookToast() {
        Toast.makeText(MainActivity.getAppContext(), getString(R.string.you_booked_a_book), Toast.LENGTH_LONG).show();
    }

    @Override
    public void startProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void endProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void openOnInternetDialog() {
        noInternetDialog.show(getFragmentManager(), getString(R.string.no_internet_dialog));
        noInternetDialog.setOnClickedBack();
    }

    @Override
    public void openInformDialog() {
        FilContactDetailsDialog dialog = new FilContactDetailsDialog(this);
        dialog.show(getFragmentManager(), "");
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(MainActivity.getAppContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showStopBorrowDialog() {
        StopBorrowDialog stopBorrowDialog = new StopBorrowDialog();
        stopBorrowDialog.show(getActivity().getSupportFragmentManager(), getString(R.string.no_internet_dialog));
    }

    @Override
    public void onRefresh() {
        presenter.reserveBook(copiesOfBookListRecycleViewAdapter.getIdBook());
    }

    @Override
    public void openIncreaseTheLimitDialog() {
        increaseTheLimitDialog.show(getFragmentManager(), "");
    }

    @Override
    public void onContinueRegistrationClicked() {
        dismiss();
        listener.openEditProfileFragment();
    }

    @Override
    public void onSendClicked(String message) {
        dismiss();
        presenter.increaseLimit(message);
    }
}
