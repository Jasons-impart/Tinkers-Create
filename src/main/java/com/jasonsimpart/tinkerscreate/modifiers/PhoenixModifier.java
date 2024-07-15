package com.jasonsimpart.tinkerscreate.modifiers;

import com.jasonsimpart.tinkerscreate.hooks.LivingDeathModifierHook;
import com.jasonsimpart.tinkerscreate.hooks.TinkersCreateModifierHooks;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;

public class PhoenixModifier extends NoLevelsModifier implements LivingDeathModifierHook, TooltipModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, TinkersCreateModifierHooks.LIVING_DEATH, ModifierHooks.TOOLTIP);
    }

    @Override
    public int getPriority() {
        return 10;
    }

    @Override
    public boolean onLivingDeath(IToolStackView tool, ModifierEntry modifier, LivingEntity entity, boolean cancelled) {
        if (cancelled)
            return true;
        if (tool.getCurrentDurability() < 1000)
            return false;

        tool.setDamage(tool.getDamage() + 1000);
        entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 400, 1));
        entity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 100, 1));
        entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 400));
        return true;
    }

    @Override
    public void addTooltip(IToolStackView iToolStackView, ModifierEntry modifierEntry, @Nullable Player player, List<Component> list, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {

    }
}
