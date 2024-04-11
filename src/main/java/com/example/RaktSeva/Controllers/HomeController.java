package com.example.RaktSeva.Controllers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.RaktSeva.DAO.BloodBankRepository;
import com.example.RaktSeva.Entities.BloodBank;
import com.example.RaktSeva.Helper.Message;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    private BloodBankRepository bloodBankRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "RaktSeva");
        return "home"; // Assuming "home" is the name of the home page template
    }

    @GetMapping("/about")
    public String aboutPageHandler(Model model) {
        model.addAttribute("title", "About Page");
        return "users/about";
    }

    @GetMapping("/signup")
    public String bloodBankSignUpPageHandler(Model model, HttpSession session) {
        model.addAttribute("title", "Sign Up");
        model.addAttribute("bank", new BloodBank());

        if (session.getAttribute("message") != null) {
            model.addAttribute("sessionMessage", session.getAttribute("message"));
            session.removeAttribute("message");
        }
        return "users/signup";
    }

    @GetMapping("/signin")
    public String loginPageHandler(Model model, HttpSession session) {
        model.addAttribute("title", "Log In");
        return "users/login";
    }

    @GetMapping("/search")
    public String searchPageHandler(Model model) {
        model.addAttribute("title", "Search Blood Banks");
        model.addAttribute("banks", new BloodBank());
        return "users/search";
    }

    @PostMapping("/search-process")
    public String searchProcessHandler(
            @RequestParam("group") String group,
            @RequestParam("city") String city,
            @RequestParam("district") String district,
            @RequestParam("state") String state,
            Model model) {
        System.out.println("Blood group: " + group);
        System.out.println("City: " + city);
        System.out.println("District: " + district);
        System.out.println("State: " + state);

        model.addAttribute("title", "Search Blood Banks");

        List<BloodBank> banks = this.bloodBankRepository.findBloodBanksByBloodGroupAndLocation(group, city, district,
                state);

        // List<BloodBank> banks =
        // this.bloodBankRepository.findBloodBanksByBloodGroup(group);

        // print the banks list to the console
        for (BloodBank bank : banks) {
            System.out.println(bank);
        }

        model.addAttribute("banks", banks);
        return "users/result";
    }

    @GetMapping("/result")
    public String searchResultPageHandler(Model model) {
        model.addAttribute("title", "Blood Banks");
        return "users/result";
    }

    // register_form
    @PostMapping("/register_form")
    public String resgisterFormHandler(
            @Valid @ModelAttribute("bank") BloodBank bank,
            BindingResult result,
            @RequestParam("bankProfile") MultipartFile file,
            @RequestParam(value = "agreed", defaultValue = "false") boolean agreed,
            Model model,
            HttpSession session) {
        try {
            if (result.hasErrors()) {
                System.out.println(result.toString());
                model.addAttribute("bank", bank);
                return "signup";
            }
            if (!agreed) {
                System.out.println("Not Agreed to the T&C.");
                throw new Exception("Not Agreed to the T&C.");
            }
            if (file.isEmpty()) {
                // If the file is empty then send error message
                System.out.println("File not found");
                bank.setImage("default.jpeg");
            } else {
                // save the file to folder and update the name to contact
                bank.setImage(file.getOriginalFilename());
                File saveFile = new ClassPathResource("static/images").getFile();

                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename());
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

                System.out.println("Image is uploaded");

            }
            bank.setRole("ROLE_USER");
            bank.setStatusEnabled(true);
            // user.setImage("default.png");
            bank.setPassword(passwordEncoder.encode(bank.getPassword()));

            BloodBank dbUser = this.bloodBankRepository.save(bank);
            model.addAttribute("user", dbUser);
            System.out.println(dbUser);
            session.setAttribute("message", new Message("Successfully Registered", "alert-success"));
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("message",
                    new Message("Something Went Wrong\n"
                            + (e.getMessage().length() > 100 ? e.getMessage().substring(0, 100) : e.getMessage()),
                            "alert-danger"));
            return "users/signup";
        }
        return "users/login";
    }

}
