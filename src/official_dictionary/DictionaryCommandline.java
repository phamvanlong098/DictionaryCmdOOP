package official_dictionary;

import java.io.FileNotFoundException;


public class DictionaryCommandline extends DictionaryManagement{

    public void showAllWords(){
        System.out.println("-showAllWords");
        System.out.println("No\t| English\t| Vietnamese");
        for (int i = 0; i < Dictionary.getSize(); i++){
            Word newWord = Dictionary.getWord(i);
            System.out.print(i + 1);
            System.out.printf("\t|%s\t\t|%s\n" , newWord.getWord_target(), newWord.getWord_explain());
        }
    }

    public void dictionaryBasic(){
        System.out.println("-dictionaryBasic");
        insertFromCommandline();
        showAllWords();
    }

    public void dictionaryAdvanced() throws FileNotFoundException{
        System.out.println("-dictionaryAdvanced");
        insertFromFile();
        //sortDictionary();
        showAllWords();
        //dictionaryLookup("hello");
    }

    @Override
    public void dictionarySearcher(String subSting){
        System.out.println("-dictionarySearcher");

        super.dictionarySearcher(subSting);
    }


    // linh tinh
//    public void testModule(String target, String explain){
//        addWord("green", "mau xanh luc");
//        removeWord("hell");
//
//        editWord(target, explain);
//
//        showAllWords();
//
//
//    }

    public static void main(String[] strings) throws FileNotFoundException{
        DictionaryCommandline dic = new DictionaryCommandline();
//        dic.dictionaryBasic();
        dic.dictionaryAdvanced();
        //dic.testModule("world", "sei kai");
        dic.dictionarySearcher("h");

        //dic.addManagement();

        dic. dictionaryExportToFile();
    }
}
