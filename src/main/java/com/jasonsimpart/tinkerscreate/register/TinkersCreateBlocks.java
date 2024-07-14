package com.jasonsimpart.tinkerscreate.register;

import com.jasonsimpart.tinkerscreate.TinkersCreate;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TinkersCreateBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TinkersCreate.MODID);
    // BLOCK.Properties
    private static final BlockBehaviour.Properties METAL = Block.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(5F, 1200f).sound(SoundType.METAL);
    private static final BlockBehaviour.Properties ORE = Block.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(2.5F, 5f).sound(SoundType.STONE);
    private static final BlockBehaviour.Properties END_ORE = Block.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3F, 5.5f).sound(SoundType.STONE);
    private static final BlockBehaviour.Properties DEEPSLATE_ORE = Block.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(2.5F, 5f).sound(SoundType.DEEPSLATE);

    // register
    public static RegistryObject<Block> SPACE_ALLOY_BLOCK =BLOCKS.register("space_alloy_block", () -> new Block(METAL));


}
