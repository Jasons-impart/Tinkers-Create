package com.jasonsimpart.tinkerscreate.modifiers;

import com.jasonsimpart.tinkerscreate.hooks.TargetDeathModifierHook;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class BloodRepairModifier extends Modifier implements TargetDeathModifierHook {
    @Override
    public void onTargetDeath(IToolStackView tool, ModifierEntry modifier, ToolAttackContext attackContext, int amount) {
        // 获取modifier等级
        float level = modifier.getEffectiveLevel();
        // 获取toolCurrentDurability
        int durability = tool.getCurrentDurability();
        // 获取攻击对象health
        float health = attackContext.getLivingTarget().getHealth();
        // 随机数实现概率(精度为5%)
        if (health <= 0 && durability != 0){
            int randomInt = (int) Math.floor(Math.random() * 20);
            if (randomInt <= 3) {
                ToolDamageUtil.repair(tool, (int) (10 * level));
            } else {
                ToolDamageUtil.repair(tool, (int) (2 * level));
            }
        }
    }
}
