package szwedo.bookhubapp.ui.fragments.readingAndHistory.reading;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import szwedo.bookhubapp.R;
import szwedo.bookhubapp.adapter.OnItemClickListener;
import szwedo.bookhubapp.adapter.VerticalSpaceItemDecoration;
import szwedo.bookhubapp.adapter.darkList.small.DarkSmallListRecycleViewAdapter;
import szwedo.bookhubapp.adapter.readingList.ReadingListRecycleViewAdapter;
import szwedo.bookhubapp.models.HistoryBookModel;
import szwedo.bookhubapp.ui.activity.main.MainActivity;
import szwedo.bookhubapp.ui.dialogs.noInternet.NoInternetDialog;
import szwedo.bookhubapp.ui.dialogs.noInternet.NoInternetDialogListener;
import szwedo.bookhubapp.ui.fragments.readingAndHistory.empty.EmptyReadingFragment;

public class ReadingFragment extends Fragment implements ReadingFragmentContact.View, NoInternetDialogListener, OnItemClickListener {

    private RecyclerView recyclerView;
    private ReadingFragmentPresenter presenter;
    private ProgressBar progressBar;
    private NoInternetDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reading, container, false);
        initComponents(view);
        dialog = new NoInternetDialog(this);
        presenter = new ReadingFragmentPresenter(this);
        presenter.setList();
        return view;
    }

    private void initComponents(View view) {
        progressBar = view.findViewById(R.id.reading_progressBar);
        recyclerView = view.findViewById(R.id.reading_list);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(25));
    }

    @Override
    public void setList(List<HistoryBookModel> books) {
        if (books.size() != 0) {
            recyclerView.setAdapter(new ReadingListRecycleViewAdapter(books, this));
        } else {
            getActivity().getSupportFragmentManager().beginTransaction().
                    add(((ViewGroup) getView().getParent()).getId(), new EmptyReadingFragment(), "")
                    .addToBackStack(getView().getClass().getName())
                    .commit();
        }
    }

    @Override
    public void setDarkList() {
        recyclerView.setAdapter(new DarkSmallListRecycleViewAdapter(MainActivity.getAppContext()));
    }

    @Override
    public void setEmptyLayout() {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.readingAndHistory_fragmentContainer, new EmptyReadingFragment())
                .addToBackStack(null).commit();
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
        dialog.show(getActivity().getSupportFragmentManager(), getString(R.string.no_internet_dialog));
        dialog.setOnClickedBack();
    }

    @Override
    public void showSuccessExtendBookRentalMessage() {
        Toast.makeText(MainActivity.getAppContext(), R.string.LoanExtend, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showFailureExtendBookRentalMessage() {
        Toast.makeText(MainActivity.getAppContext(), R.string.LoanDoesntExtend, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRefresh() {
        presenter.setList();
    }

    @Override
    public void goBackToTheFragment() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.setList();
                dialog.closeDialog();
            }
        }, 5000);
    }

    @Override
    public void showNoInternetToast() {
        Toast.makeText(MainActivity.getAppContext(), R.string.no_internet_message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(int idBook) {
        presenter.onExtendBookRentalClicked(idBook);
    }
}