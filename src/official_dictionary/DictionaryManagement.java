package official_dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;


public class DictionaryManagement extends Dictionary {

    public void insertFromCommandline() {
        System.out.println("-insertFromCommandline");

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        scan.nextLine();
        for (int i = 0; i < n; i++) {
            String word_target = scan.nextLine();
            String word_explain = scan.nextLine();

            addWord(word_target, word_explain);
        }
    }

    public static void insertFromFile() throws FileNotFoundException{
        System.out.println("-insertFromFile");

        File input = new File("src/official_dictionary/dictionaries.txt");
        Scanner scan = new Scanner(input);
        while (scan.hasNextLine()){
            String line = scan.nextLine();
            String[] newWord = line.split(" = ");
            addWord(newWord[0], newWord[1]);
        }

    }

    public static String dictionaryLookup(String target){
        System.out.println("-dictionaryLookup");

        int index = findTarget(target);
        String meaning;
        if(index == -1){
            meaning = "Not found target";
        }
        else {
            meaning = getWord(index).getWord_explain();
        }
        return meaning;
    }

    public void addWord(){
        System.out.println("-addManagement");

        Scanner scan = new Scanner(System.in);
        String word_target = scan.nextLine();
        String word_explain = scan.nextLine();

        super.addWord(word_target, word_explain);

    }

    public void removeWord(String target){
        System.out.println("-removeManagement");

        int index = findTarget(target);
        super.removeWord(index);
    }

    public static void editWord(String target, String explain){
        System.out.println("-editManagement");

        Dictionary.editWord(target, explain);
    }

    public void dictionaryExportToFile(){
        System.out.println("-dictionaryExportToFile");

        super.dictionaryExportToFile();
    }


    public static void main(String[] strings) throws FileNotFoundException{
        DictionaryManagement dic = new DictionaryManagement();
//        dic.insertFromCommandline();
        dic.insertFromFile();
//        dic.dictionaryExportToFile();
        System.out.println(dic.dictionaryLookup("animal"));

    }
}
