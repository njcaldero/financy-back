package com.financy.type;




public enum TipoMovimiento
{
    CONSIGNAR("Consignar",1l), RETIRAR("Retirar",2l);

    private String movimiento;
    private Long id;

    private TipoMovimiento (String movimiento, Long id){
        this.movimiento = movimiento;
        this.id = id;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public Long getId() {
        return id;
    }

}