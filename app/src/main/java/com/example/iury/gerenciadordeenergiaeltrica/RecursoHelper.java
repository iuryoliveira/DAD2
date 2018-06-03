package com.example.iury.gerenciadordeenergiaeltrica;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.BitSet;

public class RecursoHelper {
    private Recurso recurso;
    private EditText voltagem;
    private EditText potenciaUso;
    private EditText potenciaStand;
    private EditText descricao;

    private ImageView imagemRecurso;
    private Button botaoFoto;

    public RecursoHelper(MainActivity activity) {

        this.recurso = new Recurso();

        this.voltagem = (EditText) activity.findViewById(R.id.txtVoltagem);
        this.potenciaUso = (EditText) activity.findViewById(R.id.txtPotenciaUso);
        this.potenciaStand = (EditText) activity.findViewById(R.id.txtPotenciaStand);
        this.descricao = (EditText) activity.findViewById(R.id.txtDescricao);
        this.imagemRecurso = (ImageView) activity.findViewById(R.id.imgAparelho);
        this.botaoFoto = (Button) activity.findViewById(R.id.cadastrarForm);
    }

    public Button getBotaoFoto(){
        return botaoFoto;
    }

    public Recurso pegaRecursoDoFormulario(){

        recurso.setDescricao(descricao.getText().toString());
        recurso.setTipo(1); //Teste
        recurso.setVoltagem(Double.parseDouble(voltagem.getText().toString()));
        recurso.setPotenciaUso(Double.parseDouble(potenciaUso.getText().toString()));
        recurso.setPotenciaStand(Double.parseDouble(potenciaStand.getText().toString()));
        recurso.setFoto((String) imagemRecurso.getTag());
        recurso.setCpfUsuario("1");
        return recurso;
    }
//    Colocar dados da classe no formulário
    public void colocaNoFormulario(Recurso recurso){
        descricao.setText(recurso.getDescricao().toString());
        voltagem.setText(recurso.getVoltagem().toString());
        potenciaUso.setText(recurso.getPotenciaUso().toString());
        potenciaStand.setText(recurso.getPotenciaStand().toString());

        imagemRecurso.setTag(recurso.getFoto());
        carregaImagem(recurso.getFoto());
    }

    public void carregaImagem(String localFoto){
        if(localFoto != null){
            Bitmap imagemFoto = BitmapFactory.decodeFile(localFoto);
            imagemRecurso.setImageBitmap(imagemFoto);
            imagemRecurso.setTag(localFoto);
        }
    }
}
