package com.jasonsimpart.tinkerscreate.hooks;

import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.Collection;

public interface TargetDeathModifierHook {
    void onTargetDeath(IToolStackView tool, ModifierEntry modifier, LivingEntity targetEntity);

    record AllMerger(Collection<TargetDeathModifierHook> modules) implements TargetDeathModifierHook {
        public Collection<TargetDeathModifierHook> modules() {
            return this.modules;
        }
        @Override
        public void onTargetDeath(IToolStackView tool, ModifierEntry modifier, LivingEntity targetEntity) {
            for (var module : this.modules) {
                module.onTargetDeath(tool, modifier, targetEntity);
            }
        }
    }
}
