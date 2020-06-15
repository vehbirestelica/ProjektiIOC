package com.fiek.projektiioc;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class MainFragment extends Fragment {
    //Qekjo variabla onfragmentBtnSelected eshte vetem shembull ma posht
  private onFragemntBtnSelected listener;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main,container,false);


        ///shembullllll
        Button clickme = view.findViewById(R.id.loadfragment);
        clickme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onButtonSelected();
            }
        });



        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof onFragemntBtnSelected) {
            listener = (onFragemntBtnSelected) context;
        } else {
            throw new ClassCastException(context.toString() + "must implement listener");

        }


    }

    //shembull se si te implemtentohet klikimi i nje buttoni ne nje fragment
    public  interface onFragemntBtnSelected {
     public void onButtonSelected();


    }

}