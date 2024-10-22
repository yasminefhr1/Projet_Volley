package com.example.projetws;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.widget.SearchView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.RequestQueue;
import com.example.projetws.beans.Etudiant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListEtudiant extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EtudiantAdapter etudiantAdapter;
    private ArrayList<Etudiant> etudiants; // Liste des étudiants affichés
    private ArrayList<Etudiant> etudiantsFiltered; // Liste des étudiants filtrés pour la recherche
    private RequestQueue requestQueue;
    private SearchView searchView; // Déclaration de SearchView

    private String loadUrl = "http://10.0.2.2/TPVolley/ws/loadEtudiant.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        etudiants = new ArrayList<>();
        etudiantsFiltered = new ArrayList<>();
        etudiantAdapter = new EtudiantAdapter(this, etudiants);
        recyclerView.setAdapter(etudiantAdapter);

        requestQueue = Volley.newRequestQueue(this);

        loadEtudiants();

        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });

        ImageButton btnAjouterEtudiant = findViewById(R.id.btnAjouterEtudiant);
        btnAjouterEtudiant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListEtudiant.this, AddEtudiant.class);
                startActivity(intent);
            }
        });

        setupSwipeToDelete();
    }
    public void loadEtudiants() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, loadUrl, null,
                response -> {
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject etudiantObject = response.getJSONObject(i);
                            int id = etudiantObject.getInt("id");
                            String nom = etudiantObject.getString("nom");
                            String prenom = etudiantObject.getString("prenom");
                            String ville = etudiantObject.getString("ville");
                            String sexe = etudiantObject.getString("sexe");
                            String photo = etudiantObject.getString("photo");
                            Etudiant etudiant = new Etudiant(id, nom, prenom, ville, sexe, photo);
                            etudiants.add(etudiant);
                        }
                        etudiantsFiltered.addAll(etudiants); // Remplir la liste filtrée
                        etudiantAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(ListEtudiant.this, "Erreur de chargement des données", Toast.LENGTH_SHORT).show()
        );
        requestQueue.add(jsonArrayRequest);
    }
    private void setupSwipeToDelete() {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Etudiant etudiant = etudiants.get(position);

                etudiants.remove(position);
                etudiantAdapter.notifyItemRemoved(position);
                Toast.makeText(ListEtudiant.this, "Étudiant supprimé", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                // Dessiner l'arrière-plan lors du swipe
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    View itemView = viewHolder.itemView;
                    Paint paint = new Paint();
                    paint.setColor(Color.WHITE);
                    c.drawRect((float) itemView.getLeft(), (float) itemView.getTop(), dX, (float) itemView.getBottom(), paint);

                    Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_delete);
                    Bitmap resizedIcon = resizeImage(icon, 150, 150);
                    int iconHeight = resizedIcon.getHeight();
                    int iconWidth = resizedIcon.getWidth();
                    int iconMargin = (itemView.getHeight() - iconHeight) / 2;
                    int iconTop = itemView.getTop() + iconMargin;
                    int iconBottom = iconTop + iconHeight;
                    int iconLeft = itemView.getLeft() + iconMargin;
                    int iconRight = iconLeft + iconWidth;

                    c.drawBitmap(resizedIcon, iconLeft, iconTop, null);
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
            private Bitmap resizeImage(Bitmap originalImage, int newWidth, int newHeight) {
                return Bitmap.createScaledBitmap(originalImage, newWidth, newHeight, false);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
    private void filter(String text) {
        etudiants.clear();
        if (text.isEmpty()) {
            etudiants.addAll(etudiantsFiltered);
        } else {
            text = text.toLowerCase();
            for (Etudiant etudiant : etudiantsFiltered) {
                if (etudiant.getNom().toLowerCase().contains(text) || etudiant.getPrenom().toLowerCase().contains(text)) {
                    etudiants.add(etudiant);
                }
            }
        }
        etudiantAdapter.notifyDataSetChanged();
    }
}
