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
	/* m�todo que compra una entrada con la fila y columna dadas para la sala y
	sesi�n dadas. La compra queda registrada en el objeto de tipo Sesion correspondiente.
	*/
	public void comprarEntrada(int sala,int sesion,int fila,int columna)
	{
		salas[sala - 1].comprarEntrada(sesion, fila, columna);
	}
	/*m�todo que devuelve el identificador de venta para una entrada especificada
	mediante su sala, sesi�n, fila y columna. El algoritmo para obtener este identificador se
	explica en la especificaci�n del m�todo con el mismo nombre en la clase Sesion.
	*/
	public int getIdEntrada(int sala,int sesion,int fila,int columna)
	{
		return salas[sala - 1].getIdEntrada(sesion, fila, columna);
	}
	/* m�todo que devuelve un vector de String con los t�tulos de las pel�culas que se
	proyectan en el cine. En la posici�n 0 del vector se encontrar� el t�tulo de la pel�cula
	proyectada en la sala 1, en la posici�n 1 la de la sala 2, y as� sucesivamente.
	*/
	public String[] getPeliculas()
	{
		String[] resultado = new String[salas.length];
		for (int i = 0 ; i < salas.length ; i++) {
			resultado[i] = salas[i].getPelicula();
		}
		return resultado;
	}
	/*m�todo que devuelve un vector de String con las horas de las
	sesiones asociadas a una sala. En la posici�n 0 del vector se encontrar� la hora de la sesi�n
	1, en la posici�n 1 la de la sesi�n 2, y as� sucesivamente.
	*/
	public String[] getHorasDeSesionesDeSala(int sala)
	{
		return salas[sala - 1].getHorasDeSesionesDeSala();
	}
	/*m�todo que devuelve una matriz de caracteres en la que se representa el
	estado de ocupaci�n de una sala dada para una sesi�n dada. La matriz resultado tiene el
	mismo n�mero de filas y columnas que la sala dada, y en cada posici�n (i,j) de la matriz
	debe aparecer un car�cter �#� si la butaca ha sido comprada, y un car�cter �O� si la butaca
	est� disponible.
	*/
	public char[][] getEstadoSesion(int sala,int sesion)
	{
		return salas[sala - 1].getEstadoSesion(sesion);
	}
	/*m�todo que devuelve el n�mero de butacas disponibles en una
	sala dada para una sesi�n dada.
	*/
	public int getButacasDisponiblesSesion(int sala,int sesion)
	{
		return salas[sala - 1].getButacasDisponiblesSesion(sesion);
	}
	/*m�todo que devuelve las N entradas asociadas a un identificador de venta
	dado para una sala y una sesi�n dadas. Las N entradas se devolver�n dentro de un String
	con el siguiente formato:
	�nombre_del_cine@t�tulo_de_la_pel�cula@hora_de_la_sesi�n+fila1,columna1+fila2,columna2+ � +filaN,columnaN+�
	Por ejemplo: �Cine Para�so@Tibur�n@22:00+3,4+3,5+�
	Si el identificador de venta dado no existe en la sala y la sesi�n dadas, se devuelve null.
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
	/*m�todo que dados un n�mero de butacas, una sala y una
	sesi�n, devuelve un objeto de tipo ButacasContiguas que contiene la fila y la columna de
	la butaca recomendaba con menor n�mero de columna, y el n�mero de butacas solicitadas.
	El algoritmo para obtener las butacas recomendadas se explica en la especificaci�n del
	m�todo con el mismo nombre en la clase Sesion.
	*/
	public ButacasContiguas recomendarButacasContiguas(int noButacas,int sala,int sesion)
	{
		return salas[sala - 1].recomendarButacasContiguas(noButacas, sesion);
	}
	/*m�todo que dados un objeto de tipo ButacasContiguas,
	una sala y una sesi�n, registra la compra en el objeto de tipo Sesion correspondiente
	*/
	public void comprarEntradasRecomendadas(int sala,int sesion,ButacasContiguas butacas)
	{
		salas[sala - 1].comprarEntradasRecomendadas(sesion, butacas);
	}

}
