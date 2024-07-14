package com.jasonsimpart.tinkerscreate.register;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class TinkersCreateCreativeTabs {
    public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab("TinkersCreateItemGroup") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(TinkersCreateItems.SPACE_ALLOY_INGOT.get());
        }
    };
    public static final CreativeModeTab TOOL_GROUP = new CreativeModeTab("TinkersCreateToolGroup") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(TinkersCreateItems.SPACE_ALLOY_NUGGET.get());
        }
    };
}
