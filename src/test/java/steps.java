package githubAPI;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import io.cucumber.datatable.DataTable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import io.cucumber.java.en.*;
import io.github.cdimascio.dotenv.Dotenv;
import static org.junit.Assert.*;

public class steps {

    /** URL under test.*/
    private String base = "https://api.github.com/";
    /** initialize JSONParser*/
    private JSONParser parser = new JSONParser();
    /** http request */
    private HttpURLConnection connection;
    /** final endpoint */
    private String endPoint;
    /** account name */
    private String accountName;
    /** topic */
    private String topic;
    /** method */
    private final String reqMethod = "GET";
    /** username & token from env */
    private String username;
    private String token;

    /**
     * @param req: request method such as GET, POST, DELETE
     * @param endPoint: end point URL under test
     * @return HttpURLConnection
     * @throws IOException
     */
    private HttpURLConnection createRequest(String req, String endPoint) throws IOException {
        URL url = new URL(endPoint);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(req);
        String auth = username + ":" + token;
        String encodedAuth = "Basic " + Base64.getEncoder().encodeToString((auth).getBytes(StandardCharsets.UTF_8));
        connection.setRequestProperty("Authorization", encodedAuth);
        connection.connect();
        return connection;
    }

    /**
     *
     * @param i: InputStream GET from URL
     * @return payload: as a JSON object
     * @throws IOException
     * @throws ParseException
     */
    private JSONObject InputStreamToJSON(InputStream i) throws IOException, ParseException {
        JSONObject payload = null;
        BufferedReader in = new BufferedReader(new InputStreamReader(i));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            payload = (JSONObject) parser.parse(inputLine);
        }
        return payload;
    }

    @Given("GitHub account with username {word}")
    public void gitHubAccountWithUsername(String username) {
        accountName = username;
    }

    @When("I query the user information")
    public void queryTheUserData() {
        topic = "users";
        endPoint = base + topic + "/" + accountName;
    }

//    @Then("the name is (.*)$")
//    public void theNameIs(String fetchedName) throws IOException, ParseException {
//        if(fetchedName.contains("\"")){
//            fetchedName = fetchedName.replaceAll("\"","");
//        }
//        connection = createRequest(reqMethod, endPoint);
//        InputStream inputStream = connection.getInputStream();
//        JSONObject payload = InputStreamToJSON(inputStream);
//        assertEquals(payload.get("name"),fetchedName);
//    }
//
//    @Then("the email is (.*)$")
//    public void theEmailIs(String email) throws IOException, ParseException {
//        InputStream inputStream = connection.getInputStream();
//        JSONObject payload= InputStreamToJSON(inputStream);
//        assertEquals(payload.get("email"),email);
//    }

    @When("I query the repository called {word}")
    public void iQueryTheRepositoryCalled(String repository) throws IOException {
        topic = "repos";
        String reqMethod = "GET";
        endPoint = base + topic + "/" + accountName + "/" + repository;
        connection = createRequest(reqMethod, endPoint);
    }

    @Then("the message is {int}")
    public void theMessageIs(int code) throws IOException {
        assertEquals(code,connection.getResponseCode());
    }

    @Given("I am an authenticated user")
    public void iAmAnAuthenticatedUser() {
        Dotenv dotenv = Dotenv.load();
        username = dotenv.get("USER_NAME");
        token = dotenv.get("TOKEN");
    }

    @When("I query the user data for {word}")
    public void iQueryTheUserDataForUser(String username) {
        topic = "users";
        accountName = username;
        endPoint = base + topic + "/" + accountName;
    }

    @Then("the email is (.*)$")
    public void theEmailIs(String email) throws IOException, ParseException {
        if (email.contains("\"")){email = email.replaceAll("\"","");}
        connection = createRequest(reqMethod, endPoint);
        InputStream inputStream = connection.getInputStream();
        JSONObject payload = InputStreamToJSON(inputStream);
        if(email.equals("null")){assertNull(payload.get("email"));}
        else{assertEquals(payload.get("email"),email);}
    }

    @Then("the name is (.*)$")
    public void theNameIs(String name) throws IOException, ParseException {
        if (name.contains("\"")){name = name.replaceAll("\"","");}
        connection = createRequest(reqMethod, endPoint);
        InputStream inputStream = connection.getInputStream();
        JSONObject payload = InputStreamToJSON(inputStream);
        if(name.equals("null")){assertNull(payload.get("name"));}
        else{assertEquals(payload.get("name"),name);}
    }

    @Given("user with these info")
    public void userWithTheseInfo(DataTable dataTable) throws IOException, ParseException {
        List<Map<String, String>> userList = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> e : userList){
            iQueryTheUserDataForUser(e.get("username"));
            theNameIs(e.get("name"));
        }
    }
}
