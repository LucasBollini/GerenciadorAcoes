package bdacoes.PkgJanelas.Models;

import bdacoes.PkgGlobais.ClasseAcao;
import static bdacoes.PkgGlobais.Ferramentas.FtoS;
import bdacoes.PkgGlobais.HandlerBd;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class M_JanelaPrincipal {
    
    public  DefaultTableModel   modelE = new DefaultTableModel(0,3){
                                    @Override
                                        public boolean isCellEditable(int row, int column){return false;}
                                },
                                modelD = new DefaultTableModel(0,12){
                                    @Override
                                        public boolean isCellEditable(int row, int column){return false;}
                                };
    
    public  JTable  tabelaE = new JTable(modelE),
                    tabelaD = new JTable(modelD);
    
    public  JButton btnAdd = new JButton("Adicionar"),
                    btnRmv = new JButton("Remover"),
                    btnMensal = new JButton("Tabela Mensal"),
                    btnAnual = new JButton("Resumo Anual"),
                    btnDesdobr = new JButton("Desdobramento");
    
    public  JComboBox   comboTeste = new JComboBox(new String[]{"ttt","trrrr","bugao"}),
                        comboAno = new JComboBox(),
                        comboMes = new JComboBox(new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"});

    public void preencherTabelaD(){
            modelD.getDataVector().clear();
            tabelaD.getRowSorter().allRowsChanged();
            //tabelaD.updateUI();
            
        try{
            float holder[] = new float[2];
            int contador = 0;
            
            for(ClasseAcao acao : HandlerBd.lerArquivo(tabelaE.getValueAt(tabelaE.getSelectedRow(), 0).toString()))
                switch(acao.getTipo()){
                    case "D":   modelD.addRow(new String[]{ "" + acao.getData(), acao.getTipo(), "" + acao.getQtd(), "", "", "", "", "", "", "", "", "" + contador++});
                                break;
                    case "C":   modelD.addRow(new String[]{ "" + acao.getData(), acao.getTipo(), "" + acao.getQtd(), "", FtoS(acao.getValor()), "" + acao.getCorret(), "" + acao.getEmol(), FtoS(((acao.getQtd() * acao.getValor()) + acao.getCorret() + acao.getEmol())), "", "", "", "" + contador++});
                                break;
                    case "V":   holder[0] = acao.getQtd() * acao.getpMedio();
                                holder[1] = (acao.getQtd() * acao.getValor()) - acao.getCorret() - acao.getEmol();
                                modelD.addRow(new String[]{ "" + acao.getData(), acao.getTipo(), "" + (-acao.getQtd()), FtoS(acao.getpMedio()), FtoS(acao.getValor()), "" + acao.getCorret(), "" + acao.getEmol(), FtoS(holder[0]), FtoS(holder[1]), FtoS((holder[1] - holder[0])), FtoS(((holder[1] / holder[0] - 1) * 100)), "" + contador++});
                                break;
                    default:    break;
                }
        }catch(Exception e){}
    }
    
    public void preencherTabelaE(){
            modelE.getDataVector().clear();
            tabelaE.getRowSorter().allRowsChanged();
            //tabelaE.updateUI();
        try{
            ArrayList zerados = new ArrayList<>(); 
            float resultado[];


            for(String nome : HandlerBd.procurarArquivos()){
                resultado = HandlerBd.calcPMedio(nome, 0);
                if(resultado[1] != 0)
                    modelE.addRow(new String [] {nome, "" + (int)resultado[1], "" + FtoS(resultado[2])});
                else
                    zerados.add(nome);
            }

            zerados.forEach(nome -> modelE.addRow(new String []{nome.toString(), "" + 0, "" + 0}));
        }catch(Exception e){}
    }
    
    public void reselect(String alvo){
        preencherTabelaE();
        try{
            for(int i = 0; true; i++)
                if(alvo.equals(tabelaE.getValueAt(i, 0).toString()))
                    tabelaE.setRowSelectionInterval(i, i);
        }catch(Exception e){}
        preencherTabelaD();
    }
    
    public void acaoRemover(){
        if(tabelaD.getSelectedRow() == -1 || modelD.getDataVector().isEmpty())
            return;
        
        String selecao = tabelaE.getValueAt(tabelaE.getSelectedRow(), 0).toString();

        HandlerBd.rmvAcao(selecao, Integer.parseInt(tabelaD.getValueAt(tabelaD.getSelectedRow(), 11).toString()));
        reselect(selecao);
    }
}