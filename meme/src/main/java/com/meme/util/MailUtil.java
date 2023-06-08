package com.meme.util;

import java.util.Properties;

public class MailUtil {

    public static void main(String[] args) {
        Properties props = new Properties();

        props.setProperty("mail.host", "");
        props.setProperty("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "smtp.office365.com");
    }
}
