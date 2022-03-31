package unipiloto.edu.co.recicla;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import unipiloto.edu.co.recicla.models.ListPublications;

public class MyAdapterPublication extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<ListPublications> publications;

    public MyAdapterPublication(Context context, int layout, ArrayList<ListPublications> publications) {
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
        v = layoutInflater.inflate(R.layout.list_view_publications_rec, null);
        TextView t1 = (TextView) v.findViewById(R.id.Item_publication_type_material);
        TextView t2 = (TextView) v.findViewById(R.id.Item_publication_peso);
        TextView t3 = (TextView) v.findViewById(R.id.Item_publication_volumen);
        TextView t4 = (TextView) v.findViewById(R.id.Item_publication_ubicacion);
        ImageView imageView = (ImageView) v.findViewById(R.id.materialImage);
        String type_material = publications.get(position).getType_material();
        String peso = publications.get(position).getWeight();
        String volumen = publications.get(position).getVolume();
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
        t1.setText("Tipo: " + type_material);
        t2.setText("Peso: " + peso + " Kg");
        t3.setText("Volumen: " + volumen + " m3");
        t4.setText("Ubicación: " + address);
        return v;
    }
}
