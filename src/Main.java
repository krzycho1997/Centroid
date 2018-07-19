import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        boolean zdanie=true;
        int lll;
        int iter=0;
        int rozmiar=0;
        int liczba_cent=0;
        int indeks_c=0;
        int sumy[];
        float tab_sum_x[];
        float tab_sum_y[];
        float suma_x=0f, suma_y=0f;
        double min_odl;
        String cent[];
        String centuriony[];
        String pom[];
        float x1, y1, x2, y2, x_pom, y_pom;
        List<String>[] tab_cent;
        List<String> punkty=new ArrayList<String>();
        FileReader fr = null;
        String linia = "";

        try {
            fr = new FileReader("plik1.txt");
        } catch (FileNotFoundException e) {
            System.out.println("BŁĄD PRZY OTWIERANIU PLIKU!");
            System.exit(1);
        }

        BufferedReader bfr = new BufferedReader(fr);
        try {
            while((linia = bfr.readLine()) != null){
                if (linia.charAt(linia.length()-1)=='*') ++liczba_cent;
                punkty.add(linia);
                //System.out.println(linia);
            }
        } catch (IOException e) {
            System.out.println("BŁĄD ODCZYTU Z PLIKU!");
            System.exit(2);
        }

        try {
            fr.close();
        } catch (IOException e) {
            System.out.println("BŁĄD PRZY ZAMYKANIU PLIKU!");
            System.exit(3);
        }
        centuriony=new String[liczba_cent];
        rozmiar=punkty.size();
        sumy=new int[liczba_cent];
        tab_sum_x=new float[liczba_cent];
        tab_sum_y=new float[liczba_cent];
        tab_cent=(ArrayList<String>[])new ArrayList[liczba_cent];
        for (int a=0; a<liczba_cent; a++)
        {
            centuriony[a]="";
            sumy[a]=0;
            tab_sum_x[a]=0f;
            tab_sum_y[a]=0f;
        }
        for (int k=0; k<liczba_cent; k++) tab_cent[k]=new ArrayList<>();
        while(true)
        {
            tab_cent=(ArrayList<String>[])new ArrayList[liczba_cent];
            for (int a=0; a<liczba_cent; a++)
            {
                sumy[a]=0;
                tab_sum_x[a]=0f;
                tab_sum_y[a]=0f;
            }
            for (int k=0; k<liczba_cent; k++) tab_cent[k]=new ArrayList<>();
            //koniec inicjalizacji
            for (int i = 0; i < punkty.size()-liczba_cent; i++)
            {
                pom=punkty.get(i).split(" ");
                x_pom=Float.parseFloat(pom[0]);
                y_pom=Float.parseFloat(pom[1]);
                cent=punkty.get(punkty.size()-liczba_cent).split(" ");
                x1=Float.parseFloat(cent[0]);
                y1=Float.parseFloat(cent[1]);
                min_odl= Math.sqrt(Math.pow(x1-x_pom,2)+Math.pow(y1-y_pom,2));
                iter=0;
                indeks_c=0;
                for (int x=punkty.size()-liczba_cent; x<punkty.size(); x++)
                {
                    cent=punkty.get(x).split(" ");
                    x1=Float.parseFloat(cent[0]);
                    y1=Float.parseFloat(cent[1]);
                    if (Math.sqrt(Math.pow(x1-x_pom,2)+Math.pow(y1-y_pom,2))<min_odl)
                    {
                        indeks_c=iter;
                        min_odl=(Math.sqrt(Math.pow(x1-x_pom,2)+Math.pow(y1-y_pom,2)));
                    }
                    ++iter;
                }
                tab_cent[indeks_c].add(punkty.get(i));
                ++sumy[indeks_c];
                tab_sum_x[indeks_c]+=x_pom;
                tab_sum_y[indeks_c]+=y_pom;
            }
            cent=new String[2];
            cent[0]="";
            cent[1]="";
            lll=punkty.size()-liczba_cent;
            for (int x=rozmiar-1; x>=lll; x--) punkty.remove(x);
            for (int q=0; q<liczba_cent; q++)
            {
                cent[0]=Float.toString(tab_sum_x[q]/sumy[q]);
                cent[1]=Float.toString(tab_sum_y[q]/sumy[q]);
                punkty.add(cent[0] + " " + cent[1] + " " + "s" + " " + "*");
            }
            iter=0;
            zdanie=true;
            for (int x=punkty.size()-liczba_cent; x<punkty.size(); x++)
            {
                if (centuriony[iter].equals(punkty.get(x)) && zdanie) zdanie=true;
                else zdanie=false;
                ++iter;
            }
            for (int p=0; p<liczba_cent; p++)
            {
                cent[0]=Float.toString(tab_sum_x[p]/sumy[p]);
                cent[1]=Float.toString(tab_sum_y[p]/sumy[p]);
                centuriony[p]=cent[0] + " " + cent[1] + " " + "s" + " " + "*";
            }
            if (zdanie) break;
        }
        for (int c=0; c<liczba_cent; c++)
        {
            System.out.println("Nalezace do: " + c);
            for (int v=0; v<tab_cent[c].size(); v++) System.out.println(tab_cent[c].get(v));
            System.out.println();
        }
        for (int i=0; i<liczba_cent; i++)
        {
            System.out.println(centuriony[i]);
        }
        System.out.println("Koniec!!!");
    }
}