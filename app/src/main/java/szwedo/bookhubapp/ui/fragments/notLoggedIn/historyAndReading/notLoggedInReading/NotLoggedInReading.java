package szwedo.bookhubapp.ui.fragments.notLoggedIn.historyAndReading.notLoggedInReading;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import szwedo.bookhubapp.R;
import szwedo.bookhubapp.ui.activity.login.LoginActivity;

public class NotLoggedInReading  extends Fragment {

    private ImageView loginImage;
    private TextView loginButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_no_login_reading, container, false);
        initComponents(view);
        setOnClickListener();
        return view;
    }

    private void initComponents(View view) {
        loginImage=view.findViewById(R.id.fragmentNoLoginReading_image_LoginIn);
        loginButton=view.findViewById(R.id.fragment_no_login_reading_button_login);
    }

    private void setOnClickListener(){
        loginButton.setOnClickListener(onLoginClicked);
        loginImage.setOnClickListener(onLoginClicked);
    }

    private View.OnClickListener onLoginClicked =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }
    };
}