package com.jasonsimpart.tinkerscreate.modifiers.Tool;

import com.jasonsimpart.tinkerscreate.hooks.ShieldBlockModifierHook;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.helper.ToolAttackUtil;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class ReboundModifier extends Modifier implements ShieldBlockModifierHook {

    @Override
    public void onShieldBlocking(IToolStackView tool, ShieldBlockEvent event, Player player, DamageSource source, int level) {
        Entity entity = event.getDamageSource().getDirectEntity();
        if (entity instanceof LivingEntity target) {
            if (player.getUseItemRemainingTicks() > 71960) {
                ToolAttackUtil.extraEntityAttack(tool, player, player.getUsedItemHand(), target);
                ToolDamageUtil.damage(tool, 5, player, player.getUseItem());
            }
        }
    }
}
