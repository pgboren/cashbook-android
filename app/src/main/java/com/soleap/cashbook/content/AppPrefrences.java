package com.soleap.cashbook.content;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.soleap.cashbook.document.User;

public class AppPrefrences {

    private static SharedPreferences mPrefs;
    private static SharedPreferences.Editor mPrefsEditor;

    public static boolean isUserLogin(Context ctx) {
        String userId = getUserId(ctx);
        if (userId != null && userId !="") {
            return true;
        }
        return false;
    }

    public static void signIn(Context context, User user) {
        setUserId(context, user.get_id());
        setUserName(context, user.getUsername());
        setUserAccessToken(context, user.getAccessToken());
        setUserEmail(context, user.getEmail());
        setUserLoggedOut(context, false);
    }

    public static void signOut(Context context) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        mPrefsEditor = mPrefs.edit();
        mPrefsEditor.clear();
        mPrefsEditor.commit();
    }

    public static void setUserLoggedOut(Context ctx, boolean value) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        mPrefsEditor = mPrefs.edit();


        mPrefsEditor.putBoolean("id_logged_in", value);
        mPrefsEditor.commit();
    }

    public static void setUserId(Context ctx, String id) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        mPrefsEditor = mPrefs.edit();
        mPrefsEditor.putString("userId", id);
        mPrefsEditor.commit();
    }

    public static void setUserBranch(Context ctx, String branch) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        mPrefsEditor = mPrefs.edit();
        mPrefsEditor.putString("branch", branch);
        mPrefsEditor.commit();
    }

    public static String getUserBranch(Context ctx) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        return mPrefs.getString("branch", "");
    }

    public static String getUserId(Context ctx) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        return mPrefs.getString("userId", "");
    }

    public static String getUserName(Context ctx) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        return mPrefs.getString("username", "");
    }

    public static void setUserName(Context ctx, String value) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        mPrefsEditor = mPrefs.edit();
        mPrefsEditor.putString("username", value);
        mPrefsEditor.commit();
    }

    public static void setUserAccessToken(Context ctx, String id) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        mPrefsEditor = mPrefs.edit();
        mPrefsEditor.putString("accessToken", id);
        mPrefsEditor.commit();
    }

    public static String getUserAccessToken(Context ctx) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        return mPrefs.getString("accessToken", "");
    }

    public static String getUserEmail(Context ctx) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        return mPrefs.getString("userEmail", "");
    }

    public static void setUserEmail(Context ctx, String email) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        mPrefsEditor = mPrefs.edit();
        mPrefsEditor.putString("userEmail", email);
        mPrefsEditor.commit();
    }

    public static void setUserPhoto(Context ctx, String photo) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        mPrefsEditor = mPrefs.edit();
        mPrefsEditor.putString("userEmail", photo);
        mPrefsEditor.commit();
    }
}