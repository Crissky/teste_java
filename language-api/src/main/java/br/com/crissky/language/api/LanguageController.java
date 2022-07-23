package br.com.crissky.language.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LanguageController {
    private List<Language> languageList = List.of(
            new Language(
                    "Java",
                    "https://raw.githubusercontent.com/abrahamcalf/programming-languages-logos/master/src/java/java_256x256.png",
                    1),
            new Language(
                    "PHP",
                    "https://raw.githubusercontent.com/abrahamcalf/programming-languages-logos/master/src/php/php_256x256.png",
                    2));

    @GetMapping(value = "/preferred-language")
    public String processingPreferredLanguage() {
        return "Oi, Java!!!";
    }

}