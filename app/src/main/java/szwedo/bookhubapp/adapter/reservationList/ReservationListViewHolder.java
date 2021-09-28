package szwedo.bookhubapp.adapter.reservationList;

import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import szwedo.bookhubapp.R;
import szwedo.bookhubapp.adapter.OnItemClickListener;

public class ReservationListViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageBook;
    private TextView titleTextView;
    private TextView reservationTextView;
    private TextView timeAgoTextView;
    private Button cancel;
    private OnItemClickListener onItemClickListener;
    private View view;
    private int idBook;

    public ReservationListViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
        super(itemView);
        this.onItemClickListener = onItemClickListener;
        initComponents(itemView);
        setOnCLickListeners();
        view = itemView;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    private void initComponents(View itemView) {
        imageBook = itemView.findViewById(R.id.reservation_item_list_image);
        titleTextView = itemView.findViewById(R.id.reservation_item_list_text_title);
        reservationTextView = itemView.findViewById(R.id.reservation_item_list_text_dateOfBorrow);
        timeAgoTextView = itemView.findViewById(R.id.reservation_item_list_text_timeAgo);
        cancel = itemView.findViewById(R.id.reservation_item_list_button_Cancel);
    }

    private void setOnCLickListeners() {
        cancel.setOnClickListener(onCancelClicked);
    }

    private View.OnClickListener onCancelClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onItemClickListener.onClick(idBook);
        }
    };

    public void setImageBook(Uri bookUri) {
        Picasso.with(view.getContext()).load(bookUri).into(imageBook);
    }

    public void setTitleTextView(String title) {
        titleTextView.setText(title);
    }

    public void setReservationTextView(String reservation) {
        this.reservationTextView.setText(reservation);
    }

    public void setTimeAgoTextView(String timeAgo) {
        this.timeAgoTextView.setText(timeAgo);
    }

}
