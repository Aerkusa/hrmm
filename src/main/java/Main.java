import com.google.api.client.auth.oauth2.*;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.*;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.*;


public class Main {

    private static final File DATA_STORE_DIR = new File(System.getProperty("user.home"), ".store/accesslink");

    private static FileDataStoreFactory DATA_STORE_FACTORY;

    private static final String SCOPE = "";

    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    static final JsonFactory JSON_FACTORY = new GsonFactory();

    private static final String TOKEN_SERVER_URL = "https://polarremote.com/v2/oauth2/token";

    private static final String AUTHORIZATION_SERVER_URL = "https://flow.polar.com/oauth2/authorization";

    private static Credential authorize() throws Exception {
        OAuth2ClientCredentials.errorIfNotSpecified();

        AuthorizationCodeFlow flow =
                new AuthorizationCodeFlow.Builder(
                        BearerToken.authorizationHeaderAccessMethod(),
                        HTTP_TRANSPORT,
                        JSON_FACTORY,
                        new GenericUrl(TOKEN_SERVER_URL),
                        new ClientParametersAuthentication(
                                OAuth2ClientCredentials.API_KEY, OAuth2ClientCredentials.API_SECRET),
                        OAuth2ClientCredentials.API_KEY,
                        AUTHORIZATION_SERVER_URL)
                        .setScopes(List.of(SCOPE))
                        .setDataStoreFactory(DATA_STORE_FACTORY)
                        .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder()
                .setHost(OAuth2ClientCredentials.DOMAIN)
                .setPort(OAuth2ClientCredentials.PORT)
                .build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }
    private static void run(HttpRequestFactory requestFactory) throws IOException {
        AccesslinkUrl url = new AccesslinkUrl("https://www.polaraccesslink.com/v3");
        url.setFields("heartrate");

        HttpRequest request = requestFactory.buildGetRequest(url);
        /* TODO: real json parse class */
        Exercise heartRate = request.execute().parseAs(Exercise.class);
    }

    public static void main(String[] args) {
            new GUI();
    }
    public static void makeACall(String token){
     
     try{   
        // How to get html of any webPage
      URL url = new URL("https://usman-z.github.io/3Dweb/");
      //Retrieving the contents of the specified page
      Scanner sc = new Scanner(url.openStream());
      //Instantiating the StringBuffer class to hold the result
      StringBuffer sb = new StringBuffer();
      while(sc.hasNext()) {
         sb.append(sc.next());
         //System.out.println(sc.next());
      }
      //Retrieving the String from the String Buffer object
      String result = sb.toString();
      //Removing the HTML tags
      result = result.replaceAll("<[^>]*>", "");
         System.out.println("----------------------------* Content of the Page! *----------------------------");
      System.out.println(result);
     } catch(MalformedURLException e){
         JOptionPane.showMessageDialog(new JFrame(), "URL Exception", "Error", JOptionPane.ERROR_MESSAGE);
         System.exit(0);
     } catch(IOException e){
         JOptionPane.showMessageDialog(new JFrame(), "Input/Ouput Exception", "Error", JOptionPane.ERROR_MESSAGE);
         System.exit(0);
     } catch(Exception e){
         JOptionPane.showMessageDialog(new JFrame(), "An Exception", "Error", JOptionPane.ERROR_MESSAGE);
         System.exit(0);
     }
     

     
     
     
     
     
     
//      try {
//            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
//            final Credential credential = authorize();
//            HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory(
//                request -> {
//                    credential.initialize(request);
//                    request.setParser(new JsonObjectParser(JSON_FACTORY));
//                });
//            run(requestFactory);
//            return;
//        } catch (IOException e) {
//            System.err.println(e.getMessage());
//        } catch (Throwable t) {
//            t.printStackTrace();
//        }
//        finally{
//        System.exit(0);
//        }
    }
}
/*
                                      OLD CODE
public class Main {
    private static final File DATA_STORE_DIR =
            new File(System.getProperty("user.home"), ".store/accesslink");
    private static FileDataStoreFactory DATA_STORE_FACTORY;
    private static final String SCOPE = "";
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    static final JsonFactory JSON_FACTORY = new GsonFactory();
    private static final String TOKEN_SERVER_URL = "https://polarremote.com/v2/oauth2/token";
    private static final String AUTHORIZATION_SERVER_URL = "https://flow.polar.com/oauth2/authorization";
    private static Credential authorize() throws Exception {
        OAuth2ClientCredentials.errorIfNotSpecified();
        AuthorizationCodeFlow flow =
                new AuthorizationCodeFlow.Builder(
                        BearerToken.authorizationHeaderAccessMethod(),
                        HTTP_TRANSPORT,
                        JSON_FACTORY,
                        new GenericUrl(TOKEN_SERVER_URL),
                        new ClientParametersAuthentication(
                                OAuth2ClientCredentials.API_KEY, OAuth2ClientCredentials.API_SECRET),
                        OAuth2ClientCredentials.API_KEY,
                        AUTHORIZATION_SERVER_URL)
                        //.setScopes(Arrays.asList(SCOPE))
                        .setDataStoreFactory(DATA_STORE_FACTORY)
                        .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder()
                .setHost(OAuth2ClientCredentials.DOMAIN)
                .setPort(OAuth2ClientCredentials.PORT)
                .build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("");
    }
    private static void run(HttpRequestFactory requestFactory) throws IOException {
        AccesslinkUrl url = new AccesslinkUrl("https://www.polaraccesslink.com/v3");
        url.setFields("heartrate");
        HttpRequest request = requestFactory.buildGetRequest(url);
        /* TODO: real json parse class 
        Exercise heartRate = request.execute().parseAs(Exercise.class);
    }
    public static void main(String[] args) {
        try {
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
            final Credential credential = authorize();
            HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory(
                    new HttpRequestInitializer() {
                        @Override
                        public void initialize(HttpRequest request) throws IOException {
                            credential.initialize(request);
                            request.setParser(new JsonObjectParser(JSON_FACTORY));
                        }
                    });
            run(requestFactory);
            return;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
        System.exit(1);
    }
}
*/