package com.springmvcapp.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springmvcapp.dto.LocationResultDTO;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
@Service
public class GoogleService {
  private static final String API_KEY = "AIzaSyAg766i1pE6KWbV2ADunBLk4QxT2Ebm7hM";

  public LocationResultDTO searchAddress(String address) throws Exception {
    String encodedAddress = java.net.URLEncoder.encode(address, java.nio.charset.StandardCharsets.UTF_8);
    String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + encodedAddress + "&key=" + API_KEY;

    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .GET()
        .build();

    HttpClient client = HttpClient.newHttpClient();
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    ObjectMapper mapper = new ObjectMapper();
    JsonNode root = mapper.readTree(response.body());

    if (!"OK".equals(root.path("status").asText())) {
      throw new RuntimeException("Không tìm thấy địa chỉ.");
    }

    JsonNode result = root.path("results").get(0);
    String formatted = result.path("formatted_address").asText();
    double lat = result.path("geometry").path("location").path("lat").asDouble();
    double lng = result.path("geometry").path("location").path("lng").asDouble();

    LocationResultDTO location = new LocationResultDTO();
    location.setFormattedAddress(formatted);
    location.setLat(lat);
    location.setLng(lng);
    return location;
  }
}
