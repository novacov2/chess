package Tests;

import static org.junit.Assert.*;

public class PiecesTest {

	public void checkMoves(boolean[][] output, boolean[][] expectedOutput) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				assertEquals(expectedOutput[i][j], output[i][j]);
			}
		}
	}
}
