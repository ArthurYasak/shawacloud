package com.shawa.web;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.shawa.IngredientRef;
import com.shawa.Shawa;
import com.shawa.ShawaOrder;
import com.shawa.data.IngredientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import lombok.extern.slf4j.Slf4j;
import com.shawa.Ingredient;
import com.shawa.Ingredient.Type;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@RequestMapping("/design")
@SessionAttributes("shawaOrder")
// указывает, что объект ShawaOrder, объявленный в классе чуть ниже, должен поддерживаться на уровне сеанса. Это важно, потому что создание шаурмы также является первым шагом в создании заказа, и созданный нами заказ необходимо будет перенести в сеанс, охватывающий несколько запросов.
public class DesignShawaController {

    public IngredientRepository ingredientRepo;

    @Autowired
    public DesignShawaController(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {

        Iterable<Ingredient> ingredients = ingredientRepo.findAll();

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            // adding to model: <String(Type)> -> <Iterable<Ingredient>>
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

//    @ModelAttribute(name = "ingredientRef")
//    public IngredientRef ingredientRef() {
//        return new IngredientRef();
//    }

    @ModelAttribute(name = "shawaOrder")
    public ShawaOrder order() {
        return new ShawaOrder();
    }

    @ModelAttribute(name = "shawa")
    public Shawa shawa() {  // TODO: NOW мб здесь сохранять список выбранных ингредиентов?
        return new Shawa();     // moment when shawa is created and is put to model
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processShawa(@ModelAttribute @Valid Shawa shawa, Errors errors,
                               @ModelAttribute ShawaOrder shawaOrder
//                               @ModelAttribute IngredientRef ingredientRef,
//                               @ModelAttribute List<Ingredient> ingredients
    ) { // TODO: как сохранять IngredientRef
        if (errors.hasErrors()) {
            return "design";
        }
        shawa.setShawaOrder(shawaOrder);
        shawaOrder.addShawa(shawa);
//        ingredientRef.setShawa(shawa); // TODO NOW: как достать нужный List<IngredientRef>, в котором уже есть ссылки на ингредиенты
//        ingredientRef.setShawa(shawa);
//        ingredientRef.setIngredient();
        log.info("Processing shawarma: {}", shawa);
        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(
            Iterable<Ingredient> ingredients, Type type) {
//        List<Ingredient> ingredientList = (List) ingredients;
        return StreamSupport.stream(ingredients.spliterator(), false)
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}