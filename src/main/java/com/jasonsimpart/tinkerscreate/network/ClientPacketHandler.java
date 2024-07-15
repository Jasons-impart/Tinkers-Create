package com.jasonsimpart.tinkerscreate.network;

import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ClientPacketHandler {
    public static void handlePhoenixPacket(PhoenixPacket phoenixMessage, Supplier<NetworkEvent.Context> context) {
        var minecraft = Minecraft.getInstance();
        if (minecraft.player == null)
            return;
        var entity = minecraft.player.level.getEntity(phoenixMessage.entityID);
        if (entity == null)
            return;
        minecraft.particleEngine.createTrackingEmitter(entity, ParticleTypes.TOTEM_OF_UNDYING, 30);
        minecraft.player.level.playLocalSound(entity.getX(), entity.getY(), entity.getZ(), SoundEvents.TOTEM_USE, entity.getSoundSource(), 1.0F, 1.0F, false);
    }
}
