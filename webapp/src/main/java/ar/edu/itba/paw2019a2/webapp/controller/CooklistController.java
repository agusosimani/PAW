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
public class CooklistController {

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

    private int cardsPerPage = 999;

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


    @RequestMapping(value = "/cooklist", method = {RequestMethod.GET})
    public ModelAndView cooklist(@RequestParam int cookListId, @RequestParam int userId) {

        final ModelAndView mav = new ModelAndView("cooklist_recipes");


        Either<User, Warnings> eitherUser = userService.getById(userId);
        if (eitherUser.isValuePresent())
            mav.addObject("user", eitherUser.getValue());
        else {
            return new ModelAndView("404");
        }

        mav.addObject("cooklist", recipeService.getCookList(cookListId).getValue());
        mav.addObject("yourCooklist", userId == getCurrentUserID());
        mav.addObject("recipes_amount", recipeService.userRecipesNumber(userId));
        mav.addObject("averageRate",userService.getRelativeRatingFromUser(userId));

        return mav;
    }


    @RequestMapping(value = "/your_cooklists", method = {RequestMethod.GET})
    public ModelAndView yourCooklists(@Valid @ModelAttribute("newCookListForm") final NewCookListForm form) {
        return userCooklists(form, getCurrentUserID());
    }

    @RequestMapping(value = "/user_cooklists", method = {RequestMethod.GET})
    public ModelAndView userCooklists(@Valid @ModelAttribute("newCookListForm") final NewCookListForm form, @RequestParam int userId) {
        final ModelAndView mav = new ModelAndView("cooklists");

        if (userId == -1)
            userId = getCurrentUserID();

        Either<User,Warnings> eitherUser = userService.getById(userId);
        if(!eitherUser.isValuePresent()) {
            return new ModelAndView("404");
        }

        if (userId != getCurrentUserID())
            mav.addObject("title", messageSource.getMessage("cooklist.title", new Object[]{eitherUser.getValue().getName()}, Locale.getDefault()));
        else
            mav.addObject("title", messageSource.getMessage("myCooklists", null, Locale.getDefault()));

        mav.addObject("averageRate",userService.getRelativeRatingFromUser(userId));
        mav.addObject("recipes_amount", recipeService.getAllRecipesByUserId(userId).size());
        mav.addObject("editable", getCurrentUserID() == userId);
        mav.addObject("cookList", recipeService.getUserCookLists(userId));
        mav.addObject("user", userService.getById(userId).getValue());
        return mav;
    }

    @RequestMapping(value = "/create_cooklist", method = {RequestMethod.POST})
    public ModelAndView createCookList(@Valid @ModelAttribute("newCookListForm") final NewCookListForm form, final BindingResult errors) {
        if (errors.hasErrors()) {
//            return newRecipe(form);
        }

        recipeService.addNewCookListWithoutIngredients(getCurrentUserID(), form.getName());

        Map<String, Object> arguments = new HashMap<>();
        arguments.put("form", form);
        arguments.put("userId", getCurrentUserID());
        return new ModelAndView("redirect:/user_cooklists", arguments);
    }

    @RequestMapping(value = "/delete_cooklist", method = RequestMethod.POST) //Le digo que url mappeo
    public ModelAndView deleteCooklist(@RequestParam int cooklistId) {
        Either<RecipeList, Warnings> eitherCooklist = recipeService.getCookList(cooklistId);

        if(eitherCooklist.isValuePresent())
            recipeService.deleteCookList(cooklistId,getCurrentUserID());
        return new ModelAndView("redirect:/your_cooklists");
    }

    @RequestMapping(value = "/add_recipe_to_cooklist", method = RequestMethod.POST) //Le digo que url mappeo
    public ModelAndView addRecipeToCooklist(@ModelAttribute("selectCookListForm") final SelectCookListForm form, final BindingResult errors, @RequestParam int recipeId) {
        if (errors.hasErrors()) {
            return new ModelAndView("400");
        }

        recipeService.addRecipeToCookList(form.getCooklistId(), recipeId);
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("recipeId", recipeId);
        return new ModelAndView("redirect:/recipe", arguments);
    }

}