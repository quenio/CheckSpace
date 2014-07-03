package checkspace.command;

public abstract class Command
{

    /** Retorna uma linha explicando como executar o comando e sua função. **/
    public abstract String getHelpLine();

    /** Returna true se a linha fornecida é reconhecida como este comando. **/
    public abstract boolean accepts(String line);

    /** Executa este comando e retorna true se a execução deste aplicativo deve continuar. **/
    public abstract boolean execute(String line);

}
