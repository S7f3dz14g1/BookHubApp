package szwedo.bookhubapp.ui.fragments.firstWindow;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import szwedo.bookhubapp.R;
import szwedo.bookhubapp.adapter.OnItemClickListener;
import szwedo.bookhubapp.adapter.VerticalSpaceItemDecoration;
import szwedo.bookhubapp.adapter.darkList.large.DarkLargeListRecycleViewAdapter;
import szwedo.bookhubapp.adapter.darkList.small.DarkSmallListRecycleViewAdapter;
import szwedo.bookhubapp.adapter.homeList.HomeListRecycleViewAdapter;
import szwedo.bookhubapp.adapter.recommendedList.RecommendedListRecycleViewAdapter;
import szwedo.bookhubapp.models.BookModel;
import szwedo.bookhubapp.ui.activity.main.MainActivity;
import szwedo.bookhubapp.ui.dialogs.banedUser.BanedUserDialog;
import szwedo.bookhubapp.ui.dialogs.noInternet.NoInternetDialog;
import szwedo.bookhubapp.ui.dialogs.noInternet.NoInternetDialogListener;
import szwedo.bookhubapp.ui.fragments.bookDetails.BookDetailsFragment;

public class FirstWindowFragment extends Fragment implements FirstWindowFragmentContract.View, NoInternetDialogListener {

    private ImageButton sortBtn;
    private PopupMenu menu;
    private RecyclerView recommendedRecyclerView;
    private RecyclerView theMostPopularRecyclerView;
    private SearchView searchExitText;
    private FirstWindowFragmentPresenter presenter;
    private ProgressBar progressBar;
    private TextView text1;
    private TextView text2;
    private NoInternetDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initComponents(view);
        setAdapters();
        setDarkList();
        setOnClickListener();
        presenter = new FirstWindowFragmentPresenter(this, getContext());
        dialog = new NoInternetDialog(this);
        presenter.setFirstLists();
        presenter.setPaginationComponent(view);
        return view;
    }

    private void initComponents(View view) {
        sortBtn = view.findViewById(R.id.first_window_btn_sort);
        menu = new PopupMenu(view.getContext(), sortBtn);
        menu.getMenuInflater().inflate(R.menu.sort_list_menu, menu.getMenu());
        recommendedRecyclerView = view.findViewById(R.id.first_window_recycle_view1);
        theMostPopularRecyclerView = view.findViewById(R.id.first_window_recycle_view2);
        searchExitText = view.findViewById(R.id.first_window_searchView_search);
        progressBar = view.findViewById(R.id.first_window_progressBar);
        text1 = view.findViewById(R.id.first_window_text_view_theMostPopular);
        text2 = view.findViewById(R.id.first_window_text_view_recommendedForYou);
    }

    private void setDarkList() {
        theMostPopularRecyclerView.setAdapter(new DarkSmallListRecycleViewAdapter(MainActivity.getAppContext()));
        recommendedRecyclerView.setAdapter(new DarkLargeListRecycleViewAdapter(MainActivity.getAppContext()));
    }

    private void setOnClickListener() {
        sortBtn.setOnClickListener(sortButtonOnClick);
        searchExitText.setOnQueryTextListener(searchOnSearchListener);
    }

    private SearchView.OnQueryTextListener searchOnSearchListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            hideComponents();
            if (newText.isEmpty())
                text1.setText("");
            else
                text1.setText("''" + newText + "''");
            presenter.setListByName(newText);
            return false;
        }
    };

    private View.OnClickListener sortButtonOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            menu.setOnMenuItemClickListener(menuOnClickListener);
            menu.show();
        }
    };

    private PopupMenu.OnMenuItemClickListener menuOnClickListener =
            new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    if (item.getItemId() == R.id.bottom_sort_title) {
                        presenter.setListSortByTitle();
                        hideComponents();
                        text1.setText(R.string.books_sorted_by_title);
                    } else if (item.getItemId() == R.id.bottom_sort_date) {
                        presenter.setListSortByDate();
                        hideComponents();
                        text1.setText(R.string.books_sorted_by_date);
                    } else if (item.getItemId() == R.id.bottom_sort_discover) {
                        presenter.setListSortByDiscover();
                        text1.setText(R.string.discover_books);
                        hideComponents();
                    } else if (item.getItemId() == R.id.bottom_sort_ranting) {
                        presenter.setListSortByRating();
                        text1.setText(R.string.books_sorted_by_rating);
                        hideComponents();
                    } else {
                        presenter.setListTopBooks();
                        hideComponents();
                        text1.setText(R.string.top_books);
                    }
                    return true;
                }
            };

    private void hideComponents() {
        recommendedRecyclerView.setVisibility(View.GONE);
        text2.setVisibility(View.GONE);
    }

    private void setAdapters() {
        theMostPopularRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        theMostPopularRecyclerView.addItemDecoration(new VerticalSpaceItemDecoration(25));
        recommendedRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.HORIZONTAL, false));
        recommendedRecyclerView.addItemDecoration(new VerticalSpaceItemDecoration(25));
    }

    @Override
    public void setTheMostPopularList(List<BookModel> booksList) {
        theMostPopularRecyclerView.setAdapter(new HomeListRecycleViewAdapter(getContext(), booksList, onItemClickListener));
    }

    @Override
    public void setRecommendedList(List<BookModel> booksList) {
        recommendedRecyclerView.setAdapter(new RecommendedListRecycleViewAdapter(getContext(), booksList, onItemClickListener));
    }

    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onClick(int idBook) {
            openBookViewFragment(idBook);
        }
    };

    private void openBookViewFragment(int idBook) {
        getActivity().getSupportFragmentManager().beginTransaction().
                add(((ViewGroup) getView().getParent()).getId(), getBookViewFragmentWithSetArguments(idBook))
                .addToBackStack(getView().getClass().getName())
                .commit();
    }

    private BookDetailsFragment getBookViewFragmentWithSetArguments(int argument) {
        BookDetailsFragment bookViewFragment = new BookDetailsFragment();
        bookViewFragment.setArguments(getBundleAndPutString(argument));
        return bookViewFragment;
    }

    private Bundle getBundleAndPutString(int argument) {
        Bundle bundle = new Bundle();
        bundle.putInt(getString(R.string.idBook), argument);
        return bundle;
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
    }

    @Override
    public String getSearchText() {
        return searchExitText.toString();
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
        setDarkList();
        dialog.show(getActivity().getSupportFragmentManager(), getString(R.string.no_internet_dialog));
        dialog.setOnClickedBack();
    }

    @Override
    public void refreshWindow() {
        presenter.setFirstLists();
    }

    @Override
    public void showUserBannedDialog() {
        BanedUserDialog banedUserDialog=new BanedUserDialog();
        banedUserDialog.show(getFragmentManager(),"");
    }

    @Override
    public void goBackToTheFragment() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.setFirstLists();
                dialog.closeDialog();
            }
        }, 5000);
    }

    @Override
    public void showNoInternetToast() {
        Toast.makeText(MainActivity.getAppContext(), R.string.no_internet_message, Toast.LENGTH_LONG).show();
    }
}
