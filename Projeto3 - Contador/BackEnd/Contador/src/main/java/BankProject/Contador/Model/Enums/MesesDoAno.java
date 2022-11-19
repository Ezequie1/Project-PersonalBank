package BankProject.Contador.Model.Enums;

public enum MesesDoAno {
    DEFAULT("Default"),
    JAN("Janeiro"),
    FEV("Fevereiro"),
    MAR("Marco"),
    ABR("Abril"),
    MAI("Maio"),
    JUN("Junho"),
    JUL("Julho"),
    AGO("Agosto"),
    SET("Setembro"),
    OUT( "Outubro"),
    NOV( "Novembro"),
    DEZ( "Dezembro");

    private MesesDoAno(String nomeMesCompleto){

        this.nomeMesCompleto = nomeMesCompleto;
    }
    private String nomeMesCompleto;

    public static String getNomeMes(int mes) {
        switch (mes){
            case 1:
                return MesesDoAno.JAN.nomeMesCompleto;
            case 2:
                return MesesDoAno.FEV.nomeMesCompleto;
            case 3:
                return MesesDoAno.MAR.nomeMesCompleto;
            case 4:
                return MesesDoAno.ABR.nomeMesCompleto;
            case 5:
                return MesesDoAno.MAI.nomeMesCompleto;
            case 6:
                return MesesDoAno.JUN.nomeMesCompleto;
            case 7:
                return MesesDoAno.JUL.nomeMesCompleto;
            case 8:
                return MesesDoAno.AGO.nomeMesCompleto;
            case 9:
                return MesesDoAno.SET.nomeMesCompleto;
            case 10:
                return MesesDoAno.OUT.nomeMesCompleto;
            case 11:
                return MesesDoAno.NOV.nomeMesCompleto;
            case 12:
                return MesesDoAno.DEZ.nomeMesCompleto;
            default:
                return null;
        }
    }


    public String getNomeMesCompleto(){
        return this.nomeMesCompleto;
    }
}
