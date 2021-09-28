package szwedo.bookhubapp.adapter.copiesOfBookList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import szwedo.bookhubapp.R;
import szwedo.bookhubapp.adapter.OnItemClickListener;
import szwedo.bookhubapp.models.CopiesOfBookModel;
import szwedo.bookhubapp.ui.activity.main.MainActivity;
import szwedo.bookhubapp.utils.Helper;

public class CopiesOfBookListRecycleViewAdapter extends RecyclerView.Adapter<CopiesOfBookListViewHolder> implements OnItemClickListener {

    private Context context;
    private List<CopiesOfBookModel> booksList;
    private long idBook;
    private List<CopiesOfBookListViewHolder> holders;

    public CopiesOfBookListRecycleViewAdapter(Context context, List<CopiesOfBookModel> booksList) {
        this.context = context;
        this.booksList = booksList;
        holders = new ArrayList<>();
    }

    @NonNull
    @Override
    public CopiesOfBookListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.item_list_copies_of_books, parent, false);
        return new CopiesOfBookListViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull CopiesOfBookListViewHolder holder, int position) {
        setBookDetails(holder, position);
    }

    private void setBookDetails(CopiesOfBookListViewHolder holder, int position) {
        holders.add(holder);
        holder.setBookId("#" + booksList.get(position).getId());
        if (!booksList.get(position).isBorrow()) {
            if (booksList.get(position).isAccess()) {
                holder.setStatus(MainActivity.getAppContext().getString(R.string.available_second));
                holder.setBalloon(MainActivity.getAppContext().getString(R.string.book_available_borrow_home));
            } else {
                holder.setStatus(MainActivity.getAppContext().getString(R.string.restricted_access));
                holder.setBalloon(MainActivity.getAppContext().getString(R.string.book_available_borrow_library));
            }
        } else {
            holder.setImage(R.drawable.bookmark_yellow);
            holder.setStatus(MainActivity.getAppContext().getString(R.string.occupied));
            holder.setBalloon(MainActivity.getAppContext().getString(R.string.available_from) + Helper.getShortDate(booksList.get(position).getApproximateDate()));
        }
    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }

    @Override
    public void onClick(int idBook) {
        this.idBook = idBook;
        cleanAll();
    }

    private void cleanAll() {
        for (CopiesOfBookListViewHolder holder : holders) {
            if (holder.isChecked())
                holder.clean();
        }
    }

    public long getIdBook() {
        return idBook;
    }
}
