package dunn.ted.java.instateam.web.controller;

import dunn.ted.java.instateam.model.Collaborator;
import dunn.ted.java.instateam.service.CollaboratorService;
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

/**
 * Created by Ted on 3/19/2017.
 */
@Controller
public class CollaboratorController {

    @Autowired
    private CollaboratorService collaboratorService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("/collaborator/add")
    public String addCollaborator(Model model) {

        model.addAttribute("newCollaborator", new Collaborator());
        model.addAttribute("collaborators", collaboratorService.findAll());
        model.addAttribute("roles", roleService.findAll());

        return "add-collaborator";
    }

    @RequestMapping(value="/collaborator/add", method= RequestMethod.POST)
    public String addRole(@Valid Collaborator collaborator, BindingResult result, RedirectAttributes redirectAttributes) {

        if(result.hasErrors()) {
            // Include validation errors upon redirect
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.category", result);

            // Add  category if invalid was received
            redirectAttributes.addFlashAttribute("collaborator", collaborator);

            // Redirect back to the form
            return "redirect:/collaborator/add";
        }

        collaboratorService.save(collaborator);

        redirectAttributes.addFlashAttribute("flash",new FlashMessage("Collaborator successfully added!", FlashMessage.Status.SUCCESS));

        return "redirect:/collaborator/add";
    }

}
