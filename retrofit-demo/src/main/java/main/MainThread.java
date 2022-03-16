package main;

import entity.Song;
import retrofit.RetrofitGenerator;
import service.SongService;

import java.io.IOException;
import java.util.List;

public class MainThread {
    public static void main(String[] args) throws IOException {
        SongService songService = RetrofitGenerator.createService(SongService.class);;

        List<Song> listSong =  songService.getList().execute().body();

        System.out.println(listSong.size());

        for (Song song :
                listSong) {
            System.out.println(song.toString());
        }
    }
}
