package utils;

import java.util.Comparator;

import model.data_structures.ILista;

public final class Ordenamiento <T extends Comparable<T>>
{
	public final void ordenarSeleccion(ILista<T> lista, Comparator<T> criterio, boolean ascendente)
	{
		for (int i = 0; i < lista.size(); i++) 
		{
			int mayor=i;
			for (int j = i+1; j < lista.size(); j++) 
			{
				int ascendiendo=ascendente?1:-1;
				int factor=ascendiendo*criterio.compare(lista.getElement(j), lista.getElement(mayor));
				if (factor<0)
				{
					mayor=j;
				}


			}
			lista.exchange(mayor, i);
		}
	}
	public final void ordenarInsercion(ILista<T> lista, Comparator<T> criterio, boolean ascendente)
	{

		for (int i = 1; i < lista.size(); i++) 
		{
			boolean posicion=false;
			for (int j = i; j >1 && !posicion; j--) 
			{
				int ascendiendo = ascendente?1:-1;
				int factor = ascendiendo*criterio.compare(lista.getElement(j), lista.getElement(j-1));
				if (factor<0)
				{
					lista.exchange(j, j-1);
				}
				else
					posicion=true;
			}
		}
	}
	public final void ordenarShellSort(ILista<T> lista, Comparator<T> criterio, boolean ascendente)
	{
		int n=lista.size();
		int h=1;
		while(h<n/3)
		{
			h=h*3+1;
		}
		while(h>=1)
		{
			for (int i = h+1; i <= n; i++) 
			{
				boolean posicion=false;
				for (int j = i; j > h &&!posicion; j-=h) 
				{
					int ascendiendo=ascendente?1:-1;
					int factor = ascendiendo*criterio.compare(lista.getElement(j), lista.getElement(j-h));
					if (factor<0)
					{
						lista.exchange(j, j-h);	
					}
					else
						posicion=true;
				}
			}
		}
	}
	public final void ordenarMergeSort(ILista<T> lista, Comparator<T> criterio, boolean ascendente)
	{
		int size = lista.size();
		if(size > 1)
		{
			int mid = size/2;
			ILista<T> leftList = lista.subList(1, mid);
			ILista<T> rightList = lista.subList(mid+1, size - mid);

			ordenarMergeSort(leftList, criterio, ascendente);
			ordenarMergeSort(rightList, criterio, ascendente);

			int i,j,k;
			i=j=k= 1;

			int leftelements = leftList.size();
			int rightelements = rightList.size();

			while(i <= leftelements && j <= rightelements)
			{
				T elemi = leftList.getElement(i);
				T elemj = rightList.getElement(j);
				int factorComparacion = (ascendente?1:-1) * criterio.compare(elemi, elemj);

				if(factorComparacion <= 0) 
				{
					lista.changeInfo(k, elemi);
					i++;
				}
				else
				{
					lista.changeInfo(k, elemj);
					j++;
				}
				k++;
			}
			while(i <= leftelements)
			{
				lista.changeInfo(k, leftList.getElement(i));
				i++;
				k++;
			}

			while(j <= rightelements)
			{
				lista.changeInfo(k, rightList.getElement(j));
				j++;
				k++;
			}
		}
	}
	private final int partition(ILista<T> lista, Comparator<T> criterio, boolean ascendente, int lo, int hi)
	{
		int follower, leader;
		follower = leader = lo;
		while (leader < hi)
		{
			int factorComparacion = (ascendente?1:-1) * criterio.compare(lista.getElement(leader), lista.getElement(hi));
			if(factorComparacion < 0)
			{
				lista.exchange(follower, leader);
				follower ++;
			}
			leader ++;
		}
		lista.exchange(follower, hi);
		return follower;
	}


	private final void sort(ILista<T> lista, Comparator<T> criterio, boolean ascendente, int lo, int hi)
	{
		if(lo >= hi)
			return;
		int pivot = partition(lista, criterio, ascendente, lo, hi);
		sort(lista, criterio, ascendente, lo, pivot - 1);
		sort(lista, criterio, ascendente, pivot +1, hi);
	}


	public final void ordenarQuickSort(ILista<T> lista, Comparator<T> criterio, boolean ascendente)
	{
		sort(lista, criterio, ascendente, 1, lista.size());
	}
	
	public static boolean isCategory( int n )
	{
		if(n <= 1)
			return false;
		if(n <= 3)
			return true;
		if(n % 2 ==0 || n % 3 == 0)
			return false;
		for(int i = 5; i < ((int) Math.sqrt(n) + 1); i+= 6)
		{
			if(n % i == 0 || n % (i + 2) == 0)
				return false;
		}
		return true;
	}
	public static int getNextCategory(int n)
	{
		if (n <= 1)
			return 2;
		int prime = (int) n;
		boolean found = false;
		while(!found)
		{
			prime += 1;
			if(isCategory(prime))
				found = true;
		}
		return prime; 
	}

}
