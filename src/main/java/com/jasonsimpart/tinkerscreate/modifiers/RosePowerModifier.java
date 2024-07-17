package com.jasonsimpart.tinkerscreate.modifiers;

import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ToolStatsModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolContext;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class RosePowerModifier extends Modifier implements ToolStatsModifierHook{
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.TOOL_STATS);
    }
    @Override
    public int getPriority() {
        return 53;
    }

    @Override
    public void addToolStats(IToolContext tool, ModifierEntry modifier, ModifierStatsBuilder builder) {
        float level = modifier.getEffectiveLevel();
        ToolStats.MINING_SPEED.multiply(builder, 1 + 0.5 * level);
        ToolStats.ATTACK_SPEED.multiply(builder, 1 + 0.5 * level);
        ToolStats.VELOCITY.multiply(builder, 1 + 0.3 * level);
    }
}
