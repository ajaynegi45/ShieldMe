package com.shieldme.positiveaffirmations.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "shloks")
public class Shlok {

    @Id
    private String shlokId;
    private String sanskritShlok;
    private String englishShlok;
    private String hindiMeaning;
    private String englishMeaning;

    public Shlok() {
    }

    public Shlok(String shlokId, String sanskritShlok, String englishShlok, String hindiMeaning, String englishMeaning) {
        this.shlokId = shlokId;
        this.sanskritShlok = sanskritShlok;
        this.englishShlok = englishShlok;
        this.hindiMeaning = hindiMeaning;
        this.englishMeaning = englishMeaning;
    }

    public String getShlokId() {
        return shlokId;
    }

    public void setShlokId(String shlokId) {
        this.shlokId = shlokId;
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
}