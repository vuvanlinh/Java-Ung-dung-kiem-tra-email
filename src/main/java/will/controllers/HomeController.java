package will.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.String;

@Controller
public class HomeController {
    private static Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_REGEX = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";

    public HomeController(){
        pattern = Pattern.compile(EMAIL_REGEX);
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String home(Locale locale, Model model){
        return "home";
    }

    @RequestMapping(value = "/validate", method =  RequestMethod.POST)
    public String user(@RequestParam("email") String email, ModelMap model){
        boolean isValid = validate(email);
        if (!isValid){
            model.addAttribute("message","Email is invalid");
            return "home";
        }

        model.addAttribute("email", email);
        return "success";
    }

    private boolean validate(String regex){
        matcher = pattern.matcher(regex);
        return matcher.matches();
    }
}
