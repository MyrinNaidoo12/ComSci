package animationSkeletonCode;

import java.util.concurrent.atomic.AtomicBoolean;

public class GridBlock {
	static private int IDcounter=0;
	private AtomicBoolean occupied;
	private final boolean isExit;
	private final boolean isRefreshmentStation;
	private int ID;
	private int [] coords;
	
	GridBlock(boolean exitBlock, boolean refreshBlock) {
		occupied=new AtomicBoolean(false);
		isExit=exitBlock;
		isRefreshmentStation=refreshBlock;
		synchronized (GridBlock.class) {
			ID=IDcounter;
			GridBlock.IDcounter++;
			}
	}
	GridBlock(int x, int y, boolean exitBlock, boolean refreshBlock) {
		this(exitBlock,refreshBlock);
		coords = new int [] {x,y};
	}
	
	public int getX() {return coords[0];}
	public int getY() {return coords[1];}

	
	public synchronized void waitBlock()  
	{
		try
		{
			if (occupied.get())
			{
				wait();
				getBlock();
			}
			else
			{getBlock();}
		}
		catch(InterruptedException ex)
		{System.out.println(ex);}
	}
	
	
	public synchronized boolean getBlock() 
	{
		if (occupied.get() == true) 
		{
			return false;
		}
		occupied.set(true);
		return true;
	}
		
	public synchronized void releaseBlock() 
	{
		occupied.set(false);
		notify();
	}
	
	public boolean getStatus() {
		return occupied.get();	
	}
	
	public boolean isExit() {
		return isExit;	
	}
	
	public boolean isRefreshmentStation() {
		return isRefreshmentStation;
	}
	public int getID() { return ID;}
	
}
