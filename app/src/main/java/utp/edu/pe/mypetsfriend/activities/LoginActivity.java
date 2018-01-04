package utp.edu.pe.mypetsfriend.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import utp.edu.pe.mypetsfriend.R;
import utp.edu.pe.mypetsfriend.Singleton;
import utp.edu.pe.mypetsfriend.models.Person;
import utp.edu.pe.mypetsfriend.models.User;
import utp.edu.pe.mypetsfriend.services.NewsService;

public class LoginActivity extends AppCompatActivity {

     private Button loginButton;
    private EditText userEditText;
    private EditText passwordEditText;

private static  String TAG ="login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userEditText = (EditText) findViewById(R.id.userEditText);
        passwordEditText =(EditText) findViewById(R.id.passwordEditText);


        loginButton = (Button) findViewById(R.id.loginButton) ;
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AndroidNetworking.get( NewsService.USERS_URL + "login/" + userEditText.getText().toString() + " /" + passwordEditText.getText().toString() )
                        .setTag(this)
                        .setPriority(Priority.LOW)
                        .build()
                        .getAsParsed(new TypeToken<User>() {}, new ParsedRequestListener<User>() {
                            @Override
                            public void onResponse(User user) {
                                // do anything with response
                                if ( user.isStatus() ){
                                    Log.d(TAG, "dni : " + user.getDni());
                                    Log.d(TAG, "username : " + user.getUser_name());
                                    Log.d(TAG, "type : " + user.getType());

                                    //Change the string value and launch intent to ActivityB
                                    Singleton.getInstance().setString(user.getDni());
                                    startActivity(new Intent(LoginActivity.this, CustWelcomeActivity.class));
                                }
                                else {
                                    //CODIGO DE ERROR PASSWORD
                                    Toast.makeText(getApplicationContext() ,"Error user / password", Toast.LENGTH_SHORT).show();
                                }

                            }
                            @Override
                            public void onError(ANError anError) {
                                // handle error
                                Log.d("PetsFriend", "Error : " + anError.getMessage());
                            }
                        });


            }
        });
    }

}
