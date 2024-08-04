package com.jasonsimpart.tinkerscreate.datagen;

import com.jasonsimpart.tinkerscreate.TinkersCreate;
import com.jasonsimpart.tinkerscreate.init.TinkersCreateItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import slimeknights.tconstruct.library.data.recipe.IToolRecipeHelper;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class TInkersCreateRecipes extends RecipeProvider implements IToolRecipeHelper {

    public TInkersCreateRecipes(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(@Nonnull Consumer<FinishedRecipe> consumer) {
        String toolFolder = "tools/";
        toolBuilding(consumer, TinkersCreateItems.SIGN_BATTLE, toolFolder);

    }

    @Override
    public String getModId() {
        return TinkersCreate.MODID;
    }
}
