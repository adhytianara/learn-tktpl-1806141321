package id.ac.ui.cs.mobileprogramming.adhytia1806141321.helloworld;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListDestinationAdapter extends RecyclerView.Adapter<ListDestinationAdapter.ListViewHolder> {
    private ArrayList<Destination> destinationsList;
    private OnItemClickCallback onItemClickCallback;

    public ListDestinationAdapter(ArrayList<Destination> list) {
        this.destinationsList = list;
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public ListDestinationAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_destination, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListDestinationAdapter.ListViewHolder holder, int position) {
        Destination destination = destinationsList.get(position);
        holder.iv_foto.setImageResource(destination.getFoto());
        holder.tv_name.setText(destination.getNama());
        holder.tv_description.setText(destination.getDeskripsi());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(destinationsList.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return destinationsList.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_destination_name)
        TextView tv_name;

        @BindView(R.id.iv_foto)
        ImageView iv_foto;

        @BindView(R.id.tv_description)
        TextView tv_description;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(Destination data);
    }
}
