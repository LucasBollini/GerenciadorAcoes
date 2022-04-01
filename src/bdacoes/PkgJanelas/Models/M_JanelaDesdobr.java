package bdacoes.PkgJanelas.Models;

import bdacoes.PkgGlobais.HandlerBd;
import bdacoes.PkgJanelas.Avulsos.CombosData;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class M_JanelaDesdobr {
    
    public JButton  btnConfirmar = new JButton("Confirmar");
    
    public JTextField   txtNome = new JTextField(),
                        txtQtd = new JTextField();
    
    public CombosData   objCombosData = new CombosData();
    
    public JLabel lblErro = new JLabel("", SwingConstants.CENTER);
    
    public void acaoDesdobrar() throws Exception{
        
        txtNome.setForeground(Color.black);
        txtQtd.setForeground(Color.black);
        
        if(!txtNome.getText().matches("[a-zA-Z]+[0-9]+")){
            txtNome.setForeground(Color.red);
            throw new Exception("Nome inválido");
        }
        
        if(!txtQtd.getText().matches("[0-9]+")){
            txtQtd.setForeground(Color.red);
            throw new Exception("Quantidade inválida");
        }
        
        if(!HandlerBd.addAcao(  txtNome.getText().toUpperCase(),
                                txtQtd.getText(),
                                "0",
                                "0",
                                "0",
                                (objCombosData.comboAno.getSelectedItem().toString() + "-" + objCombosData.comboMes.getSelectedItem().toString() + "-" + objCombosData.comboDia.getSelectedItem().toString()),
                                "D"))
            throw new Exception("Entrada Inválida");
    }
}