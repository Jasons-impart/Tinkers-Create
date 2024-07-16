package com.jasonsimpart.tinkerscreate.modifiers;

import com.jasonsimpart.tinkerscreate.Utils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.tools.stats.ToolType;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
public class ShadowModifier extends Modifier implements MeleeDamageModifierHook, TooltipModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.MELEE_DAMAGE, ModifierHooks.TOOLTIP);
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
        return damage + 5 * level;
    }

    // 添加TOOLTIP
    public ToolType[] TYPES = new ToolType[]{ToolType.MELEE};
    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, TooltipKey key, TooltipFlag tooltipFlag) {
        ToolType type = ToolType.from(tool.getItem(), TYPES);
        float level = modifier.getEffectiveLevel();
        if (type != null && player != null) {
            double bonus = 5 * level;
            // 判断是否存在bonus，若存在则显示
            if (bonus > 0.0) {
                // 添加到hook里
                TooltipModifierHook.addFlatBoost(this, TooltipModifierHook.statName(this, ToolStats.ATTACK_DAMAGE), bonus, tooltip);
            }
        }
    }


}
