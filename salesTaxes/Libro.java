package salesTaxes;

class Libro extends Articolo {
	double prezzo;
	
	Libro() {
		super();
		prezzo=12.49;
	}
	
	Libro(double prezzo) {
		super();
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
