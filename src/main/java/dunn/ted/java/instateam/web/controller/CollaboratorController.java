package dunn.ted.java.instateam.web.controller;

import dunn.ted.java.instateam.model.Collaborator;
import dunn.ted.java.instateam.service.CollaboratorService;
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
public class CollaboratorController {

    @Autowired
    private CollaboratorService collaboratorService;

    // Form listing current collaborators and ability to add new collaborators
    @RequestMapping("/collaborators")
    public String addCollaborator(Model model) {

        List<Collaborator> collaborators = collaboratorService.findAll();

        model.addAttribute("collaborator", new Collaborator());
        model.addAttribute("collaborators", collaborators);
        model.addAttribute("action", "/collaborator/add");
        model.addAttribute("heading", "Collaborators");
        model.addAttribute("submit", "Add");

        return "/collaborator/add";
    }

    // Add a new collaborator
    @RequestMapping(value = "/collaborator/add", method = RequestMethod.POST)
    public String addCollaborator(@Valid Collaborator collaborator, BindingResult result, RedirectAttributes attributes) {

        if(result.hasErrors()) {
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.project", result);
            attributes.addFlashAttribute("project", collaborator);

            return "redirect:/collaborators";
        }

        collaboratorService.save(collaborator);

        return "redirect:/collaborators";
    }
}
