package com.example.lab2app;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment implements OnItemClickListener {

    public static String MOVIE_TITLE = "movie title";
    public static String MOVIE = "movie";

    public static List<MovieModel> movieList = new ArrayList<>();

    public FirstFragment() { super(R.layout.fragment_first);}

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeMovieList();

        CustomAdapter adapter = new CustomAdapter(movieList, this);
        RecyclerView rv = view.findViewById(R.id.recycler_view);
        rv.setAdapter(adapter);
    }

    private void initializeMovieList() {
        movieList.add(new MovieModel(
                "Avengers: Infinity War",
                "148min",
                R.drawable.avengers
        ));
        movieList.add( new MovieModel(
                "Green Book",
                "130min",
                R.drawable.greenbook
        ));
    }

    @Override
    public void onItemClick(MovieModel item) {
        Bundle bundle = new Bundle();
        bundle.putString(MOVIE_TITLE, item.getTitle());
        bundle.putParcelable(MOVIE, item);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        SecondFragment secondFragment = new SecondFragment();
        secondFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragment_container, secondFragment)
                .addToBackStack(null);

        fragmentTransaction.commit();
    }
}
