package salesTaxes;

import java.math.BigDecimal;

class Calcolatore {
	Articolo acquistato;
	double totTasse;
	double totale;
	
	Calcolatore(){
		totTasse=0;
		totale=0;
	}
	
	void resetCalcolatore(){
		totTasse=0;
		totale=0;
	}
	
	void acquistaArticolo(String articolo, int isImportato) {
		if(articolo.equals("libro"))
			acquistato=new Libro();
		if(articolo.equals("profumo"))
			acquistato=new Profumo();
		if(articolo.equals("cd"))
			acquistato=new CD();
		if(articolo.equals("cioccolato"))
			acquistato=new Cioccolato();
		if(articolo.equals("pillole"))
			acquistato=new Pillole();
	}
	
	void acquistaArticolo(String articolo, int isImportato, double prezzo) {
		if(isImportato==0){
			if(articolo.equals("libro"))
				acquistato=new Libro(prezzo);
			if(articolo.equals("profumo"))
				acquistato=new Profumo(prezzo);
			if(articolo.equals("cd"))
				acquistato=new CD(prezzo);
			if(articolo.equals("cioccolato"))
				acquistato=new Cioccolato(prezzo);
			if(articolo.equals("pillole"))
				acquistato=new Pillole(prezzo);
		}else{
			if(articolo.startsWith("profumo"))
				acquistato=new Profumo(prezzo,1);
			if(articolo.startsWith("cioccolato"))
				acquistato=new Cioccolato(prezzo,1);
		}
	}
	
	void addebitaArticolo(String articolo, int isImportato, RegistratoreCassa cassa, String descrizione){
		double prezzoUnitario=0;
		if(articolo.startsWith("libro") && acquistato instanceof Libro)
			prezzoUnitario=((Libro)acquistato).getPrezzo();	
		if(articolo.startsWith("cd") && acquistato instanceof CD)
			prezzoUnitario=((CD)acquistato).getPrezzo();
		if(articolo.startsWith("profumo") && acquistato instanceof Profumo)
			prezzoUnitario=((Profumo)acquistato).getPrezzo();
		if(articolo.startsWith("cioccolato") && acquistato instanceof Cioccolato)
			prezzoUnitario=((Cioccolato)acquistato).getPrezzo();
		if(articolo.startsWith("pillole") && acquistato instanceof Pillole)
			prezzoUnitario=((Pillole)acquistato).getPrezzo();
		
		if(acquistato.getTassaApplicata()+acquistato.getTassaDaImportazione()==0)
			cassa.scontrino.append("1 "+descrizione+" "+formattaImporto(((acquistato.getTassaApplicata()+acquistato.getTassaDaImportazione())*prezzoUnitario)+prezzoUnitario)+"\n\r");
		else
			cassa.scontrino.append("1 "+descrizione+" "+formattaImporto(new BigDecimal(((acquistato.getTassaApplicata()+acquistato.getTassaDaImportazione())*prezzoUnitario)+prezzoUnitario).setScale(2,BigDecimal.ROUND_UP).doubleValue())+"\n\r");
		totTasse=((acquistato.getTassaApplicata()+acquistato.tassaDaImportazione)*prezzoUnitario)+totTasse;
		totale=totale+prezzoUnitario;
	}
	
	void eseguiTotale(RegistratoreCassa cassa){
		cassa.scontrino.append("Tasse applicate: "+formattaImporto(new BigDecimal(totTasse).setScale(2,BigDecimal.ROUND_UP).doubleValue())+"\n\r");
		cassa.scontrino.append("Importo totale: "+formattaImporto(new BigDecimal(totTasse+totale).setScale(2,BigDecimal.ROUND_UP).doubleValue())+"\n\r");	
	}
	
	String formattaImporto(double importo){
		String valore=new Double(importo).toString();
		if(valore.length()-valore.indexOf('.')<=2)
			return valore+"0";
		else
			return valore;
	}

}
