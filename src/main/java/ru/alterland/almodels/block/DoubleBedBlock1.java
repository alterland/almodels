package ru.alterland.almodels.block;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.AbstractBlock;;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import ru.alterland.almodels.tileentity.DoubleBedTileEntity1;

import javax.annotation.Nullable;

public class DoubleBedBlock1 extends IBigBlock {

    private static final IntegerProperty PART = IntegerProperty.create("part", 0, 11);

    private static final VoxelShape[][] shape = {
        {
                Block.makeCuboidShape(0,0,0, 15,15,15),
                Block.makeCuboidShape(0,0,0, 15,15,15),
                Block.makeCuboidShape(0,0,0, 15,15,15),
                Block.makeCuboidShape(0,0,0, 15,15,15),
                Block.makeCuboidShape(0,0,0, 15,15,15),
                Block.makeCuboidShape(0,0,0, 15,15,15),
        },
        {
                Block.makeCuboidShape(0,0,0, 15,15,15),
                Block.makeCuboidShape(0,0,0, 15,15,15),
                Block.makeCuboidShape(0,0,0, 15,15,15),
                Block.makeCuboidShape(0,0,0, 15,15,15),
                Block.makeCuboidShape(0,0,0, 15,15,15),
                Block.makeCuboidShape(0,0,0, 15,15,15),
        }
    };

    public DoubleBedBlock1(final AbstractBlock.Properties properties) {
        super(properties, 3, 2, 2, PART, shape);
    }

    @Override
    public void fillStateContainer(final StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(HORIZONTAL_FACING, PART);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) { return new DoubleBedTileEntity1(); }
}
