package com.chuchu;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class TierOneToolMaterial implements ToolMaterial {
    public static final TierOneToolMaterial INSTANCE = new TierOneToolMaterial();


    @Override
    public int getDurability() {
        return 1000;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 10;
    }

    @Override
    public float getAttackDamage() {
        return 3;
    }

    @Override
    public int getMiningLevel() {
        return 4;
    }

    @Override
    public int getEnchantability() {
        return 22;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(CMMain.TOOII);
    }
}
