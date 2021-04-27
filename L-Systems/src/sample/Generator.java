package sample;

public class Generator {
    private static String atual = "";
    private static String translacaoF;
    private static String translacaoG;

    public static void Begin(String init, String translacaoFF, String translacaoGG)
    {
        atual = init;
        translacaoF = translacaoFF;
        translacaoG = translacaoGG;
    }

    public static String Read(int interacoes)
    {
        for(int n = 0; n < interacoes; n++)
        {
            StringBuffer next = new StringBuffer();
            for (int i = 0; i < atual.length(); i++)
            {
                char c = atual.charAt(i);

                if (c == 'F')
                    next.append(translacaoF);
                else if (c == 'G')
                    next.append(translacaoG);
            }
            atual = next.toString();
        }
        return atual;
    }
}