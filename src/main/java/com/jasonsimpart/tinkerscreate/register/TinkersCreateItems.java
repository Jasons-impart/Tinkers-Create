package com.jasonsimpart.tinkerscreate.register;

import com.jasonsimpart.tinkerscreate.TinkersCreate;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;
import slimeknights.tconstruct.library.tools.part.ToolPartItem;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;

public class TinkersCreateItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TinkersCreate.MODID);

    private static final Item.Properties TOOL = new Item.Properties().stacksTo(1).tab(TinkersCreateCreativeTabs.TOOL_GROUP);
    private static final Item.Properties PARTS = new Item.Properties().tab(TinkersCreateCreativeTabs.TOOL_GROUP);
    private static final Item.Properties ITEM = new Item.Properties().tab(TinkersCreateCreativeTabs.ITEM_GROUP);

    // Tools
    public static RegistryObject<Item> BATTLE_SIGN = ITEMS.register("battle_sign", () -> new ModifiableItem(TOOL, TinkersCreateTools.BATTLE_SIGN));

    // Parts
    public static RegistryObject<Item> SIGN_PLATE = ITEMS.register("sign_plate", () -> new ToolPartItem(TOOL, HeadMaterialStats.ID));

    // Common
    public static RegistryObject<Item> SIGN_PLATE_SNAD_CAST = ITEMS.register("sign_plate_sand_cast", () -> new Item(ITEM));
    public static RegistryObject<Item> SIGN_PLATE_RED_SNAD_CAST = ITEMS.register("sign_plate_red_sand_cast", () -> new Item(ITEM));
    public static RegistryObject<Item> SIGN_PLATE_GOLD_CAST = ITEMS.register("sign_plate_gold_cast", () -> new Item(ITEM));

    // ingots
    public static RegistryObject<Item> SPACE_ALLOY_INGOT = ITEMS.register("space_alloy_ingot", () -> new Item(ITEM.fireResistant()));
    public static RegistryObject<Item> HYPER_EXPERIENCE_INGOT = ITEMS.register("hyper_experience_ingot", TinkersCreateItems::register_item);

    // nuggets
    public static RegistryObject<Item> SPACE_ALLOY_NUGGET = ITEMS.register("space_alloy_nugget", () -> new Item(ITEM.fireResistant()));

    // blocks
    public static RegistryObject<Item> SPACE_ALLOY_BLOCK = ITEMS.register("space_alloy_block", () -> new BlockItem(TinkersCreateBlocks.SPACE_ALLOY_BLOCK.get(), ITEM.fireResistant()));

    public static Item register_item() {
        return new Item(ITEM);
    }

    private static BlockItem register_block(Block block) {
        return new BlockItem(block, new Item.Properties().tab(TinkersCreateCreativeTabs.ITEM_GROUP));
    }

}
