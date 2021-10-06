package official_dictionary;

public class Solution{
    public static int[] arr = new int[]{25, 2, 14, 11, 16, 23, 8, 0};

    public static void insertionSort(){
        for(int i = 0; i < arr.length - 1; i++){
            for(int j = i; j >= 0; j--){
                if(arr[j+1] < arr[j]){
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
                else break;
            }
        }
    }


    public static void print(){
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }

    public static int binarySearch(int x){
        int low = 0;
        int hight = arr.length;

        while (low <= hight){
            int mid = low + (int)((hight - low)/2);

            if(arr[mid] < x){
                low = mid + 1;
            }
            else if(arr[mid] > x){
                hight = mid - 1;
            }
            else return mid;
        }


        return -1;
    }

    public static void main(String[] strings){
        insertionSort();

        print();

        System.out.println("\n" + binarySearch(11));

    }
}