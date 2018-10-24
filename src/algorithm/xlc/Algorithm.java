package algorithm.xlc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Algorithm {

	public class LCSS{
		List<String> LongestCommonSubsequence;
		int[][] Lcs;
		int[][] Flag;
		public LCSS() {
			LongestCommonSubsequence = new ArrayList<String>();
		}
		
		public void setFlagLcss(String[] SequenceA, String[] SequenceB) {
			if (SequenceA == null || SequenceB == null || SequenceA.length == 0 || SequenceB.length == 0) {
		    	   System.exit(1);
		    }
			int ArrayLengthA = SequenceA.length;
			int ArrayLengthB = SequenceB.length;
			//记录匹配的最长子序列中元素的个数
			this.Lcs = new int[ArrayLengthA + 1][ArrayLengthB + 1];
			//1:ok 2:left 3:up
			this.Flag = new int[ArrayLengthA + 1][ArrayLengthB + 1];
		    for (int i = 0; i < ArrayLengthA; ++i) {
		        for (int j = 0; j < ArrayLengthB; ++j) {
		       	    if (SequenceA[i].equals(SequenceB[j])) {
		       		    Lcs[i + 1][j + 1] = Lcs[i][j] + 1;
		       		    Flag[i + 1][j + 1] = 1;
		       		}else if (Lcs[i + 1][j] > Lcs[i][j + 1]) {
		       			Lcs[i + 1][j + 1] = Lcs[i + 1][j];
		       			Flag[i + 1][j + 1] = 2;
		       		}else {
		       			Lcs[i + 1][j + 1] = Lcs[i][j + 1];
		       			Flag[i + 1][j + 1] = 3;
		       		}
		       	}
		    }
		}
		
		public void getLongestCommonSubsequence(int[][] Flag, String[] SequenceA, int LengthA, int LengthB) {
			if (Flag[LengthA][LengthB] == 1) {
				getLongestCommonSubsequence(Flag, SequenceA, LengthA - 1, LengthB - 1);
				this.LongestCommonSubsequence.add(SequenceA[LengthA - 1]);
			}else if (Flag[LengthA][LengthB] == 2) {
				getLongestCommonSubsequence(Flag, SequenceA, LengthA, LengthB - 1);
			}else if (Flag[LengthA][LengthB] == 3) {
				getLongestCommonSubsequence(Flag, SequenceA, LengthA - 1, LengthB);
			}
		}
	}
	
	public class DTW {
		
		double[][] Distance;
		double[][] SumDistance;
		
		public double DistanceByDtw(double[] SequenceA, double[] SequenceB) {
			if (SequenceA == null || SequenceB == null || SequenceA.length == 0 || SequenceB.length == 0) {
		    	   System.exit(1);
		    }
	    	int LengthA = SequenceA.length;
	    	int LengthB = SequenceB.length;
	    	this.Distance= new double[LengthA][LengthB];
	    	this.SumDistance = new double[LengthA][LengthB];
	    	for (int i = 0; i < LengthA; ++i) {
	    		for (int j = 0; j < LengthB; ++j) {
	    			this.Distance[i][j] = Math.abs(SequenceA[i] - SequenceB[j]);
	    			this.SumDistance[i][j] = 1000000.0;
	    		}
	    	}
	    	double DistanceTmp1 = 0.0;
	    	double DistanceTmp2 = 0.0;
			double DistanceTmp3 = 0.0;
	    	for (int i = 0; i < LengthA; ++i) {
	    		for (int j = 0; j < LengthB; ++j) {
	    			if (i == 0 && j == 0) {
	    				this.SumDistance[i][j] = this.Distance[0][0];
	    				DistanceTmp1 = 0.0;
	    				DistanceTmp2 = 0.0;
	    				DistanceTmp3 = 0.0;
	    			}
	    			if (i == 0 && j > 0) {
	    				DistanceTmp1 = 1000000.0;
	    				DistanceTmp2 = this.SumDistance[i][j - 1];
	    				DistanceTmp3 = 1000000.0;
	    			}
	    			if (i > 0 && j == 0) {
	    				DistanceTmp1 = this.SumDistance[i - 1][j];
	    				DistanceTmp2 = 1000000.0;
	    				DistanceTmp3 = 1000000.0;
	    			}
	    			if (i > 0 && j > 0) {
	    				DistanceTmp1 = this.SumDistance[i - 1][j];
	    				DistanceTmp2 = this.SumDistance[i][j - 1];
	    				DistanceTmp3 = this.SumDistance[i - 1][j - 1];
	    			}
	    			this.SumDistance[i][j] = this.Distance[i][j] + Math.min(DistanceTmp1, Math.min(DistanceTmp2, DistanceTmp3));
	    		}
	    	}
	    	return this.SumDistance[LengthA - 1][LengthB - 1];
	    }
	}
	
	public class LRS {
		
		public String[] getLongestRepeatingSubstring(String[] OriginalString) {
	    	if (OriginalString == null || OriginalString.length == 0) {
	    		System.exit(1);
	    	}
	    	int MaxLength = 0;
			int First = 0;
			int TempLength = 0;
			for (int i = 1; i < OriginalString.length; ++i) {
				for (int j = 0; j < OriginalString.length - i; ++j) {
					if (OriginalString[j].equals(OriginalString[i + j])) {
						TempLength++;
					} else {
						TempLength = 0;
					}
					if (TempLength > MaxLength) {
						MaxLength = TempLength;
						First = j - TempLength + 1;
					}
					if ((i + j + 1) == OriginalString.length) {
						TempLength = 0;
					}
				}
			}
			//大于1的时候返回，不然就是没有重复的串，返回原始串
			if (MaxLength > 1) {
				return Arrays.copyOfRange(OriginalString, First, First + MaxLength);
			}
			return OriginalString;
	    }
	}
	
	public class KMP {
		int[] Next;
		
		public void getNext(String[] StringP) {
		    this.Next = new int[StringP.length];
		    this.Next[0] = -1;
		    int PointerJ = 0;
		    int PointerK = -1;
		    while (PointerJ < StringP.length - 1) {
		       if (PointerK == -1 || StringP[PointerJ] == StringP[PointerK]) {
		           if (StringP[++PointerJ] == StringP[++PointerK]) { // 当两个字符相等时要跳过
		        	   this.Next[PointerJ] = this.Next[PointerK];
		           } else {
		        	   this.Next[PointerJ] = PointerK;
		           }
		       } else {
		    	   PointerK = this.Next[PointerK];
		       }
		    }
		}
		
		public int isMatched(String[] StringT, String[] StringP) {

		    int PointerI = 0; // 主串的位置
		    int PointerJ = 0; // 模式串的位置
		    getNext(StringP);
		    while (PointerI < StringT.length && PointerJ < StringP.length) {
		       if (PointerJ == -1 || StringT[PointerI] == StringP[PointerJ]) { // 当j为-1时，要移动的是i，当然j也要归0
		    	   PointerI++;
		    	   PointerJ++;
		       } else {
		    	   PointerJ = this.Next[PointerJ];
		       }
		    }
		    if (PointerJ == StringP.length) {
		       return PointerI - PointerJ;
		    } else {
		       return -1;
		    }
		}
	}

}
