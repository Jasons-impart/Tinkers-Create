package com.jasonsimpart.tinkerscreate.register;

import com.jasonsimpart.tinkerscreate.TinkersCreate;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TinkersCreateItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TinkersCreate.MODID);
    public static Item register_item() {
        return new Item(new Item.Properties().tab(TinkersCreateCreativeTabs.ITEM_GROUP));
    }
    private static final Item.Properties TOOL = new Item.Properties().stacksTo(1).tab(TinkersCreateCreativeTabs.TOOL_GROUP);
    private static final Item.Properties PARTS_PROPS = new Item.Properties().tab(TinkersCreateCreativeTabs.TOOL_GROUP);
    public static BlockItem register_block(Block block) {
        return new BlockItem(block, new Item.Properties().tab(TinkersCreateCreativeTabs.ITEM_GROUP));
    }
    // ingots
    public static RegistryObject<Item> SPACE_ALLOY_INGOT = ITEMS.register("space_alloy_ingot", TinkersCreateItems::register_item);
    public static RegistryObject<Item> HYPER_EXPERIENCE_INGOT = ITEMS.register("hyper_experience_ingot", TinkersCreateItems::register_item);
    // nuggets
    public static RegistryObject<Item> SPACE_ALLOY_NUGGET = ITEMS.register("space_alloy_nugget", TinkersCreateItems::register_item);
    // blocks
    public static RegistryObject<Item> SPACE_ALLOY_BLOCK = ITEMS.register("space_alloy_block", () -> register_block(TinkersCreateBlocks.SPACE_ALLOY_BLOCK.get()));
}
