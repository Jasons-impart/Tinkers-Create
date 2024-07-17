package com.jasonsimpart.tinkerscreate.modifiers;

import com.jasonsimpart.tinkerscreate.Utils;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileLaunchModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.tools.stats.ToolType;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.tools.Tool;
import java.util.List;


@ParametersAreNonnullByDefault
public class ColdBloodModifier extends NoLevelsModifier implements MeleeDamageModifierHook, TooltipModifierHook, ProjectileHitModifierHook {

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.MELEE_DAMAGE, ModifierHooks.TOOLTIP, ModifierHooks.PROJECTILE_HIT);
    }

    @Override
    // 设定priority以确定作用顺序
    public int getPriority() {
        return 51;
    }

    @Override
    public float getMeleeDamage(IToolStackView tool, ModifierEntry modifier, ToolAttackContext Context, float baseDamage, float damage) {
        // 获取modifier等级
        float level = modifier.getEffectiveLevel();
        // 获取被攻击生物
        var target = Context.getLivingTarget();
        if (target == null)
            return damage;
        // 判断满血与否
        if (target.getHealth() == target.getMaxHealth()) {
            // 50%面板伤害加成
            return damage + baseDamage * 0.5F;
        }
        return damage;
    }

    public boolean onProjectileHitEntity(ModifierNBT modifiers, NamespacedNBT persistentData, ModifierEntry modifier, Projectile projectile, EntityHitResult hit, @javax.annotation.Nullable LivingEntity attacker, @javax.annotation.Nullable LivingEntity target) {
        // 获取Modifier等级
        float level = modifier.getEffectiveLevel();
        // 判断是否为ServerPlayer以及target是否存在、projectile是否为arrow、判断满血与否
        if (target != null && projectile instanceof AbstractArrow arrow && attacker instanceof ServerPlayer player && target.getHealth() == target.getMaxHealth()) {
            // 50%面板伤害加成
            arrow.setBaseDamage(arrow.getBaseDamage() * 1.5);
        }
        return false;
    }

    // 添加TOOLTIP
    public ToolType[] TYPES = new ToolType[]{ToolType.MELEE};
    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, TooltipKey key, TooltipFlag tooltipFlag) {
        ToolType type = ToolType.from(tool.getItem(), TYPES);
        if (type != null && player != null) {
            double bonus = 0.5 * Utils.getBaseDamage(player, tool);
            double ranged_multiply = 0.5;
            // 判断是否存在bonus，若存在则显示
            if (bonus > 0.0) {
                // 添加到hook里
                TooltipModifierHook.addPercentBoost(this, TooltipModifierHook.statName(this, ToolStats.PROJECTILE_DAMAGE), ranged_multiply, tooltip);
                TooltipModifierHook.addFlatBoost(this, TooltipModifierHook.statName(this, ToolStats.ATTACK_DAMAGE), bonus, tooltip);
            }
        }
    }

}
