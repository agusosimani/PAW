package ar.edu.itba.paw2019a2.webapp.controller;
import ar.edu.itba.paw2019a2.interfaces.service.*;
import ar.edu.itba.paw2019a2.model.*;
import ar.edu.itba.paw2019a2.model.Enum.*;
import ar.edu.itba.paw2019a2.webapp.auth.PawUserDetails;
import ar.edu.itba.paw2019a2.webapp.form.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserService userService;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AuthenticationService authenticationService;


    private String fromDefaultDate = "01/05/2019";


    private int getCurrentUserID() {
        if (authenticationService.getAuthentication() == null || !authenticationService.getAuthentication().isAuthenticated()
                || authenticationService.getAuthentication() instanceof AnonymousAuthenticationToken)
            return -1;
        else {
            return ((PawUserDetails) authenticationService.getAuthentication().getPrincipal()).getId();
        }
    }

    private boolean isAdmin() {
        return authenticationService.getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    @RequestMapping(value = "/add_ingredient_user", method = {RequestMethod.POST})
    public ModelAndView addIngredientUser(@Valid @ModelAttribute("recipeForm") final AddIngredientForm addIngredientForm, final BindingResult errors) {

        List<RecipeIngredient> ingredientsList = addIngredientForm.getIngredients();

        for (RecipeIngredient ri : ingredientsList) {
            ingredientService.addNewUserIngredient(ri, getCurrentUserID());
        }
        return new ModelAndView("redirect:/my_ingredients");
    }

    @RequestMapping(value = "/delete_ingredient", method = RequestMethod.POST) //Le digo que url mappeo
    public ModelAndView deleteIngredient(@RequestParam int ingredientId) {
        Optional<RecipeIngredient> maybeRI = ingredientService.getByIngredientUserId(ingredientId,getCurrentUserID());
        if(!maybeRI.isPresent()){
            return new ModelAndView("redirect:/403");
        }
        ingredientService.deleteUI(ingredientId, getCurrentUserID());
        return new ModelAndView("redirect:/my_ingredients");
    }



    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(required = false) String error) {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/my_account", method = RequestMethod.GET)
    public ModelAndView myAccount(Authentication authentication) {
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("userId", getCurrentUserID());
        return new ModelAndView("redirect:/account", arguments);
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public ModelAndView account(@RequestParam int userId) {
        final ModelAndView mav = new ModelAndView("account");

        Either<User, Warnings> eitherUser = userService.getById(userId);
        if (eitherUser.isValuePresent()) {
            mav.addObject("user", eitherUser.getValue());
            try{
                mapUserParams(mav,eitherUser.getValue());
            }
            catch (Exception e){
                return new ModelAndView("404");
            }
        } else {
            return new ModelAndView("404");
        }

        mav.addObject("averageRate",userService.getRelativeRatingFromUser(userId));
        mav.addObject("recipes_amount", recipeService.userRecipesNumber(userId));
        mav.addObject("yourAccount", getCurrentUserID() == userId);
        return mav;
    }

    private Date parseStringToDate(String date){
        DateTimeFormatter fromPattern = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        DateTimeFormatter toPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return Date.valueOf(LocalDate.parse(date, fromPattern).format(toPattern));
    }

    private String getToday() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate localDate = LocalDate.now();
        return dtf.format(localDate);
    }

    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public ModelAndView statistics(@Valid @ModelAttribute("dateForm") final DateForm dateForm, final BindingResult errors) {
        final ModelAndView mav = new ModelAndView("statistics");

        if(dateForm.getFrom() == null){
            dateForm.setFrom(fromDefaultDate);
        }
        if(dateForm.getTo() == null){
            dateForm.setTo(getToday());
        }

        Date from = parseStringToDate(dateForm.getFrom());
        Date to = parseStringToDate(dateForm.getTo());

        Either<User,Warnings> user = userService.getById(getCurrentUserID());
        try {mapUserParams(mav,user.getValue());}
        catch (Exception e){

        }

        Gson g = new Gson();

        mav.addObject("donutList", g.toJson(recipeService.tagStatistics(getCurrentUserID(),from, to)));
        mav.addObject("list", g.toJson(recipeService.nutritionStatistics(getCurrentUserID(),from,to)));
        return mav;
    }

    @RequestMapping(value = "/my_ingredients", method = RequestMethod.GET)
    public ModelAndView myIngredients(@ModelAttribute("addIngredientForm") final AddIngredientForm addIngredientForm) {
        final ModelAndView mav = new ModelAndView("ingredients");

        int id = getCurrentUserID();
        Either<User, Warnings> eitherUser = userService.getById(id);
        if (eitherUser.isValuePresent())
            mav.addObject("user", eitherUser.getValue());
        else {
            return new ModelAndView("404");
        }

        List<Ingredient> allIngredients = ingredientService.getAllIngredients();

        mav.addObject("averageRate",userService.getRelativeRatingFromUser(getCurrentUserID()));
        mav.addObject("recipes_amount", recipeService.getAllRecipesByUserId(getCurrentUserID()).size());
        mav.addObject("allIngredients", allIngredients);
        mav.addObject("ingredientsList", ingredientService.findByUser(id));
        return mav;
    }

    @RequestMapping(value = "/ban_user", method = {RequestMethod.POST})
    public ModelAndView banUser(@RequestParam int userId) {

        Either<User,Warnings> user = userService.getById(getCurrentUserID());
        if(!user.isValuePresent()){
            return new ModelAndView("redirect:/404");
        }
        if(user.getValue().isAdmin()){
            userService.deleteAccount(userId);
        }
        else{
            return new ModelAndView("redirect:/403");
        }

        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register(@ModelAttribute("registerForm") final RegisterForm form) {
        if (getCurrentUserID() != -1)
            return new ModelAndView("redirect:/");

        return new ModelAndView("register");
    }

    @RequestMapping(value = "/create", method = {RequestMethod.POST})
    public ModelAndView create(@Valid @ModelAttribute("registerForm") final RegisterForm registerForm, final BindingResult errors, WebRequest request) {
        if (errors.hasErrors()) {
            return register(registerForm);
        }

        String hashedPassword = passwordEncoder.encode(registerForm.getPassword());
        final Either<User, Warnings> eitherUser = userService.signUpUser(new User.Builder(registerForm.getUsername(), hashedPassword, registerForm.getEmail())
                .name(registerForm.getName()).surname(registerForm.getSurname()).build());
        if(eitherUser.isValuePresent()) {
            ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequestUri();
            builder.scheme("http");
            URI uri = builder.build().toUri();
            Warnings emailWarnings = emailService.sendMailConfirmationEmail(eitherUser.getValue(), uri.toString());
            if (emailWarnings != Warnings.valueOf("EmailError"))
                return new ModelAndView("redirect:/login");
        } else {
            return new ModelAndView("redirect:/403");
        }
        return new ModelAndView("redirect:/register");
    }

    @RequestMapping("/logout")
    public ModelAndView logout() {
        final ModelAndView mav = new ModelAndView("logout");
        mav.addObject("greeting", "PAW");
        return mav;
    }

    private void mapUserParams(final ModelAndView mav, User user) throws Exception {
        Either<User,Warnings> eitherUser = userService.getById(getCurrentUserID());
        if(!eitherUser.isValuePresent())
            throw new Exception("Error while loading user");
        if(eitherUser.getValue().isAdmin() && user.getId() != getCurrentUserID())
            mav.addObject("isAdmin", true);
        else
            mav.addObject("isAdmin", false);
        mav.addObject("user", user);
        mav.addObject("recipes_amount", recipeService.userRecipesNumber(user.getId()));
        mav.addObject("averageRate",userService.getRelativeRatingFromUser(user.getId()));

    }


}