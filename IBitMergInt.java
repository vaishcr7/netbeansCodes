package ibitmergint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import static java.util.concurrent.ThreadLocalRandom.current;

public class IBitMergInt {

    public static void main(String[] args) {
      
//        Interval obj1=new Interval(1, 3);
//        Interval obj2=new Interval(6, 9);
//      Interval obj1=new Interval(1, 2);
//      Interval obj2=new Interval(3, 5);
//      Interval obj3=new Interval(6, 7);
//      Interval obj4=new Interval(8, 10);
//      Interval obj5=new Interval(12, 16);
//      Interval objToInsert=new Interval(4, 9);
//        Interval objToInsert=new Interval(2, 5);
//        Interval obj1=new Interval(1, 2);
//        Interval obj2=new Interval(3, 6);
//        Interval objToInsert=new Interval(10, 8);
//     
//      Interval[] intervalsarr={new Interval(6037774, 6198243), new Interval(6726550, 7004541),new Interval(8842554, 10866536),new Interval(11027721, 11341296), new Interval(11972532, 14746848),new Interval(16374805, 16706396), new Interval(17557262, 20518214), new Interval(22139780, 22379559), new Interval(27212352, 28404611), new Interval(28921768, 29621583), new Interval(29823256, 32060921), new Interval(33950165, 36418956), new Interval(37225039, 37785557), new Interval(40087908, 41184444), new Interval(41922814, 45297414), new Interval(48142402, 48244133), new Interval(48622983, 50443163), new Interval(50898369, 55612831), new Interval(57030757, 58120901), new Interval(59772759, 59943999), new Interval(61141939, 64859907), new Interval(65277782, 65296274), new Interval(67497842, 68386607), new Interval(70414085, 73339545), new Interval(73896106, 75605861), new Interval(79672668, 84539434), new Interval(84821550, 86558001), new Interval(91116470, 92198054), new Interval(96147808, 98979097)};
//      ArrayList<Interval> intervals=new ArrayList<Interval>(Arrays.asList(intervalsarr));
//      intervals.add(obj1);
//      intervals.add(obj2);
//      intervals.add(obj3);
//      intervals.add(obj4);
//      intervals.add(obj5);
//(3,5),(8,10);(1,12)->(1, 12) answer
        ArrayList<Interval> intervals=new ArrayList<>();
        Interval objToInsert=new Interval(36210193, 61984219);
      intervals=insert(intervals, objToInsert);
      Collections.sort(intervals, new sortByInterval());
        for (int i = 0; i < intervals.size(); i++) {
            System.out.println("[ "+intervals.get(i).start+" , "+intervals.get(i).end+" ]");
        }
    }
    public static ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        
        if(newInterval.start>newInterval.end)
        {
            int a=newInterval.end;
            newInterval.end=newInterval.start;
            newInterval.start=a;
        }
        if(intervals.isEmpty())
        {
            intervals.add(newInterval);
            return intervals;
        }
    int numOfPairs=intervals.size();
    System.out.println("num of pairs= "+numOfPairs);
    boolean pairAdded=false;
    int i=0;
    while(i<numOfPairs)
    {
        System.out.println("i= "+i);
        if(pairAdded)
            break;
        Interval curInterval=intervals.get(i);
        System.out.println("current pair= [ "+curInterval.start+" , "+curInterval.end+" ]");
        if(curInterval.start<newInterval.start)
        {
            System.out.println("case1");
         if(curInterval.end<newInterval.end)  
         {
             System.out.println("case1a");
             if(i>=numOfPairs-1)
             {
                 intervals.add(newInterval);
                 System.out.println("case1aa");
             }
             else if(curInterval.end>newInterval.start)
             {
                 System.out.println("case1ab");
                 if (i < numOfPairs - 1) 
                 {
                     System.out.println("case1aba");
                     int numOfPairsToBeMerged = 0, j = i;
                     while (i < numOfPairs && (curInterval.end <= newInterval.end || curInterval.start<=newInterval.end)) {
                         System.out.println("old current interval= [ "+curInterval.start+" , "+curInterval.end+" ] ");
                         numOfPairsToBeMerged += 1;
                         i += 1;
                         if (i >= numOfPairs) {
                             break;
                         }
                         curInterval = intervals.get(i);
                         System.out.println("new current interval= [ "+curInterval.start+" , "+curInterval.end+" ] ");
                     }
                     System.out.println("number of pairs to  be merged= "+numOfPairsToBeMerged);
                     ArrayList<Interval> pi=new ArrayList<>(intervals);
                     intervals = removeRet(intervals, numOfPairsToBeMerged, j);
                     curInterval=pi.get(i-1);
                     if(!(curInterval.start<=newInterval.end))
                        intervals.add(newInterval);
                     else
                     {
                         System.out.println("case1abab");
                         System.out.println("max b/w "+pi.get(numOfPairsToBeMerged+j-1).end+" and "+newInterval.end);
                         newInterval.end=Math.max(newInterval.end,pi.get(numOfPairsToBeMerged+j-1).end);
                         newInterval.start=Math.min(newInterval.start,pi.get(j).start);
                         System.out.println("new interval= [ "+newInterval.start+" , "+newInterval.end+" ]");
                         intervals.add(newInterval);
                         pi=null;
                     }
                     pairAdded = true;
                 } 
                 else 
                 {
                     System.out.println("case1abb");
                     intervals.remove(curInterval);
                     intervals.add(newInterval);
                     pairAdded = true;
                 }
             }
             else
             {
                 i++;
                 System.out.println("case1ac");
                 continue;
             }                 
         }
         else 
         {
             System.out.println("case1b");
             pairAdded=true;
         }
        }
        else if(curInterval.start==newInterval.start)
        {
            System.out.println("case2");
          if(curInterval.end<newInterval.end)  
         {
             System.out.println("case2aa");
             if(i<numOfPairs-1)
             {
                 int numOfPairsToBeMerged=0,j=i;
                 while(i<numOfPairs && curInterval.end<=newInterval.end)
                 {
                     numOfPairsToBeMerged+=1;
                     i+=1;
                     if(i>=numOfPairs)
                         break;
                     curInterval=intervals.get(i);
                 }
                 intervals=removeRet(intervals,numOfPairsToBeMerged,j);
                 intervals.add(newInterval);
                 pairAdded=true;
             }
             else
             {
                 System.out.println("case2ab");
                 intervals.remove(curInterval);
                 intervals.add(newInterval);
                 pairAdded=true;
             }
         }
         else
         {
             System.out.println("case2b");
            pairAdded=true; 
         }
        }
        else
        {
            System.out.println("case3");
         if(curInterval.end<=newInterval.end)  
         {
             System.out.println("case3a");
             intervals.remove(curInterval);
             intervals.add(newInterval);
             pairAdded=true;
         }
         else
         {
             intervals.add(newInterval);
             pairAdded=true;
         }   
        }
        i++;
    }
    return intervals;
    }
    public static ArrayList<Interval> removeRet(ArrayList<Interval> intervals,int numOfPairsToBeMerged,int j)
    {
        System.out.println("edit func called");
        ArrayList<Interval> al=new ArrayList<>();
        for (int i = 0; i < j; i++) {
            al.add(intervals.get(i));
        }
        for (int i = j+numOfPairsToBeMerged; i < intervals.size(); i++) {
            al.add(intervals.get(i));
        }
        return al;
    }
}

  class Interval {
      int start;
      int end;
      Interval() { start = 0; end = 0; }
      Interval(int s, int e) { start = s; end = e; }
  }
class sortByInterval implements Comparator<Interval>
{

    @Override
    public int compare(Interval o1, Interval o2) {
        if(o1.start<o2.start)
            return -1;
        else if(o1.start==o2.start)
                if(o1.end<o2.end)
                    return -1;
                else
                    return 1;
        else
            return 1;
    }
    
}
