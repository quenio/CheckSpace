### Projeto Final INE 5404 - POO II
#### Quenio Cesar Machado dos Santos (14100868)
#### 1 de dezembro de 2014

# 1 - Introdução ao Projeto CheckSpace
A seguir, encontram-se o propósito do projeto CheckSpace e os princípios que guiaram sua implementação.

## 1.1 - Propósito
O CheckSpace é um aplicativo que deverá auxiliar o usuário na liberação de espaço em seu computador ao informá-lo quais são as pastas que podem ser removidas e/ou arquivadas sem prejudicar seu fluxo de trabalho.

## 1.2 - Princípios
1. *Segurança*: O aplicativo somente fornecerá informações sobre as pastas do usuário e não irá remover (ou arquivar) os arquivos do usuário. Para executar tais tarefas, o usuário deverá utilizar o sistema operacional de seu computador.

1. *Praticidade*: O aplicativo será fácil de executar e as informações serão apresentadas de forma clara. O usuário precisa saber apenas digitar o caminho da pasta a ser analisada e o aplicativo fará a análise da pasta, mostrando os resultados.

1. *Eficiência*: O aplicativo deverá executar de forma rápida. Se alguma operação não for instantânea, o usuário deverá ser informado do progresso da execução.





# 2 - Uso do Aplicativo
Os computadores pessoais possuem grande capacidade de armazenamento, porém a demanda dos usuários é cada vez maior na medida em que se pode armazenar de forma digital vários tipos de mídia, tais como fotos, vídeos, música, cópias de documentos, etc.
Assim sendo, é comum que o usuário do computador se surpreenda com uma mensagem do sistema operacional lhe informando que não há mais espaço disponível para armazenamento de arquivos.
Nesta situação, o usuário pode não saber quais arquivos e pastas podem ser removidos e/ou arquivados (por falta de uso) sem que isto lhe cause eventual transtorno quando os arquivos se tornem necessários para alguma atividade.

## 2.1 - Solução
Uma solução é este pequeno aplicativo para auxiliar na tarefa de remoção e/ou arquivamento dos arquivos e pastas menos usados. 

Este utiliza pouco espaço em disco; uma vez que o computador já está deficiente de espaço.


## 2.2 - Execução
Ao iniciar, o usuário pode digitar o caminho de uma pasta a ser analisada, ou seja, pasta da qual o usuário suspeita que haja arquivos ou sub-pastas com pouco uso. O aplicativo oferece como sugestão a pasta `home` (ou “pasta do usuário”). 

Uma vez escolhida a pasta, o usuário precisa apenas pressionar `Analisar Pasta` para que a análise se inicie.

## 2.3 - Análise da Pasta
Uma vez que a pasta foi definida pelo usuário, o aplicativo faz uma análise da pasta, suas sub-pastas e arquivos, capturando as seguintes informações:
* quanto espaço está sendo utilizado pelas sub-pastas e arquivos dentro da pasta-raíz;
- quando foi o último acesso a cada uma destas sub-pastas e arquivos.

## 2.4 - Relatório de Uso
As informações capturadas sobre a pasta definida pelo usuário são apresentadas em tempo real ao usuário em uma tabela listando os arquivos e sub-pastas da pasta-raíz, contendo as seguintes colunas:
- *Nome*: O nome do arquivo ou sub-pasta.
- *Espaço*: O espaço ocupado pelo arquivo ou sub-pasta.
- *Último Accesso*: a data e hora em que o arquivo ou sub-pasta foi acessado pela última vez.


## 2.5 - Ordem do Relatório
Primeiramente, os itens do *Relatório de Uso* são listados em ordem decrescente do momento de análise, porém o usuário pode mudar a coluna usada na ordenação -- basta pressionar o cabeçalho da coluna desejada. Pode também mudar para a ordem decrescente pressionando na mesma coluna uma segunda vez.

## 2.6 - Mudando a Pasta Analisada
A qualquer momento durante a execução do aplicativo, o usuário pode mudar a pasta a ser analisada digitando um novo caminho de pasta. 

Neste caso, o usuário deverá pressionar `Analisar Pasta` novamente para que se reinicie o processo de análise.

## 2.7 - Pasta Inexistente
Se o usuário digitar um caminho que não leva a qualquer pasta do computador, então uma mensagem informa o usuário que `A pasta digitada não se encontra neste computador.`

Logo após, o usuário pode novamente digitar um caminho de uma pasta reiniciar o processo de análise.

## 2.8 - Informando o Progresso da Análise
A pasta sendo analisada pode conter centenas ou até milhares de arquivos, o que pode tornar demorada a captura de informações do *Relatório de Uso*.

Quando isto ocorrer, o aplicativo mostrará o resultado parcial da análise de forma continuada, incluindo novos itens da análise e atualizando os dados dos item ainda em análise.

Durante este processo, usuário pode navegar pelo relatório, mudar sua ordenação e até cancelar o processo como um todo.

## 2.9 - Cancelamento da Análise
Ao iniciar o processo de análise, o botão `Analisar Pasta` se transforma em `Cancelar Análise`, permitindo assim ao usuário cancelar o processo a qualquer momento.
Uma vez pressionado o botão `Cancelar Análise`, esta é terminada imediatamente e usuário verá novamente o botão `Analisar Pasta` para pode iniciar uma nova análise. 

# 3 - Arquitetura do Aplicativo
O aplicativo foi dividido em componentes, sendo cada um responsável por um aspecto do aplicativo. 
Os seguintes padrões de projeto foram utilizados para definir os componentes deste aplicativo:
- *Model-View-Controller (MVC)*: utilizado para separar os aspectos da interface gráfica com o usuário (através das classes *Window* e *Controller*) dos aspectos do domínio do problema em questão que é o processamento de informações sobre pastas do computador, através das classes *FolderAnalysis* e *FolderAnalysisItem*.
- "Command Design Pattern": este padrão foi utilizado para separar a implementação das atividades distintas que são executadas pelo usuário (tais como iniciar ou cancelar a análise de pastas) das classes da análise propriamente dita citadas acima.
Cada sub-seção a seguir descreve um componente do aplicativo CheckSpace.
- "Data/Property Binding": auxiliando a comunicação entre a "View" e o "Model" do aplicativo, utilizou-se o mecanismo de property binding, onde as propriedades dos elementos da *Window* são conectados diretamente aos elementos de *FolderAnalysis* sem comprometer o encapsulamento dos mesmos.
- "Dependency Injection": para auxiliar na composição do grafo de objetos que formam o aplicativo em tempo de execução, utilizou-se uma *framework* chamada *Dagger*, que permite as dependências de cada classe serem injetadas automaticamente, ao invés de deixar ao encargo das próprias classes o gerenciamento de suas dependências.

Toda arquitetura foi largamente influenciada pelos recursos disponíveis na *framework Java FX* que hoje está disponível como parte do *Java SDK 8*. 

## 3.1 - Interface Gráfica
Este componente é responsável por informar qual pasta-raíz está sendo analisada e listar suas sub-pastas e arquivos com a devida informação.

Suas classes principais são:
* *Window*: responsável por apresentar informações ao usuário ou requisitar comandos deste.
* *LayoutLoader*: carrega os elementos visuais a serem mostrados em *Window*.
* *Controller*: super-classe de todos os controllers do aplicativo.
* *MainController*: utiliza *LayoutLoader* para montar a *Window* principal com o layout superior (controlado por *FolderAnalysisController*) e central (controlado por *UsageReportController*).
* *FolderAnalysisController*: controla o campo de digitação de pastas e o botão de acionar ou cancelar a análise de pastas.
* *UsageReportController*: responsável por manter o *Relatório de Uso* atualizado.

## 3.2 - Serviços e Tarefas
Para executar e acompanhar o processo de análise de pastas, existe a colaboração do *FolderAnalysisController* e as seguintes classes:
* *FolderAnalysisService*: controla a execução de *FolderAnalysisTask* num *background thread*, permitindo a atualização do progresso de execução através de mensagens mostradas ao usuário. 
* *FolderAnalysisTask*: executa a análise num *background thread* sem afetar a interatividade do aplicativo no *foreground thread*. Utiliza-se de *FolderAnalysis* para a execução da análise propriamente dita. 

## 3.3 - Análise de Pastas
*FolderAnalysis* se encarrega de introspectar a pasta-raíz e capturar toda a informação necessária sobre as sub-pastas e arquivos para que se possa mostrar o *Relatório de Uso*.

Ela executa o processamento, gerando como resultado uma lista de objetos do tipo *FolderAnalysisItem*, que contém informações sobre uma sub-pasta ou um arquivo dentro da pasta-raíz:
* *Name*: o nome do item.
* *Space*: o espaço utilizado pelo item em *bytes*.
* *LastAccess*: um *timestamp* do último acesso efetuado a este item.

Se a instância de *FolderAnalysisItem* está representando uma sub-pasta dentro da pasta-raíz:
* o atributo *Space* é a soma do espaço ocupado por todos os arquivos encontrados nesta pasta, incluindo os arquivos em suas sub-pastas;
* e o atributo *LastAccess* é o *timestamp* do arquivo encontrado nesta sub-pasta que foi acessado mais recentemente, recursivamente.

Estas informações serão utilizadas por *TableView* para imprimir o *Relatório de Uso*.

## 3.4 - Relatórios
Uma *TableView* da *framework Java FX*, que se encontra no layout de *Window*, vai permitir ao usuário visualizar o *Relatório de Uso* descrito na seção *Cenário de Uso do Aplicativo*.

Esta instância de *TableView* é configurada pelo *UsageReportController*, o qual utiliza-se do mecanismo de *property binding* (implementado pelo próprio aplicativo através de *ColumnBinding*) para associar cada coluna e linha do relatório a um dos atributos de *FolderAnalysisItem*.
Este mecanismo permite que os dados do *Relatório de Uso* sejam atualizados em tempo real.

3.5 - Grafo de Objetos
Para que o aplicativo possa ser executado é preciso construir o grafo de todos os objetos que fazem parte do aplicativo em tempo de execução.
Para isto serve a classe *CheckSpaceModule*. Através dela é possível verificar como toda a árvore de dependências é formada e como os objetos são construídos para formar o aplicativo.
Uma instância da classe *CheckSpaceApp*, (a classe principal) é responsável por acionar *CheckSpaceModule*, criando o grafo e iniciando a execução através da instância de *Window*.

# 4 - Conclusão
Através da definição clara do propósito do aplicativo CheckSpace e dos princípios que guiaram o projeto, foi possível definir uma arquitetura coerente para a sua implementação, a qual se desenvolveu de forma rápida e com poucos percalços.
Vale mencionar porém que se trata de um aplicativo relativamente simples. Um domínio mais complexo certamente traria consigo mais desafios, tanto para a definição dos requisitos como para a concepção da arquitetura do sistema.
De todo modo, a definição prévia do problema a ser solucionado pelo aplicativo CheckSpace permitiu delimitar o escopo deste projeto, facilitando assim sua implementação.

# 5 - Referências
- *Model-View-Controller*: http://en.wikipedia.org/wiki/Model–view–controller
- *Command Design Pattern*: http://en.wikipedia.org/wiki/Command_pattern
- *Dagger*: http://square.github.io/dagger
- *JavaFX*: http://docs.oracle.com/javase/8/javase-clienttechnologies.htm