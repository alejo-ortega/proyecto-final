package com.subasta2.Proyecto.Final.Egg.controllers;

import com.subasta2.Proyecto.Final.Egg.entities.Objects;
import com.subasta2.Proyecto.Final.Egg.services.ObjectsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/object")
@Controller
public class ObjectController {

    private ObjectsService objectService;

    @Autowired
    public ObjectController(ObjectsService objectService) {
        this.objectService = objectService;
    }

    @GetMapping
    public String showList(ModelMap model) {
        try {
            List<Objects> objects = objectService.showList();
            model.addAttribute("objects", objects);

        } catch (Exception e) {
            model.put("error", e.getMessage());
        }
        return "object/list-objects";
    }

    @GetMapping("/form")
    public String showForm(ModelMap model, @RequestParam(required = false) String id) {
        try {
            if (id == null) {
                model.addAttribute("object", new Objects());
            } else {
                model.addAttribute("object", objectService.showOne(id));
            }
        } catch (Exception e) {
            model.put("error", e.getMessage());
        }
        return "object/form";
    }

    @PostMapping("/form")
    public String processForm(@ModelAttribute Objects object, ModelMap model) {
        try {
            objectService.save(object);
        } catch (Exception e) {
            model.put("error", e.getMessage());
            return "object/form";
        }
        return "redirect:/object";
    }

    @GetMapping("/activate")
    public String activate(ModelMap model, @RequestParam String id) {
        try {
            model.addAttribute("object", objectService.showOne(id));
        } catch (Exception e) {
            model.put("error", e.getMessage());
        }
        return "object/list-objects";
    }

    @PostMapping("/activate")
    public String activatePost(ModelMap model, @RequestParam String id) {
        try {
            objectService.onOff(id);
        } catch (Exception e) {
            model.put("error", e.getMessage());
        }
        return "redirec:/object";
    }

    @GetMapping("/edit")
    public String edit(ModelMap model, String id) {
        try {
            Objects object = objectService.showOne(id);
            model.addAttribute("object", object);
        } catch (Exception e) {
            model.put("error", e.getMessage());
        }
        return "object/edit";
    }

    @PostMapping("/edit")
    public String editPost(ModelMap model, Objects object) {
        try {
            objectService.edit(object);
        } catch (Exception e) {
            model.put("object", object);
            model.put("error", e.getMessage());
            return "object/edit";
        }
        return "redirect:/object";
    }
}
