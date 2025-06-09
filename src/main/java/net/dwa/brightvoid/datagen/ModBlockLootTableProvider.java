package net.dwa.brightvoid.datagen;

import net.dwa.brightvoid.block.ModBlocks;
import net.dwa.brightvoid.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;


import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.MOON_STONE.get());
        dropSelf(ModBlocks.MOON_DEEP_STONE.get());

        add(ModBlocks.MOON_COAL_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.MOON_COAL_ORE.get(), Items.COAL, 1, 4));
        add(ModBlocks.MOON_IRON_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.MOON_IRON_ORE.get(), Items.RAW_IRON, 1, 3));
        add(ModBlocks.MOON_COPPER_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.MOON_COPPER_ORE.get(), Items.RAW_COPPER, 3, 5));
        add(ModBlocks.MOON_GOLD_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.MOON_GOLD_ORE.get(), Items.RAW_GOLD, 1, 3));
        add(ModBlocks.MOON_REDSTONE_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.MOON_REDSTONE_ORE.get(), Items.REDSTONE, 1, 6));
        add(ModBlocks.MOON_DIAMOND_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.MOON_DIAMOND_ORE.get(), Items.DIAMOND, 1, 3));
        add(ModBlocks.MOON_EMERALD_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.MOON_EMERALD_ORE.get(), Items.EMERALD, 1, 2));
        add(ModBlocks.MOON_LAPIS_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.MOON_LAPIS_ORE.get(), Items.LAPIS_LAZULI, 2, 5));
        add(ModBlocks.METEOR_BLOCK.get(),
               block -> createMultipleOreDrops(ModBlocks.METEOR_BLOCK.get(), ModItems.MAGNETITE.get(), 1, 3));
    }




    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
