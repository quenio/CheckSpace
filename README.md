### Projeto Final INE 5404 - POO II
#### Quenio Cesar Machado dos Santos (14100868)
#### 28 de novembro de 2014

# 1 - Introdução ao Projeto CheckSpace
A seguir, encontram-se o propósito do projeto CheckSpace e os princípios que guiaram sua implementação.

## 1.1 - Propósito
O CheckSpace é um aplicativo que deverá auxiliar o usuário na liberação de espaço em seu computador ao informá-lo quais são as pastas que podem ser removidas e/ou arquivadas sem prejudicar seu fluxo de trabalho.

## 1.2 - Princípios
1. *Segurança*: O aplicativo somente fornecerá informações sobre as pastas do usuário e não irá remover (ou arquivar) os arquivos do usuário. Para executar tais tarefas, o usuário deverá utilizar o sistema operacional de seu computador.

1. *Praticidade*: O aplicativo será fácil de executar e as informações serão apresentadas de forma clara. O usuário precisa saber apenas executar o aplicativo e este guiará o usuário durante o uso com mensagens explicativas.

1. *Eficiência*: O aplicativo deverá executar de forma rápida. Se alguma operação não for instantânea, o usuário deverá ser informado dos passos sendo executados e do progresso da execução.





# 2 - Uso do Aplicativo
Os computadores pessoais possuem grande capacidade de armazenamento, porém a demanda dos usuários é cada vez maior na medida em que se pode armazenar de forma digital vários tipos de mídia, tais como fotos, vídeos, música, cópias de documentos, etc.
Assim sendo, é comum que o usuário do computador se surpreenda com uma mensagem do sistema operacional lhe informando que não há mais espaço disponível para armazenamento de arquivos.
Nesta situação, o usuário pode não saber quais arquivos e pastas podem ser removidos e/ou arquivados (por falta de uso) sem que isto lhe cause eventual transtorno quando os arquivos se tornem necessários para alguma atividade.

## 2.1 - Solução
Uma solução possível será instalar um pequeno aplicativo para auxiliar na tarefa de remoção e/ou arquivamento dos arquivos e pastas menos usados. 

Este aplicativo deverá utilizar pouco espaço em disco; uma vez que o computador já está deficiente de espaço.


## 2.2 - Execução
Uma vez instalado, o aplicativo pode ser executado da maneira usual. 

Ao iniciar, o aplicativo então pergunta ao usuário o nome de uma pasta a ser analisada e oferece como sugestão a pasta `home` (ou “pasta do usuário”). 

Neste momento, o usuário pode digitar o caminho de uma pasta específica (pasta da qual o usuário suspeita que haja arquivos ou sub-pastas com pouco uso), ou pode apenas pressionar `OK` para que a sugestão de pasta seja aceita.

## 2.3 - Análise da Pasta
Uma vez que a pasta foi definida pelo usuário, o aplicativo faz uma análise da pasta, suas sub-pastas e arquivos, capturando as seguintes informações:
* quanto espaço está sendo utilizado pelas sub-pastas e arquivos dentro da pasta-raíz;
- quando foi o último acesso a cada uma destas sub-pastas e arquivos.

## 2.4 - Relatório de Uso
As informações capturadas sobre a pasta definida pelo usuário são apresentadas ao usuário em uma tabela listando os arquivos e sub-pastas da pasta-raíz, contendo as seguintes colunas:
- *Nome*: O nome do arquivo ou sub-pasta.
- *Espaço*: O espaço ocupado pelo arquivo ou sub-pasta.
- *Último Accesso*: a data e hora em que o arquivo ou sub-pasta foi acessado pela última vez.


## 2.5 - Ordem da Lista
Primeiramente, os itens do relatório são listados em ordem crescente do *Nome*, porém o usuário pode mudar a ordem para decrescente  e mudar a coluna usada na ordenação -- basta clicar na coluna desejada. Pode também retornar à ordem crescente clicando na mesma coluna uma segunda vez.

Imediatamente após um dos comandos descritos acima ser executado pelo usuário, o *Relatório de Uso* é reimpresso na tela com a devida ordem.

## 2.6 - Mudando a Pasta Analisada
A qualquer momento durante a execução do aplicativo, o usuário pode mudar a pasta sendo analisada pressionando o botão 'Mudar de Pasta'. 

Neste caso, o aplicativo vai novamente perguntar ao usuário o caminho da pasta a ser analisada e o usuário deve proceder como foi descrito na seção *Execução*.

## 2.7 - Pasta Inexistente
Se o usuário digitar um caminho que não leva a qualquer pasta do computador, então uma mensagem informa o usuário que `A pasta digitada não se encontra neste computador.`

Logo após, o aplicativo novamente pergunta ao usuário o caminho de uma pasta e a execução procede como descrito na seção *Execução*.

## 2.8 - Informando o Progresso da Análise
A pasta sendo analisada pode conter centenas ou até milhares de arquivos, o que pode tornar demorada a captura de informações do *Relatório de Uso*.

Quando isto ocorrer, o usuário será informado através de uma Janela de Progresso qual arquivo ou sub-pasta está sendo analisada pelo aplicativo naquele momento.

# 3 - Arquitetura do Aplicativo
O aplicativo será dividido em componentes, sendo cada um responsável por um aspecto do aplicativo. 
Dois padrões de projeto foram utilizados para definir os componentes deste aplicativo:
- *Model-View-Controller (MVC)*: utilizado para separar os aspectos da interface gráfica com o usuário dos aspectos do domínio do problema em questão que é o processamento de informações sobre pastas do computador.
- "Command": este padrão foi utilizado para separar a implementação das atividades distintas que são executadas pelo usuário.
Cada sub-seção a seguir descreve um componente do aplicativo CheckSpace.

## 3.1 - Janela Principal
Este componente é responsável por informar qual pasta-raíz está sendo analisada e listar suas sub-pastas e arquivos com a devida informação.

Sua classe principal é chamada *MainWindow* e é utilizada pelos demais componentes sempre que é necessário apresentar informações ao usuário ou requisitar comandos deste.

## 3.2 - Comandos
O componente de comandos será responsável pela interação com o usuário e com os demais componentes do aplicativo. Esse receberá os comandos do usuário e executará a função correspondente.

A abstração central deste componente é a interface *Command*, que representa um comando que pode ser executado pelo usuário. Existem algumas implementações:
* *RootFolderProcessingCommand*: ativado quando o aplicativo é iniciado pela primeira vez ou quando o botão "Mudar de Pasta" é pressionado, permitindo ao usuário definir uma pasta, processando esta e finalmente imprimindo o *Relatório de Uso*.
* *ReportOrderDirectionCommand*: ativado quando o usuário pressiona o cabeçalho de uma das colunas  do *Relatório de Uso*, ordenando-o de forma descrente ou crescente.
* *ReportOrderColumnCommand*: também ativado quando o usuário pressiona o cabeçalho de uma das colunas do *Relatório de Uso*, ordenando a coluna correspondente.

A classe principal de controle é *MainController*, que processa os comandos ativados pelo usuário na Janela Principal. Todas as instâncias de *Command* e a única instância de *MainController* vão interagir com o usuário através da classe *MainWindow*.

## 3.3 - Relatório
Este componente implementa a impressão do *Relatório de Uso* descrito na seção *Cenário de Uso do Aplicativo* em uma tabela da Janela Principal.

Sua classe principal é *UsageReport* que representa *Relatório de Uso* e fornece os dados para a tabela de *MainWindow*.

As classes *ReportSorter* e *ReportOrder* definem a ordem de listagem do *Relatório de Uso* -- crescente ou decrescente -- e a coluna que define a ordem.

## 3.4 - Processamento
O componente de processamento se encarrega de introspectar a pasta-raíz e capturar toda a informação necessária sobre as sub-pastas e arquivos para que se possa imprimir o *Relatório de Uso*.

A classe principal deste componente é chamada *RootFolderProcessor*. Ela executa o processamento, gerando como resultado a seguinte estrutura de objetos da seguintes classes:
* *RootFolder*: representa a pasta-raíz na qual foi feita a introspecção.
* *RootFolderItem*: representa uma sub-pasta ou um arquivo dentro da pasta-raíz.
O resultado da execução de *RootFolderProcessor* retorna uma instância de *RootFolder* que contém uma ou mais instâncias de *RootFolderItem*, cada uma representando um item encontrado na pasta-raíz da instância de *RootFolder*.

A classe *RootFolderItem* contém os seguintes atributos:
* *Name*: o nome do item.
* *Space*: o espaço utilizado pelo item em *bytes*.
* *LastAccess*: um *timestamp* do último acesso efetuado a este item.

Se a instância de *RootFolderItem* está representando uma sub-pasta dentro da pasta-raíz:
* o atributo *Space* é a soma do espaço ocupado por todos os arquivos encontrados nesta pasta, incluindo os arquivos em suas sub-pastas;
* e o atributo *LastAccess* é o *timestamp* do arquivo encontrado nesta sub-pasta que foi acessado mais recentemente, recursivamente.

Estas informações serão utilizadas por *UsageReport* para imprimir o *Relatório de Uso*.

# 4 - Conclusão
Através da definição clara do propósito do aplicativo CheckSpace e dos princípios que guiaram o projeto, foi possível definir uma arquitetura coerente para a sua implementação, a qual se desenvolveu de forma rápida e com poucos percalços.
Vale mencionar porém que se trata de um aplicativo relativamente simples. Um domínio mais complexo certamente traria consigo mais desafios, tanto para a definição dos requisitos como para a concepção da arquitetura do sistema.
De todo modo, a definição prévia do problema a ser solucionado pelo aplicativo CheckSpace permitiu delimitar o escopo deste projeto, facilitando assim sua implementação.

# 5 - Referências
- *Model-View-Controller*: http://en.wikipedia.org/wiki/Model–view–controller
- *Command Design Pattern*: http://en.wikipedia.org/wiki/Command_pattern