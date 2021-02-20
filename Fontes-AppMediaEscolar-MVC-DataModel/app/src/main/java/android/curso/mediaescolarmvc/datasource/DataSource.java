package android.curso.mediaescolarmvc.datasource;

import android.content.Context;
import android.curso.mediaescolarmvc.datamodel.MediaEscolarDataModel;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataSource extends SQLiteOpenHelper {

    private static final String DB_NAME = "media_escolar.sqlite";
    private static final int DB_VERSION = 1;

    SQLiteDatabase db;

    public DataSource(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        // getWritableDatabase(), dá acesso a leitura e gravação do banco de dados
        db = getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try{

            db.execSQL(MediaEscolarDataModel.criarTabela());

        }catch (Exception e){

            Log.e("Media", "DB----> ERRO: "+e.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}