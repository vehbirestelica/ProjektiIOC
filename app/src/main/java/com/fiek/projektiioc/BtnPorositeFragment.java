package com.fiek.projektiioc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class BtnPorositeFragment extends Fragment {
    Button btnOrderCompleted;
    Button btnOrderProcessing;
    Button btnOrderNotPaid;
    Button addNewOrder;
    Button btnnewproducts;
    Button btnMyOrders;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_btn_porosite, container, false);
        int companyID = 0;
        Bundle bundle = this.getArguments();
        if (bundle != null){
            companyID = bundle.getInt("Company ID:", 19999);
        }
        Log.d("companyid", "onCreateView: " + companyID);

        addNewOrder = (Button) view.findViewById(R.id.btn_newOrder);
        addNewOrder.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent1 = new Intent(getActivity(), NewOrdersActivity.class);
                startActivity(intent1);
            }
        });

        btnOrderCompleted = (Button) view.findViewById(R.id.btn_orderCompleted);
        if(companyID>10000 && companyID<20000){
            btnOrderCompleted.setVisibility(View.VISIBLE);
        } else {
            btnOrderCompleted.setVisibility(View.GONE);
        }
        btnOrderCompleted.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),OrdersActivity.class);
                startActivity(intent);
            }
        });

        btnOrderProcessing = (Button) view.findViewById(R.id.btn_orderOnProces);
        if(companyID>10000 && companyID<20000){
            btnOrderProcessing.setVisibility(View.VISIBLE);
        } else {
            btnOrderProcessing.setVisibility(View.GONE);
        }
        btnOrderProcessing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent intent2 = new Intent(getActivity(),ProcessingOrders.class);
                startActivity(intent2);
            }
        });

        btnOrderNotPaid = (Button) view.findViewById(R.id.btn_orderCancel2);
        if(companyID>10000 && companyID<20000){
            btnOrderNotPaid.setVisibility(View.VISIBLE);
        } else {
            btnOrderNotPaid.setVisibility(View.GONE);
        }
        btnOrderNotPaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent intent3 = new Intent(getActivity(),NotPaidOrders.class);
                startActivity(intent3);
            }
        });

        btnnewproducts = (Button) view.findViewById(R.id.btnPorosite);
        btnnewproducts.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),newProducts.class);
                startActivity(intent);
            }
        });

        btnMyOrders = (Button) view.findViewById(R.id.myOrders);
        btnMyOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent intent = new Intent(getActivity(),MyOrders.class);
                startActivity(intent);
            }
        });

        return view;
    }
}