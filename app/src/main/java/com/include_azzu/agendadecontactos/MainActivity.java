package com.include_azzu.agendadecontactos;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    //Objetos de la View
    ArrayList<Contacto> contactos;
    RecyclerView rv;
    ContactoAdapter adapter;

    //Para leer los contactos, es un modelo para obtenerlos
    private String[] mColumnProjection = new String[]{
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
            ContactsContract.Contacts.CONTACT_STATUS,
            ContactsContract.Contacts.HAS_PHONE_NUMBER

    };

    //Cosas del video que no se para que las usa
    private String mSelectionCluse = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + "= 'Anil'";
    private String[] nSelectionArguments = new String[]{"Anil"};
    private String mOrderBy = ContactsContract.Contacts.DISPLAY_NAME;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializando objetos a usar
        contactos = new ArrayList<>();
        rv = findViewById(R.id.rv);

        //Instanciando el contactProvider
        ContentResolver contactProvider = getContentResolver();
        //Instanciando lector de cada fila, indicandole que columnas usar con el modelo anterior
        Cursor cursor = contactProvider.query(ContactsContract.Contacts.CONTENT_URI,
                mColumnProjection,
                null,
                null,
                null);

        //Verificando que no esten vacias y que si hay valores, muestre
        if (cursor != null && cursor.getCount() > 0){
            //StringBuilder stringBuilderResult = new StringBuilder();
            //LLenando el recyclerview
            while (cursor.moveToNext()){
                contactos.add(new Contacto(cursor.getString(0)+"", cursor.getString(1)+"",cursor.getString(2)+""));
            }
        }

        rv.setHasFixedSize(true);

        adapter = new ContactoAdapter(contactos, getApplicationContext());
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);

        rv.setAdapter(adapter);

        //contactos.add(new Contacto("Jorgito", 70079623, "jorge.cuadra.13@gmail.com"));
    }
    
    public void marcarNumero(View v){
        
    }

    public void contactos(View v){
        
    }
    
    public void favoritos(View v){
        
    }
}
