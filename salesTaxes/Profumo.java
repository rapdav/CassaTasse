package salesTaxes;

class Profumo extends Articolo {
	double prezzo;
	
	Profumo() {
		super(1);
		prezzo=18.99;
	}
	
	Profumo(double prezzo) {
		super(1);
		this.prezzo=prezzo;
	}
	
	Profumo(double prezzo, int isImported) {
		super(1,1);
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