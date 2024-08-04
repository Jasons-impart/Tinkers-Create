package com.jasonsimpart.tinkerscreate;

import com.jasonsimpart.tinkerscreate.datagen.TInkersCreateRecipes;
import com.jasonsimpart.tinkerscreate.datagen.TinkersCreateItemsTags;
import com.jasonsimpart.tinkerscreate.datagen.tinkers.TinkersCreateToolDefinitionDataProvider;
import com.jasonsimpart.tinkerscreate.datagen.tinkers.TinkersCreateToolSlotLayout;
import com.jasonsimpart.tinkerscreate.init.TinkersCreateItems;
import com.mojang.logging.LogUtils;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import slimeknights.tconstruct.library.client.model.tools.ToolModel;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;

@Mod(TinkersCreate.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TinkersCreate {
    public static final String MODID = "tinkerscreate";
    private static final Logger LOGGER = LogUtils.getLogger();

    public TinkersCreate() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        TinkersCreateItems.ITEMS.register(bus);


    }

    @SubscribeEvent
    public static void gatherData(final GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();

        boolean server = event.includeServer();
        boolean client = event.includeClient();

        generator.addProvider(server, new TinkersCreateToolDefinitionDataProvider(generator));
        generator.addProvider(server, new TinkersCreateToolSlotLayout(generator));
        generator.addProvider(server, new TInkersCreateRecipes(generator));
        generator.addProvider(server, new TinkersCreateItemsTags(generator, new BlockTagsProvider(generator, TinkersCreate.MODID, fileHelper), fileHelper));

    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = MODID, value = Dist.CLIENT)
    public static class ClientEvents {
        @SubscribeEvent
        public static void itemColors(RegisterColorHandlersEvent.Item event) {
            final ItemColors colors = event.getItemColors();

            ToolModel.registerItemColors(colors, TinkersCreateItems.SIGN_BATTLE) ;

        }
    }

}
