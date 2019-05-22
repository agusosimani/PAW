package ar.edu.itba.paw2019a2.webapp.controller;
import ar.edu.itba.paw2019a2.interfaces.service.*;
import ar.edu.itba.paw2019a2.model.*;
import ar.edu.itba.paw2019a2.model.Enum.*;
import ar.edu.itba.paw2019a2.webapp.auth.PawUserDetails;
import ar.edu.itba.paw2019a2.webapp.form.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
import java.util.*;

import javax.validation.Valid;

@Controller
public class HomeController {


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

    @RequestMapping("/") //Le digo que url mappeo
    public ModelAndView index(@ModelAttribute("filterForm") final FilterForm filterForm) {
        final ModelAndView mav = new ModelAndView("index");

        //filterForm.setTags(tags);

        if(filterForm.getOrder() != null )
            ;//filterForm.setOrder(order);
        else
            filterForm.setOrder(Order.New);

        if(filterForm.getTags() != null ) {
            for (String s : filterForm.getTags()) {
                System.out.printf("%s\n", s);
            }
        }

        mav.addObject("allOrders", Order.values());
        mav.addObject("allTags", Tag.values());
        if(filterForm.getWithMyIngredients())
            mav.addObject("RecipeList", recipeService.getRecipesBasedOnOrderTagsCookable(filterForm.getTags(),filterForm.getOrder(),getCurrentUserID()));
        else
            mav.addObject("RecipeList", recipeService.getRecipesBasedOnOrderTags(filterForm.getTags(),filterForm.getOrder()));
        return mav;
    }


    @RequestMapping(value = "/new_recipe", method = {RequestMethod.GET})
    public ModelAndView newRecipe(@ModelAttribute("recipeForm") final RecipeForm recipeForm) {
        final ModelAndView mav = new ModelAndView("new_recipe");

        mav.addObject("allTags", Tag.values());
        mav.addObject("allIngredients", ingredientService.getAllIngredients());

        return mav;
    }

    @RequestMapping(value = "/cooklist", method = {RequestMethod.GET})
    public ModelAndView cooklist(@RequestParam int cookListId, @RequestParam int userId) {
        //TODO: tomar la cooklist con el userId dueño

        final ModelAndView mav = new ModelAndView("cooklist_recipes");


        Either<User, Warnings> eitherUser = userService.getById(userId);
        if (eitherUser.isValuePresent())
            mav.addObject("user", eitherUser.getValue());
        else {
            //TODO: tirar el error
        }

        mav.addObject("cooklist", recipeService.getCookList(cookListId).getValue());
        mav.addObject("yourCooklist", userId == getCurrentUserID());
        mav.addObject("recipes_amount", recipeService.userRecipesNumber(userId));

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

        User user = userService.getById(userId).getValue();

        if (userId != getCurrentUserID())
            mav.addObject("title", messageSource.getMessage("cooklist.title", new Object[]{user.getName()}, Locale.getDefault()));
        else
            mav.addObject("title", messageSource.getMessage("myCooklists", null, Locale.getDefault()));

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

    @RequestMapping(value = "/create_recipe", method = {RequestMethod.POST})
    public ModelAndView createRecipe(@Valid @ModelAttribute("recipeForm") final RecipeForm recipeForm, final BindingResult errors) {
        if (errors.hasErrors()) {
            return newRecipe(recipeForm);
        }

        for (String tag : recipeForm.getTags()) {
            System.out.printf("LA LISTA TIENE TAGS: %s", tag);
        }
        List<RecipeIngredient> listIngredients = new ArrayList<>();

        byte[] bytes = null;
        try {
            //TODO: VALIDAR ESTO!
            bytes = recipeForm.getImage().getBytes();
        } catch (Exception e) {

        }

        List<Integer> formIngredients = recipeForm.getIngredients();
        List<Integer> formIngredientsAmount = recipeForm.getIngredientsAmount();
        if (formIngredients != null) {
            for(int i = 0; i < formIngredients.size(); i++) {
                listIngredients.add(new RecipeIngredient.Builder(ingredientService.getById(formIngredients.get(i)).get(), formIngredientsAmount.get(i)).build());
            }
        } else {
            //TODO: tirar el error
        }

        final Recipe recipeToAdd = new Recipe.Builder(0, recipeForm.getName(), listIngredients, recipeForm.getInstructions(), getCurrentUserID())
                .description(recipeForm.getDescription())
                .tags(recipeForm.getTags()).image(bytes)
                .build();
        recipeService.addNewRecipe(recipeToAdd);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/add_ingredient_user", method = {RequestMethod.POST})
    public ModelAndView addIngredientUser(@Valid @ModelAttribute("recipeForm") final AddIngredientForm addIngredientForm, final BindingResult errors) {
        if (errors.hasErrors()) {
            //TODO
            return null;
        }

        List<RecipeIngredient> ingredientsList = addIngredientForm.getIngredients();

        for (RecipeIngredient ri : ingredientsList) {
            ingredientService.addNewUserIngredient(ri, getCurrentUserID());
        }
        return new ModelAndView("redirect:/my_ingredients");
    }

    @RequestMapping(value = "/delete_cooklist", method = RequestMethod.POST) //Le digo que url mappeo
    public ModelAndView deleteCooklist(@RequestParam int cooklistId) {
        //TODO VERIFICAR QUE ESTE AUTORIZADO!
        //TODO Por que le tengo que pasar el userId?
        recipeService.deleteCookList(cooklistId,getCurrentUserID());
        return new ModelAndView("redirect:/your_cooklists");
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

    @RequestMapping(value = "/delete_ingredient", method = RequestMethod.POST) //Le digo que url mappeo
    public ModelAndView deleteIngredient(@RequestParam int ingredientId) {
        Optional<RecipeIngredient> maybeRI = ingredientService.getByIngredientUserId(ingredientId,getCurrentUserID());
        if(!maybeRI.isPresent()){
            return new ModelAndView("redirect:/403");
        }
        ingredientService.deleteUI(ingredientId, getCurrentUserID());
        return new ModelAndView("redirect:/my_ingredients");
    }

    @RequestMapping(value = "/rate_recipe", method = RequestMethod.POST) //Le digo que url mappeo
    public void rateRecipe(@RequestParam float rate, @RequestParam int recipeId) {
        recipeService.addNewRating(getCurrentUserID(), recipeId, rate);
        //return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(required = false) String error) {

        return new ModelAndView("login");
    }

    @RequestMapping(value = "/my_account", method = RequestMethod.GET)
    public ModelAndView myAccount(Authentication authentication) {
        return account(authentication, getCurrentUserID());
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public ModelAndView account(Authentication authentication, @RequestParam int userId) {
        final ModelAndView mav = new ModelAndView("account");

        Either<User, Warnings> eitherUser = userService.getById(userId);

        if (eitherUser.isValuePresent()) {
            mav.addObject("user", eitherUser.getValue());
        } else {
            //TODO: tirar el warning
        }

        mav.addObject("recipes_amount", recipeService.userRecipesNumber(userId));
        mav.addObject("yourAccount", getCurrentUserID() == userId);
        return mav;
    }

    @RequestMapping(value = "/my_ingredients", method = RequestMethod.GET)
    public ModelAndView myIngredients(Authentication authentication, @Valid @ModelAttribute("addIngredientForm") final AddIngredientForm addIngredientForm, final BindingResult errors) {
        final ModelAndView mav = new ModelAndView("ingredients");

        int id = getCurrentUserID();

        List<RecipeIngredient> ingredientList = ingredientService.findByUser(id);


        List<Ingredient> allIngredientsList = ingredientService.getAllIngredients();

        Either<User, Warnings> eitherUser = userService.getById(id);

        mav.addObject("recipes_amount", recipeService.getAllRecipesByUserId(getCurrentUserID()).size());
        if (eitherUser.isValuePresent())
            mav.addObject("user", eitherUser.getValue());
        else {
            //TODO: tirar el warning
        }
        mav.addObject("allIngredients", allIngredientsList);
        mav.addObject("ingredientsList", ingredientList);
        return mav;
    }

    @RequestMapping(value = "/cook_recipe", method = {RequestMethod.POST})
    public ModelAndView cookRecipes(@RequestParam int recipeId) {


        List<RecipeIngredient> list = ingredientService.findByRecipe(recipeId);
        Boolean s = ingredientService.cookRecipe(list, this.getCurrentUserID()).equals(Warnings.Success);


        Map<String, Object> arguments = new HashMap<>();
        arguments.put("recipeId", recipeId);
        arguments.put("cooked", s);
        return new ModelAndView("redirect:/recipe", arguments);
    }

    //CON EL ID LLAMO A SERVICES Y LA TRAIGO
    @RequestMapping(value = "/recipe", method = RequestMethod.GET)
    public ModelAndView recipe(@ModelAttribute("selectCookListForm") final SelectCookListForm cookListForm, @ModelAttribute("commentForm") final CommentForm commentForm, @RequestParam Integer recipeId
            , @RequestParam(required = false) Boolean cooked) {
        final ModelAndView mav = new ModelAndView("recipe");

        Optional<Recipe> maybeRecipe = recipeService.getById(recipeId);

        if(!maybeRecipe.isPresent()) {
            return new ModelAndView("redirect:/404");
        }

        Recipe recipe = maybeRecipe.get();

        Either<User, Warnings> eitherUser = userService.getById(recipe.getUserId());

        float userRating = 0;
        Optional<Float> maybeUserRating = recipeService.getUserRating(getCurrentUserID(), recipeId);
        if (maybeUserRating.isPresent())
            userRating = maybeUserRating.get();

        double fat =0;
        double calorie=0,carbohydrate=0,protein=0;
        for(RecipeIngredient recipeIngredient : recipe.getIngredients()){
            System.out.printf("RECIPE CANTIDA: %f\n EN SERVING: %f\n", recipeIngredient.getAmount(),recipeIngredient.getIngredient().getServing());
            double fatAdd = recipeIngredient.getAmount() / recipeIngredient.getIngredient().getServing() * recipeIngredient.getIngredient().getTotalFat();
            System.out.printf("Agrego: %f", recipeIngredient.getIngredient().getTotalFat());
            fat +=  fatAdd;
            calorie +=  recipeIngredient.getAmount() / recipeIngredient.getIngredient().getServing() * recipeIngredient.getIngredient().getCalories();
            carbohydrate +=  recipeIngredient.getAmount() / recipeIngredient.getIngredient().getServing() * recipeIngredient.getIngredient().getCarbohydrates();
            protein +=  recipeIngredient.getAmount() / recipeIngredient.getIngredient().getServing() * recipeIngredient.getIngredient().getProtein();
        }

        List<NutricionalInfo> nutricionalInfo = new ArrayList<>();
        nutricionalInfo.add(new NutricionalInfo(NutricionalInfoTypes.Fat,fat));
        nutricionalInfo.add(new NutricionalInfo(NutricionalInfoTypes.Calorie,calorie));
        nutricionalInfo.add(new NutricionalInfo(NutricionalInfoTypes.Carbohydrate,carbohydrate));
        nutricionalInfo.add(new NutricionalInfo(NutricionalInfoTypes.Protein,protein));

        mav.addObject("nutricionalInfoList", nutricionalInfo);
        mav.addObject("cooked", cooked);
        mav.addObject("editable", recipe.getUserId() == getCurrentUserID());
        mav.addObject("editable", recipe.getUserId() == getCurrentUserID() || isAdmin());
        mav.addObject("cookLists", recipeService.getUserCookLists(getCurrentUserID()));
        mav.addObject("previous_rate", userRating);
        mav.addObject("recipes_amount", recipeService.userRecipesNumber(recipe.getUserId()));
        mav.addObject("recipe", recipe);
        if (eitherUser.isValuePresent())
            mav.addObject("user", eitherUser.getValue());
        else {
            //TODO tirar el warning
        }
        mav.addObject("ingredientsList", recipe.getIngredients());
        return mav;
    }

    @RequestMapping(value = "/add_recipe_to_cooklist", method = RequestMethod.POST) //Le digo que url mappeo
    public ModelAndView addRecipeToCooklist(@ModelAttribute("selectCookListForm") final SelectCookListForm form, final BindingResult errors, @RequestParam int recipeId) {
        if (errors.hasErrors()) {
            //TODO
        }

        recipeService.addRecipeToCookList(form.getCooklistId(), recipeId);
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("recipeId", recipeId);
        return new ModelAndView("redirect:/recipe", arguments);
    }


    @RequestMapping(value = "/add_comment", method = RequestMethod.POST) //Le digo que url mappeo
    public ModelAndView deleteIngredient(@ModelAttribute("commentForm") final CommentForm form, @RequestParam Integer recipeId) {

        recipeService.addComment(new Comment(getCurrentUserID(), recipeId, form.getComment()));

        Map<String, Object> arguments = new HashMap<>();
        arguments.put("recipeId", recipeId);
        return new ModelAndView("redirect:/recipe", arguments);
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register(@ModelAttribute("registerForm") final RegisterForm form) {
        if (getCurrentUserID() != -1)
            return new ModelAndView("redirect:/");

        final ModelAndView mav = new ModelAndView("register");
        return mav;
    }

    @RequestMapping(value = "/create", method = {RequestMethod.POST})
    public ModelAndView create(@Valid @ModelAttribute("registerForm") final RegisterForm registerForm, final BindingResult errors, WebRequest request) {
        if (errors.hasErrors()) {
            return register(registerForm);
        }

        String hashedPassword = passwordEncoder.encode(registerForm.getPassword());
        final Either<User, Warnings> u = userService.signUpUser(new User.Builder(registerForm.getUsername(), hashedPassword, registerForm.getEmail())
                .name(registerForm.getName()).surname(registerForm.getSurname()).build());
        if(u.isValuePresent()) {
            ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequestUri();
            builder.scheme("http");
            URI uri = builder.build().toUri();
            Warnings emailWarnings = emailService.sendMailConfirmationEmail(u.getValue(), uri.toString());
            if (emailWarnings != Warnings.valueOf("EmailError"))
                return new ModelAndView("redirect:/login");
        } else {
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

        Either<User, Warnings> eitherUser = userService.getById(userId);

        mav.addObject("RecipeList", recipeList);
        if (eitherUser.isValuePresent())
            mav.addObject("user", eitherUser.getValue());
        else {
            //TODO: tirar el error
        }
        List<Recipe> rec = recipeService.getAllRecipesByUserId(userId);

        if (userId != getCurrentUserID())
            mav.addObject("title", messageSource.getMessage("recipe.title", new Object[]{eitherUser.getValue().getName()}, Locale.getDefault()));
        else
            mav.addObject("title", messageSource.getMessage("myRecipes", null, Locale.getDefault()));
        mav.addObject("yourAccount", getCurrentUserID() == userId);
        mav.addObject("recipes_amount", recipeService.userRecipesNumber(userId));

        return mav;
    }

    @RequestMapping(value = "/edit_recipe", method = RequestMethod.GET)
    public ModelAndView editRecipe(@ModelAttribute("recipeForm") final RecipeForm recipeForm, @RequestParam int recipeId) {

        final ModelAndView mav = new ModelAndView("edit_recipe");
        Optional<Recipe> maybeRecipe = recipeService.getById(recipeId);
        if (maybeRecipe.isPresent()) {
            Recipe recipe = maybeRecipe.get();
            //recipeForm.setIngredients(recipe.getIngredients());
            recipeForm.setTags(recipe.getTags());
            recipeForm.setInstructions(recipe.getInstructions());

            mav.addObject("allTags", Tag.values());
            mav.addObject("recipeName", recipe.getName());
            mav.addObject("recipeDescription", recipe.getDescription());
            //mav.addObject("recipeInstructions", recipe.getInstructions());
            mav.addObject("allIngredients", ingredientService.getAllIngredients());
            mav.addObject("recipeId", recipeId);

            return mav;
        }
        return new ModelAndView("redirect:/404");

    }

    @RequestMapping(value = "/save_edit_changes", method = RequestMethod.POST)
    public ModelAndView editRecipeSave(@RequestParam int recipeId, @ModelAttribute("recipeForm") final RecipeForm recipeForm, final BindingResult errors) {
        if (errors.hasErrors()) {
            //TODO
        }
        Optional<Recipe> maybeRecipe = recipeService.getById(recipeId);
        if(!maybeRecipe.isPresent())
            return new ModelAndView("redirect:/404");
        if(maybeRecipe.get().getUserId() != getCurrentUserID())
            return new ModelAndView("redirect:/403");

        //TODO CAMBIAR en recipe forms a lista de recipeIngredients

        Recipe recipe = new Recipe.Builder(recipeForm.getName(),new ArrayList<>(),recipeForm.getInstructions(),getCurrentUserID()).description(recipeForm.getDescription()).tags(recipeForm.getTags()).build();

        recipeService.update(recipe);



        final ModelAndView mav = new ModelAndView("redirect:/recipe");
        mav.addObject("recipeId", recipeId);

        return mav;
    }
}