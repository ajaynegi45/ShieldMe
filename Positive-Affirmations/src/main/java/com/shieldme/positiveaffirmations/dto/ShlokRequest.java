package com.shieldme.positiveaffirmations.dto;

public record ShlokRequest(
        String sanskritShlok,
        String englishShlok,
        String hindiMeaning,
        String englishMeaning
) {
}
