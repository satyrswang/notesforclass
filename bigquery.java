understanding the logic of the pieces of code 

  step1  current project ID  :String PROJECT_NUMBER = "XXXXXXXXXX";

  2:   Load Client ID/secret from client_secrets.json file. （CLIENTSECRETS_LOCATION and the GoogleClientSecrets）
  3:   Objects for handling HTTP transport and JSON formatting of API calls
  private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
  private static final JsonFactory JSON_FACTORY = new JacksonFactory();
  4:  GoogleAuthorizationCodeFlow(what is a flow?
    itis an authorization flow and stored as a static class attribute(and the HttpTransport/jsonFactory/GoogleClientSecrets and other configs tobe the arguments ))
    Bigquery （bigqueryclient, returns by a method buildsevice）
  
  5 :main:

    step 1 :Load existing Refresh Token
    String storedRefreshToken = loadRefreshToken();
2:2.1 
    if loaded sucessfully: then 2.1.2 create a credential and 2.1.2 get a new access token--(what is the ifference between the loaded token and the new access token? 
      you can use a refresh token to get an access token )
     GoogleCredential credential = createCredentialWithRefreshToken(HTTP_TRANSPORT, JSON_FACTORY, new TokenResponse().setRefreshToken(storedRefreshToken));
     credential.refreshToken();//to get a new access token
     bigquery = buildService(credential);//return a client , and the buildservice method is implemented by us

    2.2 if failed--no token, then start authorization flow:
    2.2.1 create a url:
    String authorizeUrl = new GoogleAuthorizationCodeRequestUrl(clientSecrets,REDIRECT_URI,Collections.singleton(BigqueryScopes.BIGQUERY)).setState("").build();
    2.2.2 read the System.in to string 
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      String authorizationCode = in.readLine();
    2.2.3 exchange the auth code for an access token and refesh token
      Credential credential = exchangeCode(authorizationCode);
      // use url request and response to exchange

    2.2.4 store the refresh token for future use.(load and store just about the properties file,read and write)
      storeRefreshToken(credential.getRefreshToken());
      bigquery = buildService(credential);// the same,return a client  Builds an authorized BigQuery API client

3: make API calls using your client.
    listDatasets(bigquery, PROJECT_NUMBER);
