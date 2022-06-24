package com.homework.one;
import java.util.Random;

public class RandomCharGenerator{
	    
	private static final String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789~!@#$%^&*()_+";

    public String randomCharGenerator() {
		
        Random rand = new Random();
		
        StringBuilder sb = new StringBuilder();
		
        for (int i = 0; i < 3; i++) {
            int index = (int) (chars.length() * Math.random());
            sb.append(chars.charAt(index));
        }
		
        return sb.toString();
		
    }
	
	public void storeString(String[][] table, int row, int col){

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                table[i][j] = randomCharGenerator();
            }
        }
		
	}
	
}