package com.jasonsimpart.tinkerscreate.events;

import com.jasonsimpart.tinkerscreate.TinkersCreate;
import com.jasonsimpart.tinkerscreate.hooks.TinkersCreateModifierHooks;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

@Mod.EventBusSubscriber(modid = TinkersCreate.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ServerEvents {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onLivingDeath(LivingDeathEvent event) {
        if (event.getSource() == DamageSource.OUT_OF_WORLD)
            return;
        var entity = event.getEntity();
        boolean cancelled = false;
        EquipmentContext context = new EquipmentContext(entity);
        for (var slotType : EquipmentSlot.values()) {
            if (ModifierUtil.validArmorSlot(entity, slotType)) {
                IToolStackView tool = context.getToolInSlot(slotType);
                if (tool != null && !tool.isBroken()) {
                    for (var entry : tool.getModifierList()) {
                        cancelled = entry.getHook(TinkersCreateModifierHooks.LIVING_DEATH).onLivingDeath(tool, entry, entity, cancelled);
                    }
                }
            }
        }
        if (cancelled) {
            event.setCanceled(true);
            entity.setHealth(1.0F);
        }

        var attackerEntity = event.getSource().getEntity();
        if (!event.isCanceled() && attackerEntity instanceof LivingEntity attacker) {
            EquipmentContext attackerContext = new EquipmentContext(attacker);
            for (var slotType : EquipmentSlot.values()) {
                IToolStackView tool = attackerContext.getToolInSlot(slotType);
                if (tool != null && !tool.isBroken()) {
                    for (var entry : tool.getModifierList()) {
                        entry.getHook(TinkersCreateModifierHooks.TARGET_DEATH).onTargetDeath(tool, entry, entity);
                    }
                }
            }
        }
    }
}
