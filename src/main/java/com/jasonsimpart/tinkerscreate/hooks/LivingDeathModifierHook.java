package com.jasonsimpart.tinkerscreate.hooks;

import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.Collection;

public interface LivingDeathModifierHook {
    boolean onLivingDeath(IToolStackView tool, ModifierEntry modifier, LivingEntity entity, boolean cancelled);
    record AllMerger(Collection<LivingDeathModifierHook> modules) implements LivingDeathModifierHook {
        public Collection<LivingDeathModifierHook> modules() {
            return this.modules;
        }

        @Override
        public boolean onLivingDeath(IToolStackView tool, ModifierEntry modifier, LivingEntity entity, boolean cancelled) {
            for (var module : this.modules)
                cancelled = module.onLivingDeath(tool, modifier, entity, cancelled);
            return cancelled;
        }
    }
}
