package ru.alterland.almodels.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelDataManager;
import net.minecraftforge.client.model.data.EmptyModelData;
import net.minecraftforge.client.model.data.IModelData;
import ru.alterland.almodels.block.DoubleBed1Block;

import java.util.Objects;

import static net.minecraft.state.properties.BlockStateProperties.HORIZONTAL_FACING;

public class ObjRenderer extends TileEntityRenderer<TileEntity> {

    public ObjRenderer(TileEntityRendererDispatcher tileEntityRendererDispatcher) {
        super(tileEntityRendererDispatcher);
    }

    @Override
    public void render(TileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {

        BlockRendererDispatcher dispatcher = Minecraft.getInstance().getBlockRendererDispatcher();

        BlockState state = tileEntityIn.getBlockState().getBlock().getDefaultState().with(DoubleBed1Block.MODEL, true);
        IBakedModel model = dispatcher.getModelForState(state);

        Direction direction = tileEntityIn.getBlockState().get(HORIZONTAL_FACING);

        float angle = 0;

        matrixStackIn.push();

        IVertexBuilder vertexBuffer = bufferIn.getBuffer(RenderType.getSolid());

        float brightness = 0.87F;

        switch (direction) {
            default:
            case SOUTH:
                angle = 0;
                break;
            case NORTH:
                angle = 180;
                break;
            case EAST:
                angle = 90;
                break;
            case WEST:
                angle = 270;
                break;
        }

        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(angle));

        dispatcher.getBlockModelRenderer().renderModel(
                matrixStackIn.getLast(), vertexBuffer, state, model, brightness, brightness, brightness, combinedLightIn, combinedOverlayIn, EmptyModelData.INSTANCE
        );

        matrixStackIn.pop();

    }
}
