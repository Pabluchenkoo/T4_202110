package model.data_structures;

import utils.Ordenamiento;

public class TablaHashLinearProbing < K extends Comparable<K>, V extends Comparable<V> > implements ITablaSimbolos<K, V> 
{
	
	
	/**
	 * Representa el factor de carga actual.
	 */
	private double factorDeCarga;

	/**
	 * Representa el total de elementos del mapa.
	 */
	private int totalElementos;

	/**
	 * Representa el arreglo utilizado en el mapa;
	 */

	private ArregloDinamico<NodoTablas<K,V>> mapa;

	/**
	 * Representa la constante a utilizada en el MAD.
	 */
	private int a;

	/**
	 * Representa la constante b utilizada en el MAD.
	 */
	private int b;

	/**
	 * Representa la constante p utilizada en el MAD.
	 */
	private int p;

	/**
	 * Representa la constante m utilizada en el MAD.
	 */
	private int m;
	
	/**
	 * Representa el numero de rehash desde que se creo.
	 */
	private int nreHash; 

//	public int contador;
	/**
	 * Constructor onstructor de la clase.
	 * @param size. Tamano inicial de la clase.
	 */
	public TablaHashLinearProbing( int size ) 
	{
		m = Ordenamiento.getNextCategory(2*size);
		p = Ordenamiento.getNextCategory(m);
		a  = (int) (Math.random() * (p-1)+1);
		b  = (int) (Math.random() * (p-1)+1);
		mapa = new ArregloDinamico<NodoTablas<K,V>> (m);
//		contador = 0;
	}

	/**
	 * Mete un elemento al mapa.
	 * @param key. LLave del nodo.
	 * @param value. Valor del nodo.
	 */
	public void put(K key, V value) 
	{
		
		if((darFactorDeCarga() + (1/m)) >= 0.75)
			rehash( );
		
		int pos = getPos(key);
		NodoTablas<K,V> act = mapa.darElemento(pos);
//		int numero1 = Integer.parseInt((String) numero);
		if(act == null || act.getKey().equals("EMPTY"))
		{
			
			NodoTablas<K,V> nuevo = new NodoTablas<K,V>(key, value);
			
			mapa.changeInfo(pos, nuevo);
			totalElementos++;
			
//			numero1 = 1;
//			contador = 1;
		}
		
		else if(act.getKey().equals(key))
		{
			
			act.cambiarValor( value );
			
//			contador ++;
//			numero1++;
		}
		
		else
			putRecursiveVersion(pos + 1, key, value);

		verificarInvariante();
	}

	/**
	 * Mete un elemento al mapa recursivamente.
	 * @param pos. Posicion actual a revisar.
	 * @param key. LLave del nodo.
	 * @param value. Valor del nodo.
	 */
	private void putRecursiveVersion(int pos, K key, V value)
	{
		if(pos > m)
			pos = 1;
		NodoTablas<K,V> act = mapa.darElemento(pos);
		if(act == null || act.getKey().equals("EMPTY"))
		{
			NodoTablas<K,V> nuevo = new NodoTablas<K,V>(key, value);
			mapa.changeInfo(pos, nuevo);
			totalElementos++;
		}
		
		else if(act.getKey().equals(key))
			act.cambiarValor(value);
		
		else
			putRecursiveVersion(pos + 1, key, value);
	}

	/**
	 * Busca un elemento del mapa.
	 * @param key. LLave del nodo.
	 */
	@Override
	public V get( K key ) 
	{
		int pos = getPos(key);
		NodoTablas<K,V> act = mapa.darElemento(pos);
		
		if(act != null && key.equals(act.getKey()))
			return act.getValue();
		
		else if(act != null && act.getKey().equals("EMPTY"))
			return getRecursiveVersion(pos + 1, key);
		
		else
			return null;
	}

	/**
	 * Busca un elemento del mapa recursivamente.
	 * @param key. LLave del nodo.
	 */
	private V getRecursiveVersion(int pos, K key) 
	{
		V retorno = null;
		if(pos > m )
			pos = 1;
		
		NodoTablas<K,V> act = mapa.darElemento(pos);
		
		if(act != null && key.equals(act.getKey()))
			retorno =  act.getValue();
		
		else if(act != null &&  act.getKey().equals("EMPTY"))
			
			retorno = getRecursiveVersion(pos+1, key);
		
		return retorno;
	}

	/**
	 * Elimina un elemento del mapa.
	 * @param key. LLave del nodo.
	 */
	@Override
	public V remove( K key) 
	{
		int pos = getPos(key);
		V retorno = null;
		NodoTablas<K,V> act = mapa.darElemento(pos);
		
		if(act != null && key.equals(act.getKey()))
		{
			retorno = act.getValue();
			act.cambiarValor(null);
			totalElementos--;
		}
		
		else 
			return deleteRecursiveVersion(pos + 1, key);

		verificarInvariante();
		return retorno;
	}

	/**
	 * Elimina un elemento del mapa recursivamente.
	 * @param key. LLave del nodo.
	 */
	private V deleteRecursiveVersion(int pos, K key) 
	{
		if(pos >= (m-1))
			pos = 0;
		
		NodoTablas<K,V> act = mapa.darElemento(pos);
		if(act != null && key.equals(act.getKey()))
		{
			V retorno = act.getValue();
			act.cambiarValor(null);;
			totalElementos--;
			return retorno;
		}
		
		else if(key.equals("EMPTY") || (act != null && act.getKey() != null))
			return getRecursiveVersion(pos + 1, key);
		
		else
			return null; 
	}

	/**
	 * Busca un elemento en el mapa.
	 * @param key. LLave del nodo.
	 * @return true si esta, false de lo contrario.
	 */
	@Override
	public boolean contains( K key) 
	{
		return get(key) != null ? true:false;
	}

	/**
	 * Revisa si la tabla se encuentra vacia.
	 * @return True si esta vacia, false de lo contrario.
	 */
	public boolean isEmpty( )
	{
		return totalElementos > 0 ? false : true;
	}

	/**
	 * Retornar el numero de tuplas presentes en la tabla de simobolos.
	 * @return numero de tuplas presentes en la tabla de simobolos.
	 */
	public int size( )
	{
		return totalElementos;
	}

	@Override
	public ArregloDinamico<K> keySet() {
		// TODO Auto-generated method stub
		ArregloDinamico<K> respuesta = new ArregloDinamico<>(totalElementos);
		for ( int i = 0 ; i < m ; i++)
		{
			NodoTablas<K,V> temporal = mapa.darElemento(i);
			if (temporal != null && temporal.getKey() != null)
			{
				respuesta.addLast(temporal.getKey());
			}
		}
		return respuesta;
	}

	@Override
	public ArregloDinamico<V> valueSet() {
		// TODO Auto-generated method stub
		ArregloDinamico<V> respuesta = new ArregloDinamico<>(totalElementos);
		for ( int i = 0; i < m ; i++ )
		{
			
			NodoTablas<K,V> temporal = mapa.darElemento(i);
			if (temporal != null && temporal.getValue() != null  )
			{
				
				respuesta.addLast(temporal.getValue());
				
			}
			
		}
		return respuesta;
	}
//	public int numero( P numero)
//	{
//		int numero1 = Integer.parseInt((String) numero);
//		return numero1;
//	}
	
	public double darFactorDeCarga()
	{
		return (0.0 + totalElementos)/(0.0 + m);
	}
	
	public void rehash()
	{
		nreHash++;
		int j = m;
//		m = Extras.getNextPrime((2*j));
//		p = Extras.getNextPrime(m);
		a  = (int) (Math.random() * (p-1)+1);
		b  = (int) (Math.random() * (p-1)+1);
		ArregloDinamico<NodoTablas<K,V>> todo = getAll();
		totalElementos = 0;
		mapa = new ArregloDinamico<NodoTablas<K,V>>(m);
		
		for(int i = 1; i <= todo.size();i++)
		{
			NodoTablas<K,V> act= todo.darElemento(i);
			put(act.getKey(), act.getValue());
		}
	}
	
	public int numeroReHash()
	{
		return nreHash;
	}
	
	
	public ArregloDinamico<NodoTablas<K,V>> getAll() 
	{
		ArregloDinamico<NodoTablas<K,V>> respuesta = new ArregloDinamico<NodoTablas<K,V>>(totalElementos);
		for( int i = 0; i < m ; i++ )
		{
			NodoTablas<K,V> temporal = mapa.darElemento(i);
			if (temporal != null)
			{
				respuesta.addLast(temporal);
			}
		}
		return respuesta;
	}

	public int getPos(K key)
	{
		int hashInicial =  (a * key.hashCode( ) + b) % p;
		int hashFinal = Math.abs(hashInicial) % m;
		return hashFinal;
	}

	private void verificarInvariante( )
	{
		assert factorDeCarga < 0.75;
		assert factorDeCarga >= 0;
	}
//	protected int getContador()
//	{
//		return contador;
//	}

	
}