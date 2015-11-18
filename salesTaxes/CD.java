package salesTaxes;

class CD extends Articolo {
	double prezzo;
	
	CD() {
		super(1);
		prezzo=14.99;
	}
	
	CD(double prezzo) {
		super(1);
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
