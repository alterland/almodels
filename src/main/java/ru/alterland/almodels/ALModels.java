package ru.alterland.almodels;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import ru.alterland.almodels.init.Blocks;
import ru.alterland.almodels.init.TileEntities;
import ru.alterland.almodels.renderer.ObjRenderer;

@Mod(ALModels.MOD_ID)
public class ALModels {

    public static final String MOD_ID = "almodels";
    public static final String MOD_NAME = "ALTERLAND Models";
    public static final ItemGroupALModels ITEM_GROUP = new ItemGroupALModels();

    public ALModels() {
        final IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientOnlyActions);

        Blocks.BLOCKS.register(eventBus);
        Blocks.ITEMS.register(eventBus);
        TileEntities.TILE_ENTITY_TYPES.register(eventBus);
    }

    private void clientOnlyActions(final FMLClientSetupEvent event) {
        for (RegistryObject<TileEntityType<?>> tileEntityTypeRegistryObject : TileEntities.TILE_ENTITY_TYPES.getEntries()) {
            ClientRegistry.bindTileEntityRenderer(tileEntityTypeRegistryObject.get(), ObjRenderer::new);
        }
    }
}
