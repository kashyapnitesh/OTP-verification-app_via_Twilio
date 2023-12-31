package com.otpverification.otpapp.service;




import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.util.Random;


@Service
public class OtpService {


        @Value("${twilio.account.sid}")
        private String accountSid;

        @Value("${twilio.auth.token}")
        private String authToken;

    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;


//    private final TwilioRestClient twilioRestClient;



//    public OtpService(@Value("${twilio.account-sid") String accountSid,
//                         @Value("${twilio.auth-token") String authToken){
//           this.twilioRestClient = new TwilioRestClient.Builder(accountSid, authToken).build();
//            }

    public String generateOtp(String to) {
        int otp = new Random().nextInt(900000) + 100000;
        sendOtpSms(to, String.valueOf(otp));
        return String.valueOf(otp);
    }

    public void sendOtpSms(String to, String otp) {
        Message message = Message.creator(new PhoneNumber(to), new PhoneNumber(twilioPhoneNumber), "Your OTP is:" + otp).create();
    }



}
