package com.dangkhoa.user.schedule;

import com.dangkhoa.user.model.User;
import com.dangkhoa.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.List;

@Component
public class SendMessage {

    public static final String API_KEY = "4D1C19DEA5E1EA813CCC19F84576D5";
    public static final String SECRET_KEY = "208ABF2DE9238B0C2D7AF133AE34EE";

    @Autowired
    UserRepository repository;

    @Scheduled(cron = "0 0 0 * * *")
    public void sendAllMessage() {
        List<User> users = repository.findByBirthday(LocalDate.now());
        users.forEach(user -> {
            try {
                sendMessage(user);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void sendMessage(User user) throws IOException {
        String message = "Chuc mung ban " + user.getName() + " da nhan 50tr tu TK 0969696969 ";
        String spec = "http://rest.esms.vn/MainService.svc/xml/SendMultipleMessage_V4_get?ApiKey=" + URLEncoder.encode(API_KEY, "UTF-8")
                + "&SecretKey=" + URLEncoder.encode(SECRET_KEY, "UTF-8")
                + "&SmsType=2&Brandname=QCAO_ONLINE&Phone=" + URLEncoder.encode(user.getPhone_number(), "UTF-8")
                + "&Content=" + URLEncoder.encode(message, "UTF-8");

        URL url = new URL(spec);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        System.out.println("Sending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        System.out.println("Send to " + user.getName());

        String response = getResponse(con);
        System.out.println(response);
    }

    private String getResponse(HttpURLConnection con) throws IOException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

}
