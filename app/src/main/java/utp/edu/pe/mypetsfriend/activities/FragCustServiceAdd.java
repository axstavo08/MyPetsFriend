package utp.edu.pe.mypetsfriend.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;


import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import utp.edu.pe.mypetsfriend.R;
import utp.edu.pe.mypetsfriend.Singleton;
import utp.edu.pe.mypetsfriend.models.Pet;
import utp.edu.pe.mypetsfriend.models.Service;
import utp.edu.pe.mypetsfriend.services.NewsService;

/**
 * Created by Marco on 01/11/2016.
 */

public class FragCustServiceAdd extends Fragment {

    //String[] datos = {"vacunas", "corte"};
    String[] datos;
    String[] datosPets;
    View view;
    Spinner servicesSpinner;
    Spinner petsSpinner;
    EditText priceEditText;
    List<Service> servicesAux;
    List<Pet> petsAux;
    int positionService = 0;
    int positionPet = 0;
    CheckBox deliveryCheckBox;
    double priceOriginal = 0;
Button saveButton;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.frag_cust_service_add, container, false);

        infoServices();
        infoPets();
        servicesSpinner = (Spinner) view.findViewById(R.id.serviceSpinner);
        petsSpinner = (Spinner) view.findViewById(R.id.petsSpinner);
        priceEditText = (EditText) view.findViewById(R.id.priceEditText);
        deliveryCheckBox = (CheckBox) view.findViewById(R.id.deliveryCheckBox);

        deliveryCheckBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                double precio;
                if (((CheckBox) v).isChecked()) {
                    precio = Double.parseDouble(priceEditText.getText().toString());
                    precio = precio * 1.20;
                } else {
                    precio = priceOriginal;
                }
                priceEditText.setText(precio + "");

            }
        });

        /* Guardar información*/
        saveButton =(Button)view.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view.getContext(), FragCustHistory.class));

                //guardar informacion
                AndroidNetworking.post(NewsService.PETS_SERVICES_URL + "create/")
                        .addBodyParameter("petserviceid", "0")
                        .addBodyParameter("petid", petsAux.get(positionPet).getPet_id())
                        .addBodyParameter("serviceid", servicesAux.get(positionService).getService_id()+"")
                        .addBodyParameter("nameservice", servicesAux.get(positionService).getDescription())
                        .addBodyParameter("price", priceEditText.getText()+"")
                        .addBodyParameter("delivery", "1")
                        .addBodyParameter("observation", "Shekhar")

                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsJSONArray(new JSONArrayRequestListener() {
                            @Override
                            public void onResponse(JSONArray response) {
                                // do anything with response
                                Log.d("PetsFriend", "Registro guardado OK");
                            }
                            @Override
                            public void onError(ANError error) {
                                // handle error
                                Toast.makeText(view.getContext() ,"Error: ", Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });



        EditText dateEditText = (EditText) view.findViewById(R.id.dateEditText);
        dateEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                    DateDialog dialog = new DateDialog(v);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    dialog.show(ft, "DatePiker");

                }
            }
        });

        EditText timeEditText = (EditText) view.findViewById(R.id.timeEditText);
        timeEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                    TimeDialog dialog = new TimeDialog(v);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    dialog.show(ft, "TimePicker");

                }
            }
        });

        return view;
    }

    private void infoServices() {


        AndroidNetworking.get(NewsService.SERVICES_URL + "list/")
                .setTag("pets")
                .setPriority(Priority.LOW)
                .build()
                .getAsParsed(new TypeToken<List<Service>>() {
                }, new ParsedRequestListener<List<Service>>() {
                    @Override
                    public void onResponse(List<Service> services) {

                        servicesAux = new ArrayList<Service>();
                        servicesAux = services;
                        datos = new String[services.size()];
                        int i = 0;
                        for (Service service : services) {
                            Log.d("service", service.getDescription());
                            datos[i] = service.getDescription();
                            i++;
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, datos);
                        servicesSpinner.setAdapter(adapter);
                        servicesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                                                                      @Override
                                                                      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                                          // switch (position) {
                                                                          //     case 1:
                                                                          positionService = position;
                                                                          Toast toast = Toast.makeText(view.getContext(), datos[position], Toast.LENGTH_SHORT);
                                                                          toast.show();
                                                                          priceEditText.setText(servicesAux.get(position).getPrice().toString());
                                                                          priceOriginal = servicesAux.get(position).getPrice();
                                                                          //        break;
                                                                          //  }
                                                                      }

                                                                      @Override
                                                                      public void onNothingSelected(AdapterView<?> parent) {

                                                                      }
                                                                  }
                        );

                    }

                    @Override
                    public void onError(ANError anError) {
                        // handle error
                        Log.d("PetsFriend", "Error : " + anError.getMessage());
                    }
                });
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


                        petsAux = new ArrayList<Pet>();
                        petsAux = petList;
                        datosPets = new String[petList.size()];
                        int i = 0;
                        for (Pet pet : petList) {
                            Log.d("pet", pet.getPet_name());
                            datosPets[i] = pet.getPet_name();
                            i++;
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, datosPets);
                        petsSpinner.setAdapter(adapter);
                        petsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                                                                  @Override
                                                                  public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                                                      positionPet = position;
                                                                      Toast toast = Toast.makeText(view.getContext(), datosPets[position], Toast.LENGTH_SHORT);
                                                                      toast.show();

                                                                  }

                                                                  @Override
                                                                  public void onNothingSelected(AdapterView<?> parent) {

                                                                  }
                                                              }
                        );
                    }

                    @Override
                    public void onError(ANError anError) {
                        // handle error
                        Log.d("PetsFriend", "Error : " + anError.getMessage());
                    }
                });
    }

    public void onStart() {
        super.onStart();

    }

}