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

public class AdapterDatos2 extends RecyclerView.Adapter<AdapterDatos2.ViewHolderDatos> implements View.OnClickListener{

    private View.OnClickListener listener;
    ArrayList<String> listDatos2;

    public AdapterDatos2(ArrayList<String> listDatos) {
        this.listDatos2 = listDatos;
    }

    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list5, null, false);

        view.setOnClickListener(this);

        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarDatos(listDatos2.get(position));

    }

    @Override
    public int getItemCount() {
        return listDatos2.size();
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
