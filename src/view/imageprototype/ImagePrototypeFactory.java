package view.imageprototype;

import java.util.HashMap;

import model.GameManager;

public class ImagePrototypeFactory {
	
	private static HashMap<String, ChessPieceImageFile> imageFiles = new HashMap<String, ChessPieceImageFile>();
	
	public static void initialiseImages() {
		ChessPieceImageFile star = new ChessPieceImageFile("src/img/star.png");
		ColouredChessPieceImageFile blackBishop = new ColouredChessPieceImageFile("src/img/bishop_Black.png", GameManager.BLACK_PLAYER);
		ColouredChessPieceImageFile whiteBishop = new ColouredChessPieceImageFile("src/img/bishop_White.png", GameManager.WHITE_PLAYER);
		ColouredChessPieceImageFile blackKnight = new ColouredChessPieceImageFile("src/img/knight_Black.png", GameManager.BLACK_PLAYER);
		ColouredChessPieceImageFile whiteKnight = new ColouredChessPieceImageFile("src/img/knight_White.png", GameManager.WHITE_PLAYER);
		ColouredChessPieceImageFile blackRock = new ColouredChessPieceImageFile("src/img/rock_Black.png", GameManager.BLACK_PLAYER);
		ColouredChessPieceImageFile whiteRock = new ColouredChessPieceImageFile("src/img/rock_White.png", GameManager.WHITE_PLAYER);
		ColouredChessPieceImageFile whiteWarrior = new ColouredChessPieceImageFile("src/img/warrior_White.png", GameManager.WHITE_PLAYER);
		ColouredChessPieceImageFile blackWarrior = new ColouredChessPieceImageFile("src/img/warrior_Black.png", GameManager.WHITE_PLAYER);
		imageFiles.put(generateKey("star"), star);
		imageFiles.put(generateKey("bishop", GameManager.BLACK_PLAYER), blackBishop);
		imageFiles.put(generateKey("bishop", GameManager.WHITE_PLAYER), whiteBishop);
		imageFiles.put(generateKey("knight", GameManager.BLACK_PLAYER), blackKnight);
		imageFiles.put(generateKey("knight", GameManager.WHITE_PLAYER), whiteKnight);
		imageFiles.put(generateKey("rock", GameManager.BLACK_PLAYER), blackRock);
		imageFiles.put(generateKey("rock", GameManager.WHITE_PLAYER), whiteRock);
		imageFiles.put(generateKey("warrior", GameManager.BLACK_PLAYER), blackWarrior);
		imageFiles.put(generateKey("warrior", GameManager.WHITE_PLAYER), whiteWarrior);
	}
	
	public static ChessPieceImageFile getPieceImageFile(String name) {
		if (imageFiles.size() == 0) {
			initialiseImages();
		}
		try {
			return imageFiles.get(generateKey(name)).clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
	
	public static ChessPieceImageFile getPieceImageFile(String name, int colour) {
		if (imageFiles.size() == 0) {
			initialiseImages();
		}
		try {
		//	System.out.println(name); System.out.println(colour);
			return imageFiles.get(generateKey(name, colour)).clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
	
	private static String generateKey(String name) {
		return name;
	}
	
	private static String generateKey(String name, int colour) {
		return name + "_" + colour;
	}

}