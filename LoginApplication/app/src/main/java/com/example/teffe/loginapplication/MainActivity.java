package com.example.teffe.loginapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnProsseguir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        incicializarElementos();
    }

    private void incicializarElementos() {
        btnProsseguir = findViewById(R.id.button);
        btnProsseguir.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                prosseguir();
                break;
        }
    }

    private void prosseguir() {
        Intent intent = new Intent(this, PasswordActivity.class);
        startActivity(intent);
    }
}
