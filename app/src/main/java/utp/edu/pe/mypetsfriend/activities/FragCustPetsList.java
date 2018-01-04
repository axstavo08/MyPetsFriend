package utp.edu.pe.mypetsfriend.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import utp.edu.pe.mypetsfriend.R;
import utp.edu.pe.mypetsfriend.Singleton;
import utp.edu.pe.mypetsfriend.adapters.PetAdapter;
import utp.edu.pe.mypetsfriend.models.Pet;
import utp.edu.pe.mypetsfriend.services.NewsService;


/**
 * Created by Marco on 01/11/2016.
 */
public class FragCustPetsList extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    RecyclerView quotesRecyclerView;
    private List<Pet> pets;
    private PetAdapter petAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        pets = new ArrayList<>();
        petAdapter = new PetAdapter();
        petAdapter.setPets(pets);

        View view = inflater.inflate(R.layout.frag_cust_pets_list, container, false);
        quotesRecyclerView = (RecyclerView) view.findViewById(R.id.listpetsRecyclerView);
        quotesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //quotesRecyclerView.setAdapter(new PetAdapter(getService().getPets()));
        quotesRecyclerView.setAdapter(petAdapter);
        return view;
    }


    private void infoPets() {

        String dni = Singleton.getInstance().getString();
        AndroidNetworking.get(NewsService.PETS_URL + "list/" + dni)
                .setTag("pets")
                .setPriority(Priority.LOW)
                .build()
                .getAsParsed(new TypeToken<List<Pet>>() {
                }, new ParsedRequestListener<List<Pet>>() {
                    @Override
                    public void onResponse(List<Pet> petList) {
                        Log.d("PetsFriend", "info : here ");
                        pets = petList;
                        petAdapter.setPets(pets);
                        petAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(ANError anError) {
                        // handle error
                        Log.d("PetsFriend", "Error : " + anError.getMessage());
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        infoPets();
    }
}
