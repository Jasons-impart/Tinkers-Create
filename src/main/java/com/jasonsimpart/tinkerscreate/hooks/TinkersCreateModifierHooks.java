package com.jasonsimpart.tinkerscreate.hooks;

import com.jasonsimpart.tinkerscreate.Utils;
import net.minecraft.resources.ResourceLocation;
import slimeknights.mantle.data.registry.IdAwareComponentRegistry;
import slimeknights.tconstruct.library.modifiers.hook.behavior.ToolDamageModifierHook;
import slimeknights.tconstruct.library.module.ModuleHook;

import java.util.function.Function;

public class TinkersCreateModifierHooks {
    public static final IdAwareComponentRegistry<ModuleHook<?>> LOADER = new IdAwareComponentRegistry<>("Unknown Modifier Hook");

    public static final ModuleHook<LivingDeathModifierHook> LIVING_DEATH =
            LOADER.register(new ModuleHook<>(Utils.getResource("living_death"), LivingDeathModifierHook.class, LivingDeathModifierHook.AllMerger::new, (tool, modifier, entity, cancelled) -> false));
    public static final ModuleHook<TargetDeathModifierHook> TARGET_DEATH =
            LOADER.register(new ModuleHook<>(Utils.getResource("target_death"), TargetDeathModifierHook.class, TargetDeathModifierHook.AllMerger::new, (tool, modifier, targetEntity) -> {}));
    public static final ModuleHook<ArrowDamageModifierHook> ARROW_DAMAGE =
            LOADER.register(new ModuleHook<>(Utils.getResource("arrow_damage"), TargetDeathModifierHook.class, ArrowDamageModifierHook.AllMerger::new, (tool, modifier, context, arrow, baseDamage) -> {return (double) baseDamage}));
}