package bdacoes.PkgJanelas.Avulsos;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class CombosData {
    
    final private DefaultComboBoxModel  modelComboVinte = new DefaultComboBoxModel(), 
                                        modelComboTrinta = new DefaultComboBoxModel(), 
                                        modelComboTrinta1 = new DefaultComboBoxModel();
    
    final public JComboBox  comboDia = new JComboBox(),
                            comboMes = new JComboBox(),
                            comboAno = new JComboBox();
    
    public void attDia(boolean init){
        int dia = comboDia.getSelectedIndex() + 1;
        int mes = comboMes.getSelectedIndex() + 1;
        
        if(init){
            dia =  LocalDate.now().getDayOfMonth();
            mes = LocalDate.now().getMonthValue();
        }
        
        comboDia.setSelectedIndex(-1);
        
        if(mes == 2)
            comboDia.setModel(modelComboVinte);
        else
            if((mes % 2) == 0)
                if(mes < 7)
                    comboDia.setModel(modelComboTrinta);
                else
                    comboDia.setModel(modelComboTrinta1);
            else
                if(mes > 7)
                    comboDia.setModel(modelComboTrinta);
                else
                    comboDia.setModel(modelComboTrinta1);
        
        comboDia.setSelectedIndex(-1);
        
        try{
            comboDia.setSelectedIndex((dia - 1));
        }catch(Exception e){}
    }
    
    public void iniciarData(){
        comboMes.setSelectedIndex((LocalDate.now().getMonthValue() - 1));
        comboAno.setSelectedIndex(0);
        attDia(true);
    }
    
    public CombosData(){
        for(int i = 1; i < 10; i++){
            modelComboVinte.addElement("0" + i);
            modelComboTrinta.addElement("0" + i);
            modelComboTrinta1.addElement("0" + i);
        }
        for(int i = 10; i < 31; i++){
            if(i < 30)
                modelComboVinte.addElement("" + i);
            modelComboTrinta.addElement("" + i);
            modelComboTrinta1.addElement("" + i);
        }
        modelComboTrinta1.addElement("31");
        
        comboMes.setModel(new DefaultComboBoxModel(new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
        comboAno.setModel(new DefaultComboBoxModel(new String[]{"" + LocalDate.now().getYear(),"" + LocalDate.now().minusYears(1).getYear()}));
        
        comboMes.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                attDia(false);
            }
        });
        
        iniciarData();
    }
}