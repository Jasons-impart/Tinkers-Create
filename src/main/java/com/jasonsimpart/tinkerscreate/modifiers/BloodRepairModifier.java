package com.jasonsimpart.tinkerscreate.modifiers;

import com.jasonsimpart.tinkerscreate.hooks.TargetDeathModifierHook;
import com.jasonsimpart.tinkerscreate.hooks.TinkersCreateModifierHooks;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.tools.stats.ToolType;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
public class BloodRepairModifier extends Modifier implements TargetDeathModifierHook, TooltipModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, TinkersCreateModifierHooks.TARGET_DEATH, ModifierHooks.TOOLTIP);
    }

    @Override
    public void onTargetDeath(IToolStackView tool, ModifierEntry modifier, LivingEntity targetEntity) {
        // 获取modifier等级
        float level = modifier.getEffectiveLevel();
        // 随机数实现概率(精度为5%)
        int randomInt = (int) Math.floor(Math.random() * 20);
        // 防止判空
        if (targetEntity == null){}
        // 杀死生物恢复期望为0.15 * (1 + 6 * level)
        if (randomInt <= 3) {
            ToolDamageUtil.repair(tool, 1 + (int) (6 * level));
        }
    }

    // 添加TOOLTIP
    public ToolType[] TYPES = new ToolType[]{ToolType.MELEE, ToolType.RANGED};
    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, TooltipKey key, TooltipFlag tooltipFlag) {
        ToolType type = ToolType.from(tool.getItem(), TYPES);
        if (type != null) {
            int level = modifier.getLevel();
            float bonus = (float) (0.15 * (1 + level * 6));
            // 判断是否存在bonus，若存在则显示
            if (bonus > 0.0F) {
                // 添加到hook里
                TooltipModifierHook.addFlatBoost(this, TooltipModifierHook.statName(this, ToolStats.DURABILITY), (double)bonus, tooltip);
            }
        }
    }

}
