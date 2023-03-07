package controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import modeles.GrapheModele;

public class MenuControleur implements ActionListener,ItemListener{

	GrapheModele m;
    vues.Menu menu;
	
	public MenuControleur(GrapheModele m,vues.Menu menu2){
		this.m=m;
		this.menu = menu2;
	}
///////////////////////////////////////////////////////////////
	
	public void actionPerformed(ActionEvent E) {	
		
		   if (E.getActionCommand().equals("algo")){
			        
			   if(m.getParEtapes()){
				  m.setParEtapes(false);
			   }
			   if(m.isKruskal() && !m.getParEtapes()){
				  m.kruskal();
			   }
			   if(m.isPrim() && !m.getParEtapes()){
				  m.prim();
			   }
		   }
		   
		   if (E.getActionCommand().equals("efface")){
				m.effacerTout();
			}

		   
		   if (E.getActionCommand().equals("compléter")){
				m.completer();
			}
		   
		   
		   if (E.getActionCommand().equals("suivant")){
			   
			     if(!m.getParEtapes()){
			              m.setParEtapes(true);
				 }
		         int etape = m.getEtape();
		         m.setEtape(etape +1);
		         
			     if(m.conditionsEtape()){
				          if(m.isKruskal()){
				                   m.kruskalParEtapes();
				          }
				          if(m.isPrim()){
				        	      m.primParEtapes();
				          }
			     }
			     else{
			    	 System.out.println("conditions non respectée");
			     }
			} 
		   
		   if(E.getActionCommand().equals("précédent")){
			   
			      if(!m.getParEtapes()){
				  m.setParEtapes(true);
			      }	 
	    	      int etape = m.getEtape();
	              m.setEtape(etape - 1);
	              
			      if(m.conditionsEtape()){               
					      if(m.isKruskal()){
				                  m.kruskalParEtapes();
				          }
				          if(m.isPrim()){
				        	      m.primParEtapes();
				          }
			      }else{
				    System.out.println("conditions non respectée");
			      }
		   }
	}

/////////////////////////////////////////////////////////////////
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		
		Object source = arg0.getSource();
		if(source == menu.getChoixTheme()){
			String s = (String) arg0.getItem();
			m.setTheme(s);
		
		}
		if(source == menu.getKruskal()){
			if(m.getParEtapes()){
				m.setEtape(0);
			}
			m.setKruskal(true);
			m.setPrim(false);
		}
		if(source == menu.getPrim()){
			if(m.getParEtapes()){
				m.setEtape(0);
			}
			m.setPrim(true);
			m.setKruskal(false);
		}

	}
	
///////////////////////////////////////////////////////////////

}
