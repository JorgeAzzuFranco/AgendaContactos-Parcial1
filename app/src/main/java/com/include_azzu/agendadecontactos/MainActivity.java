package com.include_azzu.agendadecontactos;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializando objetos a usar
        contactos = new ArrayList<>();
        rv = findViewById(R.id.rv);

        //Instanciando el contactProvider
        ContentResolver contactProvider = getContentResolver();
        //Creando cursor para recorrer los nombres de los contactos
        Cursor cursor = contactProvider.query(ContactsContract.Contacts.CONTENT_URI, null, null,null,null, null);

        //Obtiene los numeros de los contactos
        while (cursor.moveToNext()){
            //Obtiene ID de los contactos
            String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            //Guarda el nombre de los contactos
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            if (Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER) )) > 0){
                //Busca el numero de telefono de cada contacto a traves del ID
                Cursor mCursor = contactProvider.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID+" = ?",
                        new String[]{id},null);
                //Tambien debes recorrer los numeros de telefono para encontrar el que matcheé con el del ID
                while (mCursor.moveToNext()){
                    try {
                        String numTel = mCursor.getString(mCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        contactos.add(new Contacto(name+"", numTel + "",  ""));
                    }catch (Exception e) {
                        contactos.add(new Contacto(name + "", "No disponible", ""));
                        Log.d("Error", e.toString());
                    }
                }
            }
        }

        rv.setHasFixedSize(true);

        adapter = new ContactoAdapter(contactos, getApplicationContext());
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);

        rv.setAdapter(adapter);
    }
    
    public void marcarNumero(View v){
        
    }

    public void contactos(View v){
        
    }
    
    public void favoritos(View v){
        
    }
}
