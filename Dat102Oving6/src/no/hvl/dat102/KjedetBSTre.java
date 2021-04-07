package no.hvl.dat102;

import java.util.Iterator;

import no.hvl.dat102.adt.BSTreADT;

//********************************************************************
// KjedetBinærSøkeTre.java        
//
//********************************************************************

public class KjedetBSTre<T extends Comparable<T>> implements BSTreADT<T>, Iterable<T> {

	private int antall;

	/**
	 * @return the rot
	 */
	public BinaerTreNode<T> getRot() {
		return rot;
	}

	/**
	 * @param rot the rot to set
	 */
	public void setRot(BinaerTreNode<T> rot) {
		this.rot = rot;
	}

	private BinaerTreNode<T> rot;

	/******************************************************************
	 * Oppretter et tomt binært søketre.
	 ******************************************************************/
	public KjedetBSTre() {
		antall = 0;
		rot = null;
	}

	/******************************************************************
	 * Oppretter et binært søketre med en node..
	 ******************************************************************/
	public KjedetBSTre(T element) {
		rot = new BinaerTreNode<T>(element);
		antall = 1;
	}

	/*****************************************************************
	 * Returnerer sann hvis dette binære trett er tomt og usann ellers.
	 *****************************************************************/
	@Override
	public int antall() {
		return antall;
	}

	/*****************************************************************
	 * Returnerer sann hvis dette binære treet er tom og usann ellers.
	 *****************************************************************/
	@Override
	public boolean erTom() {
		return (antall == 0);
	}

	/**********************************************************************
	 * Legger det spesifiserte elementet på passende plass i BS-treet. Like
	 * elementer blir lagt til høyre. Bruk av rekursiv hjelpemetode.
	 ********************************************************************/
	@Override
	public void leggTil(T element) {
		rot = leggTilRek(rot, element);
		antall++;
	}

	private BinaerTreNode<T> leggTilRek(BinaerTreNode<T> p, T element) {

		if (p == null) {
			p = new BinaerTreNode<T>(element);
		} else if (element.compareTo(p.getElement()) > 0) {
			p.setVenstre(leggTilRek(p.getVenstre(), element));
		} else {
			p.setHoyre(leggTilRek(p.getHoyre(), element));
		}
		return p;
	}

	/******************************************************************
	 * Legger det spesifiserte elementet på passende plass i dette binære søketreet.
	 * Like elementer blir lagt til høyre.
	 ******************************************************************/

	public void leggTil2(T element) {
		//
	}

	/******************************************************************
	 * Fjerner noden med minste verdi fra dette binære søketreet.
	 *********************************************************************/
	@Override
	public T fjernMin() {

		T minVerdi = null;

		if (!erTom()) {

			if (rot.getVenstre() == null) {
				minVerdi = rot.getElement();
			} else {
				BinaerTreNode<T> aktuell;
				aktuell = rot.getVenstre();
				BinaerTreNode<T> foreldre = rot;

				while (aktuell.getVenstre() != null) {
					foreldre = aktuell;
					aktuell = aktuell.getVenstre();
				}
				minVerdi = aktuell.getElement();
				foreldre.setVenstre(aktuell.getHoyre());
			}
			antall--;
		}
		return minVerdi;
	}//

	/******************************************************************
	 * Fjerner noden med største verdi fra dette binære søketreet.
	 ******************************************************************/
	@Override
	public T fjernMaks() {

		T maxVerdi = null;

		if (!erTom()) {

			if (rot.getHoyre() == null) {
				maxVerdi = rot.getElement();
			} else {
				BinaerTreNode<T> aktuell;
				aktuell = rot.getHoyre();
				BinaerTreNode<T> foreldre = rot;

				while (aktuell.getHoyre() != null) {
					foreldre = aktuell;
					aktuell = aktuell.getHoyre();
				}
				maxVerdi = aktuell.getElement();
				foreldre.setHoyre(aktuell.getVenstre());
			}
			antall--;
		}
		return maxVerdi;
	}//

	/******************************************************************
	 * Returnerer det minste elementet i dette binære søketreet.
	 ******************************************************************/
	@Override
	public T finnMin() {

		T minVerdi = null;

		if (!erTom()) {

			if (rot.getVenstre() == null) {
				minVerdi = rot.getElement();
			} else {
				BinaerTreNode<T> aktuell;
				aktuell = rot.getVenstre();

				while (aktuell.getVenstre() != null) {
					aktuell = aktuell.getVenstre();
				}
				minVerdi = aktuell.getElement();
			}
		}
		return minVerdi;
	}//

	/******************************************************************
	 * Returnerer det største elementet i dette binære søketreet.
	 ******************************************************************/
	@Override
	public T finnMaks() {

		T maxVerdi = null;

		if (!erTom()) {
			if (rot.getHoyre() == null) {
				maxVerdi = rot.getElement();
			} else {
				BinaerTreNode<T> aktuell;
				aktuell = rot.getHoyre();

				while (aktuell.getHoyre() != null) {
					aktuell = aktuell.getHoyre();
				}
				maxVerdi = aktuell.getElement();
			}
		}
		return maxVerdi;
	}//

	/*******************************************************************************
	 * Returnerer en referanse til det spesifiserte elementet hvis det finst i dette
	 * BS-treet, null ellers. Bruk av rekursjon /
	 ******************************************************************************/
	@Override
	public T finn(T element) {
		// Søk med rekursiv hjelpemetode

		return finnRek(element, rot);
	}

	// Den rekursive hjelpemetoden for søking

	private T finnRek(T element, BinaerTreNode<T> p) {

		T resultat = null;

		if (p != null) {

			if (element.compareTo(p.getElement()) > 0) {
				resultat = finnRek(element, p.getHoyre());
			} else if (element.compareTo(p.getElement()) < 0) {
				resultat = finnRek(element, p.getVenstre());
			} else {
				resultat = p.getElement();
			}
		}
		return resultat;
	}

	/************************************************************************
	 * Returnerer en referanse til det spesifiserte elementet hvis det fins i dette
	 * BS-treet, null ellers. Uten bruk av rekursjon. /
	 ************************************************************************/
	public T finn2(T element) {

		T resultat = null;
		BinaerTreNode<T> p = rot;

		while (p != null && resultat == null) {
			if (element.compareTo(p.getElement()) > 0) {
				p = p.getHoyre();
			}
			if (element.compareTo(p.getElement()) < 0) {
				p = p.getHoyre();
			} else {
				resultat = p.getElement();
			}
		}
		return resultat;
	}

	public int binaerTreHoyde() {
		
		return binaerTreHoydeRek(rot);
	}

	private int binaerTreHoydeRek(BinaerTreNode<T> p) {

		int maxHoyde, venstre, hoyre;
		
		if(p == null) {
			maxHoyde = -1;
		}
		else {
			venstre = binaerTreHoydeRek(p.getVenstre());
			hoyre = binaerTreHoydeRek(p.getHoyre());
			maxHoyde = Math.max(venstre + 1, hoyre + 1);
		}
		return maxHoyde;
	}

	public void visInorden() {
		visInorden(rot);
		System.out.println();
	}

	private void visInorden(BinaerTreNode<T> p) {
		if (p != null) {
			visInorden(p.getVenstre());
			System.out.print(" " + p.getElement());
			visInorden(p.getHoyre());
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new InordenIterator<T>(rot);

	}

}// class
