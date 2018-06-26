package atm;

import java.util.ArrayList;
import java.util.Scanner;

public class Atm {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		ArrayList<String> pinValabil = new ArrayList<String>();
		pinValabil.add("7890");
		pinValabil.add("2813");
		pinValabil.add("4567");
		pinValabil.add("7890");
		
		ArrayList<String> numeValabil = new ArrayList<String>();
		numeValabil.add("Vasile Petrovschi");
		numeValabil.add("Mihai Sima");
		numeValabil.add("Claudia Traistaru");
		numeValabil.add("Andrei Baragan");
		
		ArrayList<Integer> sold = new ArrayList<Integer>();
		sold.add(1400);
		sold.add(2400);
		sold.add(2800);
		sold.add(3300);

		ArrayList<Integer> numarIndexPin = new ArrayList<Integer>();
		
		MetodaPrint optiuni = new MetodaPrint();
		
		int indexPin = -1;
		int indexNume = -2;
		int indexSold = 0;
		int counter = 3;
		boolean a=true;
		boolean pinIncorect = false;
		boolean numeIncorect = false;
		
		

		for (int i = 0; i < 3; i++) {
			
			System.out.println("Va rugam introduceti pinul dumneavoastra");
			String pin = scan.nextLine();
			System.out.println("Va rugam introduceti numele dumneavoastra");
			String nume = scan.nextLine();
			
			for (int j = 0; j < pinValabil.size(); j++) {
				if(pin.equals(pinValabil.get(j))) {
					indexPin = j;
					numarIndexPin.add(j);
					}
				}
			
			if(indexPin < 0){
				pinIncorect = true;
			}else {
				pinIncorect = false;
			}
			
			for (int x = 0; x < numeValabil.size(); x++) {
				if(nume.equalsIgnoreCase(numeValabil.get(x))) {
					indexNume = x;
				}
				}
			
			if(indexNume < 0){
				numeIncorect = true;
			}else {
				numeIncorect = false;
			}
			
			for (int y = 0; y < numarIndexPin.size(); y++) {
				if(indexNume == numarIndexPin.get(y)) {
					indexPin=indexNume;
				}
				}
			
			if(indexPin==indexNume) {
				optiuni.print();
				break;
			}else {
				counter--;
				if(counter == 0) {
					System.out.println("Card blocat.Contacteaza banca.");
					a=false;
					break;
				}else if (counter==1) {
					if(pinIncorect & numeIncorect) {
						System.out.println("Pin si nume gresite.");
					}else if(indexPin >= 0 & indexNume >= 0) {
						System.out.println("Pin sau nume gresit.");
					}else if(pinIncorect) {
						System.out.println("Pin gresit.");
					}else if (numeIncorect){
						System.out.println("Nume gresit.");
					}
					System.out.println("Mai aveti " + counter + " incercare.");
					
					numarIndexPin.removeAll(numarIndexPin);
					indexPin = -1;
					indexNume = -2;
				}else {
					if(pinIncorect & numeIncorect) {
						System.out.println("Pin si nume gresite.");
					}else if(indexPin >= 0 & indexNume >= 0) {
						System.out.println("Pin sau nume gresit.");
					}else if(pinIncorect) {
						System.out.println("Pin gresit.");
					}else if (numeIncorect){
						System.out.println("Nume gresit.");
					}
					System.out.println("Mai aveti " + counter + " incercari.");

					numarIndexPin.removeAll(numarIndexPin);
					indexPin = -1;
					indexNume = -2;
				}
				
			}
			
		}
		
		indexSold = indexPin;
		boolean soldInsuficient = true;
		boolean schimbarePin = true;
		boolean exit = true;
		int instructiune = 0;
		int suma = 0;
		int soldNou = 0;
		String raspunsAfirmativ = "Da";
		String pinNou;
		String pinConfirmat;
		
		while(a) {
			instructiune = scan.nextInt();
			
			if(instructiune < 1 | instructiune > 5) {
				System.out.println("Optiunea ta nu se incadreaza intre optiunile 1 si 5");
				System.out.println();
				optiuni.print();
			}else {
//				System.out.println(pinValabil.toString());
				switch (instructiune) {
				case 1:
					System.out.println("Soldul tau este de: " + sold.get(indexSold) + " lei.");
					break;
					
				case 2:
					do {
						soldInsuficient = true;
					System.out.println("Ce suma doriti sa retrageti?");
					suma = scan.nextInt();
					
					if(suma > sold.get(indexSold)) {
						System.out.println("Sold insuficient. Puteti retrage maxim "+ sold.get(indexSold) + " lei.");
					}else {
						soldNou = sold.get(indexSold) - suma;
						sold.remove(indexSold);
						sold.add(indexSold, soldNou);
						System.out.println("Soldul ramas este de " + sold.get(indexSold) + " lei.");
						soldInsuficient = false;
					}
					}while(soldInsuficient);
					break;
					
				case 3:
					System.out.println("Ce suma doriti sa depuneti?");
					suma = scan.nextInt();
					
					soldNou = sold.get(indexSold) + suma;
					sold.remove(indexSold);
					sold.add(indexSold, soldNou);
					System.out.println("Soldul dumneavoastra este de " + sold.get(indexSold) + " lei.");
					break;
					
				case 4:
					do {
					System.out.println("Introduceti noul pin.");
					pinNou = scan.next();
					System.out.println("Confirmati noul pinul.");
					pinConfirmat = scan.next();
					
					if(pinNou.length() < 4 | pinNou.length() > 4) {
						System.out.println("EROARE. Pinul trebuie sa aiba 4 caractere");
						System.out.println("Doriti sa incercati din nou? ");
						System.out.println("Raspundeti cu \"Da\" sau \"Nu\". ");
						String raspuns = scan.next();
						if(raspuns.equalsIgnoreCase(raspunsAfirmativ)) {
							schimbarePin = true;
						}else {
							schimbarePin = false;
						}
					}
					else if(pinNou.equals(pinConfirmat)) {
						pinValabil.remove(indexPin);
						pinValabil.add(indexPin, pinNou);
						System.out.println("Noul dumneavoastra pin este " + pinValabil.get(indexPin));
						schimbarePin = false;
					}else {
						System.out.println("EROARE. Pinul nou este diferit de pinul confirmat");
						System.out.println("Doriti sa incercati din nou? ");
						System.out.println("Raspundeti cu \"Da\" sau \"Nu\". ");
						String raspuns = scan.next();
						if(raspuns.equalsIgnoreCase(raspunsAfirmativ)) {
							schimbarePin = true;
						}else {
							schimbarePin = false;
						}
					}
					}while(schimbarePin);
					break;
				case 5: 
					exit = false;
					break;
				default:
					break;
				}
				if(exit) {
					System.out.println("Doriti sa mai efectuati o alta operatie?");
					System.out.println("Raspundeti cu \"Da\" sau \"Nu\". ");
					String raspuns = scan.next();
					if(raspuns.equalsIgnoreCase(raspunsAfirmativ)) {
						optiuni.print();
						
					}else {
						a=false;
						System.out.println("Operatie incheiata cu succes");
					}
				}else {
					a = false;
					System.out.println("Operatie incheiata cu succes");
				}
			}
		}	
	}
}	