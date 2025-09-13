package org.codeberg.atomfrede.htmx_samples;


import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest;
import org.codeberg.atomfrede.htmx_samples.pagination.PagerModel;
import org.codeberg.atomfrede.htmx_samples.person.Person;
import org.codeberg.atomfrede.htmx_samples.person.PersonService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.FragmentsRendering;

@Controller
@RequestMapping("tables")
public class TablesController {

    private final PersonService personService;

    public TablesController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public String index(Model model, Pageable pageable, @RequestParam(required = false, defaultValue = "") String q) {
        Page<Person> page = personService.findAll(q, pageable);
        model.addAttribute("persons", page);
        model.addAttribute("pager", PagerModel.fromPage(page));
        model.addAttribute("q", q);

        return "table/index";
    }

    @GetMapping
    @HxRequest
    public View table(Model model, Pageable pageable, @RequestParam(required = false, defaultValue = "") String q) {
        Page<Person> page = personService.findAll(q, pageable);
        model.addAttribute("persons", page);
        model.addAttribute("pager", PagerModel.fromPage(page));
        model.addAttribute("q", q);

        return FragmentsRendering
                .with("table/table")
                .fragment("table/total-count")
                .build();
//        return "table/table";
    }
}
