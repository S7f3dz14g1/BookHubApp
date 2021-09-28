package szwedo.bookhubapp.ui.fragments.firstWindow;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import szwedo.bookhubapp.dataAccess.APIAdapter;
import szwedo.bookhubapp.dataAccess.InternetConnection;
import szwedo.bookhubapp.dataAccess.LibraryAccess;
import szwedo.bookhubapp.models.BookModel;
import szwedo.bookhubapp.ui.activity.main.MainActivity;
import szwedo.bookhubapp.utils.Direction;
import szwedo.bookhubapp.utils.PageHolder;
import szwedo.bookhubapp.utils.PaginationBar;
import szwedo.bookhubapp.utils.Sorting;

import static szwedo.bookhubapp.utils.Constants.LIMIT;

public class FirstWindowFragmentPresenter extends APIAdapter implements FirstWindowFragmentContract.Presenter {

    private FirstWindowFragmentContract.View view;
    private LibraryAccess api;
    private PaginationBar pageBar;
    private Sorting currentSorting;
    private Direction currentDirection;
    private String currentSearch;
    private Context context;
    private boolean firstOpen;

    public FirstWindowFragmentPresenter(FirstWindowFragmentContract.View view, Context context) {
        this.view = view;
        this.api = LibraryAccess.getInstance();
        api.setListener(this);
        this.context = context;
        firstOpen = true;
    }

    public void setPaginationComponent(View view) {
        pageBar = new PaginationBar(view);
        pageBar.setOnNextClickListener(nextClickListener);
        pageBar.setOnPreviousClickListener(previousClickListener);
        pageBar.setOnPageClickListener(pageClickListener);
    }

    @Override
    public void setListSortByTitle() {
        if (InternetConnection.isConnection(context)) {
            currentSorting = Sorting.TITLE;
            api.getBooks(LIMIT, 0, currentSorting);
        } else
            openNoInternetDialog();
    }

    private void openNoInternetDialog() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.openOnInternetDialog();
            }
        }, 5000);
    }

    @Override
    public void setListSortByRating() {
        if (InternetConnection.isConnection(context)) {
            currentSorting = Sorting.RATING;
            currentDirection = Direction.DESC;
            api.getBooks(LIMIT, 0, currentSorting, currentDirection);
        } else
            openNoInternetDialog();
    }

    @Override
    public void setListSortByDate() {
        if (InternetConnection.isConnection(context)) {
            currentSorting = Sorting.YEAR;
            currentDirection = Direction.DESC;
            api.getBooks(LIMIT, 0, currentSorting, currentDirection);
        } else
            openNoInternetDialog();
    }

    @Override
    public void setListTopBooks() {
        setTopBooks();
    }

    private void setTopBooks() {
        if (InternetConnection.isConnection(context)) {
            currentSorting = Sorting.POPULARITY;
            currentDirection = Direction.DESC;
            api.getBooks(LIMIT, 0, currentSorting, currentDirection);
        } else
            openNoInternetDialog();
    }

    @Override
    public void setListSortByDiscover() {
        if (InternetConnection.isConnection(context))
            api.getDiscoverBooks(LIMIT);
        else
            openNoInternetDialog();
    }

    @Override
    public void setListByName(String search) {
        if (InternetConnection.isConnection(context)) {
            currentSorting = Sorting.SEARCH;
            currentSearch = search;
            api.getSearchBooks(LIMIT, 0, currentSearch);
        } else
            openNoInternetDialog();
    }

    @Override
    public void setFirstLists() {
        if (InternetConnection.isConnection(MainActivity.getAppContext())) {
            setListTopBooks();
            setListSortByDiscover();
        } else
            openNoInternetDialog();
    }

    @Override
    public void onBookListReceive(PageHolder<BookModel> page) {
        view.startProgressBar();
        view.setTheMostPopularList(page.getContent());
        view.endProgressBar();
        pageBar.setPage(page);
    }

    @Override
    public void onRefreshServer() {
        view.refreshWindow();
    }

    private View.OnClickListener previousClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (InternetConnection.isConnection(context)) {
                if (currentSorting.equals(Sorting.SEARCH))
                    api.getSearchBooks(LIMIT, pageBar.previousPage(), currentSearch);
                else
                    api.getBooks(LIMIT, pageBar.previousPage(), currentSorting, currentDirection);
            } else
                openNoInternetDialog();
        }
    };

    private View.OnClickListener nextClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (InternetConnection.isConnection(context)) {
                if (currentSorting.equals(Sorting.SEARCH))
                    api.getSearchBooks(LIMIT, pageBar.nextPage(), currentSearch);
                else
                    api.getBooks(LIMIT, pageBar.nextPage(), currentSorting, currentDirection);
            } else
                openNoInternetDialog();
        }
    };

    private final View.OnClickListener pageClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (InternetConnection.isConnection(context)) {
                TextView text = pageBar.getView().findViewById(v.getId());
                String value = text.getText().toString();
                int clickedPage = Integer.parseInt(value) - 1;
                if (currentSorting.equals(Sorting.SEARCH))
                    api.getSearchBooks(LIMIT, clickedPage, currentSearch);
                else
                    api.getBooks(LIMIT, clickedPage, currentSorting, currentDirection);
            } else
                openNoInternetDialog();
        }
    };

    @Override
    public void isUserBanned() {
        view.showUserBannedDialog();
    }

    @Override
    public void onDiscoverBookListReceive(PageHolder<BookModel> page) {
        if (InternetConnection.isConnection(context)) {
            if (firstOpen) {
                view.setRecommendedList(page.getContent());
                firstOpen = false;
            } else
                view.setTheMostPopularList(page.getContent());
        } else
            openNoInternetDialog();
    }
}
