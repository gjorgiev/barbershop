package com.example.barbershop.cryptoproject;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CryptoProjectController {

    private final CryptoProjectService cryptoProjectService;

    @GetMapping("/add-crypto-project")
    public String addCryptoProjectForm(Model model){
        model.addAttribute("cryptoProject", new CryptoProject());
        return "cryptoproject/add-crypto-project";
    }

    @PostMapping("/add-crypto-project")
    public String addCryptoProjectSubmit(@ModelAttribute CryptoProject cryptoProject, Model model) {
        cryptoProjectService.create(cryptoProject);
        model.addAttribute("cryptoProject", cryptoProject);
        return "cryptoproject/view-crypto-project";
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("cryptoProjectList", cryptoProjectService.list());
        return "index";
    }
}
