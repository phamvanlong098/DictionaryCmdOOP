package official_dictionary;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.*;


public class Dictionary extends Word{
    protected static ArrayList<Word> word = new ArrayList<Word>();;

    public Dictionary(){
    }

    public static Word getWord(int index){
        try {
            return word.get(index);
        }
        catch (Exception e){
            System.out.println("Invalid index to get");
        }
        return null;
    }

    public static int getSize(){
        return word.size();
    }

    public static void addWord(String word_target, String word_explain){
        Word newWord = new Word(word_target, word_explain);
        word.add(newWord);
        bubleSort();
    }

    public static void removeWord(int index){
        try {
            word.remove(index);
        }
        catch (Exception e){
            System.out.println("Invalid index to remove");
        }
    }

    public static void editWord(String target, String explain){
        int index = findTarget(target);
        if(index == -1){
            addWord(target, explain);
        }
        else {
            Word tempWord = word.get(index);
            tempWord.setWord_explain(explain);
        }
    }

    public static void  bubleSort(){
        if(word.size() > 1){
            for(int i = word.size() - 2; i >= 0; i--){
                if(word.get(i).getWord_target().compareTo(word.get(i + 1).getWord_target()) > 0){
                    swap(i, i + 1);
                }
                else break;
            }
        }
    }

    /**
     * Chua cai tien: dung insertion sort.
     * Cai tien:
     */
    public static void sortDictionary(){
        for(int i = 0; i < word.size() - 1; i++){
            for(int j = i; j >= 0; j--){
                if(word.get(j+1).getWord_target().compareTo(word.get(j).getWord_target()) < 0){
                    swap(j, j + 1);
                }
                else break;
            }
        }
    }

    public static void swap(int a, int b){
        Word atA = word.get(a);     // con tro
        Word atB = word.get(b);
        Word temp = new Word(word.get(a).getWord_target(), word.get(a).getWord_explain());  // chu y khoi tao sao chep

        atA.setWord_target(atB.getWord_target());
        atA.setWord_explain(atB.getWord_explain());
        atB.setWord_target(temp.getWord_target());
        atB.setWord_explain(temp.getWord_explain());
    }


    /**
     * Chua cai tien: duyet full.
     * Cai tien: dung binary search.
     * @param target target
     * @return index of target
     */
    public static int findTarget(String target){
//        for (int i = 0; i < word.size(); i++){
//            if (target.equals(word.get(i).getWord_target())) {
//                return i;
//            }
//        }
//        return -1;

        sortDictionary();

        int low = 0;
        int hight = word.size() - 1;

        while (low <= hight){
            int mid = low + ((hight - low)/2);

            if(target.compareTo(word.get(mid).getWord_target()) > 0){
                low = mid + 1;
            }
            else if(target.compareTo(word.get(mid).getWord_target()) < 0){
                hight = mid - 1;
            }
            else return mid;
        }


        return -1;

    }

    /**
     * Chua cai tien: duyet full.
     * Cai tien: ung dung binary.
     * @param subString subString
     */
    public void dictionarySearcher(String subString){
//        for (Word tempWord : word) {
//            if (tempWord.getWord_target().indexOf(subString) == 0) {
//                System.out.println(tempWord.getWord_target() + " | " + tempWord.getWord_explain());
//            }
//        }

        try{
            int index = biSearchSubstring(subString);

            if(index == -1) {
                throw new Exception();
            }

            int start = unSlowStart(index, 2, subString);
            for(int i = start; i < word.size(); i++){
                if(word.get(i).getWord_target().indexOf(subString) != 0) {
                    break;
                }

                word.get(i).print();
            }

        }
        catch(Exception e){
            System.out.println("Substring not found");
        }

    }

    public int unSlowStart(int index, int step, String subString){
        if(step == 0) return index;
        if(index - step < 0 || word.get(index - step).getWord_target().substring(0, subString.length()).compareTo(subString) != 0){
            return unSlowStart(index, step - 1, subString);
        }

        return unSlowStart(index - step, step, subString);
    }

    public int biSearchSubstring(String subString){

        int low = 0;
        int hight = word.size() - 1;

        while (low <= hight){
            int mid = low + ((hight - low)/2);
            String target = word.get(mid).getWord_target();

            if(target.length() < subString.length()){
                String tempSub = subString.substring(0, target.length());
                if(tempSub.compareTo(target) < 0){
                    hight = mid - 1;
                }
                else if(tempSub.compareTo(target) > 0){
                    low = mid + 1;
                }
                else return mid;
            }
            else{
                target = target.substring(0, subString.length());
                if(subString.compareTo(target) < 0){
                    hight = mid - 1;
                }
                else if(subString.compareTo(target) > 0){
                    low = mid + 1;
                }
                else return mid;
            }

        }

        return -1;
    }


    public void dictionaryExportToFile(){
        try {
            FileWriter file = new FileWriter("src/official_dictionary/dictionaries.txt");
            for(Word newWord : word){
                file.write(newWord.getWord_target());
                file.write(" = ");
                file.write(newWord.getWord_explain());
                file.write("\n");
            }
            file.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }


    public static void main(String[] strings) throws FileNotFoundException {
        Dictionary dictionary = new Dictionary();

    }
}
