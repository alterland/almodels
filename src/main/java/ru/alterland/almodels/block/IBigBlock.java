package ru.alterland.almodels.block;

import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

abstract public class IBigBlock extends HorizontalBlock implements ITileEntityProvider {

    private final int LENGTH;
    private final int WIDTH;
    private final int HEIGHT;
    private final VoxelShape[][] SHAPE;
    public static IntegerProperty PART;

    public IBigBlock(final Properties properties, int length, int width, int height, IntegerProperty property, VoxelShape[][] shape) {
        super(properties);
        LENGTH = length;
        WIDTH = width;
        HEIGHT = height;
        PART = property;
        SHAPE = shape;
        BlockState defaultBlockState = this.stateContainer.getBaseState().with(PART, 0);
        this.setDefaultState(defaultBlockState);
    }

    private VoxelShape getShape(BlockState state)
    {
        int part = state.get(PART);
        if (part > WIDTH+LENGTH) {
            return SHAPE[0][part-WIDTH-LENGTH-1];
        } else {
            return SHAPE[1][part];
        }
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        Direction direction = context.getPlacementHorizontalFacing();
        BlockPos blockpos = context.getPos();
        BlockPos blockpos1 = blockpos.offset(direction);
        return context.getWorld().getBlockState(blockpos1).isReplaceable(context) ? this.getDefaultState().with(HORIZONTAL_FACING, direction) : null;
    }

    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        if (!worldIn.isRemote) {

            Direction direction = state.get(HORIZONTAL_FACING);

            int i = 0;

            for (int y = 0; y < HEIGHT; y++) {
                for (int x = 0; x < LENGTH; x++) {
                    for(int z = 0; z < WIDTH; z++) {
                        BlockPos blockPos = pos;
                        switch (direction) {
                            case SOUTH:
                                blockPos = pos.add(z,y,x);
                                break;
                            case WEST:
                                blockPos = pos.add(-x,y,z);
                                break;
                            case NORTH:
                                blockPos = pos.add(-z,y,-x);
                                break;
                            case EAST:
                                blockPos = pos.add(x,y,-z);
                                break;
                        }
                        if (!pos.equals(blockPos)) {
                            worldIn.setBlockState(blockPos, state.with(PART, i));
                        }
                        i++;
                    }
                }
            }
        }
    }

    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        float shapeSize = LENGTH*WIDTH*HEIGHT;
        if (!worldIn.isRemote && player.isCreative()) {

            int part = state.get(PART);
            Direction direction = state.get(HORIZONTAL_FACING);

            BlockPos mainBlockPos = pos;

            if (part != 0) {
                //find main block

                while (part/shapeSize >= 0.5) { //make it 2d
                    mainBlockPos = mainBlockPos.down();
                    part = part-(LENGTH*WIDTH);
                }

                int col = part/WIDTH;
                int row = part-(col*WIDTH);

                switch (direction) {
                    case SOUTH:
                        mainBlockPos = mainBlockPos.offset(Direction.WEST, row);
                        mainBlockPos = mainBlockPos.offset(Direction.NORTH, col);
                        break;
                    case WEST:
                        mainBlockPos = mainBlockPos.offset(Direction.NORTH, row);
                        mainBlockPos = mainBlockPos.offset(Direction.EAST, col);
                        break;
                    case NORTH:
                        mainBlockPos = mainBlockPos.offset(Direction.EAST, row);
                        mainBlockPos = mainBlockPos.offset(Direction.SOUTH, col);
                        break;
                    case EAST:
                        mainBlockPos = mainBlockPos.offset(Direction.SOUTH, row);
                        mainBlockPos = mainBlockPos.offset(Direction.WEST, col);
                        break;
                }
            }

            BlockPos currentBlockPos = mainBlockPos;

            for (int y = 0; y < HEIGHT; y++) {
                for (int x = 0; x < LENGTH; x++) {
                    for (int z = 0; z < WIDTH; z++) {
                        switch (direction) {
                            case SOUTH:
                                currentBlockPos = mainBlockPos.add(z,y,x);
                                break;
                            case WEST:
                                currentBlockPos = mainBlockPos.add(-x,y,z);
                                break;
                            case NORTH:
                                currentBlockPos = mainBlockPos.add(-z,y,-x);
                                break;
                            case EAST:
                                currentBlockPos = mainBlockPos.add(x,y,-z);
                                break;
                        }
                        BlockState blockstate = worldIn.getBlockState(currentBlockPos);
                        worldIn.setBlockState(currentBlockPos, Blocks.AIR.getDefaultState(), 35);
                        worldIn.playEvent(player, 2001, currentBlockPos, Block.getStateId(blockstate));
                    }
                }
            }

        }

        super.onBlockHarvested(worldIn, pos, state, player);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return this.getShape(state);
    }
}
