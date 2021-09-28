package szwedo.bookhubapp.ui.activity.main;

import szwedo.bookhubapp.dataAccess.APIAdapter;
import szwedo.bookhubapp.dataAccess.LibraryAccess;
import szwedo.bookhubapp.dataAccess.local.LocalDataAccess;

class MainActivityPresenter extends APIAdapter implements MainActivityContract.Presenter {

    private final LibraryAccess api;
    private  MainActivityContract.View view;

    public MainActivityPresenter(MainActivityContract.View view) {
        this.view = view;
        api = LibraryAccess.getInstance();
        api.setListener(this);
    }

    @Override
    public void isBanedUser() {
        api.isUserBanned(LocalDataAccess.getToken());
    }
}
