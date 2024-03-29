package com.example.store.authorization;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.store.MainActivity;
import com.example.store.R;
import com.example.store.user.User;
import com.example.store.utils.TextValidator;
import com.example.store.web.WebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterFragment extends Fragment {

    EditText firstName, lastName, email, password;

    TextView emailValid;

    Button register;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        firstName = view.findViewById(R.id.editTextFirstName);
        lastName = view.findViewById(R.id.editTextLastName);
        email = view.findViewById(R.id.registerEmailAddress);
        emailValid = view.findViewById(R.id.editTextTextEmailAddressValid);
        password = view.findViewById(R.id.editTextTextPassword);

        register = view.findViewById(R.id.authRegisterButton);


        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextValidator.emailValidation(email.getText())) {
                    emailValid.setText("email valid");
                    emailValid.setTextColor(Color.GREEN);
                } else {
                    emailValid.setText("invalid email");
                    emailValid.setTextColor(Color.RED);
                }
            }
        });

        register.setOnClickListener(v -> registerEvent());


        return view;
    }

    private void registerEvent() {
        if (TextValidator.passwordValidation(password.getText())) {
            User user = new User();

            user.setEmail(email.getText().toString());
            user.setPassword(password.getText().toString());
            user.setFirstName(firstName.getText().toString());
            user.setLastName(lastName.getText().toString());

            WebService.getInstance()
                    .getUserApi()
                    .postUser(user)
                    .enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            if (response.body() != null) {
//                                ((MainActivity) getActivity()).setUser(response.body());

                                FragmentTransaction fragmentTransaction = getActivity()
                                        .getSupportFragmentManager()
                                        .beginTransaction()
                                        .setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out);
                                fragmentTransaction.replace(R.id.authorization, LoginFragment.newInstance());
                                fragmentTransaction.commit();

                                Toast.makeText(getContext(), "Success register", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Toast.makeText(getContext(), "Register failed", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

}