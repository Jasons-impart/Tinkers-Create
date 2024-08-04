package com.jasonsimpart.tinkerscreate.datagen.tinkers;

import com.jasonsimpart.tinkerscreate.init.TinkersCreateItems;
import net.minecraft.data.DataGenerator;
import slimeknights.tconstruct.library.data.tinkering.AbstractStationSlotLayoutProvider;
import slimeknights.tconstruct.tools.TinkerToolParts;

import javax.annotation.Nonnull;

public class TinkersCreateToolSlotLayout extends AbstractStationSlotLayoutProvider {

    public TinkersCreateToolSlotLayout(DataGenerator generator){
        super(generator);
    }

    @Override
    protected void addLayouts() {
        defineModifiable(TinkersCreateItems.SIGN_BATTLE)
                .sortIndex(SORT_WEAPON)
                .addInputItem(TinkersCreateItems.SIGN.get(), 30, 36)
                .addInputItem(TinkerToolParts.toolHandle, 30, 52)
                .build();

    }

    @Nonnull
    @Override
    public String getName() {
        return "Tinkers Create Tool Slot Layout";
    }
}
