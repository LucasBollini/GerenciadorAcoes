package bdacoes.PkgJanelas.Models;

import bdacoes.PkgGlobais.ClasseAcao;
import bdacoes.PkgGlobais.HandlerBd;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class M_JanelaAnual {
    
    public  JTextArea   areaTxt = new JTextArea(),
                        excelHolder = new JTextArea();
    
    public  JButton btnVoltar = new JButton("Voltar"),
                    btnCopiar = new JButton("Copiar Normal"),
                    btnExcel = new JButton("Copiar p/ Excel");
    
    public void escreverResumo(int ano){
        try{
            float vetorTotais[][] = new float[2][4];
            for(short i = 0; i < 2; i++)
                for(short j = 0; j < 4; j++)
                        vetorTotais[i][j] = 0;

            for (String listaNome : HandlerBd.procurarArquivos()) {                
                for(short j = 0; j < 4; j++)
                    vetorTotais[0][j] = 0;
                areaTxt.append("" + listaNome + ":\n");
                excelHolder.append(listaNome + "\tOperação\tData\tQuantidade\tValor\tCusto Médio\tCorretagem\tEmolumentos\tLucro\n");

                for(ClasseAcao selec : HandlerBd.lerArquivo(listaNome))
                    switch(selec.getTipo()){
                        case "D":   vetorTotais[0][0] = selec.getpMedio();
                                        areaTxt.append("        - D - " + selec.getData().toString() + " - Quantidade: " + selec.getQtd() + "\n");
                                        excelHolder.append("\tD\t" + selec.getQtd() + "\n");
                                    break;
                        case "V":   vetorTotais[0][0] -= selec.getQtd();
                                    vetorTotais[0][1] -= selec.getpMedio() * selec.getQtd();
                                    vetorTotais[0][2] = selec.getCorret();
                                    vetorTotais[0][3] = selec.getEmol();
                                    if(selec.getData().getYear() == ano){
                                        areaTxt.append("        - V - " + selec.getData().toString() + " - Quantidade: " + selec.getQtd() +  " - Valor: R$" + selec.getValor() + " - Custo Médio: R$" + selec.getpMedio() + " - Corretagem: R$" + selec.getCorret() + " - Emolumentos: R$" + selec.getEmol() + " - Lucro: R$" + (selec.getQtd() * (selec.getValor() - ((selec.getCorret() + selec.getEmol())/selec.getQtd()) - selec.getpMedio())) + "\n");
                                        excelHolder.append("\tV\t" + selec.getData().toString() + "\t" + selec.getQtd() +  "\t" + String.valueOf(selec.getValor()).replace(".", ",") + "\t" + String.valueOf(selec.getpMedio()).replace(".", ",") + "\t" + String.valueOf(selec.getCorret()).replace(".", ",") + "\t" + String.valueOf(selec.getEmol()).replace(".", ",") + "\t" + String.valueOf((selec.getQtd() * (selec.getValor() - ((selec.getCorret() + selec.getEmol())/selec.getQtd()) - selec.getpMedio()))).replace(".", ",") + "\n");
                                    }
                                    break;
                        case "C":   vetorTotais[0][0] += selec.getQtd();
                                    vetorTotais[0][1] += (selec.getQtd() * selec.getValor()) + selec.getCorret() + selec.getEmol();
                                    if(selec.getData().getYear() == ano){
                                        areaTxt.append("        - C - " + selec.getData().toString() + " - Quantidade: " + selec.getQtd() +  " - Valor: R$" + selec.getValor() + " - Corretagem: R$" + selec.getCorret() + " - Emolumentos: R$" + selec.getEmol() + "\n");
                                        excelHolder.append("\tC\t" + selec.getData().toString() + "\t" + selec.getQtd() +  "\t" + String.valueOf(selec.getValor()).replace(".", ",") + "\t-" + "\t" + String.valueOf(selec.getCorret()).replace(".", ",") + "\t" + String.valueOf(selec.getEmol()).replace(".", ",") + "\t-\n");
                                    }
                                    break;
                        default:    break;
                    }

                if(vetorTotais[0][0] == 0)
                    vetorTotais[0][1] = 0;
                for(int j = 0; j < 4; j++)
                    vetorTotais[1][j] += vetorTotais[0][j];
                areaTxt.append("    • Qtd Final: " + vetorTotais[0][0] + "   Valor Final: R$" + vetorTotais[0][1] + "\n\n");
                excelHolder.append("\tQtd Final\t" + vetorTotais[0][0] + "\n\tValor Final\t" + String.valueOf(vetorTotais[0][1]).replace(".", ",") + "\n\n\n");
            }

            areaTxt.append("\n\nTotal Final de Dezembro:\n    • Valor Final: R$" + vetorTotais[1][1] + "\n\n");
            excelHolder.append("\n\nTotal Dezembro:\tR$" + String.valueOf(vetorTotais[1][1]).replace(".", ","));
        }catch(Exception e){}
    }
}