package checkspace.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** Imprime e lê linhas no terminal do computador. **/
public class Console
{
    private static final String EMPTY_LINE = "";

    private String lastLineRead;

    /** Imprime uma linha no terminal do computador **/
    public void printLine(String line)
    {
        System.out.println(line);
    }

    /** Lê uma linha do terminal do computador. **/
    public String readNewLine()
    {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            lastLineRead = reader.readLine().trim();
        } catch (IOException exception)
        {
            printLine("Não foi possível ler linha. Erro: " + exception.getLocalizedMessage());
            lastLineRead = EMPTY_LINE;
        }

        return lastLineRead;
    }

    /** Retorna a última linha lida do console ou null se nenhuma linha foi lida até o momento. **/
    public String getLastLineRead() {
        return lastLineRead;
    }
}
