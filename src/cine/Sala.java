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
	private String pelicula; //título de la película
	private Sesion[] sesiones; // son las sesiones en las que se proyecta la película en esta sala representadas por un vector de objetos de tipo Sesion.
	/* constructor de la clase Sala que recibe como argumentos el título de la película, un vector de
	String con las horas de las sesiones de esta sala, y el número de filas y columnas de la sala. Con
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
	/*método que devuelve el título de la película asociada a la propia sala.
	*/
	public String getPelicula()
	{
		return pelicula;
	}
	/* método que compra una entrada con la fila y columna dadas para sesión dada de
	la propia sala. La compra queda registrada en el objeto de tipo Sesion correspondiente.
	*/
	public void comprarEntrada(int sesion,int fila,int columna)
	{
		sesiones[sesion - 1].comprarEntrada(fila, columna);
	}
	/* método que devuelve el identificador de venta para una entrada en la propia sala
	especificada mediante su sesión, fila y columna. El algoritmo para obtener este identificador se
	explica en la especificación del método con el mismo nombre en la clase Sesion.
	*/
	public int getIdEntrada(int sesion,int fila,int columna)
	{
		return sesiones[sesion - 1].getIdEntrada(fila, columna);
	}
	/*método que devuelve un vector de String con las horas de las
	sesiones asociadas a la propia sala. En la posición 0 del vector se encontrará la hora de la sesión
	1, en la posición 1 la de la sesión 2, y así sucesivamente.
	*/
	public String[] getHorasDeSesionesDeSala()
	{
		String[] resultado = new String[sesiones.length];
		for (int i = 0 ; i < sesiones.length ; i++) {
			resultado[i] = sesiones[i].getHora();
		}
		return resultado;
	}
	/* método que devuelve una matriz de caracteres en la que se representa el estado
	de ocupación de la propia sala para una sesión dada. El contenido de esta matriz se especifica
	en el método con el mismo nombre de la clase Sesion.
	*/
	public char[][] getEstadoSesion(int sesion)
	{
		return sesiones[sesion - 1].getEstadoSesion();
	}
	/*método que devuelve el número de butacas disponibles en la propia
	sala para una sesión dada.
	*/
	public int getButacasDisponiblesSesion(int sesion)
	{
		return sesiones[sesion - 1].getButacasDisponiblesSesion();
	}
	/*método que devuelve las N entradas asociadas a un identificador de venta dado
	para la propia sala y una sesión dada. Las N entradas se devolverán dentro de un String con el
	siguiente formato:
	“título_de_la_película@hora_de_la_sesión+fila1,columna1+fila2,columna2+ … +filaN,columnaN+”
	Si el identificador de venta dado no existe en la propia sala y la sesión dada, se devuelve null.
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
	/* método que dados un número de butacas y una sesión de la propia
	sala, devuelve un objeto de tipo ButacasContiguas que contiene la fila y la columna de la
	butaca recomendaba con menor número de columna, y el número de butacas solicitadas. El
	algoritmo para obtener las butacas recomendadas se explica en la especificación del método
	con el mismo nombre en la clase Sesion
	*/
	public ButacasContiguas recomendarButacasContiguas(int noButacas, int sesion)
	{
		return sesiones[sesion - 1].recomendarButacasContiguas(noButacas);
	}
	/* método que dados un objeto de tipo ButacasContiguas y una
	sesión de la propia sala, registra la compra en el objeto de tipo Sesion correspondiente.
	*/
	public void comprarEntradasRecomendadas(int sesion,ButacasContiguas butacas)
	{
		sesiones[sesion - 1].comprarEntradasRecomendadas(butacas);
	}

}
