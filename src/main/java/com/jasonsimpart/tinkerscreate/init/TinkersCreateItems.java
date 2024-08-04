package com.jasonsimpart.tinkerscreate.init;

import com.jasonsimpart.tinkerscreate.TinkersCreate;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import slimeknights.mantle.registration.object.ItemObject;
import slimeknights.mantle.util.SupplierCreativeTab;
import slimeknights.tconstruct.common.registration.ItemDeferredRegisterExtension;
import slimeknights.tconstruct.library.client.model.TinkerItemProperties;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;
import slimeknights.tconstruct.library.tools.part.ToolPartItem;
import slimeknights.tconstruct.tools.TinkerToolParts;
import slimeknights.tconstruct.tools.TinkerTools;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;

public class TinkersCreateItems {
    public static final ItemDeferredRegisterExtension ITEMS = new ItemDeferredRegisterExtension(TinkersCreate.MODID);

    public static final CreativeModeTab TAB_TOOL_PARTS = new SupplierCreativeTab(TinkersCreate.MODID, "tool_parts", () -> {
        return new ItemStack(TinkerToolParts.broadAxeHead.get());
    });

    public static final CreativeModeTab TAB_TOOLS = new SupplierCreativeTab(TinkersCreate.MODID, "tools", () -> new ItemStack(TinkerTools.handAxe.get()));

    private static final Item.Properties PARTS_PROPS = new Item.Properties().tab(TAB_TOOL_PARTS);

    private static final Item.Properties TOOL = new Item.Properties().stacksTo(1).tab(TAB_TOOLS);

    //parts
    public static final ItemObject<ToolPartItem> SIGN = ITEMS.register("sign", () -> new ToolPartItem(PARTS_PROPS, HeadMaterialStats.ID));

    //tools
    public static final ItemObject<ModifiableItem> SIGN_BATTLE = ITEMS.register("sign_battle", () -> new ModifiableItem(TOOL, TinkersCreateToolDefinitons.SIGN_BATTLE));
}

