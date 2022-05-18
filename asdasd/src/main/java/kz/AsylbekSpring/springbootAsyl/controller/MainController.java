package kz.AsylbekSpring.springbootAsyl.controller;


import kz.AsylbekSpring.springbootAsyl.db.Phone;
import kz.AsylbekSpring.springbootAsyl.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller

public class MainController {
    @Autowired
    private PhoneRepository phoneRepository;



    @GetMapping(value = "/")
    public String addItem(Model model){
        List<Phone> items = phoneRepository.findAll();
        model.addAttribute("additem",items);

        return "index";
    }

    @GetMapping(value = "/addphone")
    public String indexPage(Model model){

        List<Phone> phones = phoneRepository.findAll();
        model.addAttribute("addphone",phones);

        return "addphone";
    }
    @GetMapping(value = "/aplication")
    public String aplication(Model model){

        List<Phone> phones = phoneRepository.findAll();
        model.addAttribute("aplication",phones);

        return "aplication";
    }
    @GetMapping(value = "/login")
    public String login(Model model){
        return "login";
    }

    @GetMapping(value = "/profile")
    @PreAuthorize("isAuthenticated()")
    public String profile(Model model){
        return "profile";

    }
    @GetMapping(value = "/adminpanel")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String admin(Model model){
        return "adminpanel";

    }

    @GetMapping(value = "/moderatorpanel")
    @PreAuthorize("hasAnyRole('ROLE_MODERATOR')")
    public String moderator(Model model){
        return "moderatopanel";

    }


    @PostMapping(value = "/addphone")
    public String addItemPage(@RequestParam(name = "name") String name,
                              @RequestParam(name = "marka")String marka,
                              @RequestParam(name = "price")int price,
                              @RequestParam(name = "description")String description,
                              @RequestParam(name = "country")String country){

        Phone phone = new Phone();
        phone.setName(name);
        phone.setMarka(marka);
        phone.setPrice(price);
        phone.setDescription(description);
        phone.setCountry(country);
        phoneRepository.save(phone);

        return "redirect:/";
    }



    @GetMapping(value = "/details/{itemId}")
    public String itemDetails(
            @PathVariable(name = "itemId")Long id , Model model){
        Phone phone = phoneRepository.findById(id).orElse(null);
        model.addAttribute("home",phone);
        return "details";
    }




    @PostMapping(value = "/savephone")
    public String savePhone(@RequestParam(name = "id") Long id,
                           @RequestParam(name = "name") String name,
                           @RequestParam(name = "marka")String marka,
                           @RequestParam(name = "price")int price,
                           @RequestParam(name = "description")String description,
                           @RequestParam(name = "country")String country){

        Phone phone = phoneRepository.findById(id).orElse(null);

        if(phone!= null){
            phone.setName(name);
            phone.setMarka(marka);
            phone.setPrice(price);
            phone.setDescription(description);
            phone.setCountry(country);
            phoneRepository.save(phone);
            return "redirect:/addphone";
        }
        return "redirect:/";
    }

    @PostMapping(value = "/deletephone")
    public String deletePhone(@RequestParam(name = "id")Long id ){
        Phone phone = phoneRepository.findById(id).orElse(null);
        if(phone!=null){
            phoneRepository.delete(phone);
        }
        return "redirect:/";
    }

}
