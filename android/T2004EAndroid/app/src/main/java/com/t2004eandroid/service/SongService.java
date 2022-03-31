package com.t2004eandroid.service;

import com.t2004eandroid.entity.Song;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SongService {
    @GET("/api/v1/songs")
    Call<List<Song>> getSong();
}
