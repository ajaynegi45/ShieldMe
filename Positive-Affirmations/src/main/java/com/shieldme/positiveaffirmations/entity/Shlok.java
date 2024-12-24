package com.shieldme.positiveaffirmations.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "shloks")
public class Shlok {

    @Id
    private String id;
    private String sanskritShlok;
    private String englishShlok;
    private String hindiMeaning;
    private String englishMeaning;

    public Shlok() {
    }

    public Shlok(String id, String sanskritShlok, String englishShlok, String hindiMeaning, String englishMeaning) {
        this.id = id;
        this.sanskritShlok = sanskritShlok;
        this.englishShlok = englishShlok;
        this.hindiMeaning = hindiMeaning;
        this.englishMeaning = englishMeaning;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSanskritShlok() {
        return sanskritShlok;
    }

    public void setSanskritShlok(String sanskritShlok) {
        this.sanskritShlok = sanskritShlok;
    }

    public String getEnglishShlok() {
        return englishShlok;
    }

    public void setEnglishShlok(String englishShlok) {
        this.englishShlok = englishShlok;
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
                ", sanskritShlok='" + sanskritShlok + '\'' +
                ", englishShlok='" + englishShlok + '\'' +
                ", hindiMeaning='" + hindiMeaning + '\'' +
                ", englishMeaning='" + englishMeaning + '\'' +
                '}';
    }
}
