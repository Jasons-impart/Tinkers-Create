package com.jasonsimpart.tinkerscreate.hooks;

import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.hook.build.ToolStatsModifierHook;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.Collection;
import java.util.Iterator;

public interface TargetDeathModifierHook {
    void onTargetDeath(IToolStackView var1, ModifierEntry var2, ToolAttackContext var3, int var4);

    record AllMerger(Collection<TargetDeathModifierHook> modules) implements TargetDeathModifierHook {
        public AllMerger(Collection<TargetDeathModifierHook> modules) {
            this.modules = modules;
        }

        @Override
        public void onTargetDeath(IToolStackView tool, ModifierEntry modifier, ToolAttackContext attackContext, int amount) {
            for (var module : this.modules) {
                module.onTargetDeath(tool, modifier, attackContext, amount);
            }
        }
        public Collection<TargetDeathModifierHook> modules() {
            return this.modules;
        }
    }
}
