package com.example.barbershop.project;

import com.example.barbershop.common.CoinGeckoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/")
    public String index(Model model) throws CoinGeckoException {
        projectService.updateAllFromCoingecko();
        model.addAttribute("projects", projectService.list());
        return "index";
    }

}
