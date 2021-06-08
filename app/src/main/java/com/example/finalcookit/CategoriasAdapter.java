package com.example.finalcookit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CategoriasAdapter extends RecyclerView.Adapter<CategoriasAdapter.Viewholder> {

    private Context context;
    private ArrayList<llenarcategorias> courseModelArrayList;

    private ArrayList<llenarcategorias> listaO;

    // Constructor
    public CategoriasAdapter(Context context, ArrayList<llenarcategorias> courseModelArrayList) {
        this.context = context;
        this.courseModelArrayList = courseModelArrayList;
        listaO = new ArrayList<>();
        listaO.addAll(courseModelArrayList);
    }

    public void filtrado(String cadena){
        if(cadena.length()==0){
            courseModelArrayList.clear();
            courseModelArrayList.addAll(listaO);
        }else{
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<llenarcategorias> coleccion = courseModelArrayList.stream().filter(i->i.getNombre().toLowerCase().contains(cadena.toLowerCase()))
                        .collect(Collectors.toList());
                courseModelArrayList.clear();
                courseModelArrayList.addAll(coleccion);
            }else{
                for (llenarcategorias c: listaO ) {
                    if(c.getNombre().toLowerCase().contains(cadena.toLowerCase())){
                        courseModelArrayList.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CategoriasAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categorias_rv, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriasAdapter.Viewholder holder, int position) {
        llenarcategorias model = courseModelArrayList.get(position);
        holder.courseNameTV.setText(model.getNombre());
        holder.courseRatingTV.setImageResource(model.getFoto());
    }


    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return courseModelArrayList.size();
    }

    // View holder class for initializing of
    // your views such as TextView and Imageview.
    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView courseNameTV;
        private ImageView courseRatingTV;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            courseNameTV = itemView.findViewById(R.id.textocategoria);
            courseRatingTV = itemView.findViewById(R.id.imagencategoria);
        }
    }
}
