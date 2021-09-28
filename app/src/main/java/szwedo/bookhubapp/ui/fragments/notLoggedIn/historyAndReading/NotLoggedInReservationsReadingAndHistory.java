package szwedo.bookhubapp.ui.fragments.notLoggedIn.historyAndReading;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import szwedo.bookhubapp.R;
import szwedo.bookhubapp.ui.fragments.notLoggedIn.historyAndReading.notLoggedInHistory.NotLoggedInHistory;
import szwedo.bookhubapp.ui.fragments.notLoggedIn.historyAndReading.notLoggedInReading.NotLoggedInReading;
import szwedo.bookhubapp.ui.fragments.notLoggedIn.historyAndReading.notLoggedInReservations.NotLoggedInReservations;

public class NotLoggedInReservationsReadingAndHistory extends Fragment {

    private Button readingFragmentBtn;
    private Button historyFragmentBtn;
    private Button reservationFragmentBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_no_log_reservations_reading_and_history__main, container, false);
        initComponents(view);
        setOnClickListener();
        setReadingFragment();
        return view;
    }

    private void initComponents(View view) {
        readingFragmentBtn=view.findViewById(R.id.NoLoginReadingAndHistory_button_reading);
        historyFragmentBtn=view.findViewById(R.id.NoLoginReadingAndHistory_button_history);
        reservationFragmentBtn=view.findViewById(R.id.NoLoginReadingAndHistory_button_reservations);
    }

    private void  setOnClickListener(){
        readingFragmentBtn.setOnClickListener(onReadingClickedListener);
        historyFragmentBtn.setOnClickListener(onHistoryClickedListener);
        reservationFragmentBtn.setOnClickListener(onReservationClickedListener);
    }

    private final View.OnClickListener onReservationClickedListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setReservationFragment();
        }
    };

    private final View.OnClickListener onReadingClickedListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setReadingFragment();
        }
    };

    private final View.OnClickListener onHistoryClickedListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setHistoryFragment();
        }
    };

    private void setReadingFragment(){
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.notLogReadingAndHistory_fragmentContainer,new NotLoggedInReading())
                .addToBackStack(null).commit();
    }

    private void setReservationFragment(){
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.notLogReadingAndHistory_fragmentContainer,new NotLoggedInReservations())
                .addToBackStack(null).commit();
    }

    private void setHistoryFragment(){
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.notLogReadingAndHistory_fragmentContainer,new NotLoggedInHistory())
                .addToBackStack(null).commit();
    }
}
