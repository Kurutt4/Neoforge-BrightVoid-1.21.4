package net.dwa.brightvoid.datagen;

import net.dwa.brightvoid.BrightVoid;
import net.dwa.brightvoid.block.ModBlocks;
import net.dwa.brightvoid.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.stream.Stream;

public class ModModelProvider extends ModelProvider {
    public ModModelProvider(PackOutput output) {
        super(output, BrightVoid.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        itemModels.generateFlatItem(ModItems.STAR_FRAGMENT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.MAGNETITE.get(), ModelTemplates.FLAT_ITEM);


        /* BLOCKS */
        blockModels.createTrivialCube(ModBlocks.METEOR_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.MOON_STONE.get());
        blockModels.createTrivialCube(ModBlocks.MOON_DEEP_STONE.get());
        blockModels.createTrivialCube(ModBlocks.MOON_COAL_ORE.get());
        blockModels.createTrivialCube(ModBlocks.MOON_IRON_ORE.get());
        blockModels.createTrivialCube(ModBlocks.MOON_COPPER_ORE.get());
        blockModels.createTrivialCube(ModBlocks.MOON_GOLD_ORE.get());
        blockModels.createTrivialCube(ModBlocks.MOON_REDSTONE_ORE.get());
        blockModels.createTrivialCube(ModBlocks.MOON_DIAMOND_ORE.get());
        blockModels.createTrivialCube(ModBlocks.MOON_EMERALD_ORE.get());



    }

    @Override
    protected Stream<? extends Holder<Block>> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream();
    }

    @Override
    protected Stream<? extends Holder<Item>> getKnownItems() {
        return ModItems.ITEMS.getEntries().stream();
    }
}