package salesTaxes;

class Pillole extends Articolo {
	double prezzo;
	
	Pillole() {
		super();
		prezzo=9.75;
	}
	
	Pillole(double prezzo) {
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