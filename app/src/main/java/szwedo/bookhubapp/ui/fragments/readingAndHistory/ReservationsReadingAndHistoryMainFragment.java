package szwedo.bookhubapp.ui.fragments.readingAndHistory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import szwedo.bookhubapp.R;
import szwedo.bookhubapp.ui.fragments.readingAndHistory.history.HistoryFragment;
import szwedo.bookhubapp.ui.fragments.readingAndHistory.reading.ReadingFragment;
import szwedo.bookhubapp.ui.fragments.readingAndHistory.reservation.ReservationFragment;

public class ReservationsReadingAndHistoryMainFragment extends Fragment {

    private Button reading;
    private Button reservation;
    private Button history;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reservations_reading_and_history, container, false);
        initComponents(view);
        setOnClickListeners();
        setReadingFragment();
        setReservationFragment();
        return view;
    }

    private void initComponents(View view) {
        reading = view.findViewById(R.id.readingAndHistory_button_reading);
        reservation = view.findViewById(R.id.readingAndHistory_button_reservations);
        history = view.findViewById(R.id.readingAndHistory_button_history);
    }

    private void setOnClickListeners() {
        reading.setOnClickListener(onReadingClickedListener);
        history.setOnClickListener(onHistoryClickedListener);
        reservation.setOnClickListener(onReservationClickedListener);
    }

    private final View.OnClickListener onReadingClickedListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setReadingFragment();
        }
    };

    private final View.OnClickListener onHistoryClickedListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setHistoryFragment();
        }
    };

    private final View.OnClickListener onReservationClickedListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setReservationFragment();
        }
    };

    private void setReservationFragment() {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.readingAndHistory_fragmentContainer, new ReservationFragment())
                .addToBackStack(null).commit();
    }

    private void setReadingFragment() {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.readingAndHistory_fragmentContainer, new ReadingFragment())
                .addToBackStack(null).commit();
    }

    private void setHistoryFragment() {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.readingAndHistory_fragmentContainer, new HistoryFragment())
                .addToBackStack(null).commit();
    }
}