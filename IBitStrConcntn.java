package ibitstrconcntn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IBitStrConcntn {
    /*
    "pjzkrkevzztxductzzxmxsvwjkxpvukmfjywwetvfnujhweiybwvvsrfequzkhossmootkmyxgjgfordrpapjuunmqnxxdrqrfgkrsjqbszgiqlcfnrpjlcwdrvbumtotzylshdvccdmsqoadfrpsvnwpizlwszrtyclhgilklydbmfhuywotjmktnwrfvizvnmfvvqfiokkdprznnnjycttprkxpuykhmpchiksyucbmtabiqkisgbhxngmhezrrqvayfsxauampdpxtafniiwfvdufhtwajrbkxtjzqjnfocdhekumttuqwovfjrgulhekcpjszyynadxhnttgmnxkduqmmyhzfnjhducesctufqbumxbamalqudeibljgbspeotkgvddcwgxidaiqcvgwykhbysjzlzfbupkqunuqtraxrlptivshhbihtsigtpipguhbhctcvubnhqipncyxfjebdnjyetnlnvmuxhzsdahkrscewabejifmxombiamxvauuitoltyymsarqcuuoezcbqpdaprxmsrickwpgwpsoplhugbikbkotzrtqkscekkgwjycfnvwfgdzogjzjvpcvixnsqsxacfwndzvrwrycwxrcismdhqapoojegggkocyrdtkzmiekhxoppctytvphjynrhtcvxcobxbcjjivtfjiwmduhzjokkbctweqtigwfhzorjlkpuuliaipbtfldinyetoybvugevwvhhhweejogrghllsouipabfafcxnhukcbtmxzshoyyufjhzadhrelweszbfgwpkzlwxkogyogutscvuhcllphshivnoteztpxsaoaacgxyaztuixhunrowzljqfqrahosheukhahhbiaxqzfmmwcjxountkevsvpbzjnilwpoermxrtlfroqoclexxisrdhvfsindffslyekrzwzqkpeocilatftymodgztjgybtyheqgcpwogdcjlnlesefgvimwbxcbzvaibspdjnrpqtyeilkcspknyylbwndvkffmzuriilxagyerjptbgeqgebiaqnvdubrtxibhvakcyotkfonmseszhczapxdlauexehhaireihxsplgdgmxfvaevrbadbwjbdrkfbbjjkgcztkcbwagtcnrtqryuqixtzhaakjlurnumzyovawrcjiwabuwretmdamfkxrgqgcdgbrdbnugzecbgyxxdqmisaqcyjkqrntxqmdrczxbebemcblftxplafnyoxqimkhcykwamvdsxjezkpgdpvopddptdfbprjustquhlazkjfluxrzopqdstulybnqvyknrchbphcarknnhhovweaqawdyxsqsqahkepluypwrzjegqtdoxfgzdkydeoxvrfhxusrujnmjzqrrlxglcmkiykldbiasnhrjbjekystzilrwkzhontwmehrfsrzfaqrbbxncphbzuuxeteshyrveamjsfiaharkcqxefghgceeixkdgkuboupxnwhnfigpkwnqdvzlydpidcljmflbccarbiegsmweklwngvygbqpescpeichmfidgsjmkvkofvkuehsmkkbocgejoiqcnafvuokelwuqsgkyoekaroptuvekfvmtxtqshcwsztkrzwrpabqrrhnlerxjojemcxel"
["dhvf","sind","ffsl","yekr","zwzq","kpeo","cila","tfty","modg","ztjg","ybty","heqg","cpwo","gdcj","lnle","sefg","vimw","bxcb"]
    */
    public static void main(String[] args) {
        ArrayList<String> g=new ArrayList<>();
        g.add("good");
        g.add("best");
        g.add("word");
        g.add("good");
        System.out.println(subct(g,"wordgoodgoodgoodbestword"));
//        System.out.println(subct(g,"aaaaaaaa"));
    }
    public static ArrayList<Integer> subct(ArrayList<String> sal,String a)
    {
        ArrayList<Integer> ans=new ArrayList<>();
        ArrayList<String> gps=new ArrayList<>(makeGps(sal));
        System.out.println("gps= "+gps);
        if(sal.isEmpty())
            return ans;
        Map<String,Integer> mp=new HashMap<>();
        for (int i = 0; i < gps.size(); i++) {
            mp.put(gps.get(i),1);
        }
        int len=gps.get(0).length();
//        System.out.println("len= "+len);
//        System.out.println("mp = "+mp);
        for (int i = 0; i < a.length()-len+1;) {
            String f=a.substring(i,len+i);
            System.out.println("f= "+f);
            if(mp.containsKey(f.intern()))
            {
                ans.add(i);
//                i+=minLen;
            }
//            else
                i++;
        }
        return ans;
    }    
    public static ArrayList<String> makeGps(ArrayList<String> sal)
    {
//        System.out.println("sal= "+sal);
        ArrayList<ArrayList<String>> x=new ArrayList<>();
        x.add(new ArrayList<>());
        for (int i = 0; i < sal.size(); i++) {
//            System.out.println("current word= "+sal.get(i));
            ArrayList<ArrayList<String>> z=new ArrayList<>();
            for (int j = 0; j < x.size(); j++) {
                ArrayList<String> p=x.get(j);
//                System.out.println("p= "+p);
                for (int k = 0; k < p.size(); k++) {
                    ArrayList<String> v=new ArrayList<>(p);
                    v.add(k,sal.get(i));
                    z.add(new ArrayList<>(v));
                }
                ArrayList<String> v=new ArrayList<>(p);
                v.add(sal.get(i));
                z.add(new ArrayList<>(v));
//                System.out.println("z is "+z);
            }
            x=new ArrayList<>();
            x.addAll(z);
//            System.out.println("x is "+x);
        }
//        System.out.println("x= "+x);
        Set<String> st=new HashSet<>();
        for (int i = 0; i < x.size(); i++) {
            StringBuilder sba=new StringBuilder();
            for (int j = 0; j < x.get(i).size(); j++) {
                sba.append(x.get(i).get(j));
            }
            st.add(sba.toString());
        }
        ArrayList<String> ans=new ArrayList<>(st);
        return ans;
    }
}
