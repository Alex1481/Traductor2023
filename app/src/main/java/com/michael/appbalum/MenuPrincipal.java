package com.michael.appbalum;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.michael.appbalum.mainModule.view.MainActivity;

public class MenuPrincipal extends AppCompatActivity {

    private Button AVocabulario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_principal);

        AVocabulario = (Button) findViewById(R.id.btnVocabulario);

        AVocabulario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPrincipal.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
