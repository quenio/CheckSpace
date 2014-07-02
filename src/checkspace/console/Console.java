package checkspace.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/** Imprime e lê linhas no terminal do computador. **/
public class Console {

    /** Imprime uma linha no terminal do computador **/
    public void printLine(String line)
    {
        System.out.println(line);
    }

    /** Lê uma linha do terminal do computador. **/
    public String readLine()
    {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            return reader.readLine();
        } catch (IOException exception)
        {
            printLine("Não foi possível ler linha. Erro: " + exception.getLocalizedMessage());
            return "";
        }
    }

}
