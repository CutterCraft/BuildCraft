package net.minecraft.src.buildcraft.transport;

import java.util.LinkedList;

import net.minecraft.src.Block;
import net.minecraft.src.BuildCraftTransport;
import net.minecraft.src.TileEntity;
import net.minecraft.src.buildcraft.api.IPipe;
import net.minecraft.src.buildcraft.api.gates.IOverrideDefaultTriggers;
import net.minecraft.src.buildcraft.api.gates.ITrigger;
import net.minecraft.src.buildcraft.api.gates.ITriggerProvider;
import net.minecraft.src.buildcraft.api.gates.Trigger;

public class PipeTriggerProvider implements ITriggerProvider {

	@Override
	public LinkedList<ITrigger> getPipeTriggers(IPipe iPipe) {
		if (iPipe instanceof IOverrideDefaultTriggers)
			return ((IOverrideDefaultTriggers) iPipe).getTriggers();

		LinkedList<ITrigger> result = new LinkedList<ITrigger>();

		Pipe pipe = (Pipe) iPipe;

		if (pipe.hasGate())
			pipe.gate.addTrigger(result);

		if (pipe.transport instanceof PipeTransportItems) {
			result.add(BuildCraftTransport.triggerPipeEmpty);
			result.add(BuildCraftTransport.triggerPipeItems);
		} else if (pipe.transport instanceof PipeTransportPower) {
			result.add(BuildCraftTransport.triggerPipeEmpty);
			result.add(BuildCraftTransport.triggerPipeEnergy);
		} else if (pipe.transport instanceof PipeTransportLiquids) {
			result.add(BuildCraftTransport.triggerPipeEmpty);
			result.add(BuildCraftTransport.triggerPipeLiquids);
		}

		return result;
	}

	@Override
	public LinkedList<ITrigger> getNeighborTriggers(Block block, TileEntity tile) {
		return null;
	}

}
