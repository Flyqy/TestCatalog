package com.example.liqfo.catalogtest.PinPreference;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    final static String FILE_PIN = "preferences";
    final static String USERNAME = "username";

    private SharedPreferences preferences;

    public Preferences(Context context) {
        preferences = context.getSharedPreferences(FILE_PIN, 0);
    }

    private SharedPreferences.Editor getEditor() {
        return preferences.edit();
    }

    public void setUserName(String data){
        getEditor().putString(USERNAME, data).commit();
    }

    public String getUserName(){
        return preferences.getString(USERNAME, "");
    }

}
