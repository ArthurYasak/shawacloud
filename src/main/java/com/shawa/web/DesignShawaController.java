package com.shawa.web;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

@Controller
@Slf4j
@RequestMapping("/design")
@SessionAttributes("shawaOrder")  // указывает, что объект ShawaOrder, объявленный в классе чуть ниже, должен поддерживаться на уровне сеанса. Это важно, потому что создание шаурмы также является первым шагом в создании заказа, и созданный нами заказ необходимо будет перенести в сеанс, охватывающий несколько запросов.
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
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "shawaOrder")
    public ShawaOrder order() {
        return new ShawaOrder();
    }

    @ModelAttribute(name = "shawa")
    public Shawa shawa() {
        return new Shawa();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processShawa(@ModelAttribute @Valid Shawa shawa, Errors errors,
                                                  @ModelAttribute ShawaOrder shawaOrder) {
        if (errors.hasErrors()) {
            return "design";
        }
        shawa.setShawaOrder(shawaOrder);
        shawaOrder.addShawa(shawa);
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