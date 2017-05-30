package com.psicovirtual.liquidadorAdminTotal.vista.mb;

import java.math.BigDecimal;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.psicovirtual.estandar.vista.mb.MBMensajes;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNTipoUsuario;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNUsuarios;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Tema;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Usuario;

@ManagedBean(name = "MBUsuarios")
@SessionScoped
public class MBUsuarios {
	MBMensajes mensajes = new MBMensajes();

	DNUsuarios dNUsuario;
	Usuario objUsuario;
	String passConfirmar;
	DNTipoUsuario dNTipoUsuario;
	private String nombre;
	private String apellido;
	private String usuario;
	private String contrasena;

	private String repetirContrasena;
	private Date fechaNacimiento;
	private String correo;
	private String direccion;
	private String telefono;
	private String celular;
	private String descripcionPerfil;

	public MBUsuarios() {
		objUsuario = new Usuario();
	}

	public void guardarUsuario() throws Exception {
		if (dNUsuario == null) {
			dNUsuario = new DNUsuarios();
		}
		
		if (dNTipoUsuario == null) {
			dNTipoUsuario = new DNTipoUsuario();
		}
		Usuario insert = new Usuario();
		insert.setNombre(nombre);
		insert.setApellidos(apellido);
		insert.setCelular(celular);
		insert.setDescripcionPerfil(descripcionPerfil);
		insert.setEmail(correo);
		insert.setEstado("ACTIVO");
		insert.setFechaNac(fechaNacimiento);
		insert.setFoto("");
		insert.setPassword(contrasena);
		insert.setTelefono(telefono);
		insert.setUsuario(usuario);
		insert.setTipoUsuario(dNTipoUsuario.buscarTipoUsuario(new BigDecimal("1")));
		
		
		
		
		dNUsuario.crearUsuario(insert);

		mensajes.mostrarMensaje("Registro Exitoso", 1);
	
		limpiarUsuario();
		
		
	}

	public void limpiarUsuario() {
		  nombre="";
		  apellido=null;
		  usuario=null;
		  contrasena=null;

		  repetirContrasena=null;
		  fechaNacimiento=null;
		  correo=null;
		  direccion=null;
		  telefono=null;
		  celular=null;
		  descripcionPerfil=null;
		  System.out.println("pasa limpiar");
	}

	public void iniciarSesion() throws Exception {
		if (dNUsuario == null) {
			dNUsuario = new DNUsuarios();
		}

		if (dNUsuario.validarInicioSesion(objUsuario) == 1) {
			try {
				FacesContext context = FacesContext.getCurrentInstance();
				ExternalContext extContext = context.getExternalContext();
				String url2 = extContext.encodeActionURL(context.getApplication().getViewHandler().getActionURL(context,
						"/view/Usuarios/DatosPersonales.xhtml"));
				extContext.redirect(url2);
			} catch (Exception e) {
				System.out.println("Error en el metodo iniciarSesion: " + e);
			}
		}
	}

	public void insertarNuevoUsuario() throws Exception {
		if (dNUsuario == null) {
			dNUsuario = new DNUsuarios();
		}

		Usuario usuarioInsertar = new Usuario();
		usuarioInsertar = objUsuario;
		usuarioInsertar.setEstado("ACTIVO");

		if (usuarioInsertar.getPassword().equals(passConfirmar)) {
			if (dNUsuario.consultarUsuarioRepetido(usuarioInsertar) == 1) {
				System.out.println("El usuario ya se encuentra");
			} else {
				if (dNUsuario.crearUsuario(usuarioInsertar) != null) {
					objUsuario = new Usuario();
				}
			}
		} else {
			System.out.println("Contraseña incorrecta");
		}
	}

	public void actualizarDatosPersonalesUsuario() throws Exception {

		if (dNUsuario == null) {
			dNUsuario = new DNUsuarios();
		}

		Usuario usuarioTemp = dNUsuario.consultarDetalleUsuarioByUsuario(objUsuario.getUsuario());

		if (usuarioTemp.getIdUsuario() != null) {
			objUsuario.setIdUsuario(usuarioTemp.getIdUsuario());
			objUsuario.setUsuario(usuarioTemp.getUsuario());
			objUsuario.setPassword(usuarioTemp.getPassword());
			objUsuario.setEmail(usuarioTemp.getEmail());
			objUsuario.setEstado(usuarioTemp.getEstado());

			if (dNUsuario.actualizarUsuario(objUsuario) != null) {
				System.out.println("Actualizacion exitosa");
				objUsuario = new Usuario();
			}

		}

	}

	public String getPassConfirmar() {
		return passConfirmar;
	}

	public void setPassConfirmar(String passConfirmar) {
		this.passConfirmar = passConfirmar;
	}

	public Usuario getObjUsuario() {
		return objUsuario;
	}

	public void setObjUsuario(Usuario objUsuario) {
		this.objUsuario = objUsuario;
	}

	public MBMensajes getMensajes() {
		return mensajes;
	}

	public void setMensajes(MBMensajes mensajes) {
		this.mensajes = mensajes;
	}

	public DNUsuarios getdNUsuario() {
		return dNUsuario;
	}

	public void setdNUsuario(DNUsuarios dNUsuario) {
		this.dNUsuario = dNUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getRepetirContrasena() {
		return repetirContrasena;
	}

	public void setRepetirContrasena(String repetirContrasena) {
		this.repetirContrasena = repetirContrasena;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getDescripcionPerfil() {
		return descripcionPerfil;
	}

	public void setDescripcionPerfil(String descripcionPerfil) {
		this.descripcionPerfil = descripcionPerfil;
	}

}
