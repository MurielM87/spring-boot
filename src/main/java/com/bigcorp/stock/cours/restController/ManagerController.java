package com.bigcorp.stock.cours.restController;

import com.bigcorp.stock.cours.model.NewManager;
import com.bigcorp.stock.cours.service.ManagerNewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("coursManagerController")
@RequestMapping("/newmanagers")
public class ManagerController {

    @Autowired
    private ManagerNewService managerService;

    @GetMapping("/{id}")
    public NewManager getManager(@PathVariable("id") Long id) {
        NewManager manager = new NewManager();
//        manager.setId(id);
//        manager.setNom("Roger");
//        manager.setSalaire(3000);
        return manager;
    }

    @PostMapping
    public NewManager save(@RequestBody NewManager manager) {
        manager.setNom("Super");
        return manager;
    }


}
