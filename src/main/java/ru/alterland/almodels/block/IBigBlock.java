package ru.alterland.almodels.block;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;

import javax.annotation.Nullable;

abstract public class IBigBlock extends HorizontalBlock implements ITileEntityProvider {

    private final int LENGTH;
    private final int WIDTH;
    private final int HEIGHT;
    private final VoxelShape[][] SHAPE;
    private final IntegerProperty PART;

    public IBigBlock(final Properties properties, int length, int width, int height, IntegerProperty property, VoxelShape[][] shape) {
        super(properties);
        LENGTH = length;
        WIDTH = width;
        HEIGHT = height;
        PART = property;
        SHAPE = shape;
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

    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        if (!worldIn.isRemote) {
            int i = 0;

            Direction direction = state.get(HORIZONTAL_FACING);

            switch (direction) {
                case NORTH:

            }

            for (int y = 0; y < HEIGHT; y++) {
                for (int x = 0; x < LENGTH; x++) {
                    for(int z = 0; z < WIDTH; z++) {
                        if (!pos.equals(new BlockPos(x,y,z))) {
                            worldIn.setBlockState(pos.add(x,y,z), state.with(PART, i));
                        }
                        i++;
                    }
                }
            }
        }
    }

    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!worldIn.isRemote && player.isCreative()) {

            int part = state.get(PART);

            if (part != 0) {
                BlockPos blockpos = pos.offset(state.get(HORIZONTAL_FACING));
                BlockState blockstate = worldIn.getBlockState(blockpos);
                worldIn.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 35);
                worldIn.playEvent(player, 2001, blockpos, Block.getStateId(blockstate));
            }
        }

        super.onBlockHarvested(worldIn, pos, state, player);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return this.getShape(state);
    }
}
