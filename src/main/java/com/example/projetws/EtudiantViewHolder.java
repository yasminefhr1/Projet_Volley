package com.example.projetws;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class EtudiantViewHolder extends RecyclerView.ViewHolder {
    public TextView nom, prenom, ville, sexe;

    public EtudiantViewHolder(View itemView) {
        super(itemView);
        nom = itemView.findViewById(R.id.nom);
        prenom = itemView.findViewById(R.id.prenom);
        ville = itemView.findViewById(R.id.ville);
        sexe = itemView.findViewById(R.id.sexe);
        de.hdodenhof.circleimageview.CircleImageView imageView = itemView.findViewById(R.id.photo);
    }
}
