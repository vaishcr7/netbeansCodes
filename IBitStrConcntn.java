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
//        g.add("aa");
//        g.add("aa");
//        g.add("aa");
//        g.add("aa");
//        System.out.println(subct(g,"aaaaaaaaaa"));
//         g.add("word");
//         g.add("good");
//         g.add("good");
//         g.add("best");
//        System.out.println(subct(g,"goodbestwordgoodgoodgoodbestword"));
         g.add("pk");
         g.add("at");
         g.add("pc");
         g.add("at");
         System.out.println(subct(g,"pkatpcatatatpcpkatatatpkpc"));
    }
    public static ArrayList<Integer> subct(ArrayList<String> sal,String a)
    {
        ArrayList<Integer> ans=new ArrayList<>();
        if(sal.isEmpty())
            return ans;
        Map<String,Integer> mainMp=new HashMap<>();
        for (int i = 0; i < sal.size(); i++) {
            if(!mainMp.containsKey(sal.get(i)))
                mainMp.put(sal.get(i),1);
            else
                mainMp.put(sal.get(i),mainMp.get(sal.get(i))+1);
        }
        int totLen=sal.get(0).length()*sal.size();
        int len=sal.get(0).length();
        int j=len-1;
        for (int i = 0; i < a.length()-totLen+1; ) {
            Map<String,Integer> tmpMp=new HashMap<>(mainMp);
            int k=i;
            String substr=a.substring(i,j+1);
            int curTotLen=0;
//            System.out.println("tmpMp= "+tmpMp+" and substr= "+substr+" and i= "+i+" and j= "+j);
            while(curTotLen<totLen && tmpMp.containsKey(substr.intern()) && tmpMp.get(substr.intern())>0)
            {
                curTotLen+=len;
                tmpMp.put(substr,tmpMp.get(substr)-1);
                k=j+1;
                j+=len;
                if(j>a.length()-1)
                {
//                    System.out.println("j exceeded");
                    break;
                }
                substr=a.substring(k,j+1);
//                System.out.println("new tmpMp= "+tmpMp+" and substr= "+substr+" and k= "+k+" and j= "+j+" and curent totlength: "+curTotLen);
            }
            if(curTotLen<totLen)
            {
                j=i+len;
                i+=1;
            }
            else if(curTotLen==totLen)
            {
                ans.add(i);
                i+=1;
                j=i+len-1;
            }
        }
        return ans;
    }  
}
