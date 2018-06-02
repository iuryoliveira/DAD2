package com.example.iury.gerenciadordeenergiaeltrica;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Runnable {


    Spinner spiner;
    ArrayAdapter<CharSequence> adapter;

    ImageView imageView;
    Button botaoImagem;
    private final int PICK_IMAGE = 100;
    Boolean isVisible = true;
    Uri imageUri;
    RecursoHelper helper;

    //DatabaseHelper myDb;
    RecursoDAO recursoDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Recursos");

//        Instanciando o banco de dados
        //myDb = new DatabaseHelper(getApplicationContext());
        recursoDAO = new RecursoDAO(getApplicationContext());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.helper = new RecursoHelper(this);

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
        //Alimentando o Spinner de aparelhos eletrônicos
        spiner = (Spinner) findViewById(R.id.spinnerRecurso);
        adapter = ArrayAdapter.createFromResource(this, R.array.nome_recursos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner.setAdapter(adapter);

        spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getApplicationContext(), adapterView.getItemAtPosition(i) + " is selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //Fim spinner

        //Teste imagePicker
        imageView = (ImageView) findViewById(R.id.imgAparelho);
        botaoImagem = (Button) findViewById(R.id.btnImagem);

        botaoImagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        AlertDialog.Builder construtorAlerta;
        construtorAlerta = new AlertDialog.Builder(this);
        construtorAlerta.setTitle("Menu Selecionado");
        construtorAlerta.setMessage("Confirma navegação para o menu selecionado?");
        construtorAlerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        construtorAlerta.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        if (id == R.id.nav_recurso) {
            // Trata a ação Recurso
            Intent recursoIntent = new Intent(this, MainActivity.class);
            startActivity(recursoIntent);
            finish();
        } else if (id == R.id.nav_consumo) {
//            AlertDialog meuAlerta = construtorAlerta.create();
//            meuAlerta.show();
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

    public void limpar(){
        TextView voltagem = (TextView) findViewById(R.id.txtVoltagem);
        TextView potenciaUso = (TextView) findViewById(R.id.txtPotenciaUso);
        TextView potenciaStand = (TextView) findViewById(R.id.txtPotenciaStand);
        spiner.setSelection(0);
        imageView.setImageResource(R.drawable.energia);

        voltagem.setText("");
        potenciaUso.setText("");
        potenciaStand.setText("");
    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }

    public void cadastrar(){
        Spinner meuSpinner = (Spinner) findViewById(R.id.spinnerRecurso);
        EditText voltagem = (EditText) findViewById(R.id.txtVoltagem);
        EditText potenciaUso = (EditText) findViewById(R.id.txtPotenciaUso);
        EditText potenciaStand = (EditText) findViewById(R.id.txtPotenciaStand);
        EditText descricao = (EditText) findViewById(R.id.txtDescricao);
        ImageView imagemRecurso = (ImageView) findViewById(R.id.imgAparelho);


        if (isEmpty(voltagem)) {
            Toast.makeText(getApplicationContext(), "Informe a voltagem do aparelho", Toast.LENGTH_SHORT).show();
        }
        else if (isEmpty(potenciaUso)){
            Toast.makeText(getApplicationContext(), "Informe a potência em uso do aparelho", Toast.LENGTH_SHORT).show();
        }
        else if (isEmpty(potenciaStand)){
            Toast.makeText(getApplicationContext(), "Informe a potência em StandBy do aparelho", Toast.LENGTH_SHORT).show();
        }

        else{

            Recurso recurso = helper.pegaRecursoDoFormulario();
            recursoDAO = new RecursoDAO(MainActivity.this);

            if(recurso.getId() == null){
                Boolean isInserted = recursoDAO.inserirRecurso(recurso);
                if(isInserted) {
                    Toast.makeText(getApplicationContext(), "Cadastro bem sucessido", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else
                    Toast.makeText(getApplicationContext(), "Erro ao inserir", Toast.LENGTH_SHORT).show();
            }else{
                recursoDAO.alterarRecurso(recurso);
            }

            recursoDAO.close();
            //recursoDAO.inserirRecurso(novoRecurso);

            /*Boolean isInserted = recursoDAO.inserirRecurso(meuSpinner.getSelectedItem().toString(), voltagem.getText().toString(), Double.parseDouble(potenciaUso.getText().toString()), Double.parseDouble(potenciaStand.getText().toString()));
            //Boolean isInserted =  myDb.inserData(meuSpinner.getSelectedItem().toString(), voltagem.getText().toString());
            if(isInserted)
                Toast.makeText(getApplicationContext(), "Cadastro bem sucessido", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(), "Erro ao inserir", Toast.LENGTH_SHORT).show();*/
        }
    }

    @Override
    public void run() {
    }
}



