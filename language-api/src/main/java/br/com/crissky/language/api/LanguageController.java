package br.com.crissky.language.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LanguageController {
    @Autowired
    private LanguageRepository repository;

    @GetMapping(value = "/languages")
    public List<Language> getLanguageList() {
        List<Language> languageList = repository.findAll();
        return languageList;
    }

    @PostMapping(value = "/languages")
    public Language saveLanguage(@RequestBody Language language) {
        Language savedLanguage = repository.save(language);
        return savedLanguage;
    }
}
