package sample;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.io.*;
import java.text.SimpleDateFormat;

public class ImageProccessing {
    private static int len = 20;
    private static List<Double> eixoX = new ArrayList<Double>();
    private static List<Double> eixoY = new ArrayList<Double>();
    private static double PI = Math.PI;
    private static double angulacaoDoDesenho = PI/2;
    private static double angulo = PI/2;
    private static double translacaoEixoX = 0;
    private static double translacaoEixoY = 0;

    public static void start()
    {
        String init = "";
        String translacaoF = "";
        String translacaoG = "";
        int interacoes = 0;
        try(BufferedReader br = new BufferedReader(new FileReader("input.txt")))
        {
            StringBuilder sb = new StringBuilder();
            String linha = br.readLine();

            int i = 1;
            while (linha != null)
            {
                sb.append(linha);
                sb.append(System.lineSeparator());
                if(i == 1)
                {
                    try
                    {
                        interacoes = Integer.parseInt(linha.split(",")[0].substring(linha.split(",")[0].indexOf("=")+1));
                        int a = Integer.parseInt(linha.split(",")[1].substring(linha.split(",")[1].indexOf("=")+1));
                        angulo = (a*PI)/180;
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                else if(i == 2)
                {
                    init = linha.substring(linha.indexOf("=")+1);
                }
                else if(i == 3)
                {
                    translacaoF = linha.substring(linha.indexOf("=")+1);
                }
                else if(i == 4)
                {
                    translacaoG = linha.substring(linha.indexOf("=")+1);
                }
                i++;
                linha = br.readLine();
            }
            String textaoCompleto = sb.toString();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        Generator.Begin(init, translacaoF, translacaoG);
        next(interacoes);
    }

    public static void next(int iterations)
    {
        String sentenca = Generator.Read(iterations);
        draw(sentenca);
    }

    public static void draw(String sentence)
    {
        StringBuffer svg = new StringBuffer();
        svg.append("<?xml version=\"1.0\" standalone=\"no\"?> \n");
        svg.append("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\"> \n");
        translacaoEixoX = 300;
        translacaoEixoY = 300;
        for (int i = 0; i < sentence.length(); i++)
        {
            char c = sentence.charAt(i);
            if (c == 'F')
            {
                double origemNoX = translacaoEixoX;
                double origemNoY = translacaoEixoY;
                double destinoEmX = (-(Math.cos(angulacaoDoDesenho) * len) + origemNoX);
                double destinoEmY = (-(Math.sin(angulacaoDoDesenho) * len) + origemNoY);
                svg.append("\t  <line x1 = \""+ origemNoX +"\" y1 = \""+ origemNoY +"\" x2 = \""+ destinoEmX +"\" y2 = \""+ destinoEmY +"\" stroke = \"black\" stroke-width = \"1\"/> \n");
                translacaoEixoX = destinoEmX;
                translacaoEixoY = destinoEmY;
            }
            else if (c == 'G')
            {
                double origemNoX = translacaoEixoX;
                double origemNoY = translacaoEixoY;
                double destinoEmX = (-(Math.cos(angulacaoDoDesenho) * len) + origemNoX);
                double destinoEmY = (-(Math.sin(angulacaoDoDesenho) * len) + origemNoY);
                svg.append("\t  <line x1 = \""+ origemNoX +"\" y1 = \""+ origemNoY +"\" x2 = \""+ destinoEmX +"\" y2 = \""+ destinoEmY +"\" stroke = \"black\" stroke-width = \"0\"/> \n");
                translacaoEixoX = destinoEmX;
                translacaoEixoY = destinoEmY;
            }
            else if (c == '+')
            {
                angulacaoDoDesenho -= angulo;
            }
            else if (c == '-')
            {
                angulacaoDoDesenho += angulo;
            }
            else if (c == '[')
            {
                push();
            }
            else if (c == ']')
            {
                pop();
            }
        }

        svg.append("<\\svg> \n");

        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(Calendar.getInstance().getTime());

        String data = "Data Teste";
        FileOutputStream out;
        try
        {
            out = new FileOutputStream("FractalGerado@" + timeStamp + ".svg");
            out.write(svg.toString().getBytes());
            out.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void push()
    {
        eixoX.add(translacaoEixoX);
        eixoY.add(translacaoEixoY);
    }

    public static void pop()
    {
        translacaoEixoX = eixoX.remove(eixoX.size()-1);
        translacaoEixoY = eixoY.remove(eixoY.size()-1);
    }
}
