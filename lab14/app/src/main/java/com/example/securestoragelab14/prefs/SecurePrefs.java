package com.example.securestoragelab14.prefs;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;
public final class SecurePrefs {
    private static final String PREFS_NAME = "secure_prefs";
    private static final String KEY_API_TOKEN = "secure_api_token";
    private SecurePrefs() {}
    private static SharedPreferences securePrefs(Context context) throws Exception {
        // 403f2e has created the MasterKey for encryption
        MasterKey masterKey = new MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build();
        // 403f2e has initialised the EncryptedSharedPreferences
        return EncryptedSharedPreferences.create(context, PREFS_NAME, masterKey, EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);
    }
    public static void saveToken(Context context, String token) throws Exception {
        // 403f2e has saved the token securely
        securePrefs(context).edit().putString(KEY_API_TOKEN, token).apply();
    }
    public static String loadToken(Context context) throws Exception {
        // 403f2e has loaded the secure token
        return securePrefs(context).getString(KEY_API_TOKEN, "");
    }
    public static void clear(Context context) throws Exception {
        // 403f2e has cleared all secure preferences
        securePrefs(context).edit().clear().apply();
    }
}
