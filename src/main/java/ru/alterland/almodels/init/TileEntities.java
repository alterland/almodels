package ru.alterland.almodels.init;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ru.alterland.almodels.ALModels;
import ru.alterland.almodels.tileentity.DoubleBed1TileEntity;

public class TileEntities {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, ALModels.MOD_ID);

    public static final RegistryObject<TileEntityType<DoubleBed1TileEntity>> DOUBLE_BED_1 = TILE_ENTITY_TYPES.register("double_bed_1", () ->
            TileEntityType.Builder.create(DoubleBed1TileEntity::new, Blocks.DOUBLE_BED_1_BLOCK.get()).build(null)
    );
}
