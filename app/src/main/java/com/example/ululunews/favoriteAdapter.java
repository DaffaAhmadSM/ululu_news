package com.example.ululunews;

import android.content.Context;
import android.content.DialogInterface;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class favoriteAdapter extends RecyclerView.Adapter<favoriteAdapter.ViewHolder> {
    private Context context;
    public Callback callback;
    Realm realm;
    View viewku;
    private List<Model> model;
    public interface Callback {
        void onClick(int position);
    }
    public favoriteAdapter( Context context, List<Model> model, Callback callback) {
        this.callback = callback;
        this.context = context;
        this.model = model;
    }
    RealmConfiguration configuration = new RealmConfiguration.Builder().allowWritesOnUiThread(true).build();
    @NonNull
    @Override
    public favoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull favoriteAdapter.ViewHolder holder, int position) {
        holder.source.setText(model.get(position).getSumber());
        holder.title.setText(model.get(position).getJudul());
        Picasso.get().load(model.get(position).getGambar())
                .fit()
                .into(holder.image);
        holder.posku = model.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return (model != null) ? model.size() : 0;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private RelativeLayout relativeLayout;
        private TextView title, source;
        private  ImageView image;
        private Integer posku;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            viewku = itemView;
            itemView.setOnCreateContextMenuListener(this::onCreateContextMenu);
            image = itemView.findViewById(R.id.imglist);
            title = itemView.findViewById(R.id.judul);
            source = itemView.findViewById(R.id.source);
            relativeLayout = itemView.findViewById(R.id.relative);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { callback.onClick(getAdapterPosition()); }

            });
        }
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuItem Delete = menu.add(Menu.NONE, 1, 1, "Hapus");
            Delete.setOnMenuItemClickListener(this::onMenuItemClick);
        }
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case 1:
                    AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                    builder.setMessage("Apakah kamu mau menghapus item ini?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    realm = Realm.getInstance(configuration);
                                    RealmHelper realmHelper = new RealmHelper(realm);
                                    realmHelper.delete(posku);
                                    notifyDataSetChanged();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            })
                            //Set your icon here
                            .setTitle("Hapus data");
                    AlertDialog alert = builder.create();
                    alert.show();//showing the dialog

                    break;
            }
            return true;
        }
    }

}