package com.shieldme.authentication.dto;


import lombok.Builder;
import lombok.Getter;


@Getter
@Builder



public class MailBody {
    private String to;
    private String text;
    private String subject;

    // Getters and setters
    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    // Custom Builder
    public static Builder builder() {
        return new Builder();
    }

    public String to() {
        return to;
    }

    public String subject() {
        return subject;
    }

    public String text() {
        return text;
    }

    public static class Builder {
        private String to;
        private String text;
        private String subject;

        public Builder to(String to) {
            this.to = to;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public MailBody build() {
            MailBody mailBody = new MailBody();
            mailBody.setTo(to);
            mailBody.setText(text);
            mailBody.setSubject(subject);
            return mailBody;
        }
    }
}




