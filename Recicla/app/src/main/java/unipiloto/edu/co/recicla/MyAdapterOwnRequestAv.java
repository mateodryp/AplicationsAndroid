package unipiloto.edu.co.recicla;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import unipiloto.edu.co.recicla.models.ListOwnRequest;

public class MyAdapterOwnRequestAv extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<ListOwnRequest> solicitudes;

    public MyAdapterOwnRequestAv(Context context, int layout, ArrayList<ListOwnRequest> solicitudes) {
        this.context = context;
        this.layout = layout;
        this.solicitudes = solicitudes;
    }

    @Override
    public int getCount() {
        return this.solicitudes.size();
    }

    @Override
    public Object getItem(int i) {
        return this.solicitudes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        v = layoutInflater.inflate(R.layout.list_view_solicitudes_proveedor, null);
        TextView t1 = (TextView) v.findViewById(R.id.SPtem_publication_type_material);
        TextView t2 = (TextView) v.findViewById(R.id.SPtem_publication_id_p);
        TextView t3 = (TextView) v.findViewById(R.id.SPtem_publication_fecha);
        TextView t4 = (TextView) v.findViewById(R.id.SPtem_publication_solicitante);
        ImageView imageView = (ImageView) v.findViewById(R.id.SPmaterialImage);

        String type_material = solicitudes.get(i).getType_material();
        String id_publ = String.valueOf(solicitudes.get(i).getId_publication());
        String fecha = solicitudes.get(i).getTimestamp();
        String nombre_sol = solicitudes.get(i).getRecycler();
        switch (type_material){
            case "Vidrio":
                imageView.setImageResource(R.drawable.vidrio);
                break;

            case "Carton":
                imageView.setImageResource(R.drawable.carton);
                break;

            case "Metal":
                imageView.setImageResource(R.drawable.metal);
                break;

            case "Papel":
                imageView.setImageResource(R.drawable.papel);
                break;
        }
        t1.setText("Tipo de material: " + type_material);
        t2.setText("Id de publicación: "+ id_publ);
        t3.setText("Fecha: "+ fecha.substring(0,10));
        t4.setText("Solicitante: "+ nombre_sol);
        return v;
    }
}
