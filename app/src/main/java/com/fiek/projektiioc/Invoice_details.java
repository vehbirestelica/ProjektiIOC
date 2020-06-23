package com.fiek.projektiioc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class Invoice_details extends AppCompatActivity {

    TextView title,type,sum,comment,id;
    ImageView invphoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_details);

        Intent intent = getIntent();
        Invoice invoice = intent.getParcelableExtra("invoice");



        title = findViewById(R.id.inv_det_title);
        type = findViewById(R.id.inv_det_type);
        sum = findViewById(R.id.inv_det_sum);
        comment = findViewById(R.id.inv_det_comment);
        id = findViewById(R.id.inv_det_invId);

        invphoto = findViewById(R.id.inv_det_photo);

        title.setText(invoice.getInvTitle());
        type.setText(invoice.getInvType());
        sum.setText(invoice.getInvSum());
        comment.setText(invoice.getInvComment());
        id.setText(invoice.getInvId());

        Picasso.get().load(invoice.getInvPhotoUri()).into(invphoto);
        

    }
}