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
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.tools.stats.ToolType;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
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
        entity.playSound();
        entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 400, 1));
        entity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 100, 1));
        entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 400));
        return true;
    }
    // 声明TOOLTYPE是ARMOR
    public ToolType[] TYPES = new ToolType[]{ToolType.ARMOR};
    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, TooltipKey key, TooltipFlag tooltipFlag) {
        // 传参
        ToolType type = ToolType.from(tool.getItem(), TYPES);
        // 判断是否存在对应type
        if (type != null) {
            int level = modifier.getLevel();
            // 计算Phoenix能够生效的次数(向下取整)
            double bonus =  Math.floor((double) tool.getCurrentDurability() /1000);
            // 判断是否bonus大于等于1，若存在则显示
            if (bonus >= 1.0F) {
                // 添加到hook里
                TooltipModifierHook.addFlatBoost(this, TooltipModifierHook.statName(this, ToolStats.ARMOR), bonus, tooltip);
            }
        }
    }
}
