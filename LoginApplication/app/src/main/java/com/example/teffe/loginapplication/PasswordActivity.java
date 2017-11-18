package com.example.teffe.loginapplication;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PasswordActivity extends AppCompatActivity {

    TextView tvQueroSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        inicializarElementos();
    }

    private void inicializarElementos() {
        tvQueroSair = findViewById(R.id.tv_quero_sair);
        tvQueroSair.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
    }
}
