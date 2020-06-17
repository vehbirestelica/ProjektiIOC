package com.fiek.projektiioc;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FakturatFragment extends Fragment {
    Button btnNewInvoice;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fakturat,container,false);

        btnNewInvoice = (Button) view.findViewById(R.id.btnfaktura);
        btnNewInvoice.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),NewInvoiceActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}