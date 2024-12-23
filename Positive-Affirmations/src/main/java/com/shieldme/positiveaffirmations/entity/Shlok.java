package com.shieldme.positiveaffirmations.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "shloks")
public class Shlok {

    @Id
    private String id;
    private String sanskritAffirmation;
    private String englishAffirmation;
    private String hindiMeaning;
    private String englishMeaning;

    public Shlok() {
    }

    public Shlok(String id, String sanskritAffirmation, String englishAffirmation, String hindiMeaning, String englishMeaning) {
        this.id = id;
        this.sanskritAffirmation = sanskritAffirmation;
        this.englishAffirmation = englishAffirmation;
        this.hindiMeaning = hindiMeaning;
        this.englishMeaning = englishMeaning;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSanskritAffirmation() {
        return sanskritAffirmation;
    }

    public void setSanskritAffirmation(String sanskritAffirmation) {
        this.sanskritAffirmation = sanskritAffirmation;
    }

    public String getEnglishAffirmation() {
        return englishAffirmation;
    }

    public void setEnglishAffirmation(String englishAffirmation) {
        this.englishAffirmation = englishAffirmation;
    }

    public String getHindiMeaning() {
        return hindiMeaning;
    }

    public void setHindiMeaning(String hindiMeaning) {
        this.hindiMeaning = hindiMeaning;
    }

    public String getEnglishMeaning() {
        return englishMeaning;
    }

    public void setEnglishMeaning(String englishMeaning) {
        this.englishMeaning = englishMeaning;
    }

    @Override
    public String toString() {
        return "Shlok{" +
                "id='" + id + '\'' +
                ", sanskritAffirmation='" + sanskritAffirmation + '\'' +
                ", englishAffirmation='" + englishAffirmation + '\'' +
                ", hindiMeaning='" + hindiMeaning + '\'' +
                ", englishMeaning='" + englishMeaning + '\'' +
                '}';
    }
}
