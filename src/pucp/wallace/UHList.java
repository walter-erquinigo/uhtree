package pucp.wallace;
// TO DELETE
public class UHList<T> {
	
	private UHListNode<T> root;
	
	public UHList() {
		root = null;
	}
	
	public UHElement<T> findElement(T value) {
		for (UHListNode node = root; node != null ; node = node.next) {
			return null;
		}
		return null;
	}
	
	
	class UHListNode<K> {
		UHListNode<K> next;
		UHElement<K> element;

		public UHListNode(UHElement<K> element) {
			this.next = null;
			this.element = element;
		}
	}
}
