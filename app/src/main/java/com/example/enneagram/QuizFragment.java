package com.example.enneagram;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.enneagram.databinding.FragmentQuizBinding;


public class QuizFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private FragmentQuizBinding binding;
    private String mParam1;
    private DeckHolder deckHolder;

    public QuizFragment() {
        // Required empty public constructor
    }

    public static QuizFragment newInstance(String param1) {
        QuizFragment fragment = new QuizFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentQuizBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        deckHolder = new DeckHolder(requireActivity());
        binding.txtTotalCards.setText(deckHolder.getTotal().toString());
        binding.txtQuestion.setText(deckHolder.getNextCard().question);
        binding.txtCardCounter.setText((deckHolder.getIndex()+1)+"");
        binding.btbTrue.setOnClickListener(v -> {
            deckHolder.setResult(true);
            flipCard(deckHolder.getNextCard());
        });
        binding.btbFalse.setOnClickListener(v -> {
            deckHolder.setResult(false);
            flipCard(deckHolder.getNextCard());
        });
        return view;
    }

    public void flipCard(Card nextCard){
        if(nextCard != null){
            binding.txtQuestion.setText("");
            binding.card.animate().rotationY(360).setDuration(500).withEndAction(() ->{
                binding.txtQuestion.setText(nextCard.question);
                binding.txtCardCounter.setText((deckHolder.getIndex()+1)+"");
            });
        }else if(deckHolder.getIndex()!=0){
            Card.CardType type = deckHolder.getResults();
            Toast.makeText(requireContext(), "נראה שאתה טיפוס מספר "+ type.ordinal()+1, Toast.LENGTH_LONG).show();
        }
    }

}