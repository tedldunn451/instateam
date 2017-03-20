package dunn.ted.java.instateam.web.controller;

import dunn.ted.java.instateam.model.Role;
import dunn.ted.java.instateam.service.RoleService;
import dunn.ted.java.instateam.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Ted on 3/19/2017.
 */
@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/role/add")
    public String listRole(Model model) {

        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        model.addAttribute("newRole", new Role());

        return "add-role";
    }

    @RequestMapping(value="/role/add", method= RequestMethod.POST)
    public String addRole(@Valid Role role, BindingResult result, RedirectAttributes redirectAttributes) {

        if(result.hasErrors()) {
            // Include validation errors upon redirect
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.category", result);

            // Add  category if invalid was received
            redirectAttributes.addFlashAttribute("role", role);

            // Redirect back to the form
            return "redirect:/role/add";
        }

        roleService.save(role);

        redirectAttributes.addFlashAttribute("flash",new FlashMessage("Role successfully added!", FlashMessage.Status.SUCCESS));

        return "redirect:/role/add";
    }

}
