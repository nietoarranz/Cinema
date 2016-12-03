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
public class Cine
{
	private String nombre; //nombre del cine
	private Sala[] salas; //son las salas del cine representadas por un vector de objetos de tipo Sala asociados al cine.

	/* constructor de la clase Cine que recibe como argumento el nombre del cine y un vector de
		salas, y se encarga de inicializar los atributos del objeto. 
	 */
	public Cine(String nombre,Sala[] salas)
	{
		this.nombre = nombre;
		this.salas = salas;
	}
	/* método que compra una entrada con la fila y columna dadas para la sala y
	sesión dadas. La compra queda registrada en el objeto de tipo Sesion correspondiente.
	*/
	public void comprarEntrada(int sala,int sesion,int fila,int columna)
	{
		salas[sala - 1].comprarEntrada(sesion, fila, columna);
	}
	/*método que devuelve el identificador de venta para una entrada especificada
	mediante su sala, sesión, fila y columna. El algoritmo para obtener este identificador se
	explica en la especificación del método con el mismo nombre en la clase Sesion.
	*/
	public int getIdEntrada(int sala,int sesion,int fila,int columna)
	{
		return salas[sala - 1].getIdEntrada(sesion, fila, columna);
	}
	/* método que devuelve un vector de String con los títulos de las películas que se
	proyectan en el cine. En la posición 0 del vector se encontrará el título de la película
	proyectada en la sala 1, en la posición 1 la de la sala 2, y así sucesivamente.
	*/
	public String[] getPeliculas()
	{
		String[] resultado = new String[salas.length];
		for (int i = 0 ; i < salas.length ; i++) {
			resultado[i] = salas[i].getPelicula();
		}
		return resultado;
	}
	/*método que devuelve un vector de String con las horas de las
	sesiones asociadas a una sala. En la posición 0 del vector se encontrará la hora de la sesión
	1, en la posición 1 la de la sesión 2, y así sucesivamente.
	*/
	public String[] getHorasDeSesionesDeSala(int sala)
	{
		return salas[sala - 1].getHorasDeSesionesDeSala();
	}
	/*método que devuelve una matriz de caracteres en la que se representa el
	estado de ocupación de una sala dada para una sesión dada. La matriz resultado tiene el
	mismo número de filas y columnas que la sala dada, y en cada posición (i,j) de la matriz
	debe aparecer un carácter ‘#’ si la butaca ha sido comprada, y un carácter ‘O’ si la butaca
	está disponible.
	*/
	public char[][] getEstadoSesion(int sala,int sesion)
	{
		return salas[sala - 1].getEstadoSesion(sesion);
	}
	/*método que devuelve el número de butacas disponibles en una
	sala dada para una sesión dada.
	*/
	public int getButacasDisponiblesSesion(int sala,int sesion)
	{
		return salas[sala - 1].getButacasDisponiblesSesion(sesion);
	}
	/*método que devuelve las N entradas asociadas a un identificador de venta
	dado para una sala y una sesión dadas. Las N entradas se devolverán dentro de un String
	con el siguiente formato:
	“nombre_del_cine@título_de_la_película@hora_de_la_sesión+fila1,columna1+fila2,columna2+ … +filaN,columnaN+”
	Por ejemplo: “Cine Paraíso@Tiburón@22:00+3,4+3,5+”
	Si el identificador de venta dado no existe en la sala y la sesión dadas, se devuelve null.
	*/
	public String recogerEntradas(int id,int sala,int sesion)
	{
		String resultado = nombre + "@";
		resultado += salas[sala - 1].recogerEntradas(id, sesion);
		if(resultado.equals(nombre + "@" + null))
		{
			return null;
		}else{
			return resultado;
		}

	}
	/*método que dados un número de butacas, una sala y una
	sesión, devuelve un objeto de tipo ButacasContiguas que contiene la fila y la columna de
	la butaca recomendaba con menor número de columna, y el número de butacas solicitadas.
	El algoritmo para obtener las butacas recomendadas se explica en la especificación del
	método con el mismo nombre en la clase Sesion.
	*/
	public ButacasContiguas recomendarButacasContiguas(int noButacas,int sala,int sesion)
	{
		return salas[sala - 1].recomendarButacasContiguas(noButacas, sesion);
	}
	/*método que dados un objeto de tipo ButacasContiguas,
	una sala y una sesión, registra la compra en el objeto de tipo Sesion correspondiente
	*/
	public void comprarEntradasRecomendadas(int sala,int sesion,ButacasContiguas butacas)
	{
		salas[sala - 1].comprarEntradasRecomendadas(sesion, butacas);
	}

}
