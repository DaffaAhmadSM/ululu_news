package com.example.ululunews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private ImageView img, back, detailnya;
    private TextView title,description,source;
    private  String imgdetail,judul,deskripsi,sumber;
    private Integer id;
    private Bundle bundle;
    private List<Model> modelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        TextView textView = findViewById(R.id.linkselengkapnya);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        bundle();
    }

    private void bundle() {
        img = findViewById(R.id.imgdetail);
        title = findViewById(R.id.juduldetail);
        description = findViewById(R.id.deskripsidetail);
        source = findViewById(R.id.sourcedetail);
        bundle = getIntent().getExtras();

        if (bundle != null){
            judul = bundle.getString("judul");
            deskripsi = bundle.getString("deskripsi");
            sumber = bundle.getString("sumber");
            imgdetail = bundle.getString("gambar");

            title.setText(judul);
            description.setText(deskripsi);
            source.setText(sumber);

            Picasso.get()
                    .load(imgdetail)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher_round)
                    .into(img);

        }
    }
}