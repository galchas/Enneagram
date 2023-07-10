package com.example.enneagram;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.enneagram.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {
    private FragmentMainBinding binding;

    private static final String THERAPIST = "therapist";
    private boolean therapist;
    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance(String therapist, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(THERAPIST, therapist);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            therapist = getArguments().getBoolean(THERAPIST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        binding.btbStart.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_quizFragment));
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}