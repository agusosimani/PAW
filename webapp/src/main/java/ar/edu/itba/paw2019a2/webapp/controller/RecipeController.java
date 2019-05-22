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
public class RecipeController {

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

    @RequestMapping("/")
    public ModelAndView index(@ModelAttribute("filterForm") final FilterForm filterForm, @RequestParam(required=false) Integer page) {

        final ModelAndView mav = new ModelAndView("index");

        if(filterForm.getSearchBar() == null)
            filterForm.setSearchBar("");

        if(filterForm.getOrder() == null )
            filterForm.setOrder(Order.New);

        if(page == null)
            page = 1;

        int limit = page * 999;
        Set<Recipe> recipeList;
        if(filterForm.getWithMyIngredients())
            recipeList = recipeService.getRecipesBasedOnOrderTagsCookable(filterForm.getTags(),filterForm.getOrder(),getCurrentUserID(),filterForm.getSearchBar(),limit);
        else
            recipeList = recipeService.getRecipesBasedOnOrderTags(filterForm.getTags(),filterForm.getOrder(),filterForm.getSearchBar(),limit);


        mav.addObject("RecipeList",recipeList);
        mav.addObject("page", page);
        mav.addObject("hasMoreRecipes", recipeList.size() + 1 >= limit);
        mav.addObject("allOrders", Order.values());
        mav.addObject("allTags", Tag.values());
        return mav;
    }

    @RequestMapping(value = "/recently_cooked", method = {RequestMethod.GET})
    public ModelAndView yourCooklists(@RequestParam int userId) {
        final ModelAndView mav = new ModelAndView("recipes_list");

        Set<Recipe> recipeList = recipeService.getRecipesOrderCooked(userId);

        Either<User, Warnings> eitherUser = userService.getById(userId);
        if (eitherUser.isValuePresent())
            mav.addObject("user", eitherUser.getValue());
        else {
            return new ModelAndView("404");
        }

        mav.addObject("RecipeList", recipeList);

        String emptyWarning;
        if (userId != getCurrentUserID()) {
            emptyWarning = messageSource.getMessage("recentlyCooked.empty", new Object[]{eitherUser.getValue().getName()}, Locale.getDefault());
            mav.addObject("title", messageSource.getMessage("recentlyCooked.title", new Object[]{eitherUser.getValue().getName()}, Locale.getDefault()));
        }
        else {
            emptyWarning = messageSource.getMessage("myRecentlyCooked.empty", null, Locale.getDefault());
            mav.addObject("title", messageSource.getMessage("myRecentlyCooked.title", null, Locale.getDefault()));
        }

        mav.addObject("emptyWarning", emptyWarning);
        mav.addObject("editable", false);
        mav.addObject("recipes_amount", recipeService.userRecipesNumber(userId));
        mav.addObject("averageRate",userService.getRelativeRatingFromUser(userId));

        return mav;
    }

    @RequestMapping(value = "/new_recipe", method = {RequestMethod.GET})
    public ModelAndView newRecipe(@ModelAttribute("recipeForm") final RecipeForm recipeForm) {
        final ModelAndView mav = new ModelAndView("new_recipe");

        mav.addObject("allTags", Tag.values());
        mav.addObject("allIngredients", ingredientService.getAllIngredients());

        return mav;
    }

    @RequestMapping("/favourites_recipes")
    public ModelAndView favouriteRecipes(@RequestParam int userId) {

        final ModelAndView mav = new ModelAndView("recipes_list");


        Either<User, Warnings> eitherUser = userService.getById(userId);
        if (eitherUser.isValuePresent())
            mav.addObject("user", eitherUser.getValue());
        else {
            return new ModelAndView("404");
        }

        List<Recipe> recipeList = recipeService.getFavouriteRecipes(userId);

        String emptyWarning;
        if (userId != getCurrentUserID()) {
            emptyWarning = messageSource.getMessage("emptyFavourites", new Object[]{eitherUser.getValue().getName()}, Locale.getDefault());
            mav.addObject("title", messageSource.getMessage("favourites.title", new Object[]{eitherUser.getValue().getName()}, Locale.getDefault()));
        }
        else {
            emptyWarning = messageSource.getMessage("emptyMyfavourites", null, Locale.getDefault());
            mav.addObject("title", messageSource.getMessage("yourFavourites", null, Locale.getDefault()));
        }

        mav.addObject("RecipeList", recipeList);
        mav.addObject("emptyWarning", emptyWarning);
        mav.addObject("editable", false);
        mav.addObject("recipes_amount", recipeService.userRecipesNumber(userId));
        mav.addObject("averageRate",userService.getRelativeRatingFromUser(userId));


        return mav;
    }

    @RequestMapping(value = "/cook_recipe", method = {RequestMethod.POST})
    public ModelAndView cookRecipes(@RequestParam int recipeId) {


        Optional<List<RecipeIngredient>> maybeList = ingredientService.findByRecipe(recipeId);

        if(!maybeList.isPresent())
            return new ModelAndView("redirect:/404");

        List<RecipeIngredient> list = maybeList.get();

        Boolean s = ingredientService.cookRecipe(recipeId,list, this.getCurrentUserID()).equals(Warnings.Success);


        Map<String, Object> arguments = new HashMap<>();
        arguments.put("recipeId", recipeId);
        arguments.put("cooked", s);
        return new ModelAndView("redirect:/recipe", arguments);
    }

    @RequestMapping(value = "/create_recipe", method = {RequestMethod.POST})
    public ModelAndView createRecipe(@Valid @ModelAttribute("recipeForm") final RecipeForm recipeForm, final BindingResult errors) {
        if (errors.hasErrors()) {
            return newRecipe(recipeForm);
        }

        List<RecipeIngredient> listIngredients = new ArrayList<>();

        byte[] bytes = null;
        try {
            bytes = recipeForm.getImage().getBytes();
        } catch (Exception e) {
            bytes = new byte[0];
        }

        List<Integer> formIngredients = recipeForm.getIngredients();
        List<Integer> formIngredientsAmount = recipeForm.getIngredientsAmount();

        if (formIngredients == null || formIngredients.size() != formIngredientsAmount.size() || formIngredients.size() == 0) {
            return new ModelAndView("redirect:/400");
        }

        for(int i = 0; i < formIngredients.size(); i++) {
            listIngredients.add(new RecipeIngredient.Builder(ingredientService.getById(formIngredients.get(i)).get(), formIngredientsAmount.get(i)).build());
        }

        final Recipe recipeToAdd = new Recipe.Builder(0, recipeForm.getName(), listIngredients, recipeForm.getInstructions(), getCurrentUserID())
                .description(recipeForm.getDescription())
                .tags(recipeForm.getTags()).image(bytes)
                .build();
        recipeService.addNewRecipe(recipeToAdd);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/delete_recipe", method = RequestMethod.POST) //Le digo que url mappeo
    public ModelAndView deleteRecipe(@RequestParam int recipeId) {
        Optional<Recipe> maybeRecipe = recipeService.getById(recipeId);
        if (maybeRecipe.isPresent() && (maybeRecipe.get().getUserId() == getCurrentUserID() || isAdmin())) {
            recipeService.deleteRecipe(recipeId);
            return new ModelAndView("redirect:/my_recipes");
        } else {
            return new ModelAndView("redirect:/403");
        }
    }

    @RequestMapping(value = "/rate_recipe", method = RequestMethod.POST) //Le digo que url mappeo
    public void rateRecipe(@RequestParam float rate, @RequestParam int recipeId) {
        recipeService.addNewRating(getCurrentUserID(), recipeId, rate);
        //return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/recipe", method = RequestMethod.GET)
    public ModelAndView recipe(@ModelAttribute("selectCookListForm") final SelectCookListForm cookListForm, @ModelAttribute("commentForm") final CommentForm commentForm, @RequestParam Integer recipeId
            , @RequestParam(required = false) Boolean cooked) {
        final ModelAndView mav = new ModelAndView("recipe");

        Optional<Recipe> maybeRecipe = recipeService.getById(recipeId);
        if(!maybeRecipe.isPresent()) {
            return new ModelAndView("404");
        }
        Recipe recipe = maybeRecipe.get();
        Either<User, Warnings> eitherUser = userService.getById(recipe.getUserId());

        Boolean disabledUser = false;
        if(!eitherUser.isValuePresent()){
            disabledUser = true;
        }
        else{
            try {
                mapUserParams(mav, eitherUser.getValue());
            }
            catch (Exception e) {
            }
        }
        mav.addObject("disabledUser", disabledUser);

        float userRating = 0;
        Optional<Float> maybeUserRating = recipeService.getUserRating(getCurrentUserID(), recipeId);
        if (maybeUserRating.isPresent())
            userRating = maybeUserRating.get();

        double fat =0, calorie=0,carbohydrate=0,protein=0;
        for(RecipeIngredient recipeIngredient : recipe.getIngredients()){
            fat +=  recipeIngredient.getAmount() / recipeIngredient.getIngredient().getServing() * recipeIngredient.getIngredient().getTotalFat();
            calorie +=  recipeIngredient.getAmount() / recipeIngredient.getIngredient().getServing() * recipeIngredient.getIngredient().getCalories();
            carbohydrate +=  recipeIngredient.getAmount() / recipeIngredient.getIngredient().getServing() * recipeIngredient.getIngredient().getCarbohydrates();
            protein +=  recipeIngredient.getAmount() / recipeIngredient.getIngredient().getServing() * recipeIngredient.getIngredient().getProtein();
        }

        List<NutricionalInfo> nutricionalInfo = new ArrayList<>();
        nutricionalInfo.add(new NutricionalInfo(NutritionalInfoTypes.Fat,fat));
        nutricionalInfo.add(new NutricionalInfo(NutritionalInfoTypes.Calorie,calorie));
        nutricionalInfo.add(new NutricionalInfo(NutritionalInfoTypes.Carbohydrate,carbohydrate));
        nutricionalInfo.add(new NutricionalInfo(NutritionalInfoTypes.Protein,protein));


        mav.addObject("nutricionalInfoList", nutricionalInfo);
        mav.addObject("cooked", cooked);
        mav.addObject("editable", recipe.getUserId() == getCurrentUserID() || isAdmin());
        mav.addObject("cookLists", recipeService.getUserCookLists(getCurrentUserID()));
        mav.addObject("previous_rate", userRating);
        mav.addObject("recipe", recipe);

        mav.addObject("ingredientsList", recipe.getIngredients());
        return mav;
    }

    @RequestMapping("/my_recipes") //Le digo que url mappeo
    public ModelAndView myRecipes() {
        return userRecipes(getCurrentUserID());
    }

    @RequestMapping(value = "/add_comment", method = RequestMethod.POST) //Le digo que url mappeo
    public ModelAndView deleteIngredient(@ModelAttribute("commentForm") final CommentForm form, @RequestParam Integer recipeId) {

        recipeService.addComment(new Comment(getCurrentUserID(), recipeId, form.getComment()));

        Map<String, Object> arguments = new HashMap<>();
        arguments.put("recipeId", recipeId);
        return new ModelAndView("redirect:/recipe", arguments);
    }

    @RequestMapping(value = "/edit_recipe", method = RequestMethod.GET)
    public ModelAndView editRecipe(@ModelAttribute("recipeForm") final RecipeForm recipeForm, @RequestParam int recipeId) {

        final ModelAndView mav = new ModelAndView("edit_recipe");
        Optional<Recipe> maybeRecipe = recipeService.getById(recipeId);
        if (maybeRecipe.isPresent()) {
            Recipe recipe = maybeRecipe.get();
            recipeForm.setTags(recipe.getTags());
            recipeForm.setInstructions(recipe.getInstructions());

            Gson g = new Gson();
            mav.addObject("recipeIngredientsList", g.toJson(recipe.getIngredients()));

            mav.addObject("allTags", Tag.values());
            mav.addObject("recipeName", recipe.getName());
            mav.addObject("recipeDescription", recipe.getDescription());
            mav.addObject("allIngredients", ingredientService.getAllIngredients());
            mav.addObject("recipeId", recipeId);

            return mav;
        }
        return new ModelAndView("404");

    }

    @RequestMapping(value = "/save_edit_changes", method = RequestMethod.POST)
    public ModelAndView editRecipeSave(@RequestParam int recipeId, @Valid @ModelAttribute("recipeForm") final RecipeForm recipeForm, final BindingResult errors) {
        if (errors.hasErrors()) {
            return editRecipe(recipeForm,recipeId);
        }
        Optional<Recipe> maybeRecipe = recipeService.getById(recipeId);
        if(!maybeRecipe.isPresent())
            return new ModelAndView("redirect:/404");
        if(maybeRecipe.get().getUserId() != getCurrentUserID())
            return new ModelAndView("redirect:/403");

        List<RecipeIngredient> ingList = new ArrayList<>();

        for (int i = 0; i < recipeForm.getIngredientsAmount().size();i++) {
            RecipeIngredient ri = new RecipeIngredient();
            ri.setIngredient(new Ingredient());
            ri.getIngredient().setId(recipeForm.getIngredients().get(i));
            ri.setAmount(recipeForm.getIngredientsAmount().get(i));
            ingList.add(ri);
        }

        Recipe recipe = new Recipe.Builder(recipeForm.getName(),ingList,recipeForm.getInstructions(),getCurrentUserID()).description(recipeForm.getDescription()).tags(recipeForm.getTags()).build();

        recipe.setId(recipeId);

        recipeService.update(recipe);



        final ModelAndView mav = new ModelAndView("redirect:/recipe");
        mav.addObject("recipeId", recipeId);

        return mav;
    }

    @RequestMapping("/user_recipes") //Le digo que url mappeo
    public ModelAndView userRecipes(@RequestParam int userId) {

        final ModelAndView mav = new ModelAndView("recipes_list");

        List<Recipe> recipeList = recipeService.getAllRecipesByUserId(userId);

        Either<User, Warnings> eitherUser = userService.getById(userId);
        if (eitherUser.isValuePresent())
            mav.addObject("user", eitherUser.getValue());
        else {
            return new ModelAndView("404");
        }

        mav.addObject("RecipeList", recipeList);

        String emptyWarning;
        if (userId != getCurrentUserID()) {
            emptyWarning = messageSource.getMessage("emptyRecipes", new Object[]{eitherUser.getValue().getName()}, Locale.getDefault());
            mav.addObject("title", messageSource.getMessage("recipe.title", new Object[]{eitherUser.getValue().getName()}, Locale.getDefault()));
        }
        else {
            emptyWarning = messageSource.getMessage("emptyMyRecipes", new Object[]{eitherUser.getValue().getName()}, Locale.getDefault());
            mav.addObject("title", messageSource.getMessage("myRecipes", null, Locale.getDefault()));
        }


        mav.addObject("emptyWarning", emptyWarning);
        mav.addObject("editable", getCurrentUserID() == userId);
        mav.addObject("recipes_amount", recipeService.userRecipesNumber(userId));
        mav.addObject("averageRate",userService.getRelativeRatingFromUser(userId));


        return mav;
    }


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
