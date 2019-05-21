package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.service.EmailService;
import ar.edu.itba.paw.interfaces.service.UserService;
import ar.edu.itba.paw.model.Either;
import ar.edu.itba.paw.model.Enum.Warnings;
import ar.edu.itba.paw.model.User;
import ar.edu.itba.paw.model.UserTokenState;
import ar.edu.itba.paw.model.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Controller
@Component
public class RegistrationController {
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/create/registration-confirm", method = RequestMethod.GET)
    public ModelAndView confirmRegistration(WebRequest request, Model model, @RequestParam("token") String token, HttpServletResponse response) {
        //Se busca en la DB al token pasado en la url
        Either<VerificationToken, Warnings> verificationToken = userService.getVerificationToken(token);
        if(!verificationToken.isValuePresent()) {
            System.out.println("inexistent token");
            return new ModelAndView("redirect:/error").addObject("message", verificationToken.getAlternative().getWarning());
        }

        Either<UserTokenState, Warnings> userTokenState = userService.getUserTokenState(verificationToken.getValue());
        if(!userTokenState.isValuePresent()) {
            System.out.println("inexistent user");
            return new ModelAndView("redirect:/error").addObject("message", messageSource.getMessage(verificationToken.getAlternative().name(), null, LocaleContextHolder.getLocale()));
        }
        if(userTokenState.getValue() == UserTokenState.USER_DISABLED_EXPIRED_TOKEN) {
            //resend email verification
            System.out.println("user disabled expired token");
            ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequestUri();
            builder.scheme("http");
            URI uri = builder.build().toUri();
            return new ModelAndView("indexResendEmailVerification").addObject("token", token).addObject("uri", uri);
        }
        if(userTokenState.getValue() == UserTokenState.USER_ENABLED_EXPIRED_TOKEN) {
            //El usuario no pude entrar directo a su cuenta usando el token
            System.out.println("user enabled expired token");
            return new ModelAndView("redirect:/login");
        }
        Either<User, Warnings> user = userService.getById(verificationToken.getValue().getUserID());
        if(userTokenState.getValue() == UserTokenState.USER_ENABLED_VALID_TOKEN) {
            System.out.println("user enabled, valid token. Puede ingresar directo con el token");
            authWithoutPassword(user.getValue());
            return new ModelAndView("redirect:/");
        }
        System.out.println("user disabled valid token");

        userService.setUserEnabledStatus(verificationToken.getValue().getUserID(), true);
        authWithoutPassword(user.getValue());
        return new ModelAndView("redirect:/");
    }

    private void authWithoutPassword(User user){
        List<GrantedAuthority> authorities = Arrays.asList( new SimpleGrantedAuthority("ROLE_USER"));
        Authentication auth = new UsernamePasswordAuthenticationToken(new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities) ,null, authorities);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @RequestMapping(value = "/create/resend-email-verification", method = RequestMethod.POST)
    public ModelAndView resendEmailVerification(HttpServletRequest request, @RequestParam("token") String existingToken, @RequestParam("uri") String uri) {
        Warnings emailValidation = emailService.resendMailConfirmationEmail(existingToken, uri);
        if(emailValidation == Warnings.EmailError) {
            System.out.println("email error");
            return new ModelAndView("redirect:/500");
        }
        return new ModelAndView("redirect:/login");
    }
}
