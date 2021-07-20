package com.example.spotifykaraokeee.ui.SearchSong;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.spotifykaraokeee.R;

public class SearchSongFragment extends Fragment {

    private SearchSongViewModel searchSongViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        searchSongViewModel =
                new ViewModelProvider(this).get(SearchSongViewModel.class);
        View root = inflater.inflate(R.layout.fragment_search_song, container, false);
        //originally was a dashboard fragment, changed name to search song fragment
        final TextView textView = root.findViewById(R.id.text_search_song);
        searchSongViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}