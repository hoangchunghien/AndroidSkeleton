package me.hoangchunghien.popularmovies.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import me.hoangchunghien.popularmovies.R;
import me.hoangchunghien.popularmovies.data.api.OrderCriteria;
import me.hoangchunghien.popularmovies.data.api.TheMovieDbApi;
import me.hoangchunghien.popularmovies.data.model.Movie;
import me.hoangchunghien.popularmovies.ui.common.BaseFragment;
import rx.functions.Action1;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends BaseFragment {

    @Inject
    TheMovieDbApi api;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        api.discoverMovies(OrderCriteria.POPULAR)
                .subscribe(new Action1<Movie.Response>() {
                    @Override
                    public void call(Movie.Response response) {
                        Log.d("MainFragment", "Movies size: " + response.movies.size());
                    }
                });
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}
