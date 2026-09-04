package com.chatbot.service;

import java.io.IOException;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PdfService {

    private String pdfText = "";

    public void upload(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Please select a PDF file.");
        }

        try (var document = Loader.loadPDF(file.getBytes())) {
            pdfText = new PDFTextStripper().getText(document);
        }
    }

    public String getPdfText() {
        return pdfText;
    }

    public void clear() {
        pdfText = "";
    }
}