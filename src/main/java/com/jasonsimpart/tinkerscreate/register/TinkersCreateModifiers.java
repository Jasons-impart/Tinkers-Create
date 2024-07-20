package com.jasonsimpart.tinkerscreate.register;

import com.jasonsimpart.tinkerscreate.TinkersCreate;
import com.jasonsimpart.tinkerscreate.modifiers.Armor.HeavinessModifier;
import com.jasonsimpart.tinkerscreate.modifiers.Armor.PhoenixModifier;
import com.jasonsimpart.tinkerscreate.modifiers.Attack.*;
import com.jasonsimpart.tinkerscreate.modifiers.Common.BloodRepairModifier;
import com.jasonsimpart.tinkerscreate.modifiers.Common.FragileModifier;
import com.jasonsimpart.tinkerscreate.modifiers.Common.SolidModifier;
import com.jasonsimpart.tinkerscreate.modifiers.Tool.ReboundModifier;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;

public class TinkersCreateModifiers {
    public static ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(TinkersCreate.MODID);

    // Register Modifier
    public static StaticModifier<SolidModifier> solid = MODIFIERS.register("solid", SolidModifier::new);
    public static StaticModifier<FragileModifier> fragile = MODIFIERS.register("fragile", FragileModifier::new);
    public static StaticModifier<ExperienceKillerModifier> experiencekiller = MODIFIERS.register("experience_killer", ExperienceKillerModifier::new);
    public static StaticModifier<ColdBloodModifier> coldblood = MODIFIERS.register("cold_blood", ColdBloodModifier::new);
    public static StaticModifier<PhoenixModifier> phoenix = MODIFIERS.register("phoenix", PhoenixModifier::new);
    public static StaticModifier<BloodRepairModifier> bloodrepair = MODIFIERS.register("blood_repair", BloodRepairModifier::new);
    public static StaticModifier<RosePowerModifier> rosepower = MODIFIERS.register("rose_power", RosePowerModifier::new);
    public static StaticModifier<CuttingSteelModifier> cuttingsteel = MODIFIERS.register("cutting_steel", CuttingSteelModifier::new);
    public static StaticModifier<ShadowModifier> shadow = MODIFIERS.register("shadow", ShadowModifier::new);
    public static StaticModifier<ReboundModifier> rebound = MODIFIERS.register("rebound", ReboundModifier::new);
    public static StaticModifier<HeavinessModifier> heaviness = MODIFIERS.register("heaviness", HeavinessModifier::new);
}
