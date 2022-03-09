package demo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DemoKeycloakApi {

    public static void main(String[] args) {
       String accessToken = login();
        getListUser(accessToken);
    }

    public static List<User> getListUser(String accessToken){
        String userlink = "http://localhost:8180/auth/admin/realms/springboot-quickstart/users";
        ArrayList<User> users = new ArrayList<>();
        try {
            Document document = Jsoup
                    .connect(userlink)
                    .method(Connection.Method.GET)
                    .ignoreContentType(true)
                    .header("Authorization", "Bearer " + accessToken).execute().parse();
            Type listType = new TypeToken<ArrayList<User>>(){}.getType();
            users = new Gson().fromJson(document.text(), listType);
            for (User u :
                    users) {
                System.out.println(u.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    // login dành cho quyền admin. phải có hàm login khác dành cho user.
    // khi gọi api của keycloak mà trả về 403 thì phải gọi lại hàm này, nhớ cache biến.
    public static String login() {
        String username = "admin";
        String password = "admin";
        String clientId = "service01";
        String loginLink = "http://localhost:8180/auth/realms/springboot-quickstart/protocol/openid-connect/token";
        try {
            Document document = Jsoup
                    .connect(loginLink)
                    .method(Connection.Method.POST)
                    .ignoreContentType(true)
                    .data("client_id", clientId)
                    .data("username", username)
                    .data("password", password)
                    .data("grant_type", "password").execute().parse();
            Credential credential = new Gson().fromJson(document.text(), Credential.class);
            System.out.println(credential.getAccess_token());
            return credential.getAccess_token();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
