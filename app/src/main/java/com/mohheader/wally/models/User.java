package com.mohheader.wally.models;

import android.content.Context;
import android.content.SharedPreferences;

import com.mohheader.wally.WallActivity;

/**
 * Created by thedreamer on 12/13/14.
 */
public class User {
    final static public String NO_BODY = "kljfdg@dsoh-*eo8t7558eg";
    final static public String USER_KEY = "Name";


    public static void setUseName(Context context, String name){
        SharedPreferences sharedPref = context.getSharedPreferences(User.USER_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(User.USER_KEY, name);
        editor.apply();
    }

    public static String getUserName(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences(User.USER_KEY,Context.MODE_PRIVATE);
        return sharedPref.getString(User.USER_KEY,"NoBody");
    }

    public static boolean isLogedIn(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences(User.USER_KEY, Context.MODE_PRIVATE);
        return !User.NO_BODY.equals(sharedPref.getString(User.USER_KEY, User.NO_BODY));
    }

    public static void logout(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(User.USER_KEY,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(User.USER_KEY);
        editor.apply();
    }
}
