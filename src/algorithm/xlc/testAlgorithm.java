package algorithm.xlc;

import algorithm.xlc.Algorithm.DTW;
import algorithm.xlc.Algorithm.KMP;
import algorithm.xlc.Algorithm.LCSS;
import algorithm.xlc.Algorithm.LRS;

public class testAlgorithm {

	public static void testLcss() {
		String[] A = {"1", "2", "3", "4",};
        String[] B = {"5", "1", "2", "1", "3", "5", "4",};
        LCSS lcss = new Algorithm().new LCSS();
        lcss.setFlagLcss(A, B);
        System.out.println(lcss.Lcs[A.length][B.length]);
        lcss.getLongestCommonSubsequence(lcss.Flag, A, A.length, B.length);
        System.out.println(lcss.LongestCommonSubsequence);
	}
	
	public static void testDtw() {
		double[] A = {4, 0, 1, 2, 3,};
		double[] B = {4, 1, 2, 3,};
		DTW dtw = new Algorithm().new DTW();
		double re = dtw.DistanceByDtw(A, B);
		System.out.println(re);
		
	}
	
    public static void testLrs() {
    	String[] A = {"a", "b", "c", "c", "d", "a", "b", "c", "d", };
    	LRS lrs = new Algorithm().new LRS();
    	String[] re = lrs.getLongestRepeatingSubstring(A);
    	for (String str: re) {
    		System.out.print(str + " ");
    	}
	}
    
    public static void testKmp() {
    	//                                |4                        |
    	String[] T = {"q", "a", "b", "e", "a", "b", "c", "a", "b", "b", "e", "p",};
		String[] P = {"a", "b", "c", "a", "b", "b",};
		KMP kmp = new Algorithm().new KMP();
		int re = kmp.isMatched(T, P);
		for (int i: kmp.Next) {
			System.out.print(i + " ");
		}
		System.out.println();
		System.out.println(re);
		
	}

	//LCSS DTW 最长重复子串（LRS） KMP
	public static void main(String[] args) {
//		testLcss();
//		testDtw();
//		testLrs();
//		testKmp();
	}
}
