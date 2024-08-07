package com.jasonsimpart.tinkerscreate;

import com.jasonsimpart.tinkerscreate.network.TinkersCreatePacketHandler;
import com.jasonsimpart.tinkerscreate.register.TinkersCreateBlocks;
import com.jasonsimpart.tinkerscreate.register.TinkersCreateItems;
import com.jasonsimpart.tinkerscreate.register.TinkersCreateMaterials;
import com.jasonsimpart.tinkerscreate.register.TinkersCreateModifiers;
import com.mojang.logging.LogUtils;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(TinkersCreate.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TinkersCreate {
    public static final String MODID = "tinkerscreate";
    private static final Logger LOGGER = LogUtils.getLogger();

    public TinkersCreate() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        TinkersCreateItems.ITEMS.register(bus);
        TinkersCreateBlocks.BLOCKS.register(bus);
        TinkersCreateModifiers.MODIFIERS.register(bus);

        TinkersCreatePacketHandler.init();
    }

    @SubscribeEvent
    public static void gatherData(final GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        if (event.includeClient()) {}
        if (event.includeServer()) {}
    }

}
