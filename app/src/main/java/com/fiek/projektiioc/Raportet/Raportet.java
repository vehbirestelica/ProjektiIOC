package com.fiek.projektiioc.Raportet;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fiek.projektiioc.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class Raportet extends Fragment {

    private String mParam1;
    private String mParam2;

    TextView TotalOrders;
    TextView TotalProccesing;
    TextView TotalPaid;
    TextView TotalNotPaid;

    //private DatabaseReference Database;
    private int countdata;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_raportet, container, false);

        TotalNotPaid = view.findViewById(R.id.notpaidOrderstxt);
        TotalPaid = view.findViewById(R.id.paidOrderstxt);
        TotalOrders = view.findViewById(R.id.totalOrderstxt);
        TotalProccesing = view.findViewById(R.id.processingOrderstxt);

        //Database = FirebaseDatabase.getInstance().getReference();

        Query query = FirebaseDatabase.getInstance().getReference("addOrder").orderByChild("statusi").equalTo("Pa Paguar");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    countdata =(int) snapshot.getChildrenCount();
                    TotalNotPaid.setText(Integer.toString(countdata));

                }
                else {
                    TotalNotPaid.setText("0");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Query query2 = FirebaseDatabase.getInstance().getReference("addOrder").orderByChild("statusi").equalTo("Paguar");

        query2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    countdata =(int) snapshot.getChildrenCount();
                    TotalPaid.setText(Integer.toString(countdata));

                }
                else {
                    TotalNotPaid.setText("0");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Query query3 = FirebaseDatabase.getInstance().getReference("addOrder").orderByChild("statusi").equalTo("Ne Proces");

        query3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    countdata =(int) snapshot.getChildrenCount();
                    TotalProccesing.setText(Integer.toString(countdata));

                }
                else {
                    TotalNotPaid.setText("0");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Query query4 = FirebaseDatabase.getInstance().getReference("addOrder");

        query4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    countdata =(int) snapshot.getChildrenCount();
                    TotalOrders.setText(Integer.toString(countdata));

                }
                else {
                    TotalNotPaid.setText("0");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        return  view;
    }
}