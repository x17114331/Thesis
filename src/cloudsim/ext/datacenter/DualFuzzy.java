/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudsim.ext.datacenter;

import net.sourceforge.jFuzzyLogic.FIS;

/**
 *
 * @author Bala J
 */
public class DualFuzzy {
    
    FIS fis1;
    FIS fis2;
    public static DualFuzzy inst=null;
    DualFuzzy()
    {
        loadFuzzy();
                
    }        
    
    public static DualFuzzy getInstance()
    {
        if (inst==null)
        {
            inst= new DualFuzzy();
            
        }
        return inst;
        
    }
    void loadFuzzy()
     {
           
            String fileName = "D1.fcl";
            fis1 = FIS.load(fileName,true);
            // Error while loading?
            if( fis1 == null ) {
                System.err.println("Can't load file: '"
                                       + fileName + "'");
                return;
            }

            // Show
            
            {
              fis1.chart();
            }
            
            fis2 = FIS.load("D2.fcl",true);
            // Error while loading?
            if( fis2 == null ) {
                System.err.println("Can't load file: '"
                                       + fileName + "'");
                return;
            }

            // Show
            
            {
              fis2.chart();
            }

           // System.out.println("Fuzzy trained . model created");


     }
    
    
     double getPreferScore(double I,double C,double AS)
     {
         fis1.setVariable("I", I);
         fis1.setVariable("C", C);
         fis1.setVariable("AS", AS);
         fis1.evaluate();
         double ps =  fis1.getVariable("PS").getValue();
         
         return ps;
         
     }
     int getTimeSlot(int A,int AT,double ra)
     {
           fis2.setVariable("A", A);
         fis2.setVariable("AT", AT);
         fis2.setVariable("RA", ra);
         fis2.evaluate();
         double ps =  fis2.getVariable("T").getValue();
         int p = (int)ps;
         return p;
         
     }
             
    
    
}
