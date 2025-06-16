import java.io.FileNotFoundException;
import java.io.PrintWriter;

import covid.PerimeterClosureInfo;
import covid.COVIDInfo;
import covid.PopulationInfo;
import covid.COVIDMap;

/**
 * Test class of the prMapaCOVID project. 
 */
public class TestCOVIDMap {
	public static void main(String[] args) throws FileNotFoundException {
		// We create a COVIDMap object with data from Andalusia, updated on June 2, 2021
		COVIDMap mapa = new COVIDMap("Andalucia", "datosCOVID-dist.csv");

		// We print on the screen the information of the COVID map of Andalusia
		mapa.printMap(new PrintWriter(System.out, true));

		// We save in a file the information of the COVID map of Andalusia
		mapa.printMap("COVIDMapAndalucia.txt");

		// We define a variable to obtain information. Initially, it is initialized
		// to null
		COVIDInfo info = null;

		// We upload information on the population ranges of the health districts on the
		// screen
		System.out.println("\nINFORMATION ON HEALTH DISTRICTS WITH");
		int inc = 100000;
		for (int pob = 0; pob < 600000; pob += inc) {
			info = new PopulationInfo(pob, pob + inc);
			System.out.println("POPULATION BETWEEN " + pob + " AND " + (pob + inc) + ": \n\t" + mapa.getCOVIDInfo(info));
		}
		info = new PopulationInfo(600000, 2000000);
		System.out.println("POPULATION GREATER THAN 600000: \n\t" + mapa.getCOVIDInfo(info));

		// We print information about provinces with perimeter closure 
		info = new PerimeterClosureInfo();
		System.out.print("\nINFORMATION ON PROVINCES WITH PERIMETER CLOSURE: ");
		System.out.println(mapa.getCOVIDInfo(info));
	}
}

/*
<pre>
Expected output:
ANDALUCIA: 
Almeria
	District(Almeria Distrito, 246)
	District(Levante-Alto Almanzora, 119)
	District(Poniente de Almeria, 157)
Cadiz
	District(Bahia de Cadiz-La Janda, 542)
	District(Campo de Gibraltar Este, 93)
	District(Campo de Gibraltar Oeste, 251)
	District(Jerez-Costa Noroeste, 397)
	District(Sierra de Cadiz, 253)
Cordoba
	District(Cordoba Distrito, 558)
	District(Cordoba Norte, 149)
	District(Cordoba Sur, 355)
	District(Guadalquivir, 101)
Granada
	District(Granada Distrito, 709)
	District(Granada Nordeste, 63)
	District(Granada Sur, 234)
	District(Metropolitano de Granada, 1019)
Huelva
	District(Condado-Campina, 572)
	District(Huelva-Costa, 691)
	District(Sierra de Huelva-Andavalo Central, 112)
Jaen
	District(Jaen, 482)
	District(Jaen Nordeste, 204)
	District(Jaen Norte, 602)
	District(Jaen Sur, 259)
Malaga
	District(Axarquia, 184)
	District(Costa del Sol, 761)
	District(La Vega, 291)
	District(Malaga Distrito, 761)
	District(Serrania, 99)
	District(Valle del Guadalhorce, 262)
Sevilla
	District(Aljarafe, 663)
	District(Sevilla Distrito, 1194)
	District(Sevilla Este, 693)
	District(Sevilla Norte, 1317)
	District(Sevilla Sur, 638)

INFORMATION ON HEALTH DISTRICTS WITH
POPULATION BETWEEN 0 AND 100000: 
	[Cordoba Norte, Granada Nordeste, Jaen Sur, Serrania, Sierra de Huelva-Andavalo Central]
POPULATION BETWEEN 100000 AND 200000: 
	[Axarquia, Campo de Gibraltar Este, Campo de Gibraltar Oeste, Condado-Campina, Granada Sur, Guadalquivir, Jaen, Jaen Nordeste, Jaen Norte, La Vega, Levante-Alto Almanzora, Sevilla Este, Sierra de Cadiz, Valle del Guadalhorce]
POPULATION BETWEEN 200000 AND 300000: 
	[Cordoba Sur, Granada Distrito, Huelva-Costa, Poniente de Almeria, Sevilla Norte]
POPULATION BETWEEN 300000 AND 400000: 
	[Aljarafe, Almeria Distrito, Cordoba Distrito, Jerez-Costa Noroeste]
POPULATION BETWEEN 400000 AND 500000: 
	[Metropolitano de Granada, Sevilla Sur]
POPULATION BETWEEN 500000 AND 600000: 
	[Bahia de Cadiz-La Janda, Costa del Sol]
POPULATION GREATER THAN 600000: 
	[Malaga Distrito, Sevilla Distrito]

INFORMATION ON PROVINCES WITH PERIMETER CLOSURE: []
</pre>
 */ 