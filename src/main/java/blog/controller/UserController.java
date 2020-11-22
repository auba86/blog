package blog.controller;

import blog.model.User;
import blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/createNewUser")
    public String createNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "adduser";
    }

    @PostMapping("/saveUserData")
    public String saveUserData(@ModelAttribute("user") User user){
        userService.saveUserData(user);
        return "redirect:/";
    }


}
