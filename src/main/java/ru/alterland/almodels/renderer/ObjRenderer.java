package ru.alterland.almodels.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.data.EmptyModelData;
import ru.alterland.almodels.block.IBigBlock;

import java.util.Random;

public class ObjRenderer extends TileEntityRenderer<TileEntity> {

    public ObjRenderer(TileEntityRendererDispatcher tileEntityRendererDispatcher) {
        super(tileEntityRendererDispatcher);
    }

    @Override
    public void render(TileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {

        World world = tileEntityIn.getWorld();
        if (world == null) return;
        BlockPos blockPos = tileEntityIn.getPos();

        BlockRendererDispatcher dispatcher = Minecraft.getInstance().getBlockRendererDispatcher();

        int part = tileEntityIn.getBlockState().get(IBigBlock.PART);
        if (part == 0) {
            BlockState state = tileEntityIn.getBlockState();
            IBakedModel model = dispatcher.getModelForState(state);

            matrixStackIn.push();
            matrixStackIn.scale(0.5F, 0.5F, 0.5F);
            matrixStackIn.translate(20, 0, 0);

            IVertexBuilder vertexBuffer = bufferIn.getBuffer(RenderType.getSolid());

            dispatcher.getBlockModelRenderer().renderModel(
                    world, model, state, blockPos, matrixStackIn, vertexBuffer, false, new Random(), combinedLightIn, combinedOverlayIn, EmptyModelData.INSTANCE
            );

            matrixStackIn.pop();
        }
    }
}
