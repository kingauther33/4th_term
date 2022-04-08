package com.example.sqlitedemo;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CredentialDao {
    @Insert(onConflict = REPLACE)
    void insertCredential(Credential credential);

    @Update
    int updateCredential(Credential credential);

    @Query("UPDATE credentials SET account_id = :account_id, " +
            "access_token = :access_token, " +
            "refresh_token = :refresh_token, " +
            "expire_time = :expire_time, " +
            "created_at = :created_at, " +
            "updated_at = :updated_at, " +
            "status = :status WHERE account_id = :account_id")
    int updateCredential(int account_id,
                         String access_token,
                         String refresh_token,
                         String expire_time,
                         String created_at,
                         String updated_at,
                         int status);

    @Delete
    void deleteCredential(Credential credential);

    @Query("SELECT * FROM credentials")
    List<Credential> getAllCredentials();

    @Query("SELECT * FROM credentials WHERE account_id = :id")
    List<Credential> getCredential(int id);

    @Query("DELETE FROM credentials WHERE account_id = :id")
    int deleteCredential(int id);

    @Query("DELETE FROM credentials")
    void deleteAll();
}
