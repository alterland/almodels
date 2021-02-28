package ru.alterland.almodels;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import ru.alterland.almodels.init.Blocks;
import ru.alterland.almodels.init.TileEntities;

@Mod(ALModels.MOD_ID)
public class ALModels {

    public static final String MOD_ID = "almodels";
    public static final String MOD_NAME = "ALTERLAND Models";
    public static final ItemGroupALModels ITEM_GROUP = new ItemGroupALModels();

    public ALModels() {
        final IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        Blocks.BLOCKS.register(eventBus);
        Blocks.ITEMS.register(eventBus);
        TileEntities.TILE_ENTITY_TYPES.register(eventBus);
    }
}
