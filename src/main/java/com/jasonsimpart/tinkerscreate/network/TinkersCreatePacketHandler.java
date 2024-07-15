package com.jasonsimpart.tinkerscreate.network;

import com.jasonsimpart.tinkerscreate.TinkersCreate;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class TinkersCreatePacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(TinkersCreate.MODID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void init() {
        INSTANCE.registerMessage(PhoenixPacket.ID, PhoenixPacket.class,
                PhoenixPacket::encode, PhoenixPacket::decode, PhoenixPacket::process);
    }
}
