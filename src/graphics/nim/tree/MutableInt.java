package graphics.nim.tree;

public class MutableInt {
	public int value = 0;
	
	public MutableInt(int value) {
		this.value = value;
	}
	
	public void increment() {
		value++;
	}
}
