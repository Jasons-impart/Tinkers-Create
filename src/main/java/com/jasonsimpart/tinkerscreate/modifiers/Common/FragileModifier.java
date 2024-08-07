package com.jasonsimpart.tinkerscreate.modifiers.Common;

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
public class FragileModifier extends Modifier implements ToolStatsModifierHook {
    // I'm Fragile, but not that Fragile
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.TOOL_STATS);
    }

    @Override
    public int getPriority() {
        return 51;
    }

    @Override
    public void addToolStats(IToolContext toolContext, ModifierEntry modifier, ModifierStatsBuilder builder) {
        float level = modifier.getEffectiveLevel();
        ToolStats.DURABILITY.multiply(builder, 1 - 0.2 * level);
    }
}
