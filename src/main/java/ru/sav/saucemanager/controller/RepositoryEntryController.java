package ru.sav.saucemanager.controller;

import ru.sav.saucemanager.controller.form.DatePickForm;
import ru.sav.saucemanager.repositories.IdentityRepository;
import ru.sav.saucemanager.repositories.QtiResultSetRepository;
import ru.sav.saucemanager.repositories.RepositoryEntryRepository;
import ru.sav.saucemanager.repositories.UserPropertyRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Controller
@RequestMapping("/repositoryentry")
public class RepositoryEntryController {

    static Logger log = Logger.getLogger(RepositoryEntryController.class);

    @Autowired
    RepositoryEntryRepository repositoryEntryRepository;

    @Autowired
    UserPropertyRepository userPropertyRepository;

    @Autowired
    IdentityRepository identityRepository;

    @Autowired
    QtiResultSetRepository qtiResultSetRepository;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
    }

    @RequestMapping("/listcourses")
    public String listCourses(Model model) {
        model.addAttribute("repositoryEntries", repositoryEntryRepository.findAllCourses());
        return "repositoryentry/list";
    }

    @RequestMapping("/view/{id}")
    public String view(@PathVariable Long id, Model model) {
        model.addAttribute("datePickForm", new DatePickForm());
        model.addAttribute("repositoryEntry", repositoryEntryRepository.findOne(id));
        return "repositoryentry/view";
    }

    @RequestMapping("/view/{id}/maintable")
    public String viewMainTable(@PathVariable Long id, Model model) {
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        calendar.add(Calendar.MONTH, -1);
        Date dateFrom = calendar.getTime();

        model.addAttribute("datePickForm", new DatePickForm());
        model.addAttribute("tests", repositoryEntryRepository.findReferences(id));
        model.addAttribute("userProperties", userPropertyRepository.findByRepositoryEntryIdTimeBoundaries(id, dateFrom, now));
        model.addAttribute("qtiResultSets", qtiResultSetRepository.findByRepositoryEntryIdLaterThen(id, dateFrom));
        return "repositoryentry/view-maintable";
    }

    @RequestMapping(value = "/view/{id}/datepick", method = RequestMethod.POST)
    public String viewDatePick(@PathVariable Long id, @ModelAttribute("datePickForm")DatePickForm datePickForm, BindingResult bindingResult, Model model) {
        model.addAttribute("tests", repositoryEntryRepository.findReferences(id));
        model.addAttribute("userProperties",
                userPropertyRepository.findByRepositoryEntryIdTimeBoundaries(id, datePickForm.getDateFrom(), datePickForm.getDateTo()));
        model.addAttribute("qtiResultSets", qtiResultSetRepository.findByRepositoryEntryIdLaterThen(id, datePickForm.getDateFrom()));
        return "repositoryentry/view-maintable";
    }

}
