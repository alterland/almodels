package ru.alterland.almodels.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import ru.alterland.almodels.init.TileEntities;

public class DoubleBed1Block extends HorizontalBlock {

    public static final IntegerProperty PART = IntegerProperty.create("part", 0, 11);
    public static final Property<Boolean> MODEL = BooleanProperty.create("model");

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

    public DoubleBed1Block(final Properties properties) {
        super(properties);
        this.setDefaultState(this.getDefaultState().with(HORIZONTAL_FACING, Direction.NORTH).with(MODEL, false));
        //super(properties, 3, 2, 2, PART, shape);
    }

    @Override
    protected void fillStateContainer(final StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(HORIZONTAL_FACING);
        builder.add(PART);
        builder.add(MODEL);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    public boolean hasTileEntity(final BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(final BlockState state, final IBlockReader world) { return TileEntities.DOUBLE_BED_1.get().create(); }

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
