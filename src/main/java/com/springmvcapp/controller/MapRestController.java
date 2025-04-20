package com.springmvcapp.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class MapRestController {

  RestTemplate restTemplate = new RestTemplate();
  @GetMapping("/location")
  public ResponseEntity<?> getLocation(@RequestParam String address) {
    try {
      String encodedAddress = URLEncoder.encode(address, StandardCharsets.UTF_8);
      String url = "https://nominatim.openstreetmap.org/search?q=" + encodedAddress + "&format=json&limit=1";

      HttpHeaders headers = new HttpHeaders();
      headers.set("User-Agent", "MotelApp/1.0 (your_email@example.com)"); // Bắt buộc!

      HttpEntity<String> entity = new HttpEntity<>(headers);

      RestTemplate restTemplate = new RestTemplate();
      ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

      // Parse JSON trả về
      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode root = objectMapper.readTree(response.getBody());

      if (root.isArray() && root.size() > 0) {
        JsonNode location = root.get(0);
        Map<String, String> result = new HashMap<>();
        result.put("lat", location.get("lat").asText());
        result.put("lng", location.get("lon").asText());
        return ResponseEntity.ok(result);
      } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy vị trí.");
      }

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Lỗi khi gọi API: " + e.getMessage());
    }
  }

  @GetMapping("/search")
  public ResponseEntity<?> searchLocation(
      @RequestParam String query,
      @RequestParam(defaultValue = "1") int limit) {

    String url = "https://nominatim.openstreetmap.org/search?q={query}&format=json&limit={limit}";

    HttpHeaders headers = new HttpHeaders();
    headers.set("User-Agent", "Mozilla/5.0"); // Bắt buộc với nominatim

    HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

    ResponseEntity<String> response = restTemplate.exchange(
        url,
        HttpMethod.GET,
        requestEntity,
        String.class,
        Map.of("query", query, "limit", limit)
    );

    return ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(response.getBody());
  }
}

