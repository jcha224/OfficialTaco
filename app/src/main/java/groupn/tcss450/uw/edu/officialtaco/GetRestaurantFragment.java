package groupn.tcss450.uw.edu.officialtaco;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class GetRestaurantFragment extends Fragment
        implements View.OnClickListener {
    private static final String API_URL = "https://maps.googleapis.com/maps/api/place/details/json?";
    private static final String API_KEY = "AIzaSyB6uUO1NN88F35RPpaxfWTOiXsyXFpgIAg";
    private GetRestaurantFragment.OnFragmentInteractionListener mListener;

    private EditText mTextView;

    public GetRestaurantFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_get_restaurant, container, false);
        Button bb = (Button) v.findViewById(R.id.getRsearchButton);
        bb.setOnClickListener(this);
        mTextView = (EditText) v.findViewById(R.id.getRsearchText);
        return v;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(getArguments() != null) {
            CharSequence u = getArguments().getCharSequence("uKey");
            updateContent(u);
        }
    }

    public void updateContent(CharSequence u) {
        TextView tv = (TextView) getActivity().findViewById(R.id.getRuserText);

        tv.setText(u);
    }

    @Override
    public void onClick(View view) {
        AsyncTask<String, Void, String> task = new RetrieveFeedTask();
        if (mListener != null) {
            switch (view.getId()) {
                case R.id.getRsearchButton:
                    task.execute(mTextView.getText().toString());
            }
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onDisplayInteraction(JSONObject obj);
    }


    class RetrieveFeedTask extends AsyncTask<String, Void, String> {

        Exception exception;

        @Override
        protected String doInBackground(String... strings) {
            if (strings.length != 1) {
                throw new IllegalArgumentException("Three String arguments required.");
            }

            String response = "";
            HttpURLConnection urlConnection = null;
            String arg = strings[0];

            try {
                URL urlObject = new URL(API_URL + "placeid=" + arg +  "&key=" +API_KEY);
                urlConnection = (HttpURLConnection) urlObject.openConnection();
                InputStream content = urlConnection.getInputStream();
                BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                String s = "";
                while ((s = buffer.readLine()) != null) {
                    response += s;
                }
            } catch (Exception e) {
                response = "Unable to connect, Reason: "
                        + e.getMessage();
            } finally {
                if (urlConnection != null)
                    urlConnection.disconnect();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            // Something wrong with the network or the URL.
            if (result.startsWith("Unable to")) {
                Toast.makeText(getActivity().getApplicationContext(), result, Toast.LENGTH_LONG)
                        .show();
                return;
            }

            JSONObject ob = null;
            JSONArray ar = null;
            try {
                ob = new JSONObject(result);
                ob = ob.getJSONObject("result");
                mListener.onDisplayInteraction(ob);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }
}
