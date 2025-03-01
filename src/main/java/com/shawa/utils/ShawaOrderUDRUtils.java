package com.shawa.utils;

import com.shawa.Shawa;
import com.shawa.ShawaUDT;

public class ShawaOrderUDRUtils {

    public static ShawaUDT toShawaUDT(Shawa shawa) {

        return ShawaUDT.builder()
                .name(shawa.getName())
                .ingredients(shawa.getIngredients())
                .build();
    }
}
