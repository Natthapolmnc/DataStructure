public interface Map {
    public int size();
    public boolean isEmpty();
    public boolean containsKey(Object key);
    public Object get(Object key);
    public Object put(Object key, Object value);
    public void remove(Object key);        
    public void printTbl();
}
