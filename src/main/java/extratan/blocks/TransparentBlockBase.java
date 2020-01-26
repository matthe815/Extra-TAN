package extratan.blocks;

import extratan.core.ExtraTAN;
import extratan.creativetabs.CreativeTabHandler;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class TransparentBlockBase extends BlockGlass {

	public TransparentBlockBase(Material material, SoundType footstepSound, String name, String registryName, float hardness) {
		super(material, false); // Set the material.
		setUnlocalizedName(ExtraTAN.modId + "." + name); // Set the unlocalized name.
		setRegistryName(registryName); // Set the registry name.
		setCreativeTab(CreativeTabHandler.ExtraTANBlocks); // Set it as an Extra TAN block.
		setHardness(hardness); // Set the hardness to the requested value.
		setResistance(hardness / 4); // Set the resistance to half the hardness.
		setSoundType(footstepSound); // Set the footstep sound.
	}

}
