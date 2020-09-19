
import java.util.Stack;

public class AddingBigNumber {
    public static int add(int num1, int num2) {
        int biggerNum=num1>num2 ? num1:num2;
        int lessNum=num1>num2 ? num2:num1;
        String strLnum=lessNum+"";
        Stack biggerStack=new Stack<>();
        Stack stackLess=new Stack<>();
        Stack result=new Stack<>();
        String strRes="";
        for (int i=0;i< (biggerNum+"").length();i++){
            int theNum=getDigit(biggerNum, (biggerNum+"").length()-i-1);
            biggerStack.push(theNum);
        }
        for (int i=0;i< (lessNum+"").length();i++){
            int theNum=getDigit(lessNum, (lessNum+"").length()-i-1);
            stackLess.push(theNum);
        }
        int total2=0;
        for (int y=0;y<strLnum.length();y++){
            int sum=(int) biggerStack.pop()+(int) stackLess.pop();
            int total1=sum%10;
            result.push(total1+total2);
            if (sum >=10) {
                total2=1;
            }else if(sum<10){
                total2=0;
            }
        }
        if(biggerStack.isEmpty()){
            result.push(total2);
        }
        while (!biggerStack.isEmpty()){
            int sum=(int) biggerStack.pop();
            result.push(sum+total2);
            if (sum+total2 >=10) {
                total2=1;
            }else if(sum+total2<10){
                total2=0;
            }
        }
        while (!result.isEmpty()){
            int theNum=(int) result.pop();
            strRes+= theNum+"";
        }
        return Integer.parseInt(strRes);
    }



    public static int getDigit(Integer v, int k) {
        int n = v.intValue();
        if (v.toString().length() - 1 >= k) {
            for (int i = 0; i < k; i++) {
                n /= 10;
            }
            return n % 10;
        } else
            return 0;
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        int b=add(9999999, 9999999);
        System.out.println("func1:"+(System.nanoTime()-startTime));
        long startTime2 = System.nanoTime();
        int a=9999999+9999999;
        System.out.println("func2:"+(System.nanoTime()-startTime2));
    }
}