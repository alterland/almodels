package ru.alterland.almodels.init;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ru.alterland.almodels.ALModels;
import ru.alterland.almodels.block.DoubleBedBlock1;

import java.util.function.Supplier;

public class Blocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ALModels.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ALModels.MOD_ID);

    public static final RegistryObject<DoubleBedBlock1> DOUBLE_BED_1 = registerBlock("double_bed_1",
            () -> new DoubleBedBlock1(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD))
    );

    /**
     * Registers a block with a standard {@link BlockItem} as its block item.
     *
     * @param name         The registry name of the block
     * @param blockFactory The factory used to create the block
     * @param <BLOCK>      The block type
     * @return A RegistryObject reference to the block
     */
    private static <BLOCK extends Block> RegistryObject<BLOCK> registerBlock(final String name, final Supplier<BLOCK> blockFactory) {
        return registerBlock(name, blockFactory, block -> new BlockItem(block, defaultItemProperties()));
    }

    /**
     * Registers a block and its block item.
     *
     * @param name         The registry name of the block
     * @param blockFactory The factory used to create the block
     * @param itemFactory  The factory used to create the block item
     * @param <BLOCK>      The block type
     * @return A RegistryObject reference to the block
     */
    private static <BLOCK extends Block> RegistryObject<BLOCK> registerBlock(final String name, final Supplier<BLOCK> blockFactory, final IBlockItemFactory<BLOCK> itemFactory) {
        final RegistryObject<BLOCK> block = BLOCKS.register(name, blockFactory);

        ITEMS.register(name, () -> itemFactory.create(block.get()));

        return block;
    }

    /**
     * Gets an {@link Item.Properties} instance with the {@link ItemGroup} set to {@link ALModels#ITEM_GROUP}.
     *
     * @return The item properties
     */
    private static Item.Properties defaultItemProperties() {
        return new Item.Properties().group(ALModels.ITEM_GROUP);
    }

    /**
     * A factory function used to create block items.
     *
     * @param <BLOCK> The block type
     */
    @FunctionalInterface
    private interface IBlockItemFactory<BLOCK extends Block> {
        Item create(BLOCK block);
    }
}
