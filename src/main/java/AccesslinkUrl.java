import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.Key;

public class AccesslinkUrl extends GenericUrl {

    @Key
    private String fields;
    public AccesslinkUrl(String encodedUrl) {
        super(encodedUrl);
    }
public String getFields() {
        return fields;
}
    public void setFields(String fields) {
        this.fields = fields;
    }
}
