package com.example.store.authorization;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.store.R;
import com.example.store.web.WebService;

public class AuthorizationFragment extends Fragment {

    private TextView register, login;

    private WebService service;

    public static AuthorizationFragment newInstance() {
        return new AuthorizationFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_authorization, container, false);

        loadFragment(LoginFragment.newInstance());


        register = view.findViewById(R.id.go_to_register);

        login = view.findViewById(R.id.go_to_login);

        register.setOnClickListener(v -> {
            loadFragment(RegisterFragment.newInstance());
            register.setVisibility(View.INVISIBLE);
            login.setVisibility(View.VISIBLE);
        });

        login.setOnClickListener(v -> {
            loadFragment(LoginFragment.newInstance());
            login.setVisibility(View.INVISIBLE);
            register.setVisibility(View.VISIBLE);

        });
        return view;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out);;
        fragmentTransaction.replace(R.id.authorization, fragment);
        fragmentTransaction.commit();
    }
}