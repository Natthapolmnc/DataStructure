class Entry{ 
    Object key;
    Object element;
    Entry(Object key,Object element){
        this.key=key;
        this.element=element;
    }

    public String toString(){
        return "("+key+","+element+")";
    }
}