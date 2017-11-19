package dev.nullpointer.emergencycall.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import dev.nullpointer.emergencycall.R;
import dev.nullpointer.emergencycall.model.Items;


/**
 * Created by umarfadil on 11/19/17.
 */

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.MyViewHolder> {

    private Context context;
    private List<Items> itemsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        }
    }

    public ItemsAdapter(Context mContext, List<Items> items) {
        this.context = mContext;
        this.itemsList = items;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Items item = itemsList.get(position);
        holder.title.setText(item.getTitle());

        //Loading Image
        Picasso.with(context).load(item.getThumbnail()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }
}