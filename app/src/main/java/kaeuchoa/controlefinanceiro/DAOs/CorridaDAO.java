package kaeuchoa.controlefinanceiro.DAOs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import kaeuchoa.controlefinanceiro.models.Corrida;

/**
 * Created by kaeuc on 30/07/2017.
 */

public class CorridaDAO  extends SQLiteOpenHelper {
    // Constantes com o nome de colunas e do banco de dados
    private static final String DB_NAME = "corridas";
    private static final String ORIGEM_COLUMN = "origem";
    private static final String DESTINO_COLUMN = "destino";
    private static final String DATA_COLUMN = "data";
    private static final String VALOR_COLUMN = "valor";
    private Context parentContext;

    private static int VERSION = 1;

    private static CorridaDAO instance;

    public static synchronized CorridaDAO getInstance(Context context) {
        if(instance == null){
            return new CorridaDAO(context);
        }
        return instance;
    }

    private CorridaDAO(Context context) {
        super(context, DB_NAME, null, VERSION);
        parentContext = context;
    }

    // Inicialização do banco de dados
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DB_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE , " +
                ORIGEM_COLUMN +" TEXT NOT NULL ,"+ DESTINO_COLUMN +" TEXT NOT NULL ,"
                + DATA_COLUMN + " TEXT NOT NULL ," + VALOR_COLUMN + " DOUBLE NOT NULL)");
    }

    // Método para atualização do banco
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Método público para adicionar um usuário ao banco

    public void insertCorrida(Corrida corrida){
        // Recupera o banco para poder inserir a o conteúdo
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ORIGEM_COLUMN, corrida.getOrigem());
        values.put(DESTINO_COLUMN,corrida.getDestino());
        values.put(DATA_COLUMN,corrida.getData());
        values.put(VALOR_COLUMN,corrida.getValor());
        long insert = db.insert(DB_NAME, null, values);

        // TODO: 2. Tratar resultados vindo do SQL
        if(insert != -1){
            Toast.makeText(parentContext, "Corrida Criada com Sucesso!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(parentContext, "Falha", Toast.LENGTH_SHORT).show();
            }


    }


    // Método publico para recuperar os usuários do banco de dados
    public List<Corrida> getCorridas(){
        // Acha o banco de onde os dados serão lidos
        SQLiteDatabase db = getReadableDatabase();
        List<Corrida> corridas = new ArrayList<>();

        // Cria a SELECT QUERY
        Cursor cursor = db.query(DB_NAME,
                new String[]{ORIGEM_COLUMN, DESTINO_COLUMN, DATA_COLUMN,VALOR_COLUMN},
                null,null,null,null,null);
        cursor.moveToFirst();
        if (cursor.getCount()!= 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                // Cria um mapa para guardar informação do usuário

                Corrida corrida = new Corrida(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getDouble(3));
                corridas.add(corrida);
                cursor.moveToNext();

            }
        }
        cursor.close();
        return corridas;
    }

    // Método que retorna a id do usuário
    public int getCorridaId(String username){
        SQLiteDatabase db = getReadableDatabase();
        int userID = 0;
        final String[] selectColumn = {"_id"};
        String whereClause = ORIGEM_COLUMN + " =?";
        String [] whereArgs = {username};
        Cursor cursor = db.query(DB_NAME,selectColumn,whereClause,whereArgs,null,null,null);
        cursor.moveToFirst();
        if (cursor.getCount() != 0) {
            userID = cursor.getInt(0);
            cursor.close();

        }
        return userID;
    }
}
