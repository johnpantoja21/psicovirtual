package com.psicovirtual.liquidadorAdminTotal.vista.mb;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.psicovirtual.estandar.modelo.utilidades.Parametros;
import com.psicovirtual.estandar.vista.mb.MBMensajes;
import com.psicovirtual.estandar.vista.utilidades.AccesoPropiedadesVista;
import com.psicovirtual.estandar.vista.utilidades.ParametrosWeb;
import com.psicovirtual.estandar.vista.utilidades.UtilidadesWeb;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNTipoUsuario;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNUsuarios;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Tema;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.TipoUsuario;
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

	// private UploadedFile fotoUsuario;
	private UploadedFile fotoUsuario;

	private File fotoGuardar;

	private String nombre2;
	private String apellido2;
	private String usuario2;
	private String contrasena2;
	private String repetirContrasena2;
	private Date fechaNacimiento2;
	private String correo2;
	private String direccion2;
	private String telefono2;
	private String celular2;
	private String descripcionPerfil2;
	private String foto2;
	private TipoUsuario tipoUsuario2;

	private Usuario usuarioModificar;

	public MBUsuarios() throws Exception {
		objUsuario = new Usuario();

		// nombre = "john";
		// apellido = "pantija";
		// usuario = "asd";
		// contrasena = "123456";
		// repetirContrasena = "123456";
		// direccion = "asd";
		// correo = "johnpatnoja21@gmail.com";
		// descripcionPerfil = "asd";
		// celular = "1";
		// telefono = "2";

	}

	public void cargarUsuario(String user) throws Exception {
		System.out.println("java " + user);
		if (dNUsuario == null) {
			dNUsuario = new DNUsuarios();
		}

		usuarioModificar = dNUsuario.consultarDetalleUsuarioByUsuario(user);
		if (usuarioModificar != null) {
			usuarioLogueado(usuarioModificar);
		}

	}

	public void usuarioLogueado(Usuario parametroUsuario) {

		nombre2 = parametroUsuario.getNombre();
		apellido2 = parametroUsuario.getApellidos();
		usuario2 = parametroUsuario.getUsuario();
		contrasena2 = parametroUsuario.getPassword();
		repetirContrasena2 = parametroUsuario.getPassword();
		direccion2 = parametroUsuario.getDireccion();
		correo2 = parametroUsuario.getEmail();
		descripcionPerfil2 = parametroUsuario.getDescripcionPerfil();
		celular2 = parametroUsuario.getCelular();
		telefono2 = parametroUsuario.getTelefono();
		fechaNacimiento2 = parametroUsuario.getFechaNac();
		foto2 = parametroUsuario.getFoto();
		tipoUsuario2 = parametroUsuario.getTipoUsuario();
	}

	public void guardarPsicologo() throws Exception {
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
		insert.setDireccion(direccion);
		;
		insert.setPassword(contrasena);
		insert.setTelefono(telefono);
		insert.setUsuario(usuario);
		insert.setTipoUsuario(dNTipoUsuario.buscarTipoUsuario(2));
		System.out.println("fotoh");
		int usuarioRepetido = 0;
		usuarioRepetido = dNUsuario.consultarUsuarioRepetido(insert);

		if (usuarioRepetido == 0) {

			if (contrasena.length() >= 6) {

				if (contrasena.compareTo(repetirContrasena) == 0) {

					boolean insertar = true;

					if (fotoUsuario != null) {

						if (fotoUsuario.getFileName().length() > 0) {

							System.out.println("foto" + fotoUsuario.getFileName());

							String ext2 = FilenameUtils.getExtension(fotoUsuario.getFileName());

							if (ext2.equalsIgnoreCase("jpg") || ext2.equalsIgnoreCase("png")
									|| ext2.equalsIgnoreCase("jpeg")) {

								String carpetaArchivos = null;
								OutputStream outputStream = null;
								InputStream inputStream = null;
								int bufferSize = 5048;
								AccesoPropiedadesVista prop = null;

								ParametrosWeb param = (ParametrosWeb) UtilidadesWeb.getManagedBean("Parametros");
								prop = new AccesoPropiedadesVista();
								carpetaArchivos = param.getCONTEXTO_APP() + Parametros.RUTA_FOTOS;

								System.out.println("carpeta " + carpetaArchivos);
								// Leemos el archivo
								fotoGuardar = new File(carpetaArchivos

										+ usuario + "." + ext2);
								outputStream = new FileOutputStream(fotoGuardar);
								inputStream = fotoUsuario.getInputstream();
								byte bufferData[] = new byte[bufferSize];
								int bytesWrite;
								while ((bytesWrite = inputStream.read(bufferData)) > 0)
									outputStream.write(bufferData, 0, bytesWrite);

								outputStream.close();
								inputStream.close();
								insert.setFoto(usuario + "." + ext2);

							} else {
								insertar = false;
								mensajes.mostrarMensaje("Formato De Foto No valido", 2);
							}
						} else {
							insert.setFoto("NO.jpg");

						}

					} else {
						insert.setFoto("NO.jpg");

					}

					if (insertar == true) {
						dNUsuario.crearUsuario(insert);
						limpiarUsuario();
						mensajes.mostrarMensaje("Registro Exitoso", 1);

						try {
							FacesContext context = FacesContext.getCurrentInstance();
							ExternalContext extContext = context.getExternalContext();
							String url2 = extContext.encodeActionURL(context.getApplication().getViewHandler()
									.getActionURL(context, "/view/gestion/bienvenido.xhtml"));
							extContext.redirect(url2);
						} catch (Exception e) {
							System.out.println("Error en el metodo iniciarSesion: " + e);
						}
					}

				} else {
					mensajes.mostrarMensaje("La contraseña y repetir contraseña no son iguales", 2);

				}

			} else {
				mensajes.mostrarMensaje("La contraseña debe ser de minimo 6 caracteres", 2);

			}

		} else {

			mensajes.mostrarMensaje("Ya existe un usuario registrado con este nombre : " + usuario, 2);

		}

		// limpiarUsuario();

	}

	public void modificarUsuario() throws Exception {
		if (dNUsuario == null) {
			dNUsuario = new DNUsuarios();
		}

		if (dNTipoUsuario == null) {
			dNTipoUsuario = new DNTipoUsuario();
		}

		if (usuarioModificar != null) {
			Usuario insert = usuarioModificar;
			insert.setNombre(nombre2);
			insert.setApellidos(apellido2);
			insert.setCelular(celular2);
			insert.setDescripcionPerfil(descripcionPerfil2);
			insert.setEmail(correo2);
			insert.setEstado("ACTIVO");
			insert.setFechaNac(fechaNacimiento2);
			insert.setDireccion(direccion2);

			insert.setPassword(contrasena2);
			insert.setTelefono(telefono2);
			insert.setUsuario(usuario2);
			insert.setTipoUsuario(tipoUsuario2);

			System.out.println("fotoh");

			if (contrasena2.length() >= 6) {

				if (contrasena2.compareTo(repetirContrasena2) == 0) {

					boolean insertar = true;

					if (fotoUsuario != null) {

						if (fotoUsuario.getFileName().length() > 0) {

							System.out.println("foto" + fotoUsuario.getFileName());

							String ext2 = FilenameUtils.getExtension(fotoUsuario.getFileName());

							if (ext2.equalsIgnoreCase("jpg") || ext2.equalsIgnoreCase("png")
									|| ext2.equalsIgnoreCase("jpeg")) {

								String carpetaArchivos = null;
								OutputStream outputStream = null;
								InputStream inputStream = null;
								int bufferSize = 5048;
								AccesoPropiedadesVista prop = null;

								ParametrosWeb param = (ParametrosWeb) UtilidadesWeb.getManagedBean("Parametros");
								prop = new AccesoPropiedadesVista();
								carpetaArchivos = param.getCONTEXTO_APP() + Parametros.RUTA_FOTOS;

								System.out.println("carpeta " + carpetaArchivos);
								// Leemos el archivo
								fotoGuardar = new File(carpetaArchivos

										+ usuario2 + "." + ext2);

								if (fotoGuardar.exists()) {
									fotoGuardar.delete();
								}

								outputStream = new FileOutputStream(fotoGuardar);
								inputStream = fotoUsuario.getInputstream();
								byte bufferData[] = new byte[bufferSize];
								int bytesWrite;
								while ((bytesWrite = inputStream.read(bufferData)) > 0)
									outputStream.write(bufferData, 0, bytesWrite);

								outputStream.close();
								inputStream.close();
								insert.setFoto(usuario2 + "." + ext2);

							} else {
								insertar = false;
								mensajes.mostrarMensaje("Formato De Foto No valido", 2);
							}
						}
					}

					if (insertar == true) {
						dNUsuario.actualizarUsuario(insert);

						mensajes.mostrarMensaje("Modificacion Exitosa", 1);

						try {
							FacesContext context = FacesContext.getCurrentInstance();
							ExternalContext extContext = context.getExternalContext();
							String url2 = extContext.encodeActionURL(context.getApplication().getViewHandler()
									.getActionURL(context, "/view/gestion/bienvenido.xhtml"));
							extContext.redirect(url2);
						} catch (Exception e) {
							System.out.println("Error en el metodo iniciarSesion: " + e);
						}
					}

				} else {
					mensajes.mostrarMensaje("La contraseña y repetir contraseña no son iguales", 2);

				}

			} else {
				mensajes.mostrarMensaje("La contraseña debe ser de minimo 6 caracteres", 2);

			}

		}
		// limpiarUsuario();

	}

	public void guardarCliente() throws Exception {
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
		insert.setDireccion(direccion);

		insert.setPassword(contrasena);
		insert.setTelefono(telefono);
		insert.setUsuario(usuario);
		insert.setTipoUsuario(dNTipoUsuario.buscarTipoUsuario(3));
		System.out.println("fotoh");
		int usuarioRepetido = 0;
		usuarioRepetido = dNUsuario.consultarUsuarioRepetido(insert);
		System.out.println("RETORNA " + usuarioRepetido);
		if (usuarioRepetido == 0) {

			if (contrasena.length() >= 6) {

				if (contrasena.compareTo(repetirContrasena) == 0) {

					boolean insertar = true;

					if (fotoUsuario != null) {

						if (fotoUsuario.getFileName().length() > 0) {

							System.out.println("foto" + fotoUsuario.getFileName());

							String ext2 = FilenameUtils.getExtension(fotoUsuario.getFileName());

							if (ext2.equalsIgnoreCase("jpg") || ext2.equalsIgnoreCase("png")
									|| ext2.equalsIgnoreCase("jpeg")) {

								String carpetaArchivos = null;
								OutputStream outputStream = null;
								InputStream inputStream = null;
								int bufferSize = 5048;
								AccesoPropiedadesVista prop = null;

								ParametrosWeb param = (ParametrosWeb) UtilidadesWeb.getManagedBean("Parametros");
								prop = new AccesoPropiedadesVista();
								carpetaArchivos = param.getCONTEXTO_APP() + Parametros.RUTA_FOTOS;

								System.out.println("carpeta " + carpetaArchivos);
								// Leemos el archivo
								fotoGuardar = new File(carpetaArchivos

										+ usuario + "." + ext2);
								outputStream = new FileOutputStream(fotoGuardar);
								inputStream = fotoUsuario.getInputstream();
								byte bufferData[] = new byte[bufferSize];
								int bytesWrite;
								while ((bytesWrite = inputStream.read(bufferData)) > 0)
									outputStream.write(bufferData, 0, bytesWrite);

								outputStream.close();
								inputStream.close();
								insert.setFoto(usuario + "." + ext2);

							} else {
								insertar = false;
								mensajes.mostrarMensaje("Formato De Foto No valido", 2);
							}
						} else {
							insert.setFoto("NO.jpg");

						}
					} else {
						insert.setFoto("NO.jpg");

					}

					if (insertar == true) {
						dNUsuario.crearUsuario(insert);

						mensajes.mostrarMensaje("Registro Exitoso", 1);
						limpiarUsuario();
						try {
							FacesContext context = FacesContext.getCurrentInstance();
							ExternalContext extContext = context.getExternalContext();
							String url2 = extContext.encodeActionURL(context.getApplication().getViewHandler()
									.getActionURL(context, "/view/gestion/bienvenido.xhtml"));
							extContext.redirect(url2);
						} catch (Exception e) {
							System.out.println("Error en el metodo iniciarSesion: " + e);
						}
					}

				} else {
					mensajes.mostrarMensaje("La contraseña y repetir contraseña no son iguales", 2);

				}

			} else {
				mensajes.mostrarMensaje("La contraseña debe ser de minimo 6 caracteres", 2);

			}

		} else {

			mensajes.mostrarMensaje("Ya existe un usuario registrado con este nombre : " + usuario, 2);

		}

		// limpiarUsuario();

	}

	public void limpiarUsuario() {
		nombre = null;
		apellido = null;
		usuario = null;
		contrasena = null;

		repetirContrasena = null;
		fechaNacimiento = null;
		correo = null;
		direccion = null;
		telefono = null;
		celular = null;
		descripcionPerfil = null;
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

		if (usuarioTemp !=null) {
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

	public DNTipoUsuario getdNTipoUsuario() {
		return dNTipoUsuario;
	}

	public void setdNTipoUsuario(DNTipoUsuario dNTipoUsuario) {
		this.dNTipoUsuario = dNTipoUsuario;
	}

	public UploadedFile getFotoUsuario() {
		return fotoUsuario;
	}

	public void setFotoUsuario(UploadedFile fotoUsuario) {
		this.fotoUsuario = fotoUsuario;
	}

	public File getFotoGuardar() {
		return fotoGuardar;
	}

	public void setFotoGuardar(File fotoGuardar) {
		this.fotoGuardar = fotoGuardar;
	}

	public String getNombre2() {
		return nombre2;
	}

	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getUsuario2() {
		return usuario2;
	}

	public void setUsuario2(String usuario2) {
		this.usuario2 = usuario2;
	}

	public String getContrasena2() {
		return contrasena2;
	}

	public void setContrasena2(String contrasena2) {
		this.contrasena2 = contrasena2;
	}

	public String getRepetirContrasena2() {
		return repetirContrasena2;
	}

	public void setRepetirContrasena2(String repetirContrasena2) {
		this.repetirContrasena2 = repetirContrasena2;
	}

	public Date getFechaNacimiento2() {
		return fechaNacimiento2;
	}

	public void setFechaNacimiento2(Date fechaNacimiento2) {
		this.fechaNacimiento2 = fechaNacimiento2;
	}

	public String getCorreo2() {
		return correo2;
	}

	public void setCorreo2(String correo2) {
		this.correo2 = correo2;
	}

	public String getDireccion2() {
		return direccion2;
	}

	public void setDireccion2(String direccion2) {
		this.direccion2 = direccion2;
	}

	public String getTelefono2() {
		return telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	public String getCelular2() {
		return celular2;
	}

	public void setCelular2(String celular2) {
		this.celular2 = celular2;
	}

	public String getDescripcionPerfil2() {
		return descripcionPerfil2;
	}

	public void setDescripcionPerfil2(String descripcionPerfil2) {
		this.descripcionPerfil2 = descripcionPerfil2;
	}

	public String getFoto2() {
		return foto2;
	}

	public void setFoto2(String foto2) {
		this.foto2 = foto2;
	}

	public TipoUsuario getTipoUsuario2() {
		return tipoUsuario2;
	}

	public void setTipoUsuario2(TipoUsuario tipoUsuario2) {
		this.tipoUsuario2 = tipoUsuario2;
	}

}
