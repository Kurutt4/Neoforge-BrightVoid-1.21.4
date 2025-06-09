package net.dwa.brightvoid.block;


import net.dwa.brightvoid.BrightVoid;
import net.dwa.brightvoid.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(BrightVoid.MOD_ID);

    public static final DeferredBlock<Block> MOON_STONE = registerBlock("moon_stone",
            () -> new Block(BlockBehaviour.Properties.of().setId(ModBlocks.MOON_STONE.getKey())
                    .strength(4f).sound(SoundType.SAND)));

    public static final DeferredBlock<Block> MOON_DEEP_STONE = registerBlock("moon_deep_stone",
            () -> new Block(BlockBehaviour.Properties.of().setId(ModBlocks.MOON_DEEP_STONE.getKey())
                    .strength(4f).sound(SoundType.STONE)));

    public static final DeferredBlock<Block> MOON_COAL_ORE = registerBlock("moon_coal_ore",
            () -> new Block(BlockBehaviour.Properties.of().setId(ModBlocks.MOON_COAL_ORE.getKey())
                    .strength(4f).sound(SoundType.TUFF)));

    public static final DeferredBlock<Block> MOON_IRON_ORE = registerBlock("moon_iron_ore",
            () -> new Block(BlockBehaviour.Properties.of().setId(ModBlocks.MOON_IRON_ORE.getKey())
                    .strength(4f).sound(SoundType.TUFF)));

    public static final DeferredBlock<Block> MOON_COPPER_ORE = registerBlock("moon_copper_ore",
            () -> new Block(BlockBehaviour.Properties.of().setId(ModBlocks.MOON_COPPER_ORE.getKey())
                    .strength(4f).sound(SoundType.TUFF)));

    public static final DeferredBlock<Block> MOON_GOLD_ORE = registerBlock("moon_gold_ore",
            () -> new Block(BlockBehaviour.Properties.of().setId(ModBlocks.MOON_GOLD_ORE.getKey())
                    .strength(4f).sound(SoundType.TUFF)));

    public static final DeferredBlock<Block> MOON_REDSTONE_ORE = registerBlock("moon_redstone_ore",
            () -> new Block(BlockBehaviour.Properties.of().setId(ModBlocks.MOON_REDSTONE_ORE.getKey())
                    .strength(4f).sound(SoundType.TUFF)));

    public static final DeferredBlock<Block> MOON_DIAMOND_ORE = registerBlock("moon_diamond_ore",
            () -> new Block(BlockBehaviour.Properties.of().setId(ModBlocks.MOON_DIAMOND_ORE.getKey())
                    .strength(4f).sound(SoundType.TUFF)));

    public static final DeferredBlock<Block> MOON_EMERALD_ORE = registerBlock("moon_emerald_ore",
            () -> new Block(BlockBehaviour.Properties.of().setId(ModBlocks.MOON_EMERALD_ORE.getKey())
                    .strength(4f).sound(SoundType.TUFF)));

    public static final DeferredBlock<Block> MOON_LAPIS_LAZULI_ORE = registerBlock("moon_lapis_lazuli_ore",
            () -> new Block(BlockBehaviour.Properties.of().setId(ModBlocks.MOON_LAPIS_LAZULI_ORE.getKey())
                    .strength(4f).sound(SoundType.TUFF)));

    public static final DeferredBlock<Block> METEOR_BLOCK = registerBlock("meteor_block",
            () -> new Block(BlockBehaviour.Properties.of().setId(ModBlocks.METEOR_BLOCK.getKey())
                    .strength(4f).sound(SoundType.TUFF)));



    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    public static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}