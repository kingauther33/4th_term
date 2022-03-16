package service;


import entity.Song;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface SongService {

    @GET("/api/v1/songs")
    Call<List<Song>> getList();

}
