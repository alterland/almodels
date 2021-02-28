package ru.alterland.almodels.init;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ru.alterland.almodels.ALModels;
import ru.alterland.almodels.tileentity.DoubleBedTileEntity1;

import java.util.function.Supplier;

public class TileEntities {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, ALModels.MOD_ID);

    public static final RegistryObject<TileEntityType<DoubleBedTileEntity1>> DOUBLE_BED_1 = register("double_bed_1",
            DoubleBedTileEntity1::new,
            Blocks.DOUBLE_BED_1
    );

    private static <T extends TileEntity> RegistryObject<TileEntityType<T>> register(final String name, final Supplier<T> tileEntityFactory, final RegistryObject<? extends Block> validBlock) {
        return TILE_ENTITY_TYPES.register(name, () -> {
            @SuppressWarnings("ConstantConditions")
            // dataFixerType will always be null until mod data fixers are implemented
            final TileEntityType<T> tileEntityType = TileEntityType.Builder
                    .create(tileEntityFactory, validBlock.get())
                    .build(null);

            return tileEntityType;
        });
    }
}
