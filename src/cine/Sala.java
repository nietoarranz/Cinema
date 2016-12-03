package cine;
import anotacion.Programacion2;
@Programacion2 (
nombreAutor1	=	"Enrique",
apellidoAutor1	=	"Nieto Arranz",
emailUPMAutor1	=	"enrique.nieto.arranz@alumnos.upm.es",
enGrupo	=	true,
nombreAutor2	=	"Javier",
apellidoAutor2	=	"Moris Miranda",	
emailUPMAutor2	=	"j.moris@alumnos.upm.es"
)
public class Sala
{
	private String pelicula; //t�tulo de la pel�cula
	private Sesion[] sesiones; // son las sesiones en las que se proyecta la pel�cula en esta sala representadas por un vector de objetos de tipo Sesion.
	/* constructor de la clase Sala que recibe como argumentos el t�tulo de la pel�cula, un vector de
	String con las horas de las sesiones de esta sala, y el n�mero de filas y columnas de la sala. Con
	estos argumentos, inicializa los atributos del objeto. 
	*/
	public Sala(String pelicula,String[] horasSesiones,int filas,int columnas)
	{
		Sesion[] resultado = new Sesion[horasSesiones.length];
		for (int i = 0 ; i < resultado.length ; i++) {
			Sesion aux = new Sesion(horasSesiones[i], filas, columnas);
			resultado[i] = aux;
		}

		this.pelicula = pelicula;
		sesiones = resultado;
	}
	/*m�todo que devuelve el t�tulo de la pel�cula asociada a la propia sala.
	*/
	public String getPelicula()
	{
		return pelicula;
	}
	/* m�todo que compra una entrada con la fila y columna dadas para sesi�n dada de
	la propia sala. La compra queda registrada en el objeto de tipo Sesion correspondiente.
	*/
	public void comprarEntrada(int sesion,int fila,int columna)
	{
		sesiones[sesion - 1].comprarEntrada(fila, columna);
	}
	/* m�todo que devuelve el identificador de venta para una entrada en la propia sala
	especificada mediante su sesi�n, fila y columna. El algoritmo para obtener este identificador se
	explica en la especificaci�n del m�todo con el mismo nombre en la clase Sesion.
	*/
	public int getIdEntrada(int sesion,int fila,int columna)
	{
		return sesiones[sesion - 1].getIdEntrada(fila, columna);
	}
	/*m�todo que devuelve un vector de String con las horas de las
	sesiones asociadas a la propia sala. En la posici�n 0 del vector se encontrar� la hora de la sesi�n
	1, en la posici�n 1 la de la sesi�n 2, y as� sucesivamente.
	*/
	public String[] getHorasDeSesionesDeSala()
	{
		String[] resultado = new String[sesiones.length];
		for (int i = 0 ; i < sesiones.length ; i++) {
			resultado[i] = sesiones[i].getHora();
		}
		return resultado;
	}
	/* m�todo que devuelve una matriz de caracteres en la que se representa el estado
	de ocupaci�n de la propia sala para una sesi�n dada. El contenido de esta matriz se especifica
	en el m�todo con el mismo nombre de la clase Sesion.
	*/
	public char[][] getEstadoSesion(int sesion)
	{
		return sesiones[sesion - 1].getEstadoSesion();
	}
	/*m�todo que devuelve el n�mero de butacas disponibles en la propia
	sala para una sesi�n dada.
	*/
	public int getButacasDisponiblesSesion(int sesion)
	{
		return sesiones[sesion - 1].getButacasDisponiblesSesion();
	}
	/*m�todo que devuelve las N entradas asociadas a un identificador de venta dado
	para la propia sala y una sesi�n dada. Las N entradas se devolver�n dentro de un String con el
	siguiente formato:
	�t�tulo_de_la_pel�cula@hora_de_la_sesi�n+fila1,columna1+fila2,columna2+ � +filaN,columnaN+�
	Si el identificador de venta dado no existe en la propia sala y la sesi�n dada, se devuelve null.
	*/
	public String recogerEntradas(int id,int sesion)
	{
		String resultado = pelicula + "@";
		resultado += sesiones[sesion - 1].recogerEntradas(id);
		if(resultado.equals(pelicula + "@" + null))
		{
			return null;
		}else{
			return resultado;
		}
	}
	/* m�todo que dados un n�mero de butacas y una sesi�n de la propia
	sala, devuelve un objeto de tipo ButacasContiguas que contiene la fila y la columna de la
	butaca recomendaba con menor n�mero de columna, y el n�mero de butacas solicitadas. El
	algoritmo para obtener las butacas recomendadas se explica en la especificaci�n del m�todo
	con el mismo nombre en la clase Sesion
	*/
	public ButacasContiguas recomendarButacasContiguas(int noButacas, int sesion)
	{
		return sesiones[sesion - 1].recomendarButacasContiguas(noButacas);
	}
	/* m�todo que dados un objeto de tipo ButacasContiguas y una
	sesi�n de la propia sala, registra la compra en el objeto de tipo Sesion correspondiente.
	*/
	public void comprarEntradasRecomendadas(int sesion,ButacasContiguas butacas)
	{
		sesiones[sesion - 1].comprarEntradasRecomendadas(butacas);
	}

}
