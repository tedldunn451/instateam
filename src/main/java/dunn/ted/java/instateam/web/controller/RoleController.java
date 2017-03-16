package dunn.ted.java.instateam.web.controller;

import dunn.ted.java.instateam.model.Role;
import dunn.ted.java.instateam.service.RoleService;
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
 * Created by Ted on 3/16/2017.
 */
@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    // Form listing current roles and ability to add new role
    @RequestMapping("/roles")
    public String addRole(Model model) {


        List<Role> roles = roleService.findAll();

        model.addAttribute("newRole", new Role());
        model.addAttribute("roles", roles);
        model.addAttribute("action", "/roles");
        model.addAttribute("heading", "Roles");

        return "/role/add";
    }

    // Add a new project
    @RequestMapping(value = "/roles", method = RequestMethod.POST)
    public String addRole(@Valid Role role, BindingResult result, RedirectAttributes attributes) {

        if(result.hasErrors()) {
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.project", result);
            attributes.addFlashAttribute("project", role);

            return "redirect:/roles";
        }

        roleService.save(role);

        return "redirect:/roles";
    }

}