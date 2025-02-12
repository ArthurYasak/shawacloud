package com.shawa.web;

import com.shawa.Ingredient;
import com.shawa.IngredientRef;
import com.shawa.Shawa;
import com.shawa.data.IngredientRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Component
@Service
public class IngredientRefByIdConverter implements Converter<String, IngredientRef> {

    IngredientRepository ingredientRepo;

    @Autowired
    public IngredientRefByIdConverter(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    // TODO: NOW (Try to Pass Data from Controller to Converter via a Service (gpt)

    @Override
    public IngredientRef convert(String id) {
        Ingredient ingredient = ingredientRepo.findById(id).orElse(null);
        IngredientRef ingredientRef = new IngredientRef();
        ingredientRef.setIngredient(ingredient);
//        ModelAndView modelAndView = new ModelAndView();
//        Map<String, Object> modelMap = modelAndView.getModel();
//        Shawa shawa = (Shawa) modelMap.get("shawa");
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//        Shawa shawa = (Shawa) request.getAttribute("shawa");
//        Model model = modelService.getModel();
//        Shawa shawa = (Shawa) model.getAttribute("shawa");
//        ingredientRef.setShawa();
        return ingredientRef;
        // TODO: как достать IngredientRefs (мб также доставать ingredient как и раньше, как он тогда подтянется в IngredientRef?)
    }

//    public abstract IngredientRef convert(String id, Model model); { todo: delete
//        Ingredient ingredient = ingredientRepo.findById(id).orElse(null);
//        IngredientRef ingredientRef = new IngredientRef();
//        ingredientRef.setIngredient(ingredient);
//        ingredientRef.setShawa((Shawa) model.getAttribute("shawa"));
//        return ingredientRef;
//    }
}
