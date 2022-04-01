# GerenciadorAcoes
Gerenciador de Ações em Java

Programa que visa ajudar a organização de investimentos na bolsa de valores.<br>
&emsp;&emsp;• Adição/Remoção/Desdobramento de ativos<br>
&emsp;&emsp;• Separação por ativos<br>
&emsp;&emsp;• Tabelas mensais<br>
&emsp;&emsp;• Relatório anual<br>

Compilação e geração de arquivo .jar (executar no nível em que se encontra o arquivo Manifest.txt):<br>

Windows:
<pre><code>xcopy /s /i src\bdacoes bdacoes<br>
javac bdacoes\BdAcoes.java -encoding utf8<br>
del /s /q bdacoes\*.java<br>
jar -cfm "Gerenciador Ações.jar" "Manifest.txt" bdacoes<br>
rmdir /s /q bdacoes<br>
</code></pre>

Na primeira execução, será criada uma pasta chamada "arquivos" onde o .jar se encontra. Os dados inseridos no programa serão guardados nesta pasta.
