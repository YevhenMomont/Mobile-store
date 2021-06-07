package com.example.store.authorization;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.store.MainActivity;
import com.example.store.R;
import com.example.store.product.ProductFragment;
import com.example.store.user.User;
import com.example.store.utils.TextValidator;
import com.example.store.web.WebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    EditText email, password;

    Button login;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        email = view.findViewById(R.id.editTextTextEmailAddress);
        password = view.findViewById(R.id.editTextTextPassword);

        login = view.findViewById(R.id.authLoginButton);

        login.setOnClickListener(v -> loginEvent());

        return view;
    }

    private void loginEvent() {
        if (TextValidator.emailValidation(email.getText()) && TextValidator.passwordValidation(password.getText())) {
            String authHeader = "Basic " + android.util.Base64.encodeToString((email.getText().toString() + ":" + password.getText().toString()).getBytes(), Base64.NO_WRAP);
            WebService.getInstance().getUserApi().getUserByEmail(authHeader).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.body() != null) {
                        User user = new User();

                        user.setId(response.body().getId());
                        user.setFirstName(response.body().getFirstName());
                        user.setLastName(response.body().getLastName());
                        user.setEmail(response.body().getEmail());
                        user.setPassword(password.getText().toString());
                        user.setRoles(response.body().getRoles());

                        ((MainActivity) getActivity()).setUser(user);

                        FragmentTransaction fragmentTransaction = getActivity()
                                .getSupportFragmentManager()
                                .beginTransaction()
                                .setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out);
                        fragmentTransaction.replace(R.id.authorization, ProductFragment.newInstance());
                        fragmentTransaction.commit();

                        Toast.makeText(getActivity(), "You are authorized", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getContext(), R.string.error_login, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.d("Login", "onFailure: ");
                    Toast.makeText(getContext(), R.string.error_login, Toast.LENGTH_LONG).show();
                }
            });
        }
        if (((MainActivity) getActivity()).getUser() != null) {

        }
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

}
