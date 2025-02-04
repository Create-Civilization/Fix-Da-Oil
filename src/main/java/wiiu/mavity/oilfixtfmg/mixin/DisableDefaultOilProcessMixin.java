package wiiu.mavity.oilfixtfmg.mixin;

import com.drmangotea.tfmg.blocks.machines.oil_processing.pumpjack.base.PumpjackBaseBlockEntity;
import com.drmangotea.tfmg.registry.TFMGBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("AddedMixinMembersNamePattern")
@Mixin(PumpjackBaseBlockEntity.class)
public abstract class DisableDefaultOilProcessMixin {

	@Shadow
	public BlockPos deposit;

	@SuppressWarnings("DiscouragedShift")
    @Inject(
            method = "process",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraftforge/fluids/capability/templates/FluidTank;setFluid(Lnet/minecraftforge/fluids/FluidStack;)V",
                    shift = At.Shift.BEFORE
            ),
            remap = false,
            cancellable = true
    )
    public void interceptProcessing(CallbackInfo ci) {
        ci.cancel();
    }

    @SuppressWarnings("resource")
	@Inject(method = "findDeposit", at = @At(value = "HEAD"), remap = false, cancellable = true)
    public void disableDefaultOilProcess(CallbackInfo ci) {
		ci.cancel();
		for(int i = 0; i < this.mixinGetPos().getY() + 64; ++i) {
			BlockPos checkedPos = new BlockPos(this.mixinGetPos().getX(), this.mixinGetPos().getY() - 1 - i, this.mixinGetPos().getZ());
			if (this.mixinGetLevel().getBlockState(new BlockPos(checkedPos)).is(TFMGBlocks.OIL_DEPOSIT.get())) {
				this.deposit = checkedPos;
				return;
			}

			if (!this.mixinGetLevel().getBlockState(new BlockPos(checkedPos)).is(TFMGBlocks.INDUSTRIAL_PIPE.get())) {
				this.deposit = null;
				return;
			}
		}
    }

	@Unique
	public BlockPos mixinGetPos() {
		return ((PumpjackBaseBlockEntity)(Object)this).getBlockPos();
	}

	@Unique
	public Level mixinGetLevel() {
		return ((PumpjackBaseBlockEntity) (Object) this).getLevel();
	}
}