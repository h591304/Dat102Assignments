package no.hvl.dat102.klient;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import no.hvl.dat102.BinaerTreNode;
import no.hvl.dat102.KjedetBSTre;
import no.hvl.dat102.adt.BSTreADT;

public class KlientBSTre2 {

	private static List<BSTreADT<Integer>> liste;
	private static final int ANTALL_NODER = 1023;
	private static final int ANTALL_TRE = 100;
	private static Random tilfeldig;
	
	public static void main(String[] args) {
		
		int n, hoyde, stoerst, minst, sum;
		
		n = ANTALL_NODER;
		hoyde = 0;
		stoerst = 0;
		minst = 50000000;
		sum = 0;
		
		tilfeldig = new Random();
		liste = new ArrayList<>(ANTALL_TRE);
		
		KjedetBSTre<Integer> tre = new KjedetBSTre<Integer>();
	
		for(int i = 0; i < ANTALL_TRE; i++) {
			liste.add(tre);
			for(int j = 0; j < ANTALL_NODER; j++) {
				Integer el = Integer.valueOf(tilfeldig.nextInt(ANTALL_TRE));
				tre.leggTil(el);
			}
			
			hoyde = (tre.binaerTreHoyde() - 1);
//			hoyde = liste.get(i).binaerTreHoyde()-1;
			sum += hoyde;
			
			if(hoyde < minst) {
				minst = hoyde;
			}
			if(hoyde > stoerst) {
				stoerst = hoyde;
			}
		}
		double totalSum = (sum/ANTALL_TRE);
		
		System.out.println("---------- MÅLING AV TILFELIDGE BINÆRE SØKETRE ----------");
		System.out.println("");
		System.out.println("Antall noder: " + n);
		System.out.println("Minimal teoretisk høyde: " + Math.round((Math.log(n + 1) / Math.log(2)) + 1));
		System.out.println("Maksimal teoretisk høyde: " + (n-1));
		System.out.println("Minste høyde ila. kjøring: " + minst);
		System.out.println("Største høyde ila. kjøring: " + stoerst);
		System.out.println("Gjennomsnittlig høyde ila. kjøring: " + totalSum);
		System.out.println("---------------------------------------------------------");
	}
	

}
