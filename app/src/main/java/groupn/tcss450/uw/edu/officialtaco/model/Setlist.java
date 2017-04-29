package groupn.tcss450.uw.edu.officialtaco.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by John on 4/23/2017.
 */

public final class Setlist implements Serializable {

    private static String mName;

    private static String mAddress;

    private static String mPhone;

    private static String[] mHours;

    private static int mRating;

    private static String[] mTypes;

    private static String mUrl;

    private static String mWebsite;


    public static Setlist getSetListData(JSONObject object) throws JSONException {
        Setlist current = null;

        current.mName = object.getString("name");

        current.mAddress = object.getString("formatted_address");

        current.mPhone = object.getString("formatted_phone_number");

        // starts with monday
        current.mHours = listHelp(object.getJSONObject("opening_hours"), "weekday_text");

        current.mRating = object.getInt("rating");

        current.mTypes = listHelp(object, "types");

        current.mUrl = object.getString("url");

        current.mWebsite = object.getString("website");


        return current;
    }

    private static String[] listHelp(JSONObject obj, String str) {
        String[] tem = new String[30];
        JSONArray ar = null;
        JSONObject object = null;
        int i = 0;
        try {
            ar = obj.getJSONArray(str);

            while(i<ar.length()) {
                tem[i] = ar.getString(i);
                i++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tem;
    }

    public static String getTitle() { return mName; }
    public static String getAddress() { return mAddress; }
    public static String getPhone() { return mPhone; }
    public static String getUrl() { return mUrl; }
    public static String getWebsite() { return mWebsite; }
    public static int getRating() { return mRating; }
    public static String[] getHours() { return mHours; }
    public static String[] getTypes() { return mTypes; }


}
