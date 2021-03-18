package model.data_structures;



public class NodoTablas <K extends Comparable<K>, V extends Comparable<V> > implements Comparable<NodoTablas<K,V>>
{

    private K key;

    private V value;

    public NodoTablas (K pLlave , V pValor)
    {
        key = pLlave;

        value = pValor;
    }

    public K getKey ()
    {
        return key;
    }

    public V getValue ()
    {
        return value;
    }

    public void cambiarValor ( V pValor )
    {
        value = pValor;
    }
    @Override
    public int compareTo(NodoTablas<K, V> arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

}

