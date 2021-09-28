package szwedo.bookhubapp.adapter.readingList;

import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import szwedo.bookhubapp.R;
import szwedo.bookhubapp.adapter.OnItemClickListener;
import szwedo.bookhubapp.models.HistoryBookModel;
import szwedo.bookhubapp.ui.activity.main.MainActivity;
import szwedo.bookhubapp.utils.Helper;

public class ReadingListRecycleViewAdapter extends RecyclerView.Adapter<ReadingListViewHolder> {

    private List<HistoryBookModel> booksList;
    private OnItemClickListener listener;

    public ReadingListRecycleViewAdapter(List<HistoryBookModel> booksList, OnItemClickListener listener) {
        this.booksList = booksList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ReadingListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(MainActivity.getAppContext());
        View view = mInflater.inflate(R.layout.item_list_reading, parent, false);
        return new ReadingListViewHolder(view, listener);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ReadingListViewHolder holder, int position) {
        holder.setBorrowedText(Helper.getShortDate(booksList.get(position).getDateIssued()));
        holder.setImageBook(Uri.parse(booksList.get(position).getImageUrl()));
        holder.setTitleText(booksList.get(position).getTitle());
        holder.setTimeAgoText(Helper.getReservationDate(booksList.get(position).getExpectedDate()));
        holder.setIdBook((int) booksList.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }
}
