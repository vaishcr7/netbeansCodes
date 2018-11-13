package ibitmaxcontsrsone;

import java.util.ArrayList;

public class IBitMaxContSrsOne {
    public static void main(String[] args) {
        ArrayList<Integer> tp=new ArrayList<>();
        tp.add(1);
//        tp.add(0);
//        tp.add(0);
        tp.add(1);
//        tp.add(0);
//        tp.add(0);
//        tp.add(0);
//        tp.add(0);
        tp.add(1);
        tp.add(1);
        tp.add(1);
//        tp.add(0);
        tp.add(1);
        System.out.println(getLongSrs(tp,10));
//        System.out.println("al.size= "+tp.size());
    }
    public static ArrayList<Integer> getLongSrs(ArrayList<Integer> al,int m)
    {
//        System.out.println("al= "+al);
        ArrayList<obj> objCollection=new ArrayList<>();
        if(al.isEmpty())
            return al;
        int ls=0;
        int rs=0;
        for (int i = 0; i < al.size(); i++) {
            if(al.get(i).equals(0))
            {
                 obj a=new obj(i);
                 if(!objCollection.isEmpty())
                 {
                     ls=i-objCollection.get(objCollection.size()-1).pos-1;
                     a.numOfOnesToLeft=ls;
                     objCollection.get(objCollection.size()-1).numOfOnesToRight=ls;
                 }
                 else
                 {
                     ls=i;
                     a.numOfOnesToLeft=ls;
                 }
                 objCollection.add(a);
            }
        }
        if(objCollection.size()==0)
        {
            ArrayList<Integer> ap=new ArrayList<>();
            for (int i = 0; i < al.size(); i++) {
                ap.add(i);
            }
            return ap;
        }
        if(objCollection.size()==1)
            objCollection.get(0).numOfOnesToRight=al.size()-1-objCollection.get(0).pos;
        if(objCollection.size()>1)
            objCollection.get(objCollection.size()-1).numOfOnesToRight=al.size()-1-objCollection.get(objCollection.size()-1).pos;
        while(m>objCollection.size())
            m--;
        System.out.println("m= "+m);
        for (int i = 0; i < objCollection.size(); i++) {
            System.out.println(objCollection.get(i).pos+" -> left: "+objCollection.get(i).numOfOnesToLeft+" , right: "+objCollection.get(i).numOfOnesToRight);
        }
        
        if(m==0)
        {
            int stChosenObj=-1;
            int min=-1;
            boolean goTowardsLeft=false;
            for (int i = 0; i < objCollection.size(); i++) {
                int y=objCollection.get(i).numOfOnesToLeft;
                int z=objCollection.get(i).numOfOnesToRight;
                int h=Math.max(y, z);
                if(h>min)
                {
                    min=h;
                    stChosenObj=i;
                    if(h==y)
                        goTowardsLeft=true;
                    else
                        goTowardsLeft=false;
                }
            }
            stChosenObj=objCollection.get(stChosenObj).pos;
//            System.out.println("min= "+min+" and stpos= "+stChosenObj+" and goto left= "+goTowardsLeft);
            ArrayList<Integer> ap=new ArrayList<>();
            if(goTowardsLeft)
            {
                for (int i = Math.abs(stChosenObj-min);i<stChosenObj ;i++) {
                    ap.add(i);
                }
            }
            else
                {
                for (int i=stChosenObj+1;i <=Math.abs(stChosenObj+min) ;i++) {
                    ap.add(i);
                }
            }
            return ap;
        }

        int stChosenObj=-1;
        int min=-1;
        for (int j = 0; j <=objCollection.size()-m; j++) {
            int y=0;
            for (int k = 0; k < m; k++) {
                 y+=(objCollection.get(j+k).numOfOnesToLeft)+1;
            }
//            System.out.println("initial y= "+y);
            y+=objCollection.get(j+m-1).numOfOnesToRight;
            if(y>min)
            {
                min=y;
                stChosenObj=j;
            }
            System.out.println("y for index "+j+" is "+y);
          }
        System.out.println("min= "+min+" and chosen index= "+stChosenObj);
        ArrayList<Integer> ap=new ArrayList<>();
        int i = objCollection.get(stChosenObj).pos;
        int leftToIt=objCollection.get(stChosenObj).numOfOnesToLeft;
        for (int j = Math.abs(i-leftToIt); j <(min+Math.abs(i-leftToIt)); j++) {
            ap.add(j);
        }
        return ap;
    }
    
}
class obj
{
    int numOfOnesToLeft;
    int numOfOnesToRight;
    int pos;

    public obj(int pos) {
        this.numOfOnesToLeft = Integer.MIN_VALUE;
        this.numOfOnesToRight = Integer.MIN_VALUE;
        this.pos = pos;
    }
    
}
