package ar.edu.itba.paw2019a2.service;

import ar.edu.itba.paw2019a2.interfaces.service.EmailService;
import ar.edu.itba.paw2019a2.interfaces.service.UserService;
import ar.edu.itba.paw2019a2.model.Either;
import ar.edu.itba.paw2019a2.model.Enum.Warnings;
import ar.edu.itba.paw2019a2.model.User;
import ar.edu.itba.paw2019a2.model.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserService userService;

    @Override
    public Warnings sendSimpleEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        return sendEmail(to, message);
    }

    @Override
    public Warnings sendMimeEmail(String to, String subject, String text) {
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper;
            helper = new MimeMessageHelper(message,true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);
        } catch (MessagingException e) {
            return Warnings.valueOf("EmailError");
        }
        return sendEmail(to, message);
    }

    private Warnings sendEmail(String to, Object  message) {
        try {
            if(message instanceof MimeMessage) {
                emailSender.send((MimeMessage) message);
            } else if (message instanceof SimpleMailMessage){
                emailSender.send((SimpleMailMessage) message);
            } else {
                System.out.println("Wrong message type");
            }
            System.out.println("sending email to: " + to);
        } catch (Exception ex){
            System.out.println(ex.getCause());
            System.out.println(ex.getMessage());
            System.out.println("failed sending email to: " + to);
            return Warnings.valueOf("EmailError");
        }
        return Warnings.valueOf("Success");
    }

    @Override
    public Warnings sendMailConfirmationEmail(User user, String appUrl) {
        Either<VerificationToken, Warnings> verificationToken = userService.createVerificationToken(user);
        if(!verificationToken.isValuePresent()) {
            return verificationToken.getAlternative();
        }
        String confirmUrl = appUrl + "/registration-confirm?token=" + verificationToken.getValue().getToken();
        return sendMimeEmail(user.getEmail(), messageSource.getMessage("mailConfirmation.Subject",null, LocaleContextHolder.getLocale()), mailConfirmationEmailBody(user, confirmUrl));
    }


    @Override
    public Warnings resendMailConfirmationEmail(String existingToken, String registrationConfirmUrl) {
        Either<VerificationToken, Warnings> newVerificationToken = userService.createNewVerificationToken(existingToken);
        if(!newVerificationToken.isValuePresent()) {
            return newVerificationToken.getAlternative();
        }
        Either<User, Warnings> user = userService.getById(newVerificationToken.getValue().getUserID());
        if(!user.isValuePresent()) {
            return user.getAlternative();
        }
        String confirmUrl = registrationConfirmUrl + "?token=" + newVerificationToken.getValue().getToken();
        return  sendMimeEmail(user.getValue().getEmail(), messageSource.getMessage("mailConfirmation.Subject",null, LocaleContextHolder.getLocale()), mailConfirmationEmailBody(user.getValue(), confirmUrl));
    }

    @Override
    public Warnings sendResetPasswordEmail(User user, String appUrl)  {
        Either<VerificationToken, Warnings> verificationToken = userService.createVerificationToken(user);
        if(!verificationToken.isValuePresent()) {
            return verificationToken.getAlternative();
        }
        String resetUrl = appUrl + "/reset-password/validate?id=" + user.getId() + "&token=" + verificationToken.getValue().getToken();
        return sendMimeEmail(user.getEmail(), messageSource.getMessage("resetPassword.Subject",null, LocaleContextHolder.getLocale()), resetPasswordEmailBody(resetUrl));
    }

    private String mailConfirmationEmailBody(User user, String confirmUrl) {
        return messageSource.getMessage("mailConfirmation.Body", null, LocaleContextHolder.getLocale()) +
                "\n" +
                confirmUrl;
    }

    private String resetPasswordEmailBody(String resetUrl) {
        return messageSource.getMessage("resetPassword.Body", null, LocaleContextHolder.getLocale()) +
                ' ' + resetUrl;
    }
}
