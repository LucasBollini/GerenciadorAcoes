package bdacoes.PkgJanelas.Views;

import bdacoes.PkgJanelas.Models.M_JanelaMensal;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class V_JanelaMensal extends JFrame{
    
    public V_JanelaMensal(String stringData, M_JanelaMensal model){        
        
        JPanel painelConteudo = new JPanel();
        painelConteudo.setPreferredSize(new Dimension(1000,600));
        painelConteudo.setLayout(null);
        
        model.modelE.setColumnIdentifiers(new String [] {"Nome","Data","Qtd","Valor","Corret","Emol","Total"});
        model.tabelaE.getColumnModel().getColumn(0).setMaxWidth(60);
        model.tabelaE.getColumnModel().getColumn(2).setMaxWidth(40);
        model.tabelaE.getColumnModel().getColumn(3).setMaxWidth(50);
        model.tabelaE.getColumnModel().getColumn(4).setMaxWidth(45);
        model.tabelaE.getColumnModel().getColumn(5).setMaxWidth(45);
        model.tabelaE.getColumnModel().getColumn(6).setMaxWidth(65);
        model.tabelaE.getTableHeader().setReorderingAllowed(false);
        model.tabelaE.setAutoCreateRowSorter(true);
        JScrollPane scrollE = new JScrollPane(model.tabelaE);
        scrollE.setBounds(10, 40, 400, 450);
        painelConteudo.add(scrollE);


        model.modelD.setColumnIdentifiers(new String [] {"Nome","Data","Qtd","Custo M","Valor","Corret","Emol","Compra T","Venda T","Lucro T"});
        model.tabelaD.getColumnModel().getColumn(0).setMaxWidth(60);
        model.tabelaD.getColumnModel().getColumn(2).setMaxWidth(40);
        model.tabelaD.getColumnModel().getColumn(3).setMaxWidth(55);
        model.tabelaD.getColumnModel().getColumn(4).setMaxWidth(50);
        model.tabelaD.getColumnModel().getColumn(5).setMaxWidth(45);
        model.tabelaD.getColumnModel().getColumn(6).setMaxWidth(45);
        model.tabelaD.getColumnModel().getColumn(7).setMaxWidth(65);
        model.tabelaD.getColumnModel().getColumn(8).setMaxWidth(65);
        model.tabelaD.getColumnModel().getColumn(9).setMaxWidth(65);
        model.tabelaD.getTableHeader().setReorderingAllowed(false);
        model.tabelaD.setAutoCreateRowSorter(true);
        JScrollPane scrollD = new JScrollPane(model.tabelaD);
        scrollD.setBounds(410, 40, 580, 450);
        painelConteudo.add(scrollD);
        
        JLabel lblCompras = new JLabel("Compras");
        lblCompras.setBounds(180, 5, 70, 35);
        painelConteudo.add(lblCompras);
        
        JLabel lblVenda = new JLabel("Vendas");
        lblVenda.setBounds(670, 5, 70, 35);
        painelConteudo.add(lblVenda);
        
        model.lblTCompras.setBounds(30, 510, 240, 35);
        painelConteudo.add(model.lblTCompras);
        
        model.lblTVendas.setBounds(800, 500, 190, 35);
        painelConteudo.add(model.lblTVendas);
        
        model.lblTLucro.setBounds(550, 500, 240, 35);
        painelConteudo.add(model.lblTLucro);
        
        model.lblTPrejuizo.setBounds(550, 525, 240, 35);
        painelConteudo.add(model.lblTPrejuizo);
        
        model.lblTResultado.setBounds(550, 550, 240, 35);
        painelConteudo.add(model.lblTResultado);
        
        model.lblTDarf.setBounds(800, 525, 190, 35);
        painelConteudo.add(model.lblTDarf);
        
        add(painelConteudo);
        setTitle(stringData);
        setSize(1000,600);
        setResizable(false);
        pack();
        setVisible(true);
        
    }
}