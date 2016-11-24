package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

public class Ranking {
	private FileWriter rankFile;
	public class RankEle {
		public String name;
		public int score;
		public RankEle(String n, int s ) {
			name=n;
			score=s;
		}
	}
	private class MemberComparator implements Comparator<RankEle>{
		  public int compare(RankEle arg0, RankEle arg1) {
			   return (arg0.score > arg1.score) ? -1 : (arg0.score < arg1.score) ? 1:0;
		  }

	}
	private Vector<int[]> score;
	private Vector<RankEle> rankList;
	
	private int allTime;
	private int allMove;
	private int clearStage;
	private Ranking() {
		try {
			rankFile = new FileWriter("C:\\javaProject\\JavaModels\\ranking.txt", true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		score = new Vector<int[]>();
		rankList = new Vector<RankEle>();
	}

	private static class SingletonRank {
		private static final Ranking instance = new Ranking();
	}

	public static Ranking getInstance() {
		return SingletonRank.instance;
	}

	public void appendScore(int time, int move) {
		System.out.println("append : " + time);
		score.add(new int[] { time, move });
	}

	public void getScore() {
		for (int[] sc : score) {
			allTime += sc[0];
			allMove += sc[1];
			clearStage++;
		}
	}

	public void writeRank(String nickName) {
		// TODO Auto-generated method stub
		getScore();
		System.out.println("allTime:" + allTime);
		int finalScore = clearStage*500 - allMove*2 - allTime;  
		String input = nickName + "\t" + Integer.toString(finalScore) + "\n";
		try {
			rankFile.write(input);
			rankFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Vector getRankFile() {
		// TODO Auto-generated method stub
		try {
			BufferedReader in = new BufferedReader(new FileReader("C:\\javaProject\\JavaModels\\ranking.txt"));
			String s;
			while( (s = in.readLine() ) != null) {
				String[] line = s.split("\t");
				rankList.addElement(new RankEle(line[0], Integer.parseInt(line[1])));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 Collections.sort(rankList, new MemberComparator() );

		return rankList;
	}
}
