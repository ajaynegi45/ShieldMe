package com.shieldme.sosalerts.exception;

import java.util.Date;

public record ErrorDetails(Date timestamp, String message, String details) {
}
