package com.example.ululunews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{
    private List<Model> model;
    private Callback callback;
    private Context context;
    interface Callback {
        void call(int position);
    }
    public  MainAdapter(Context context, List<Model> model, Callback callback){
        this.context = context;
        this.model = model;
        this.callback = callback;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Model i = model.get(position);
        holder.judul.setText(i.getJudul());
        holder.sumber.setText(i.getSumber());
        Picasso.get()
                .load(model.get(position).getGambar())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher_round)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return (model != null) ? model.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView judul, sumber;
        private RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imglist);
            judul = itemView.findViewById(R.id.judul);
            sumber = itemView.findViewById(R.id.source);
            relativeLayout = itemView.findViewById(R.id.relative);
            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.call(getAdapterPosition());
                }
            });
        }
    }
}
