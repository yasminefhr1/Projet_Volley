package com.example.projetws;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.projetws.beans.Etudiant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class EtudiantAdapter extends RecyclerView.Adapter<EtudiantAdapter.EtudiantViewHolder>{
    private ArrayList<Etudiant> etudiantList;
    private ArrayList<Etudiant> etudiantListFull;
    private Context context;
    private ArrayList<Etudiant> etudiants;

    public EtudiantAdapter(Context context, ArrayList<Etudiant> etudiantList) {
        this.context = context;
        this.etudiantList = etudiantList;
        etudiantListFull = new ArrayList<>(etudiantList); // Store a copy of the original list
    }
    @NonNull
    @Override
    public EtudiantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_etudiant, parent, false);
        return new EtudiantViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull EtudiantViewHolder holder, int position) {
        Etudiant etudiant = etudiantList.get(position);
        holder.nomTextView.setText(etudiant.getNom());
        holder.prenomTextView.setText(etudiant.getPrenom());
        holder.villeTextView.setText(etudiant.getVille());
        holder.sexeTextView.setText(etudiant.getSexe());

        Glide.with(context)
                .load(etudiant.getPhoto())
                .into(holder.imageView);

        holder.itemView.setOnClickListener(v -> {
            //showStudentDetails(etudiant);
            showStudentDetailsDialog(etudiant);
        });
    }
    private void showStudentDetailsDialog(Etudiant etudiant) {
        Dialog detailsDialog = new Dialog(context);
        detailsDialog.setContentView(R.layout.dialog_item_detail);


        CircleImageView imageView = detailsDialog.findViewById(R.id.image_student_photo);
        TextView textName = detailsDialog.findViewById(R.id.text_item_name);
        TextView textSurname = detailsDialog.findViewById(R.id.text_item_surname);
        TextView textCity = detailsDialog.findViewById(R.id.text_item_city);
        TextView textSexe = detailsDialog.findViewById(R.id.text_item_sexe);
        Button buttonUpdate = detailsDialog.findViewById(R.id.button_update);
        Button buttonClose = detailsDialog.findViewById(R.id.button_close);


        textName.setText("Nom : " + etudiant.getNom());
        textSurname.setText("Preom : " + etudiant.getPrenom());
        textCity.setText("Ville : " + etudiant.getVille());
        textSexe.setText("Genre : " + etudiant.getSexe());
        String imageUrl = etudiant.getPhoto();
            Glide.with(context)
                    .load(imageUrl)
                    .into(imageView);


        buttonClose.setOnClickListener(v -> detailsDialog.dismiss());

        buttonUpdate.setOnClickListener(v -> {

            showStudentDetails(etudiant);
            detailsDialog.dismiss(); // Close the details dialog
        });

        detailsDialog.show();
    }
    @Override
    public int getItemCount() {
        return etudiantList.size();
    }
    public static class EtudiantViewHolder extends RecyclerView.ViewHolder {
        public TextView nomTextView, prenomTextView, villeTextView, sexeTextView;
        public CircleImageView imageView;

        public EtudiantViewHolder(@NonNull View itemView) {
            super(itemView);
            nomTextView = itemView.findViewById(R.id.nom);
            prenomTextView = itemView.findViewById(R.id.prenom);
            villeTextView = itemView.findViewById(R.id.ville);
            sexeTextView = itemView.findViewById(R.id.sexe);
            imageView = itemView.findViewById(R.id.photo);
        }
    }
    public void removeItem(int position) {
        etudiantList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, etudiantList.size());
    }
    private void showStudentDetails(Etudiant etudiant) {
        Log.d("DEBUG", "Affichage des détails pour: " + etudiant.getNom());
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.update, null);
        builder.setView(dialogView);

        EditText editTextNom = dialogView.findViewById(R.id.editTextNom);
        EditText editTextPrenom = dialogView.findViewById(R.id.editTextPrenom);
        EditText editTextVille = dialogView.findViewById(R.id.editTextVille);
        RadioGroup radioGroupSexe = dialogView.findViewById(R.id.radioGroupSexe);
        EditText editTextPhoto = dialogView.findViewById(R.id.editTextPhoto);
        Button buttonUpdate = dialogView.findViewById(R.id.buttonUpdate);


        editTextNom.setText(etudiant.getNom());
        editTextPrenom.setText(etudiant.getPrenom());
        editTextVille.setText(etudiant.getVille());
        editTextPhoto.setText(etudiant.getPhoto());
        if ("Male".equalsIgnoreCase(etudiant.getSexe())) {
            radioGroupSexe.check(R.id.radioMale);
        } else {
            radioGroupSexe.check(R.id.radioFemale);
        }

        AlertDialog dialog = builder.create();
        dialog.show();

        buttonUpdate.setOnClickListener(v -> {
            String newNom = editTextNom.getText().toString().trim();
            String newPrenom = editTextPrenom.getText().toString().trim();
            String newVille = editTextVille.getText().toString().trim();
            int selectedSexeId = radioGroupSexe.getCheckedRadioButtonId();
            String newSexe = (selectedSexeId == R.id.radioMale) ? "Male" : "Female"; // Get selected sexe
            String newPhoto = editTextPhoto.getText().toString().trim(); // Get updated photo URL

            if (validateInput(newNom, newPrenom, newVille, newSexe, newPhoto)) {
                updateEtudiant(etudiant.getId(), newNom, newPrenom, newVille, newSexe, newPhoto);

                dialog.dismiss();
            } else {
                Toast.makeText(context, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private boolean validateInput(String nom, String prenom, String ville, String sexe, String photo) {
        return !nom.isEmpty() && !prenom.isEmpty() && !ville.isEmpty() && !sexe.isEmpty() && !photo.isEmpty();
    }
    private void updateEtudiant(int id, String nom, String prenom, String ville, String sexe, String photo) {
        String url = "http://10.0.2.2/TPVolley/ws/editEtudiant.php"; // Update with your server URL
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");
                        if (success) {
                            for (Etudiant etudiant : etudiantList) {
                                if (etudiant.getId() == id) {
                                    etudiant.setNom(nom);
                                    etudiant.setPrenom(prenom);
                                    etudiant.setVille(ville);
                                    etudiant.setSexe(sexe);
                                    etudiant.setPhoto(photo);
                                    notifyDataSetChanged();
                                    break;
                                }
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(context, "Mise à jour", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    Toast.makeText(context, "Erreur de connexion", Toast.LENGTH_SHORT).show();
                }) {
            @Override
            protected Map<String, String> getParams() {
                // Create a map to hold the parameters
                Map<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(id));
                params.put("nom", nom);
                params.put("prenom", prenom);
                params.put("ville", ville);
                params.put("sexe", sexe);
                params.put("photo", photo);
                return params;
            }

            @Override
            public String getBodyContentType() {
                // Set the content type for JSON
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id", id);
                    jsonObject.put("nom", nom);
                    jsonObject.put("prenom", prenom);
                    jsonObject.put("ville", ville);
                    jsonObject.put("sexe", sexe);
                    jsonObject.put("photo", photo);
                    return jsonObject.toString().getBytes(StandardCharsets.UTF_8);
                } catch (JSONException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };
        requestQueue.add(stringRequest);
    }

}
