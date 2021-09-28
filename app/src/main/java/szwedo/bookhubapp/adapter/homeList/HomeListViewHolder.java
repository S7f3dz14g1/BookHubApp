package szwedo.bookhubapp.adapter.homeList;

import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import szwedo.bookhubapp.R;
import szwedo.bookhubapp.adapter.OnItemClickListener;

public class HomeListViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageBook;
    private TextView titleTextView;
    private TextView authorTextView;
    private TextView publisherTextView;
    private Button moreButton;
    private RatingBar ratingBar;
    private OnItemClickListener onItemClickListener;
    private View view;
    private int idBook;

    public HomeListViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
        super(itemView);
        setComponents(itemView);
        setOnClickListener(itemView);
        view = itemView;
        this.onItemClickListener = onItemClickListener;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }


    private void setOnClickListener(View view) {
        view.setOnClickListener(onMoreButtonClickListener);
    }

    private View.OnClickListener onMoreButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onItemClickListener.onClick(idBook);
        }
    };

    private void setComponents(View view) {
        ratingBar = view.findViewById(R.id.recommended_list_rating);
        moreButton = view.findViewById(R.id.list_button_more);
        publisherTextView = view.findViewById(R.id.list_text_publisher);
        authorTextView = view.findViewById(R.id.recommended_list_text_author);
        titleTextView = view.findViewById(R.id.recommended_list_text_title);
        imageBook = view.findViewById(R.id.recommended_list_image);
    }

    public void setRatingBar(float rating) {
        ratingBar.setRating(rating);
    }

    public void setPublisherText(String publisher) {
        publisherTextView.setText(publisher);
    }

    public void setAuthorText(String author) {
        authorTextView.setText(author);
    }

    public void setTitleText(String title) {
        titleTextView.setText(title);
    }

    public void setImageBook(Uri bookUri) {
        Picasso.with(view.getContext()).load(bookUri).into(imageBook);
    }
}
