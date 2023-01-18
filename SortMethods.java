/**
 *	SortMethods - Sorts an array of Integers in ascending order.
 *
 *	@author  
 *	@since	6 December 2022
 */
import java.util.List;
import java.util.*;
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.ArrayList;
public class SortMethods {
	private List<City> temp;
	/**
	 *	Bubble Sort algorithm - in ascending order
	 *	@param arr		array of Integer objects to sort
	 */
	public void bubbleSort(List<City>  arr) 
	{
			for(int outer = arr.size() -1; outer > 0; outer--)
				for(int inner = 0; inner < outer; inner++)
					if(arr.get(inner).compareTo(arr.get(inner+1)) > 0)
						swap(arr, inner, inner + 1);
	}
	
	/**
	 *	Swaps two Integer objects in array arr
	 *	@param arr		array of Integer objects
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 */
	private void swap(List<City>  arr, int x, int y) 
	{
		City temporary = arr.get(x);
		
		arr.set(x,arr.get(y));
		arr.set(y,temporary);
	}
	
	/**
	 *	Selection Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void selectionSort(List<City>  arr) 
	{
		
		for(int i = 0; i < arr.size() -1 ; i ++)
		{
			int pointer1 = i;
			for(int pointer2 = pointer1 + 1; pointer2 < arr.size(); pointer2++)
			{
				if(arr.get(pointer1).compareTo(arr.get(pointer2)) > 0)
				{
					pointer1 = pointer2;
				}
			}
			swap(arr, pointer1, i);
			
		}
	}
	
	/**
	 *	Insertion Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void insertionSort(List<City>  arr) 
	{
		for(int i = 1; i < arr.size(); i ++)
		{
			City temporary = arr.get(i);
			
			int n = i;
			while(n > 0 && arr.get(n-1).compareName(temporary) > 0)
			{
				swap(arr, n, n-1);
				n--;
			}
			
			arr.set(n,temporary);
		}
	}
	
	/**
	 *	Merge Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void mergeSort(List<City>  arr)
	{
		int n = arr.size();
		temp = new ArrayList<City>(Arrays.asList(new City[n]));
		recursiveSort(arr, 0, n-1);
	}
	
	public void recursiveSort(List<City>  array, int from, int to)
	{
		if(to - from < 2)
		{
			if(to > from && array.get(to).compareTo(array.get(from)) > 0)
			{
				swap(array, to, from);				
			}
		}
		else
		{
			int middle = (from + to)/2;
			recursiveSort(array, from, middle);
		
			recursiveSort(array, middle + 1, to);
		
			merge(array, from, middle, to);
			
			
		}
		
	}
	
	public void merge(List<City> array, int from, int middle, int to)
	{
		int i = from;
		int j = middle+1;
		int k = from;
		
		while(i <= middle && j <= to)
		{
			if(array.get(i).compareTo(array.get(j)) > 0)
			{
				temp.set(k,array.get(i));
				i++;
			}
			else
			{
				temp.set(k,array.get(j));
				j++;
			}
			k++;
		}
		
		while(i <= middle)
		{
			temp.set(k,array.get(i));
			i++;
			k++;
		}
		
		while(j <= to)
		{
			temp.set(k,array.get(j));
			j++;
			k++;
		}
		
		for(k = from; k <= to; k++)
		{
			array.set(k,temp.get(k));
		}
	}
	
	public void mergeSortName(List<City>  arr)
	{
		int n = arr.size();
		temp = new ArrayList<City>(Arrays.asList(new City[n]));
		recursiveSortName(arr, 0, n-1);
	}
	
	public void recursiveSortName(List<City>  array, int from, int to)
	{
		if(to - from < 2)
		{
			if(to > from && array.get(to).compareName(array.get(from)) > 0)
			{
				swap(array, to, from);				
			}
		}
		else
		{
			int middle = (from + to)/2;
			recursiveSortName(array, from, middle);
		
			recursiveSortName(array, middle + 1, to);
		
			mergeName(array, from, middle, to);
			
			
		}
		
	}
	
	public void mergeName(List<City> array, int from, int middle, int to)
	{
		int i = from;
		int j = middle+1;
		int k = from;
		
		while(i <= middle && j <= to)
		{
			if(array.get(i).compareName(array.get(j)) > 0)
			{
				temp.set(k,array.get(i));
				i++;
			}
			else
			{
				temp.set(k,array.get(j));
				j++;
			}
			k++;
		}
		
		while(i <= middle)
		{
			temp.set(k,array.get(i));
			i++;
			k++;
		}
		
		while(j <= to)
		{
			temp.set(k,array.get(j));
			j++;
			k++;
		}
		
		for(k = from; k <= to; k++)
		{
			array.set(k,temp.get(k));
		}
	}
		
	
	/*****************************************************************/
	/************************* For Testing ***************************/
	/*****************************************************************/
	
	/**
	 *	Print an array of Integers to the screen
	 *	@param arr		the array of Integers
	 */
	public void printArray(List<City>  arr) {
		if (arr.size() == 0) System.out.print("(");
		else System.out.printf("( %4d", arr.get(0));
		for (int a = 1; a < arr.size(); a++) {
			if (a % 10 == 0) System.out.printf(",\n  %4d", arr.get(a));
			else System.out.printf(", %4d", arr.get(a));
		}
		System.out.println(" )");
	}

	public static void main(String[] args) {
		SortMethods se = new SortMethods();
		se.run();
	}
	
	public void run() {
		List<City>  arr = new ArrayList<City>();
		// Fill arr with random numbers
		/*
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nBubble Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		bubbleSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();
		* */
	/*
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nSelection Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		selectionSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();


		for (int a = 0; a < 10; a++)
			arr.set(a, (int)(Math.random() * 100) + 1);
		System.out.println("\nInsertion Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		insertionSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();

		
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nMerge Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		mergeSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();
	*/
	}
}
