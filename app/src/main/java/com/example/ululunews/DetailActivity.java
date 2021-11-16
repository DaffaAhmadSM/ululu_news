package com.example.ululunews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DetailActivity extends AppCompatActivity {
    private ImageView img, back, detailnya, btn;
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
        btn = findViewById(R.id.btn_favorite);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        bundle();
    }

    private void bundle() {
        img = findViewById(R.id.imgdetail);
        title = findViewById(R.id.juduldetail);
        description = findViewById(R.id.deskripsidetail);

        source = findViewById(R.id.sourcedetail);
        bundle = getIntent().getExtras();
        Realm.init(DetailActivity.this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().allowWritesOnUiThread(true).build();
        Realm realm = Realm.getInstance(configuration);
        RealmHelper realmHelper = new RealmHelper(realm);

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

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Model model = new Model(id, judul, deskripsi,sumber, imgdetail);
                    realmHelper.save(model);
                    Toast.makeText(getApplicationContext(),"Behasil masuk favorite", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}