import java.sql.SQLOutput;

public class OAuth2ClientCredentials {
    public static final String DOMAIN = "127.0.0.1";
    public static String API_KEY = "";     //id = 67424c18-23c5-4170-86c4-03cee1419298
    public static String API_SECRET = ""; // secret = a2e90b01-21c4-4655-ab94-7c0d5b11ece1
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
