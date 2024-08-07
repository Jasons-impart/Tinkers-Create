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
public class ExperienceKillerModifier extends Modifier implements MeleeDamageModifierHook, TooltipModifierHook, ProjectileHitModifierHook {

    // 注册HOOK
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.MELEE_DAMAGE, ModifierHooks.TOOLTIP, ModifierHooks.PROJECTILE_HIT);
    }
    @Override
    public int getPriority() {
        return 50;
    }
    // 修改伤害
    @Override
    public float getMeleeDamage(IToolStackView tool, ModifierEntry modifier, ToolAttackContext Context, float basedamage, float damage) {
        // 获取Modifier等级
        float level = modifier.getEffectiveLevel();
        // 判断是否为ServerPlayer
        if (!(Context.getPlayerAttacker() instanceof ServerPlayer attacker))
            return damage;
        // level + 0.3倍经验加成,设定最大值为20*level+100
        return damage + Math.min((level + attacker.experienceLevel * 0.3F), (100 + 20 * level));
    }

    public boolean onProjectileHitEntity(ModifierNBT modifiers, NamespacedNBT persistentData, ModifierEntry modifier, Projectile projectile, EntityHitResult hit, @javax.annotation.Nullable LivingEntity attacker, @javax.annotation.Nullable LivingEntity target) {
        // 获取Modifier等级
        float level = modifier.getEffectiveLevel();
        // 判断是否为ServerPlayer以及target是否存在、projectile是否为arrow
        if (target != null && projectile instanceof AbstractArrow arrow && attacker instanceof ServerPlayer player) {
            // 0.25 * (level + 0.3)倍经验加成,设定最大值为0.25 * (20 * level + 100)
            arrow.setBaseDamage(arrow.getBaseDamage() + 0.25 * Math.min((level + player.experienceLevel * 0.3F), (100 + 20 * level)));
        }
        return false;
    }

    // 添加TOOLTIP
    public ToolType[] TYPES = new ToolType[]{ToolType.MELEE, ToolType.RANGED};
    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, TooltipKey key, TooltipFlag tooltipFlag) {
        ToolType type = ToolType.from(tool.getItem(), TYPES);
        if (type != null) {
            int level = modifier.getLevel();
            float bonus = Math.min(level + player.experienceLevel * 0.3F, 100 + 20 * level);
            float ranged_bonus = (float) (0.25 * Math.min((level + player.experienceLevel * 0.3F), (100 + 20 * level)));
            // 判断是否存在bonus，若存在则显示
            if (bonus > 0.0F) {
                // 添加到hook里
                TooltipModifierHook.addFlatBoost(this, TooltipModifierHook.statName(this, ToolStats.PROJECTILE_DAMAGE), (double) ranged_bonus, tooltip);
                TooltipModifierHook.addFlatBoost(this, TooltipModifierHook.statName(this, ToolStats.ATTACK_DAMAGE), (double)bonus, tooltip);
            }
        }
    }
}
