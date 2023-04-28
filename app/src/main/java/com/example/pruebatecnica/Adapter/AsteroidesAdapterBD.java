package com.example.pruebatecnica.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pruebatecnica.Models.ModelBD;
import com.example.pruebatecnica.R;
import com.example.pruebatecnica.Utils.Utils;
import com.example.pruebatecnica.Views.FragmentAsteroidesGuardados;
import com.example.pruebatecnica.Views.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class AsteroidesAdapterBD extends RecyclerView.Adapter<AsteroidesAdapterBD.ViewHolder> {
    private Context context;
    private HomeFragment homeFragment;
    private FragmentAsteroidesGuardados fragmentAsteroidesGuardados;
    private List<ModelBD> infoAsteroides = new ArrayList<>();
    private Utils metodoReutilizable = new Utils();

    public AsteroidesAdapterBD(List<ModelBD> infoAsteroides, FragmentAsteroidesGuardados fragmentAsteroidesGuardados) {
        this.infoAsteroides = infoAsteroides;
        this.context = fragmentAsteroidesGuardados.getContext();
        this.fragmentAsteroidesGuardados = fragmentAsteroidesGuardados;
    }

    @NonNull
    @Override
    public AsteroidesAdapterBD.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_info_asteroides, parent, false);
        return new AsteroidesAdapterBD.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AsteroidesAdapterBD.ViewHolder holder, int position) {
        Glide.with(context)
                .load(R.mipmap.asteroidess)
                .into(holder.imgAsteroides);

        holder.tvNombreAsteroide.setText(infoAsteroides.get(position).getName());
        holder.tvDiametro.setText("Su diametro es: "+   metodoReutilizable.printDecimal(infoAsteroides.get(position).getDiametro_Metros())+ " M");

        if (infoAsteroides.get(position).getPeligro() == 1) {
            holder.tvPeligro.setText("Potencialmente peligroso");
        }else {
            holder.llPeligro.setBackgroundColor(Color.parseColor("#036CE7"));
            holder.tvPeligro.setText("No representa peligro");
        }
    }

    @Override
    public int getItemCount() {
        return infoAsteroides.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgAsteroides;
        private TextView tvNombreAsteroide, tvPeligro,tvDiametro;
        private LinearLayout llPeligro;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAsteroides = itemView.findViewById(R.id.imgAsteroides);
            tvNombreAsteroide = itemView.findViewById(R.id.tvNombreAsteroide);
            tvPeligro = itemView.findViewById(R.id.tvPeligro);
            llPeligro = itemView.findViewById(R.id.llPeligro);
            tvDiametro = itemView.findViewById(R.id.tvDiametro);

        }
    }
}

