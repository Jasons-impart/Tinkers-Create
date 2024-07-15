package com.jasonsimpart.tinkerscreate;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class Utils {
    public static double getBaseDamage(Player player, IToolStackView tool) {
        if (player instanceof ServerPlayer serverPlayer) {
            return serverPlayer.getAttribute(Attributes.ATTACK_DAMAGE).getBaseValue();
        } else {
            double damage = Attributes.ATTACK_DAMAGE.getDefaultValue();
            player = Minecraft.getInstance().player;
            if (player != null) {
                AttributeInstance instance = player.getAttribute(Attributes.ATTACK_DAMAGE);
                if (instance != null) {
                    damage = instance.getBaseValue();
                }
            }
            return damage + ((Number)tool.getStats().get(ToolStats.ATTACK_DAMAGE)).floatValue();
        }
    }

    public static ResourceLocation getResource(String name) {
        return new ResourceLocation(TinkersCreate.MODID, name);
    }
}
