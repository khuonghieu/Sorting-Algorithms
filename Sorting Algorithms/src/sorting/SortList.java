
package sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SortList{
    public List<Integer> list;
    public int compare;
    public int exchange;
    public SortList(){
        list= new ArrayList<>();
        compare =0;
        exchange =0;
    }
    
    private void insertionSort(){
        int n = list.size();
        for (int i=1; i<n; i++){
            int key = list.get(i);
            int j = i-1;
            int change=0;

            while (j>=0 && list.get(j) > key){
                compare++;
                list.set(j+1,list.get(j));
                j = j-1;
                change++;
            }
            list.set(j+1,key);
            if(change!=0){
                exchange++;
            }
        }
        System.out.println("There are "+compare+ " comparisons.");
        System.out.println("There are "+exchange+" exchanges.");
    }
    private int partition(int low, int high){
        int pivot = list.get(high); 
        int i = low-1; // index of smaller element
        for (int j=low; j<high; j++){
            // If current element is smaller than or
            // equal to pivot
            if (list.get(j) <= pivot){
                compare++;
                i++;
                int temp = list.get(i);
                list.set(i,list.get(j));
                list.set(j,temp);
                exchange+=1;
            }
        }
        int temp = list.get(i+1);
        list.set(i+1,list.get(high));
        list.set(high,temp);
        return i+1;
    }
    private void quickSort(int low, int high){
        if (low < high){
            compare++;
            int pi = partition(low, high);
            quickSort(low, pi-1);
            quickSort(pi+1, high);
        }
        
    }
    
    public void printArray(){
        int n = list.size();
        System.out.print("The list is ");
        for (int i=0; i<n; ++i){
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
    }
    
    public void merge(int l, int m, int r){
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;
 
        /* Create temp arrays */
        int L[] = new int [n1];
        int R[] = new int [n2];
 
        /*Copy data to temp arrays*/
        for (int i=0; i<n1; ++i)
            L[i] = list.get(l + i);
        for (int j=0; j<n2; ++j)
            R[j] = list.get(m + 1+ j);
 
 
        /* Merge the temp arrays */
 
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;
 
        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2){
            if (L[i] <= R[j]){
                compare++;
                list.set(k,L[i]);
                exchange++;
                i++;
            }
            else{
                compare++;
                list.set(k,R[j]);
                exchange++;
                j++;
            }
            k++;
        }
 
        /* Copy remaining elements of L[] if any */
        while (i < n1){
            list.set(k,L[i]);
            exchange++;
            i++;
            k++;
        }
 
        /* Copy remaining elements of R[] if any */
        while (j < n2){
            list.set(k,R[j]);
            exchange++;
            j++;
            k++;
        }
    }    
    public void mergeSort(int l, int r){
        if (l < r)
        {
            // Find the middle point
            int m = (l+r)/2;
 
            // Sort first and second halves
            mergeSort(l, m);
            mergeSort(m+1, r);
 
            // Merge the sorted halves
            merge(l, m, r);
        }
    }

    public static void main(String args[]){        
        
        Scanner scan = new Scanner(System.in);
        System.out.println("Please type in the size of the list you want: ");
        int m = scan.nextInt();
        Random rand = new Random();
        
        
        // QUICKSORT
        
        SortList sortList1 = new SortList();
        for (int i=0;i<m;i++){
            int n = rand.nextInt(100);
            sortList1.list.add(n);
        }
        sortList1.printArray();
        long startTime1 = System.nanoTime(); 
        sortList1.quickSort(0,m-1);
        long estimatedTime1 = System.nanoTime() - startTime1;
        System.out.println("There are "+sortList1.compare+" comparisons.");
        System.out.println("There are "+sortList1.exchange+" exchanges.");
        System.out.println("QUICK SORT DONE.");
        sortList1.printArray();
        System.out.println("The run time is "+estimatedTime1+" nanoseconds.");  
        
        
        System.out.println("\n\n");
        
        //INSERTION SORT
        
        SortList sortList2 = new SortList();
        for (int i=0;i<m;i++){
            int n = rand.nextInt(100);
            sortList2.list.add(n);
        }
         
        sortList2.printArray();
        long startTime2 = System.nanoTime();
        sortList2.insertionSort();
        System.out.println("INSERTION SORT DONE.");
        long estimatedTime2 = System.nanoTime() - startTime2;
        sortList2.printArray();
        System.out.println("The run time is "+estimatedTime2+" nanoseconds.");    
        
        
        System.out.println("\n\n");
        
        //MERGE SORT

        SortList sortList3 = new SortList();
        for (int i=0;i<m;i++){
            int n = rand.nextInt(100);
            sortList3.list.add(n);
        }
        
        sortList3.printArray();
        long startTime3 = System.nanoTime();
        sortList3.mergeSort(0,sortList3.list.size()-1);
        long estimatedTime3 = System.nanoTime() - startTime3;
        System.out.println("There are "+sortList3.compare+" comparisons.");
        System.out.println("There are "+sortList3.exchange+" exchanges.");
        System.out.println("MERGE SORT DONE.");
        sortList2.printArray();
        System.out.println("The run time is "+estimatedTime3+" nanoseconds.");  
    }
}


    
    

