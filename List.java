import java.util.*;
import java.io.*;
class List{
	private Burger[] burgerArray;
	private int nextIndex;
	private int initSize;
	private double loadFact;
	
	List(int initSize, double loadFact){
		this.initSize = initSize;
		this.loadFact = loadFact;
		burgerArray = new Burger[initSize];
		nextIndex = 0;
	}
	
	private void extendArray(){
		int size = (int)(burgerArray.length*(1+loadFact));
		Burger[] tempBurgerArray = new Burger[size];
		for (int i = 0; i < burgerArray.length; i++){
			tempBurgerArray[i] = burgerArray[i];
		}
		burgerArray = tempBurgerArray;		
	}
	
	public boolean remove(Burger burger){
		int index = indexOf(burger);
		return remove(index)!= null;
	}
	
	public Burger remove(int index){
		if(index>=0 && index<nextIndex){
			Burger burger = burgerArray[index];
			for (int i = index; i < nextIndex-1; i++){
				burgerArray[i] = burgerArray[i+1];
			}
			nextIndex--;
			return burger;
		}
		return null;
	}
	
	public int indexOf(Burger burger){
		for (int i = 0; i < nextIndex; i++){
			if(burgerArray[i].equals(burger)){
				return i;
			}
		}
		return -1;
	}
	
	public boolean add(Burger burger){
		if(nextIndex>=burgerArray.length){
			extendArray();
		}
		burgerArray[nextIndex++] = burger;
		return true;
	}
	
	public boolean add(int index, Burger burger){
		if(index>=0 && index<=nextIndex){
			for (int i = nextIndex; i > index ; i--){
				burgerArray[i] = burgerArray[i-1];
			}
			burgerArray[index] = burger;
			nextIndex++;
			return true;
		}
		return false;
	}
	
	public Burger get(int index){
		if(index>=0 && index<nextIndex){
			return burgerArray[index];
		}
		return null;
	}
	
	public boolean contains(Burger burger){
		return indexOf(burger) != -1;
	}
	
	public Burger[] toArray(){
		Burger[] tempBurgerArray = new Burger[nextIndex];
		for (int i = 0; i < nextIndex; i++){
			tempBurgerArray[i] = burgerArray[i];
		}
		return tempBurgerArray;
	}
	
	public String toString(){
		String line="";
		for (int i = 0; i < nextIndex; i++){
			line+=burgerArray[i]+"\n";//same as => b1.toString()
		}
		return isEmpty()?"[empty]":line;	
	}
	
	public boolean isEmpty(){
		return (nextIndex==0);
	}
	
	public boolean set(Burger burger){
		int index = indexOf(burger);
		if(index == -1){
			return false;
		}else{
			burgerArray[index] = burger;
			return true;
		}
	}
	public int size(){
		return nextIndex;
	}
	
	//
	public static void main(String[] args){
		List burgerList1 = new List(100,0.5);
		burgerList1.add(new Burger("B0001","0794837261","Amali",1,2));
		burgerList1.add(new Burger("B0002","0701928374","Shehan",2,1));
		burgerList1.add(new Burger("B0003","0715647382","Kasun",4,2));
		burgerList1.add(new Burger("B0004","0729483726","Shehan",2,2));
		burgerList1.add(new Burger("B0005","0731827364","Chathura",1,1));
		burgerList1.add(new Burger("B0006","0715647382","Kasun",1,1));
		burgerList1.add(new Burger("B0007","0783927461","Rishmi",3,0));
		burgerList1.add(new Burger("B0008","0761827365","Gihan",6,2));
		burgerList1.add(new Burger("B0009","0766489623","Yasith",5,1));
		burgerList1.add(new Burger("B0010","0788489623","Dilshan",2,1));
		
		try{
			FileWriter fw = new FileWriter("Burger.txt");
			for (int i = 0; i < burgerList1.size(); i++){
				Burger b1 = burgerList1.get(i);
				fw.write(b1.toString()+"\n");
			}
			
			fw.close();
		}catch(IOException ex){
			 ex.printStackTrace();
		}
	}
	//
	
}

class Demo{
	
}
