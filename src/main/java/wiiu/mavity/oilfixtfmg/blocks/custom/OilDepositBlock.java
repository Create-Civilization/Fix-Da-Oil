package wiiu.mavity.oilfixtfmg.blocks.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import wiiu.mavity.oilfixtfmg.blocks.entity.OilDepositBlockEntity;

public class OilDepositBlock extends BaseEntityBlock {


	public OilDepositBlock(Properties pProperties) {
		super(pProperties);
	}

	@Override
	@Nullable
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return new OilDepositBlockEntity(pPos, pState);
	}
}
