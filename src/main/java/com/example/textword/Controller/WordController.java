package com.example.textword.Controller;

import com.example.textword.service.AIService;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.Map;

@RestController
@RequestMapping("/api/word")
public class WordController {

    private final AIService aiService;

    public WordController(AIService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/generate")
    public ResponseEntity<?> generateWord(@RequestBody Map<String, String> body) throws IOException {
        String content = body.get("text");

        // Validation de l'entrée
        if (content == null || content.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Le texte ne peut pas être vide");
        }

        // Appelle le service IA pour améliorer le texte
        String improvedText = aiService.improveText(content);

        // Création du document Word
        XWPFDocument document = new XWPFDocument();
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(improvedText);

        // Sauvegarde dans un flux de sortie
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        document.write(out);
        document.close();

        // Préparer la réponse HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(ContentDisposition.attachment().filename("document.docx").build());

        return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);
    }
}