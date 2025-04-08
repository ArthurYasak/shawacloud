package com.shawa.utils;

import com.shawa.Shawa;
import com.shawa.ShawaUDT;
import org.springframework.stereotype.Component;

@Component
public class ShawaOrderUDRUtils {

    public static ShawaUDT toShawaUDT(Shawa shawa) {

        return ShawaUDT.builder()
                .name(shawa.getName())
                .ingredients(shawa.getIngredients())
                .build();
    }
}
