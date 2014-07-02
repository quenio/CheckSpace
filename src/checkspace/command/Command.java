package checkspace.command;

import checkspace.console.Console;

public interface Command
{

    /** Retorna uma linha explicando como executar o comando e sua função. **/
    String getHelpLine();

    /** Returna true se a linha fornecida é reconhecida como este comando. **/
    boolean accepts(String line);

    /** Executa este comando e retorna true se a execução deste aplicativo deve continuar. **/
    boolean execute(Console console);

}
