import java.util.ArrayList;
public class AES
{
	private static int[][] sBox = 	
								{
									{0x63, 0x7c, 0x77, 0x7b, 0xf2, 0x6b, 0x6f, 0xc5, 0x30, 0x01, 0x67, 0x2b, 0xfe, 0xd7, 0xab, 0x76},
									{0xca, 0x82, 0xc9, 0x7d, 0xfa, 0x59, 0x47, 0xf0, 0xad, 0xd4, 0xa2, 0xaf, 0x9c, 0xa4, 0x72, 0xc0},
									{0xb7, 0xfd, 0x93, 0x26, 0x36, 0x3f, 0xf7, 0xcc, 0x34, 0xa5, 0xe5, 0xf1, 0x71, 0xd8, 0x31, 0x15},
									{0x04, 0xc7, 0x23, 0xc3, 0x18, 0x96, 0x05, 0x9a, 0x07, 0x12, 0x80, 0xe2, 0xeb, 0x27, 0xb2, 0x75},
									{0x09, 0x83, 0x2c, 0x1a, 0x1b, 0x6e, 0x5a, 0xa0, 0x52, 0x3b, 0xd6, 0xb3, 0x29, 0xe3, 0x2f, 0x84},
									{0x53, 0xd1, 0x00, 0xed, 0x20, 0xfc, 0xb1, 0x5b, 0x6a, 0xcb, 0xbe, 0x39, 0x4a, 0x4c, 0x58, 0xcf},
									{0xd0, 0xef, 0xaa, 0xfb, 0x43, 0x4d, 0x33, 0x85, 0x45, 0xf9, 0x02, 0x7f, 0x50, 0x3c, 0x9f, 0xa8},
									{0x51, 0xa3, 0x40, 0x8f, 0x92, 0x9d, 0x38, 0xf5, 0xbc, 0xb6, 0xda, 0x21, 0x10, 0xff, 0xf3, 0xd2},
									{0xcd, 0x0c, 0x13, 0xec, 0x5f, 0x97, 0x44, 0x17, 0xc4, 0xa7, 0x7e, 0x3d, 0x64, 0x5d, 0x19, 0x73},
									{0x60, 0x81, 0x4f, 0xdc, 0x22, 0x2a, 0x90, 0x88, 0x46, 0xee, 0xb8, 0x14, 0xde, 0x5e, 0x0b, 0xdb},
									{0xe0, 0x32, 0x3a, 0x0a, 0x49, 0x06, 0x24, 0x5c, 0xc2, 0xd3, 0xac, 0x62, 0x91, 0x95, 0xe4, 0x79},
									{0xe7, 0xc8, 0x37, 0x6d, 0x8d, 0xd5, 0x4e, 0xa9, 0x6c, 0x56, 0xf4, 0xea, 0x65, 0x7a, 0xae, 0x08},
									{0xba, 0x78, 0x25, 0x2e, 0x1c, 0xa6, 0xb4, 0xc6, 0xe8, 0xdd, 0x74, 0x1f, 0x4b, 0xbd, 0x8b, 0x8a},
									{0x70, 0x3e, 0xb5, 0x66, 0x48, 0x03, 0xf6, 0x0e, 0x61, 0x35, 0x57, 0xb9, 0x86, 0xc1, 0x1d, 0x9e},
									{0xe1, 0xf8, 0x98, 0x11, 0x69, 0xd9, 0x8e, 0x94, 0x9b, 0x1e, 0x87, 0xe9, 0xce, 0x55, 0x28, 0xdf},
									{0x8c, 0xa1, 0x89, 0x0d, 0xbf, 0xe6, 0x42, 0x68, 0x41, 0x99, 0x2d, 0x0f, 0xb0, 0x54, 0xbb, 0x16},
								};

	private static int[][] invSBox = 	
								{
									{0x52, 0x09, 0x6a, 0xd5, 0x30, 0x36, 0xa5, 0x38, 0xbf, 0x40, 0xa3, 0x9e, 0x81, 0xf3, 0xd7, 0xfb},
									{0x7c, 0xe3, 0x39, 0x82, 0x9b, 0x2f, 0xff, 0x87, 0x34, 0x8e, 0x43, 0x44, 0xc4, 0xde, 0xe9, 0xcb},
									{0x54, 0x7b, 0x94, 0x32, 0xa6, 0xc2, 0x23, 0x3d, 0xee, 0x4c, 0x95, 0x0b, 0x42, 0xfa, 0xc3, 0x4e},
									{0x08, 0x2e, 0xa1, 0x66, 0x28, 0xd9, 0x24, 0xb2, 0x76, 0x5b, 0xa2, 0x49, 0x6d, 0x8b, 0xd1, 0x25},
									{0x72, 0xf8, 0xf6, 0x64, 0x86, 0x68, 0x98, 0x16, 0xd4, 0xa4, 0x5c, 0xcc, 0x5d, 0x65, 0xb6, 0x92},
									{0x6c, 0x70, 0x48, 0x50, 0xfd, 0xed, 0xb9, 0xda, 0x5e, 0x15, 0x46, 0x57, 0xa7, 0x8d, 0x9d, 0x84},
									{0x90, 0xd8, 0xab, 0x00, 0x8c, 0xbc, 0xd3, 0x0a, 0xf7, 0xe4, 0x58, 0x05, 0xb8, 0xb3, 0x45, 0x06},
									{0xd0, 0x2c, 0x1e, 0x8f, 0xca, 0x3f, 0x0f, 0x02, 0xc1, 0xaf, 0xbd, 0x03, 0x01, 0x13, 0x8a, 0x6b},
									{0x3a, 0x91, 0x11, 0x41, 0x4f, 0x67, 0xdc, 0xea, 0x97, 0xf2, 0xcf, 0xce, 0xf0, 0xb4, 0xe6, 0x73},
									{0x96, 0xac, 0x74, 0x22, 0xe7, 0xad, 0x35, 0x85, 0xe2, 0xf9, 0x37, 0xe8, 0x1c, 0x75, 0xdf, 0x6e},
									{0x47, 0xf1, 0x1a, 0x71, 0x1d, 0x29, 0xc5, 0x89, 0x6f, 0xb7, 0x62, 0x0e, 0xaa, 0x18, 0xbe, 0x1b},
									{0xfc, 0x56, 0x3e, 0x4b, 0xc6, 0xd2, 0x79, 0x20, 0x9a, 0xdb, 0xc0, 0xfe, 0x78, 0xcd, 0x5a, 0xf4},
									{0x1f, 0xdd, 0xa8, 0x33, 0x88, 0x07, 0xc7, 0x31, 0xb1, 0x12, 0x10, 0x59, 0x27, 0x80, 0xec, 0x5f},
									{0x60, 0x51, 0x7f, 0xa9, 0x19, 0xb5, 0x4a, 0x0d, 0x2d, 0xe5, 0x7a, 0x9f, 0x93, 0xc9, 0x9c, 0xef},
									{0xa0, 0xe0, 0x3b, 0x4d, 0xae, 0x2a, 0xf5, 0xb0, 0xc8, 0xeb, 0xbb, 0x3c, 0x83, 0x53, 0x99, 0x61},
									{0x17, 0x2b, 0x04, 0x7e, 0xba, 0x77, 0xd6, 0x26, 0xe1, 0x69, 0x14, 0x63, 0x55, 0x21, 0x0c, 0x7d}
								};

	private static int[][] rCon=
						{	
							{1,0,0,0},
							{2,0,0,0},
							{4,0,0,0},
							{8,0,0,0},
							{16,0,0,0},
							{32,0,0,0},
							{64,0,0,0},
							{128,0,0,0},
							{27,0,0,0},
							{54,0,0,0},
						};


	/*private char[][] key = 	{
								{'S', ' ', ' ', ' '}, 
								{'O', '1', 'B', 'K'},
								{'M', '2', 'I', 'E'}, 
								{'E', '8', 'T', 'Y'}
							};*/

	/*private char[][] input = {
								{'A', 'C', 'T', 'W'}, 
								{'T', 'K', ' ', 'N'},
								{'T', ' ', 'D', '!'}, 
								{'A', 'A', 'A', '1'}
							};*/



		

	/*********************************************************************
	 *********************************************************************
	 Encrypt performs the encryption of a given input based on a given key
	 and the 10 round expansion of that key.
	 *********************************************************************
	 *********************************************************************/

	public static int[][] encrypt(int[][] in, ArrayList<int[][]> keyShedule)
	{
		int[][] preFirstRountState = xor(in, keyShedule.get(0));
		for (int i = 0; i<9; i++) {
			preFirstRountState = subBytes(preFirstRountState);
			preFirstRountState = shiftRows(preFirstRountState);
			preFirstRountState = mixColumns(preFirstRountState);
			preFirstRountState = xor(preFirstRountState, keyShedule.get(i+1));
		}
		preFirstRountState = subBytes(preFirstRountState);
		preFirstRountState = shiftRows(preFirstRountState);
		preFirstRountState = xor(preFirstRountState, keyShedule.get(10));

		return preFirstRountState;
	}

	/*********************************************************************
	 *********************************************************************
	 Decrypt performs the decryption of a given input based on a given key
	 and the 10 round expansion of that key.
	 *********************************************************************
	 *********************************************************************/

	public static int[][] decrypt(int[][] in, ArrayList<int[][]> keyShedule)
	{
		int[][] encryptedData = xor(in, keyShedule.get(10));
		for (int i = 9; i>0; i--) {
			encryptedData = invShiftRows(encryptedData);
			encryptedData = invSubBytes(encryptedData);
			encryptedData = xor(encryptedData, keyShedule.get(i));
			encryptedData = invMixColumns(encryptedData);
		}

		encryptedData = invShiftRows(encryptedData);
		encryptedData = invSubBytes(encryptedData);
		encryptedData = xor(encryptedData, keyShedule.get(0));

		return encryptedData;
	}
/*
	public int[][] AES1(char[][] in){
		int[][] preFirstRountState = xor(in, keyShedule.get(0));
		for (int i = 0; i<9; i++) {
			preFirstRountState = shiftRows(preFirstRountState);
			preFirstRountState = mixColumns(preFirstRountState);
			preFirstRountState = xor(preFirstRountState, keyShedule.get(i+1));
		}
		preFirstRountState = shiftRows(preFirstRountState);
		preFirstRountState = xor(preFirstRountState, keyShedule.get(10));

		return preFirstRountState;
	}

	public int[][] AES2(char[][] in){
		int[][] preFirstRountState = xor(in, keyShedule.get(0));
		for (int i = 0; i<9; i++) {
			preFirstRountState = subBytes(preFirstRountState);
			preFirstRountState = mixColumns(preFirstRountState);
			preFirstRountState = xor(preFirstRountState, keyShedule.get(i+1));
		}
		preFirstRountState = subBytes(preFirstRountState);
		preFirstRountState = xor(preFirstRountState, keyShedule.get(10));

		return preFirstRountState;
	}

	public int[][] AES3(char[][] in){
		int[][] preFirstRountState = xor(in, keyShedule.get(0));
		for (int i = 0; i<9; i++) {
			preFirstRountState = subBytes(preFirstRountState);
			preFirstRountState = shiftRows(preFirstRountState);
			preFirstRountState = xor(preFirstRountState, keyShedule.get(i+1));
		}
		preFirstRountState = subBytes(preFirstRountState);
		preFirstRountState = shiftRows(preFirstRountState);
		preFirstRountState = xor(preFirstRountState, keyShedule.get(10));

		return preFirstRountState;
	}

	public int[][] AES4(char[][] in){
		int[][] preFirstRountState = new int[4][4];
		for (int i = 0; i<4; i++) {
			for (int j = 0; j<4; j++) {
				preFirstRountState[i][j] = in[i][j];
			}
		}
		for (int i = 0; i<9; i++) {
			preFirstRountState = subBytes(preFirstRountState);
			preFirstRountState = shiftRows(preFirstRountState);
			preFirstRountState = mixColumns(preFirstRountState);
		}
		preFirstRountState = subBytes(preFirstRountState);
		preFirstRountState = shiftRows(preFirstRountState);

		return preFirstRountState;
	} */

	/*********************************************************************
	 *********************************************************************
	 mixColumns performs a matrix multiplication on a 2-d 16 byte array
	 to perform the mix columns step. invMixColumns undoes mixColumns
	 *********************************************************************
	 *********************************************************************/

	public static int[][] mixColumns(int[][] stateIn)
	{
		int[][] actualOutput = new int[4][4];
		int[][] output = new int[4][4];
		for(int i = 0; i<4; i++) {
			for (int j = 0; j<4; j++) {
				output[i][j] = stateIn[i][j];
			}
		}

		for(int i = 0; i<4; i++) {
				actualOutput[0][i] = (peasantsAlgorithm(2, output[0][i])^peasantsAlgorithm(3, output[1][i])^output[2][i]^output[3][i]);
				actualOutput[1][i] = (output[0][i]^peasantsAlgorithm(2, output[1][i])^peasantsAlgorithm(3, output[2][i])^output[3][i]);
				actualOutput[2][i] = (output[0][i]^output[1][i]^peasantsAlgorithm(2, output[2][i])^peasantsAlgorithm(3, output[3][i]));
				actualOutput[3][i] = (peasantsAlgorithm(3, output[0][i])^output[1][i]^output[2][i]^peasantsAlgorithm(2, output[3][i]));
		}
		return actualOutput;
	}

	public static int[][] invMixColumns(int[][] stateIn)
	{
		int[][] actualOutput = new int[4][4];
		int[][] output = new int[4][4];
		for(int i = 0; i<4; i++) 
		{
			for (int j = 0; j<4; j++) 
			{
				output[i][j] = stateIn[i][j];
			}
		}

		for(int i = 0; i<4; i++) {
				actualOutput[0][i] = (peasantsAlgorithm(0x0e, output[0][i])^peasantsAlgorithm(0x0b, output[1][i])^peasantsAlgorithm(0x0d, output[2][i])^peasantsAlgorithm(0x09, output[3][i]));
				actualOutput[1][i] = (peasantsAlgorithm(0x09, output[0][i])^peasantsAlgorithm(0x0e, output[1][i])^peasantsAlgorithm(0x0b, output[2][i])^peasantsAlgorithm(0x0d, output[3][i]));
				actualOutput[2][i] = (peasantsAlgorithm(0x0d, output[0][i])^peasantsAlgorithm(0x09, output[1][i])^peasantsAlgorithm(0x0e, output[2][i])^peasantsAlgorithm(0x0b, output[3][i]));
				actualOutput[3][i] = (peasantsAlgorithm(0x0b, output[0][i])^peasantsAlgorithm(0x0d, output[1][i])^peasantsAlgorithm(0x09, output[2][i])^peasantsAlgorithm(0x0e, output[3][i]));
		}
		return actualOutput;
	}

	/*********************************************************************
	 *********************************************************************
	 This is the function for GF(2^8) multiplication, used for mixColumns.
	 Includes rightMostBitSet and leftMostBitSet functions.
	 *********************************************************************
	 *********************************************************************/

	public static int peasantsAlgorithm(int aIn, int bIn)
	{
		int p = 0, a = aIn, b = bIn;
		for(int i = 0; i<8; i++)
		{
			if(rightMostBitSet(b))
			{
				p ^= a;
			}

			b = b >> 1;

			boolean carry = leftMostBitSet(a);

			a = a << 1;

			if(carry)
			{
				a ^= 0x1b;
			}

			if(a == 0 || b == 0)
			{
				break;
			}
		}
		return p & 0xFF;
	}

	// Takes a byte as input
	// returns true if right-most bit is 1
	// returns false otherwise
	public static boolean rightMostBitSet(int byteIn)
	{
		int bit = (byteIn & 1);
		return (bit == 1) ? true : false;
	}

	// Takes a byte as input
	// returns true if left-most bit is 1
	// returns false otherwise
	public static boolean leftMostBitSet(int byteIn)
	{
		int bit = (byteIn & 0x80);
		return (bit == 0x80) ? true : false;
	}

	/*********************************************************************
	 *********************************************************************
	 This is the xor function, takes a 4x4 matrix of values, xors them
	 with a given key and returns the result, it's the first step of each
	 round. The one for first round uses the plaintext key. xorWord xors
	 2 4 byte arrays.
	 *********************************************************************
	 *********************************************************************/

	public static int[][] xor(int[][] currentRoundInputIn, int[][] keyIn)
	{
		int[][] current = currentRoundInputIn;
		int[][] output = new int[4][4];
		for(int i = 0; i<4; i++)
		{
			for(int j = 0; j<4; j++)
			{
				output[i][j] = current[i][j]^keyIn[i][j];
			}
		}
		return output;
	}

	/*public int[][] xorFirstRound(char[][] currentRoundInputIn)
	{
		char[][] current = currentRoundInputIn;
		int[][] output = new int[4][4];
		for(int i = 0; i<4; i++)
		{
			for(int j = 0; j<4; j++)
			{
				output[i][j] = current[i][j]^key[i][j];
			}
		}
		return output;
	}*/

	public static int[] wordXor(int[] wordIn, int[]xoredWith)
	{
		int[] output = new int[4];
		for(int j = 0; j<4; j++)
		{
			output[j] = wordIn[j]^xoredWith[j];
		}
		return output;
	}

	/*********************************************************************
	 *********************************************************************
	 Key expansion method creates 10 keys based on the input key and
	 stores them in the keyShedule arraylist.
	 *********************************************************************
	 *********************************************************************/

	public static ArrayList<int[][]> keyExpansion(int[][] key)
	{
		ArrayList<int[][]> keyShedule = new ArrayList<int[][]>();
		keyShedule.add(key);
		for(int j = 0; j < 10; j++){
			int[] newFirstWord = new int[4];
			int[] newSecondWord = new int[4];
			int[] newThirdWord = new int[4];
			int[] newFourthWord = new int[4];
			int[] oldFourthWord = new int[4];
			int[] oldFirstWord = new int[4];
			int[] oldSecondWord = new int[4];
			int[] oldThirdWord = new int[4];
			for(int i = 0; i<4; i++)
			{
				oldFirstWord[i] = keyShedule.get(j)[i][0];
				oldSecondWord[i] = keyShedule.get(j)[i][1];
				oldThirdWord[i] = keyShedule.get(j)[i][2];
				oldFourthWord[i] = keyShedule.get(j)[i][3];
			}
			newFirstWord = rotWord(oldFourthWord);
			newFirstWord = subWord(newFirstWord);
			newFirstWord = wordXor(newFirstWord, rCon[j]);
			newFirstWord = wordXor(newFirstWord, oldFirstWord);
			newSecondWord = wordXor(oldSecondWord, newFirstWord);
			newThirdWord = wordXor(oldThirdWord, newSecondWord);
			newFourthWord = wordXor(oldFourthWord, newThirdWord);
			int[][] output = new int[4][4];
			for (int k = 0; k<4; k++) {
				output[k][0] = newFirstWord[k];
				output[k][1] = newSecondWord[k];
				output[k][2] = newThirdWord[k];
				output[k][3] = newFourthWord[k];
			}

			keyShedule.add(output);
		}
		return keyShedule;
	}

	/*********************************************************************
	 *********************************************************************
	 subByte takes a byte of data and substitutes it for a value in the 
	 sBox, subWord uses this function for a 4 byte array and subBytes uses
	 this function for 2-D 16 byte array.
	 *********************************************************************
	 *********************************************************************/

	public static int subByte(int byteIn)
	{
		String byteToHex = String.format("%02X", byteIn);
		int r = (Integer.decode("0x0" + byteToHex.charAt(0)));
		int c = (Integer.decode("0x0" + byteToHex.charAt(1)));
		return sBox[r][c];
	}

	// Each word == 4 bytes
	// 
	public static int[] subWord(int[] wordIn)
	{
		int[] wordOut = new int[4];
		// for each byte
		for(int i = 0; i < 4; i++)
		{
			// copy into wordOut
			wordOut[i] = wordIn[i];
			// 
			wordOut[i] = subByte(wordOut[i]);
		}
		return wordOut;
	}

	public static int[][] subBytes(int[][] bytesIn){
		int[][] bytesOut = new int[4][4];
		for(int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				bytesOut[i][j] = subByte(bytesIn[i][j]);
			}
		}
		return bytesOut;
	}

	public static int invSubByte(int byteIn)
	{
		String byteToHex = String.format("%02X", byteIn);
		int r = (Integer.decode("0x0" + byteToHex.charAt(0)));
		int c = (Integer.decode("0x0" + byteToHex.charAt(1)));
		return invSBox[r][c];
	}

	public static int[] invSubWord(int[] wordIn)
	{
		int[] wordOut = new int[4];
		for(int i = 0; i < 4; i++)
		{
			wordOut[i] = wordIn[i];
			wordOut[i] = invSubByte(wordOut[i]);
		}
		return wordOut;
	}

	public static int[][] invSubBytes(int[][] bytesIn){
		int[][] bytesOut = new int[4][4];
		for(int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				bytesOut[i][j] = invSubByte(bytesIn[i][j]);
			}
		}
		return bytesOut;
	}

	/*********************************************************************
	 *********************************************************************
	 Rotword takes a 4 byte array and puts the first byte on the end.
	 Rotwordright takes a 4 byte array and puts the last byte on the front.
	 *********************************************************************
	 *********************************************************************/

	public static int[] rotWord(int[] wordIn)
	{
		int[] wordOut = new int[4];
		int temp = wordIn[0];
		for(int i = 0; i < 4; i++)
		{
			wordOut[i] = wordIn[i];
		}
		for(int i = 0;i<3;i++)
		{
			wordOut[i] = wordOut[i+1];
		}
		wordOut[3] = temp;
		return wordOut;
	}

	public static int[] rotWordRight(int[] wordIn)
	{
		int[] wordOut = new int[4];
		int temp = wordIn[3];
		for(int i = 0; i < 4; i++)
		{
			wordOut[i] = wordIn[i];
		}
		for(int i = 3;i>0;i--)
		{
			wordOut[i] = wordOut[i-1];
		}
		wordOut[0] = temp;
		return wordOut;
	}

	/*********************************************************************
	 *********************************************************************
	 Shift rows performs a single rot word on the second row of a 2-d
	 matrix, 2 rotwords on the third row of the matrix and 3 rotwords on 
	 the fourth row of the matrix. Inverse shift rows does the same thing
	 but using rotwordright instead.
	 *********************************************************************
	 *********************************************************************/

	public static int[][] shiftRows(int[][] stateIn)
	{
		int[][] output = new int[4][4];
		for (int i = 0; i<4; i++) {
			output[0][i] = stateIn[0][i];
		}
		// first row is untouched
		// row 2 shift by 1
		output[1] = rotWord(stateIn[1]);
		// row 3 shift by 2
		output[2] = rotWord(stateIn[2]);
		output[2] = rotWord(output[2]);
		//row 4 shift by 3
		output[3] = rotWord(stateIn[3]);
		output[3] = rotWord(output[3]);
		output[3] = rotWord(output[3]);
		return output;
	}

	public static int[][] invShiftRows(int[][] stateIn)
	{
		int[][] output = new int[4][4];
		for (int i = 0; i<4; i++) {
			output[0][i] = stateIn[0][i];
		}
		// first row is untouched
		// row 2 shift by 1
		output[1] = rotWordRight(stateIn[1]);
		// row 3 shift by 2
		output[2] = rotWordRight(stateIn[2]);
		output[2] = rotWordRight(output[2]);
		//row 4 shift by 3
		output[3] = rotWordRight(stateIn[3]);
		output[3] = rotWordRight(output[3]);
		output[3] = rotWordRight(output[3]);
		return output;
	}

	/*
		Input: 128 bit String
		Output: ArrayList of type int[][] which
	*/
	public static ArrayList<int[][]> avFlipBit(String input)
	{
		ArrayList<int[][]> returnList = new ArrayList<int[][]>();
		// for 128 bits
		String copyString = "";
		if(input.charAt(0) == '0')
		{
			copyString = "1";
			copyString+=input.substring(1);
		}
		else
		{
			copyString="0";
			copyString+=input.substring(1);	
		}
		returnList.add(stringToArray(copyString));
		for(int i=1;i<127;i++)
		{
			// change the bit at i
			if(input.charAt(i) == '0')
			{
				copyString = input.substring(0,i);
				copyString+="1";
				copyString+= input.substring(i+1);
			}
			else
			{
				copyString = input.substring(0,i);
				copyString+="0";
				copyString+= input.substring(i+1);
			}
			System.out.println(copyString);
			returnList.add(stringToArray(copyString));
		}
		if(input.charAt(127) == '0')
		{
			copyString = input.substring(0,127);
			copyString+="1";
		}
		else
		{
			copyString = input.substring(0,127);
			copyString+="0";
		}
		return returnList;
	}

	// Takes a 128 bit string, turns it into a 4x4 array of
	// hexadecimals
	public static int[][] stringToArray(String input)
	{
		int[][] returnMe = new int[4][4];
		String origInput = input;
		String word = "";
		int l = 0; int m = 0;
		// returnMe[0][1] - row 1 column 0
		for (int i = 0; i<16; i++) 
		{
			word = "";
			for (int j = 0; j<8; j++) 
			{
				word += origInput.charAt(0);
				origInput = origInput.substring(1);
			}
			returnMe[l][m] = Integer.parseInt(word, 2);
			if((i+1)%4 == 0 && i != 0)
			{
				m++;
				l = 0;
			}
			else
			{
				l++;
			}
		}
		return returnMe;
	}
}
/*
if((i+1)%4 == 0 && i != 0)
			{
				m++;
				l = 0;
			}
			else
			{
				l++;
			}
*/