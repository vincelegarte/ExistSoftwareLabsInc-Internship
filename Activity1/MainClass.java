package com.activity.one;
import com.activity.one.util.ClassC;

public class MainClass{
	
	static void classA(){
		
		ClassA a = new ClassA();
		System.out.println(a.isBool);
		
	}

	static void classB() {
		
		ClassB b = new ClassB();
		
		for (int i = 0; i < b.days.length; i++){
			System.out.println(b.days[i]);
		}
		
	}
	
	static void classC() {
		
		ClassC c = new ClassC();
		
		int result = 7;
		
		c.displayNumber(result);
		
	}

	public static void main(String[] args){
		
		ClassA a = new ClassA();
		
		if (a.isBool != true){
			System.out.println("False");
		} else {
			System.out.println("True");
		}
		
		classA();
		classB();
		classC();
		
	}
	
}