//package Main;

/**
 * 
 * @author Pablo Sao
 * @version 1.3
 */

import java.io.*;
import java.util.StringTokenizer;


public class EvaluadorPostfix{

    
//    final static String PATH_DATOS = "datos.txt";
    final static String PATH_DATOS = "C:\\datos.txt";
    //identificador de los delimitadores para eliminarse en la informacion
    final static String DELIMITADOR = " \t\n\r\fABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz|°!\"#$%&()=?¡¿'\\´¨[]{}_-:.;,^`¬~";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Pila pila = new Pila();
        //iCalculadora calc = new Calculadora();
        
        //Implementación Andrea
        iCalculadora calc = new CalculadoraAndrea();
        double res = 0;
        double a, b;
        int cont = 1;
        int cont2 = 1;
        System.out.println(pila);
        
        StringTokenizer token = new StringTokenizer(getDataFile(), DELIMITADOR);
        /*
        Tokenizer va a separar / parsear el string proporionado por el metodo getDataFile(),
        este metodo toma un buffered reader y un FileReader para leer "Datos.txt"
        * */

        while(token.hasMoreTokens()){


            for (char c: token.nextToken().toCharArray()){
                /*
                Iteramos el String devuelto luego de convertirlo a arreglo de Char

                */
                if (c == '+' || c == '-' || c == '/' || c == '*'){
                    // si esto se cumple es un operador entonces usamos la calculadora
                    //  tambien verificamos si hay suficientes operandos
                    System.out.println("Encontramos un operador : " + Character.toString(c));
                    System.out.println("Stack size: " + pila.size());

                    if (pila.size() < 2){
                        continue;
                        //  no hay suficientas operandos, sigamos buscando operandos
                        // y se tomara el siguiente operador para la operacion

                    } else {
                        switch (c){
                            case '+':
                                while (!pila.empty()){
                                    if (pila.size() == 1){
                                        //  es el ultimo valor
                                        break;

                                    } else {
                                        res = calc.sumar((double)pila.pop(), (double)pila.pop());
                                        pila.push(res);
                                        break; //
                                    }

                                }


                                break;

                            case '-':
                                while (!pila.empty()){
                                    if (pila.size() == 1){
                                        //  es el ultimo valor
                                        break;

                                    } else {
                                        res = calc.restar((double)pila.pop(), (double)pila.pop());
                                        pila.push(res);
                                        break;
                                    }

                                }

                                break;

                            case '*':
                                while (!pila.empty()){
                                    if (pila.size() == 1){
                                        //  es el ultimo valor
                                        break;

                                    } else {
                                        res = calc.multiplicar((double)pila.pop(), (double)pila.pop());
                                        pila.push(res);
                                        break;
                                    }

                                }


                                break;

                            case '/':
                                while (!pila.empty()){
                                    if (pila.size() == 1){
                                        //  es el ultimo valor
                                        break;

                                    } else {
                                        res = calc.dividir((double)pila.pop(), (double)pila.pop());
                                        pila.push(res);
                                        break;
                                    }

                                }

                                break;
                        }
                    }

                } else if (Character.isDigit(c)){
                    pila.push((double)(c - '0'));
                    //  Ya que estamos trabajando con Characters, tienen un valor en ascii
                    // al restarles char '0' restamos 50 y eso nos da el valor real.
                    // tambien podiamos usar Double.parseDouble pero esto es mas cool.
                    System.out.println("Añadiendo a stack: " + c);
                    System.out.println("Stack size: " + pila.size());



                } else {
                    /*
                    Si no es ni numero ni operador podemos obviarlo.
                     */
                    continue;
                }
            }


        }
        System.out.println("Resultado final: " + Double.toString(res));
        

    }

    
    /***
     * Metodo para obtener en un string toda la información contenida dentro del archivo .txt
     * 
     * @return String todos los datos ingresados en el archivo de texto 
     */
    private static String getDataFile() throws IOException, FileNotFoundException{
        
        BufferedReader reader;
        File file;
        String linea,datos = "";
        try{
            if((new File(PATH_DATOS)).exists()){ //verificamos que el archivo exista
                
                
                reader = new BufferedReader(new FileReader(PATH_DATOS));
                
                while((linea = reader.readLine()) != null){
                    //concatenamos con un tabular la lectura de la linea,
                    //el tabular se eliminara al separar las expresiones.
                    datos += linea + "\t";
                }
                
                reader.close();
            }
            else{
                System.out.println("El archivo ingresado no fue encontrado.");
            }
            
        }
        //Tomaremos todo tipo de error en la ejecución del bloque de codigo dentro del catch
        catch(Exception e){
            e.printStackTrace();
        }
        
        return datos;
    }
    
}
