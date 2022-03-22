package com.danielstone.materialaboutlibrarydemo.dfinitionetutilisationdefragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class BlankFragment extends Fragment {

    private EditText txtLogin;
    private EditText txtPassword;
    private Button btnConnect;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_blank, container, true);

        txtLogin = (EditText) rootView.findViewById(R.id.txtLogin);
        txtPassword = (EditText) rootView.findViewById(R.id.txtPassword);
        btnConnect = (Button) rootView.findViewById(R.id.btn_submit);

        btnConnect.setOnClickListener(btnConnectListener);

        return rootView;

    }

    private View.OnClickListener btnConnectListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.i("DEBUG", txtLogin.getText() + "/" + txtPassword.getText());
        }
    };
}