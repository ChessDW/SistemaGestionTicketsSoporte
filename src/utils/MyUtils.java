package utils;

import java.util.Scanner;
import java.util.function.Predicate;

public final class MyUtils {
    private static Scanner sc = new Scanner(System.in);

    public static <T> T pedirDato(String mensaje, Class<T> tipo, Predicate<T> validador){
        while(true){
            System.out.print(mensaje + ": ");
            String entrada = sc.nextLine();
            try{
                T valor = convertir(entrada, tipo);
                if(validador == null || validador.test(valor)) {
                    return valor;
                };
                System.out.println("Valor fuera del rango");
            } catch (Exception e){
                System.out.println("Formato incorrecto");
            }
        }
    }
    private static <T> T convertir(String str, Class<T> tipo){
        if(tipo == Integer.class) return (T) Integer.valueOf(str);
        if(tipo == Double.class) return (T) Double.valueOf(str);
        if(tipo == String.class) return (T) str;

        throw new RuntimeException("Tipo no soportado");
    }

    public static String pedirStringOpcional(String mensaje){
        System.out.print(mensaje + ": ");
        String entrada = sc.nextLine();
        if(entrada.trim().isEmpty()){
            return null;
        }
        return entrada;
    }

    public static <E extends Enum<E>> E pedirEnum(String mensaje, Class<E> enumClass){
        E[] constantes = enumClass.getEnumConstants();

        System.out.println(mensaje + ": ");
        for(int i = 0; i < constantes.length; i++){
            System.out.println(" " + (i + 1) + ". " + constantes[i].name());
        }
        while (true){
            System.out.print("Elige (1-" + constantes.length + "): ");
            String entrada = sc.nextLine().trim();

            try{
                int seleccion = Integer.parseInt(entrada) - 1;
                if(seleccion >= 0 && seleccion < constantes.length){
                    return constantes[seleccion];
                }
                System.out.println("Número fuera de rango");
            } catch(NumberFormatException e){
                // intentar por nombre
                try{
                    return Enum.valueOf(enumClass, entrada.toUpperCase());
                } catch (IllegalArgumentException ex) {
                    System.out.println("Opción inválida");
                }
            }
        }
    }
}
