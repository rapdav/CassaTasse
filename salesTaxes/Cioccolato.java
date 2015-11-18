package salesTaxes;

class Cioccolato extends Articolo {
	double prezzo;
	
	Cioccolato() {
		super();
		prezzo=0.85;
	}
	
	Cioccolato(double prezzo) {
		super();
		this.prezzo=prezzo;
	}
	
	Cioccolato(double prezzo, int isImported) {
		super(0,1);
		this.prezzo=prezzo;
	}

	double getPrezzo(){
		return prezzo;
	}
	
	void setPrezzo(double prezzo){
		this.prezzo=prezzo;
	}
	
	double getTassaApplicata(){
		return super.tassaApplicata;
	}
	
	double getTassaDaImportazione(){
		return super.tassaDaImportazione;
	}
	
}