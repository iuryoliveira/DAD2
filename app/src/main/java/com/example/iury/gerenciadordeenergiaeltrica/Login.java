package com.example.iury.gerenciadordeenergiaeltrica;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import android.widget.EditText;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.Writer;
import java.io.Writer;

import org.ksoap2.*;
import org.ksoap2.serialization.*;
import org.ksoap2.transport.*;
import org.xmlpull.v1.XmlSerializer;

public class Login extends AppCompatActivity{
    EditText login;
    EditText senha;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        login = (EditText) findViewById(R.id.txtLogin);
        senha = (EditText) findViewById(R.id.txtSenha);
        login.requestFocus();


    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;
        return true;
    }

    public void login(View view){

        //if(!isEmpty(login) && !isEmpty(senha)) {
            startActivity(new Intent(this, PerfilConsumo.class));
            finish();
        /*}
        else
            Toast.makeText(getApplicationContext(), "Informe os dados de login.", Toast.LENGTH_SHORT).show();*/
    }

    public void redirecionarCadastro(View view){
        startActivity(new Intent(this, cadastro_usuario.class));
        finish();
    }

    public void chamarUsuario(View view){
        /*
        SoapObject spSmbc = new SoapObject("http://localhost/dad/Service1.svc", "RetornarUsuario");
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.bodyOut = spSmbc;
        envelope.encodingStyle = SoapSerializationEnvelope.ENC2003;
        envelope.dotNet=true;
        envelope.setOutputSoapObject(spSmbc);

        String URL =  "http://localhost/dad/Service1.svc/";
        HttpTransportSE http = new HttpTransportSE(URL);

        System.setProperty("http.keepAlive", "false");
        http.debug = true;

        spSmbc.addProperty("login","lucasa.teste");
        spSmbc.addProperty("senha","1234");


        //add objectSoap in envelope to send web service
        envelope.setOutputSoapObject(spSmbc);
        envelope.implicitTypes = true;

        try {

            http.call(URL, envelope);
            Object msg = envelope.getResponse();
            Toast.makeText(getApplicationContext(), msg.toString(), Toast.LENGTH_SHORT);
        }
        catch (XmlPullParserException erro){

        }catch(IOException erro2){

        }
        */
        /*SoapObject soap = new SoapObject("http://localhost/dad/Service1.svc", "RetornarUsuario");
        soap.addProperty("senha", "1");
        soap.addProperty("login", "i");

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);

        envelope.setOutputSoapObject(soap);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(soap);

        String url = "localhost/dad/Service1.svc";
        HttpTransportSE httpTransport = new HttpTransportSE(url);

        try{
            httpTransport.call("http://localhost/dad/IService1/RetornarUsuario", envelope);

            Object msg = envelope.getResponse();

        } catch(){

        }*/
    }
}
