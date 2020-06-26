package com.fiek.projektiioc;

import android.content.Intent;
import android.os.Bundle;
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
    Button getBtnOrderNotPaid;
    Button addNewOrder;
    Button btnnewproducts;
    Button btnMyOrders;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_btn_porosite, container, false);

        addNewOrder = (Button) view.findViewById(R.id.btn_newOrder);
        addNewOrder.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent1 = new Intent(getActivity(), NewOrdersActivity.class);
                startActivity(intent1);
            }
        });

        btnOrderCompleted = (Button) view.findViewById(R.id.btn_orderCompleted);
        btnOrderCompleted.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),OrdersActivity.class);
                startActivity(intent);
            }
        });

        btnOrderProcessing = (Button) view.findViewById(R.id.btn_orderOnProces);
        btnOrderProcessing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent intent2 = new Intent(getActivity(),ProcessingOrders.class);
                startActivity(intent2);
            }
        });

        getBtnOrderNotPaid = (Button) view.findViewById(R.id.btn_orderCancel2);
        getBtnOrderNotPaid.setOnClickListener(new View.OnClickListener() {
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