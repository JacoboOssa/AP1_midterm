package model;

import java.util.Collections;

/**
 * @author avillota
 * @since may 2022
 */
public class CrosswordController {
	
	/**
	 * Matrix of cells representing the crossword puzzle
	 */
	private Cell [][] crossword;
	
	/**
	 * method for initializing a crossword puzzle
	 * @param puzzle is a matrix of Strings containing 
	 * the initial state of a crossword puzzle
	 */
	public void initCrossword(String[][] puzzle) {
		int countCell = 0;
		crossword = new Cell [4][4];
		for (int i=0; i< puzzle.length; i++ ){
			for (int j = 0; j < puzzle[0].length; j++) {
			if (puzzle[i][j].equals("")){
				Cell c1 = new Cell(CellType.BLACK,"",0);
				crossword[i][j] = c1;
			 } else{
			 	Cell c2 = new Cell(CellType.CLOSED,puzzle[i][j],countCell);
			 	crossword[i][j] = c2;
			 	countCell ++;
			 }
				
			}
		}
	}

	/**
	 * Method to verify if a crossword puzzle is initialized
	 * @return boolean, true if it is initialized, else false
	 */
	public boolean isInitialized(){
		return crossword!=null;
	}
	
	/**
	 * Method to provide the dimensions of the crossword puzzle
	 * @return
	 */
	public int[] getGameDimensions() {
		int[] dims = new int[2];
		dims[0]= crossword.length;
		dims[1]= crossword[0].length;
		
		return dims;
	}
	
	public Cell[][] getCells(){
		return crossword;
	}
	/**
	 * 
	 * @param letter
	 * @return
	 */
	public String getHint(String letter) {
		String out = "";
		boolean flag = false;
		for (int i=0; i<crossword.length && !flag; i++) {
			for (int j=0; j< crossword[0].length && !flag; j++) {

				if(crossword[i][j].getState().equals(CellType.CLOSED) && letter.equals(crossword[i][j].getLetter())) {
					out = "Hay una palabra con la letra " + letter + " en la casilla [" + i +"][" + j + "]";
					crossword[i][j].setState(CellType.OPEN); 
					flag = true;
				} else {
					out = "No existen palabras con la letra " + letter + " en el crucigrama";
				}

			}
		}
		return out;
	}
	
	/**
	 * 
	 * @param letter
	 * @param num
	 * @return
	 */
	public String evaluateCell(String letter, int num) {
		
		return null;
	}
	
	public String showCrossword() {
		int rows= crossword.length;
		int columns= crossword[0].length;
	
		String out="";
		String separator = "+---+ ";
		String line = "" + String.join("", Collections.nCopies(columns, separator));
		
		
		String numbers ="";
		String letters = "";
		int count =0;
		for(int i=0 ;i<rows ; i++) {
			numbers ="";
			letters ="";
			for(int j=0 ;j<columns ; j++) {
				count++;
				Cell actual = crossword[i][j];
				
				// numeros de dos cifras
				if (count>10) {
					//empty cell
					if (actual.getState()==CellType.BLACK) {
						numbers += " ---  ";
						letters += " ---  ";
						
					}else {
						numbers += " "+actual.getNumber() +"   ";
						letters += "    "+ actual.getLetter() + " ";
					}
				}else //una cifra
				{
					//empty cell
					if (actual.getState()==CellType.BLACK) {
						numbers += " ---  ";
						letters += " ---  ";
						
					}else {
						numbers += " "+actual.getNumber() +"    ";
						letters += "    "+ actual.getLetter() + " ";
					}
				}
			}
			//por cada fila se imprimen las lineas
			out+= line + "\n";
			out+= numbers + "\n";
			out+= letters + "\n";
			
			
		}
		out+= line + "\n";
		return out;
	}


}
