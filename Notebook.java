package Modelo;

public class Notebook extends Aparelho {
    private Integer memoriaRam;

    public Notebook() {
    }

    public Notebook(String tipo, String marca, String modelo,
                    String numeroSerie, Integer memoriaRam) {
        super(tipo, marca, modelo, numeroSerie);
        this.memoriaRam = memoriaRam;
    }

    public Integer getMemoriaRam() {
        return memoriaRam;
    }

    public void setMemoriaRam(Integer memoriaRam) {
        this.memoriaRam = memoriaRam;
    }

}
