package com.adarsh.RealQuizzApp.controller;


import com.adarsh.RealQuizzApp.repo.ProctoringRequest;
import com.adarsh.RealQuizzApp.util.MultipartInputStreamFileResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
//@RequestMapping("leaderboard")
@CrossOrigin(origins = "http://quizly-by-adarsh.netlify.app")
public class ProctorController {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String PYTHON_API_URL = "http://localhost:8000/detect-cheating";

    private Map<String, Integer> warningMap = new ConcurrentHashMap<>();

    @PostMapping(value = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, Object>> handleImage(
            @RequestParam("image") MultipartFile image,
            @RequestParam("quizId") String quizId)  throws IOException {



//        System.out.println("Image: " + image);
//        System.out.println("Quiz ID: " + quizId); // Image is received till here
        System.out.println("Sending image to Python API...");
        System.out.println("Image name: " + image.getOriginalFilename());
        System.out.println("Image size: " + image.getSize() + " bytes");

        // save image for proof
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
        String formattedDate = myDateObj.format(myFormatObj);

        Path filePath = Paths.get("received_image", quizId + "_" + formattedDate +"_" + System.currentTimeMillis() + ".jpg");
        Files.createDirectories(filePath.getParent());
        Files.write(filePath, image.getBytes());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new MultipartInputStreamFileResource(image.getInputStream(), image.getOriginalFilename()));

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(PYTHON_API_URL, requestEntity, Map.class);

        Map<String, Object> result = response.getBody();
        boolean cheating = (boolean) result.get("cheating_detected");
//
        Map<String, Object> finalResponse = new HashMap<>();
        finalResponse.put("warning", false);
        finalResponse.put("terminated", false);

        System.out.println("cheating:"+cheating);

        if (cheating) {
            int count = warningMap.getOrDefault(quizId, 0) + 1;
            warningMap.put(quizId, count);


//            System.out.println("After formatting: " + formattedDate);

            Path filePath2 = Paths.get("cheating_images", quizId + "_" + formattedDate +"_" + System.currentTimeMillis() + ".jpg");
            Files.createDirectories(filePath2.getParent());
            Files.write(filePath2, image.getBytes());

            if (true) {
                finalResponse.put("terminated", true);
            }
//            else {
//                finalResponse.put("warning", true);
//                finalResponse.put("reason", "Cheating Detected");
//            }
        }

        return ResponseEntity.ok(finalResponse);
    }



}
