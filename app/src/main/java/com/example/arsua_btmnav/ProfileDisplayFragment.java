package com.example.arsua_btmnav;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileDisplayFragment extends Fragment {

    private TextView displayFullName, displayAge, displayEmail, displayPhone, displayAddress, displayOccupation, displayGender, displayMaritalStatus, displayHobbies;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_display, container, false);

        // Initialize the views
        displayFullName = view.findViewById(R.id.displayFullName);
        displayAge = view.findViewById(R.id.displayAge);
        displayEmail = view.findViewById(R.id.displayEmail);
        displayPhone = view.findViewById(R.id.displayPhone);
        displayAddress = view.findViewById(R.id.displayAddress);
        displayOccupation = view.findViewById(R.id.displayOccupation);
        displayGender = view.findViewById(R.id.displayGender);
        displayMaritalStatus = view.findViewById(R.id.displayMaritalStatus);
        displayHobbies = view.findViewById(R.id.displayHobbies);

        // Get data from arguments
        Bundle bundle = getArguments();
        if (bundle != null) {
            // Update the key to match the one used in ProfileFragment ("fullName")
            displayFullName.setText(getSafeString(bundle, "fullName", "N/A"));
            displayAge.setText(getSafeString(bundle, "age", "N/A"));
            displayEmail.setText(getSafeString(bundle, "email", "N/A"));
            displayPhone.setText(getSafeString(bundle, "phone", "N/A"));
            displayAddress.setText(getSafeString(bundle, "address", "N/A"));
            displayOccupation.setText(getSafeString(bundle, "occupation", "N/A"));
            displayGender.setText(getSafeString(bundle, "gender", "N/A"));
            displayMaritalStatus.setText(getSafeString(bundle, "maritalStatus", "N/A"));
            displayHobbies.setText(getSafeString(bundle, "hobbies", "None"));
        }

        return view;
    }

    private String getSafeString(Bundle bundle, String key, String defaultValue) {
        String value = bundle.getString(key);
        return value != null ? value : defaultValue;
    }
}
