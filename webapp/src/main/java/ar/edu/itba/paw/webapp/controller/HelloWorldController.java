package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.service.UserService;
import ar.edu.itba.paw.model.User;
import ar.edu.itba.paw.webapp.form.RegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class HelloWorldController {

	@Autowired
	private UserService us;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@RequestMapping("/") //Le digo que url mappeo
	public ModelAndView helloWorld() {
		final ModelAndView mav = new ModelAndView("index"); //Seleccionar lista
		return mav;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		final ModelAndView mav = new ModelAndView("login");
		return mav;
	}

	@RequestMapping(value = "/my_account", method = RequestMethod.GET)
	public ModelAndView myAccount(Authentication authentication) {
		final ModelAndView mav = new ModelAndView("my_account");
		mav.addObject("user", authentication.getName());
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
		final User u = us.signUpUser(new User.Builder(-1, form.getUsername(), hashedPassword, form.getEmail())
				.name(form.getName()).surname(form.getSurname()).build());
		return new ModelAndView("redirect:/user?userId=" + u.getId());
	}

	@RequestMapping("/logout")
	public ModelAndView logout() {
		final ModelAndView mav = new ModelAndView("logout");
		mav.addObject("greeting", "PAW");
		return mav;
	}
	//No quiero repetirle todo el tiempo el path "WEB-INF/jsp/.." entonces configuro mi propio view resolver en web config

//	@ModelAttribute("user")
//	public Integer loggedUser(final HttpSession session)
//	{
//		return (Integer) session.getAttribute(LOGGED_USER_ID);
//	}
}