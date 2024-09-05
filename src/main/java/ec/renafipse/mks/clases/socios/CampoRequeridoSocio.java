/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.clases.socios;

/**
 *
 * @author vicastoc
 */
public class CampoRequeridoSocio {

    private boolean cargasFamiliares;
    private boolean codigoEstadoCivil;
    private boolean codigoFuenteIngresos;
    private boolean codigoInstruccion;
    private boolean codigoPersona;
    private boolean codigoProfesion;
    private boolean codigoTipoInstitucion;
    private boolean codigoUbiGeoNac;
    private boolean esSujetoSri;
    private boolean exoneradoSri;
    private boolean fechaActualizacion;
    private boolean fechaConstitucion;
    private boolean fechaIngreso;
    private boolean fechaNacimiento;
    private boolean ingresos;
    private boolean nombreComercial;
    private boolean nombres;
    private boolean objetoSocial;
    private boolean observaciones;
    private boolean primerApellido;
    private boolean razonSocial;
    private boolean segundoApellido;
    private boolean sexo;
    private boolean correoElectronico;

    public CampoRequeridoSocio() {
        this.cargasFamiliares = true;
        this.codigoEstadoCivil = true;
        this.codigoFuenteIngresos = true;
        this.codigoInstruccion = true;
        this.codigoPersona = true;
        this.codigoProfesion = true;
        this.codigoTipoInstitucion = true;
        this.codigoUbiGeoNac = true;
        this.esSujetoSri = true;
        this.exoneradoSri = true;
        this.fechaActualizacion = true;
        this.fechaConstitucion = true;
        this.fechaIngreso = true;
        this.fechaNacimiento = true;
        this.ingresos = true;
        this.nombreComercial = true;
        this.nombres = true;
        this.objetoSocial = false;
        this.observaciones = true;
        this.primerApellido = true;
        this.razonSocial = true;
        this.segundoApellido = true;
        this.sexo = true;
        this.correoElectronico = false;
        
    }
    
    public void habilitaCamposRequeridosNatural()
    {
        this.cargasFamiliares = true;
        this.codigoEstadoCivil = true;
        this.codigoFuenteIngresos = true;
        this.codigoInstruccion = true;
        this.codigoPersona = true;
        this.codigoProfesion = true;
        this.codigoTipoInstitucion = false;
        this.codigoUbiGeoNac = true;
        this.esSujetoSri = false;
        this.exoneradoSri = true;
        this.fechaActualizacion = true;
        this.fechaConstitucion = false;
        this.fechaIngreso = true;
        this.fechaNacimiento = true;
        this.ingresos = true;
        this.nombreComercial = false;
        this.nombres = true;
        this.objetoSocial = false;
        this.observaciones = true;
        this.primerApellido = true;
        this.razonSocial = false;
        this.segundoApellido = true;
        this.sexo = true;
        this.correoElectronico = false;
    }
    
    public void habilitaCamposRequeridosInstitucion()
    {
        this.cargasFamiliares = false;
        this.codigoEstadoCivil = false;
        this.codigoFuenteIngresos = false;
        this.codigoInstruccion = false;
        this.codigoPersona = false;
        this.codigoProfesion = false;
        this.codigoTipoInstitucion = true;
        this.codigoUbiGeoNac = false;
        this.esSujetoSri = true;
        this.exoneradoSri = false;
        this.fechaActualizacion = true;
        this.fechaConstitucion = true;
        this.fechaIngreso = true;
        this.fechaNacimiento = false;
        this.ingresos = false;
        this.nombreComercial = true;
        this.nombres = false;
        this.objetoSocial = false;
        this.observaciones = true;
        this.primerApellido = false;
        this.razonSocial = false;
        this.segundoApellido = false;
        this.sexo = false;
        this.correoElectronico = false;
    }

    /**
     * @return the cargasFamiliares
     */
    public boolean isCargasFamiliares() {
        return cargasFamiliares;
    }

    /**
     * @param cargasFamiliares the cargasFamiliares to set
     */
    public void setCargasFamiliares(boolean cargasFamiliares) {
        this.cargasFamiliares = cargasFamiliares;
    }

    /**
     * @return the codigoEstadoCivil
     */
    public boolean isCodigoEstadoCivil() {
        return codigoEstadoCivil;
    }

    /**
     * @param codigoEstadoCivil the codigoEstadoCivil to set
     */
    public void setCodigoEstadoCivil(boolean codigoEstadoCivil) {
        this.codigoEstadoCivil = codigoEstadoCivil;
    }

    /**
     * @return the codigoFuenteIngresos
     */
    public boolean isCodigoFuenteIngresos() {
        return codigoFuenteIngresos;
    }

    /**
     * @param codigoFuenteIngresos the codigoFuenteIngresos to set
     */
    public void setCodigoFuenteIngresos(boolean codigoFuenteIngresos) {
        this.codigoFuenteIngresos = codigoFuenteIngresos;
    }

    /**
     * @return the codigoInstruccion
     */
    public boolean isCodigoInstruccion() {
        return codigoInstruccion;
    }

    /**
     * @param codigoInstruccion the codigoInstruccion to set
     */
    public void setCodigoInstruccion(boolean codigoInstruccion) {
        this.codigoInstruccion = codigoInstruccion;
    }

    /**
     * @return the codigoPersona
     */
    public boolean isCodigoPersona() {
        return codigoPersona;
    }

    /**
     * @param codigoPersona the codigoPersona to set
     */
    public void setCodigoPersona(boolean codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    /**
     * @return the codigoProfesion
     */
    public boolean isCodigoProfesion() {
        return codigoProfesion;
    }

    /**
     * @param codigoProfesion the codigoProfesion to set
     */
    public void setCodigoProfesion(boolean codigoProfesion) {
        this.codigoProfesion = codigoProfesion;
    }

    /**
     * @return the codigoTipoInstitucion
     */
    public boolean isCodigoTipoInstitucion() {
        return codigoTipoInstitucion;
    }

    /**
     * @param codigoTipoInstitucion the codigoTipoInstitucion to set
     */
    public void setCodigoTipoInstitucion(boolean codigoTipoInstitucion) {
        this.codigoTipoInstitucion = codigoTipoInstitucion;
    }

    /**
     * @return the codigoUbiGeoNac
     */
    public boolean isCodigoUbiGeoNac() {
        return codigoUbiGeoNac;
    }

    /**
     * @param codigoUbiGeoNac the codigoUbiGeoNac to set
     */
    public void setCodigoUbiGeoNac(boolean codigoUbiGeoNac) {
        this.codigoUbiGeoNac = codigoUbiGeoNac;
    }

    /**
     * @return the esSujetoSri
     */
    public boolean isEsSujetoSri() {
        return esSujetoSri;
    }

    /**
     * @param esSujetoSri the esSujetoSri to set
     */
    public void setEsSujetoSri(boolean esSujetoSri) {
        this.esSujetoSri = esSujetoSri;
    }

    /**
     * @return the exoneradoSri
     */
    public boolean isExoneradoSri() {
        return exoneradoSri;
    }

    /**
     * @param exoneradoSri the exoneradoSri to set
     */
    public void setExoneradoSri(boolean exoneradoSri) {
        this.exoneradoSri = exoneradoSri;
    }

    /**
     * @return the fechaActualizacion
     */
    public boolean isFechaActualizacion() {
        return fechaActualizacion;
    }

    /**
     * @param fechaActualizacion the fechaActualizacion to set
     */
    public void setFechaActualizacion(boolean fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    /**
     * @return the fechaConstitucion
     */
    public boolean isFechaConstitucion() {
        return fechaConstitucion;
    }

    /**
     * @param fechaConstitucion the fechaConstitucion to set
     */
    public void setFechaConstitucion(boolean fechaConstitucion) {
        this.fechaConstitucion = fechaConstitucion;
    }

    /**
     * @return the fechaIngreso
     */
    public boolean isFechaIngreso() {
        return fechaIngreso;
    }

    /**
     * @param fechaIngreso the fechaIngreso to set
     */
    public void setFechaIngreso(boolean fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    /**
     * @return the fechaNacimiento
     */
    public boolean isFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(boolean fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return the ingresos
     */
    public boolean isIngresos() {
        return ingresos;
    }

    /**
     * @param ingresos the ingresos to set
     */
    public void setIngresos(boolean ingresos) {
        this.ingresos = ingresos;
    }

    /**
     * @return the nombreComercial
     */
    public boolean isNombreComercial() {
        return nombreComercial;
    }

    /**
     * @param nombreComercial the nombreComercial to set
     */
    public void setNombreComercial(boolean nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    /**
     * @return the nombres
     */
    public boolean isNombres() {
        return nombres;
    }

    /**
     * @param nombres the nombres to set
     */
    public void setNombres(boolean nombres) {
        this.nombres = nombres;
    }

    /**
     * @return the objetoSocial
     */
    public boolean isObjetoSocial() {
        return objetoSocial;
    }

    /**
     * @param objetoSocial the objetoSocial to set
     */
    public void setObjetoSocial(boolean objetoSocial) {
        this.objetoSocial = objetoSocial;
    }

    /**
     * @return the observaciones
     */
    public boolean isObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(boolean observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * @return the primerApellido
     */
    public boolean isPrimerApellido() {
        return primerApellido;
    }

    /**
     * @param primerApellido the primerApellido to set
     */
    public void setPrimerApellido(boolean primerApellido) {
        this.primerApellido = primerApellido;
    }

    /**
     * @return the razonSocial
     */
    public boolean isRazonSocial() {
        return razonSocial;
    }

    /**
     * @param razonSocial the razonSocial to set
     */
    public void setRazonSocial(boolean razonSocial) {
        this.razonSocial = razonSocial;
    }

    /**
     * @return the segundoApellido
     */
    public boolean isSegundoApellido() {
        return segundoApellido;
    }

    /**
     * @param segundoApellido the segundoApellido to set
     */
    public void setSegundoApellido(boolean segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    /**
     * @return the sexo
     */
    public boolean isSexo() {
        return sexo;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }

    /**
     * @return the correoElectronico
     */
    public boolean isCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * @param correoElectronico the correoElectronico to set
     */
    public void setCorreoElectronico(boolean correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
}
