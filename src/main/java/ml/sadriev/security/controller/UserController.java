package ml.sadriev.security.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class UserController {

    @RequestMapping(method = RequestMethod.GET)
    public String start(Model model) {
        return "index";
    }

}

