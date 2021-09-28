package szwedo.bookhubapp.adapter.darkList.large;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import szwedo.bookhubapp.R;

public class DarkLargeListRecycleViewAdapter extends RecyclerView.Adapter<DarkLargeListViewHolder> {

    private Context context;
    private int itemCount;

    public DarkLargeListRecycleViewAdapter(Context context) {
        this.context = context;
        itemCount = 5;
    }

    @NonNull
    @Override
    public DarkLargeListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.item_list_large_dark, parent, false);
        return new DarkLargeListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DarkLargeListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return itemCount;
    }
}
