package com.jasonsimpart.tinkerscreate.modifiers;

import com.jasonsimpart.tinkerscreate.hooks.TargetDeathModifierHook;
import com.jasonsimpart.tinkerscreate.hooks.TinkersCreateModifierHooks;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class BloodRepairModifier extends Modifier implements TargetDeathModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, TinkersCreateModifierHooks.TARGET_DEATH);
    }

    @Override
    public void onTargetDeath(IToolStackView tool, ModifierEntry modifier, LivingEntity targetEntity) {
        // 获取modifier等级
        float level = modifier.getEffectiveLevel();
        // 随机数实现概率(精度为5%)
        int randomInt = (int) Math.floor(Math.random() * 20);
        if (randomInt <= 3) {
            ToolDamageUtil.repair(tool, 1 + (int) (10 * level));
        } else {
            ToolDamageUtil.repair(tool, 1 + (int) (2 * level));
        }
    }
}
