package com.board.basic.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/signup")
    public String signup(UserForm userForm) {
        return "signup_form";
    }

    @PostMapping("signup")
    public String signup(@Valid UserForm userForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup_form";
        }
        if (!userForm.getPassword1().equals(userForm.getPassword2())) {
            bindingResult.rejectValue("password2","passwordIncorrect","비밀번호가 일치하지 않습니다.");
            return "signup_form";
        }
        userService.create(userForm.getUsername(), userForm.getPassword1(), userForm.getNickname());
        return "redirect:/article/list";
    }

    @GetMapping("login")
    public String login() {
        return "login_form";
    }

    @GetMapping("detail/{username}")
    public String detail(@PathVariable("username")String username, Model model) {
        SiteUser user = userService.getUser(username);
        model.addAttribute("user",user);
        return "user_detail";
    }
}
