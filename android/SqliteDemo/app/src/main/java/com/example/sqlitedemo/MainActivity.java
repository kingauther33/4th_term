package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    private DBHelper db;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = AppDatabase.getAppDatabase(this);

        insertCredential();

        getAllCredentials();

//        updateCredential(1);

        getAllCredentials();

        findCredential(1);

//        deleteAllCredential();

        getAllCredentials();

        ///region RAW DBHelper

        //        db = new DBHelper(this);
        //
        //        // INSERT USER
        //        insertUser();
        //
        //        // GET DATA
        //        getALlUser();
        //
        //        // UPDATE DATA
        //        updateUser();
        //
        //        // CHECK AFTER UPDATE
        //        getALlUser();
        //
        //        // DELETE DATA
        //        deleteUser();
        //
        //        // CHECK AFTER UPDATE
        //        getALlUser();

        ///engregion
    }

    private void insertCredential() {
        Credential credential = new Credential();
        credential.setAccess_token("214872187129waewaggewa5");
        credential.setRefresh_token("2148721871295124215");
        credential.setExpire_time("2022-01-01");
        credential.setUpdated_at("2022-01-12");
        credential.setAccount_id(1);
        credential.setStatus(1);
        credential.setCreated_at("2021-12-31");
        db.credentialDao().insertCredential(credential);
    }

    private void getAllCredentials() {
        List<Credential> credentials = db.credentialDao().getAllCredentials();
        for (Credential credential:
             credentials) {
//            Log.d("TAG", "access_token: " + credential.getAccess_token()
//                    + " refresh_token: " + credential.getRefresh_token()
//                    + " account_id: " + credential.getAccount_id()
//                    + " expire_time: " + credential.getExpire_time()
//                    + " created_at: " + credential.getCreated_at()
//                    + " updated_at: " + credential.getUpdated_at()
//                    + " status" + credential.getStatus());
        }
    }

    private void updateCredential(int id) {
        List<Credential> credentials = db.credentialDao().getCredential(id);
        Credential credential = credentials.get(0);
        Log.d("credential", String.valueOf(credential.getAccount_id()));
        credential.setAccess_token("38r9821irjaru21orjfsiofkjagi");
        credential.setRefresh_token("updated_refresh_token");
        Log.d("access_token", String.valueOf(credential.getAccess_token()));
        Log.d("refresh_token", String.valueOf(credential.getRefresh_token()));
        int updatedRows = db.credentialDao().updateCredential(credential.getAccount_id(),
                credential.getAccess_token(),
                credential.getRefresh_token(),
                credential.getExpire_time(),
                credential.getCreated_at(),
                credential.getUpdated_at(),
                credential.getStatus());
//        Log.d("ROWS AFFTRECTED", String.valueOf(updatedRows));
    }

    private void findCredential(int id) {
        List<Credential> credentials = db.credentialDao().getCredential(id);
//        Log.d("TAG", "Find bookmark with" + id +  " access_token: " + credentials.get(0).getAccess_token());
    }

    private void deleteAllCredential() {
        db.credentialDao().deleteAll();
    }

    ///region Raw DBHelper
//    private void insertUser() {
//        Credential credential = new Credential("2184892175982175821985666", "1oiu29821587217589", 2, "921482195891052",
//                "2022-12-01", "2022-01-12", 1);
//        String message = db.insertDB(credential);
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//    }
//
//    private void getALlUser() {
//        List<Credential> credentials = db.getAllUsers();
//        Log.d("USER", String.valueOf(credentials.size()));
//        for (Credential credential :
//                credentials) {
//            Log.d("USER", "token: " + credential.getAccess_token()
//                    + " refresh_token: " + credential.getRefresh_token()
//                    + " account_id: " + credential.getAccount_id()
//                    + " expire_time: " + credential.getExpire_time()
//                    + " created_at: " + credential.getCreated_at()
//                    + " updated_at: " + credential.getUpdated_at()
//                    + " status: " + credential.getStatus());
//        }
//    }
//
//    private void updateUser() {
//        Credential credential = new Credential("2184892175982175821985", "1oiu29821587217589", 2, "921482195891052",
//                "2022-12-01", "2022-01-12", 1);
//        String message = db.updateUser(credential);
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//    }
//
//    private void deleteUser() {
//        db.deleteUser(2);
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        db.close();
//    }
    ///endregion
}