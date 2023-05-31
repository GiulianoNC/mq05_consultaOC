package com.quantum.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.quantum.aprobacionesapp.R;

import java.util.ArrayList;

public class AdapterDatos3 extends RecyclerView.Adapter<AdapterDatos3.ViewHolderDatos> implements View.OnClickListener{

    private View.OnClickListener listener;
    ArrayList<String> listDatos;

    public AdapterDatos3(ArrayList<String> listDatos) {
        this.listDatos = listDatos;
    }

    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list4, null, false);

        view.setOnClickListener(this);

        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarDatos(listDatos.get(position));

    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    public void setOnclickListener(View.OnClickListener listener){
        this.listener = listener;
    }
    @Override
    public void onClick(View view) {
        if ( listener!= null){
            listener.onClick(view);
        }
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView dato;
        Spinner spinner;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            dato= itemView.findViewById(R.id.idDato);



        }

        public void asignarDatos(String datos) {
            dato.setText(datos) ;

        }

    }
}
