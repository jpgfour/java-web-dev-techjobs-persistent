package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.models.Employer;
import org.launchcode.javawebdevtechjobspersistent.models.Job;
import org.launchcode.javawebdevtechjobspersistent.models.Skill;
import org.launchcode.javawebdevtechjobspersistent.models.data.EmployerRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.JobRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    @Autowired
    private EmployerRepository employerRepository;

    //below = didn't tell me but we need it
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private SkillRepository skillRepository;

    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("title", "My Jobs");

        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        //adding in error catching in case trying to add a job without defined employers or skills to pick from (i.e. after table drop)
        //didn't work...following instructions and making sure to pre-populate employers and skills *before* adding jobs
//        if(errors.hasErrors()) {
//            model.addAttribute("title", "Add Job");
//            return "add";
//        } else {

            model.addAttribute("title", "Add Job");
            model.addAttribute(new Job());
            model.addAttribute("employers", employerRepository.findAll());
            //not sure this is needed but let's find out
            model.addAttribute("skills", skillRepository.findAll());
            return "add";
        }
    //}

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                       Errors errors, Model model, @RequestParam int employerId, @RequestParam List<Integer> skills) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            return "add";
        } else {
            //add employerId to newjob?
            Optional<Employer> foundEmployer = this.employerRepository.findById(employerId);
            Employer confirmedEmployer = (Employer)foundEmployer.get();
            newJob.setEmployer(confirmedEmployer);
            //emulate above employer process for skills
            List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
            newJob.setSkills(skillObjs);
            //save new job??? (yep)
            this.jobRepository.save(newJob);
            return "redirect:";
        }
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {

        Optional<Job> foundJob = this.jobRepository.findById(jobId);
        Job confirmedJob = (Job)foundJob.get();
        model.addAttribute("job",confirmedJob);
        return "view";
    }


}
