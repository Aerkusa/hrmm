import com.google.api.client.auth.oauth2.*;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


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
        /* TODO: real json parse class */
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
