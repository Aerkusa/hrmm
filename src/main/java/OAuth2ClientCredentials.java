import java.sql.SQLOutput;

public class OAuth2ClientCredentials {
    public static final String DOMAIN = "127.0.0.1";
    public static final String API_KEY = "Enter API Key";
    public static final String API_SECRET = "Enter API Secret";
    public static final int PORT = 8080;

    public static void errorIfNotSpecified() {
        if (API_KEY.startsWith("Enter ") || API_SECRET.startsWith("Enter ")) {
            System.out.println(
                    "Enter API Key and API Secret from http://www.accesslink.com/developer"
                     + " into API_KEY and API_SECRET in "
                     + OAuth2ClientCredentials.class);
            System.exit(1);
        }
    }
}
