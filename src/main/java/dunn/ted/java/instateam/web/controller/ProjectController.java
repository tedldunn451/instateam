package dunn.ted.java.instateam.web.controller;

import dunn.ted.java.instateam.model.Collaborator;
import dunn.ted.java.instateam.model.Project;
import dunn.ted.java.instateam.service.CollaboratorService;
import dunn.ted.java.instateam.service.ProjectService;
import dunn.ted.java.instateam.service.RoleService;
import dunn.ted.java.instateam.web.FlashMessage;
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
 * Created by Ted on 3/19/2017.
 */
@Controller
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    CollaboratorService collaboratorService;

    @Autowired
    RoleService roleService;

    // Index and home page, lists all projects in database
    @RequestMapping("/")
    public String listProjects(Model model) {

        model.addAttribute("projects", projectService.findAll());

        return "index";
    }

    // Detail page for specific project
    @RequestMapping("{projectId}/detail")
    public String projectDetails(@PathVariable int projectId, Model model) {

        Project project = projectService.findById(projectId);
        List<Collaborator> collaborators = project.getCollaborators();

        model.addAttribute("project", project);
        model.addAttribute("collaborators", collaborators);

        return "project-details";
    }

    // Form to add a new project
    @RequestMapping("/project/add")
    public String formAddProject(Model model) {

        model.addAttribute("newProject", new Project());
        model.addAttribute("collaborators", collaboratorService.findAll());
        model.addAttribute("roles", roleService.findAll());

        return "add-project";
    }

    // Add a new project to the database
    @RequestMapping(value="/project/add", method= RequestMethod.POST)
    public String addProject(@Valid Project project, BindingResult result, RedirectAttributes redirectAttributes) {

        if(result.hasErrors()) {
            // Include validation errors upon redirect
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.category", result);

            // Add  project if invalid was received
            redirectAttributes.addFlashAttribute("project", project);

            // Redirect back to the form
            return "redirect:/project/add";
        }

        projectService.save(project);

        redirectAttributes.addFlashAttribute("flash",new FlashMessage("Project successfully added!", FlashMessage.Status.SUCCESS));

        return "redirect:/";
    }

    // Form to edit a project
    @RequestMapping("/{projectId}/edit-project")
    public String formEditProject(@PathVariable int projectId, Model model) {

        Project project = projectService.findById(projectId);

        model.addAttribute("project", project);
        model.addAttribute("roles", roleService.findAll());

        return "add-project";
    }



    // Form to edit project collaborators
    @RequestMapping("/{projectId}/edit-collaborators")
    public String formEditCollaborators(Model model) {

        model.addAttribute("collaborators", collaboratorService.findAll());

        return "add-collaborator";
    }

}
