package Pasarela;

import java.util.Scanner;

/**
 * @author Kawtar
 * @since Este año
 */
public class Main {

    /**
     * @param cantidad es el dinero que introduces
     * @return devuelve verdadero si incluye un número del 0 al 9 incluyendo un solo punto
     */
    public static boolean validarNum(String cantidad) {

        int numeroDePuntos = 0;

        //Si el usuario solo escribe un punto o un espacio en blanco devuelve falso
        if (cantidad.equals("") || cantidad.equals(".")) return false;

        //Si contiene mas de un punto devuelve falso
        for (int i = 0; i < cantidad.length(); ++i) {
            if (cantidad.charAt(i) == '.') {
                numeroDePuntos = numeroDePuntos + 1;
            }

            if (numeroDePuntos >= 2) {
                return false;
            }
        }

        cantidad = cantidad.replace(".", "");

        for (int i = 0; i < cantidad.length(); ++i) {

            if (cantidad.charAt(i) < '0' || cantidad.charAt(i) > '9') {

                return false;
                //Te devuelve que es falso debido a que el carácter que obtenemos es mayor o menor que 0, y por lo tanto no es un carácter numérico
            }
        }

        return true; //Si no entra en la condición if verifica todos los caracteres y sale del bucle for y devuelve que es verdadero
    }

    /**
     * Función es para introducir la cantidad que debes pagar
     *
     * @return 1.0 si el scanner que se obtiene no es válido, y devuelve un float si lo es.
     */
    public static float cantidadPagar() {
        Scanner entrada = new Scanner(System.in);
        String cantidadPagar;
        System.out.println("Introduce el importe que se le tiene que cobrar (ej: 233,90): ");

        cantidadPagar = entrada.nextLine();
        cantidadPagar = cantidadPagar.replaceAll(",", ".");
        cantidadPagar = cantidadPagar.replace(" ", ".");

        //Si la cantidad de carácteres introducidos son mayores que 12 va volver a repetir la función
        if (!(cantidadPagar.length() < 12)) {
            System.out.println("Introduce una cantidad racional!!");
            return -1.0f;
        }

        // Verificar si el número introducido es válido o no, sin incluir la coma.
        if (validarNum(cantidadPagar)) {
            //Remplaza la coma por un punto para que no de error
            return Float.parseFloat(cantidadPagar.replace(",", "."));
        }

        //Devuelve -1.0 si la función validarNum es falsa
        return -1.0f;
    }

    /**
     * Función para elegir el método de pago
     *
     * @param dineroAPagar es el dinero que debes pagar validado y pasado a float
     */
    public static void metodoDePago(float dineroAPagar) {

        Scanner entrada = new Scanner(System.in);
        int metodoDePagoCorrecto = 0;

        System.out.println("Seleccione un método de pago " + Math.round(dineroAPagar * 100.0) / 100.0 + "€");
        System.out.println("*****************************************************");
        System.out.println("1. Efectivo. \n2. Cargo en cuenta. \n3. Tarjeta de crédito. \n4. Salir");
        String metodoDePagoElegir = entrada.nextLine();

        //Si no se elige una opción correctamente te vuelve a repetir la función
        if (!(metodoDePagoElegir.equals("1") || metodoDePagoElegir.equals("2") || metodoDePagoElegir.equals("3") || metodoDePagoElegir.equals("4"))) { //Para que el número introducido sea siempre igual a 1, 2 o 3.

            System.out.println("Intento incorrecto. Vuelva a intentarlo....");
            metodoDePago(dineroAPagar);

        } else {
            //Si es valida la opción te la transforma a integer para meterla en el switch
            metodoDePagoCorrecto = Integer.parseInt(metodoDePagoElegir);

        }
        //Dependiendo del metodo que elijas te llama una función u otra.
        switch (metodoDePagoCorrecto) {
            case 1:
                pagarEfectivo(dineroAPagar);
                break;
            case 2:
                pagarCuenta(dineroAPagar);
                break;
            case 3:
                pagarTarjeta(dineroAPagar);
                break;
            case 4:
                System.out.println("Adios!!! \nVuelva pronto.");
                //Lo que hace la siguiente línea es terminar el código sin que te llegue a dar un error
                System.exit(0);

        }
    }

    /**
     * Función para el pago en efectivo
     *
     * @param dineroAPagar es el dinero que debes pagar validado y pasado a float.
     */
    public static void pagarEfectivo(float dineroAPagar) {

        Scanner entrada = new Scanner(System.in);
        double dineroEfectivo = 0;
        double dineroCambio = 0;

        System.out.println("Introduce la cantidad de dinero a entregar: ");
        String dineroPagarEnEfectivo = entrada.nextLine();

        //Remplazamos la coma y el espacio en la variable dineroPgarEfectivo, por un punto para que no de un error.
        dineroPagarEnEfectivo = dineroPagarEnEfectivo.replace(",", ".");
        dineroPagarEnEfectivo = dineroPagarEnEfectivo.replace(" ", ".");

        //Si la validación de números es correcta se pasa dineroPagarEnEfectivo a un double
        if (validarNum(dineroPagarEnEfectivo) && dineroPagarEnEfectivo.length() < 10) {
            dineroEfectivo = Float.parseFloat(dineroPagarEnEfectivo);
        } else {
            //En caso de que la validación sea incorrecta se vuelve a pedir la cantidad que se va a pagar en efectivo
            while (!validarNum(dineroPagarEnEfectivo)) {
                System.out.println("Introduce una cantidad valida");
                pagarEfectivo(dineroAPagar);
            }
        }
        dineroCambio = dineroEfectivo;

        //Si el dinero que vas a pagar es menor que la cantidad que debes pagar te lo vuelve a pedir la cantidad a pagar
        while (!(dineroCambio >= dineroAPagar)) {

            System.out.println("El dinero a entregar tiene que ser mayor o igual que el dinero a pagar");
            pagarEfectivo(dineroAPagar);

        }

        dineroEfectivo = (dineroEfectivo - dineroAPagar);

        //El dinero que se devuelve (haciendo la resta del dinero que hay que pagar, y del dinero que debes pagar se obtiene)
        double devolver = dineroEfectivo;
        int cincuenta = 0, veinte = 0, diez = 0, cinco = 0, dos = 0, uno = 0;

        //Para saber el cambio que hay que efectuar con billetes o monedas, efectuamos contadores con while para saber que hay que devolver.
        while (dineroEfectivo >= 50) {
            cincuenta++;
            dineroEfectivo = dineroEfectivo - 50;

        }
        while (dineroEfectivo >= 20) {
            veinte++;
            dineroEfectivo = dineroEfectivo - 20;

        }
        while (dineroEfectivo >= 10) {
            diez++;
            dineroEfectivo = dineroEfectivo - 10;
        }
        while (dineroEfectivo >= 5) {
            cinco++;
            dineroEfectivo = dineroEfectivo - 5;
        }
        while (dineroEfectivo >= 2) {
            dos++;
            dineroEfectivo = dineroEfectivo - 2;
        }
        while (dineroEfectivo >= 1) {
            uno++;
            dineroEfectivo = dineroEfectivo - 1;
        }

        //Redondeamos el cambio que se muestra por pantalla
        dineroEfectivo *= 100;
        dineroEfectivo = Math.round(dineroEfectivo);
        dineroEfectivo /= 100;

        devolver *= 100;
        devolver = Math.round(devolver);
        devolver /= 100;


        System.out.println("SU CAMBIO");
        System.out.println("**************");

        if (cincuenta > 0) System.out.println("50€" + "   " + cincuenta + " billete(s)");
        if (veinte > 0) System.out.println("20€" + "   " + veinte + " billete(s)");
        if (diez > 0) System.out.println("10€" + "   " + diez + " billete(s)");
        if (cinco > 0) System.out.println("5€" + "   " + cinco + " billete(s)");
        if (diez > 0) System.out.println("2€" + "   " + dos + " moneda(s)");
        if (uno > 0) System.out.println("1€" + "   " + uno + " moneda(s)");
        if (dineroEfectivo > 0) System.out.println("cent" + "   " + dineroEfectivo + " centimo(s)");

        System.out.println("Su cambio es de : " + Math.round(devolver * 100.0) / 100 + "€");
        System.out.println("GRACIAS POR SU VISITA");
        //Al terminar la devolución te sale del programa.
        System.exit(0);

    }

    /**
     * Función para el pago con tarjeta.
     *
     * @param dineroAPagar es el dinero que debes pagar validado y pasado a float.
     */

    public static void pagarTarjeta(float dineroAPagar) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce el número de la tarjeta (16 números): 4013 4678 1234 4812");
        System.out.print("Credit card Number:");
        String tarjeta = entrada.nextLine();

        //Si la tarjeta contiene alguno de esos carácteres se remplazan por un espacio vacío.
        if (tarjeta.contains("/") || tarjeta.contains("-") || tarjeta.contains(" ") || tarjeta.contains(".")) { //para que me acepte la tarjeta aun habiendo puesto signos utilizados a la hora de escribir tarjetas, los elimino
            tarjeta = tarjeta.replaceAll(" ", "");
            tarjeta = tarjeta.replaceAll("-", "");
            tarjeta = tarjeta.replaceAll("/", "");
            tarjeta = tarjeta.replaceAll(".", "");
        }

        //Las tarjetas tiene que tener siempre 16 dígitos, por este motivo se han de eliminar los signos.
        if (tarjeta.length() == 16 && validarNum(tarjeta)) {
            //Se valida que son números, y se verifica que la tarjeta empieza por 3, 4 o 5 debido a que si no estaría incorrecta
            if (validarNum(tarjeta)) {

                int tipo = tarjeta.charAt(0);
                switch (tipo) {
                    case 3:
                        System.out.println("American Express");
                        break;
                    case 4:
                        System.out.println("Visa Card");
                        break;
                    case 5:
                        System.out.println("Mastercard");
                        break;
                    default:
                        System.out.println("La tarjeta " + tarjeta + " es incorrecta. Por favor, seleccione de nuevo");
                        metodoDePago(dineroAPagar);
                        break;
                }
            }

            // Si no se cumplen las condiciones mencionadas, se vuelve a pedir el método de pago con el que se desea realizar la transacción
        } else {
            System.out.println("La tarjeta " + tarjeta + " es incorrecta. Por favor, seleccione de nuevo");
            metodoDePago(dineroAPagar);

        }

        String tarjetaTerminada = "";

        //La variable impar se utilizará para las posiciones impares de la tarjeta introducida
        int impar = -1;
        char numPoscnPar, numPoscnImpar;
        //La variable suma se utilizará para la suma de todos los números
        int suma = 0;

        //Bucle for para hacer el recuento hasta el número 15 debido a que la tarjeta tiene 16 números
        for (int par = 0; par <= 15; par = par + 2) {
            //le sumo a la variable 2 para que siga siendo impar durante todo el bucle.
            impar += 2;

            //Se le asigna a numPoscnPar a los números pares y a numPoscnImpar los números impares.
            numPoscnPar = tarjeta.charAt(par);
            numPoscnImpar = tarjeta.charAt(impar);

            //Paso de un char a un int llamado posición par o impar
            int posicionPar = Integer.parseInt(String.valueOf(numPoscnPar));
            int posicionImpar = Integer.parseInt(String.valueOf(numPoscnImpar));

            //Multiplicación del número par por 2 y si es mayor que 9 se le resta 9.
            posicionPar *= 2;
            if (posicionPar >= 9) {

                posicionPar -= 9;
            }

            //aSe le asigna el valor a la tarjeta poniendo en la posición par el número que se han multiplicado por 2.
            tarjetaTerminada = tarjetaTerminada + posicionPar + posicionImpar;

            //Suma de todos los dígitos de la tarjeta.
            suma = suma + posicionPar + posicionImpar;
        }

        //Si la tarjeta es múltiplo de 10 y menor o igual que 150 se da como válida.
        if (suma % 10 == 0 && suma <= 150) {
            System.out.println("La tarjeta " + tarjeta + " es válida.");
            System.out.println("Se le ha procedido a cobrar " + Math.round(dineroAPagar) + "€");
            System.out.println("GRACIAS POR SU VISTA!!");
            System.exit(0);

            //Si no es válida te vuelve a pedir otra vez el método de pago.
        } else {
            System.out.println("La tarjeta " + tarjeta + " es incorrecta. Por favor, seleccione de nuevo");
            metodoDePago(dineroAPagar);
        }

    }

    /**
     * Función para el pago con cuenta Bancaria
     * @param dineroAPagar es el dinero que debes pagar validado y pasado a float.
     */
    public static void pagarCuenta(float dineroAPagar) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce el número de cuenta corriente (20 números):1234 5678 - 97 1234567890");
        System.out.print("Núm. CCC:");
        String cuenta = entrada.nextLine();

        //Para que la validación no me dé errónea quitamos todos los signos que se puedan llegar a introducir
        cuenta = cuenta.replaceAll(" ", "");
        cuenta = cuenta.replaceAll("-", "");
        cuenta = cuenta.replaceAll("/", "");


        String primerosDiezDigitos = "";
        int sumaPrimerosDiezDigitos = 0;

        //Se valida que son 20 números, en caso de que no sea cierto se vuelve a pedir el método de pago.
        if (cuenta.length() == 20 && validarNum(cuenta)) {
            for (int i = 0; i < 8; i++) {

                char poscPrimeraParteCuenta = cuenta.charAt(i);
                int posicionPrimera = Integer.parseInt(String.valueOf(poscPrimeraParteCuenta));

                //Obtención de los primeros 8 dígitos debido a que los dos ceros no cuentan

                primerosDiezDigitos = primerosDiezDigitos + posicionPrimera;

                //Realiza las multiplicaciones que se pedían para verificar la cuenta bancaria.
                switch (i) {
                    case 0:
                        sumaPrimerosDiezDigitos = sumaPrimerosDiezDigitos + (posicionPrimera * 4);
                        break;
                    case 1:
                        sumaPrimerosDiezDigitos = sumaPrimerosDiezDigitos + (posicionPrimera * 8);
                        break;
                    case 2:
                        sumaPrimerosDiezDigitos = sumaPrimerosDiezDigitos + (posicionPrimera * 5);
                        break;
                    case 3:
                        sumaPrimerosDiezDigitos = sumaPrimerosDiezDigitos + (posicionPrimera * 10);
                        break;
                    case 4:
                        sumaPrimerosDiezDigitos = sumaPrimerosDiezDigitos + (posicionPrimera * 9);
                        break;
                    case 5:
                        sumaPrimerosDiezDigitos = sumaPrimerosDiezDigitos + (posicionPrimera * 7);
                        break;
                    case 6:
                        sumaPrimerosDiezDigitos = sumaPrimerosDiezDigitos + (posicionPrimera * 3);
                        break;
                    case 7:
                        sumaPrimerosDiezDigitos = sumaPrimerosDiezDigitos + (posicionPrimera * 6);
                        break;
                }

            }

            int sumaSegundosDiezDigitos = 0;
            String segundosDiezDigitos = "";

            //Para obtener los últimos 10 dígitos
            for (int j = 9; j < cuenta.length(); j++) {

                char poscSegundaParteCuenta = cuenta.charAt(j);
                int posocionSegunda = Integer.parseInt(String.valueOf(poscSegundaParteCuenta));
                segundosDiezDigitos = segundosDiezDigitos + posocionSegunda;



                switch (j) {
                    case 10:
                        sumaSegundosDiezDigitos = sumaSegundosDiezDigitos + (posocionSegunda);
                        break;
                    case 11:
                        sumaSegundosDiezDigitos = sumaSegundosDiezDigitos + (posocionSegunda * 2);
                        break;
                    case 12:
                        sumaSegundosDiezDigitos = sumaSegundosDiezDigitos + (posocionSegunda * 4);
                        break;
                    case 13:
                        sumaSegundosDiezDigitos = sumaSegundosDiezDigitos + (posocionSegunda * 8);
                        break;
                    case 14:
                        sumaSegundosDiezDigitos = sumaSegundosDiezDigitos + (posocionSegunda * 5);
                        break;
                    case 15:
                        sumaSegundosDiezDigitos = sumaSegundosDiezDigitos + (posocionSegunda * 10);
                        break;
                    case 16:
                        sumaSegundosDiezDigitos = sumaSegundosDiezDigitos + (posocionSegunda * 9);
                        break;
                    case 17:
                        sumaSegundosDiezDigitos = sumaSegundosDiezDigitos + (posocionSegunda * 7);
                        break;
                    case 18:
                        sumaSegundosDiezDigitos = sumaSegundosDiezDigitos + (posocionSegunda * 3);
                        break;
                    case 19:
                        sumaSegundosDiezDigitos = sumaSegundosDiezDigitos + (posocionSegunda * 6);
                        break;
                }
            }

            int dc = 0;
            int dc2 = 0;
            int dcFinal;

            // los 10 primeros y 10 últimos números que se han obtenido se dividen entre 11 y el resultado se le resta a 11.
            sumaPrimerosDiezDigitos = sumaPrimerosDiezDigitos % 11;
            sumaPrimerosDiezDigitos = 11 - sumaPrimerosDiezDigitos;

            sumaSegundosDiezDigitos = sumaSegundosDiezDigitos % 11;
            sumaSegundosDiezDigitos = 11 - sumaSegundosDiezDigitos;

            //si el número obtenido es 10, el dc se transforma a 1 y si es 11 se transforma en 0, y si no es el caso el dc se queda en el número obtenido en el paso anterior.
            if (sumaPrimerosDiezDigitos > 9) {
                if (sumaPrimerosDiezDigitos == 10) {
                    dc = 1;
                }

            } else {
                dc = sumaPrimerosDiezDigitos;
            }

            if (sumaSegundosDiezDigitos > 9) {
                if (sumaSegundosDiezDigitos == 10) {
                    dc2 = 1;
                }

            } else {
                dc2 = sumaSegundosDiezDigitos;
            }

            //Suma del contenido de los 2 Dígitos de Control.
            dcFinal = dc + dc2;

            //Se obtiene el dc de la cuenta que ha sido obtenido vía scanner.
            int dcEscrito = Integer.parseInt(String.valueOf(cuenta.charAt(8)) + cuenta.charAt(9));

            //Si los Dígitos de control coinciden la cuenta es válida, en caso contrario se repetirá el método de pago
            if (dcEscrito == dcFinal) {
                System.out.println("La cuenta " + cuenta + " es correcta. DC:" + dcEscrito + "->" + dcFinal);
                System.out.println("Se le ha procedido a cobrar " + Math.round(dineroAPagar) + "€");
                System.out.println("GRACIAS POR SU VISTA!!");
                System.exit(0);

            } else {
                System.out.println();
                System.out.println("La cuenta " + cuenta + " NO es correcta. DC:" + dcEscrito + "->" + dcFinal);
                System.out.println("Por favor vuelva a elegir:");
                metodoDePago(dineroAPagar);
            }

            //En caso de que la validación dá false, o sea menos que 20 dígitos numéricos te vuelve a pedir el método de pago
        } else {
            System.out.println("Formato incorrecto. Por favor, seleccione de nuevo.");
            metodoDePago(dineroAPagar);
        }
    }

    /**
     * Función principal.
     * @param args
     */
    public static void main(String[] args) {

        float dineroPagar;

        System.out.print("**********************************\nBienvenido a la pasarela de pagos\n**********************************\n");

        //Obtención del dinero a pagar.
        do {
            dineroPagar = cantidadPagar();
        } while (dineroPagar == -1.0f);
        // Si la validación da false te lo vuelve a pedir hasta que deje de devolver -1.0.


        //si el importe es menos o igual que cero te vuelve a pedir la cantidad a apagar
        while (dineroPagar <= 0) {
            System.out.println("El importe debe ser mayor que 0");
            do {
                dineroPagar = cantidadPagar();
            } while (dineroPagar == -1.0);
        }

        //Te pide el método de pago, y desde la función llama a las otras funciones.
        metodoDePago(dineroPagar);
    }
}
