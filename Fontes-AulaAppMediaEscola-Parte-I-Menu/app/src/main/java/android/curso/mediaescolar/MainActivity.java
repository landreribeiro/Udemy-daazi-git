package android.curso.mediaescolar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.text.DecimalFormat;

/**
 * Esta versão do aplicativo Média Escolar demonstra
 * como criar o menu principal contendo botões para
 * cada bimestre
 */

public class MainActivity extends AppCompatActivity {

    public static final String NOME_SHARED_PREFER = "mediaEscolarPref";

    Button btnPrimeiroBimestre;
    Button btnSegundoBimestre;
    Button btnTerceiroBimestre;
    Button btnQuartoBimestre;
    Button btnResultadoFinal;

    /**
     *
     *     <string name="txtSituacaoFinal">Aprovado</string>
     *     <string name="notaProva">7.0</string>
     *     <boolean name="primeiroBimestre" value="true" />
     *     <string name="txtResultado">8.0</string>
     *     <string name="notaTrabalho">9.0</string>
     *     <string name="materia">Artes</string>
     *     <string name="media">8.0</string>
     *
     */

    String materiaPrimeiroBimestre, materiaSegundoBimestre,
            materiaTerceiroBimestre, materiaQuartoBimestre;

    String situacaoPrimeiroBimestre, situacaoSegundoBimestre,
            situacaoTerceiroBimestre, situacaoQuartoBimestre;

    String notaProvaPrimeiroBimestre, notaProvaSegundoBimestre,
            notaProvaTerceiroBimestre, notaProvaQuartoBimestre;

    String notaTrabalhoPrimeiroBimestre, notaTrabalhoSegundoBimestre,
            notaTrabalhoTerceiroBimestre, notaTrabalhoQuartoBimestre;

    String notaMediaPrimeiroBimestre, notaMediaSegundoBimestre,
            notaMediaTerceiroBimestre, notaMediaQuartoBimestre;

    // status dos menus, ativado ou desativado
    boolean primeiroBimestre,
            segundoBimestre,
            terceiroBimestre,
            quartoBimestre;

    double mediaFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.i("MediaEscolar", " -----> onCreate: ()");

        lerSharedPreferences();

        btnPrimeiroBimestre = findViewById(R.id.btnPrimeiroBimestre);
        btnSegundoBimestre = findViewById(R.id.btnSegundoBimestre);
        btnTerceiroBimestre = findViewById(R.id.btnTerceiroBimestre);
        btnQuartoBimestre = findViewById(R.id.btnQuartoBimestre);
        btnResultadoFinal = findViewById(R.id.btnResultadoFinal);

        btnPrimeiroBimestre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, PrimeiroBimestreActivity.class);
                startActivity(intent);
            }
        });

        btnSegundoBimestre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, SegundoBimestreActivity.class);
                startActivity(intent);
            }
        });

        btnTerceiroBimestre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, TerceiroBimestreActivity.class);
                startActivity(intent);
            }
        });

        btnQuartoBimestre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, QuartoBimestreActivity.class);
                startActivity(intent);
            }
        });

        btnResultadoFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplication(), "Resultado Final!", Toast.LENGTH_LONG).show();
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Limpando todos os registros!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                clearSharedPreferences();
            }
        });

        visualizarResultado();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sair) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart(){
        super.onStart();
    }

    @Override
    public void onResume(){
        super.onResume();

       // Log.i("MediaEscolar", "onResume: ");

        // significa bimestre fechado ou concluído

/*
        primeiroBimestre = true;
        if (primeiroBimestre) {
            btnPrimeiroBimestre.setEnabled(false);
            btnSegundoBimestre.setEnabled(true);
            btnPrimeiroBimestre.setText("*** CONCLUÍDO ***");

        }*/

        lerSharedPreferences();
        visualizarResultado();

    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onStop(){
        super.onStop();
    }

    /**
     * Recuperar os dados salvos com os valores
     * das notas de cada bimestre
     */
    private void lerSharedPreferences(){

        SharedPreferences mediaEscolarPref =
                getSharedPreferences(NOME_SHARED_PREFER, 0);

        materiaPrimeiroBimestre = mediaEscolarPref.getString("materiaPrimeiroBimestre", "");
        materiaSegundoBimestre = mediaEscolarPref.getString("materiaSegundoBimestre", "");
        materiaTerceiroBimestre = mediaEscolarPref.getString("materiaTerceiroBimestre", "");
        materiaQuartoBimestre = mediaEscolarPref.getString("materiaQuartoBimestre", "");

        situacaoPrimeiroBimestre = mediaEscolarPref.getString("situacaoPrimeiroBimestre", "");
        situacaoSegundoBimestre = mediaEscolarPref.getString("situacaoSegundoBimestre", "");
        situacaoTerceiroBimestre = mediaEscolarPref.getString("situacaoTerceiroBimestre", "");
        situacaoQuartoBimestre = mediaEscolarPref.getString("situacaoQuartoBimestre", "");

        notaProvaPrimeiroBimestre = mediaEscolarPref.getString("notaProvaPrimeiroBimestre", "0.0");
        notaProvaSegundoBimestre = mediaEscolarPref.getString("notaProvaSegundoBimestre", "0.0");
        notaProvaTerceiroBimestre = mediaEscolarPref.getString("notaProvaTerceiroBimestre", "0.0");
        notaProvaQuartoBimestre = mediaEscolarPref.getString("notaProvaQuartoBimestre", "0.0");

        notaTrabalhoPrimeiroBimestre = mediaEscolarPref.getString("notaTrabalhoPrimeiroBimestre", "0.0");
        notaTrabalhoSegundoBimestre = mediaEscolarPref.getString("notaTrabalhoSegundoBimestre", "0.0");
        notaTrabalhoTerceiroBimestre = mediaEscolarPref.getString("notaTrabalhoTerceiroBimestre", "0.0");
        notaTrabalhoQuartoBimestre = mediaEscolarPref.getString("notaTrabalhoQuartoBimestre", "0.0");

        notaMediaPrimeiroBimestre = mediaEscolarPref.getString("notaMediaPrimeiroBimestre", "0.0");
        notaMediaSegundoBimestre = mediaEscolarPref.getString("notaMediaSegundoBimestre", "0.0");
        notaMediaTerceiroBimestre = mediaEscolarPref.getString("notaMediaTerceiroBimestre", "0.0");
        notaMediaQuartoBimestre = mediaEscolarPref.getString("notaMediaQuartoBimestre", "0.0");

        primeiroBimestre = mediaEscolarPref.getBoolean("primeiroBimestre", false);
        segundoBimestre = mediaEscolarPref.getBoolean("segundoBimestre", false);
        terceiroBimestre = mediaEscolarPref.getBoolean("terceiroBimestre", false);
        quartoBimestre = mediaEscolarPref.getBoolean("quartoBimestre", false);


    }

    private void visualizarResultado() {
        if(primeiroBimestre){
            // TODO: criar método para montar o texto de apresentação
            btnPrimeiroBimestre.setText(materiaPrimeiroBimestre+" - 1º Bimestre: "+ situacaoPrimeiroBimestre + " - Nota " + formatarValorDecimal(Double.parseDouble(notaMediaPrimeiroBimestre)));
            btnPrimeiroBimestre.setEnabled(false);
            btnSegundoBimestre.setEnabled(primeiroBimestre);
        }

        if(segundoBimestre){
            btnSegundoBimestre.setText(materiaSegundoBimestre+" - 2º Bimestre: "+ situacaoSegundoBimestre + " - Nota " + formatarValorDecimal(Double.parseDouble(notaMediaSegundoBimestre)));
            btnSegundoBimestre.setEnabled(false);
            btnTerceiroBimestre.setEnabled(segundoBimestre);
        }

        if(terceiroBimestre){
            btnTerceiroBimestre.setText(materiaTerceiroBimestre+" - 3º Bimestre: "+ situacaoTerceiroBimestre + " - Nota " + formatarValorDecimal(Double.parseDouble(notaMediaTerceiroBimestre)));
            btnTerceiroBimestre.setEnabled(false);
            btnQuartoBimestre.setEnabled(terceiroBimestre);
        }

        if(quartoBimestre){
            btnQuartoBimestre.setText(materiaQuartoBimestre+" - 4º Bimestre: "+ situacaoQuartoBimestre + " - Nota " + formatarValorDecimal(Double.parseDouble(notaMediaQuartoBimestre)));
            btnQuartoBimestre.setEnabled(false);
            btnResultadoFinal.setEnabled(true);
        }

        mediaFinal = 0;
        mediaFinal += Double.parseDouble(notaMediaPrimeiroBimestre);
        mediaFinal += Double.parseDouble(notaMediaSegundoBimestre);
        mediaFinal += Double.parseDouble(notaMediaTerceiroBimestre);
        mediaFinal += Double.parseDouble(notaMediaQuartoBimestre);

        String mensagemFinal = "";

        mediaFinal = (mediaFinal / 4 );

        if((Double.parseDouble(notaMediaPrimeiroBimestre) >=7)
                && (Double.parseDouble(notaMediaSegundoBimestre) >= 7)
                && (Double.parseDouble(notaMediaTerceiroBimestre) >= 7)
                && (Double.parseDouble(notaMediaQuartoBimestre) >= 7)){

            mensagemFinal = mediaFinal >= 7 ?
                    "Aprovado com Média Final "+ formatarValorDecimal(mediaFinal) :
                    "Reprovado com Média Final "+ formatarValorDecimal(mediaFinal);

        }else{

            mensagemFinal = "Reprovado por matéria com a Média Final " + formatarValorDecimal(mediaFinal);

        }

        btnResultadoFinal.setText(mensagemFinal);

    }

    /**
     * limpar os dados
     */

    private void clearSharedPreferences(){

        SharedPreferences mediaEscolarPref = getSharedPreferences(NOME_SHARED_PREFER, 0);
        SharedPreferences.Editor editor = mediaEscolarPref.edit();
        editor.clear();
        editor.commit();
        
        clearMenu();

    }

    private void clearMenu() {

        btnResultadoFinal.setEnabled(false);
        btnQuartoBimestre.setEnabled(false);
        btnTerceiroBimestre.setEnabled(false);
        btnSegundoBimestre.setEnabled(false);
        btnPrimeiroBimestre.setEnabled(true);

        btnResultadoFinal.setText("Resultado Final");
        btnPrimeiroBimestre.setText("1º Bimestre");
        btnSegundoBimestre.setText("2º Bimestre");
        btnTerceiroBimestre.setText("3º Bimestre");
        btnQuartoBimestre.setText("4º Bimestre");
    }

    static String formatarValorDecimal(Double valor){

        DecimalFormat df = new DecimalFormat("#,###,##0.00");

        return df.format(valor);

    }

}



