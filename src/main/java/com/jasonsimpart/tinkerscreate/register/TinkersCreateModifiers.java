package com.jasonsimpart.tinkerscreate.register;

import com.jasonsimpart.tinkerscreate.TinkersCreate;
import com.jasonsimpart.tinkerscreate.modifiers.*;
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
}
