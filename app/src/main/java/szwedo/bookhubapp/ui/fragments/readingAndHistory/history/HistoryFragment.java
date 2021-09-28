package szwedo.bookhubapp.ui.fragments.readingAndHistory.history;

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
import szwedo.bookhubapp.adapter.VerticalSpaceItemDecoration;
import szwedo.bookhubapp.adapter.darkList.small.DarkSmallListRecycleViewAdapter;
import szwedo.bookhubapp.adapter.historyList.HistoryListRecycleViewAdapter;
import szwedo.bookhubapp.models.HistoryBookModel;
import szwedo.bookhubapp.ui.activity.main.MainActivity;
import szwedo.bookhubapp.ui.dialogs.noInternet.NoInternetDialog;
import szwedo.bookhubapp.ui.dialogs.noInternet.NoInternetDialogListener;
import szwedo.bookhubapp.ui.fragments.readingAndHistory.empty.EmptyHistoryFragment;

public class HistoryFragment extends Fragment implements HistoryFragmentContact.View, NoInternetDialogListener {

    private RecyclerView recyclerView;
    private HistoryFragmentPresenter presenter;
    private ProgressBar progressBar;
    private NoInternetDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        initComponents(view);
        dialog = new NoInternetDialog(this);
        presenter = new HistoryFragmentPresenter(this);
        presenter.setList();
        return view;
    }

    private void initComponents(View view) {
        progressBar = view.findViewById(R.id.history_progressBar);
        recyclerView = view.findViewById(R.id.history_list);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(25));
    }

    @Override
    public void setList(List<HistoryBookModel> books) {
        if (books.size() != 0)
            recyclerView.setAdapter(new HistoryListRecycleViewAdapter(MainActivity.getAppContext(), books));
        else {
            getActivity().getSupportFragmentManager().beginTransaction().
                    add(((ViewGroup) getView().getParent()).getId(), new EmptyHistoryFragment(), "")
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
                .replace(R.id.readingAndHistory_fragmentContainer, new EmptyHistoryFragment())
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
}