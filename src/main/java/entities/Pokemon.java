package entities;

public class Pokemon {

	private String name = "";
	
	private String type = "";
	
	private String move = "";
	
	private int pokedexNumber = 0;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMove() {
		return move;
	}
	public void setMove(String move) {
		this.move = move;
	}
	public int getPokedexNumber() {
		return pokedexNumber;
	}
	public void setPokedexNumber(int pokedexNumber) {
		this.pokedexNumber = pokedexNumber;
	}
	
}
