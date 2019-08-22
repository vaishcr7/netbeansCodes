public class LeetCodeMinCostForTickets {
	public static Map<Integer,Integer> mp=new HashMap<>();
	public static void main(String []args) {
	}
	public static int mincostTickets(int[] days, int[] costs) {
        return getRes(days, costs, 0, 0);
    }
	public static int getRes(int []days,int []costs,int index,int costTillNow) {
		if(mp.get(index)<costTillNow)
			return mp.get(index);
		if(index>days[days.length-1])
			return costTillNow;
		mp.put(index,
				Math.min(getRes(days, costs, index+30, costTillNow+days[2]), 
						Math.min(getRes(days, costs, index+7, costTillNow+days[1])
								, getRes(days, costs, index+1, costTillNow+days[0]))));
		return mp.get(index);
	}
}
