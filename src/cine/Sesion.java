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
public class Sesion
{
	private String hora; //hora de la sesión en formato HH:MM.
	private int[][] estadoAsientos;/* es una matriz de enteros con las mismas dimensiones que la sala asociada a la
												propia sesión, en la que se representan las butacas compradas y las disponibles para la propia
												sesión. Si una butaca en la posición (i, j) está disponible, se guarda un valor 0, y en caso
												contrario, se guarda el identificador de venta asociado a la butaca. Como en una venta se puede
												comprar más de una butaca, podría suceder que en la matriz haya varios identificadores de
												ventas iguales en posiciones contiguas.
												*/
	private int asientosDisponibles; //es un entero que indica el número de asientos disponibles en la propia sesión
	private int sigIdCompra; //es un entero que se incrementa cada vez que se realiza una venta de entradas para la
	                         //propia sesión. Se utiliza para generar identificadores de venta diferentes para cada compra.
	
	/*constructor de la clase Sesion que recibe como argumentos la hora de proyección, y el
	número de filas y columnas de la sala asociada a la propia sesión. Con estos argumentos,
	inicializa los atributos del objeto. El atributo sigIdComprase inicializa a 1.
	*/
	public Sesion(String hora,int filas,int columnas)
	{
		this.hora = hora;
		estadoAsientos = new int[filas][columnas];
		asientosDisponibles = columnas * filas;
		sigIdCompra = 1;
	}

	/*método que devuelve la hora asociada a la propia sesión
	*/
	public String getHora()
	{
		return hora;
	}
	/*método que compra una entrada con la fila y columna dadas para la propia
	sesión. Para registrar la venta, se guarda el valor actual del atributo sigIdCompra en la posición
	(fila-1, columna-1) del atributo estadoAsientos. A continuación, se incrementa en uno el
	atributo sigIdCompra.
	*/
	public void comprarEntrada(int fila,int columna)
	{
		estadoAsientos[fila - 1][columna - 1] = sigIdCompra;
		sigIdCompra++;
		asientosDisponibles--;
	}
	/*método que devuelve el identificador de venta para una entrada en la propia sesión
	especificada mediante su fila y columna. El identificador que se devuelve se toma de la
	posición (fila-1, columna-1) del atributo estadoAsientos
	*/
	public int getIdEntrada(int fila,int columna)
	{
		return estadoAsientos[fila - 1][columna - 1];
	}
	/* método que devuelve una matriz de caracteres en la que se representa el estado
	de ocupación de la propia sesión. La matriz resultado tiene el mismo número de filas y
	columnas que la sala asociada a la propia sesión, y en cada posición (i, j) de la matriz debe
	aparecer un carácter ‘#’ si la butaca ha sido comprada, y un carácter ‘O’ si la butaca está 
	Programación II Práctica 1: Cine pág:6
	disponible. En la figura 2 se puede observar un ejemplo de una matriz de caracteres que refleja
	el estado de ocupación de una sala en una sesión.
	*/
	public char[][] getEstadoSesion()
	{
		char[][] resultado = new char[estadoAsientos.length][estadoAsientos[0].length];

		for (int i = 0 ; i < estadoAsientos.length ; i++) {
			for (int o = 0 ; o < estadoAsientos[0].length ; o++) {
				if (estadoAsientos[i][o] == 0) {
					resultado[i][o] = 'O';
				} else {
					resultado[i][o] = '#';
				}
			}
		}
		return resultado;
	}
	/*método que devuelve el número de butacas disponibles en la propia
	sesión.
	*/
	public int getButacasDisponiblesSesion()
	{
		return asientosDisponibles;
	}
	/*método que devuelve las N entradas asociadas a un identificador de venta dado
	para la propia sesión. Las N entradas se devolverán dentro de un String con el siguiente
	formato:
	“hora_de_la_sesión+fila1,columna1+fila2,columna2+ … +filaN,columnaN+”
	Si el identificador de venta dado no existe en la propia sesión, se devuelve null.
	*/
	public String recogerEntradas(int id)
	{
		String resultado = hora;
		for (int i = 0 ; i < estadoAsientos.length ; i++) {
			for (int o = 0 ; o < estadoAsientos[0].length ; o++) {
				if (estadoAsientos[i][o] == id) {
					resultado += "+" + (i + 1) + "," + (o + 1);
				}
			}
		}
		if(resultado.equals(hora))
		{
			return null;
		}else{
			return resultado + "+";
		}
	}
	/*método que dados un número N de butacas, devuelve un objeto de
	tipo ButacasContiguas que contiene la fila y la columna de la butaca recomendaba con menor
	número de columna, y el número de butacas solicitadas. 
	 */
	public ButacasContiguas recomendarButacasContiguas(int noButacas)
	{
		int fila = 0;
		int columna = 0;
		boolean auxiliar = false;
		for (int i = ((estadoAsientos.length + 1 )/ 2 + 1)-1 ; i < estadoAsientos.length && auxiliar == false ; i++) {
			for (int o = estadoAsientos[0].length - 1 ; o >= 0 + noButacas -1 && auxiliar == false ; o--) {
				if (butacasLibres(noButacas, i, o)) {
					fila = i + 1;
					columna = (o - (noButacas - 1)) + 1;// columna de la izquierda
					auxiliar = true;
				}
			}
		}
		if (auxiliar == false) {
			for (int i = ((estadoAsientos.length + 1) / 2) ; i >= 0 && auxiliar == false ; i--) {
				for (int o = estadoAsientos[0].length - 1 ; o >= 0 + noButacas-1 && auxiliar == false ; o--) {
					if (butacasLibres(noButacas, i, o)) {
						fila = i + 1;
						columna = (o - (noButacas - 1)) + 1; // columna de la izquierda
						auxiliar = true;
					}
				}
			}
		}
		if (auxiliar == false) {
			return null;
		} else {
			ButacasContiguas resultado = new ButacasContiguas(fila, columna, noButacas);
			return resultado;
		}
	}

	private boolean butacasLibres(int numButacas,int fila,int columna)
	{
		boolean resultado = true;
		for (int i = columna ; i > columna - numButacas && resultado ; i--) {
			if (estadoAsientos[fila][i] != 0) {
				resultado = false;
			}
		}
		return resultado;
	}
	/*método que dado un objeto de tipo ButacasContiguas,
	registra la compra en la propia sesión guardando el valor actual del atributo sigIdCompra en las
	posiciones especificadas por el objeto dado como argumento. A continuación, se incrementa en
	uno el atributo sigIdCompra.
	*/
	public void comprarEntradasRecomendadas(ButacasContiguas butacas)
	{
		for (int i = butacas.getColumna() - 1 ; i < butacas.getColumna() + butacas.getNoButacas() - 1 ; i++) {
			estadoAsientos[butacas.getFila() - 1][i] = sigIdCompra;
			asientosDisponibles--;
		}
		sigIdCompra++;
	}
}
