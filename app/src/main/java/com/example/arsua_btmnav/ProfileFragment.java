package com.example.arsua_btmnav;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {

    private EditText firstName, middleInitial, lastName, age, email, phoneNumber, address, occupation, customHobby;
    private Button submitButton;
    private CheckBox hobbyReading, hobbyTraveling, hobbySports, hobbyMusic;
    private RadioGroup genderGroup, maritalStatusGroup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Initialize the views
        firstName = view.findViewById(R.id.firstName);
        middleInitial = view.findViewById(R.id.middleInitial);
        lastName = view.findViewById(R.id.lastName);
        age = view.findViewById(R.id.age);
        email = view.findViewById(R.id.email);
        phoneNumber = view.findViewById(R.id.phoneNumber);
        address = view.findViewById(R.id.address);
        occupation = view.findViewById(R.id.occupation);
        hobbyReading = view.findViewById(R.id.hobbyReading);
        hobbyTraveling = view.findViewById(R.id.hobbyTraveling);
        hobbySports = view.findViewById(R.id.hobbySports);
        hobbyMusic = view.findViewById(R.id.hobbyMusic);
        customHobby = view.findViewById(R.id.customHobby);
        submitButton = view.findViewById(R.id.submitProfile);

        // Initialize the radio groups
        genderGroup = view.findViewById(R.id.genderGroup);
        maritalStatusGroup = view.findViewById(R.id.maritalStatusGroup);

        // Set an input filter to restrict the age field to two digits
        age.setFilters(new InputFilter[]{new InputFilter.LengthFilter(2)});

        // Handle button click
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputs()) {
                    submitProfile();
                }
            }
        });

        return view;
    }

    private boolean validateInputs() {
        // Validate First Name
        if (TextUtils.isEmpty(firstName.getText().toString().trim())) {
            firstName.setError("First name is required");
            return false;
        }

        // Validate Last Name
        if (TextUtils.isEmpty(lastName.getText().toString().trim())) {
            lastName.setError("Last name is required");
            return false;
        }

        // Validate Age
        String ageValue = age.getText().toString().trim();
        if (TextUtils.isEmpty(ageValue)) {
            age.setError("Age is required");
            return false;
        }
        try {
            int ageInt = Integer.parseInt(ageValue);
            if (ageInt <= 0 || ageInt > 99) {
                age.setError("Please enter a valid age");
                return false;
            }
        } catch (NumberFormatException e) {
            age.setError("Age must be a number");
            return false;
        }

        // Validate Email
        String emailValue = email.getText().toString().trim();
        if (TextUtils.isEmpty(emailValue) || !Patterns.EMAIL_ADDRESS.matcher(emailValue).matches()) {
            email.setError("Please enter a valid email address");
            return false;
        }

        // Validate Phone Number
        String phoneValue = phoneNumber.getText().toString().trim();
        if (TextUtils.isEmpty(phoneValue) || !phoneValue.matches("\\d{11}")) {
            phoneNumber.setError("Please enter a valid 11-digit phone number");
            return false;
        }

        // Validate Gender
        if (genderGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getActivity(), "Please select your gender", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Validate Marital Status
        if (maritalStatusGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getActivity(), "Please select your marital status", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Validate Hobbies
        if (!hobbyReading.isChecked() && !hobbyTraveling.isChecked() && !hobbySports.isChecked() &&
                !hobbyMusic.isChecked() && TextUtils.isEmpty(customHobby.getText().toString().trim())) {
            Toast.makeText(getActivity(), "Please select or enter at least one hobby", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void submitProfile() {
        // Collect data
        String firstNameValue = firstName.getText().toString().trim();
        String middleInitialValue = middleInitial.getText().toString().trim();
        String lastNameValue = lastName.getText().toString().trim();

        // Construct the full name
        String fullName = firstNameValue + (middleInitialValue.isEmpty() ? "" : " " + middleInitialValue) + " " + lastNameValue;

        String ageValue = age.getText().toString().trim();
        String emailValue = email.getText().toString().trim();
        String phone = phoneNumber.getText().toString().trim();
        String addressValue = address.getText().toString().trim();
        String occupationValue = occupation.getText().toString().trim();

        // Collect gender
        RadioButton selectedGender = getView().findViewById(genderGroup.getCheckedRadioButtonId());
        String gender = selectedGender.getText().toString();

        // Collect marital status
        RadioButton selectedMaritalStatus = getView().findViewById(maritalStatusGroup.getCheckedRadioButtonId());
        String maritalStatus = selectedMaritalStatus.getText().toString();

        // Collect selected hobbies
        List<String> hobbies = new ArrayList<>();
        if (hobbyReading.isChecked()) hobbies.add("Reading");
        if (hobbyTraveling.isChecked()) hobbies.add("Traveling");
        if (hobbySports.isChecked()) hobbies.add("Sports");
        if (hobbyMusic.isChecked()) hobbies.add("Music");

        // Add the custom hobby if it exists
        String customHobbyText = customHobby.getText().toString().trim();
        if (!customHobbyText.isEmpty()) {
            hobbies.add(customHobbyText);
        }

        // Convert the hobbies list to a string
        String selectedHobbies = String.join(", ", hobbies);

        // Create a bundle to pass the data to ProfileDisplayFragment
        Bundle bundle = new Bundle();
        bundle.putString("fullName", fullName);
        bundle.putString("age", ageValue);
        bundle.putString("email", emailValue);
        bundle.putString("phone", phone);
        bundle.putString("address", addressValue);
        bundle.putString("occupation", occupationValue);
        bundle.putString("gender", gender);
        bundle.putString("maritalStatus", maritalStatus);
        bundle.putString("hobbies", selectedHobbies);

        // Navigate to ProfileDisplayFragment
        ProfileDisplayFragment displayFragment = new ProfileDisplayFragment();
        displayFragment.setArguments(bundle);
        getFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, displayFragment)
                .addToBackStack(null)
                .commit();
    }
}
