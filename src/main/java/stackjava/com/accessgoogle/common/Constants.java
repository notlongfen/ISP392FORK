/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stackjava.com.accessgoogle.common;

import io.github.cdimascio.dotenv.Dotenv;

/**
 *
 * @author notlongfen
 */
public class Constants {
    
    // public static String GOOGLE_CLIENT_ID = "YOUR_GOOGLE_CLIENT_ID"; 
    // public static String GOOGLE_CLIENT_SECRET = "YOUR_GOOGLE_CLIENT_SECRET";
    // public static String GOOGLE_REDIRECT_URI = "http://localhost:8080/ISP392/google-login";
    // public static String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";
    // public static String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
    // public static String GOOGLE_GRANT_TYPE = "authorization_code";
    private static Dotenv dotenv = Dotenv.configure().directory("/home/notlongfen/code/java/ISP392/.env").load();
    public static String GOOGLE_CLIENT_ID = dotenv.get("GOOGLE_CLIENT_ID");
    public static String GOOGLE_CLIENT_SECRET = dotenv.get("GOOGLE_CLIENT_SECRET"); ;
    public static String GOOGLE_REDIRECT_URI = dotenv.get("GOOGLE_REDIRECT_URI");
    public static String GOOGLE_LINK_GET_TOKEN = dotenv.get("GOOGLE_LINK_GET_TOKEN");
    public static String GOOGLE_LINK_GET_USER_INFO = dotenv.get("GOOGLE_LINK_GET_USER_INFO");
    public static String GOOGLE_GRANT_TYPE = dotenv.get("GOOGLE_GRANT_TYPE");
}
