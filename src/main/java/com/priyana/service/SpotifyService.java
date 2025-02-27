package com.priyana.service;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.Base64;
import java.util.Map;

@Service
public class SpotifyService {

    private final Dotenv dotenv = Dotenv.load(); // Load environment variables

    private final String clientId = dotenv.get("SPOTIFY_CLIENT_ID");
    private final String clientSecret = dotenv.get("SPOTIFY_CLIENT_SECRET");
    private final String tokenUrl = dotenv.get("SPOTIFY_API_TOKEN_URL");
    private final String searchUrl = dotenv.get("SPOTIFY_API_SEARCH_URL");
    private final String trackDetailsUrl = "https://api.spotify.com/v1/tracks/";

    private String accessToken;
    private final RestTemplate restTemplate = new RestTemplate();

    // Fetch Access Token
    private void fetchAccessToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String credentials = clientId + ":" + clientSecret;
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
        headers.set("Authorization", "Basic " + encodedCredentials);

        HttpEntity<String> request = new HttpEntity<>("grant_type=client_credentials", headers);
        ResponseEntity<Map> response = restTemplate.exchange(tokenUrl, HttpMethod.POST, request, Map.class);

        accessToken = response.getBody().get("access_token").toString();
    }

    // Search Songs
    public Map<String, Object> searchSongs(String query, int limit) {
        if (accessToken == null) {
            fetchAccessToken();
        }

        String url = searchUrl + "?q=" + query + "&type=track&limit=" + limit;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);

        return response.getBody();
    }

    public Map<String, Object> getSongDetails(String spotifyId) {
        if (accessToken == null) {
            fetchAccessToken();
        }

        String url = trackDetailsUrl + spotifyId;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);

        return response.getBody();
    }
}
