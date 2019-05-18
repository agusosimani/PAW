package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.service.IngredientService;
import ar.edu.itba.paw.interfaces.service.RecipeService;
import ar.edu.itba.paw.model.*;
import ar.edu.itba.paw.model.Enum.Tag;
import ar.edu.itba.paw.webapp.auth.PawUserDetails;
import ar.edu.itba.paw.webapp.form.AddIngredientForm;
import ar.edu.itba.paw.webapp.form.RecipeForm;
import ar.edu.itba.paw.interfaces.service.UserService;
import ar.edu.itba.paw.webapp.form.RegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@Controller
public class HelloWorldController {

    @Autowired
    private UserService userService;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private int getCurrentUserID() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken)
            return -1;
        else {
            return ((PawUserDetails) authentication.getPrincipal()).getId();
        }
    }

    @RequestMapping("/") //Le digo que url mappeo
    public ModelAndView helloWorld(@ModelAttribute("recipeForm") final RecipeForm recipeForm) {
        final ModelAndView mav = new ModelAndView("index"); //Seleccionar lista

        //System.out.printf("%s",LocaleContextHolder.getLocale().getDisplayLanguage());

        List<Recipe> recipeList = recipeService.getRecipes();

        List<Ingredient> allIngredientsList = ingredientService.getAllIngredients();


        //TODO: se puede usar form:checkboxes con objetos?
        List<RecipeTag> allTagsList = recipeService.getAllTags();

        List<String> allTagsStringList = new ArrayList<>();
        for (RecipeTag tag : allTagsList) {
            allTagsStringList.add(tag.getTag());
        }


        for (Recipe in : recipeList) {
            if (in.getTags() != null && !in.getTags().isEmpty())
                System.out.printf("no es empty");
        }

        mav.addObject("allTags", Tag.values());
        mav.addObject("allIngredients", allIngredientsList);
        mav.addObject("RecipeList", recipeList); //Popular model
        return mav;
    }

    @RequestMapping(value = "/create_recipe", method = {RequestMethod.POST})
    public ModelAndView createRecipe(@Valid @ModelAttribute("recipeForm") final RecipeForm recipeForm, final BindingResult errors) {
        if (errors.hasErrors()) {
            return null;
        }

        List<RecipeIngredient> listIngredients = new ArrayList<>();

        List<RecipeTag> recipeTags = new ArrayList<>();


        for (String tagName : recipeForm.getTags()) {
            //recipeTags.add(new RecipeTag("Vegetariana",1));
        }

        byte[] bytes = null;
        try {
            //TODO: VALIDAR ESTO!
            bytes = recipeForm.getImage().getBytes();
        } catch (Exception e) {

        }

        listIngredients.add(new RecipeIngredient.Builder(ingredientService.getById(recipeForm.getIngredientOne()).get(), recipeForm.getIngredientOneAmount()).build());
        final Recipe recipeToAdd = new Recipe.Builder(0, recipeForm.getName(), listIngredients, recipeForm.getInstructions(), getCurrentUserID())
                .description(recipeForm.getDescription())
                .tags(recipeTags).image(bytes)
                .build();
        recipeService.addNewRecipe(recipeToAdd);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/add_ingredient_user", method = {RequestMethod.POST})
    public ModelAndView addIngredientUser(@Valid @ModelAttribute("recipeForm") final AddIngredientForm addIngredientForm, final BindingResult errors) {
        if (errors.hasErrors()) {
            return null;
        }

        System.out.printf("%d", addIngredientForm.getIngredientId());

        Ingredient aux = ingredientService.getById(addIngredientForm.getIngredientId()).get();

        RecipeIngredient ingredientToAdd = new RecipeIngredient.Builder(aux, addIngredientForm.getAmount()).observation("podrido").build();

        ingredientService.addNewUserIngredient(ingredientToAdd, getCurrentUserID());
        return new ModelAndView("redirect:/my_ingredients");
    }

    @RequestMapping(value = "/cook_recipe", method = {RequestMethod.POST})
    public ModelAndView cookRecipes(@RequestParam int recipeId) {


        List<RecipeIngredient> list = ingredientService.findByRecipe(recipeId);
        for (RecipeIngredient ri : list) {
            ingredientService.cookRecipe(ri, this.getCurrentUserID());
        }


        final ModelAndView mav = new ModelAndView("redirect:/recipe");
        mav.addObject("recipeId", recipeId);
        return mav;
    }


    @RequestMapping(value = "/delete_ingredient", method = RequestMethod.POST) //Le digo que url mappeo
    public ModelAndView deleteIngredient(@RequestParam int ingredientId) {
        ingredientService.deleteUI(ingredientId, getCurrentUserID());
        return new ModelAndView("redirect:/my_ingredients");
    }

    @RequestMapping(value = "/rate_recipe", method = RequestMethod.POST) //Le digo que url mappeo
    public ModelAndView rateRecipe(@RequestParam float rate, @RequestParam int recipeId) {
        //TODO: QUITAR (int)
        recipeService.addNewRating(getCurrentUserID(), recipeId ,(int)rate);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET) //Le digo que url mappeo
    public ModelAndView login() {
        final ModelAndView mav = new ModelAndView("login");
        return mav;
    }

    @RequestMapping(value = "/my_account", method = RequestMethod.GET)
    public ModelAndView myAccount(Authentication authentication) {
        final ModelAndView mav = new ModelAndView("my_account");

        mav.addObject("recipes_amount", recipeService.userRecipesNumber(getCurrentUserID()));

        mav.addObject("user", userService.getById(getCurrentUserID()).get());
        return mav;
    }

    @RequestMapping(value = "/my_ingredients", method = RequestMethod.GET)
    public ModelAndView myIngredients(Authentication authentication, @Valid @ModelAttribute("addIngredientForm") final AddIngredientForm addIngredientForm, final BindingResult errors) {
        final ModelAndView mav = new ModelAndView("ingredients");

        int id = getCurrentUserID();

        List<RecipeIngredient> ingredientList = ingredientService.findByUser(id);


        List<Ingredient> allIngredientsList = ingredientService.getAllIngredients();

        mav.addObject("recipes_amount", recipeService.getAllRecipesByUserId(getCurrentUserID()).size());
        mav.addObject("user", userService.getById(id).get());
        mav.addObject("allIngredients", allIngredientsList);
        mav.addObject("ingredientsList", ingredientList);
        return mav;
    }

	//CON EL ID LLAMO A SERVICES Y LA TRAIGO
	@RequestMapping(value = "/recipe", method = RequestMethod.GET)
	public ModelAndView recipe(@RequestParam Integer recipeId) {
		final ModelAndView mav = new ModelAndView("recipe");

        Recipe recipe = recipeService.getById(recipeId).get();

        //TODO: agarrar el rate previo de un usuario, si no hay poner 0.
        mav.addObject("previous_rate", 2);
        mav.addObject("recipes_amount", recipeService.userRecipesNumber(recipe.getUserId()));
        mav.addObject("recipe", recipe);
        mav.addObject("user", userService.getById(recipe.getUserId()).get());
        mav.addObject("ingredientsList", recipe.getIngredients());
        return mav;
    }


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register(@ModelAttribute("registerForm") final RegisterForm form) {
        final ModelAndView mav = new ModelAndView("register");
        return mav;
    }

    @RequestMapping(value = "/create", method = {RequestMethod.POST})
    public ModelAndView create(@Valid @ModelAttribute("registerForm") final RegisterForm form, final BindingResult errors) {
        if (errors.hasErrors()) {
            return register(form);
        }
        String hashedPassword = passwordEncoder.encode(form.getPassword());
        final User u = userService.signUpUser(new User.Builder(form.getUsername(), hashedPassword, form.getEmail())
                .name(form.getName()).surname(form.getSurname()).build());
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping("/logout")
    public ModelAndView logout() {
        final ModelAndView mav = new ModelAndView("logout");
        mav.addObject("greeting", "PAW");
        return mav;
    }


    @RequestMapping("/my_recipes") //Le digo que url mappeo
    public ModelAndView myRecipes() {
        return userRecipes(getCurrentUserID());
    }

    @RequestMapping("/user_recipes") //Le digo que url mappeo
    public ModelAndView userRecipes(@RequestParam int userId) {

        final ModelAndView mav = new ModelAndView("user_recipes");

        List<Recipe> recipeList = recipeService.getAllRecipesByUserId(userId);

        mav.addObject("RecipeList", recipeList);
        mav.addObject("user", userService.getById(userId).get());
        List<Recipe> rec = recipeService.getAllRecipesByUserId(userId);

        mav.addObject("recipes_amount", recipeService.userRecipesNumber(userId));


        return mav;
    }

    //No quiero repetirle todo el tiempo el path "WEB-INF/jsp/.." entonces configuro mi propio view resolver en web config

//	@ModelAttribute("user")
//	public Integer loggedUser(final HttpSession session)
//	{
//		return (Integer) session.getAttribute(LOGGED_USER_ID);
//	}
}