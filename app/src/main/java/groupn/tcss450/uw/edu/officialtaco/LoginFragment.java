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
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener{
    private static final String PARTIAL_URL
            = "http://cssgate.insttech.washington.edu/" +
            "~jcha224/lab4";
    private OnFragmentInteractionListener mListener;
    private EditText et;
    private EditText pt;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        Button b = (Button) v.findViewById(R.id.loginbutton);
        b.setOnClickListener(this);
        et = (EditText) v.findViewById(R.id.loginusername);
        pt = (EditText) v.findViewById(R.id.loginpassword);

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
    public void onClick(View v) {
        if(mListener != null) {
            AsyncTask<String, Void, String> task = new LoginWebServiceTask();
            if(et.getText().length() < 1) {
                et.setError("Cannot be left empty");
            }
            if(pt.getText().length() < 1) {
                pt.setError("Cannot be left empty");
            }
            if(et.getText().length() > 0 &&
                    pt.getText().length() > 0) {
                task.execute(PARTIAL_URL, et.getText().toString());
            }
        }
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onLoginInteraction(CharSequence usr, CharSequence pwd);
    }
    private class LoginWebServiceTask extends AsyncTask<String, Void, String> {
        private final String SERVICE = "_login.php";
        @Override
        protected String doInBackground(String... strings) {
            if (strings.length != 2) {
                throw new IllegalArgumentException("Two String arguments required.");
            }
            String response = "";
            HttpURLConnection urlConnection = null;
            String url = strings[0];
            String args = "?my_usr=" + strings[1];
            try {
                URL urlObject = new URL(url + SERVICE + args);
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
            if(result.equals("[]")) {
                et.setError("User not found.");
            } else {
                String res = result.substring(9, result.length()-3);
                Log.d("RESULTTT", res);
                if(res.equals(pt.getText().toString())) {
                    mListener.onLoginInteraction(et.getText(), pt.getText());
                } else {
                    pt.setError("Incorrect password!");
                }
            }
        }
    }
}
