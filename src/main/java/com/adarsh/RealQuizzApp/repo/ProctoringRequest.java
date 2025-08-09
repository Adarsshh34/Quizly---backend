package com.adarsh.RealQuizzApp.repo;

import org.springframework.web.multipart.MultipartFile;

public class ProctoringRequest {
    private MultipartFile image;
    private String quizId;

    // Getters and Setters
    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }
}
