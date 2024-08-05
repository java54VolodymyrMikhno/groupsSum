package telran.numbers.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import telran.numbers.*;
class GroupsSumTest {
private static final int N_GROUPS = 10000;
private static final int GROUP_LENGTH = 10000;
long startTime;
long endTime;

int[][] groups = {
		{1,2},
		{3,4},
		{5,6}
};
int[][] largeGroups = getLargeGroups(N_GROUPS, GROUP_LENGTH);
	@Test
	void goupsSumTaskThreadTest() {
		GroupsSum gs = new GroupsSumTaskThread(groups);
		assertEquals(21, gs.getSum());
	}
	private int[][] getLargeGroups(int nGroups, int groupLength) {
		return Stream.generate(() -> new Random().ints(groupLength).toArray())
				.limit(nGroups).toArray(int[][]::new);
	}

	@Test
	void groupsSumThreadPoolTest() {
		GroupsSum gs = new GroupsSumThreadPool(groups);
		assertEquals(21, gs.getSum());
	}
	@Test
	void groupsSumTaskThreadPerformance() {
		 startTime = System.currentTimeMillis();	
		new GroupsSumTaskThread(largeGroups).getSum();
		 endTime = System.currentTimeMillis();
		 System.out.println("GroupsSumTaskThread performance time: " + (endTime - startTime) + " ms");
	}
	@Test
	void groupsSumTaskThreadPoolsPerformance() {
		 startTime = System.currentTimeMillis();
		new GroupsSumThreadPool(largeGroups).getSum();
		 endTime = System.currentTimeMillis();
        System.out.println("GroupsSumThreadPool performance time: " + (endTime - startTime) + " ms");

	}

}