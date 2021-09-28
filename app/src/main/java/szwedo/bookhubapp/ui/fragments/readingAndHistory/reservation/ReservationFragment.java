package szwedo.bookhubapp.ui.fragments.readingAndHistory.reservation;

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
import szwedo.bookhubapp.adapter.reservationList.ReservationListRecycleViewAdapter;
import szwedo.bookhubapp.models.ReservationBookModel;
import szwedo.bookhubapp.ui.activity.main.MainActivity;
import szwedo.bookhubapp.ui.dialogs.banedUser.BanedUserDialog;
import szwedo.bookhubapp.ui.dialogs.cancelReservationBook.CancelReservationBookDialog;
import szwedo.bookhubapp.ui.dialogs.cancelReservationBook.DialogCancelReservationBookListener;
import szwedo.bookhubapp.ui.dialogs.noInternet.NoInternetDialog;
import szwedo.bookhubapp.ui.dialogs.noInternet.NoInternetDialogListener;
import szwedo.bookhubapp.ui.fragments.readingAndHistory.empty.EmptyReservationsFragment;

public class ReservationFragment extends Fragment implements ReservationFragmentContact.View , NoInternetDialogListener, OnItemClickListener, DialogCancelReservationBookListener {

    private RecyclerView recyclerView;
    private ReservationFragmentPresenter presenter;
    private ProgressBar progressBar;
    private NoInternetDialog noInternetDialog;
    private CancelReservationBookDialog cancelReservationBookDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reservation, container, false);
        initComponents(view);
        noInternetDialog =new NoInternetDialog(this);
        cancelReservationBookDialog=new CancelReservationBookDialog();
        presenter=new ReservationFragmentPresenter(this);
        presenter.setList();
        return view;
    }

    private void initComponents(View view) {
        progressBar=view.findViewById(R.id.reservation_progressBar);
        recyclerView=view.findViewById(R.id.reservation_list);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(25));
    }

    @Override
    public void setList(List<ReservationBookModel> books) {
        if(books.size()!=0){
            recyclerView.setAdapter(new ReservationListRecycleViewAdapter(books,this));
        }else{
            getActivity().getSupportFragmentManager().beginTransaction().
                    add(((ViewGroup)getView().getParent()).getId(),new EmptyReservationsFragment(),"Empty")
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
                .replace(R.id.readingAndHistory_fragmentContainer,new EmptyReservationsFragment())
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
        noInternetDialog.show(getActivity().getSupportFragmentManager(),getString(R.string.no_internet_dialog));
        noInternetDialog.setOnClickedBack();
    }

    @Override
    public void openCancelReservationDialog() {
        cancelReservationBookDialog.show(getActivity().getSupportFragmentManager(),getString(R.string.cancel_book_reservation));
        cancelReservationBookDialog.setListener(this);
    }

    @Override
    public void onSuccessCancelBookMessage() {
        Toast.makeText(MainActivity.getAppContext(),getString(R.string.reservation_canceled), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showUserBannedDialog() {
        BanedUserDialog banedUserDialog=new BanedUserDialog();
        banedUserDialog.show(getFragmentManager(),"");
    }

    @Override
    public void goBackToTheFragment() {
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.setList();
                noInternetDialog.closeDialog();
            }
        },5000);
    }

    @Override
    public void showNoInternetToast() {
        Toast.makeText(MainActivity.getAppContext(),R.string.no_internet_message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(int idBook) {
        presenter.onCancelClicked(idBook);
    }

    @Override
    public void onCancel() {
        presenter.cancelBook();
    }
}