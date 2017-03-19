package dunn.ted.java.instateam.web.controller;

import dunn.ted.java.instateam.model.Collaborator;
import dunn.ted.java.instateam.model.Project;
import dunn.ted.java.instateam.model.Role;
import dunn.ted.java.instateam.service.CollaboratorService;
import dunn.ted.java.instateam.service.ProjectService;
import dunn.ted.java.instateam.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private ProjectService projectService;

    @Autowired
    private RoleService roleService;

    // Form listing current collaborators and ability to add new collaborators
    @RequestMapping("/collaborator")
    public String listCollaborator(Model model) {

        model.addAttribute("newCollaborator", new Collaborator());
        model.addAttribute("collaborators", collaboratorService.findAll());
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("action", "/collaborator");
        model.addAttribute("heading", "Collaborators");
        model.addAttribute("submit", "Add");

        return "/collaborator/add";
    }

    // Add a new collaborator
    @RequestMapping(value = "/collaborator", method = RequestMethod.POST)
    public String addCollaborator(@Valid Collaborator collaborator, BindingResult result, RedirectAttributes attributes) {

        if(result.hasErrors()) {
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.collaborator", result);
            attributes.addFlashAttribute("collaborator", collaborator);

            return "redirect:/collaborator";
        }

        collaboratorService.save(collaborator);

        return "redirect:/collaborator";
    }

    @RequestMapping("/collaborator/{projectId}/edit")
    public String editCollaborators(@PathVariable Long projectId, Model model) {

        Project project = projectService.findById(projectId);
        List<Collaborator> collaborators = project.getCollaborators();

        model.addAttribute("project", project);
        model.addAttribute("collaborators", collaborators);
        model.addAttribute("roles", project.getRolesNeeded());
        model.addAttribute("action", String.format("/collaborator/{projectId}/edit", projectId));

        return "/collaborator/edit";
    }

    @RequestMapping(value = "/collaborator/{projectId}/edit", method = RequestMethod.POST)
    public String editCollaborator(@Valid Collaborator collaborator, BindingResult result, RedirectAttributes attributes) {

        if(result.hasErrors()) {
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.collaborator", result);
            attributes.addFlashAttribute("collaborator", collaborator);

            return "redirect:/collaborator/edit";
        }

        collaboratorService.save(collaborator);

        return "redirect:/collaborator";
    }

}
