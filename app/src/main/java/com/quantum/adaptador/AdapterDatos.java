package com.quantum.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.quantum.aprobacionesapp.R;

import java.util.ArrayList;

public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolderDatos> implements View.OnClickListener{

    private View.OnClickListener listener;
    ArrayList<String> listDatos;

    public AdapterDatos(ArrayList<String> listDatos) {
        this.listDatos = listDatos;
    }


    //conexion con layout
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, null, false);

        view.setOnClickListener(this);

        return new ViewHolderDatos(view);
    }

    //conexion con el arraylist
    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarDatos(listDatos.get(position));

    }

    //conexion con un punto en especifico del arraylist
    @Override
    public int getItemCount() {
        return listDatos.size();
    }


    //que hacer cuando se hace click en el dato array
    public void setOnclickListener(View.OnClickListener listener){
        this.listener = listener;
    }
    @Override
    public void onClick(View view) {
        if ( listener!= null){
            listener.onClick(view);
        }
    }

    //conexion con los textviews del layout con los del adapter
    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView dato;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            dato= itemView.findViewById(R.id.idDato);



        }

        public void asignarDatos(String datos) {
            dato.setText(datos) ;

        }

    }
}
