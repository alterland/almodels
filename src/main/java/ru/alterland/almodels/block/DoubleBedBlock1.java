package ru.alterland.almodels.block;

import net.minecraft.block.AbstractBlock;;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import ru.alterland.almodels.tileentity.DoubleBedTileEntity1;

import javax.annotation.Nullable;

public class DoubleBedBlock1 extends IBigBlock {

    public static final IntegerProperty PART = IntegerProperty.create("part", 0, 11);

    private static final VoxelShape[][] shape = {
        {
                Block.makeCuboidShape(1,1,1, 15,4,15),
                Block.makeCuboidShape(1,1,1, 16,16,16),
                Block.makeCuboidShape(1,1,1, 16,16,16),
                Block.makeCuboidShape(1,1,1, 16,15,16),
                Block.makeCuboidShape(1,1,1, 16,16,16),
                Block.makeCuboidShape(1,1,1, 16,16,16),
        },
        {
                Block.makeCuboidShape(1,1,1, 16,16,16),
                Block.makeCuboidShape(1,1,1, 16,16,16),
                Block.makeCuboidShape(1,1,1, 16,16,16),
                Block.makeCuboidShape(1,1,1, 16,15,16),
                Block.makeCuboidShape(1,1,1, 16,16,16),
                Block.makeCuboidShape(1,1,1, 16,16,16),
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

    public void onLanded(IBlockReader worldIn, Entity entityIn) {
        if (entityIn.isSuppressingBounce()) {
            super.onLanded(worldIn, entityIn);
        } else {
            this.bounceEntity(entityIn);
        }

    }

    private void bounceEntity(Entity entity) {
        Vector3d vector3d = entity.getMotion();
        if (vector3d.y < 0.0D) {
            double d0 = entity instanceof LivingEntity ? 1.0D : 0.8D;
            entity.setMotion(vector3d.x, -vector3d.y * (double)0.66F * d0, vector3d.z);
        }

    }
}
