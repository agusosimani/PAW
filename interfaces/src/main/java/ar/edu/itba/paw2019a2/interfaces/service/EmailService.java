package ar.edu.itba.paw2019a2.interfaces.service;

import ar.edu.itba.paw2019a2.model.Enum.Warnings;
import ar.edu.itba.paw2019a2.model.User;

public interface EmailService {
    Warnings sendSimpleEmail(String to, String subject, String body);
    Warnings sendMailConfirmationEmail(User user, String appUrl);
    Warnings sendResetPasswordEmail(User user, String appUrl);
    Warnings resendMailConfirmationEmail(String existingToken, String registrationConfirmUrl);
    Warnings sendMimeEmail(String to, String subject, String text);
}
