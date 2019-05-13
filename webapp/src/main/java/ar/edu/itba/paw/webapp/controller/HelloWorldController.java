package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.service.IngredientService;
import ar.edu.itba.paw.interfaces.service.RecipeService;
import ar.edu.itba.paw.model.Recipe;
import ar.edu.itba.paw.webapp.form.RecipeForm;
import ar.edu.itba.paw.interfaces.service.UserService;
import ar.edu.itba.paw.model.User;
import ar.edu.itba.paw.webapp.form.RegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
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

	@RequestMapping("/") //Le digo que url mappeo
	public ModelAndView helloWorld(@ModelAttribute("recipeForm") final RecipeForm recipeForm) {
		final ModelAndView mav = new ModelAndView("index"); //Seleccionar lista

		Optional<List<Recipe>> maybeList = recipeService.getRecipes();

		List<Recipe> recipeList = new LinkedList<>();
		if(maybeList.isPresent())
			recipeList = maybeList.get();

		mav.addObject("RecipeList", recipeList); //Popular model
		return mav;
	}

	@RequestMapping(value = "/create_recipe", method = { RequestMethod.POST })
	public ModelAndView createRecipe(@Valid @ModelAttribute("recipeForm") final RecipeForm recipeForm, final BindingResult errors) {
		if (errors.hasErrors()) {
			return null;
		}
		final Recipe recipeToAdd = new Recipe.Builder(0, recipeForm.getName(), null, recipeForm.getInstructions(),1)
				.description(recipeForm.getDescription())
				.build();
		recipeService.addNewRecipe(recipeToAdd);
		return new ModelAndView("redirect:/");
	}


	/* ESTO ESTA HORRIBLE . PODRIA SER MUUUUCHO MEJOR*/
	@RequestMapping(value = "/new_recipe")
	public ModelAndView newRecipe(@Valid @ModelAttribute("recipeForm") final RecipeForm recipeForm, final BindingResult errors) {
		return new ModelAndView("new_recipe");
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET) //Le digo que url mappeo
	public ModelAndView login() {
		final ModelAndView mav = new ModelAndView("login");
		return mav;
	}

	@RequestMapping(value = "/my_account", method = RequestMethod.GET)
	public ModelAndView myAccount() {
		final ModelAndView mav = new ModelAndView("my_account");
		mav.addObject("recipes_amount",846684);
		mav.addObject("user", new User.Builder("Noobmaster69", "asd", "asd@gmail.com").build());
		return mav;
	}

	//CON EL ID LLAMO A SERVICES Y LA TRAIGO
	@RequestMapping(value = "/recipe", method = RequestMethod.GET)
	public ModelAndView recipe(@RequestParam Integer recipeId) {
		final ModelAndView mav = new ModelAndView("recipe");

		byte[] bytes = null;

		try {
            InputStream fis = new URL("https://i.blogs.es/36938e/istock-840527124/450_1000.jpg").openStream();
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			try {
				for (int readNum; (readNum = fis.read(buf)) != -1;) {
					bos.write(buf, 0, readNum);
				}
			} catch (IOException ex) {
			}
			bytes = bos.toByteArray();

		} catch (Exception f)
		{
			System.out.println("File not found");
		}

		Recipe recipe = recipeService.getById(recipeId).get();
		recipe.setImage(bytes);

		mav.addObject("recipes_amount",recipeService.getAllRecipesByUserId(recipe.getUserId()).get().size());
		mav.addObject("recipe",recipe);
		mav.addObject("user", userService.getById(recipe.getUserId()).get());
		return mav;
	}


	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register(@ModelAttribute("registerForm") final RegisterForm form) {
		final ModelAndView mav = new ModelAndView("register");
		return mav;
	}

	@RequestMapping(value = "/create", method = { RequestMethod.POST })
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


	@RequestMapping("/user_recipes") //Le digo que url mappeo
	public ModelAndView userRecipes(@RequestParam int userId) {

		final ModelAndView mav = new ModelAndView("user_recipes");

		Optional<List<Recipe>> maybeList = recipeService.getAllRecipesByUserId(userId);

		List<Recipe> recipeList = new LinkedList<>();
		if(maybeList.isPresent())
			recipeList = maybeList.get();

		mav.addObject("RecipeList", recipeList);
		mav.addObject(userService.getById(userId).get());
		mav.addObject("recipes_amount",846684);
		return mav;
	}

	//No quiero repetirle todo el tiempo el path "WEB-INF/jsp/.." entonces configuro mi propio view resolver en web config

//	@ModelAttribute("user")
//	public Integer loggedUser(final HttpSession session)
//	{
//		return (Integer) session.getAttribute(LOGGED_USER_ID);
//	}
}