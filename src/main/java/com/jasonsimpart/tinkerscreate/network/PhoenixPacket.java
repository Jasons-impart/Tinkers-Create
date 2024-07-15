package com.jasonsimpart.tinkerscreate.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PhoenixPacket {
    public static final int ID = 0;

    public final int entityID;

    public PhoenixPacket(int entityID) {
        this.entityID = entityID;
    }

    public PhoenixPacket(Entity entity) {
        this.entityID = entity.getId();
    }

    public void process(Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
                ClientPacketHandler.handlePhoenixPacket(this, context);
            });
        });
        context.get().setPacketHandled(true);
    }

    public static void encode(PhoenixPacket msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.entityID);
    }

    public static PhoenixPacket decode(FriendlyByteBuf buf) {
        return new PhoenixPacket(buf.readInt());
    }
}
