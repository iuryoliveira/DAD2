package com.example.iury.gerenciadordeenergiaeltrica;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.nio.DoubleBuffer;

public class simulacao extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Spinner spiner;
    ArrayAdapter<CharSequence> adapter;
    Boolean isVisible = true;

    DatabaseHelper myDb;
    Button listarTodos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulacao);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Simulação");
        myDb = new DatabaseHelper(this);
        listarTodos = (Button) findViewById(R.id.btnListar);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.limpar) {
                    Toast.makeText(getApplicationContext(), "Clicou em settings", Toast.LENGTH_SHORT).show();
                    return true;
                }
                else {
                    Toast.makeText(getApplicationContext(), "Não clicou", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        });

        //Alimentando o Spinner de aparelhos eletrônicos
        spiner = (Spinner) findViewById(R.id.spinnerRecurso);
        adapter = ArrayAdapter.createFromResource(this, R.array.nome_recursos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner.setAdapter(adapter);

        spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //Fim spinner

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.simulacao, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_recurso) {
            // Trata a ação Recurso
            Intent recursoIntent = new Intent(this, MainActivity.class);
            startActivity(recursoIntent);
            finish();
        } else if (id == R.id.nav_consumo) {
            Intent consumoIntent = new Intent(this, Consumo.class);
            startActivity(consumoIntent);
            finish();
        } else if(id == R.id.nav_historico){
            Intent consumoIntent = new Intent(this, simulacao.class);
            startActivity(consumoIntent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isVisible = false;
    }

    public void listarTodos(View view){
       Cursor res =  myDb.getAllData();

       if(res.getCount() == 0){
           showMessages("Erro", "Não há dados");
           return;
       }

        StringBuffer buffer = new StringBuffer();

        while (res.moveToNext()) {
            buffer.append(" Tipo: " + res.getString(1)+"\n");
            buffer.append(" Voltagem: " + res.getString(2)+"\n \n");
        }

        showMessages("Dados", buffer.toString() + "\n Soma das voltagens: " + myDb.getSum().toString());
    }

    private void showMessages(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}


