package unipiloto.edu.co.recicla;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import unipiloto.edu.co.recicla.models.ListOwnPublications;
import unipiloto.edu.co.recicla.models.ListPublications;

public class MyAdapterOwnPublicationHistory  extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<ListOwnPublications> publications;

    public MyAdapterOwnPublicationHistory(Context context, int layout, ArrayList<ListOwnPublications> publications) {
        this.context = context;
        this.layout = layout;
        this.publications = publications;
    }

    @Override
    public int getCount() {
        return this.publications.size();
    }

    @Override
    public Object getItem(int i) {
        return this.publications.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View v = view;
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        v = layoutInflater.inflate(R.layout.list_view_publicaciones_historial, null);
        TextView t1 = (TextView) v.findViewById(R.id.LV_PUB_HIS_type_material);
        TextView t2 = (TextView) v.findViewById(R.id.LV_PUB_HIS_state);
        TextView t3 = (TextView) v.findViewById(R.id.LV_PUB_HIS_fecha);
        TextView t4 = (TextView) v.findViewById(R.id.LV_PUB_HIS_address);
        ImageView imageView = (ImageView) v.findViewById(R.id.LV_PUB_HIS_Image);

        String type_material = publications.get(position).getType_material();
        String state;
        if(publications.get(position).isState()){
            state = "Activa";
        }else{
            state = "Inactiva";
        }

        String date = publications.get(position).getTimestamp();
        String address = publications.get(position).getAddress();

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
        t2.setText("Estado: " + state);
        t3.setText("Fecha: " + date.substring(0,10));
        t4.setText("Dirección: " + address);
        return v;

    }
}
