
import java.io.FileNotFoundException;

import hospital.HospitalException;
import hospital.HospitalPlus;

public class MainHospitalPlus {

	public static void main(String[] args) {
		try {
			HospitalPlus m = new HospitalPlus("Hospital Regional Universitario de Malaga", 50, 20);
			m.readPatients("patients.txt");
			System.out.println(m.patientsPerYear());
			System.out.println(m.numberOfPatientsPerYear());
		} catch (HospitalException e) {
			e.printStackTrace();
		}
	}

}

/**
 * Expected output:
 * 
 * <pre>
{1678=[Patient [name=Antonio, surname=Vivaldi, ssn=8414193157, birthYear=1678]], 1723=[Patient [name=Adam, surname=Smith, ssn=5186046392, birthYear=1723]], 1743=[Patient [name=Antonio, surname=Lavoisier, ssn=1566876261, birthYear=1743]], 1775=[Patient [name=Andre-Marie, surname=Ampere, ssn=7276995671, birthYear=1775]], 1781=[Patient [name=Andres, surname=Bello, ssn=3992731073, birthYear=1781]], 1788=[Patient [name=Arthur, surname=Schopenhauer, ssn=2805848874, birthYear=1788]], 1802=[Patient [name=Alejandro, surname=Dumas, ssn=6503158367, birthYear=1802]], 1809=[Patient [name=Abraham, surname=Lincoln, ssn=6669444861, birthYear=1809]], 1854=[Patient [name=Arthur, surname=Rimbaud, ssn=9462931693, birthYear=1854]], 1860=[Patient [name=Antonio, surname=Chekhov, ssn=6313288345, birthYear=1860]], 1870=[Patient [name=Amado, surname=Nervo, ssn=4413957112, birthYear=1870]], 1875=[Patient [name=Antonio, surname=Machado, ssn=0673932455, birthYear=1875], Patient [name=Albert, surname=Schweitzer, ssn=7030339829, birthYear=1875]], 1879=[Patient [name=Albert, surname=Einstein, ssn=7104083471, birthYear=1879]], 1881=[Patient [name=Alexander, surname=Kerensky, ssn=9051869743, birthYear=1881]], 1889=[Patient [name=Adolfo, surname=Hitler, ssn=5465995264, birthYear=1889]], 1890=[Patient [name=Agatha, surname=Chriestie, ssn=2895510826, birthYear=1890]], 1896=[Patient [name=Andre, surname=Breton, ssn=8365764592, birthYear=1896]], 1898=[Patient [name=Alonso, surname=Damaso, ssn=3885431592, birthYear=1898]], 1899=[Patient [name=Alfred, surname=Hitchcock, ssn=3635336290, birthYear=1899], Patient [name=Al, surname=Capone, ssn=3946671130, birthYear=1899]], 1900=[Patient [name=Agustin, surname=Lara, ssn=2876703738, birthYear=1900]], 1908=[Patient [name=Atahualpa, surname=Yupanqui, ssn=7263307860, birthYear=1908]], 1913=[Patient [name=Albert, surname=Camus, ssn=1710002791, birthYear=1913]], 1915=[Patient [name=Anthony, surname=Quinn, ssn=1435164914, birthYear=1915], Patient [name=Augusto, surname=Pinochet, ssn=5247602491, birthYear=1915]], 1916=[Patient [name=Aldo, surname=Moro, ssn=3745187275, birthYear=1916]], 1923=[Patient [name=Allen, surname=Ginsberg, ssn=5439262872, birthYear=1923], Patient [name=Alan, surname=Chepard, ssn=6489579680, birthYear=1923]], 1926=[Patient [name=Allan, surname=Greenspan, ssn=3546896340, birthYear=1926]], 1927=[Patient [name=Antonio Carlos, surname=Jobim, ssn=2344845664, birthYear=1927]], 1928=[Patient [name=Ariel, surname=Sharon, ssn=5460434336, birthYear=1928]], 1931=[Patient [name=Adolfo, surname=Perez-Esquivel, ssn=0837990171, birthYear=1931]], 1937=[Patient [name=Anthony, surname=Hopkins, ssn=8059089594, birthYear=1937]], 1938=[Patient [name=Annan, surname=Kofi, ssn=9428986747, birthYear=1938]], 1940=[Patient [name=Al, surname=Pacino, ssn=7611603235, birthYear=1940]], 1943=[Patient [name=Angelica, surname=Maria, ssn=5038997426, birthYear=1943]], 1947=[Patient [name=Arnold, surname=Schwarzenegger, ssn=7215395217, birthYear=1947]], 1948=[Patient [name=Al, surname=Gore, ssn=4262893352, birthYear=1948]], 1951=[Patient [name=Anatoly, surname=Karpov, ssn=5057523820, birthYear=1951]], 1954=[Patient [name=Angela, surname=Merkel, ssn=3856312192, birthYear=1954]], 1960=[Patient [name=Ayrton, surname=Senna, ssn=3262532049, birthYear=1960], Patient [name=Antonio, surname=Banderas, ssn=7169050132, birthYear=1960]], 1968=[Patient [name=Ashley, surname=Judd, ssn=7922298376, birthYear=1968]], 1971=[Patient [name=Adriana, surname=Karenbeu, ssn=0561128832, birthYear=1971]], 1975=[Patient [name=Angelina, surname=Jolie, ssn=8167807396, birthYear=1975]], 1981=[Patient [name=Alicia, surname=Keys, ssn=4488106841, birthYear=1981], Patient [name=Anna, surname=Kournikova, ssn=8418100035, birthYear=1981], Patient [name=Alessandra, surname=Ambrosio, ssn=8993637613, birthYear=1981]], 1984=[Patient [name=Avril, surname=Lavigne, ssn=7867894312, birthYear=1984]]}
{1678=1, 1723=1, 1743=1, 1775=1, 1781=1, 1788=1, 1802=1, 1809=1, 1854=1, 1860=1, 1870=1, 1875=2, 1879=1, 1881=1, 1889=1, 1890=1, 1896=1, 1898=1, 1899=2, 1900=1, 1908=1, 1913=1, 1915=2, 1916=1, 1923=2, 1926=1, 1927=1, 1928=1, 1931=1, 1937=1, 1938=1, 1940=1, 1943=1, 1947=1, 1948=1, 1951=1, 1954=1, 1960=2, 1968=1, 1971=1, 1975=1, 1981=3, 1984=1}
 * </pre>
 */