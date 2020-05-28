package model;

import java.util.List;
import java.util.Random;

public class SortArray {
    private int[] arrayToSort;
    private int currentSize;
    private long sortingTime;


    SortArray(int currentSize)
    {
        this.currentSize = currentSize;
        this.sortingTime = sortTime(generateArray());
    }
    public long getSortingTime()
    {
        return sortingTime;
    }
    private int[] generateArray() {
        arrayToSort = new int[currentSize];
        Random random = new Random();
        for (int i = 0; i < arrayToSort.length; i++) {
            arrayToSort[i] = random.nextInt(currentSize);
        }
        return arrayToSort;
    }
    private int[] directExchangeSort(int[] array) {
        for(int i = 0; i < array.length - 1;i++)
        {
            for(int j = i; j < array.length;j++)
            {
                if(array[i] > array[j])
                {
                    int temp;
                    temp =array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        return  array;
    }
    public long sortTime(int[] arrayToSort) {
        long startTime = System.nanoTime() /10;
        directExchangeSort(arrayToSort);
        long endTime = System.nanoTime() /10;
        long result = endTime - startTime;
        return result;
    }
}
