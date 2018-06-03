package com.example.iury.gerenciadordeenergiaeltrica;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class cadastro_usuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.limpar) {
                    Toast.makeText(getApplicationContext(), "Limpeza efetuada", Toast.LENGTH_SHORT).show();
                    limpar();
                    return true;
                }
                else if(item.getItemId() == R.id.cadastrarForm){
                    cadastrar();
                    return true;
                }
                else {
                    Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cadastro_usuario, menu);
        return true;
    }

    private void cadastrar(){

    }

    private void limpar(){

    }

}
