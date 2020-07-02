package com.fiek.projektiioc.Faturat;

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

import com.fiek.projektiioc.R;

public class FakturatFragment extends Fragment {
    Button btnNewInvoice;
    Button btnMyInvoices;
    Button btnAllInvoices;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fakturat,container,false);
        int companyId = 0;
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            companyId = bundle.getInt("Company Id", 19999);
        }

        Log.d("companyid", "onCreateView: " + companyId);

        btnNewInvoice = (Button) view.findViewById(R.id.btnfaktura);
        btnNewInvoice.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewInvoiceActivity.class);
                startActivity(intent);
            }
        });
        btnMyInvoices = (Button) view.findViewById(R.id.myInvbtn);
        btnMyInvoices.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyInvoiceActivity.class);
                startActivity(intent);
            }
        });

        btnAllInvoices = view.findViewById(R.id.allinvoicesbtn);
        if(companyId>10000 && companyId <20000){
            btnAllInvoices.setVisibility(View.VISIBLE);
        }
        else {
            btnAllInvoices.setVisibility(View.GONE);
        }
        btnAllInvoices.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), All_InvoicesActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}