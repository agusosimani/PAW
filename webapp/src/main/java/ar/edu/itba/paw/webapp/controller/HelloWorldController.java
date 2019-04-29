package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.dao.UserDao;
import ar.edu.itba.paw.interfaces.service.UserService;
import ar.edu.itba.paw.model.User;
import ar.edu.itba.paw.webapp.form.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
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
		mav.addObject("greeting", "PAW"); //Popular model
		return mav;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET) //Le digo que url mappeo
	public ModelAndView login() {
		final ModelAndView mav = new ModelAndView("login"); //Seleccionar lista
		return mav;
	}

	@RequestMapping(value = "/my_account", method = RequestMethod.GET) //Le digo que url mappeo
	public ModelAndView myAccount() {
		final ModelAndView mav = new ModelAndView("my_account"); //Seleccionar lista
		mav.addObject("greeting", "PAW"); //Popular model
		return mav;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET) //Le digo que url mappeo
	public ModelAndView register(@ModelAttribute("registerForm") final UserForm form) {
		final ModelAndView mav = new ModelAndView("register"); //Seleccionar lista
		return mav;
	}

	@RequestMapping(value = "/create", method = { RequestMethod.POST })
	public ModelAndView create(@Valid @ModelAttribute("registerForm") final UserForm form, final BindingResult errors) {
		if (errors.hasErrors()) {
			return register(form);
		}
		String hashedPassword = passwordEncoder.encode(form.getPassword());
		final User u = us.signUpUser(new User.Builder(-1, form.getUsername(), hashedPassword, form.getEmail())
				.name(form.getName()).surname(form.getSurname()).build());
		return new ModelAndView("redirect:/user?userId=" + u.getId());
	}

	@RequestMapping("/logout") //Le digo que url mappeo
	public ModelAndView logout() {
		final ModelAndView mav = new ModelAndView("logout"); //Seleccionar lista
		mav.addObject("greeting", "PAW"); //Popular model
		return mav;
	}
	//No quiero repetirle todo el tiempo el path "WEB-INF/jsp/.." entonces configuro mi propio view resolver en web config
}