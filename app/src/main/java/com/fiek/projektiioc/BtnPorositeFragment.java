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
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_btn_porosite, container, false);

        btnOrderCompleted = (Button) view.findViewById(R.id.btn_orderCompleted);
        btnOrderCompleted.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),OrdersActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}