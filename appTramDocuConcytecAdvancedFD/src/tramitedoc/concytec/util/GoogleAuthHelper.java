package tramitedoc.concytec.util;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collection;

public class GoogleAuthHelper {
	
	
	
	/**dsic.concytec  
	 private static final String CLIENT_ID = "597300230948-cnt61stcrk4hnl0u5blc1tpvcisg7lkn.apps.googleusercontent.com";
	 private static final String CLIENT_SECRET = "T70U30Fs7Qhtqs42CXE5gZXj";
	**/
	/**	
	 private static final String CLIENT_ID = "197110879411-v3lahqdbctp7d8qs3ha3re0mk5vg5e6i.apps.googleusercontent.com";
	 private static final String CLIENT_SECRET = "AmI1yH5dLPzq7Byet65cRVV5";

	 /**dsic.concytec new  **/
	 private static final String CLIENT_ID = "53232345992-cfi09d7i7m2em4g4r50ut7b9t62abu1g.apps.googleusercontent.com";
	 private static final String CLIENT_SECRET = "FC1Jd8GtERKSICYJ_7XkUb7w";
	
	//private static final String CALLBACK_URI = "http://localhost:8080/appTramDocuConcytecAdvanced/LoginGoogle.do";
	
	private static final String CALLBACK_URI = "http://dsic.concytec.gob.pe:8080/appTramDocuConcytecAdvancedFD/LoginGoogle.do";
	 
	//private static final String CALLBACK_URI = "http://dsic.concytec.gob.pe:8080/appTramDocuConcytecAdvancedFD/LoginGoogle.do";
	
	//private static final String CALLBACK_URI = "http://192.168.5.172:8080/appTramDocuConcytecAdvanced/LoginGoogle.do";
	//private static final String CALLBACK_URI ="http://192.168.5.114/appTramDocu/LoginGoogle.do";
	//private static final String CALLBACK_URI = "http://std.concytec.gob.pe/appTramDocu/LoginGoogle.do";
	
    private static final Collection<String> SCOPE = Arrays.asList("https://www.googleapis.com/auth/userinfo.profile", "https://www.googleapis.com/auth/userinfo.email");
    private static final String USER_INFO_URL = "https://www.googleapis.com/oauth2/v1/userinfo";
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    private String stateToken;

    private final GoogleAuthorizationCodeFlow flow;

    public GoogleAuthHelper() {
        flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT,
                JSON_FACTORY, CLIENT_ID, CLIENT_SECRET, SCOPE).build();
        generateStateToken();
    }
    
    public String buildLoginUrl() {
        final GoogleAuthorizationCodeRequestUrl url = flow.newAuthorizationUrl();
        return url.setRedirectUri(CALLBACK_URI).setState(stateToken).build();
    }
   
    private void generateStateToken() {
        SecureRandom sr1 = new SecureRandom();
        stateToken = "google;" + sr1.nextInt();
    }
    
    public String getStateToken() {
        return stateToken;
    }
   
    public String getUserInfoJson(final String authCode) throws IOException {
    	System.getProperties().put("proxySet", "true");
    	System.getProperties().put("proxyHost", "192.168.5.14");
    	System.getProperties().put("proxyPort", "3128");
    	
        final GoogleTokenResponse response = flow.newTokenRequest(authCode).setRedirectUri(CALLBACK_URI).execute();
        final Credential credential = flow.createAndStoreCredential(response, null);
        final HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory(credential);
        
        final GenericUrl url = new GenericUrl(USER_INFO_URL);
        final HttpRequest request = requestFactory.buildGetRequest(url);
        request.getHeaders().setContentType("application/json");
        final String jsonIdentity = request.execute().parseAsString();
        return jsonIdentity;
    }

}
