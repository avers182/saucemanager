package ru.sav.saucemanager.controller;

import ru.sav.saucemanager.controller.form.DatePickForm;
import ru.sav.saucemanager.model.QtiResultSet;
import ru.sav.saucemanager.model.RepositoryEntry;
import ru.sav.saucemanager.repositories.IdentityRepository;
import ru.sav.saucemanager.repositories.QtiResultSetRepository;
import ru.sav.saucemanager.repositories.RepositoryEntryRepository;
import ru.sav.saucemanager.repositories.UserPropertyRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

    @RequestMapping("/{id}/tests")
    public @ResponseBody List<RepositoryEntry> viewTests(@PathVariable Long id, Model model) {
        return repositoryEntryRepository.findReferences(id);
    }

    @RequestMapping("/{id}/userproperties")
    public @ResponseBody List<Object[]> viewUserProperties(@PathVariable Long id, Model model) {
        return userPropertyRepository.findByRepositoryEntryId(id);
    }

    @RequestMapping("/{id}/qtiresultsets")
    public @ResponseBody List<QtiResultSet> viewQtiResultSets(@PathVariable Long id, Model model) {
        return qtiResultSetRepository.findByRepositoryEntryId(id);
    }

}
