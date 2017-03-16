package dunn.ted.java.instateam.web.controller;

import dunn.ted.java.instateam.model.Collaborator;
import dunn.ted.java.instateam.model.Project;
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
 * Created by Ted on 3/13/2017.
 */
@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private CollaboratorService collaboratorService;

    @Autowired
    private RoleService roleService;

    // Index of projects
    @RequestMapping("/")
    public String projectIndex(Model model) {

        List<Project> projects = projectService.findAll();

        model.addAttribute("projects", projects);
        return "project/index";
    }

    // Project detail page
    @RequestMapping("/project/{projectId}")
    public String projectDetail(@PathVariable Long projectId, Model model) {

        Project project = projectService.findById(projectId);
        List<Collaborator> collaborators = project.getCollaborators();

        model.addAttribute("project", project);
        model.addAttribute("collaborators", collaborators);

        return "project/details";
    }

    // Form for adding a new project
    @RequestMapping("/project/add")
    public String formAddProject(Model model) {

        if(!model.containsAttribute("project")) {
            model.addAttribute("project", new Project());
        }

        model.addAttribute("action", "/project");
        model.addAttribute("heading", "New Project");
        model.addAttribute("submit", "Add");

        return "project/add_edit";
    }

    // Add a new project
    @RequestMapping(value = "/project", method = RequestMethod.POST)
    public String addProject(@Valid Project project, BindingResult result, RedirectAttributes attributes) {

        if(result.hasErrors()) {
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.project", result);
            attributes.addFlashAttribute("project", project);

            return "redirect:/project/add";
        }

        projectService.save(project);

        return "redirect:/";
    }

    // Form for editing an existing project
    @RequestMapping("/project/{projectId}/edit")
    public String formEditProject(@PathVariable Long projectId, Model model) {

        if(!model.containsAttribute("project")) {
            model.addAttribute("project", projectService.findById(projectId));
        }

        model.addAttribute("action", String.format("/project/%s/edit", projectId));
        model.addAttribute("heading", "Edit Project");
        model.addAttribute("submit", "Update");

        return "project/add_edit";
    }

    // Edit an existing project
    @RequestMapping(value = "/project/{projectId}/edit", method = RequestMethod.POST)
    public String editProject(@Valid Project project, BindingResult result, RedirectAttributes attributes) {

        if(result.hasErrors()) {
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.project", result);
            attributes.addFlashAttribute("project", project);

            return String.format("redirect:/project/%s/edit", project.getId());
        }

        projectService.save(project);

        return "redirect:/";
    }

}
