package szwedo.bookhubapp.adapter.readingList;

import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import szwedo.bookhubapp.R;
import szwedo.bookhubapp.adapter.OnItemClickListener;
import szwedo.bookhubapp.ui.activity.main.MainActivity;

public class ReadingListViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageBook;
    private TextView titleTextView;
    private TextView borrowedTextView;
    private TextView timeAgoTextView;
    private TextView button;
    private OnItemClickListener listener;
    private int idBook;

    public ReadingListViewHolder(@NonNull View itemView, OnItemClickListener listener) {
        super(itemView);
        this.listener = listener;
        setComponents(itemView);
        setOnclickListener();
    }

    private void setOnclickListener() {
        button.setOnClickListener(onButtonClicked);
    }

    private View.OnClickListener onButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            listener.onClick(idBook);
        }
    };

    private void setComponents(View view) {
        imageBook = view.findViewById(R.id.reading_item_list_image);
        titleTextView = view.findViewById(R.id.reading_item_list_textView_title);
        borrowedTextView = view.findViewById(R.id.reading_item_list_textView_dateOfBorrow);
        timeAgoTextView = view.findViewById(R.id.reading_item_list_textView_dateOfBorrow2);
        button = view.findViewById(R.id.reading_item_list_button_extend_book);
    }

    public void setTimeAgoText(String timeAgoText) {
        timeAgoTextView.setText(timeAgoText);
    }

    public void setBorrowedText(String date) {
        borrowedTextView.setText(date);
    }

    public void setTitleText(String title) {
        titleTextView.setText(title);
    }

    public void setImageBook(Uri bookUri) {
        Picasso.with(MainActivity.getAppContext()).load(bookUri).into(imageBook);
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }
}
