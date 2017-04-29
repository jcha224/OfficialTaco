package groupn.tcss450.uw.edu.officialtaco;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;
import org.json.JSONObject;

import groupn.tcss450.uw.edu.officialtaco.model.Setlist;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainActivity extends AppCompatActivity
        implements BeginFragment.OnFragmentInteractionListener,
        LoginFragment.OnFragmentInteractionListener,
        RegisterFragment.OnFragmentInteractionListener,
        GetRestaurantFragment.OnFragmentInteractionListener {


    public MainActivity() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        if(savedInstanceState == null) {
            if (findViewById(R.id.fragmentContainer) != null) {
                getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainer, new BeginFragment())
                .commit();
            }
        }
    }

    @Override
    public void onBeginInteraction(int button) {

        LoginFragment frag2;
        RegisterFragment frag3;
        FragmentTransaction transaction;

        if(button == R.id.beginloginbutton) {
            frag2 = new LoginFragment();
            transaction = getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, frag2)
                    .addToBackStack(null);
        }else {
            frag3 = new RegisterFragment();
            transaction = getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, frag3)
                    .addToBackStack(null);
        }

        transaction.commit();
    }

    @Override
    public void onLoginInteraction(CharSequence u, CharSequence p) {

        GetRestaurantFragment frag4 = new GetRestaurantFragment();
        Bundle args = new Bundle();
        args.putCharSequence("uKey", u);
        frag4.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, frag4)
                .addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onDisplayInteraction(JSONObject obj) {
        DisplayFragment frag4 = new DisplayFragment();
        Bundle args = new Bundle();
        Setlist s = null;
        try {
            s = Setlist.getSetListData(obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        args.putCharSequence("sTitle", Setlist.getTitle());
        args.putCharSequence("sUrl", Setlist.getUrl());
        args.putCharSequence("sWebsite", Setlist.getWebsite());
        args.putCharSequence("sAddress", Setlist.getAddress());
        args.putCharSequence("sPhone", Setlist.getPhone());
        args.putStringArray("sHours", Setlist.getHours());
        args.putStringArray("sTypes", Setlist.getTypes());
        args.putInt("sRating", Setlist.getRating());
        frag4.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, frag4)
                .addToBackStack(null);
        transaction.commit();
    }

}
