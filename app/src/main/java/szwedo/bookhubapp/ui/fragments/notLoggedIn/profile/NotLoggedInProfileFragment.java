package szwedo.bookhubapp.ui.fragments.notLoggedIn.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import szwedo.bookhubapp.R;
import szwedo.bookhubapp.ui.activity.login.LoginActivity;
import szwedo.bookhubapp.ui.activity.register.RegisterActivity;
import szwedo.bookhubapp.ui.fragments.firstWindow.FirstWindowFragment;

public class NotLoggedInProfileFragment extends Fragment {

    private Button homeFragmentBtn;
    private TextView loginBtn;
    private TextView registrationBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_not_logged_in, container, false);
        initComponents(view);
        setOnClickListener();
        return view;
    }

    private void setOnClickListener() {
        homeFragmentBtn.setOnClickListener(onHomeBtnClickedListener);
        loginBtn.setOnClickListener(onLoginBanClickedListener);
        registrationBtn.setOnClickListener(onRegistrationBanClickedListener);
    }

    private View.OnClickListener onHomeBtnClickedListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openHomeFragment();
        }
    };

    private View.OnClickListener onRegistrationBanClickedListener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openRegistrationActivity();
        }
    };

    private View.OnClickListener onLoginBanClickedListener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openLoginActivity();
        }
    };

    private void openRegistrationActivity(){
        Intent intent=new Intent(getContext(), RegisterActivity.class);
        startActivity(intent);
    }

    private void openLoginActivity(){
        Intent intent=new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
    }

    private void openHomeFragment(){
        getActivity().getSupportFragmentManager().beginTransaction().
                replace(((ViewGroup) getView().getParent()).getId(),new FirstWindowFragment())
                .addToBackStack(getView().getClass().getName())
                .commit();
    }

    private void initComponents(View view) {
        homeFragmentBtn =view.findViewById(R.id.no_internet_profile_welcome_button_GoToTheLibrary);
        loginBtn=view.findViewById(R.id.no_internet_profile_welcome_btn_login);
        registrationBtn=view.findViewById(R.id.no_internet_profile_welcome_btn_register);
    }
}
