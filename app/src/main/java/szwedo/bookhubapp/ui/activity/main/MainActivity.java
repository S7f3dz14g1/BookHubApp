package szwedo.bookhubapp.ui.activity.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import szwedo.bookhubapp.R;
import szwedo.bookhubapp.dataAccess.local.LocalDataAccess;
import szwedo.bookhubapp.ui.fragments.firstWindow.FirstWindowFragment;
import szwedo.bookhubapp.ui.fragments.notLoggedIn.historyAndReading.NotLoggedInReservationsReadingAndHistory;
import szwedo.bookhubapp.ui.fragments.notLoggedIn.profile.NotLoggedInProfileFragment;
import szwedo.bookhubapp.ui.fragments.profile.ProfileFragment;
import szwedo.bookhubapp.ui.fragments.readingAndHistory.ReservationsReadingAndHistoryMainFragment;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    private BottomNavigationView bottom_nav;
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.context = getApplicationContext();
        setContentView(R.layout.activity_bottom_navigation);
        bottom_nav = findViewById(R.id.mainActivity_bottom_nav);
        bottom_nav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().add(R.id.mainActivity_fragment_container, new FirstWindowFragment()).commit();
        presenter = new MainActivityPresenter(this);
    }

    public static Context getAppContext() {
        return MainActivity.context;
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @SuppressLint("NonConstantResourceId")
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.bottom_nav_profile:
                            presenter.isBanedUser();
                            if (LocalDataAccess.isLogin())
                                selectedFragment = new ProfileFragment();
                            else
                                selectedFragment = new NotLoggedInProfileFragment();
                            break;
                        case R.id.bottom_nav_book:
                            presenter.isBanedUser();
                            if (LocalDataAccess.isLogin())
                                selectedFragment = new ReservationsReadingAndHistoryMainFragment();
                            else
                                selectedFragment = new NotLoggedInReservationsReadingAndHistory();
                            break;
                        case R.id.bottom_nav_home:
                            presenter.isBanedUser();
                            selectedFragment = new FirstWindowFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainActivity_fragment_container,
                            selectedFragment).addToBackStack(null).commit();
                    return true;
                }
            };
}
