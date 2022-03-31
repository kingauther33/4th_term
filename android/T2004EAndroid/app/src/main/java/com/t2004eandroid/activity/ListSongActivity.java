package com.t2004eandroid.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.StrictMode;

import com.t2004eandroid.R;
import com.t2004eandroid.adapter.ListSongAdapter;
import com.t2004eandroid.entity.Song;
import com.t2004eandroid.service.SongService;
import com.t2004eandroid.util.RetrofitGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class ListSongActivity extends AppCompatActivity {

    private SongService songService;
    private RecyclerView recyclerViewListSong;
    private List<Song> songs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // set permission lại cho internet
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_song);
        initView();
        initData();
    }

    private void initView() {
        songs = new ArrayList<>();
        recyclerViewListSong = findViewById(R.id.recycler_view_list_song);
        recyclerViewListSong.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewListSong.setAdapter(new ListSongAdapter(this, songs));
    }

    private void initData() {
        if (songService == null) {
            songService = RetrofitGenerator.createService(SongService.class); // thêm token nếu cần
        }
        try {
            Response<List<Song>> listResponse = songService.getSong().execute();
            if (listResponse.isSuccessful()) {
                songs.clear();
                songs.addAll(listResponse.body());
                ((RecyclerView.Adapter) recyclerViewListSong.getAdapter()).notifyDataSetChanged();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}