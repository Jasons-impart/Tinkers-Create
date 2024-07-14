package com.jasonsimpart.tinkerscreate.register;

import com.jasonsimpart.tinkerscreate.TinkersCreate;
import net.minecraft.resources.ResourceLocation;
import slimeknights.tconstruct.library.materials.definition.MaterialId;

public class TinkersCreateMaterials {
    public static MaterialId createMaterial(String name){
        return new MaterialId(new ResourceLocation(TinkersCreate.MODID, name));
    }

    // register materials
    public static final MaterialId SPACE_ALLOY = createMaterial("space_alloy");
}
