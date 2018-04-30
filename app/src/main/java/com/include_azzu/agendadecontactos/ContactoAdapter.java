package com.include_azzu.agendadecontactos;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Jorge Azzu Franco on 28/4/2018.
 */

public class ContactoAdapter extends RecyclerView.Adapter<ContactoAdapter.ContactoViewHolder> {

    List<Contacto> contactos;
    private Context context;

    public ContactoAdapter(List<Contacto> contacto, Context context){
        this.contactos = contacto;
        this.context = context;
    }

    @Override
    public ContactoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.cardview_contacto, parent, false);
        return (new ContactoViewHolder(v));
    }

    @Override
    public void onBindViewHolder(final ContactoViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(holder.nombre.getText() == "Jorgito")
                    holder.nombre.setText("Jack");
                else
                    holder.nombre.setText("Jorgito");
            }
        });
        holder.nombre.setText(contactos.get(position).getNombre());
        holder.telefono.setText(contactos.get(position).getTelefono()+"");
        holder.correo.setText(contactos.get(position).getCorreo());
        holder.img.setImageResource(contactos.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return contactos.size();
    }

    public static class ContactoViewHolder extends RecyclerView.ViewHolder {

        CardView card;
        TextView nombre;
        TextView telefono;
        TextView correo;
        ImageView img;

        public ContactoViewHolder(View itemView) {
            super(itemView);

            card = itemView.findViewById(R.id.cv);
            nombre = itemView.findViewById(R.id.nombre);
            telefono = itemView.findViewById(R.id.telefono);
            correo = itemView.findViewById(R.id.correo);
            img = itemView.findViewById(R.id.img);
        }
    }
}
