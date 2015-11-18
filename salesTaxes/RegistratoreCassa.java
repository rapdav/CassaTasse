package salesTaxes;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class RegistratoreCassa implements ActionListener{
	final String[] nationalItems={"libro","cd","cioccolato","profumo","pillole"};
	final String[] foreignItems={"cioccolato_da_10","profumo_da_4750","profumo_da_2799","cioccolato_da_1125"};
	final double[] foreignPrices={10.00,47.50,27.99,11.25};
	final String[] etichette={"Libro","CD","Barretta cioccolato","Profumo","Pillole"};
	final String[] labels={"Scatola cioccolatini a 10.00","Profumo a 47.50","Profumo a 27.99","Scatola cioccolatini a 11.25"};
	JFrame finestra;
	JPanel nazionali, esteri, azioni, display, pannelloArticoliNaz, pannelloArticoliEst;
	JTextArea scontrino;
	JButton totale;
	JButton[] articoliNazionali;
	JButton[] articoliImportati;
	Container contenitore;
	Calcolatore calcCassa;
	JScrollPane scroll;
	int flagTotaleEseguito=0;
	
	public RegistratoreCassa(){
		finestra=new JFrame("Cassa");
		finestra.setLocation(100,100);
		finestra.setResizable(true);
		finestra.setEnabled(true);
		finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contenitore=finestra.getContentPane();
		contenitore.setLayout(new GridLayout(4,1));
		nazionali=new JPanel();
		pannelloArticoliNaz=new JPanel();
		nazionali.setLayout(new GridLayout(2,1));
		pannelloArticoliNaz.setLayout(new FlowLayout());
		esteri=new JPanel();
		pannelloArticoliEst=new JPanel();
		esteri.setLayout(new GridLayout(2,1));
		pannelloArticoliEst.setLayout(new FlowLayout());
		azioni=new JPanel();
		azioni.setLayout(new FlowLayout());
		display=new JPanel();
		display.setLayout(new FlowLayout());
		
		addElements();
		
		contenitore.add(nazionali);
		contenitore.add(esteri);
		contenitore.add(azioni);
		contenitore.add(display);
		
		finestra.setSize(800, 550);
		finestra.setResizable(false);
		finestra.setVisible(true);	
		
		calcCassa=new Calcolatore();
	}
	
	public void addElements(){
		nazionali.add(new JLabel("---- Articoli nazionali --------------"));
		articoliNazionali=new JButton[nationalItems.length];
		for(int i=0;i<nationalItems.length;i++){
			articoliNazionali[i]=new JButton();
			articoliNazionali[i].setText(etichette[i]);
			articoliNazionali[i].setName(nationalItems[i].trim()+"NazItems");
			pannelloArticoliNaz.add(articoliNazionali[i]);
			articoliNazionali[i].addActionListener(this);
			
		}
		nazionali.add(pannelloArticoliNaz);
		
		esteri.add(new JLabel("---- Articoli esteri --------------"));
		articoliImportati=new JButton[foreignItems.length];
		for(int i=0;i<foreignItems.length;i++){
			articoliImportati[i]=new JButton();
			articoliImportati[i].setText(labels[i]);
			articoliImportati[i].setName(foreignItems[i].trim()+"EstItems");
			pannelloArticoliEst.add(articoliImportati[i]);
			articoliImportati[i].addActionListener(this);
		}
		esteri.add(pannelloArticoliEst);
		
		totale=new JButton();
		totale.setText("TOTALE");
		totale.setName("totale");
		totale.setEnabled(false);
		totale.addActionListener(this);
		azioni.add(totale);
		
		scontrino=new JTextArea(6,40);
		scroll = new JScrollPane(scontrino);
		display.add(scroll);
	}

	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource().toString().contains("NazItems")){
			if(flagTotaleEseguito==1){
				scontrino.setText("");
				flagTotaleEseguito=0;
			}
			String articolo=e.getSource().toString().substring(e.getSource().toString().indexOf('[')+1,e.getSource().toString().indexOf("NazItems"));
			calcCassa.acquistaArticolo(articolo,0);
			calcCassa.addebitaArticolo(articolo,0,this,e.getActionCommand().trim());
			if(!totale.isEnabled()){
				totale.setEnabled(true);
			}
		}
		
		if(e.getSource().toString().contains("EstItems")){
			if(flagTotaleEseguito==1){
				scontrino.setText("");
				flagTotaleEseguito=0;
			}
			String descArticoloImportato="";
			String articolo=e.getSource().toString().substring(e.getSource().toString().indexOf('[')+1,e.getSource().toString().indexOf("EstItems"));
			for(int i=0;i<=foreignItems.length;i++)
				if(articolo.equals(foreignItems[i])){
					calcCassa.acquistaArticolo(articolo,1,foreignPrices[i]);
					descArticoloImportato=(foreignItems[i].contains("cioccolato"))?"scatola di cioccolatini da importazione":"profumo da importazione";
					break;
				}
			calcCassa.addebitaArticolo(articolo,1,this,descArticoloImportato);
			if(!totale.isEnabled()){
				totale.setEnabled(true);
			}
		}
		
		if(e.getSource().toString().contains("[totale")){
			calcCassa.eseguiTotale(this);
			flagTotaleEseguito=1;
			totale.setEnabled(false);
			calcCassa.resetCalcolatore();
		}
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				RegistratoreCassa registratore=new RegistratoreCassa();
			}
		});
	}

}
