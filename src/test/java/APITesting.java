import org.json.simple.JSONArray;
import org.junit.Test;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
// import com.oracle.javafx.jmx.json.JSONException;

import static org.junit.Assert.*;

public class APITesting {

    /** URL under test.*/
    private String base = "https://suchonsite-server.herokuapp.com";
    /** initialize JSONParser*/
    private JSONParser parser = new JSONParser();

    /**
     * @param req: request method such as GET, POST, DELETE
     * @param endPoint: end point URL under test
     * @return HttpURLConnection
     * @throws IOException
     */
    private HttpURLConnection createRequest(String req, String endPoint) throws IOException {
        URL url = new URL(endPoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(req);
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

    /**
     *
     * @param i: InputStream GET from URL
     * @return arrayPayload: as a JSON Array
     * @throws IOException
     * @throws ParseException
     */
    private JSONArray InputStreamToJSONArray(InputStream i) throws IOException, ParseException {
        JSONArray arrayPayload = null;
        BufferedReader in = new BufferedReader(new InputStreamReader(i));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            arrayPayload = (JSONArray) parser.parse(inputLine);
        }
        return arrayPayload;
    }

    // -------------------- Basic Status Code Testing ------------------- //
    /**
     * Test Case ID: 1
     *
     * Test description: check whether or not GET all is fetch able.
     *
     * Given base url https://suchonsite-server.herokuapp.com
     * When GET with param /people/all
     * Then status code is 200 OK
     *
     * @throws IOException
     */
    @Test
    public void testGetAllUsers() throws IOException {
        String endPoint = base + "/people/all";
        String reqMethod = "GET";
        HttpURLConnection connection = createRequest(reqMethod, endPoint);
        int responseCode = connection.getResponseCode();
        String responseMsg = connection.getResponseMessage();
        assertEquals(200,responseCode);
        assertEquals("OK",responseMsg);
    }

    /**
     * Test Case ID: 2
     *
     * Test description: check whether or not GET by_date is fetch able.
     *
     * Given base url https://suchonsite-server.herokuapp.com
     * When GET with param /people/by_date/11-11-2021 (valid date format)
     * Then status code is 200 OK
     *
     * @throws IOException
     */
    @Test
    public void testGetUsersByExistDate() throws IOException {
        String endPoint = base + "/people/by_date/11-11-2021";
        String reqMethod = "GET";
        HttpURLConnection connection = createRequest(reqMethod, endPoint);
        int responseCode = connection.getResponseCode();
        String responseMsg = connection.getResponseMessage();
        assertEquals(200,responseCode);
        assertEquals("OK",responseMsg);
    }

    /**
     * Test Case ID: 3
     *
     * Test description: check whether or not GET by_date return 404 if user not exist.
     *
     * Given base url https://suchonsite-server.herokuapp.com
     * When GET with param /people/by_date/31-10-3021 (not exist for sure)
     * Then status code is 204 No Content
     *
     * @throws IOException
     */
    @Test
    public void testGetUsersByNonExistDate() throws IOException {
        String endPoint = base + "/people/by_date/31-10-3021";
        String reqMethod = "GET";
        HttpURLConnection connection = createRequest(reqMethod, endPoint);
        int responseCode = connection.getResponseCode();
        String responseMsg = connection.getResponseMessage();
        assertEquals(204,responseCode);
        assertEquals("No Content",responseMsg);
    }

    /**
     * Test Case ID: 4
     *
     *
     * Test description: check whether or not GET by date not except empty date.
     *
     * Given base url https://suchonsite-server.herokuapp.com
     * When GET with param /people/by_date (without date)
     * Then status code is 406 No date param included in request
     *
     * @throws IOException
     */
    @Test
    public void testGetByDateWithEmptyParam() throws IOException {
        String endPoint = base + "/people/by_date";
        String reqMethod = "GET";
        HttpURLConnection connection = createRequest(reqMethod, endPoint);
        int responseCode = connection.getResponseCode();
        String responseMsg = connection.getResponseMessage();
        assertEquals(406,responseCode);
        assertEquals("No date param included in request",responseMsg);
    }

    /**
     * Test Case ID: 5
     *
     *
     * Test description: check whether or not GET by_date is not accept any incorrect param format.
     *
     * Given base url https://suchonsite-server.herokuapp.com
     * When GET with param /people/by_date/21-OCT-2021 (invalid date format)
     * Then status code is 404 Not Found
     *
     * @throws IOException
     */
    @Test
    public void testGetUsersByInvalidDateFormat1() throws IOException {
        String endPoint = base + "/people/by_date/21-OCT-2021";
        String reqMethod = "GET";
        HttpURLConnection connection = createRequest(reqMethod, endPoint);
        int responseCode = connection.getResponseCode();
        String responseMsg = connection.getResponseMessage();
        assertEquals(204,responseCode);
        assertEquals("No Content",responseMsg);
    }

//    /**
//     * Test Case ID: 6
//     *
//     * Test description: check whether or not GET by_date is not accept any incorrect param format.
//     *
//     * Given base url https://suchonsite-server.herokuapp.com
//     * When GET with param /people/by_date/21/10/2021 (invalid date format)
//     * Then status code is 404 Not Found
//     *
//     * @throws IOException
//     */
//    @Test
//    public void testGetUsersByInvalidDateFormat2() throws IOException {
//        String endPoint = base + "/people/by_date/21/10/2021";
//        String reqMethod = "GET";
//        HttpURLConnection connection = createRequest(reqMethod, endPoint);
//        int responseCode = connection.getResponseCode();
//        String responseMsg = connection.getResponseMessage();
//        assertEquals(204,responseCode);
//        assertEquals("No people in this date",responseMsg);
//    }

    /**
     * Test Case ID: 7
     *
     *
     * Test description: check whether or not GET by_date is not accept any incorrect param format.
     *
     * Given base url https://suchonsite-server.herokuapp.com
     * When GET with param /people/by_date/hacking (invalid date format)
     * Then status code is 404 Not Found
     *
     * @throws IOException
     */
    @Test
    public void testGetUsersByInvalidDateFormat3() throws IOException {
        String endPoint = base + "/people/by_date/hacking";
        String reqMethod = "GET";
        HttpURLConnection connection = createRequest(reqMethod, endPoint);
        int responseCode = connection.getResponseCode();
        String responseMsg = connection.getResponseMessage();
        assertEquals(204,responseCode);
        assertEquals("No Content",responseMsg);
    }

    /**
     * Test Case ID: 8
     *
     * Test description: check whether or not GET all is not accept any param.
     *
     * Given base url https://suchonsite-server.herokuapp.com
     * When GET with param /people/all/11-11-2021
     * Then status code is 404 Not Found
     *
     * @throws IOException
     */
    @Test
    public void testGetAllWithParam() throws IOException {
        String endPoint = base + "/people/all/11-11-2021";
        String reqMethod = "GET";
        HttpURLConnection connection = createRequest(reqMethod, endPoint);
        int responseCode = connection.getResponseCode();
        String responseMsg = connection.getResponseMessage();
        assertEquals(404,responseCode);
        assertEquals("Not Found",responseMsg);
    }

    /**
     * Test Case ID: 9
     *
     * Test description: check whether or not /getDataFromGov return 404 Not Found.
     *
     * Given base url https://suchonsite-server.herokuapp.com
     * When POST with param /getDataFromGov
     * Then status code is 404 Not Found
     *
     * @throws IOException
     */
    @Test
    public void testGetDataFromGovWithNoParams() throws IOException {
        String endPoint = base + "/getDataFromGov";
        String reqMethod = "POST";
        HttpURLConnection connection = createRequest(reqMethod, endPoint);
        int responseCode = connection.getResponseCode();
        String responseMsg = connection.getResponseMessage();
        assertEquals(404,responseCode);
        assertEquals("Not Found",responseMsg);
    }

    /**
     * Test Case ID: 10
     *
     * Test description: check whether or not /getDataFromGov/11-11-2021 return 401
     * about reservation on 11-11-2021 stored in our database.
     *
     * Given base url https://suchonsite-server.herokuapp.com
     * When POST with param /getDataFromGov/11-21-2021
     * Then status code is 401.
     *
     * @throws IOException
     */
    @Test
    public void testGetDataFromGovWithExistDate() throws IOException {
        String endPoint = base + "/getDataFromGov/11-11-2021";
        String reqMethod = "POST";
        HttpURLConnection connection = createRequest(reqMethod, endPoint);
        int responseCode = connection.getResponseCode();
        String responseMsg = connection.getResponseMessage();
        assertEquals(401,responseCode);
    }

    // ----------------------- Media type Testing ------------------------ //
    /**
     * Test Case ID: 12
     *
     * Test description: check whether or not GET all return JSON.
     *
     * Given base url https://suchonsite-server.herokuapp.com
     * When GET with param /people/all
     * Then receive JSON
     *
     * @throws IOException
     */
    @Test
    public void testMediaFormat() throws IOException {
        String endPoint = base + "/people/all";
        String reqMethod = "GET";
        HttpURLConnection connection = createRequest(reqMethod, endPoint);
        assertEquals("application/json; charset=utf-8", connection.getContentType());
    }

    // ----------------------- JSON content Testing ------------------------ //
    /**
     * Test Case ID: 13
     *
     * Test description: check whether or not GET all return array that contain JSON objects inside.
     *
     * Given base url https://suchonsite-server.herokuapp.com
     * When GET with param /people/all
     * Then receive array that contain JSON objects inside
     *
     * @throws IOException
     * @throws ParseException
     */
    @Test
    public void testCheckGetAll() throws IOException, ParseException {
        String endPoint = base + "/people/all";
        String reqMethod = "GET";

        HttpURLConnection connection = createRequest(reqMethod, endPoint);
        InputStream inputStream = connection.getInputStream();

        // inputStream can cast to JSONArray without any error
        JSONArray arrayPayload = InputStreamToJSONArray(inputStream);
        assertEquals(arrayPayload.getClass(), JSONArray.class);
        assertEquals(arrayPayload.get(0).getClass(), JSONObject.class);
    }

    /**
     * Test Case ID: 14
     *
     * Test description: check whether or not GET by_date return just 1 JSON object.
     *
     * Given base url https://suchonsite-server.herokuapp.com
     * When GET with param /people/by_date/11-11-2021
     * Then receive only 1 JSON object
     *
     * @throws IOException
     * @throws ParseException
     */
    @Test
    public void testCheckGetByDate() throws IOException, ParseException {
        String endPoint = base + "/people/by_date/11-11-2021";
        String reqMethod = "GET";

        HttpURLConnection connection = createRequest(reqMethod, endPoint);
        InputStream inputStream = connection.getInputStream();

        // inputStream can cast to JSONObject without any error
        JSONObject payload = InputStreamToJSON(inputStream);
        assertEquals(payload.getClass(), JSONObject.class);
    }

    /**
     * Test Case ID: 15
     *
     * Test description: check whether or not GET by_date only return information of that particular date.
     *
     * Given base url https://suchonsite-server.herokuapp.com
     * When GET with param /people/by_date/11-11-2021
     * Then receive JSON object which key date has value "11-11-2021"
     *
     * @throws IOException
     * @throws ParseException
     */
    @Test
    public void testCheckDateOfJsonObject() throws IOException, ParseException {
        String endPoint = base + "/people/by_date/11-11-2021";
        String reqMethod = "GET";

        HttpURLConnection connection = createRequest(reqMethod, endPoint);
        InputStream inputStream = connection.getInputStream();
        JSONObject payload = InputStreamToJSON(inputStream);
        assertEquals("11-11-2021", payload.get("date"));
    }

    /**
     * Test Case ID: 16
     *
     * Test description: check whether or not JSON object of each user contain all important key.
     *
     * Given base url https://suchonsite-server.herokuapp.com
     * When GET with param /people/by_date/11-11-2021
     * Then receive JSON which have list of user, and each user have at least
     * reservation_id, timestamp, name, surname, birthdate, citizen_id, occupation, address
     *
     * @throws IOException
     * @throws ParseException
     */
    @Test
    public void testCheckImportantKeyOfJsonObject() throws IOException, ParseException {
        String endPoint = base + "/people/by_date/11-11-2021";
        String reqMethod = "GET";

        HttpURLConnection connection = createRequest(reqMethod, endPoint);
        InputStream inputStream = connection.getInputStream();
        JSONObject payload = InputStreamToJSON(inputStream);
        JSONArray people = (JSONArray) payload.get("people");
        JSONObject firstPeople = (JSONObject) people.get(0);

        assertEquals(firstPeople.get("reservation_id").getClass(), Long.class);
        assertEquals(firstPeople.get("register_timestamp").getClass(), String.class);
        assertEquals(firstPeople.get("name").getClass(), String.class);
        assertEquals(firstPeople.get("surname").getClass(), String.class);
        assertEquals(firstPeople.get("birth_date").getClass(), String.class);
        assertEquals(firstPeople.get("citizen_id").getClass(), String.class);
        assertEquals(firstPeople.get("occupation").getClass(), String.class);
        assertEquals(firstPeople.get("address").getClass(), String.class);
    }
}
