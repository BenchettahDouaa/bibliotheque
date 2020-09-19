package com.pharmaservice.bibliotheque;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {
    // le nom de la table de base de données.
    public static final String BOOKS_DB_NAME = "bibliotheque";
    public static final String BOOKS_TABLE_NAME = "Books";
    private HashMap hp;

    public DBHelper(Context context) {
        super(context, BOOKS_DB_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
// TODO Auto-generated method stub
        db.execSQL(
                "create table Books " +
                        "(id integer primary key AutoIncrement, titre text,auteur text,motCles text)"
        );
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS Books");
        onCreate(db);
    }


    public boolean insertBooks(String ptitre, String pauteur, String pmotCles) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("titre", ptitre);
        contentValues.put("auteur", pauteur);
        contentValues.put("motCles", pmotCles);
        long result = db.insert("Books", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }



    public Books RechercherBooksByTitre(String ptitre){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =
                db.rawQuery( "select * from Books where titre='"+ptitre+"'", null );
        res.moveToFirst();
        Books b = new Books();;
// on parcours le résultat et on crée pour chaque ligne un objet Rdv
        if(res.isAfterLast() == false){
// on crée un nouveau objet Books
            b.setId(res.getInt(0)); // on mis son ID
            b.setTitre(res.getString(1)); // on mis son Titre
            b.setAuteur(res.getString(2));
// on mis son Auteur
            b.setMotCles(res.getString(3)); // on mis ça MotCles
            res.moveToNext();
        }
        return b;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, BOOKS_TABLE_NAME);
        return numRows;
    }
    //mettre à jour un Books.
    public boolean updateBooks (Integer id, String titre, String auteur, String motCles)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("titre", titre);
        contentValues.put("auteur", auteur);
        contentValues.put("motCles", motCles);
        db.update("Books", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }




    public Integer deleteBook (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("Books",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }
    // Lister tous les Books
    public ArrayList<Books> ListerTousRDV()
    {
//on crée un liste vide
        ArrayList<Books> array_list = new ArrayList<Books>();
        SQLiteDatabase db = this.getReadableDatabase();
// on lance la requête
        Cursor res = db.rawQuery( "select * from Books", null );
        res.moveToFirst();
        Books b;
// on parcours le résultat et on crée pour chaque ligne un objet Books
        while(res.isAfterLast() == false){
            b= new Books();
// on crée un nouveau objet Books
            b.setId(res.getInt(0)); // on mis son ID
            b.setTitre(res.getString(1)); // on mis son Titre
            b.setAuteur(res.getString(2));
// on mis son Auteur
            b.setMotCles(res.getString(3)); // on mis ça MoteCles
            array_list.add(b);
            res.moveToNext();
        }
// on renvoi le résultat.
        return array_list;
    }

    public Cursor GetAllBooks(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "select * from Books", null );
        return res;


    }

}




