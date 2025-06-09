package net.dwa.brightvoid.datagen;

import net.dwa.brightvoid.BrightVoid;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags) {
        super(output, lookupProvider, blockTags, BrightVoid.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

    }
}