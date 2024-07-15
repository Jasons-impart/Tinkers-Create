package com.jasonsimpart.tinkerscreate.hooks;

import com.jasonsimpart.tinkerscreate.Utils;
import net.minecraft.resources.ResourceLocation;
import slimeknights.mantle.data.registry.IdAwareComponentRegistry;
import slimeknights.tconstruct.library.module.ModuleHook;

public class TinkersCreateModifierHooks {
    public static final IdAwareComponentRegistry<ModuleHook<?>> LOADER = new IdAwareComponentRegistry<>("Unknown Modifier Hook");

    public static final ModuleHook<LivingDeathModifierHook> LIVING_DEATH =
            LOADER.register(new ModuleHook<>(Utils.getResource("living_death"), LivingDeathModifierHook.class, LivingDeathModifierHook.AllMerger::new, (tool, modifier, entity, cancelled) -> false));
}
