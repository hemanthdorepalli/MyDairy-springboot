package com.example.demo.Controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.Repo.DataRepo;
import com.example.demo.Repo.UsersRepo;
import com.example.demo.entity.Users;
import com.example.demo.entity.data;
import com.example.demo.service.UsersService;

@Controller
//@RequestMapping("/entries")
public class HomeController {

    @Autowired
    private UsersRepo usersRepo;
    
    @Autowired
    private UsersService dataService;
    
    @Autowired
    private DataRepo dataRepo;

    // Method to get the current username
    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userDetails.getUsername();
        }
        return null;
    }

    @GetMapping({"", "/"})
    public String home(Model model) {
        String currentUsername = getCurrentUsername();

        if (currentUsername != null) {
            Users currentUser = usersRepo.findByUsername(currentUsername);

            if (currentUser != null) {
                model.addAttribute("currentName", currentUser.getName());
            }
        }

        return "index"; // Change this to your actual template name
    }
    
    @PostMapping("/data/add")
    public String addData(@RequestParam("entries") String entries, RedirectAttributes redirectAttributes) {

        String username = getCurrentUsername();

        if (username != null) {
            // Call the service to add data entry
            dataService.addData(entries, username);
            redirectAttributes.addFlashAttribute("message", "Data entry added successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "User not authenticated");
        }

        // Redirect to the home page
        return "redirect:/";
    }
    
    @GetMapping("/pages")
    public String getEntries(Model model) {
        String username = getCurrentUsername();  // Get the logged-in user's username

        if (username != null) {
            // Fetch entries for the logged-in user
            List<data> entries = dataRepo.findByUser_Username(username);
            model.addAttribute("entries", entries);
        } else {
            model.addAttribute("error", "User not authenticated");
        }

        return "pages"; 
    }
    
    
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        data entry = dataRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid entry Id:" + id));
        model.addAttribute("entry", entry);
        return "edit"; 
    }
    
    @PostMapping("/edit/{id}")
    public String updateEntry(@PathVariable int id, @RequestParam("entries") String entries, RedirectAttributes redirectAttributes) {
        data existingEntry = dataRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid entry Id:" + id));
        existingEntry.setEntries(entries); // Assuming 'entries' is a field in your `data` entity
        dataRepo.save(existingEntry);
        redirectAttributes.addFlashAttribute("message", "Entry updated successfully!");
        return "redirect:/pages"; // Redirect to the entries list page after editing
    }
    
    @PostMapping("/delete/{id}")
    public String deleteEntry(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            data entry = dataRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid entry Id:" + id));
            dataRepo.delete(entry);
            redirectAttributes.addFlashAttribute("message", "Entry deleted successfully!");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", "Entry not found");
        }
        return "redirect:/pages"; // Redirect to the entries list page after deletion
    }
    

}
