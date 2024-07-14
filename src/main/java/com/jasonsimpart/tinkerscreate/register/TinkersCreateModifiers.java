package com.jasonsimpart.tinkerscreate.register;

import com.jasonsimpart.tinkerscreate.TinkersCreate;
import com.jasonsimpart.tinkerscreate.modifiers.SolidModifier;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;

public class TinkersCreateModifiers {
    public static ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(TinkersCreate.MODID);

    // Register Modifier
    public static StaticModifier<SolidModifier> solid = MODIFIERS.register("solid", SolidModifier::new);
}
