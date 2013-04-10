package pucp.wallace;

public class UHList<T> {
	
	private UHListNode<T> root;
	
	class UHListNode<K> {
		UHListNode<K> next;
		UHElement<K> element;

		public UHListNode(UHElement<K> element) {
			this.next = null;
			this.element = element;
		}
	}
}
