class SeparateChainingSLLHashMap implements Map {
    private int size;
    private SinglyLinkedList[] table;

    SeparateChainingSLLHashMap(int length) {
        table = new SinglyLinkedList[length];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void remove(Object key) {
        if (table[h(key)] != null) {
            for (int y = 0; y < table[h(key)].size(); y++) {
                if (((Entry) table[h(key)].get(y)).key == key) {
                    table[h(key)].remove((Entry) table[h(key)].get(y));
                }
            }
        }
    }

    @Override
    public boolean containsKey(Object key) {
        return get(key)!=null;
    }

    @Override
    public void printTbl() {
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
                System.out.println("table[" + i + "]: " + null);
            } else
                System.out.println("table[" + i + "]: " + table[i]);
        }
    }

    public String get(Object key) {
        if (table[h(key)] == null) {
            return null;
        } else if (table[h(key)] != null) {
            for (int y = 0; y < table[h(key)].size(); y++) {
                if (((Entry) table[h(key)].get(y)).key == key) {
                    return ((Entry) table[h(key)].get(y)).element.toString();
                }
            }
        }
        return null;
    }

    public Object put(Object key, Object data) {
        Entry value = new Entry(key, data);
        Object oldVal = null;
        if (table[h(key)] == null) {
            table[h(key)] = new SinglyLinkedList();
            table[h(key)].add(value);
            size++;
        } else if (table[h(key)] != null) {
            for (int y = 0; y < table[h(key)].size(); y++) {
                if (((Entry) table[h(key)].get(y)).key == key) {
                    oldVal = table[h(key)];
                    table[h(key)].remove(y);
                    table[h(key)].add(value);
                    size++;
                    return oldVal;
                }
            }
            table[h(key)].add(value);
            size++;
        }
        return oldVal;
    }

    private int h(Object x) {
        return Math.abs(x.hashCode()) % table.length;
    }

    public static void main(String[] args) {
        Map hashTbl = new SeparateChainingSLLHashMap(13);
        hashTbl.put(6, "A");
        hashTbl.put(12, "B");
        hashTbl.put(19, "C");
        hashTbl.put(0, "D");
        hashTbl.put(6, "E");
        hashTbl.put(19, "F");
        hashTbl.put(32, "G");
        hashTbl.put(45, "H");
        hashTbl.printTbl();
        System.out.println("key:6, value:" + hashTbl.get(6));
        System.out.println("key 45 exist?: " + hashTbl.containsKey(45));
        System.out.println("key 17 exist?: " + hashTbl.containsKey(17));
        System.out.println("Remove key:45. The table becomes");
        hashTbl.remove(45);
        hashTbl.printTbl();
    }
}