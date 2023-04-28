package com.example.pruebatecnica.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pruebatecnica.BdLocal.AdminSQLite;
import com.example.pruebatecnica.Global;
import com.example.pruebatecnica.Models.EventDate;
import com.example.pruebatecnica.R;
import com.example.pruebatecnica.Utils.Utils;
import com.example.pruebatecnica.Views.DetallesAsteroide;
import com.example.pruebatecnica.Views.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class AsteroidesAdapter extends RecyclerView.Adapter<AsteroidesAdapter.ViewHolder> {
    private Context context;
    private HomeFragment homeFragment;
    private List<EventDate> infoAsteroides = new ArrayList<>();
    private Utils metodoReutilizable = new Utils();
    private AdminSQLite db;

    public AsteroidesAdapter(List<EventDate> infoAsteroides, HomeFragment homeFragment) {
        this.infoAsteroides = infoAsteroides;
        this.context = homeFragment.getContext();
        this.homeFragment = homeFragment;
    }

    @NonNull
    @Override
    public AsteroidesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_info_asteroides, parent, false);
        return new AsteroidesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AsteroidesAdapter.ViewHolder holder, int position) {
        Glide.with(context)
                .load(R.mipmap.asteroidess)
                .into(holder.imgAsteroides);

        holder.tvNombreAsteroide.setText(infoAsteroides.get(position).getName());
        holder.tvDiametro.setText("Su diametro es: "+   metodoReutilizable.printDecimal(infoAsteroides.get(position).getEstimated_diameter().getMeters().getEstimated_diameter_max())+ " M");

        if (infoAsteroides.get(position).isIs_potentially_hazardous_asteroid()) {
            holder.tvPeligro.setText("Potencialmente peligroso");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = new AdminSQLite(context);
                Utils.asteroideSelect = new EventDate();
                Utils.asteroideSelect = infoAsteroides.get(position);
                db.userAndAsteroid(Integer.parseInt(db.consultUser("id", Utils.emailRegistrado)),infoAsteroides.get(position).getId());
                Utils.loadFragment(homeFragment.getActivity(), new DetallesAsteroide(homeFragment),"detalle");


            }
        });
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

