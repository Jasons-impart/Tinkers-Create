package com.jasonsimpart.tinkerscreate.hooks;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.Collection;

public interface ShieldBlockModifierHook {
    void onShieldBlocking (IToolStackView tool, ShieldBlockEvent event, Player player, DamageSource source, int level);
    record AllMerger(Collection<ShieldBlockModifierHook> modules) implements ShieldBlockModifierHook {
        public Collection<ShieldBlockModifierHook> modules() {
            return this.modules;
        }
        @Override
        public void onShieldBlocking(IToolStackView tool, ShieldBlockEvent event, Player player, DamageSource source, int level) {
            for (var module : this.modules)
                module.onShieldBlocking(tool, event, player, source, level);
        }
    }
}
