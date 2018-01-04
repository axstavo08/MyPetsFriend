package utp.edu.pe.mypetsfriend.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import utp.edu.pe.mypetsfriend.R;
import utp.edu.pe.mypetsfriend.Singleton;
import utp.edu.pe.mypetsfriend.models.Pet;
import utp.edu.pe.mypetsfriend.services.NewsService;

public class CustPetsViewActivity extends AppCompatActivity {

    ImageView pictureImageView;
    TextView namePetTextView, breedPetTextView, agePetTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_pets_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        pictureImageView = (ImageView) findViewById(R.id.pictureImageView);
        namePetTextView = (TextView) findViewById(R.id.nameTextView);
        breedPetTextView = (TextView) findViewById(R.id.breedTextView);
        agePetTextView = (TextView) findViewById(R.id.ageTextView);


        infoPet();



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void infoPet() {
        Bundle bundle = getIntent().getExtras();
        String dni = bundle.getString("dni");
        String id = bundle.getString("id");

        AndroidNetworking.get(NewsService.PETS_URL + "list/" + dni + "/" + id)
                .setTag("pets")
                .setPriority(Priority.LOW)
                .build()
                .getAsParsed(new TypeToken<Pet>() {
                }, new ParsedRequestListener<Pet>() {
                    @Override
                    public void onResponse(Pet pet) {
                        Log.d("PetsFriend", "info : here ");
                        namePetTextView.setText(pet.getPet_name());
                        breedPetTextView.setText(pet.getBreed());
                        agePetTextView.setText(pet.getAge_year());
                       // pictureImageView.setImageResource(Integer.parseInt(pet.getPictureUri()));
                    }

                    @Override
                    public void onError(ANError anError) {
                        // handle error
                        Log.d("PetsFriend", "Error : " + anError.getMessage());
                    }
                });
    }

}
