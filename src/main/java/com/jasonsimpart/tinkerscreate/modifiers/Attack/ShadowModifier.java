package com.jasonsimpart.tinkerscreate.modifiers.Attack;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.tools.stats.ToolType;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
public class ShadowModifier extends Modifier implements MeleeDamageModifierHook, TooltipModifierHook, ProjectileHitModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.MELEE_DAMAGE, ModifierHooks.TOOLTIP, ModifierHooks.PROJECTILE_HIT);
    }

    @Override
    public int getPriority() {
        return 200;
    }

    @Override
    public float getMeleeDamage(IToolStackView iToolStackView, ModifierEntry modifierEntry, ToolAttackContext toolAttackContext, float baseDamage, float damage) {
        var level = modifierEntry.getLevel();
        var attacker = toolAttackContext.getAttacker();
        var attackerLevel = attacker.level;
        var pos = attacker.blockPosition();
        if (!attackerLevel.isRainingAt(pos) &&
            attackerLevel.getMaxLocalRawBrightness(pos) > 7)
            return damage;
        return damage * (float) (1 + 0.3 * level);
    }

    public boolean onProjectileHitEntity(ModifierNBT modifiers, NamespacedNBT persistentData, ModifierEntry modifier, Projectile projectile, EntityHitResult hit, @Nullable LivingEntity attacker, @Nullable LivingEntity target) {
        // 获取Modifier等级
        float level = modifier.getEffectiveLevel();
        if (attacker == null)
            return false;
        var attackerLevel = attacker.level;
        var pos = attacker.blockPosition();
        if (!attackerLevel.isRainingAt(pos) &&
                attackerLevel.getMaxLocalRawBrightness(pos) > 7)
            return false;
        // 判断是否为ServerPlayer以及target是否存在、projectile是否为arrow
        if (target != null && projectile instanceof AbstractArrow arrow && attacker instanceof ServerPlayer player) {
            // 0.3 * level面板伤害加成
            arrow.setBaseDamage(arrow.getBaseDamage() * (float) (1 + 0.3 * level));
        }
        return false;
    }


    // 添加TOOLTIP
    public ToolType[] TYPES = new ToolType[]{ToolType.MELEE, ToolType.RANGED};
    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, TooltipKey key, TooltipFlag tooltipFlag) {
        ToolType type = ToolType.from(tool.getItem(), TYPES);
        float level = modifier.getEffectiveLevel();
        if (type != null && player != null) {
            double multiply = 0.3 * level;
            // 判断是否存在bonus，若存在则显示
            if (multiply > 0.0) {
                // 添加到hook里
                TooltipModifierHook.addPercentBoost(this, TooltipModifierHook.statName(this, ToolStats.ATTACK_DAMAGE), multiply, tooltip);
            }
        }
    }
}
