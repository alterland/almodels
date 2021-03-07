package ru.alterland.almodels.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ru.alterland.almodels.ALModels;
import ru.alterland.almodels.block.DoubleBed1Block;

import java.util.function.Supplier;

public class Blocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ALModels.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ALModels.MOD_ID);

    public static final RegistryObject<Block> DOUBLE_BED_1_BLOCK = register("double_bed_1", () -> new DoubleBed1Block(Block.Properties.create(Material.WOOD).hardnessAndResistance(3.0F, 3.0F)));


    public static RegistryObject<Block> register(String name, final Supplier<Block> blockFactory) {
        RegistryObject<Block> block = BLOCKS.register(name, blockFactory);
        ITEMS.register(name, () -> new BlockItem(block.get(), defaultItemProperties()));
        return block;
    }

    private static Item.Properties defaultItemProperties() {
        return new Item.Properties().group(ALModels.ITEM_GROUP);
    }
}
