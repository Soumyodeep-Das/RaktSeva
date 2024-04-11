package com.example.RaktSeva.Controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.RaktSeva.DAO.BloodBankRepository;
import com.example.RaktSeva.DAO.BloodRepository;
import com.example.RaktSeva.Entities.Blood;
import com.example.RaktSeva.Entities.BloodBank;
import com.example.RaktSeva.Helper.Message;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/bank")
public class BankController {

    @Autowired
    private BloodBankRepository bloodBankRepository;

    @Autowired
    private BloodRepository bloodRepository;

    @Autowired
    private HttpSession session;

    // method to add common data to Response
    @ModelAttribute
    public void commonMethod(Model model, Principal principal) {
        String username = principal.getName();
        // System.out.println("User Email : "+username);

        BloodBank bank = bloodBankRepository.getUserByUserName(username);
        // System.out.println(user.toString());

        model.addAttribute("bank", bank);

        // to remove the session msg after showing it
        if (session != null)
            session.removeAttribute("message");
    }

    @GetMapping("/dashboard")
    public String userDashboardHandler(Model model, Principal principal) {
        model.addAttribute("title", "Bank Dashboard");
        return "bank/bank-dashboard";
    }

    // add new blood stock
    @GetMapping("/add-blood")
    public String addNewBlood(Model model) {
        model.addAttribute("title", "Add Blood Stock");
        model.addAttribute("blood", new Blood());
        return "bank/add-blood";
    }

    // save bloods
    @PostMapping("/process-blood")
    public String saveNewContacts(
            @ModelAttribute Blood blood,
            Principal principal,
            HttpSession session) {
        try {
            String name = principal.getName();
            BloodBank bank = this.bloodBankRepository.getUserByUserName(name);
            blood.setBloodBank(bank);
            bank.getBlood().add(blood);

            this.bloodBankRepository.save(bank);
            System.out.println(blood.toString());

            System.out.println("Added new Blood Stock Successfully!!");

            // send success msg
            session.setAttribute("message", new Message("New Blood Stock Added Successfully", "success"));
        } catch (Exception e) {
            e.printStackTrace();
            // ! send unsuccess msg
            session.setAttribute("message", new Message("Something Went Wrong!!\nTry Again.", "danger"));
        }
        return "bank/add-blood";
    }

    @GetMapping("/stock")
    public String stockUpdationPageHandler(Model model) {
        model.addAttribute("title", "Blood Stock Updation");
        // model.addAttribute("user", new User());

        // if (session.getAttribute("message") != null) {
        // model.addAttribute("sessionMessage", session.getAttribute("message"));
        // session.removeAttribute("message");
        // }
        return "bank/stock";
    }

    @GetMapping("/view-stocks/{page}")
    public String viewAllBlood(@PathVariable("page") Integer page, Model model, Principal principal) {
        model.addAttribute("title", "Blood Stock View");
        // *One type
        String name = principal.getName();
        // User user = this.userRepository.getUserByUserName(name);
        // List<Contacts> contactList = user.getContacts();
        // System.out.println(contactList);
        // model.addAttribute("contact-list", contactList);

        // * Another way */

        Pageable pageable = PageRequest.of(page, 5);

        BloodBank bank = this.bloodBankRepository.getUserByUserName(name);
        Page<Blood> bloods = this.bloodRepository.findBloodByBloodBank(bank.getId(), pageable);
        // System.out.println(contacts);
        model.addAttribute("bloods", bloods);
        model.addAttribute("currentpage", page);
        model.addAttribute("totalpages", bloods.getTotalPages());
        return "bank/view-stocks";
    }

    @PostMapping("/update/{id}")
    public String updateContactById(
            @PathVariable("id") Integer id,
            Model model,
            Principal principal) {
        System.out.println("Blood Id :" + id);

        Blood blood = this.bloodRepository.findById(id).get();

        // find how can access the contacts
        // String username = principal.getName();
        // User user = this.userRepository.getUserByUserName(username);

        model.addAttribute("blood", blood);
        // if (user.getUserId() == contact.getUser().getUserId()) {
        // model.addAttribute("contact", contact);
        // } else
        // return "users/no-contact";
        return "bank/update-blood";
    }

    // save edited contacts
    @PostMapping("/updated-blood")
    public String saveEditedContacts(
            @ModelAttribute Blood blood,
            Principal principal,
            HttpSession session) {
        try {
            // old contact details
            // Blood oldBlood = this.bloodRepository.findById(blood.getId()).get();

            BloodBank bank = this.bloodBankRepository.getUserByUserName(principal.getName());

            blood.setBloodBank(bank);

            this.bloodRepository.save(blood);
            // send success msg
            session.setAttribute("update", new Message("Blood Updated Successfully", "success"));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            // ! send unsuccess msg
            session.setAttribute("update", new Message("Something Went Wrong!!\nTry Again.", "danger"));
        }
        return "redirect:/bank/view-stocks/0";
    }

    @GetMapping("/view-by-group/{group}")
    public String viewByGroupPageHandler(
            @PathVariable("group") String group,
            Model model,
            Principal principal) {
        System.out.println("Blood group: " + group);
        String name = principal.getName();
        BloodBank bank = this.bloodBankRepository.getUserByUserName(name);
        List<Blood> bloods = this.bloodRepository.findByBloodGroupAndBloodBankId(group, bank.getId());

        model.addAttribute("bloods", bloods);
        return "bank/view-by-group";
    }

    @GetMapping("/profile")
    public String bankProfile() {
        return "bank/bank-dashboard";
    }

    @GetMapping("/not-found")
    public String pageNotFoundHandler() {
        return "bank/page-not-found";
    }

    // Delete user
    @GetMapping("/delete-stock/{id}")
    public String deleteContact(
            @PathVariable("id") Integer id,
            Model model,
            Principal principal,
            HttpSession session) throws IOException {
        Optional<Blood> bloodOptional = this.bloodRepository.findById(id);
        Blood blood = bloodOptional.get();

        // find how can access the contacts
        String username = principal.getName();
        BloodBank bank = this.bloodBankRepository.getUserByUserName(username);

        if (bank.getId() == blood.getBloodBank().getId()) {
            blood.setBloodBank(null);
            this.bloodRepository.delete(blood);
            session.setAttribute("delete",
                    new Message("Blood id " + blood.getId() + " is deleted.", "danger"));
        } else
            return "error";
        return "redirect:/bank/view-stocks/0";
    }

}
