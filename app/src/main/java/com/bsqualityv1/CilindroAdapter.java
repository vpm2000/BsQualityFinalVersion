package com.bsqualityv1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CilindroAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Cilindro> listaCilindros;
    private LayoutInflater inflater;

    public CilindroAdapter(Activity context, ArrayList<Cilindro>listaCilindros){
        this.context = context;
        this.listaCilindros = listaCilindros;
        inflater = LayoutInflater.from(context);
    }

    static class ViewHolder{
        TextView txt_Id;
        TextView txt_Cliente;
        TextView txt_Marca;
        TextView txt_Medida;
    }
    @Override
    public int getCount() {
        return listaCilindros.size();
    }

    @Override
    public Object getItem(int position) {
        return listaCilindros.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override // Pasamos los datos a la list View para poder formatearlos y mostrarlos
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null){
            convertView = inflater.inflate(R.layout.item_lista,null);
            holder = new ViewHolder();
            holder.txt_Id = convertView.findViewById(R.id.TxtId);
            holder.txt_Cliente = convertView.findViewById(R.id.TxtCli);
            holder.txt_Marca = convertView.findViewById(R.id.TxtMarca);
            holder.txt_Medida = convertView.findViewById(R.id.TxtMedida);

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        Cilindro cilindro = listaCilindros.get(position);
        holder.txt_Id.setText(cilindro.getIdCil());
        holder.txt_Cliente.setText(cilindro.getNombreCli());
        holder.txt_Marca.setText(cilindro.getMarcaCil());
        holder.txt_Medida.setText(cilindro.getDiamCil().toString());

        return convertView;
    }
}
