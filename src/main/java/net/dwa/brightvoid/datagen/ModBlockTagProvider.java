package net.dwa.brightvoid.datagen;

import net.dwa.brightvoid.BrightVoid;
import net.dwa.brightvoid.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, BrightVoid.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.MOON_DEEP_STONE.get())
                .add(ModBlocks.MOON_COAL_ORE.get())
                .add(ModBlocks.MOON_IRON_ORE.get())
                .add(ModBlocks.MOON_COPPER_ORE.get())
                .add(ModBlocks.MOON_GOLD_ORE.get())
                .add(ModBlocks.MOON_REDSTONE_ORE.get())
                .add(ModBlocks.MOON_DIAMOND_ORE.get())
                .add(ModBlocks.MOON_EMERALD_ORE.get())
                .add(ModBlocks.MOON_LAPIS_ORE.get())
                .add(ModBlocks.METEOR_BLOCK.get());


        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.MOON_DIAMOND_ORE.get())
                .add(ModBlocks.MOON_EMERALD_ORE.get());

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.METEOR_BLOCK.get());
    }
}