class Splay {
    SplayNode root;
    SplayNode header = new SplayNode(null, null, null);

    Splay() {
        root = new SplayNode(null, null, null);
    }

    public void splay(int target){
        SplayNode p=root;
        SplayNode r=header;
        SplayNode l=header;
        header.setLeft(null);
        header.setRight(null);
        while (true){
            if (target>p.element){
                if (p.getRight()==null){
                    break;
                }
                if (target>p.getRight().getElement()){
                    SplayNode pChild=p.getRight();
                    p.setRight(pChild.getLeft());
                    pChild.setLeft(p);
                    p=pChild;   
                    if (p.getRight()==null){
                        break;
                    }
                }
                l.setRight(p);
                l=p;
                p=p.getRight();
            }else if (target<p.element){
                if (p.getLeft==null){
                    break;
                }
                if (target>p.getLeft().getElement()){
                    SplayNode pChild=p.getLeft();
                    p.setLeft(pChild.getRight());
                    pChild.setRight(p);
                    p=pChild;   
                    if (p.getLeft()==null){
                        break;
                    }
                }
                r.setLeft(p);
                r=p;
                p=p.getLeft();
            }else break;
            l.setRight(p.getLeft());
            r.setLeft(p.getRight());
            p.setLeft(header.getRight());
            p.setRight(header.getLeft());
            root=p;
        }
    }
}