package aginsun.taleofkingdoms.core.handlers.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import aginsun.taleofkingdoms.core.GoldKeeper;

import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;

public class PacketGold extends PacketToK
{
	private String username;
	private int GoldValue;
	
	public PacketGold() 
	{
		super(PacketType.GOLD, false);
	}
	
	public PacketGold(String username, int GoldValue)
	{
		super(PacketType.GOLD, false);
		this.username = username;
		this.GoldValue = GoldValue;
	}
	
	@Override
	public void readData(DataInputStream data) throws IOException
	{
		this.username = data.readUTF();
		this.GoldValue = data.readInt();
	}
	
	public void writeData(DataOutputStream dos) throws IOException
	{
		dos.writeUTF(username);
		dos.writeInt(GoldValue);
	}
	
	public void execute(INetworkManager network, Player player)
	{
		EntityPlayer thePlayer = (EntityPlayer)player;
		
		setGold(thePlayer);
		
	}
	
	@SideOnly(Side.CLIENT)
	public void setGold(EntityPlayer player)
	{
		GoldKeeper.setGold(player, GoldValue);
	}
}
