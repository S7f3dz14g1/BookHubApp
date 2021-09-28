package szwedo.bookhubapp.ui.dialogs.banedUser;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.content.ContextCompat;

import com.skydoves.balloon.Balloon;
import com.skydoves.balloon.BalloonAnimation;

import szwedo.bookhubapp.R;
import szwedo.bookhubapp.ui.activity.main.MainActivity;

public class BanedUserDialog extends AppCompatDialogFragment {

    private Button cancel;
    private Balloon balloon;
    private ImageView informationImage;

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_banner_user, null);
        initComponents(view);
        setOnClickListeners();
        builder.setView(view);
        return builder.create();
    }

    private void initComponents(View view) {
        cancel = view.findViewById(R.id.banner_user_button_cancel);
        informationImage = view.findViewById(R.id.banner_user_imageView);
    }

    private void setOnClickListeners() {
        cancel.setOnClickListener(onCancelClicked);
        informationImage.setOnClickListener(onImageClicked);
    }

    public void setBalloon(String message) {
        balloon = new Balloon.Builder(MainActivity.getAppContext())
                .setArrowVisible(false)
                .setWidthRatio(1.0f)
                .setHeight(78)
                .setTextSize(18f)
                .setArrowPosition(0.62f)
                .setCornerRadius(4f)
                .setAlpha(0.9f)
                .setText(message)
                .setMarginBottom(5)
                .setMarginLeft(16)
                .setMarginRight(16)
                .setTextColor(ContextCompat.getColor(MainActivity.getAppContext(), R.color.colorWhite))
                .setBackgroundColor(ContextCompat.getColor(MainActivity.getAppContext(), R.color.colorPrimaryGreen))
                .setBalloonAnimation(BalloonAnimation.FADE)
                .setAutoDismissDuration(5000L)
                .build();
    }

    private View.OnClickListener onImageClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setBalloon(getString(R.string.If_you_want_to_learn));
            balloon.show(v);
        }
    };

    private View.OnClickListener onCancelClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dismiss();
        }
    };
}