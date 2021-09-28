package szwedo.bookhubapp.adapter.copiesOfBookList;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.skydoves.balloon.Balloon;
import com.skydoves.balloon.BalloonAnimation;

import szwedo.bookhubapp.R;
import szwedo.bookhubapp.adapter.OnItemClickListener;
import szwedo.bookhubapp.ui.activity.main.MainActivity;

public class CopiesOfBookListViewHolder extends RecyclerView.ViewHolder {

    private TextView bookId;
    private TextView status;
    private ImageView image;
    private OnItemClickListener onItemClickListener;
    private View view;
    private Balloon balloon;
    private ImageView informationImage;
    private boolean checked;

    public CopiesOfBookListViewHolder(@NonNull View itemView,OnItemClickListener onItemClickListener) {
        super(itemView);
        setComponents(itemView);
        view = itemView;
        setOnclickListeners(itemView);
        checked =false;
        this.onItemClickListener =onItemClickListener;
    }

    private void setOnclickListeners(View view) {
        informationImage.setOnClickListener(onImageClicked);
        view.setOnClickListener(onItemClicked);
    }

    public boolean isChecked() {
        return checked;
    }

    public void clean(){
        view.setBackground(view.getResources().getDrawable(R.drawable.white_rounded_panel));
        checked=false;
    }

    private final View.OnClickListener onItemClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!checked){
                onItemClickListener.onClick(Integer.parseInt(bookId.getText().toString().replace("#","")));
                v.setBackground(v.getResources().getDrawable(R.drawable.clicked_item_style));
                checked=true;
            }
            else{
                v.setBackground(v.getResources().getDrawable(R.drawable.white_rounded_panel));
                onItemClickListener.onClick(0);
                checked=false;
            }
        }
    };

    private View.OnClickListener onImageClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            balloon.show(v);
        }
    };

    private void setComponents(View view) {
        bookId = view.findViewById(R.id.CopiesOfBook_text_copyNumber);
        status = view.findViewById(R.id.CopiesOfBook_text_status);
        image = view.findViewById(R.id.CopiesOfBook_image_bookmark);
        informationImage=view.findViewById(R.id.CopiesOfBook_image_info);
    }

    public void setImage(int image) {
        this.image.setBackgroundResource(image);
    }

    public void setBookId(String bookId) {
        this.bookId.setText(bookId);
    }

    public void setStatus(String status) {
        this.status.setText(status);
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

    public void setBackground(){
        view.setBackground(view.getResources().getDrawable(R.drawable.white_rounded_panel));
    }
}
