package szwedo.bookhubapp.adapter.historyList;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import szwedo.bookhubapp.R;
import szwedo.bookhubapp.models.HistoryBookModel;
import szwedo.bookhubapp.utils.Helper;

public class HistoryListRecycleViewAdapter extends RecyclerView.Adapter<HistoryListViewHolder> {

    private Context context;
    private List<HistoryBookModel> booksList;

    public HistoryListRecycleViewAdapter(Context context, List<HistoryBookModel> booksList) {
        this.context = context;
        this.booksList = booksList;
    }

    @NonNull
    @Override
    public HistoryListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.item_list_history, parent, false);
        return new HistoryListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryListViewHolder holder, int position) {
        holder.setBorrowedText(Helper.getShortDate(booksList.get(position).getDateIssued()));
        holder.setImageBook(Uri.parse(booksList.get(position).getImageUrl()));
        holder.setReturnedText(Helper.getShortDate(booksList.get(position).getDateReturn()));
        holder.setTitleText(booksList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }
}
