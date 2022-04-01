package bdacoes.PkgJanelas.Models;

import bdacoes.PkgGlobais.HandlerBd;
import bdacoes.PkgJanelas.Avulsos.CombosData;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class M_JanelaAdd {
    
    public JButton  btnConfirmar = new JButton("Confirmar");
    
    public JTextField   txtNome = new JTextField(),
                        txtQtd = new JTextField(),
                        txtValor = new JTextField(),
                        txtCorret = new JTextField(),
                        txtEmol = new JTextField();
    
    public JComboBox    comboTipo = new JComboBox(new String[]{"Compra","Venda"});
    
    public CombosData   objCombosData = new CombosData();
    
    public JLabel lblErro = new JLabel("", SwingConstants.CENTER);
    
    public void acaoAdicionar() throws Exception{
        
        txtNome.setForeground(Color.black);
        txtQtd.setForeground(Color.black);
        txtValor.setForeground(Color.black);
        txtCorret.setForeground(Color.black);
        txtEmol.setForeground(Color.black);
        
        if(!txtNome.getText().matches("[a-zA-Z]+[0-9]+")){
            txtNome.setForeground(Color.red);
            throw new Exception("Nome inválido");
        }

        if(!txtQtd.getText().matches("[0-9]+")){
            txtQtd.setForeground(Color.red);
            throw new Exception("Quantidade inválida");
        }
        
        if(!txtValor.getText().matches("[0-9]+[,\\.]?[0-9]*")){
            txtValor.setForeground(Color.red);
            throw new Exception("Valor inválido");
        }
        
        if(!txtCorret.getText().matches("[0-9]+[,\\.]?[0-9]*")){
            txtCorret.setForeground(Color.red);
            throw new Exception("Correntagem inválida");
        }
        
        if(!txtEmol.getText().matches("[0-9]+[,\\.]?[0-9]*")){
            txtEmol.setForeground(Color.red);
            throw new Exception("Emolumento inválido");
        }
        
        if(!HandlerBd.addAcao(  txtNome.getText().toUpperCase(),
                            txtQtd.getText(),
                            txtValor.getText(),
                            txtCorret.getText(),
                            txtEmol.getText(),
                            (objCombosData.comboAno.getSelectedItem().toString() + "-" + objCombosData.comboMes.getSelectedItem().toString() + "-" + objCombosData.comboDia.getSelectedItem().toString()),
                            (comboTipo.getSelectedItem().toString().equals("Compra") ? "C" : "V")))
            throw new Exception("Entrada inválida");
    }
}