package ru.alterland.almodels.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

/*
    12 blocks structure:
    3 blocks long;
    2 blocks width;
    2 block height.
 */
abstract public class I322Block extends ITileEntityBlock<TileEntity> {

    private final TileEntity TILE_ENTITY;

    public I322Block(final Block.Properties properties, TileEntity tileEntity) {
        super(properties);
        this.TILE_ENTITY = tileEntity;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return this.TILE_ENTITY;
    }
}
