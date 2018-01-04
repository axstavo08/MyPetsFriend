package utp.edu.pe.mypetsfriend.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import utp.edu.pe.mypetsfriend.R;
import utp.edu.pe.mypetsfriend.Singleton;
import utp.edu.pe.mypetsfriend.models.Person;
import utp.edu.pe.mypetsfriend.models.User;
import utp.edu.pe.mypetsfriend.services.NewsService;


/**
 * Created by Marco on 01/11/2016.
 */
public class FragCustHome extends Fragment {

    private TextView dniTextView;
    private TextView phoneTextView;
    private TextView addressTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_cust_home, container, false);
        //Show the string value of the singleton
        //  Toast.makeText(view.getContext(), Singleton.getInstance().getString(), Toast.LENGTH_SHORT).show();


        dniTextView = (TextView) view.findViewById(R.id.dniTextView);
        phoneTextView = (TextView) view.findViewById(R.id.phoneTextView);
        addressTextView = (TextView) view.findViewById(R.id.addressTextView);

        //Toast.makeText( getContext(),Singleton.getInstance().getString(), Toast.LENGTH_SHORT).show();
        //String dni = Singleton.getInstance().getString();
        infoCustomer();

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.chagePassfab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CustChangePassActivity.class);
                startActivity(intent);
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        return view;

    }

    private void infoCustomer() {
        String dni = Singleton.getInstance().getString();
        AndroidNetworking.get(NewsService.PERSONS_URL + "list/" + dni)
                .setTag("people")
                .setPriority(Priority.LOW)
                .build()
                .getAsParsed(new TypeToken<List<Person>>() {
                }, new ParsedRequestListener<List<Person>>() {
                    @Override
                    public void onResponse(List<Person> persons) {

                        for (Person person : persons) {
                            // Toast.makeText(getContext(), person.getAddress(), Toast.LENGTH_SHORT).show();
                            // do anything with response
                            dniTextView.setText(person.getDni());
                            phoneTextView.setText(person.getPhone_number());
                            addressTextView.setText(person.getAddress());

                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        // handle error
                        Log.d("PetsFriend", "Error : " + anError.getMessage());
                    }
                });
    }


}