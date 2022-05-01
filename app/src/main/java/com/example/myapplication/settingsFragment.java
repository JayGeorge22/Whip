package com.example.myapplication;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.preference.PreferenceFragmentCompat;

public class settingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // below line is used to add preference
        // fragment from our xml folder.
        addPreferencesFromResource(R.xml.preferences);

    }

    SharedPreferences.OnSharedPreferenceChangeListener listener =
            new SharedPreferences.OnSharedPreferenceChangeListener() {
                @Override
                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                    int switchVal = Character.getNumericValue((key.charAt(key.length() - 1)));
                    Log.d("STATE", "key is " + key);
                    switch(switchVal) {
                        case 1:
                            MainActivity2.infoPreset1.updateContactSP(key, sharedPreferences);
                            break;
                        case 2:
                            MainActivity2.infoPreset2.updateContactSP(key, sharedPreferences);
                            break;
                        case 3:
                            MainActivity2.infoPreset3.updateContactSP(key, sharedPreferences);
                            break;
                    }
                }
            };

    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {

    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(listener);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(listener);
    }


}

