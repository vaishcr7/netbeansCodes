package spojinvcnt;

public class SpojInvcnt {

    public static void main(String[] args) {
      int i=2;
      convertToBin(0,1<<i);
    }
    public static void convertToBin(int h,int a)
    {
    a=(a)|h;
        System.out.println("a= "+a);
    StringBuffer sba=new StringBuffer();
    while(a>0)
    {
        sba.append(a%2);
        a/=2;
    }
        System.out.println(sba.reverse().toString());
    }
}
