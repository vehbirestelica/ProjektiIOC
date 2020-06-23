package com.fiek.projektiioc;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.protobuf.StringValue;

public class FakturatFragment extends Fragment {
    Button btnNewInvoice;
    Button btnMyInvoices;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fakturat,container,false);
        int i = 0;
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            i = bundle.getInt("Compani Id", 19999);
        }

        Log.d("companid", "onCreateView: " + i);

        btnNewInvoice = (Button) view.findViewById(R.id.btnfaktura);
        btnNewInvoice.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),NewInvoiceActivity.class);
                startActivity(intent);
            }
        });
        btnMyInvoices = (Button) view.findViewById(R.id.myInvbtn);
        if(i>10000 && i <20000){
            btnMyInvoices.setVisibility(View.GONE);
        }
        else {
            btnMyInvoices.setVisibility(View.VISIBLE);
        }
        btnMyInvoices.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MyInvoiceActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}