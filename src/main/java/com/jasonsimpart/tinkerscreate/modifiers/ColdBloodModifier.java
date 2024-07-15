package com.jasonsimpart.tinkerscreate.modifiers;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.tools.stats.ToolType;

import java.util.List;

public class ColdBloodModifier extends NoLevelsModifier implements MeleeDamageModifierHook, TooltipModifierHook {

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.MELEE_DAMAGE, ModifierHooks.TOOLTIP);
    }

    @Override
    // 设定priority以确定作用顺序
    public int getPriority() {
        return 51;
    }

    @Override
    public float getMeleeDamage(IToolStackView tool, ModifierEntry modifier, ToolAttackContext Context, float baseDamage, float damage) {
        // 获取baseDamage
        // 获取modifier等级
        float level = modifier.getEffectiveLevel();
        // 获取被攻击生物max_health
        float max_health = Context.getLivingTarget().getMaxHealth();
        // 判断是否为ServerPlayer
        if (!(Context.getPlayerAttacker() instanceof ServerPlayer attacker))
            return damage;
        // 判断满血与否
        if (max_health == Context.getLivingTarget().getHealth()) {
            return damage + baseDamage * 0.5F;// 50%面板伤害加成
        }
        return damage;
    }

    // 添加TOOLTIP
    public ToolType[] TYPES = new ToolType[]{ToolType.MELEE, ToolType.RANGED};
    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, TooltipKey key, TooltipFlag tooltipFlag) {
        ToolType type = ToolType.from(tool.getItem(), TYPES);
        if (type != null) {
            int level = modifier.getLevel();
            float bonus = (float) (0 * 0.5F);// 未实现传参！！！
            // 判断是否存在bonus，若存在则显示
            if (bonus > 0.0F) {
                // 添加到hook里
                TooltipModifierHook.addFlatBoost(this, TooltipModifierHook.statName(this, ToolStats.ATTACK_DAMAGE), (double)bonus, tooltip);
            }
        }
    }
}
