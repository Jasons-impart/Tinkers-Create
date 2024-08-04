package com.jasonsimpart.tinkerscreate.datagen.tinkers;

import com.jasonsimpart.tinkerscreate.TinkersCreate;
import com.jasonsimpart.tinkerscreate.init.TinkersCreateItems;
import net.minecraft.data.DataGenerator;
import slimeknights.tconstruct.library.data.tinkering.AbstractToolDefinitionDataProvider;
import slimeknights.tconstruct.library.materials.RandomMaterial;
import slimeknights.tconstruct.library.tools.definition.module.material.DefaultMaterialsModule;
import slimeknights.tconstruct.library.tools.definition.module.material.PartStatsModule;
import slimeknights.tconstruct.tools.TinkerToolParts;

public class TinkersCreateToolDefinitionDataProvider extends AbstractToolDefinitionDataProvider {

    public TinkersCreateToolDefinitionDataProvider(DataGenerator generator) {
        super(generator, TinkersCreate.MODID);
    }

    @Override
    protected void addToolDefinitions() {
        RandomMaterial tier1Material = RandomMaterial.random().tier(1).build();
        DefaultMaterialsModule defaultTwoParts = DefaultMaterialsModule.builder().material(tier1Material, tier1Material).build();
        DefaultMaterialsModule defaultThreeParts = DefaultMaterialsModule.builder().material(tier1Material, tier1Material, tier1Material).build();
        DefaultMaterialsModule defaultFourParts = DefaultMaterialsModule.builder().material(tier1Material, tier1Material, tier1Material, tier1Material).build();

        define(TinkersCreateItems.SIGN_BATTLE)
                .module(PartStatsModule.parts()
                        .part(TinkersCreateItems.SIGN)
                        .part(TinkerToolParts.toolHandle)
                        .build())
                .module(defaultTwoParts);

    }

    @Override
    public String getName() {
        return "Tinkers Create Tool Definitions";
    }
}
