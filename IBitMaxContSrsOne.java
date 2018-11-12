package ibitmaxcontsrsone;

import java.util.ArrayList;

public class IBitMaxContSrsOne {
    public static void main(String[] args) {
        ArrayList<Integer> tp=new ArrayList<>();
        tp.add(1);
        tp.add(1);
        tp.add(0);
        tp.add(0);
        tp.add(1);
        tp.add(1);
        tp.add(0);
        tp.add(1);
        tp.add(1);
        tp.add(1);
        System.out.println(getLongSrs(tp, 1));
    }
    public static ArrayList<Integer> getLongSrs(ArrayList<Integer> al,int m)
    {
        ArrayList<Integer> zeroRange=new ArrayList<Integer>();
        for (int i = 0; i < al.size(); i++) {
            if(al.get(i).equals(0))
                zeroRange.add(i);
        }
        int r=-1,stpos=-1;
        if(m<1)
        {
            for (int k = 0; k < zeroRange.size(); k++) {
                if(k==0)
                {
                    r=zeroRange.get(k)-0;
                    stpos=0;
                }
                else
                {
                    int p=(zeroRange.get(k)-zeroRange.get(k-1)-1);
                    if(r<p)
                    {
                        r=p;
                        stpos=k-1;
                    }
                }
            }
        }
        else
        {
            while(m>zeroRange.size())  //if we can flip three but only 2 zeroes there in list 
                m--;
            int i=0;
            int j=m;
            while(j<zeroRange.size())
            {
                int p,st;
                if(i==0)
                {
                    p=(zeroRange.get(j));
                    st=0;
                }
                else
                {
                    p=(zeroRange.get(j)-zeroRange.get(i));
                    st=i;
                    
                }
                if(r<p)
                {
                    r=p;
                    stpos=st;
                }
                i++;
                j++;
            }
        }
        if(zeroRange.get(0).equals(stpos))
        {
            stpos=0;
            r+=(zeroRange.get(0));
        }
        if(zeroRange.get(zeroRange.size()-1).equals((stpos+r)))
        {
            r+=al.size()-1-(zeroRange.size()-1);
        }
        ArrayList<Integer> ap=new ArrayList<>();
        for (int i = stpos; i <stpos+r; i++) {
            ap.add(i);
        }
        return ap;
    }
    
}
