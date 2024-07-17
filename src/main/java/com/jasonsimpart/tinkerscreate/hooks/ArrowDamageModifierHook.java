package com.jasonsimpart.tinkerscreate.hooks;

import net.minecraft.world.entity.projectile.AbstractArrow;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.Collection;

public interface ArrowDamageModifierHook {
    double onArrowDamage(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, AbstractArrow arrow, double baseDamage);
    record AllMerger(Collection<ArrowDamageModifierHook> modules) implements ArrowDamageModifierHook {
        public Collection<ArrowDamageModifierHook> modules() {
            return this.modules;
        }
        @Override
        public double onArrowDamage(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, AbstractArrow arrow, double baseDamage) {
            for (var module : this.modules)
                baseDamage = module.onArrowDamage(tool, modifier, context, arrow,  baseDamage);
            return baseDamage;
        }
    }
}
