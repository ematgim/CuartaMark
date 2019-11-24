package test;

import java.util.Random;

public class test1 {
	public static void main(String[] args) {

		Random r = new Random();
		int numero = 30 + r.nextInt(11);
		System.out.println(numero);
	}
}
