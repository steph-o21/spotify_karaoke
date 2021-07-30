package com.example.spotifykaraokeee.ui.SearchSong;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.fragment.app.ListFragment;

import com.example.spotifykaraokeee.MainActivity;
import com.example.spotifykaraokeee.R;

import java.util.ArrayList;
import java.util.List;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Track;
import kaaes.spotify.webapi.android.models.TracksPager;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SearchSongFragment extends ListFragment implements SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener {

    List<String> trackNames;
    private ArrayAdapter<String> mAdapter;
    private Context mContext;

    public String access_token1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        setHasOptionsMenu(true);

        //access token for spotify web api
        access_token1 = (MainActivity.access_token);

        String songTitle = "All we need";
        populateList(songTitle);

    }
    //when clicking a list item
    @Override
    public void onListItemClick(ListView listView, View v, int position, long id) {
        /*
        String item = (String) listView.getAdapter().getItem(position);
        if (getActivity() instanceof OnItem1SelectedListener) {
            ((OnItem1SelectedListener) getActivity()).OnItem1SelectedListener(item);
        }
        getFragmentManager().popBackStack();
        */
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.song_search_fragment, container, false);
        ListView listView = (ListView) layout.findViewById(android.R.id.list);
        TextView emptyTextView = (TextView) layout.findViewById(android.R.id.empty);
        listView.setEmptyView(emptyTextView);
        return layout;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint("Search");

        super.onCreateOptionsMenu(menu, inflater);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        populateList(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (newText == null || newText.trim().isEmpty()) {
            resetSearch();
            return false;
        }

        List<String> filteredValues = new ArrayList<String>(trackNames);
        for (String value : trackNames) {
            if (!value.toLowerCase().contains(newText.toLowerCase())) {
                filteredValues.remove(value);
            }
        }

        mAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, filteredValues);
        setListAdapter(mAdapter);

        return false;
    }

    public void resetSearch() {
        mAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, trackNames);
        setListAdapter(mAdapter);
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        return true;
    }

    public interface OnItem1SelectedListener {
        void OnItem1SelectedListener(String item);
    }

    private void populateList(String query){

        trackNames = new ArrayList<>();

        //song title
        String song_title = query;

        //spotify web api
        SpotifyApi api = new SpotifyApi();
        api.setAccessToken(access_token1);
        SpotifyService spotify = api.getService();
        //search song by song title
        spotify.searchTracks(song_title, new Callback<TracksPager>() {
            @Override
            public void success(TracksPager tracks, Response response) {

                Log.d("sucesss", ("size: " + tracks.tracks.items.size()));

                for(int i = 0; i<tracks.tracks.items.size();i++){
                    Track track = tracks.tracks.items.get(i);

                    //adds track name to list
                    trackNames.add(track.name);
                }
            }
            @Override
            public void failure(RetrofitError error) {
                Log.d("u failed homie", error.toString());
            }
        });
        mAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, trackNames);
        setListAdapter(mAdapter);
    }
}

        /*
        searchView = (SearchView)getView().findViewById(R.id.search_bar);
        searchView.getQuery();
        imageView = (ImageView)getView().findViewById(R.id.album_cover);

        trackName = (TextView)getView().findViewById(R.id.track_title);
//        trackName.setOnClickListener();
        //song title
        String song_title = "All we need";

        //initialize access token string
        access_token1 = (MainActivity.access_token);

        //spotify web api
        SpotifyApi api = new SpotifyApi();
        api.setAccessToken(access_token1);
        SpotifyService spotify = api.getService();
        //search song by song title
        spotify.searchTracks(song_title, new Callback<TracksPager>() {
            @Override
            public void success(TracksPager tracks, Response response) {

                Log.d("sucesss", ("size: " + tracks.tracks.items.size()));

                for(int i = 0; i<tracks.tracks.items.size();i++){
                    Log.d("song:", tracks.tracks.items.get(i).name + "\n artist: " + tracks.tracks.items.get(i).artists.get(i).name);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("u failed homie", error.toString());
            }
        });
*/




