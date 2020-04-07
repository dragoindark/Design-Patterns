package composite;

import java.util.ArrayList;
//
// The classes and/or objects participating in this pattern are:
// 1. Component   (DrawingElement)
//		Declares the interface for objects in the composition. Implements
//      default behavior for the interface common to all classes, as
//      appropriate. declares an interface for accessing and managing its
//		child components.
// 2. Leaf   (PrimitiveElement)
//		represents leaf objects in the composition. A leaf has no children.
//	    Defines behavior for primitive objects in the composition.
// 3. Composite   (CompositeElement)
//		defines behavior for components having children. Stores child
//		components. Implements child-related operations in the Component interface.
// 4. Client  (CompositeApp)
//		Manipulates objects in the composition through the Component interface.

// This is the "Component". (i.e tree node.)

//çok iyi çalışmamışım kusura bakmayın düzgün olmadı bana göre
interface DrawingElement {
	void Add(DrawingElement d);
	void Remove(DrawingElement d);
	void Display(int indent);
	public String getName();
	DrawingElement getComposite(String name);
}
//This is the "Leaf".
class PrimitiveElement implements DrawingElement {
	private String name;
	public String getName() { return name;}
	public PrimitiveElement(String name) {this.name = name;}
	public void Add(DrawingElement c) {
		System.out.println("Cannot add to a PrimitiveElement.");
	}
	public  void Remove(DrawingElement c) {
		System.out.println("Cannot remove from a PrimitiveElement.");
	}
	public void Display(int indent) {
		for(int i = 1;i <= indent;i++) 	System.out.print("-");
		System.out.println(" "  + name);
	}
	@Override
	public DrawingElement getComposite(String name) {
		return null;
	}
}
// This is the "Composite"
class CompositeElement implements DrawingElement {
	private String name;
	public String getName() { return name;}
	public CompositeElement(String name) {this.name = name;}
	public void Add(DrawingElement d) {
		try{
			elements.add(d);
		}catch(Exception e) {
			System.out.println(e.toString());
		}finally {
			System.out.println("Add try catch finished");
		}
	}
	public void Remove(DrawingElement d) {
		try {
			for (int i = 0; i< elements.size(); i++) {
				if (elements.get(i).getName() == d.getName()) {
					elements.remove(i);
					return;
				}
			}			
		}catch(Exception e) {
			System.out.println(e.toString());
		}finally {
			System.out.println("Remove try catch finished");
		}
	}
	public	void Display(int indent) {
		for(int i = 1;i <= indent;i++) System.out.print("-");
		System.out.println( "+ " + getName());

		// Display each child element on this node
		for (int i= 0; i< elements.size(); i++) {
			elements.get(i).Display(indent+2);
		}
	}
	private	ArrayList<DrawingElement> elements = new ArrayList<DrawingElement>();
	@Override
	public DrawingElement getComposite(String name) {
		boolean found=false;
		DrawingElement newPrim=null;
		for (int i = 0; i< elements.size(); i++) {
			if (elements.get(i).getName() == name) {
				newPrim=elements.get(i);
				found=true;
			}
		}
		if(!found) {
			newPrim=new PrimitiveElement(name);
		}
		return newPrim;
	}
}
//This is the "client"
public class CompositePattern {
	public static void main(String[] args) {
		// Create a tree structure
		DrawingElement root = new CompositeElement("Picture");
		root.Add(root.getComposite("Red Line"));
		root.Add(root.getComposite("Blue Circle"));
		root.Add(root.getComposite("Green Box"));

		DrawingElement comp = new CompositeElement("Two Circles");
		comp.Add(root.getComposite("Black Circle"));
		comp.Add(root.getComposite("White Circle"));
		root.Add(comp);

		// Add and remove a PrimitiveElement
		DrawingElement pe = new PrimitiveElement("Yellow Line");
		pe.Add(new PrimitiveElement("Red Line"));
		root.Add(pe);
		root.Remove(pe);

		// Recursively display nodes
		root.Display(1);
		
	}
}
