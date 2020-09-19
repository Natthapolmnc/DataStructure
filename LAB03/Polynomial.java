class Polynomial {
    Term header = new Term(0, 0, null);

    class Term {
        double co;
        int expo;
        Term next;

        Term(double c, int e, Term next) {
            this.co = c;
            this.expo = e;
            this.next = next;
        }
    }

    void addTerm(double coef, int exp) {
        Term currentTerm = this.header;
        while (currentTerm != null) {
            if (currentTerm.next != null) {
                if (currentTerm.next.expo < exp) {
                    Term newTerm = new Term(coef, exp, currentTerm.next);
                    currentTerm.next = newTerm;
                    break;
                } else if (currentTerm.next.expo == exp) {
                    currentTerm.co += coef;
                    break;
                } else {
                    currentTerm = currentTerm.next;
                }
            } else {
                Term newTerm = new Term(coef, exp, currentTerm.next);
                currentTerm.next = newTerm;
                break;
            }
        }

    }

    Polynomial add(Polynomial b) {
        Term currentTerm = this.header.next;
        Term currB = b.header.next;
        Polynomial thePolynomial = new Polynomial();
        Term thePointer = thePolynomial.header;
        while (currentTerm != null) {
            if (currentTerm != null && currB != null) {
                if (currentTerm.expo == currB.expo) {
                    Term newTerm = new Term(currentTerm.co + currB.co, currB.expo, null);
                    thePointer.next = newTerm;
                    thePointer = thePointer.next;
                    currentTerm = currentTerm.next;
                    currB = currB.next;
                    continue;
                } else if (currentTerm.expo < currB.expo) {
                    Term newTerm = new Term(currB.co, currB.expo, null);
                    thePointer.next = newTerm;
                    thePointer = thePointer.next;
                    Term newTerm2 = new Term(currentTerm.co, currentTerm.expo, null);
                    thePointer.next = newTerm2;
                    thePointer = thePointer.next;
                    currentTerm = currentTerm.next;
                    currB = currB.next;
                    continue;
                } else if (currentTerm.expo > currB.expo) {
                    Term newTerm2 = new Term(currentTerm.co, currentTerm.expo, null);
                    thePointer.next = newTerm2;
                    thePointer = thePointer.next;
                    Term newTerm = new Term(currB.co, currB.expo, null);
                    thePointer.next = newTerm;
                    thePointer = thePointer.next;
                    currentTerm = currentTerm.next;
                    currB = currB.next;
                    continue;
                }
            } else if (currentTerm != null && currB == null) {
                Term newTerm2 = new Term(currentTerm.co, currentTerm.expo, null);
                thePointer.next = newTerm2;
                thePointer = thePointer.next;
                currentTerm = currentTerm.next;
                continue;
            } else{
                Term newTerm = new Term(currB.co, currB.expo, null);
                thePointer.next = newTerm;
                thePointer = thePointer.next;
                currB = currB.next;
                continue;
            }
        }
        return thePolynomial;
    }

    Polynomial differential() {
        Term currentTerm = this.header.next;
        Polynomial thePolynomial = new Polynomial();
        Term thePointer = thePolynomial.header;
        while (currentTerm.expo != 0) {
            thePointer.next = new Term(currentTerm.co * currentTerm.expo, currentTerm.expo - 1, null);
            thePointer=thePointer.next;
            currentTerm = currentTerm.next;
        }
        return thePolynomial;
    }

    void printElement() {
        Term currentTerm = this.header.next;
        String stringTemp = "";
        while (currentTerm != null) {
            stringTemp += currentTerm.co + "x^" + currentTerm.expo + "+";
            currentTerm = currentTerm.next;
        }
        if (stringTemp.length() > 0) {
            System.out.println(stringTemp.substring(0, stringTemp.length() - 1));
        } else {
            System.out.println(stringTemp);
        }
    }

    public static void main(String[] args) {
        Polynomial p1 = new Polynomial();
        p1.addTerm(4, 2);
        p1.addTerm(7, 0);
        p1.addTerm(3, 4);
        p1.addTerm(5, 1);
        p1.printElement();
        Polynomial p2 = new Polynomial();
        p2.addTerm(6, 3);
        p2.addTerm(-8, 2);
        p2.printElement();
        Polynomial sum = p1.add(p2);
        sum.printElement();
        Polynomial sumdiff = sum.differential();
        sumdiff.printElement();
    }

}