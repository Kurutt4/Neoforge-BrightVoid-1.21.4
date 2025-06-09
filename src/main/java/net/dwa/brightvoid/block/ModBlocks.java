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
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

import static net.dwa.brightvoid.item.ModItems.ITEMS;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(BrightVoid.MOD_ID);

    public static final DeferredBlock<Block> MOON_STONE  = registerBlock(
            "moon_stone",
            properties -> new Block(properties),
            BlockBehaviour.Properties.of()
                    .strength(4f)
                    .sound(SoundType.SAND));

    public static final DeferredBlock<Block> MOON_DEEP_STONE  = registerBlock(
            "moon_deep_stone",
            properties -> new Block(properties),
            BlockBehaviour.Properties.of()
                    .strength(4f)
                    .sound(SoundType.STONE));

    public static final DeferredBlock<Block> MOON_COAL_ORE  = registerBlock(
            "moon_coal_ore",
            properties -> new Block(properties),
            BlockBehaviour.Properties.of()
                    .strength(4f)
                    .sound(SoundType.STONE));

    public static final DeferredBlock<Block> MOON_IRON_ORE  = registerBlock(
            "moon_iron_ore",
            properties -> new Block(properties),
            BlockBehaviour.Properties.of()
                    .strength(4f)
                    .sound(SoundType.STONE));

    public static final DeferredBlock<Block> MOON_GOLD_ORE  = registerBlock(
            "moon_gold_ore",
            properties -> new Block(properties),
            BlockBehaviour.Properties.of()
                    .strength(4f)
                    .sound(SoundType.STONE));

    public static final DeferredBlock<Block> MOON_REDSTONE_ORE  = registerBlock(
            "moon_redstone_ore",
            properties -> new Block(properties),
            BlockBehaviour.Properties.of()
                    .strength(4f)
                    .sound(SoundType.STONE));

    public static final DeferredBlock<Block> MOON_DIAMOND_ORE  = registerBlock(
            "moon_diamond_ore",
            properties -> new Block(properties),
            BlockBehaviour.Properties.of()
                    .strength(4f)
                    .sound(SoundType.STONE));

    public static final DeferredBlock<Block> MOON_EMERALD_ORE  = registerBlock(
            "moon_emerald_ore",
            properties -> new Block(properties),
            BlockBehaviour.Properties.of()
                    .strength(4f)
                    .sound(SoundType.STONE));

    public static final DeferredBlock<Block> MOON_LAPIS_ORE  = registerBlock(
            "moon_lapis_ore",
            properties -> new Block(properties),
            BlockBehaviour.Properties.of()
                    .strength(4f)
                    .sound(SoundType.STONE));

    public static final DeferredBlock<Block> MOON_COPPER_ORE  = registerBlock(
            "moon_copper_ore",
            properties -> new Block(properties),
            BlockBehaviour.Properties.of()
                    .strength(4f)
                    .sound(SoundType.STONE));

    public static final DeferredBlock<Block> METEOR_BLOCK  = registerBlock(
            "meteor_block",
            properties -> new Block(properties),
            BlockBehaviour.Properties.of()
                    .strength(4f)
                    .sound(SoundType.STONE));

    private static <B extends Block> DeferredBlock<B> registerBlock(String name, Function<BlockBehaviour.Properties, ? extends B> blockFactory, BlockBehaviour.Properties blockProperties) {
        DeferredBlock<B> block = BLOCKS.registerBlock(name, blockFactory, blockProperties);
        registerBlockItem(name, block);
        return block;
    }

    private static <B extends Block> void registerBlockItem(String name, DeferredBlock<B> block) {
        ITEMS.registerSimpleBlockItem(name, block);
    }


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}