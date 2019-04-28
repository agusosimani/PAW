package ar.edu.itba.paw.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloWorldController {

//	@Autowired
//	private UserDao us;

	@RequestMapping("/") //Le digo que url mappeo
	public ModelAndView helloWorld() {
		final ModelAndView mav = new ModelAndView("index"); //Seleccionar lista
		mav.addObject("greeting", "PAW"); //Popular model
		return mav;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET) //Le digo que url mappeo
	public ModelAndView login() {
		final ModelAndView mav = new ModelAndView("login"); //Seleccionar lista
		mav.addObject("greeting", "PAW"); //Popular model
		return mav;
	}

	@RequestMapping(value = "/my_account", method = RequestMethod.GET) //Le digo que url mappeo
	public ModelAndView myAccount() {
		final ModelAndView mav = new ModelAndView("my_account"); //Seleccionar lista
		mav.addObject("greeting", "PAW"); //Popular model
		return mav;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET) //Le digo que url mappeo
	public ModelAndView register() {
		final ModelAndView mav = new ModelAndView("register"); //Seleccionar lista
		mav.addObject("greeting", "PAW"); //Popular model
		return mav;
	}

	@RequestMapping("/logout") //Le digo que url mappeo
	public ModelAndView logout() {
		final ModelAndView mav = new ModelAndView("logout"); //Seleccionar lista
		mav.addObject("greeting", "PAW"); //Popular model
		return mav;
	}
	//No quiero repetirle todo el tiempo el path "WEB-INF/jsp/.." entonces configuro mi propio view resolver en web config
}