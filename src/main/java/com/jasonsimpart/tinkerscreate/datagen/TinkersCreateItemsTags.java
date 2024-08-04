package com.jasonsimpart.tinkerscreate.datagen;

import com.jasonsimpart.tinkerscreate.TinkersCreate;
import com.jasonsimpart.tinkerscreate.init.TinkersCreateItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.data.ExistingFileHelper;
import slimeknights.tconstruct.common.TinkerTags;

import javax.annotation.Nullable;
import java.awt.event.ItemListener;

import static slimeknights.tconstruct.common.TinkerTags.Items.*;

public class TinkersCreateItemsTags extends ItemTagsProvider {

    private static TagKey<Item> create(String name) {
        return ItemTags.create(new ResourceLocation(name));
    }

    public TinkersCreateItemsTags(DataGenerator gen, BlockTagsProvider blockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(gen, blockTagsProvider, TinkersCreate.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        addToolTags(TinkersCreateItems.SIGN_BATTLE.get(), MULTIPART_TOOL, DURABILITY, HARVEST, MELEE_PRIMARY, INTERACTABLE_RIGHT, SWORD, SMALL_TOOLS, BONUS_SLOTS, AOE);
        tag(SHIELDS).add(TinkersCreateItems.SIGN_BATTLE.get());

    }

    @SafeVarargs
    private void addToolTags(ItemLike tool, TagKey<Item>... tags) {
        for (TagKey<Item> tag : tags) {
            tag(tag).add(tool.asItem());
        }
    }

}
