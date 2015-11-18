package salesTaxes;

class Articolo{
	double tassaApplicata;
	double tassaDaImportazione;
	
	public Articolo() {
		tassaApplicata=0;
		tassaDaImportazione=0;
	}
	
	public Articolo(int isTaxable) {
		tassaApplicata=0.1;
		tassaDaImportazione=0;
	}
	
	public Articolo(int isTaxable, int isImported) {
		if(isTaxable==1)
			tassaApplicata=0.1;
		else
			tassaApplicata=0;
		tassaDaImportazione=0.05;
	}
	
	double getTassaApplicata(){
		return tassaApplicata;
	}
	
	double getTassaDaImportazione(){
		return tassaDaImportazione;
	}
}
