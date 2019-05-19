package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.service.IngredientService;
import ar.edu.itba.paw.interfaces.service.RecipeService;
import ar.edu.itba.paw.model.*;
import ar.edu.itba.paw.model.Enum.Tag;
import ar.edu.itba.paw.model.Enum.Warnings;
import ar.edu.itba.paw.webapp.auth.PawUserDetails;
import ar.edu.itba.paw.webapp.form.AddIngredientForm;
import ar.edu.itba.paw.webapp.form.CommentForm;
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

import java.util.*;

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
    public ModelAndView helloWorld() {
        final ModelAndView mav = new ModelAndView("index");

        //TODO: se puede usar form:checkboxes con objetos?

        mav.addObject("allTags", Tag.values());
        mav.addObject("RecipeList", recipeService.getRecipes());
        return mav;
    }

    @RequestMapping(value = "/new_recipe", method = {RequestMethod.GET})
    public ModelAndView newRecipe(@ModelAttribute("recipeForm") final RecipeForm recipeForm) {
        final ModelAndView mav = new ModelAndView("new_recipe");

        mav.addObject("allTags", Tag.values());
        mav.addObject("allIngredients", ingredientService.getAllIngredients());

        return mav;
    }

    @RequestMapping(value = "/create_recipe", method = {RequestMethod.POST})
    public ModelAndView createRecipe(@Valid @ModelAttribute("recipeForm") final RecipeForm recipeForm, final BindingResult errors) {
        if (errors.hasErrors()) {
            return newRecipe(recipeForm);
        }

        System.out.printf("LA LISTA TIENE UNOS: %d , primero %d", recipeForm.getIngredients().size(), recipeForm.getIngredients().get(0));
        System.out.printf("LA LISTA TIENE UNOS: %d , primero %d", recipeForm.getIngredientsAmount().size(), recipeForm.getIngredientsAmount().get(0));

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

        List<Integer> formIngredients = recipeForm.getIngredients();
        List<Integer> formIngredientsAmount = recipeForm.getIngredientsAmount();
        for(int i = 0; i < formIngredients.size(); i++) {
            listIngredients.add(new RecipeIngredient.Builder(ingredientService.getById(formIngredients.get(i)).get(), formIngredientsAmount.get(i)).build());
        }

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

        Ingredient aux = ingredientService.getById(addIngredientForm.getIngredientId()).get();

        RecipeIngredient ingredientToAdd = new RecipeIngredient.Builder(aux, addIngredientForm.getAmount()).observation("podrido").build();

        ingredientService.addNewUserIngredient(ingredientToAdd, getCurrentUserID());
        return new ModelAndView("redirect:/my_ingredients");
    }

    @RequestMapping(value = "/cook_recipe", method = {RequestMethod.POST})
    public ModelAndView cookRecipes(@RequestParam int recipeId) {


        List<RecipeIngredient> list = ingredientService.findByRecipe(recipeId);
        ingredientService.cookRecipe(list, this.getCurrentUserID());


        final ModelAndView mav = new ModelAndView("redirect:/recipe");
        mav.addObject("recipeId", recipeId);
        return mav;
    }


    @RequestMapping(value = "/delete_ingredient", method = RequestMethod.POST) //Le digo que url mappeo
    public ModelAndView deleteIngredient(@RequestParam int ingredientId) {
        ingredientService.deleteUI(ingredientId, getCurrentUserID());
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/rate_recipe", method = RequestMethod.POST) //Le digo que url mappeo
    public void rateRecipe(@RequestParam float rate, @RequestParam int recipeId) {
        recipeService.addNewRating(getCurrentUserID(), recipeId ,rate);
        //return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET) //Le digo que url mappeo
    public ModelAndView login(@RequestParam(required = false) String error) {
        final ModelAndView mav = new ModelAndView("login");

        if (error != null)
            mav.addObject("errorMessage", "error");

        return mav;
    }

    @RequestMapping(value = "/my_account", method = RequestMethod.GET)
    public ModelAndView myAccount(Authentication authentication) {
        final ModelAndView mav = new ModelAndView("my_account");

        mav.addObject("recipes_amount", recipeService.userRecipesNumber(getCurrentUserID()));

        Either <User,Warnings> eitherUser = userService.getById(getCurrentUserID());

        if(eitherUser.isValuePresent()) {
            mav.addObject("user", eitherUser.getValue());
        }
        else {
            //TODO: tirar el warning
        }
        return mav;
    }

    @RequestMapping(value = "/my_ingredients", method = RequestMethod.GET)
    public ModelAndView myIngredients(Authentication authentication, @Valid @ModelAttribute("addIngredientForm") final AddIngredientForm addIngredientForm, final BindingResult errors) {
        final ModelAndView mav = new ModelAndView("ingredients");

        int id = getCurrentUserID();

        List<RecipeIngredient> ingredientList = ingredientService.findByUser(id);


        List<Ingredient> allIngredientsList = ingredientService.getAllIngredients();

        Either <User,Warnings> eitherUser = userService.getById(id);

        mav.addObject("recipes_amount", recipeService.getAllRecipesByUserId(getCurrentUserID()).size());
        if(eitherUser.isValuePresent())
            mav.addObject("user", eitherUser.getValue());
        else{
            //TODO: tirar el warning
        }
        mav.addObject("allIngredients", allIngredientsList);
        mav.addObject("ingredientsList", ingredientList);
        return mav;
    }

	//CON EL ID LLAMO A SERVICES Y LA TRAIGO
	@RequestMapping(value = "/recipe", method = RequestMethod.GET)
	public ModelAndView recipe(@ModelAttribute("commentForm") final CommentForm form, @RequestParam Integer recipeId) {
		final ModelAndView mav = new ModelAndView("recipe");

        Recipe recipe = recipeService.getById(recipeId).get();

        Either <User,Warnings> eitherUser = userService.getById(recipe.getUserId());

        float userRating = 0;
        Optional<Float> maybeUserRating = recipeService.getUserRating(getCurrentUserID(),recipeId);
        if(maybeUserRating.isPresent())
            userRating = maybeUserRating.get();
        mav.addObject("previous_rate", userRating);
        mav.addObject("recipes_amount", recipeService.userRecipesNumber(recipe.getUserId()));
        mav.addObject("recipe", recipe);
        if(eitherUser.isValuePresent())
            mav.addObject("user", eitherUser.getValue());
        else {
            //TODO tirar el warning
        }
        mav.addObject("ingredientsList", recipe.getIngredients());
        return mav;
    }

    @RequestMapping(value = "/add_comment", method = RequestMethod.POST) //Le digo que url mappeo
    public ModelAndView deleteIngredient(@ModelAttribute("commentForm") final CommentForm form, @RequestParam Integer recipeId) {

        recipeService.addComment(new Comment(getCurrentUserID(),recipeId,form.getComment()));

        Map<String,Object> arguments = new HashMap<>();
        arguments.put("recipeId", recipeId);
        return new ModelAndView("redirect:/recipe", arguments);
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register(@ModelAttribute("registerForm") final RegisterForm form) {
        System.out.println("lll");
        final ModelAndView mav = new ModelAndView("register");
        return mav;
    }

    @RequestMapping(value = "/create", method = {RequestMethod.POST})
    public ModelAndView create(@Valid @ModelAttribute("registerForm") final RegisterForm registerForm, final BindingResult errors) {
        if (errors.hasErrors()) {
            return register(registerForm);
        }

        String hashedPassword = passwordEncoder.encode(registerForm.getPassword());
        final Either<User, Warnings> u = userService.signUpUser(new User.Builder(registerForm.getUsername(), hashedPassword, registerForm.getEmail())
                .name(registerForm.getName()).surname(registerForm.getSurname()).build());
        if(u.isValuePresent())
            return new ModelAndView("redirect:/login");
        else {
            //TODO: mostrar el warning y volver a la pagina
        }
        return new ModelAndView("redirect:/register");
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

        Either <User,Warnings> eitherUser = userService.getById(userId);

        mav.addObject("RecipeList", recipeList);
        if(eitherUser.isValuePresent())
            mav.addObject("user",eitherUser.getValue());
        else {
            //TODO: tirar el error
        }
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