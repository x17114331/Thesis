/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ResultSummary.java
 *
 * Created on Nov 27, 2013, 9:15:29 AM
 */

package cloudsim.ext.gui;

import cloudsim.ext.datacenter.FileAppender;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


/**
 *
 * @author bala
 */
public class ResultSummary extends javax.swing.JFrame {

    /** Creates new form ResultSummary */

   
    public ResultSummary() {
        initComponents();

        //generateValues();
       

    }


    void addToAtable(String vmno,String count)
    {
        DefaultTableModel df = (DefaultTableModel)Atable.getModel();
        
        String [] res = new String[2];
        res[0] = vmno;
        res[1] = count;
        
        df.addRow(res);


    }

    void addToOTable(String vmno,String count)
    {

        DefaultTableModel df = (DefaultTableModel)Otable.getModel();

        String [] res = new String[2];
        res[0] = vmno;
        res[1] = count;

        df.addRow(res);


    }

    void addToEtable(String vmno,String count)
    {
        DefaultTableModel df = (DefaultTableModel)Etable.getModel();

        String [] res = new String[2];
        res[0] = vmno;
        res[1] = count;

        df.addRow(res);




    }


    

     private XYDataset createDatasetForPerfChart(String filename)
    {
       final XYSeriesCollection dataset = new XYSeriesCollection();


       XYSeries  A = new XYSeries("Least Connection");
       XYSeries  O = new XYSeries("Reliable Maximum Utility");
       XYSeries  E = new XYSeries("Round Robin");
       


       try
       {
           FileInputStream fstream = new FileInputStream(filename);
           DataInputStream in = new DataInputStream(fstream);
           BufferedReader br = new BufferedReader(new InputStreamReader(in));

           String strLine;
           //Read File Line By Line
           while ((strLine = br.readLine()) != null)   {
              // Print the content on the console
              System.out.println (strLine);

              String [] tem = strLine.split("#");

              if(tem[0].equals("A"))
              {
                
                 A.add(Double.parseDouble(tem[1]),Double.parseDouble(tem[2])) ;
              }
              else if(tem[0].equals("O"))
              {
                 
                 O.add(Double.parseDouble(tem[1]),Double.parseDouble(tem[2])) ;
              }
              else if(tem[0].equals("E"))
              {
                
                 E.add(Double.parseDouble(tem[1]),Double.parseDouble(tem[2])) ;
              }
             


           }
           //Close the input stream
           in.close();



       }
       catch(Exception e)
       {
           e.printStackTrace();
       }

       dataset.addSeries(A);
       dataset.addSeries(O);
       dataset.addSeries(E);
       



       return dataset;


    }


     private JFreeChart createChartForPerfChart(final XYDataset dataset, String tit,String xl,String yl) {

        // create the chart...
        final JFreeChart chart = ChartFactory.createXYLineChart(
            tit,      // chart title
            xl,                      // x axis label
            yl,                      // y axis label
            dataset,                  // data
            PlotOrientation.VERTICAL,
            true,                     // include legend
            true,                     // tooltips
            false                     // urls
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        chart.setBackgroundPaint(Color.white);

//        final StandardLegend legend = (StandardLegend) chart.getLegend();
  //      legend.setDisplaySeriesShapes(true);

        // get a reference to the plot for further customisation...
        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
    //    plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, true);
        renderer.setSeriesShapesVisible(1, true);
        plot.setRenderer(renderer);

        //change the auto tick unit selection to integer units only...
        //final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        //rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        //OPTIONAL CUSTOMISATION COMPLETED.

        return chart;

    }

    void displayAllocation()
    {
       try
       {
           FileInputStream fstream = new FileInputStream("Perfg1.txt");
           DataInputStream in = new DataInputStream(fstream);
           BufferedReader br = new BufferedReader(new InputStreamReader(in));

           String strLine;
           //Read File Line By Line
           while ((strLine = br.readLine()) != null)   {
              // Print the content on the console
              System.out.println (strLine);

              String [] tem = strLine.split("#");

              if(tem[0].equals("A"))
              {
                 addToAtable(tem[1],tem[2]);
                
              }
              else if(tem[0].equals("O"))
              {
                 addToOTable(tem[1],tem[2]);
                
              }
              else if(tem[0].equals("E"))
              {
                 addToEtable(tem[1],tem[2]);
                
              }
             


           }
           //Close the input stream
           br.close();
           in.close();
           fstream.close();


       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
        
        
        
    }
   

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        showGraph = new javax.swing.JButton();
        perfpanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Atable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        Otable = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        Etable = new javax.swing.JTable();
        opti = new javax.swing.JComboBox();
        clearPrevHistory = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Result Summary");

        showGraph.setText("SHOW GRAPH");
        showGraph.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showGraphActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout perfpanelLayout = new javax.swing.GroupLayout(perfpanel);
        perfpanel.setLayout(perfpanelLayout);
        perfpanelLayout.setHorizontalGroup(
            perfpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 603, Short.MAX_VALUE)
        );
        perfpanelLayout.setVerticalGroup(
            perfpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 331, Short.MAX_VALUE)
        );

        Atable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "VMNo", "Allocations"
            }
        ));
        jScrollPane1.setViewportView(Atable);

        Otable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "VMNo", "Allocations"
            }
        ));
        jScrollPane2.setViewportView(Otable);

        Etable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "VMNo", "Allocations"
            }
        ));
        jScrollPane3.setViewportView(Etable);

        opti.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Resource Utilization", "VM Cost", "Allocation" }));

        clearPrevHistory.setText("CLEAR PREV HISTORY");
        clearPrevHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearPrevHistoryActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Traditional Arabic", 3, 11)); // NOI18N
        jLabel1.setText("Round Robin Balancer");

        jLabel2.setFont(new java.awt.Font("Traditional Arabic", 3, 11)); // NOI18N
        jLabel2.setText("Least Connection Balancer");

        jLabel3.setFont(new java.awt.Font("Traditional Arabic", 3, 11)); // NOI18N
        jLabel3.setText("Reliable Maximum Utility");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(perfpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(showGraph, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(opti, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addComponent(clearPrevHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(66, 66, 66)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(showGraph, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(clearPrevHistory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(opti, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(perfpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(129, 129, 129))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void showGraphActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showGraphActionPerformed
        // TODO add your handling code here:

        XYDataset dataset = null;
        JFreeChart chart = null;

        
        int o = opti.getSelectedIndex();

         if (o==0)
        {
             dataset = createDatasetForPerfChart("Perfg2.txt");
             chart = createChartForPerfChart(dataset,"Resource Utilization",
                                                 "Request",
                                                 "Utilization(%)");

            
        }
        else if(o==1)
        {
             dataset = createDatasetForPerfChart("Perfg3.txt");
             chart = createChartForPerfChart(dataset,"VM cost",
                                                 "Request",
                                                 "Cost(Rs)");
            
        }        
        else if (o==2)
        {
             displayAllocation();
             return;
            
        }
        final ChartPanel chartPanel = new ChartPanel(chart);
        //JFrame jf = new JFrame("hello");
        //jf.setContentPane(chartPanel);

        final File file1 = new File("g1.png");
        try
        {
           ChartUtilities.saveChartAsPNG(file1, chart, 400, 250);

           BufferedImage image = ImageIO.read(file1);

           perfpanel.getGraphics().drawImage(image, 0, 0, null);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        



    }//GEN-LAST:event_showGraphActionPerformed

    private void clearPrevHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearPrevHistoryActionPerformed
        // TODO add your handling code here:
        
        try
        {
            File fx = new File("Perfg1.txt");
            fx.delete();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
         
        try
        {
            File fx = new File("Perfg2.txt");
            fx.delete();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
         
        try
        {
            File fx = new File("Perfg3.txt");
            fx.delete();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
         
        JOptionPane.showMessageDialog(this, "Previous performance results cleared");
        
    }//GEN-LAST:event_clearPrevHistoryActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ResultSummary().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Atable;
    private javax.swing.JTable Etable;
    private javax.swing.JTable Otable;
    private javax.swing.JButton clearPrevHistory;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JComboBox opti;
    private javax.swing.JPanel perfpanel;
    private javax.swing.JButton showGraph;
    // End of variables declaration//GEN-END:variables

}
