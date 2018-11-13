package ibitmaxcontsrsone;

import java.util.ArrayList;

public class IBitMaxContSrsOne {
    public static void main(String[] args) {
        ArrayList<Integer> tp=new ArrayList<>();
        tp.add(1);
        tp.add(1);
        tp.add(1);
        tp.add(1);
        tp.add(1);
        tp.add(1);
        tp.add(1);
        tp.add(1);
        tp.add(1);
        tp.add(1);
        tp.add(1);
        tp.add(1);
        tp.add(0);
        System.out.println(getLongSrs(tp, 1));
//        System.out.println("al.size= "+tp.size());
    }
    public static ArrayList<Integer> getLongSrs(ArrayList<Integer> al,int m)
    {
        ArrayList<Integer> zeroRange=new ArrayList<>();
        if(al.isEmpty())
            return al;
        for (int i = 0; i < al.size(); i++) {
            if(al.get(i).equals(0))
                zeroRange.add(i);
        }
        System.out.println("zero range= "+zeroRange+" and al= "+al);
        int r=-1,stpos=-1;
        if(m==0)
        {
            for (int k = 0; k < zeroRange.size(); k++) {
                int p=-2;
                int st=-2;
                if(k==0)
                {
                    p=zeroRange.get(k)-0;
                    st=0;
                }
                else if(k==zeroRange.size()-1)
                {
                    p=(al.size()-zeroRange.get(k));
                    st=k+1;
                }
                else
                {
                    p=(zeroRange.get(k)-zeroRange.get(k-1));
                    st=k;
                }
                if (r < p) {
                    r = p;
                    stpos = st;
                }
                System.out.println("r= "+r+" and stpos= "+stpos);
            }
            ArrayList<Integer> ap = new ArrayList<>();
            for (int i = stpos; i < stpos + r; i++) {
                ap.add(i);
            }
            return ap;
        }
        else
        {
            while(m>zeroRange.size())  //if we can flip three but only 2 zeroes there in list 
                m--;
            int i=0;
            int j=m-1;
            System.out.println("m= "+m);
            while(j<zeroRange.size())
            {
                System.out.println("i= "+i+" and j= "+j);
                int p,st;
                if(i==0)
                {
                    p=(zeroRange.get(j));
                    st=0;
                }
                else
                {
                    p=(zeroRange.get(j)-zeroRange.get(i));
                    st=zeroRange.get(i);            
                    if(j==zeroRange.size()-1)
                    {
                        System.out.println("initial p= "+p);
                        p+=(al.size()-1-zeroRange.get(j));
                    }
                }
                if(r<p)
                {
                    r=p;
                    stpos=st;
                }
                System.out.println("r= "+r+" and stpos= "+stpos);
                i=j;
                j++;
            }
        }
        if(zeroRange.get(0).equals(stpos))
        {
            stpos=0;
            r+=(zeroRange.get(0));
            System.out.println("new r= "+r);
        }
        else
        {
            System.out.println("not zero start");
            int y = 0;
            while (zeroRange.get(y) < stpos) {
                y++;
            }
            if(y>0)
            {    y -= 1;
                System.out.println("y= " + y);
                r += (stpos - zeroRange.get(y) - 1);
                stpos = zeroRange.get(y) + 1;
                if(zeroRange.size()-1-stpos==m)
                    stpos+=1;
                System.out.println("stpos= " + stpos + " and r= " + r);
            }
        }
        if(zeroRange.get(zeroRange.size()-1).equals((stpos+r)))
        {
            r+=al.size()-(zeroRange.get(zeroRange.size()-1));
            System.out.println("modified r= "+r);
        }
        else
        {
            System.out.println("not zero end");
            int x=zeroRange.size()-1;
            while(zeroRange.get(x)>(stpos+r))
                x--;
            if(x!=zeroRange.size()-1)
            {
                x+=1;
                System.out.println("x= " + x);
                r += (zeroRange.get(x) - stpos - r);
                System.out.println("stpos= " + stpos + " and r= " + r);
            }
            else
            {
                r+=(al.size()-(stpos+r));
                System.out.println("stpos= "+stpos+" and new r= "+r); 
            }       
        }
        ArrayList<Integer> ap=new ArrayList<>();
        for (int i = stpos; i <stpos+r; i++) {
            ap.add(i);
        }
        return ap;
    }
    
}
